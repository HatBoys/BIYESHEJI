package com.gdin.teach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gdin.teach.activity.BaseActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }

    /**
     * 跳转到MainActivity
     *
     * @param context
     */
    public static void start2MainActivity(Context context) {
        Intent mIntent = new Intent(context, MainActivity.class);
        context.startActivity(mIntent);
    }
}
