package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.ClassInfoDetailFragment;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoDetailActivity extends BaseActivity {

    // TODO: 16/3/13 两个onCreate方法，有什么不同点
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
       /* setContentView(R.layout.activity_class_info_detail);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_class_info_detail_activity, new ClassInfoDetailFragment(), Constan.CLASSINFODETAILFRAGMENT)
                .commit();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_class_info_detail);
        mTlBase.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_base, new ClassInfoDetailFragment(), Constan.CLASSINFODETAILFRAGMENT)
                .commit();
    }

    /**
     * 跳转到ClassInfoDetailActivity
     *
     * @param context
     */
    public static void start2ClassInfoDetailActivity(Context context) {
        Intent mIntent = new Intent(context, ClassInfoDetailActivity.class);
        context.startActivity(mIntent);
    }
}
