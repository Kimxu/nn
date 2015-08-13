package kimxu.nn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kimxu.nn.R;
import kimxu.nn.model.Bill;

public class AccountBookAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<Bill> datas;
    public AccountBookAdapter(Context context,List<Bill> datas) {
        this.context=context;
        this.datas=datas;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_account, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Bill bill =datas.get(position);
        holder.icon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.type_big_1));
        holder.title.setText("消费了" + bill.getMoney());
        holder.subTitle.setText(bill.getCreatedAt());

    }

    public void addDatas(List<Bill> data){
        this.datas.addAll(0,data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}

 class ItemViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView title;
    TextView subTitle;

    public ItemViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
        subTitle = (TextView) itemView.findViewById(R.id.subtitle);
    }

}





