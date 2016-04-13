package com.gdin.teach.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
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
import com.gdin.teach.bean.StudentClassInfoBean;
import com.gdin.teach.util.PickColors;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

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

    @Bind(R.id.tv_bespoke_need_person)
    TextView mTvBespokeNeedPerson;
    @Bind(R.id.tv_bespoke_person)
    TextView mTvBespokePerson;
    @Bind(R.id.pie_chart)
    PieChartView mPieChart;
    private ClassInfoDetailActivity mClassInfoDetailActivity;
    private ImageLoader mLoader;
    private int mCurrentPoaition;
    private String mClassInfo;
    private ArrayList<SliceValue> mSliceValueArrayList;
    private PieChartData mPieChardata;
    private StudentClassInfoBean mStudentClassInfoBean;
    private int[] mColorData;

    public ClassInfoDetailFragment() {
    }

    @SuppressLint("ValidFragment")
    public ClassInfoDetailFragment(int position, String classInfo, StudentClassInfoBean studentClassInfoBean) {
        mCurrentPoaition = position;
        mClassInfo = classInfo;
        mStudentClassInfoBean = studentClassInfoBean;
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
//        int imagePositon = mCurrentPoaition % mClassInfoDetailBean.size();
        mNiClassInfoDetail.setImageUrl(mStudentClassInfoBean.getImageUrl(),
                mLoader);
        mTvClassInfoTime.setText(mStudentClassInfoBean.getClassTime());
        mTvClassInfoLocation.setText(mStudentClassInfoBean.getClassPosition());
        mTvBespokeNeedPerson.setText(mStudentClassInfoBean.getYinDaoNum() + "");
        mTvBespokePerson.setText(mStudentClassInfoBean.getYuYueNum() + "");

        setPieChartData();
        initPieChart();
    }

    /**
     * 初始化饼状图
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initPieChart() {
        mPieChardata = new PieChartData();
        mPieChardata.setHasLabels(true);//显示表情
        mPieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        mPieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        mPieChardata.setHasCenterCircle(true);//是否是环形显示
        mPieChardata.setValues(mSliceValueArrayList);//填充数据
        mPieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        mPieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        mPieChardata.setCenterText1("预约情况");//环形中间的文字1
        mPieChardata.setCenterText1Color(getResources().getColor(R.color.colorPrimaryDark));//文字颜色
        mPieChardata.setCenterText1FontSize(10);//文字大小

//        mPieChardata.setCenterText2("饼图测试");
//        mPieChardata.setCenterText2Color(Color.BLACK);
//        mPieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//      data.setCenterText1Typeface(tf);
        mPieChart.setPieChartData(mPieChardata);
        mPieChart.setValueSelectionEnabled(true);//选择饼图某一块变大
        mPieChart.setAlpha(0.9f);//设置透明度
        mPieChart.setCircleFillRatio(1f);//设置饼图大小
    }

    /**
     * 设置饼状图数据
     */
    private void setPieChartData() {
        mColorData = new int[]{mStudentClassInfoBean.getYinDaoNum(), mStudentClassInfoBean.getYuYueNum()};
        mSliceValueArrayList = new ArrayList<SliceValue>();
        for (int i = 0; i < mColorData.length; i++) {
            SliceValue mSliceValue = new SliceValue(mColorData[i], PickColors.pickColor());
            mSliceValueArrayList.add(mSliceValue);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
