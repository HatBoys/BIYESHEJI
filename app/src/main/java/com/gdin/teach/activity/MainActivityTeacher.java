package com.gdin.teach.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.fragment.MainTeacherFragment;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: Teacher主页
 */
public class MainActivityTeacher extends BaseActivity {

    public FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_base, new MainTeacherFragment(), Constan.CLASSINFODETAILFRAGMENT).commit();
    }

    /**
     * 跳转到MainActivityTeacher
     *
     * @param context
     */
    public static void start2MainActivityTeacher(Context context) {
        Intent mIntent = new Intent(context, MainActivityTeacher.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(mIntent);
//        if (context instanceof LoginActivity) {
//            LoginActivity mLoginActivity = (LoginActivity) context;
//            mLoginActivity.finish();
//        }
        MyApplication.mSharedPreferences.edit().putBoolean(Constan.FINISHLOGIN, true).commit();
    }

    /**
     * 为下属的三个Fragment提供一个设置toolbar是否显示的方法
     *
     * @param visiable
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void setToolBar(boolean visiable) {
        if (mTlBase == null) {
            mTlBase = reSetToolBar();
        }
        if (visiable) {
            mTlBase.setVisibility(View.VISIBLE);
            mTlBase.setTitle("");
        } else {
            mTlBase.setVisibility(View.GONE);
        }
    }


    /**
     * 为下属的三个Fragment提供一个设置toolbar的title名称的方法
     *
     * @param title
     */
    public void setFragmentTitle(String title) {
        setMyTitle(title);
    }
}
