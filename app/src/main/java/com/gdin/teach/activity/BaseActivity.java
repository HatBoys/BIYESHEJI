package com.gdin.teach.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.gdin.teach.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.tl_base)
    Toolbar mTlBase;
    @Bind(R.id.fl_base)
    FrameLayout mFlBase;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
        ButterKnife.bind(this);
        if (mTlBase != null) {
            setSupportActionBar(mTlBase);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }
}
