package com.gdin.teach.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gdin.slidingmenulib.lib.SlidingMenu;
import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.fragment.MainStudentFragment;
import com.gdin.teach.fragment.MainTeacherFragment;
import com.gdin.teach.fragment.SampleListFragment;
import com.gdin.teach.util.CommomUtil;

/**
 * Created by 黄培彦 on 16/4/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: Teacher主页
 */
public class MainStudentActivity extends BaseActivity {

    public FragmentManager mFragmentManager;
    private SlidingMenu mMenu;
    private MenuItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_base, new MainStudentFragment(), Constan.MAINSTUDENTFRAGMENT).commit();
        mTlBase.setTitle("");
        mTlBase.setNavigationIcon(R.mipmap.setting);
        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 16/4/13 SlidingMenu
                mMenu.showMenu();
            }
        });

        initSlidingMenu();
    }

    private void initSlidingMenu() {
        mMenu = new SlidingMenu(this);
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
        mMenu.setShadowDrawable(R.drawable.shadow);
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mMenu.setFadeDegree(0.35f);
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置滑动菜单的视图界面
        mMenu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();//给menu增添界面
    }

    public void setSligdingMenuIgnorView(View view) {
        if (mMenu != null) {
            mMenu.addIgnoredView(view);
        }
    }

    public void clearSlidingMenuIgnoreView() {
        mMenu.clearIgnoredViews();
    }

    /**
     * 跳转到MainStudentActivity
     *
     * @param context
     */
    public static void start2MainStudentActivity(Context context) {
        Intent mIntent = new Intent(context, MainStudentActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(mIntent);
        MyApplication.mSharedPreferences.edit().putBoolean(Constan.FINISHLOGIN, true).commit();
    }

    @Override
    public void onBackPressed() {
        //点击返回键关闭滑动菜单
        if (mMenu.isMenuShowing()) {
            mMenu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 为下属的三个Fragment提供一个设置toolbar是否显示的方法
     *
     * @param visiable
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void setToolBar(boolean visiable) {
        if (mTlBase == null) {
            mTlBase = reSetToolBar();
        }
        if (visiable) {
            mTlBase.setVisibility(View.VISIBLE);
            mTlBase.setTitle("");
        } else {
            mTlBase.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.take_notes_menu, menu);
        mItem = menu.findItem(R.id.take_notes);
        setMenuVisable(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        CommomUtil.toastMessage(this,"add");
        // TODO: 16/4/14 skip to take notes activity;
        return true;
    }

    public void setMenuVisable(boolean visiable) {
        if (mItem == null)
            return;
        mItem.setVisible(visiable);
    }


    /**
     * 为下属的三个Fragment提供一个设置toolbar的title名称的方法
     *
     * @param title
     */
    public void setFragmentTitle(String title) {
        setMyTitle(title);
    }
}
