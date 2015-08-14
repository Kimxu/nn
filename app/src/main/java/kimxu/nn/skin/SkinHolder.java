package kimxu.nn.skin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import kimxu.nn.utils.logger.Klog;

/**
 * 当前皮肤持有者，用来获取和设置当前皮肤
 */
public class SkinHolder {
	private static Skin currentSkin;
	
	private SkinHolder(){
	}
	
	/**
	 * 设置皮肤
	 * @param shin .
	 */
	public static void setSkin(Skin shin){
		SkinHolder.currentSkin = shin;
	}
	
	/**
	 * 获取当前皮肤
	 * @param context .
	 * @return Skin
	 */
	public static Skin getSkin(Context context){
		if(currentSkin == null){
			try {
				Class<?> skinManagerClass = Class.forName("kimxu.nn.skin.manager.SkinManager");
				Constructor<?> constructor = skinManagerClass.getConstructor(Context.class);
				constructor.newInstance(context);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentSkin;
	}
	
	public static int getColorPrimary(Context context){
		int color = Color.BLACK;
		try{
			Class<?> skinManagerClass = Class.forName("kimxu.nn.skin.manager.SkinManager");
			Constructor<?> constructor = skinManagerClass.getConstructor(Context.class);
			Method method = skinManagerClass.getMethod("getColorPrimary");
			color = (Integer) method.invoke(constructor.newInstance(context));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}
	/**
	 * 比正常浅
	 * @param context .
	 * @return int
	 */
	public static int getColorPrimaryDark(Context context){
		int color = Color.BLACK;
		try{
			Class<?> skinManagerClass = Class.forName("kimxu.nn.skin.manager.SkinManager");
			Constructor<?> constructor = skinManagerClass.getConstructor(Context.class);
			Method method = skinManagerClass.getMethod("getColorPrimaryDark");
			color = (Integer) method.invoke(constructor.newInstance(context));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}

	/**
	 * icon颜色
	 * @param context .
	 * @return int
	 */
	public static int getColorIcon(Context context){
		int color = Color.BLACK;
		try{
			Class<?> skinManagerClass = Class.forName("kimxu.nn.skin.manager.SkinManager");
			Constructor<?> constructor = skinManagerClass.getConstructor(Context.class);
			Method method = skinManagerClass.getMethod("getColorIcon");
			color = (Integer) method.invoke(constructor.newInstance(context));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}




	/**
     * 应用皮肤
     */
    public static void applySkin(Activity activity){
    	if(activity == null){
    		new IllegalArgumentException("activity is null").printStackTrace();
    		return;
    	}
    	if(activity.getClass().isAnnotationPresent(DisableUseSkin.class)){
    		Klog.d(activity.getClass().getName() + " disable use skin");
    		return;
    	}


		Skin skin = SkinHolder.getSkin(activity.getBaseContext());
		if(skin == null){
			new IllegalArgumentException("not found skin").printStackTrace();
			return;
		}
		int themeId = activity.getClass().isAnnotationPresent(NoActionBarTheme.class)?skin.getNoActionBarThemeId():skin.getThemeId();
//		if (activity.getClass().isAnnotationPresent(TransparentrTitleBarTheme.class)){
//			StatusBarManager.setStatusBarColor(activity, activity.getResources().getColor(R.color.transparent));
//		}
		activity.setTheme(themeId);
    }
}
