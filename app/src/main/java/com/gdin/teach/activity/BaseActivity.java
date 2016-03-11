package com.gdin.teach.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
