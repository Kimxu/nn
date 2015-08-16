package kimxu.nn.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import java.lang.annotation.Annotation;

import kimxu.nn.AppConstant;
import kimxu.nn.skin.SkinHolder;
import kimxu.nn.skin.StatusBarManager;
import kimxu.nn.skin.TransparentrTitleBarTheme;
import kimxu.nn.utils.PreferenceUtil;

/**
 * 父类activity
 */
public abstract class AtySupport extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        setTitleBarColor();
        SkinHolder.applySkin(this);
        super.onCreate(savedInstanceState);

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


    public String getUserName() {
        return PreferenceUtil.getString(mActivity, AppConstant.USERNAME, "");
    }

    public abstract String getActivityName();


    private void setTitleBarColor() {
        Class clazz = mActivity.getClass();
        if (clazz.isAnnotationPresent(TransparentrTitleBarTheme.class)) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
               if (annotation.annotationType().getName().equals(TransparentrTitleBarTheme.class.getName())) {
                    TransparentrTitleBarTheme tTbT = (TransparentrTitleBarTheme) annotation;
                    StatusBarManager.setStatusBarColor(this, getResources().getColor(tTbT.color()));
                   break;
                }
            }
        } else {
            StatusBarManager.applyTranslucentStatusBar(this);
        }

    }
}