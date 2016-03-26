package com.gdin.teach.activity;

import android.os.Bundle;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.BroadcastDetailFragment;
import com.gdin.teach.fragment.BroadcastFragment;

/**
 * Created by 黄培彦 on 16/3/26.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class BroadcastDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_base, new BroadcastDetailFragment(), Constan.BROADCASTDETAILFRAGMENT).commit();

    }
}
