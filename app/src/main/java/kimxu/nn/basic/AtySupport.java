package kimxu.nn.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import kimxu.nn.skin.SkinHolder;

/**
 *  父类activity
 * */
public abstract class AtySupport extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        SkinHolder.applySkin(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mActivity);
        MobclickAgent.onPageStart(getActivityName()); //统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mActivity);
        MobclickAgent.onPageEnd(getActivityName());
    }

    public abstract String getActivityName();

}