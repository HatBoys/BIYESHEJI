package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.fragment.SelectUserFragment;


public class SelectUserActivity extends BaseActivity {

    private boolean mFinishLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFinishLogin = MyApplication.mSharedPreferences.getBoolean(Constan.FINISHLOGIN, false);
        if (mFinishLogin) {
            MainActivityTeacher.start2MainActivityTeacher(this);
            finish();
        }
        mTlBase.setVisibility(View.GONE);
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fl_base, new SelectUserFragment(), Constan.SELECTUSERFRAGMENT);
        mFragmentTransaction.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 跳转到SelectUserActivity
     *
     * @param context
     */
    public static void start2SelectUserActivity(Context context) {
        Intent mIntent = new Intent(context, SelectUserActivity.class);
        context.startActivity(mIntent);
        SplashActivity mSplashActivity = (SplashActivity) context;
        mSplashActivity.finish();
    }
}
