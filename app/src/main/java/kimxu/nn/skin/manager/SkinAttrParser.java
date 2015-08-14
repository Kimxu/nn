package kimxu.nn.skin.manager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

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
	 * 导航页标签栏皮肤解析器
	 * 	R.attr.navigationTabStripIconList,
	 R.attr.navigationTabStripTextColor
	 */
	public static class NavigationTabStrip {
		private static final int[] ATTRS = new int[]{

				};
		
		private TypedArray typedArray;
		
		public NavigationTabStrip(Context context){
			Skin skin = SkinHolder.getSkin(context);
			if(skin == null){
				return;
			}
			typedArray = context.obtainStyledAttributes(skin.getThemeId(), ATTRS);
		}
		
		public Drawable getRecommendDrawable(){
			if(typedArray == null || typedArray.length() <= 0){
				return null;
			}
			return typedArray.getDrawable(0);
		}
		
		public Drawable getCategoryDrawable(){
			if(typedArray == null || typedArray.length() <= 1){
				return null;
			}
			return typedArray.getDrawable(1);
		}
		
		public Drawable getNewGameDrawable(){
			if(typedArray == null || typedArray.length() <= 2){
				return null;
			}
			return typedArray.getDrawable(2);
		}
		
		public Drawable getNewsDrawable(){
			if(typedArray == null || typedArray.length() <= 3){
				return null;
			}
			return typedArray.getDrawable(3);
		}
		
		public Drawable getGroupDrawable(){
			if(typedArray == null || typedArray.length() <= 4){
				return null;
			}
			return typedArray.getDrawable(4);
		}
		
		public Drawable getListDrawable(){
			if(typedArray == null || typedArray.length() <= 5){
				return null;
			}
			return typedArray.getDrawable(5);
		}
		
		public int getTextColor(){
			if(typedArray == null || typedArray.length() <= 6){
				return Color.BLACK;
			}
			return typedArray.getColor(6, Color.BLACK);
		}
		
		public Drawable getBackgroundDrawable(){
			if(typedArray == null || typedArray.length() <= 7){
				return null;
			}
			return typedArray.getDrawable(7);
		}
		
		public Drawable getSlidingBlockDrawable(){
			if(typedArray == null || typedArray.length() <= 8){
				return null;
			}
			return typedArray.getDrawable(8);
		}
		
		public void recycle(){
			if(typedArray == null){
				return;
			}
			typedArray.recycle();
		}
	}
	
	/**
	 * 排序菜单图标解析器
	 * 	R.attr.actionBarMenuIconFilterByInstalled,
	 R.attr.actionBarMenuIconFilterByBroken,
	 */
	public static class SortMenuIcon {
		private static final int[] ATTRS = new int[] {

			};

		private TypedArray typedArray;

		public SortMenuIcon(Context context) {
			Skin skin = SkinHolder.getSkin(context);
			if (skin == null) {
				return;
			}
			typedArray = context.obtainStyledAttributes(skin.getThemeId(), ATTRS);
		}

		public Drawable getDownloadDrawable() {
			if (typedArray == null || typedArray.length() <= 0) {
				return null;
			}
			return typedArray.getDrawable(0);
		}

		public Drawable getHotDrawable() {
			if (typedArray == null || typedArray.length() <= 1) {
				return null;
			}
			return typedArray.getDrawable(1);
		}

		public Drawable getNameDrawable() {
			if (typedArray == null || typedArray.length() <= 2) {
				return null;
			}
			return typedArray.getDrawable(2);
		}

		public Drawable getSizeDrawable() {
			if (typedArray == null || typedArray.length() <= 3) {
				return null;
			}
			return typedArray.getDrawable(3);
		}

		public Drawable getTimeDrawable() {
			if (typedArray == null || typedArray.length() <= 4) {
				return null;
			}
			return typedArray.getDrawable(4);
		}

		public Drawable getSelectDrawable() {
			if (typedArray == null || typedArray.length() <= 5) {
				return null;
			}
			return typedArray.getDrawable(5);
		}
		public Drawable getViewDrawable() {
			if (typedArray == null || typedArray.length() <= 6) {
				return null;
			}
			return typedArray.getDrawable(6);
		}
		public Drawable getCountDrawable() {
			if (typedArray == null || typedArray.length() <= 7) {
				return null;
			}
			return typedArray.getDrawable(7);
		}
		public Drawable getSupportDrawable() {
			if (typedArray == null || typedArray.length() <= 8) {
				return null;
			}
			return typedArray.getDrawable(8);
		}
		public Drawable getInstalledDrawable() {
			if (typedArray == null || typedArray.length() <= 9) {
				return null;
			}
			return typedArray.getDrawable(9);
		}

		public Drawable getBrokenDrawable() {
			if (typedArray == null || typedArray.length() <= 10) {
				return null;
			}
			return typedArray.getDrawable(10);
		}

		public Drawable getDuplicateDrawable() {
			if (typedArray == null || typedArray.length() <= 11) {
				return null;
			}
			return typedArray.getDrawable(11);
		}


		public void recycle() {
			if (typedArray == null) {
				return;
			}
			typedArray.recycle();
		}
	}
}
