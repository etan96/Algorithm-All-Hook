package com.etan96.all_hook;

import android.annotation.SuppressLint;
import android.util.Log;


import com.etan96.all_hook.module.SHAwMACwMD5Module;

import org.json.JSONArray;

import de.robv.android.xqoseb.IXposedHookLoadPackage;
import de.robv.android.xqoseb.XSharedPreferences;
import de.robv.android.xqoseb.callbacks.XC_LoadPackage;


public class StartHook implements IXposedHookLoadPackage {
    @SuppressLint("NewApi")
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XSharedPreferences xSharedPreferences = new XSharedPreferences(BuildConfig.APPLICATION_ID, "app_info");
        xSharedPreferences.makeWorldReadable();
        String packNames = xSharedPreferences.getString("pack_name", null);
        Log.d("Xposed", "PackName list is："+ packNames);
        if (packNames != null){
            JSONArray packArray = new JSONArray(packNames);
            for (int i = 0; i < packArray.length(); i++){
                if (loadPackageParam.packageName.contains(packArray.getString(i))){
                    Log.d("Xposed", "Hooking： "+ loadPackageParam.packageName);
                    SHAwMACwMD5Module.shaWmacWmd5(loadPackageParam);
                }
            }
        }
    }
}
