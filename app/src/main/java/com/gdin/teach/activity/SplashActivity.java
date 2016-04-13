package com.gdin.teach.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.fragment.SplashFragmentFirst;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;


/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: 介绍欢迎界面（只开启一次，除非用户擦除数据）
 * 用到第三方依赖：AppIntro (https://github.com/PaoloRotolo/AppIntro)
 */
public class SplashActivity extends AppIntro {


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void init(Bundle savedInstanceState) {

        if (MyApplication.mSharedPreferences.getBoolean(Constan.FINISHSPLASH, false)) {
            SelectUserActivity.start2SelectUserActivity(this);
            finish();
        }
        setVibrate(true);
        setVibrateIntensity(30);
        addSlide(AppIntroFragment.newInstance(getString(R.string.mention), getString(R.string.mention_detail), R.mipmap.splash_a, getResources()
                .getColor(R.color.color_first)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.score), getString(R.string.score_detail), R.mipmap.splash_b, getResources()
                .getColor(R.color.color_second)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.broadcast), getString(R.string.broadcast_detail), R.mipmap.splash_c, getResources()
                .getColor(R.color.color_third)));
        setImageNextButton(getResources().getDrawable(R.mipmap.next));
        setSeparatorColor(getResources().getColor(R.color.alpha));
        setDoneText("开始探索");
        showSkipButton(false);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {
        onSlideChanged();
    }

    @Override
    public void onDonePressed() {
        MyApplication.mSharedPreferences.edit().putBoolean(Constan.FINISHSPLASH, true).commit();
        SelectUserActivity.start2SelectUserActivity(this);
        this.finish();
    }

    @Override
    public void onSlideChanged() {
    }
}
