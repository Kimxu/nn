package kimxu.nn.adapter;

/**
 * Created by xuzhiguo on 15/7/24.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import kimxu.nn.R;

public class TimelineAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    public TimelineAdapter(Context context, List<Map<String, Object>> list) {
        super();
        this.context = context;
        this.list = list;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.row_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String desc = list.get(position).get("desc").toString();
        String time = list.get(position).get("time").toString();
        viewHolder.desc.setText(desc);
        viewHolder.time.setText(time);

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'row_time.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.icon)
        ImageView icon;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.desc)
        TextView desc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
