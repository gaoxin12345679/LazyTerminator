package com.stars.app.lazyterminator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.stars.app.lazyterminator.util.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    static private String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName());

        ActivityCollector.addActivityToList(this);

        /*设置状态栏-沉浸式*/
        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        */
        //SystemBarTintManager tintManager = new SystemBarTintManager(this);
        //tintManager.setStatusBarTintEnabled(true);
        //tintManager.setStatusBarTintResource(R.color.statusbar_bg);//通知栏所需颜色
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivityFromList(this);
    }
}
