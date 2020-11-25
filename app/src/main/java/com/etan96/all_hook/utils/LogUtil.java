package com.etan96.all_hook.utils;

import android.util.Log;

public class LogUtil {
    private static String TAG = "AutoOutput";
    public static void d(String content){
        Log.d(TAG, content);
    }
    public static void d(String msg, Throwable tr) {
        Log.d(TAG, msg, tr);
    }

    public static void e(String content){
        Log.e(TAG, content);
    }
    public static void e(String msg, Throwable tr) {
        Log.e(TAG, msg, tr);
    }
}
