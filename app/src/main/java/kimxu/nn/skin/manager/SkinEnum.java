package kimxu.nn.skin.manager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import kimxu.nn.R;
import kimxu.nn.skin.Skin;
import kimxu.nn.utils.ColorUtil;

/**
 * 皮肤枚举对象
 */
public enum SkinEnum implements Skin {
	
	DEFAULT("\ue60d\ue614", R.style.Skin_Light, R.style.Skin_Light, Color.parseColor("#F1F1F1")){
		@Override
		public Drawable getPreviewDrawable(Context context) {
			return new ColorDrawable(Color.parseColor("#F1F1F1"));
		}
	},
		DRAK("\ue619\ue608", R.style.Skin_Dark, R.style.Skin_Dark, Color.parseColor("#DB4D6D")){
		@Override
		public Drawable getPreviewDrawable(Context context) {
			return new ColorDrawable(Color.parseColor("#DB4D6D"));
		}
	};
	

	
	private String name;
	private int themeId;
	private int noShadowThemeId;
	private int colorPrimary;
	
	private SkinEnum(String name, int themeId, int noShadowThemeId, int colorPrimary) {
		this.name = name;
		this.themeId = themeId;
		this.noShadowThemeId = noShadowThemeId;
		this.colorPrimary = colorPrimary;
	}
	
	public String getEnumName() {
		return this.name();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getThemeId() {
		return themeId;
	}

	@Override
	public int getNoShadowThemeId() {
		return noShadowThemeId;
	}
	

	public int getColorPrimary() {
		return colorPrimary;
	}
	
	public int getColorPrimaryDark(){
		return ColorUtil.darken(colorPrimary);
	}

	/**
	 * 获取在主题切换页面显示的预览图片
	 * @param context
	 * @return
	 */
	public abstract Drawable getPreviewDrawable(Context context);
}