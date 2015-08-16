package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kimxu.arcmenu.RayMenu;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.skin.NoActionBarTheme;
import kimxu.nn.skin.manager.SkinAttrParser;

@NoActionBarTheme
public class AtyMain extends AtySupport {


    private static final int[] ITEM_DRAWABLES = {R.drawable.composer_camera, R.drawable.composer_music,
            R.drawable.composer_place, R.drawable.composer_sleep, R.drawable.composer_thought, R.drawable.composer_with};
    @Bind(R.id.ray_menu)
    RayMenu rayMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        ButterKnife.bind(this);
        initMenu();
    }


    public void initMenu() {

        SkinAttrParser.MainIcon  skinAttrParser= new SkinAttrParser.MainIcon(mActivity);
        final ViewGroup controlLayout = rayMenu.getControlLayout();
        controlLayout.setBackground(skinAttrParser.getMenuIcon());
        skinAttrParser.recycle();
        final int itemCount = ITEM_DRAWABLES.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(this);
            item.setImageResource(ITEM_DRAWABLES[i]);

            final int position = i;
            rayMenu.addItem(item, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMenuIconOnClick(position);

                }
            });
        }
    }


    private final int P_SETTING=5;
    private final int P_ACCOUNTBOOK=1;
    public void setMenuIconOnClick(int position){
        switch (position){

            case P_ACCOUNTBOOK:
                AtyAccountBook.startMe(mActivity);
                break;
            case P_SETTING:
                 AtySetting.startMe(mActivity);
                break;
        }
    }


    @Override
    public String getActivityName() {
        return "AtyMain";
    }


    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyMain.class);
        context.startActivity(intent);

    }

}
