package kimxu.nn.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kimxu.nn.R;

public class ShowIconAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> list;
    private LayoutInflater inflater;

    public ShowIconAdapter(Context context) {
        super();
        this.context = context;
        list = new ArrayList<>();
        addDatas(list);
    }

    private void addDatas(List<Integer> list) {
        for (int i = 0; i <= 39; i++) {//循环装载所有名字类似的资源如“a1、a2……a15”的图片
            int id = context.getResources().getIdentifier("type_big_" + i, "drawable", context.getPackageName());
            list.add(id);
        }
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {

        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int did =list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item_show_icon, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setBackground(context.getResources().getDrawable(did));
        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.icon)
        ImageView icon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
