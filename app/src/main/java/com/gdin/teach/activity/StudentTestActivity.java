package com.gdin.teach.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.bean.StudentClassInfoBean;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentTestActivity extends BaseActivity {

    private static StudentClassInfoBean mClassInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_base, new StudentTestFragment(mClassInfoBean)
                , Constan.STUDENTTESTFRAGMENT).commit();

        mTlBase.setNavigationIcon(R.mipmap.back);
        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reSetToolBar();
        mTlBase.setTitle("");
        setMyTitle("测试");
    }

    public static void start2StudentTestActivity(Activity activity, Object item) {
        Intent mIntent = new Intent(activity, StudentTestActivity.class);
        mClassInfoBean = (StudentClassInfoBean) item;
        activity.startActivity(mIntent);
    }

}
