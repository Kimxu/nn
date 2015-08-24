package kimxu.nn.atys;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.race604.flyrefresh.FlyRefreshLayout;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import kimxu.nn.R;
import kimxu.nn.adapter.AccountBookAdapter;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.list.SampleItemAnimator;
import kimxu.nn.model.Bill;
import kimxu.nn.skin.NoActionBarTheme;
import kimxu.nn.utils.GlobalUtil;
import kimxu.nn.utils.logger.Klog;

/**
 * 账单
 */
@NoActionBarTheme
public class AtyAccountBook extends AtySupport implements FlyRefreshLayout.OnPullRefreshListener {

    private FlyRefreshLayout mFlylayout;
    private RecyclerView mListView;

    private AccountBookAdapter mAdapter;

    private Handler mHandler;
    private LinearLayoutManager mLayoutManager;
    protected MenuItem refreshItem;


    private static final int RESULT_SUCCESS_HAVE_DATA = 0010;
    private static final int RESULT_SUCCESS_NO_DATA = 0012;
    private static final int RESULT_ERROR = 0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_account_book);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mFlylayout = (FlyRefreshLayout) findViewById(R.id.fly_layout);

        mFlylayout.setOnPullRefreshListener(this);

        mListView = (RecyclerView) findViewById(R.id.list);

        mLayoutManager = new LinearLayoutManager(this);

        mListView.setItemAnimator(new SampleItemAnimator());

        mListView.setLayoutManager(mLayoutManager);


        initDatas();


        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (mActivity != null) {
                    switch (msg.what) {
                        case RESULT_SUCCESS_HAVE_DATA:
                            mFlylayout.onRefreshFinish();
                            break;
                        case RESULT_SUCCESS_NO_DATA:
                            GlobalUtil.showToast(mActivity, "没有新数据刷新啦");
                            mFlylayout.onRefreshFinish();

                            break;
                        case RESULT_ERROR:
                            mFlylayout.onRefreshFinish();

                            break;
                    }
                }

            }
        };


    }

    @Override
    public String getActivityName() {
        return "AtyAccountBook";
    }

    List<Bill> tempData = null;

    private void addItemData() {
        if(createdAt==null)
            return;

        BmobQuery<Bill> query = new BmobQuery<>();
        query.addWhereGreaterThan("createdAt", createdAt);
        query.addWhereEqualTo(Bill.USERNAME, getUserName());
        query.order("-createdAt");
        query.findObjects(mActivity, new FindListener<Bill>() {
            @Override
            public void onSuccess(List<Bill> list) {
                if (list.size() == 1) {
                    Message msg = Message.obtain();
                    msg.what = RESULT_SUCCESS_NO_DATA;
                    mHandler.sendMessage(msg);
                    return;
                }

                Message msg = Message.obtain();
                msg.what = RESULT_SUCCESS_HAVE_DATA;
                list.remove(0);
                tempData = list;
                createdAt = GlobalUtil.dateToBmobDate(list.get(0).getCreatedAt());
                mHandler.sendMessage(msg);
            }

            @Override
            public void onError(int i, String s) {
                Message msg = Message.obtain();

                msg.what = RESULT_ERROR;
                mHandler.sendMessage(msg);

            }
        });

//        mAdapter.notifyItemInserted(0);
//        mLayoutManager.scrollToPosition(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_account) {
            showRefreshAnimation(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRefreshAnimation(MenuItem item) {
        hideRefreshAnimation();
        refreshItem = item;
        //这里使用一个ImageView设置成MenuItem的ActionView，这样我们就可以使用这个ImageView显示旋转动画了
        ImageView refreshActionView = (ImageView) getLayoutInflater().inflate(R.layout.grid_list_view, null);
        refreshActionView.setImageResource(R.drawable.ic_add_white_18dp);
        refreshItem.setActionView(refreshActionView);
        //显示刷新动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.trip);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        refreshActionView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AtyAdd.startMe(mActivity);
                refreshItem.setActionView(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void hideRefreshAnimation() {
        if (refreshItem != null) {
            View view = refreshItem.getActionView();
            if (view != null) {
                view.clearAnimation();
                refreshItem.setActionView(null);
            }
        }
    }

    private BmobDate createdAt =null;

    private void initDatas() {
        BmobQuery<Bill> query = new BmobQuery<Bill>();

        String sql = "select * from Bill where username = ? order by -createdAt";

        query.doSQLQuery(mActivity, sql, new SQLQueryListener<Bill>() {
            @Override
            public void done(BmobQueryResult<Bill> bmobQueryResult, BmobException e) {
                if (e == null) {
                    List<Bill> list = bmobQueryResult.getResults();
                    if (list != null && list.size() > 0) {
                        createdAt = GlobalUtil.dateToBmobDate(list.get(0).getCreatedAt());

                        mAdapter = new AccountBookAdapter(mActivity, list);
                        mListView.setAdapter(mAdapter);
                    } else {
                        Log.i("smile", "查询成功，无数据返回");
                    }
                } else {
                    Klog.i("错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }

            }
        }, getUserName());
    }


    @Override
    public void onRefresh(FlyRefreshLayout view) {


        View child = mListView.getChildAt(0);
        if (child != null) {
            bounceAnimateView(child.findViewById(R.id.icon));
        }

        addItemData();
    }

    private void bounceAnimateView(View view) {
        if (view == null) {
            return;
        }

        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
        swing.setDuration(400);
        swing.setInterpolator(new AccelerateInterpolator());
        swing.start();
    }

    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {
        if (tempData != null) {
            mAdapter.addDatas(tempData);
            tempData = null;
        }

    }


    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyAccountBook.class);
        context.startActivity(intent);


    }

}

