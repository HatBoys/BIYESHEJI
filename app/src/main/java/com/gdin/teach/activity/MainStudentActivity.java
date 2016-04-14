package com.gdin.teach.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.gdin.slidingmenulib.lib.SlidingMenu;
import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.fragment.MainStudentFragment;
import com.gdin.teach.fragment.SampleListFragment;
import com.gdin.teach.util.CommomUtil;

/**
 * Created by 黄培彦 on 16/4/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: Teacher主页
 */
public class MainStudentActivity extends BaseActivity implements PopupWindow.OnDismissListener, View.OnClickListener {

    public FragmentManager mFragmentManager;
    private SlidingMenu mMenu;
    private MenuItem mItem;

    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    private Button mNotes;
    private Button mPhoto;
    private Button mRecord;
    private Button mVideo;
    private PopupWindow mPopupWindow;

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
        mWindow = this.getWindow();
        mParams = mWindow.getAttributes();

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
//        TakeNotesActivity.start2TakeNotesActivity(this);
        initSelectDialog();
        return true;
    }

    private void initSelectDialog() {
//        mBuilder = new AlertDialog.Builder(getApplicationContext());
//        mBuilder.setView(mView);
//        mBuilder.create();
//        mBuilder.show();
        /**
         * 报错:  java.lang.IllegalStateException: You need to use a Theme.AppCompat theme
         * (or descendant) with this activity.
         */

        View mView = getLayoutInflater().inflate(R.layout.select_notes_types, null);
        mNotes = (Button) mView.findViewById(R.id.bt_notes);
        mPhoto = (Button) mView.findViewById(R.id.bt_photo);
        mRecord = (Button) mView.findViewById(R.id.bt_record);
        mVideo = (Button) mView.findViewById(R.id.bt_video);
        initListener();
        mPopupWindow = CommomUtil.showPopupWindow(mView);
        mParams.alpha = 0.6f;//设置背景颜色
        mWindow.setAttributes(mParams);
        if (mLlContent != null) {
            mPopupWindow.showAtLocation(mLlContent, Gravity.LEFT, 0, 0);
        }
        /* else {
            mPopupWindow.showAsDropDown(mTlBase, 0, 0);
        }*/

        mPopupWindow.setOnDismissListener(this);

    }

    private void initListener() {
        mNotes.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mRecord.setOnClickListener(this);
        mVideo.setOnClickListener(this);
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

    @Override
    public void onDismiss() {
        mParams.alpha = 1f;
        mWindow.setAttributes(mParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notes:
                // TODO: 16/4/14 skip to other app
                break;
            case R.id.bt_photo:
                break;
            case R.id.bt_record:
                break;
            case R.id.bt_video:
                break;
        }

    }

    public View getContentView(){
        return mLlContent;
    }
}
