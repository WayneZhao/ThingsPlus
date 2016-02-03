package com.dremer.framework.mylibrary.utils;

import android.util.Log;

public class LogUtil {
	private static boolean isDebug = true;
	private static final String TAG = "bmdown";

    public static void setIsDebug(boolean debug){
        isDebug = debug;
    }

	public static void e(Object clazz, String msg) {
		if (isDebug) {
			Log.e(clazz.getClass().getSimpleName(), msg);
		}
	}

	public static void i(Object clazz, String msg) {
		if (isDebug) {
			Log.i(clazz.getClass().getSimpleName(), msg);
		}
	}

	public static void v(Object clazz, String msg) {
		if (isDebug) {
			Log.v(clazz.getClass().getSimpleName(), msg);
		}
	}

	public static void d(Object clazz, String msg) {
		if (isDebug) {
			Log.d(clazz.getClass().getSimpleName(), msg);
		}
	}

	public static void e(String msg) {
		if (isDebug) {
			Log.e(TAG, msg);
		}
	}

	public static void i(String msg) {
		if (isDebug) {
			Log.i(TAG, msg);
		}
	}

	public static void v(String msg) {
		if (isDebug) {
			Log.v(TAG, msg);
		}
	}

	public static void d(String msg) {
		if (isDebug) {
			Log.d(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}
}
