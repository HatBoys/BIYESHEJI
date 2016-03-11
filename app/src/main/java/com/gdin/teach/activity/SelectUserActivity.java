package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.SelectUserFragment;


public class SelectUserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.select_user_fragment, new SelectUserFragment(), Constan.SELECTUSERFRAGMENT);
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
