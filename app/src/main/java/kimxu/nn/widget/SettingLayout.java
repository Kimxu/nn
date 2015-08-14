package kimxu.nn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kimxu.nn.R;
import kimxu.widget.SwitchButton;

/**
 * 设置Layout
 */
public class SettingLayout extends RelativeLayout {

    TextView title;
    SwitchButton switchButton;

    public SettingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);
    }
    public SettingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);

    }

    public SettingLayout(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.w_setting_layout, this,true);
        title= (TextView) v.findViewById(R.id.title);
        switchButton= (SwitchButton) v.findViewById(R.id.switch_button);

    }

    public void setTitleText(String titleStr){
        title.setText(titleStr);
    }


    public boolean setSwitchButtonListener(final SwitchCallBack sBCallBack){
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sBCallBack != null) {
                    sBCallBack.callback(buttonView, isChecked);
                }
            }
        });
        return switchButton.isChecked();
    }

    public boolean slideToChecked(){
        switchButton.slideToChecked(!switchButton.isChecked());
        return switchButton.isChecked();
    }


    public SwitchButton getSwitchButton() {
        return switchButton;
    }

    public interface SwitchCallBack{
       void callback(CompoundButton buttonView, boolean isChecked);
    }
}
