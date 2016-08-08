package com.dremer.framework.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zx on 2016/1/14.
 */
public class ToastUtil {
    private static boolean isToast = false;
    private static Toast mToast = null;

    private ToastUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void setToastDisplayable(boolean displayable) {
        isToast = displayable;
    }

    public static void showToast(Context context, String toast) {
        if (isToast) {
            if (null == mToast) {
                mToast = Toast.makeText(context, toast, Toast.LENGTH_LONG);
            } else {
                mToast.setText(toast);
            }

            mToast.show();
        }
    }

    public static void cancel() {
        if (isToast && null != mToast) {
            mToast.cancel();
        }
    }
}
