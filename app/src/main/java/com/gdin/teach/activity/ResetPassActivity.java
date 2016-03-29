package com.gdin.teach.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.ResetPassFragment;

/**
 * Created by 黄培彦 on 16/3/29.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ResetPassActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_base, new ResetPassFragment(), Constan.RESETPASSFRAGMENT).commit();
        reSetToolBar();
        mTvTitle.setText(Constan.SETTINGPASS);
        mTlBase.setNavigationIcon(R.mipmap.back);
        mTlBase.setTitle("");
        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void start2ResetPassActivity(Activity activity) {
        Intent mIntent = new Intent(activity, ResetPassActivity.class);
        activity.startActivity(mIntent);
    }
}
