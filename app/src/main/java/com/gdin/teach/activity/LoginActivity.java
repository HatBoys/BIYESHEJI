package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.LoginFragment;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity {

    private static SelectUserActivity mSelectUserActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        String mUser = mIntent.getStringExtra(Constan.USER);
        mTlBase.setVisibility(View.GONE);
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fl_base, new LoginFragment(mSelectUserActivity,mUser), Constan.LOGINFRAGMENT);
        mFragmentTransaction.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 跳转到LoginActivity
     *
     * @param context
     */
    public static void start2LoginActivity(Context context, String user) {
        mSelectUserActivity = (SelectUserActivity) context;
        Intent mIntent = new Intent(context, LoginActivity.class);
        mIntent.putExtra(Constan.USER, user);
        context.startActivity(mIntent);
    }
}
