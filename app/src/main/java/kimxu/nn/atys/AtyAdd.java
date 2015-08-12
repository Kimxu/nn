package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;

/**
 * Created by xuzhiguo on 15/8/11.
 */
public class AtyAdd extends AtySupport {


    @Bind(R.id.show_count)
    TextView showCount;
    @Bind(R.id.b1)
    Button b1;
    @Bind(R.id.b2)
    Button b2;
    @Bind(R.id.b3)
    Button b3;
    @Bind(R.id.besc)
    Button esc;
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


    private String  showCountStr="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add);
        ButterKnife.bind(this);

    }

    @Override
    public String getActivityName() {
        return "AtyAdd";
    }


    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyAdd.class);
        context.startActivity(intent);

    }


    @OnClick({R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9,R.id.b0,R.id.besc,R.id.badd,R.id.bpoint,R.id.bac,R.id.bok})
    public void clicks(View v){
        switch (v.getId()){
            case R.id.b0:

                addCount(0);
                break;
            case R.id.b1:

                addCount(1);
                break;
            case R.id.b2:

                addCount(2);
                break;
            case R.id.b3:

                addCount(3);
                break;
            case R.id.b4:

                addCount(4);
                break;
            case R.id.b5:


                addCount(5);
                break;
            case R.id.b6:

                addCount(6);
                break;
            case R.id.b7:

                addCount(7);
                break;
            case R.id.b8:

                addCount(8);
                break;
            case R.id.b9:

                addCount(9);
                break;
            case R.id.bok:
                getSum();
                break;
            case R.id.badd:
                addCount("+");
                break;

            case R.id.besc:

                break;
            case R.id.bac:
                clearNum();
                break;
            case R.id.bpoint:
                addCount(".");
                break;
        }
    }

    private void clearNum() {


    }

    private void getSum() {
    }

    private void addCount(String num){
        showCountStr+=num;
        showCount.setText(showCountStr);
    }
    private void addCount(int num){
        showCountStr+=num;
        showCount.setText(showCountStr);
    }

}
