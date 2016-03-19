package com.gdin.teach.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.adapter.InClassMentionAdapter;
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
 * 类说明: 课堂点名界面
 */
public class InClassMentionFragment extends BaseFragment implements InClassMentionAdapter.OnItemClickLitener {

    @Bind(R.id.rl_in_class_mention)
    RecyclerView mRlInClassMention;
    private ArrayList<String> mUrlList;
    private LinearLayoutManager mLinearLayoutManager;
    private InClassMentionAdapter mAdapter;
    private String mKillUrl = "";
    private int mKillPosition = -1;
    private ArrayList<String> mKillUrlList;
    private ArrayList<Integer> mKillPositionList;

    public InClassMentionFragment() {
    }

    public InClassMentionFragment(ArrayList<String> urlArrayList) {
        mUrlList = urlArrayList;
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_mention, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKillUrlList = new ArrayList<String>();
        mKillPositionList = new ArrayList<Integer>();
        File mFile = new File(String.valueOf(getActivity().getDir(Constan.MENTIONSAVEDFILE
                , Context.MODE_PRIVATE)));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)//瀑布流
//        new GridLayoutManager(getActivity(), 3)

//        mRlInClassMention.addItemDecoration(new DividerItemDecoration(getActivity(),
//                DividerItemDecoration.HORIZONTAL_LIST));//ListView 格式的Divider

//        mRlInClassMention.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRlInClassMention.setLayoutManager(mLinearLayoutManager);//RecycleView的用法
        mRlInClassMention.setItemAnimator(new ScaleInLeftAnimator());
        mRlInClassMention.setHasFixedSize(true);
        mAdapter = new InClassMentionAdapter(getActivity(), mUrlList);
        mAdapter.setOnItemClickLitener(this);
        mRlInClassMention.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        mKillUrl = mUrlList.get(position);
        mKillPosition = position;

        mUrlList.remove(position);
        mAdapter.notifyItemRemoved(position);

        mKillUrlList.add(mKillUrl);
        mKillPositionList.add(mKillPosition);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @OnClick({R.id.bt_in_class_cancle, R.id.bt_inclass_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_in_class_cancle:
                int urlKillList = mKillUrlList.size();
                int positionKillList = mKillPositionList.size();

                if (urlKillList > 0 && positionKillList > 0 && positionKillList == urlKillList) {
                    mUrlList.add(mKillPositionList.get(positionKillList - 1), mKillUrlList.get(urlKillList - 1));
                    mAdapter.notifyItemInserted(mKillPositionList.get(positionKillList - 1));

                    mKillUrlList.remove(urlKillList - 1);
                    mKillPositionList.remove(positionKillList - 1);
                }
                if (urlKillList == 0 && positionKillList == 0) {//已经撤回所有数据
                    CommomUtil.toastMessage(getContext(), Constan.ALLREBACK);
                }

                if (urlKillList != positionKillList) {//操作异常
                    CommomUtil.toastMessage(getContext(), Constan.DOERROR);
                }

                break;

            case R.id.bt_inclass_upload:

                initDialog();
                break;
        }
    }

    /**
     * 实现一个Dialog
     */
    private void initDialog() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());

        final AlertDialog mAlertDialog = mBuilder.create();

        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.SUBMIT);
        mLeftButton.setText(Constan.CANCLE);
        mRightButton.setText(Constan.SURE);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream mFileOutputStream = getActivity().openFileOutput(Constan.MENTIONSAVEDFILE, Context.MODE_PRIVATE);
                    for (int i = 0; i < mUrlList.size(); i++) {
                        mFileOutputStream.write(mUrlList.get(i).getBytes());
                    }
                    mFileOutputStream.close();
                    CommomUtil.toastMessage(getContext(), Constan.SUBMITSUCCESS);//保存到本地数据
                    mAlertDialog.dismiss();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mAlertDialog.setView(mView);

        mAlertDialog.show();

    }
}
