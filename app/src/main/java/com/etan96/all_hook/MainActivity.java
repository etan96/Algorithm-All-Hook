package com.etan96.all_hook;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("app_info", MODE_PRIVATE);
        sharedPreferences.edit().putString("pack_name", "[\"com.xiaojianbang.app\" , \"com.souche.apps.destiny\"]").apply();

    }

}
