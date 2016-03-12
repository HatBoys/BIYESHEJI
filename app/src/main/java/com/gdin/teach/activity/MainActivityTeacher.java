package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gdin.teach.R;
import com.gdin.teach.adapter.MainTeacherAdapter;
import com.gdin.teach.fragment.BaseFragment;
import com.gdin.teach.fragment.BeforeClassTeacherFragment;
import com.gdin.teach.fragment.InClassTeacherFragment;
import com.gdin.teach.fragment.PersonalTeacherFragment;
import com.gdin.teach.util.CommomUtil;
import com.gdin.teach.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: Teacher主页
 */
public class MainActivityTeacher extends BaseActivity implements OnClickListener {

    @Bind(R.id.vp_main_teacher)
    NoScrollViewPager mVpMainTeacher;
    @Bind(R.id.rb_teacher_before_class)
    RadioButton mRbTeacherBeforeClass;
    @Bind(R.id.rb_teacher_in_class)
    RadioButton mRbTeacherInClass;
    @Bind(R.id.rb_teacher_personal)
    RadioButton mRbTeacherPersonal;
    @Bind(R.id.rg_main_teacher_bottom)
    RadioGroup mRgMainTeacherBottom;

    private ArrayList<BaseFragment> mFragmentArrayList;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        initData();
        initListener();
        mVpMainTeacher.setAdapter(new MainTeacherAdapter(this, mFragmentArrayList, mFragmentManager));
        mRbTeacherBeforeClass.setChecked(true);
        mVpMainTeacher.setCurrentItem(0);
        mVpMainTeacher.setOffscreenPageLimit(3);
    }

    private void initListener() {
        mRbTeacherBeforeClass.setOnClickListener(this);
        mRbTeacherInClass.setOnClickListener(this);
        mRbTeacherPersonal.setOnClickListener(this);
    }

    private void initData() {
        mFragmentArrayList = new ArrayList<BaseFragment>();
        mFragmentArrayList.add(new BeforeClassTeacherFragment());
        mFragmentArrayList.add(new InClassTeacherFragment());
        mFragmentArrayList.add(new PersonalTeacherFragment());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_teacher_before_class:
                CommomUtil.toastMessage(this, "rb_teacher_before_class");
                mRgMainTeacherBottom.check(R.id.rb_teacher_before_class);
                mVpMainTeacher.setCurrentItem(0);
                break;
            case R.id.rb_teacher_in_class:
                mRgMainTeacherBottom.check(R.id.rb_teacher_in_class);
                mVpMainTeacher.setCurrentItem(1);
                break;
            case R.id.rb_teacher_personal:
                mRgMainTeacherBottom.check(R.id.rb_teacher_personal);
                mVpMainTeacher.setCurrentItem(2);
                break;

        }
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
