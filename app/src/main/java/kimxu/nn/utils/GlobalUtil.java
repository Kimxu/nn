package kimxu.nn.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 工具类
 */
public class GlobalUtil {

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
            LoadingDialog(context, title);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    LoadingDialog(context, title);
                }
            });
        }


    }

    private static void LoadingDialog(Context context, String title) {
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
