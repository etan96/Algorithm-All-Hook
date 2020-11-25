package com.etan96.all_hook.utils;


import android.util.Base64;

public class Base64Util {

    public static String bytes2String(byte[] bytes){
        return Base64.encodeToString(bytes, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
    }
}
