package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.basic.NNApplication;
import kimxu.nn.log.SmartLogger;
import kimxu.nn.skin.SkinHolder;
import kimxu.nn.skin.manager.SkinEnum;
import kimxu.nn.skin.manager.SkinManager;

public class AtySettingSkin extends AtySupport {

    //private static Typeface typeface;


    @Bind(R.id.grid_show_skin)
    GridView gridShowSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_setting_skin);
        ButterKnife.bind(this);
        //typeface = Typeface.createFromAsset(getAssets(),"fonts/color_text.ttf");
        final GridAdapter skinAdapter = new GridAdapter(getBaseContext(), SkinEnum.values());
        gridShowSkin.setAdapter(skinAdapter);
        gridShowSkin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SkinEnum skinEnum = skinAdapter.getSkinEnums()[position];
                new SkinManager(getBaseContext()).setSkin(skinEnum);
                SmartLogger.beginTransaction().umeng(skinEnum.getUmengLog()).commit(mActivity);
                // 通知首页需要更新皮肤了
                NNApplication.updateSkin = true;

                // 回到首页并把首页和当前页面之间的所有页面全部干掉
                startActivity(new Intent(getBaseContext(), AtyMain.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });

    }

    @Override
    public String getActivityName() {
        return "AtySettingSkin";
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtySettingSkin.class);
        context.startActivity(intent);

    }

    private static class GridAdapter extends BaseAdapter{
        private Context context;
        private SkinEnum[] skinEnums;
        private SkinEnum skinEnum;
        private int itemSize;
        public GridAdapter(Context context, SkinEnum[] skinEnums){
            this.context = context;
            this.skinEnums = skinEnums;
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            screenWidth -= dp2px(context, (4+16+8)*2);
            itemSize = screenWidth / 3;
        }
        /**
         * dp单位转换为px
         * @param context 上下文，需要通过上下文获取到当前屏幕的像素密度
         * @param dpValue dp值
         * @return px值
         */
        public int dp2px(Context context, float dpValue){
            return (int)(dpValue * (context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        @Override
        public int getCount() {
            return skinEnums.length;
        }

        @Override
        public Object getItem(int position) {
            return skinEnums[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_skin, parent, false);
                viewHolder.skinPreviewImageView = (ImageView) convertView.findViewById(R.id.image_skinGridItem_preview);
                viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.text_skinGridItem_name);
                viewHolder.checkedFlagImageView = (ImageView) convertView.findViewById(R.id.image_skinGridItem_checkedFlag);
                if(position == 0) {
                    viewHolder.nameTextView.setTextColor(Color.parseColor("#565A5C"));
                } else {
                    viewHolder.nameTextView.setTextColor(Color.WHITE);
                }

//				ViewGroup.LayoutParams previewViewParams = viewHolder.skinPreviewImageView.getLayoutParams();
//				previewViewParams.width = itemSize;
//				previewViewParams.height = itemSize;
//				viewHolder.skinPreviewImageView.setLayoutParams(previewViewParams);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            skinEnum = skinEnums[position];
            viewHolder.skinPreviewImageView.setImageDrawable(skinEnum.getPreviewDrawable(context));
            //viewHolder.nameTextView.setTypeface(typeface);
            viewHolder.nameTextView.setText(skinEnum.getName());
            viewHolder.checkedFlagImageView.setVisibility(skinEnum.getName().equals(SkinHolder.getSkin(context).getName())?View.VISIBLE:View.INVISIBLE);

            return convertView;
        }
        public SkinEnum[] getSkinEnums() {
            return skinEnums;
        }

        private static class ViewHolder{
            private TextView nameTextView;
            private ImageView skinPreviewImageView;
            private ImageView checkedFlagImageView;
        }
    }


}
