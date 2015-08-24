package kimxu.nn.skin.manager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import kimxu.nn.R;
import kimxu.nn.skin.Skin;
import kimxu.nn.skin.SkinHolder;

/**
 * 皮肤属性解析器，记得用完之后调用recycle()方法
 */
public class SkinAttrParser {
	private TypedArray typedArray;

	public SkinAttrParser(Context context, int[] attrs) {
		Skin skin = SkinHolder.getSkin(context);
		if (skin == null) {
			return;
		}
		typedArray = context.obtainStyledAttributes(skin.getThemeId(), attrs);
	}

	public TypedArray getTypedArray(){
		return typedArray;
	}

	public void recycle() {
		if (typedArray == null) {
			return;
		}
		typedArray.recycle();
	}
	/**
	 * 主页icon
	 */
	public static class MainIcon {


		private TypedArray typedArray;

		public MainIcon(Context context) {
			Skin skin = SkinHolder.getSkin(context);
			if (skin == null) {
				return;
			}
			typedArray = context.obtainStyledAttributes(skin.getThemeId(), R.styleable.MainIcon);
		}
		public Drawable getMenuIcon() {
			if (typedArray == null || typedArray.length() <= 0) {
				return null;
			}
			return typedArray.getDrawable(R.styleable.MainIcon_menuIcon);
			//return null;
		}

		public void recycle() {
			if (typedArray == null) {
				return;
			}
			typedArray.recycle();
		}
	}

}
