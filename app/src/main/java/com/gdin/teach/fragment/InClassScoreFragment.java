package com.gdin.teach.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.adapter.InClassScoreAdapter;
import com.gdin.teach.util.CommomUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class InClassScoreFragment extends BaseFragment implements InClassScoreAdapter.OnItemClickLitener, View.OnClickListener {
    @Bind(R.id.rl_in_class_mention)
    RecyclerView mRlInClassMention;
    @Bind(R.id.tv_mention_num)
    TextView mTvMentionNum;
    @Bind(R.id.bt_in_class_cancle)
    Button mBtInClassCancle;
    @Bind(R.id.bt_inclass_upload)
    Button mBtInclassUpload;
    private final ArrayList<String> mImageUrlList;
    @Bind(R.id.ll_in_class)
    LinearLayout mLlInClass;
    private InClassScoreAdapter mScoreAdapter;
    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    private RadioGroup mRadioGroup;
    private PopupWindow mPopupWindow;
    private RadioButton mButtonExce;
    private RadioButton mButtonGood;
    private RadioButton mButtonPass;
    private RadioButton mButtonFail;

    public InClassScoreFragment(ArrayList<String> imageUrlArrayList) {
        mImageUrlList = imageUrlArrayList;
    }


    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_mention, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindow = getActivity().getWindow();
        mParams = mWindow.getAttributes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        mRlInClassMention.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.HORIZONTAL));
        mRlInClassMention.setItemAnimator(new ScaleInLeftAnimator());
        mRlInClassMention.setHasFixedSize(true);
        mScoreAdapter = new InClassScoreAdapter(getActivity(), mImageUrlList);
        mScoreAdapter.setOnItemClickLitener(this);
        mRlInClassMention.setAdapter(mScoreAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_in_class_cancle, R.id.bt_inclass_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_in_class_cancle:

                break;
            case R.id.bt_inclass_upload:

                break;
            case R.id.rb_exce_in_class_score_check:
                mRadioGroup.check(R.id.rb_exce_in_class_score_check);
                break;
            case R.id.rb_good_in_class_score_check:
                mRadioGroup.check(R.id.rb_good_in_class_score_check);

                break;
            case R.id.rb_pass_in_class_score_check:
                mRadioGroup.check(R.id.rb_pass_in_class_score_check);

                break;
            case R.id.rb_fail_in_class_score_check:
                mRadioGroup.check(R.id.rb_fail_in_class_score_check);

                break;

            case R.id.bt_dialog_score_sure:
                if (mButtonExce.isChecked() || mButtonGood.isChecked() || mButtonPass.isChecked() || mButtonFail.isChecked()) {
                    mPopupWindow.dismiss();
                } else {
                    CommomUtil.toastMessage(getContext(), Constan.PLEASECHECK);
                }
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        CommomUtil.toastMessage(getContext(), position + "");
        initDialogView();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initDialogView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.doalog_in_class_score, null);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_in_class_score_check);
        mButtonExce = (RadioButton) mView.findViewById(R.id.rb_exce_in_class_score_check);
        mButtonGood = (RadioButton) mView.findViewById(R.id.rb_good_in_class_score_check);
        mButtonPass = (RadioButton) mView.findViewById(R.id.rb_pass_in_class_score_check);
        mButtonFail = (RadioButton) mView.findViewById(R.id.rb_fail_in_class_score_check);
        Button mButton = (Button) mView.findViewById(R.id.bt_dialog_score_sure);

        mButtonExce.setOnClickListener(this);
        mButtonGood.setOnClickListener(this);
        mButtonPass.setOnClickListener(this);
        mButtonFail.setOnClickListener(this);
        mButton.setOnClickListener(this);

        mPopupWindow = CommomUtil.showPopupWindow(mView);
        mParams.alpha = 0.2f;//设置背景颜色
        mWindow.setAttributes(mParams);

        mPopupWindow.showAtLocation(mLlInClass, Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mParams.alpha = 1f;
                mWindow.setAttributes(mParams);
            }
        });
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
