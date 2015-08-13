package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.model.Account;
import kimxu.nn.utils.GlobalUtil;

/**
 * 注册
 * */
public class AtyRegist extends AtySupport {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    private String pwd;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_regist);
        ButterKnife.bind(this);

    }

    @Override
    public String getActivityName() {
        return "AtyRegist";
    }

    private void getupData() {

        user= username.getText().toString();
        pwd=password.getText().toString();
        if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pwd)){
            GlobalUtil.showToast(mActivity,"密码账号不能为空啊");
            return;
        }
        submit(user, pwd);

    }

    private void submit(String user, String pwd) {
        Account account=new Account(user,pwd,"");
        GlobalUtil.showLoadingDialog(mActivity, "注册中...");
        account.save(mActivity, new SaveListener() {

            @Override
            public void onSuccess() {
                GlobalUtil.showToast(mActivity,"注册成功");
                    GlobalUtil.closeDialog();
            }

            @Override
            public void onFailure(int i, String s) {
                GlobalUtil.showToast(mActivity,"注册失败"+s);
                GlobalUtil.closeDialog();
            }
        });
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyRegist.class);
        context.startActivity(intent);


    }

    @OnClick({R.id.regist,R.id.clause,R.id.to_regist,R.id.login_to_forgot})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regist:
                getupData();
                break;
            case R.id.clause:break;
            case R.id.to_regist:break;
            case R.id.login_to_forgot:break;
        }
    }


}
