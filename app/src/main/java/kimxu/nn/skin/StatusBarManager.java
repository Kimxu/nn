package kimxu.nn.skin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

public class StatusBarManager {
	
	@SuppressLint("NewApi")
	public static void applyTranslucentStatusBar(Activity activity) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && 
				!activity.getClass().isAnnotationPresent(DisableUseTranslucentStatus.class)) {
			
			//int color = Color.BLACK;
			int color = SkinHolder.getColorPrimary(activity);
			//activity.getResources().getColor(R.color.red_btn_bg_color)
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				//棒棒糖以上，使用系统支持的设置方式
				//有些手机乱改版本号，导致判断错误，走了错的分支，冇該
				try{
					activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
					activity.getWindow().setStatusBarColor(color);
				} catch (Exception ignored) {
					
				}
			} else {
				//奇巧，使用透明状态栏加上改背景色的方式
				activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				SystemBarTintManager manager = new SystemBarTintManager(activity);
				manager.setStatusBarTintEnabled(true);
				manager.setStatusBarTintColor(color);
			}
		}
	}
	
	@SuppressLint("NewApi")
	public static void setStatusBarColor(Activity activity, int color){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				//棒棒糖以上，使用系统支持的设置方式
				//有些手机乱改版本号，导致判断错误，走了错的分支，冇該
				try{
					activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
					activity.getWindow().setStatusBarColor(color);
				} catch (Exception e) {
					
				}
			} else {
				//奇巧，使用透明状态栏加上改背景色的方式
				activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				SystemBarTintManager manager = new SystemBarTintManager(activity);
				manager.setStatusBarTintEnabled(true);
				manager.setStatusBarTintColor(color);
			}
		}
	}

}
