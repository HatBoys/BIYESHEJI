package com.gdin.teach;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: Application的全局作用
 */
public class MyApplication extends Application {

    public static Context mApplicationContext;
    public static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        mSharedPreferences = mApplicationContext.getSharedPreferences(Constan.MYSHAREPREFERENCE, MODE_PRIVATE);
    }


}
