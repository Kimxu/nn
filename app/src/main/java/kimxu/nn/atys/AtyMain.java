package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import kimxu.arcmenu.RayMenu;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.R;
import kimxu.nn.adapter.TimelineAdapter;


public class AtyMain extends AtySupport {


    private static final int[] ITEM_DRAWABLES = {R.drawable.composer_camera, R.drawable.composer_music,
            R.drawable.composer_place, R.drawable.composer_sleep, R.drawable.composer_thought, R.drawable.composer_with};
    @Bind(R.id.ray_menu)
    RayMenu rayMenu;
    @Bind(R.id.list_view)
    PullToRefreshListView listView;


    private void initListView() {
        listView.getRefreshableView().setDividerHeight(0);
        listView.getRefreshableView().setDivider(null);
        listView.getRefreshableView().setSelector(android.R.color.transparent);

        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        listView.getLoadingLayoutProxy(false, true).setPullLabel(getString(R.string.pull_to_load));
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel(getString(R.string.loading));
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel(getString(R.string.release_to_load));
        listView.getLoadingLayoutProxy(true, false).setPullLabel(getString(R.string.pull_to_load));
        listView.getLoadingLayoutProxy(true, false).setRefreshingLabel(getString(R.string.loading));
        listView.getLoadingLayoutProxy(true, false).setReleaseLabel(getString(R.string.release_to_load));

        TimelineAdapter timelineAdapter = new TimelineAdapter(this, getData());
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.e("666");
                new MyTask().execute();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.i("777");
                new MyTask().execute();

            }
        });

        listView.setAdapter(timelineAdapter);
    }

    private class MyTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);//睡眠2秒，延迟加载数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> mArrayList = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
//                counter++;
//                mArrayList.add("-----" + String.valueOf(counter) + "-------");
            }
            return mArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            for (String string : result) {
                // adapter.add(string);
            }
            listView.onRefreshComplete();//数据加载到适配器完成后，刷新完成，
            super.onPostExecute(result);
        }
    }

    public void initMenu() {
        final int itemCount = ITEM_DRAWABLES.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(this);
            item.setImageResource(ITEM_DRAWABLES[i]);

            final int position = i;
            rayMenu.addItem(item, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AtyMain.this, "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("desc", "这是第1行测试数据");
        map.put("time", "2015年07月7日");
        list.add(map);

        map = new HashMap<String, Object>();

        map.put("desc", "这是第2行测试数据");
        map.put("time", "2015年07月7日");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("desc", "这是第3行测试数据");
        map.put("time", "2015年07月7日");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("desc", "这是第4行测试数据");
        map.put("time", "2015年07月7日");

        list.add(map);
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initListView();
        initMenu();
    }

    @Override
    public String getActivityName() {
        return "AtyMain";
    }


    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyMain.class);
        context.startActivity(intent);



    }

}
