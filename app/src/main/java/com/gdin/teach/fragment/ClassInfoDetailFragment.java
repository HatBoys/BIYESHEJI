package com.gdin.teach.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.activity.ClassInfoDetailActivity;
import com.gdin.teach.bean.ClassInfoDetailBean;
import com.gdin.teach.custom.GsonRequest;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课程信息详情Fragment
 */
public class ClassInfoDetailFragment extends BaseFragment {

    @Bind(R.id.ni_class_info_detail)
    NetworkImageView mNiClassInfoDetail;
    @Bind(R.id.tv_class_info)
    TextView mTvClassInfo;
    @Bind(R.id.tv_class_info_time)
    TextView mTvClassInfoTime;
    @Bind(R.id.tv_class_info_location)
    TextView mTvClassInfoLocation;
    private ClassInfoDetailActivity mClassInfoDetailActivity;
    private GsonRequest<ClassInfoDetailBean> mGsonRequest;
    private ImageLoader mLoader;
    private int mCurrentPoaition;
    private ArrayList<String> mImageUrlArrayList;
    private String mClassInfo;


    public ClassInfoDetailFragment() {
    }

    public ClassInfoDetailFragment(int position, String classInfo) {
        mCurrentPoaition = position;
        mClassInfo = classInfo;
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_class_info_detail, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassInfoDetailActivity = (ClassInfoDetailActivity) getActivity();
        mClassInfoDetailActivity.setTitle(Constan.CLASSDETAILTITLE);
        mImageUrlArrayList = new ArrayList<String>();
        mImageUrlArrayList.add(Constan.FIRSTINFO);
        mImageUrlArrayList.add(Constan.SECONDINFO);
        mImageUrlArrayList.add(Constan.THIRDINFO);
        mImageUrlArrayList.add(Constan.FOUTHINFO);
        mImageUrlArrayList.add(Constan.FIFTHINFO);
        mImageUrlArrayList.add(Constan.SIXTHINFO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoader = new ImageLoader(MyApplication.getInstance().getRequestQueue(), new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        mTvClassInfo.setText(mClassInfo);
        mNiClassInfoDetail.setDefaultImageResId(R.mipmap.loading_image);
        mNiClassInfoDetail.setErrorImageResId(R.mipmap.faild_load);
        int imagePositon = mCurrentPoaition % mImageUrlArrayList.size();
        mNiClassInfoDetail.setImageUrl(mImageUrlArrayList.get(imagePositon),
                mLoader);
        mTvClassInfoTime.setText("2016 年 03 月 18 日 16:" + mCurrentPoaition + ":09");
        mTvClassInfoLocation.setText("实验楼" + mCurrentPoaition + "课室");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
