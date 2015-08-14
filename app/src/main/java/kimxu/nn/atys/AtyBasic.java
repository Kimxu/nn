package kimxu.nn.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kimxu.nn.R;
import kimxu.nn.basic.AtySupport;

public class AtyBasic extends AtySupport {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_baisc);

    }

    @Override
    public String getActivityName() {
        return "AtyBasic";
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context, AtyBasic.class);
        context.startActivity(intent);

    }

}
