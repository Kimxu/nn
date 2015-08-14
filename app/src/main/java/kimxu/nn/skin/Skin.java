package kimxu.nn.skin;


/**
 * 皮肤接口
 */
public interface Skin {


    String getEnumName();

    /**
     * 获取皮肤名称
     *
     * @return
     */
    String getName();

    /**
     * 获取主题ID
     *
     * @return
     */
    int getThemeId();

    /**
     * 获取没有ActionBarID
     *
     * @return
     */
    int getNoActionBarThemeId();

    int getColorPrimary();

    int getColorPrimaryDark();
}
