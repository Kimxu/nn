package kimxu.nn.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.datatype.BmobDate;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 工具类
 */
public class GlobalUtil {



    public static BmobDate dateToBmobDate(String createAt){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date  = null;
        try {
            date = sdf.parse(createAt);
        } catch (ParseException e) {
        }
        return new BmobDate(date);
    }



    private static SweetAlertDialog pDialog;
    /**
     * * 会自动判断当前是不是在主线程，不在的话就会通过Handler在主线程执行
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void showToast(final Context context, final String message, final int duration) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, message, duration).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, duration).show();
                }
            });
        }
    }

    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }


    public static void showLoadingDialog(final Context context, final String title) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            loadingDialog(context, title);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    loadingDialog(context, title);
                }
            });
        }




    }
    public static void showSubimtDialog(final Context context, final String content, final SweetAlertDialog.OnSweetClickListener sListener, final SweetAlertDialog.OnSweetClickListener cListener) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            submitDialog(context, content, sListener, cListener);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    submitDialog(context, content, sListener, cListener);
                }
            });
        }




    }
    private static void submitDialog(Context context,String content,SweetAlertDialog.OnSweetClickListener sListener,SweetAlertDialog.OnSweetClickListener cListener){
        submitDialog(context, "提示", content, "确认", "取消", sListener, cListener);

    }

    private static void submitDialog(Context context, String title,String content,String submit,String cancel,SweetAlertDialog.OnSweetClickListener sListener,SweetAlertDialog.OnSweetClickListener cListener) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(submit)
                .setConfirmClickListener(sListener)
                .setCancelText(cancel)
                .setCancelClickListener(cListener)
                .show();
    }

    private static void loadingDialog(Context context, String title) {
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(title);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    public static void closeDialog() {
        pDialog.cancel();
    }






}
