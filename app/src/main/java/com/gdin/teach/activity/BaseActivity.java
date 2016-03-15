package com.gdin.teach.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

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
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//如果是继承Activity注意是:requestWindowFeature(Window.FEATURE_NO_TITLE)）
        setContentView(R.layout.activity_base_layout);
        ButterKnife.bind(this);
        if (mTlBase != null) {
            setSupportActionBar(mTlBase);
        }

    }

    /**
     * 设置导航栏的中间标题
     *
     * @param title
     */
    public void setMyTitle(String title) {
        if (mTvTitle != null && mTlBase != null) {
            mTvTitle.setText(title);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }
}
