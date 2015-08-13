package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kimxu.nn.R;
import kimxu.nn.adapter.ShowIconAdapter;
import kimxu.nn.basic.AtySupport;
import kimxu.nn.model.Bill;
import kimxu.nn.utils.GlobalUtil;

/**
 * 账单提交
 */
public class AtyAdd extends AtySupport {


    @Bind(R.id.tv_show_count)
    TextView tvShowCount;
    @Bind(R.id.b1)
    Button b1;
    @Bind(R.id.b2)
    Button b2;
    @Bind(R.id.b3)
    Button b3;
    @Bind(R.id.bequals)
    Button bequals;
    @Bind(R.id.b4)
    Button b4;
    @Bind(R.id.b5)
    Button b5;
    @Bind(R.id.b6)
    Button b6;
    @Bind(R.id.badd)
    Button badd;
    @Bind(R.id.b7)
    Button b7;
    @Bind(R.id.b8)
    Button b8;
    @Bind(R.id.b9)
    Button b9;
    @Bind(R.id.bac)
    Button bac;
    @Bind(R.id.b0)
    Button b0;
    @Bind(R.id.bpoint)
    Button bpoint;
    @Bind(R.id.bok)
    Button bok;
    @Bind(R.id.grid_show_icon)
    GridView gridShowIcon;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        gridShowIcon.setAdapter(new ShowIconAdapter(mActivity));
        gridShowIcon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drawable drawable = getResources().getDrawable((int) gridShowIcon.getAdapter().getItemId(position));
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvShowCount.setCompoundDrawables(drawable, null, null, null);
            }
        });
    }


    @Override
    public String getActivityName() {
        return "AtyAdd";
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyAdd.class);
        context.startActivity(intent);

    }


    private double number = 0.0;
    String operator = "=";
    // Is the first digit pressed?
    boolean isFirstDigit = true;

    @OnClick({R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9, R.id.b0, R.id.bequals, R.id.badd, R.id.bpoint, R.id.bac, R.id.bok})
    public void clicks(View v) {
        switch (v.getId()) {
            case R.id.b0:

                handleNumber("0");
                break;
            case R.id.b1:

                handleNumber("1");
                break;
            case R.id.b2:
                handleNumber("2");
                break;
            case R.id.b3:
                handleNumber("3");
                break;
            case R.id.b4:
                handleNumber("4");
                break;
            case R.id.b5:
                handleNumber("5");
                break;
            case R.id.b6:
                handleNumber("6");
                break;
            case R.id.b7:
                handleNumber("7");
                break;
            case R.id.b8:
                handleNumber("8");
                break;
            case R.id.b9:
                handleNumber("9");
                break;
            case R.id.bok:
                submit();
                break;
            case R.id.badd:
                handleOperator("+");
                break;
            case R.id.bequals:
                handleOperator("=");
                break;
            case R.id.bac:
                handleReset();
                break;
            case R.id.bpoint:
                handleNumber(".");
                break;
        }
    }

    private void submit() {
        if (sumOfMoney.equals("0") || TextUtils.isEmpty(sumOfMoney)) {
            GlobalUtil.showToast(mActivity, "请输入金额");
            return;
        }

        GlobalUtil.showSubimtDialog(mActivity, "确认提交吗?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        GlobalUtil.showLoadingDialog(mActivity, "提交中...");
                        Bill bill = new Bill("0", sumOfMoney, "kimxu");
                        bill.save(mActivity, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                //AtyAccountBook.startMe(mActivity);

                                GlobalUtil.closeDialog();
                                finish();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                GlobalUtil.showToast(mActivity, "提交失败..." + s);
                                GlobalUtil.closeDialog();
                            }
                        });

                    }
                }, new SweetAlertDialog.OnSweetClickListener() {

                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                }
        );

    }


    public void handleNumber(String key) {
        if (isFirstDigit)
            tvShowCount.setText(key);
        else if ((key.equals(".")) && (tvShowCount.getText().toString().indexOf(".") < 0))
            tvShowCount.setText(tvShowCount.getText().toString() + ".");
        else if (!key.equals("."))
            tvShowCount.setText(tvShowCount.getText().toString() + key);
        isFirstDigit = false;
    }

    /**
     * Reset the calculator.
     */
    public void handleReset() {
        tvShowCount.setText("0");
        isFirstDigit = true;
        operator = "=";
    }

    public void handleOperator(String key) {
        if (operator.equals("+"))
            number += Double.valueOf(tvShowCount.getText().toString());
        else if (operator.equals("-"))
            number -= Double.valueOf(tvShowCount.getText().toString());
        else if (operator.equals("*"))
            number *= Double.valueOf(tvShowCount.getText().toString());
        else if (operator.equals("/"))
            number /= Double.valueOf(tvShowCount.getText().toString());
        else if (operator.equals("="))
            number = Double.valueOf(tvShowCount.getText().toString());
        sumOfMoney = doubleToStr(number);
        tvShowCount.setText(sumOfMoney);
        operator = key;
        isFirstDigit = true;
    }

    private String sumOfMoney = "";

    private String doubleToStr(double f) {
        DecimalFormat df = new DecimalFormat("#0.0");
        String str = df.format(f);
        if (str.split("\\.")[1].equals("0")) {
            str = str.split("\\.")[0];
        }
        return str;
    }

}
