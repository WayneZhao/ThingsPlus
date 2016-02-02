package com.dremer.framework.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zx on 2016/1/14.
 */
public class ToastUtil {
    private ToastUtil() {
    }

    public static void showToast(Context context, String toast) {
        if (null == mToast) {
            mToast = Toast.makeText(context, toast, Toast.LENGTH_LONG);
        } else {
            mToast.setText(toast);
        }

        mToast.show();
    }

    public static void cancel() {
        if (null != mToast) {
            mToast.cancel();
        }
    }

    public static Toast mToast = null;
}
