package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.gdin.teach.R;
import com.gdin.teach.adapter.MainTeacherAdapter;
import com.gdin.teach.fragment.BaseFragment;
import com.gdin.teach.fragment.BeforeClassTeacherFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: Teacher主页
 */
public class MainActivityTeacher extends BaseActivity {

    @Bind(R.id.vp_main_teacher)
    ViewPager mVpMainTeacher;

    private ArrayList<BaseFragment> mFragmentArrayList;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        initData();
        mVpMainTeacher.setAdapter(new MainTeacherAdapter(this, mFragmentArrayList, mFragmentManager));
    }

    private void initData() {
        mFragmentArrayList = new ArrayList<BaseFragment>();
        mFragmentArrayList.add(new BeforeClassTeacherFragment());
        mFragmentArrayList.add(new BeforeClassTeacherFragment());
        mFragmentArrayList.add(new BeforeClassTeacherFragment());

    }

    /**
     * 跳转到SelectUserActivity
     *
     * @param context
     */
    public static void start2MainActivityTeacher(Context context) {
        Intent mIntent = new Intent(context, MainActivityTeacher.class);
        context.startActivity(mIntent);
        LoginActivity mLoginActivity = (LoginActivity) context;
        mLoginActivity.finish();
    }

}
