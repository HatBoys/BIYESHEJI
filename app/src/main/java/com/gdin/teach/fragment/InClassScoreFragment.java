package com.gdin.teach.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private ArrayList<String> mImageUrlList;
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
    private View mItemView;
    private ArrayList<String> mKillUrlList;
    private ArrayList<Integer> mKillUrlPositionList;
    private int mKillPosition;
    private int mUrlKillSize;
    private int mKillUrlPositionSize;
    private int mAllNum;

    public InClassScoreFragment() {
    }

    @SuppressLint("ValidFragment")
    public InClassScoreFragment(ArrayList<String> imageUrlArrayList) {
        mImageUrlList = imageUrlArrayList;
        mAllNum = imageUrlArrayList.size();
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
        mKillUrlList = new ArrayList<String>();
        mKillUrlPositionList = new ArrayList<Integer>();
        File mFile = new File(String.valueOf(getActivity().getDir(Constan.SCORESAVEDFILE
                , Context.MODE_PRIVATE)));
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
                solveCancle();
                break;
            case R.id.bt_inclass_upload:
                initDialog();
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
                    mKillUrlList.add(mImageUrlList.get(mKillPosition));
                    mKillUrlPositionList.add(mKillPosition);
                    mImageUrlList.remove(mKillPosition);
                    mScoreAdapter.notifyItemRemoved(mKillPosition);

                } else {
                    CommomUtil.toastMessage(getContext(), Constan.PLEASECHECK);
                }
                break;
        }
    }

    /**
     * 实现一个Dialog
     */
    private void initDialog() {
//        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
//        抽取出Dialog的创建共性，作为公共方法
//        final AlertDialog mAlertDialog = mBuilder.create();

        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.SUBMIT);
        mLeftButton.setText(Constan.CANCLE);
        mRightButton.setText(Constan.SURE);

       /* mAlertDialog = CommomUtil.showCustomDialog(getContext());

        mAlertDialog.setView(mView);*/

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

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream mFileOutputStream = getActivity().openFileOutput(Constan.SCORESAVEDFILE, Context.MODE_PRIVATE);
                    for (int i = 0; i < mImageUrlList.size(); i++) {
                        mFileOutputStream.write(mImageUrlList.get(i).getBytes());
                    }
                    mFileOutputStream.close();
                    CommomUtil.toastMessage(getContext(), Constan.SUBMITSUCCESS);//保存到本地数据
                    mPopupWindow.dismiss();


                    for (int i = 0; i < mAllNum; i++) {
                        mKillUrlPositionSize = mKillUrlPositionList.size();
                        mUrlKillSize = mKillUrlList.size();
                        if (mKillUrlPositionSize > 0 && mUrlKillSize > 0 && mUrlKillSize == mKillUrlPositionSize) {
                            mImageUrlList.add(mKillUrlPositionList.get(mKillUrlPositionSize - 1), mKillUrlList.get(mUrlKillSize - 1));
                            mScoreAdapter.notifyItemInserted(mKillUrlPositionList.get(mKillUrlPositionSize - 1));

                            mKillUrlList.remove(mUrlKillSize - 1);
                            mKillUrlPositionList.remove(mKillUrlPositionSize - 1);
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void solveCancle() {
        mUrlKillSize = mKillUrlList.size();
        mKillUrlPositionSize = mKillUrlPositionList.size();

        if (mUrlKillSize > 0 && mKillUrlPositionSize > 0 && mKillUrlPositionSize == mUrlKillSize) {
            mImageUrlList.add(mKillUrlPositionList.get(mKillUrlPositionSize - 1), mKillUrlList.get(mUrlKillSize - 1));
            mScoreAdapter.notifyItemInserted(mKillUrlPositionList.get(mKillUrlPositionSize - 1));

            mKillUrlList.remove(mUrlKillSize - 1);
            mKillUrlPositionList.remove(mKillUrlPositionSize - 1);
        }
        if (mUrlKillSize == 0 && mKillUrlPositionSize == 0) {//已经撤回所有数据
            CommomUtil.toastMessage(getContext(), Constan.ALLREBACK);
        }

        if (mUrlKillSize != mKillUrlPositionSize) {//操作异常
            CommomUtil.toastMessage(getContext(), Constan.DOERROR);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        CommomUtil.toastMessage(getContext(), position + "");
        mKillPosition = position;
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
