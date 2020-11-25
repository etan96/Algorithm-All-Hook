package com.etan96.all_hook;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        if (context == null){
            context = getApplicationContext();
        }
    }

    public static Context getContext(){
        return context;
    }
}
