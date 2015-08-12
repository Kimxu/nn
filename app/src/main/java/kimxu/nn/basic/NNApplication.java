package kimxu.nn.basic;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

import cn.bmob.v3.Bmob;
import kimxu.nn.AppConstant;
import kimxu.nn.utils.logger.Klog;

/**
 * Main Application
 */
public class NNApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "e7b8b1d573eb278d17571898bd971f67");
        Klog.getInstance().setDebug(AppConstant.IS_DEBUG);
        MobclickAgent.setDebugMode(true);
    }

}
