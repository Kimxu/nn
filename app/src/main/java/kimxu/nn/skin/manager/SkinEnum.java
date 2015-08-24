package kimxu.nn.skin.manager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import kimxu.nn.LogConstant;
import kimxu.nn.R;
import kimxu.nn.skin.Skin;
import kimxu.nn.utils.ColorUtil;

/**
 * 皮肤枚举对象
 */
public enum SkinEnum implements Skin {

    DEFAULT("默认", R.style.Skin_Light, R.style.Skin_Light_NoActionBar, Color.parseColor("#ffff7642"), LogConstant.SKIN_SELECTED_DEFAULT) {
        @Override
        public Drawable getPreviewDrawable(Context context) {
            return new ColorDrawable(Color.parseColor("#ffff7642"));
        }
    },
    DRAK("暗色", R.style.Skin_Dark,R.style.Skin_Dark_NoActionBar, Color.parseColor("#aaa222"),LogConstant.SKIN_SELECTED_DARK) {


        @Override
        public Drawable getPreviewDrawable(Context context) {
            return new ColorDrawable(Color.parseColor("#aaa222"));
        }
    };



    private String name;
    private int themeId;
    private int colorPrimary;
    private int noActionBarThemeId;
    private String umengLog;
    SkinEnum(String name, int themeId,int noActionBarThemeId, int colorPrimary, String umengLog) {
        this.name = name;
        this.noActionBarThemeId = noActionBarThemeId;
        this.themeId = themeId;
        this.colorPrimary = colorPrimary;
        this.umengLog=umengLog;
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


    public String getUmengLog() {
        return umengLog;
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