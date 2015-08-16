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

    DEFAULT("\ue60d\ue614", R.style.Skin_Light, R.style.Skin_Light_NoActionBar, Color.parseColor("#ffff7642")) {
        @Override
        public Drawable getPreviewDrawable(Context context) {
            return new ColorDrawable(Color.parseColor("#ffff7642"));
        }
    },
    DRAK("\ue619\ue608", R.style.Skin_Dark,R.style.Skin_Dark_NoActionBar, Color.parseColor("#DB4D6D")) {


        @Override
        public Drawable getPreviewDrawable(Context context) {
            return new ColorDrawable(Color.parseColor("#DB4D6D"));
        }
    };



    private String name;
    private int themeId;
    private int colorPrimary;
    private int noActionBarThemeId;

    SkinEnum(String name, int themeId,int noActionBarThemeId, int colorPrimary) {
        this.name = name;
        this.noActionBarThemeId = noActionBarThemeId;
        this.themeId = themeId;
        this.colorPrimary = colorPrimary;
     }

    @Override
    public int getNoActionBarThemeId() {
        return noActionBarThemeId;
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
    public int getColorPrimary() {
        return colorPrimary;
    }



    /**
     * 比正常颜色浅
     * @return int
     */
    @Override
    public int getColorPrimaryDark() {
        return ColorUtil.darken(colorPrimary);
    }


    /**
     * 获取在主题切换页面显示的预览图片
     *
     * @param context .
     * @return Drawable
     */
    public abstract Drawable getPreviewDrawable(Context context);
}