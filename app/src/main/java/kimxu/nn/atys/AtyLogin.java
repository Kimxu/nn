package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import kimxu.nn.AppConstant;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.model.Account;
import kimxu.nn.utils.GlobalUtil;
import kimxu.nn.utils.PreferenceUtil;

/**
 * 等了界面
 */
public class AtyLogin extends AtySupport {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    private String pwd;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.aty_login);

        isFirst();
        ButterKnife.bind(this);


    }

    @Override
    public String getActivityName() {
        return "AtyLogin";
    }

    private void isFirst() {

        if (!PreferenceUtil.getBoolean(mActivity, AppConstant.IS_FIRST_LOGIN, true)) {
         AtyAccountBook.startMe(mActivity);
          finish();
        }
    }

    private void getupData() {

        user = username.getText().toString();
        pwd = password.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
            GlobalUtil.showToast(mActivity, "密码账号不能为空啊");
            return;
        }
        GlobalUtil.showLoadingDialog(mActivity, "登录中...");
        BmobQuery<Account> query = new BmobQuery<Account>();
        query.addWhereEqualTo(Account.USERNAME, user);
        query.addWhereEqualTo(Account.PASSWORD, pwd);

        query.findObjects(mActivity, new FindListener<Account>() {
            @Override
            public void onSuccess(List<Account> list) {

                if (list.size() == 1) {
                    GlobalUtil.showToast(mActivity, "登陆成功");

                    AtyMain.startMe(mActivity);
                    PreferenceUtil.putBoolean(mActivity, AppConstant.IS_FIRST_LOGIN, false);
                    finish();
                }
                GlobalUtil.closeDialog();
                GlobalUtil.showToast(mActivity, "密码或账号错误");

            }

            @Override
            public void onError(int i, String s) {

                GlobalUtil.showToast(mActivity, "网络错误");
                GlobalUtil.closeDialog();
            }
        });

    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyLogin.class);
        context.startActivity(intent);


    }

    @OnClick({R.id.login, R.id.clause, R.id.to_regist, R.id.login_to_forgot})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                getupData();
                break;
            case R.id.clause:
                break;
            case R.id.to_regist:
                AtyRegist.startMe(mActivity);
                break;
            case R.id.login_to_forgot:
                break;
        }
    }


}
