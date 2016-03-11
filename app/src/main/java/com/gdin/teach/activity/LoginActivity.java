package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.LoginFragment;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent mIntent = getIntent();
        String mUser = mIntent.getStringExtra(Constan.USER);
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.login_fragment, new LoginFragment(mUser), Constan.LOGINFRAGMENT);
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
        Intent mIntent = new Intent(context, LoginActivity.class);
        mIntent.putExtra(Constan.USER, user);
        context.startActivity(mIntent);
//        SelectUserActivity mSelectUserActivity = (SelectUserActivity) context;
//        mSelectUserActivity.finish();
    }
}
