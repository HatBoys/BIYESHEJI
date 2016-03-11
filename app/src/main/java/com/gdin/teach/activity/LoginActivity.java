package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gdin.teach.R;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.select_user_fragment, new SelectUserFragment(), Constan.SELECTUSERFRAGMENT);
        mFragmentTransaction.commit();*/
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
    public static void start2LoginActivity(Context context) {
        Intent mIntent = new Intent(context, LoginActivity.class);
        context.startActivity(mIntent);
//        SelectUserActivity mSelectUserActivity = (SelectUserActivity) context;
//        mSelectUserActivity.finish();
    }
}
