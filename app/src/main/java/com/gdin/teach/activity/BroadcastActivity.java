package com.gdin.teach.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.BroadcastFragment;
import com.gdin.teach.util.CommomUtil;

/**
 * Created by 黄培彦 on 16/3/25.
 * Email: peiyanhuang@yeah.net
 * 类说明: 发布公告
 */
public class BroadcastActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_base, new BroadcastFragment(), Constan.BROADCASTFRAGMENT).commit();
        mTlBase.setNavigationIcon(R.mipmap.back);
        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommomUtil.toastMessage(getApplicationContext(), "back");
                finish();
            }
        });
        reSetToolBar();
        mTlBase.setTitle("");
        reSetToolBar();
        mTvTitle.setText(Constan.BROADCAST);
        mTlBase.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 跳转到BroadcastActivity
     *
     * @param activity
     */
    public static void start2BraodcastActivity(Activity activity) {
        Intent mIntent = new Intent(activity, BroadcastActivity.class);
        activity.startActivity(mIntent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        CommomUtil.toastMessage(getApplicationContext(), "share");
        return false;
    }
}
