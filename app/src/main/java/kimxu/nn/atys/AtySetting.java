package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.utils.logger.Klog;
import kimxu.nn.widget.SettingLayout;

public class AtySetting extends AtySupport {

    @Bind(R.id.set_skin)
    SettingLayout setSkin;
    @Bind(R.id.set_2)
    SettingLayout set2;
    @Bind(R.id.set_3)
    SettingLayout set3;
    @Bind(R.id.set_4)
    SettingLayout set4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_setting);
        ButterKnife.bind(this);
        setSkin.setSettingLayoutListener(new SettingLayout.SwitchCallBack() {
            @Override
            public void callback(CompoundButton buttonView, boolean isChecked) {
               AtySettingSkin.startMe(mActivity);
            }
        });
        set2.setSettingLayoutListener(new SettingLayout.SwitchCallBack() {
            @Override
            public void callback(CompoundButton buttonView, boolean isChecked) {
                Klog.d("Ss");
            }
        });
        set3.setSettingLayoutListener(new SettingLayout.SwitchCallBack() {
            @Override
            public void callback(CompoundButton buttonView, boolean isChecked) {
                Klog.d("Ss" );
            }
        });
        set4.setSettingLayoutListener(new SettingLayout.SwitchCallBack() {
            @Override
            public void callback(CompoundButton buttonView, boolean isChecked) {
                Klog.d("Ss");
            }
        });
    }

    @OnClick({R.id.set_skin, R.id.set_2, R.id.set_3, R.id.set_4})
    public void set1(SettingLayout v) {
        v.slideToChecked();
    }

    @Override
    public String getActivityName() {
        return "AtySetting";
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtySetting.class);
        context.startActivity(intent);

    }

}
