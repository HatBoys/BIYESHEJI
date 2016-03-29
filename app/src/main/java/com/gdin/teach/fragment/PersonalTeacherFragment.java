package com.gdin.teach.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.activity.BroadcastActivity;
import com.gdin.teach.activity.MainActivityTeacher;
import com.gdin.teach.activity.ResetPassActivity;
import com.gdin.teach.util.CommomUtil;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class PersonalTeacherFragment extends BaseFragment {


    @Bind(R.id.iv_user_icon)
    ImageView mIvUserIcon;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.ll_person_center)
    LinearLayout mLlPersonCenter;
    private PopupWindow mPopupWindow;
    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindow = getActivity().getWindow();
        mParams = mWindow.getAttributes();
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_personal_teacher, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ll_register_later, R.id.ll_broadcast, R.id.ll_remind, R.id.ll_setting, R.id.ll_interaction, R.id.tv_copyright, R.id.iv_user_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_register_later:
                CommomUtil.toastApplicationMessage(getContext(), Constan.REGISTERLATER);
                settingRegister();
                break;
            case R.id.ll_broadcast:
                settingBroadcast();
                break;
            case R.id.ll_remind:
                initRemind();
                break;
            case R.id.ll_setting:
                initSettingPass();
                break;
            case R.id.ll_interaction:
                break;
            case R.id.tv_copyright:
                break;
            case R.id.iv_user_icon://设置用户头像
                settingUserIcon();
                break;
        }
    }

    private void initSettingPass() {
        ResetPassActivity.start2ResetPassActivity(getActivity());
    }

    private void initRemind() {
        //开启推送并设置注册的回调处理
        PushAgent mPushAgent = PushAgent.getInstance(getActivity().getApplicationContext());
        mPushAgent.enable();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, Constan.REMINDCLASS);
        startActivity(intent);
    }

    private void settingBroadcast() {
        BroadcastActivity.start2BraodcastActivity(getActivity());
    }

    private void settingRegister() {
        MainActivityTeacher mMainActivityTeacher = (MainActivityTeacher) getActivity();
        MainTeacherFragment mMainTeacherFragment = (MainTeacherFragment) mMainActivityTeacher
                .getSupportFragmentManager().findFragmentByTag(Constan.CLASSINFODETAILFRAGMENT);
        mMainTeacherFragment.onClick(mMainTeacherFragment.mButton);//
        // TODO: 16/3/25 暂时只跳转上课界面，跳转上课——》课堂点名待定
    }

    private void settingUserIcon() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.SELECTPICTUREFROM);
        mLeftButton.setText(Constan.TAKEPHOTO);
        mRightButton.setText(Constan.GALLERY);

        mPopupWindow = CommomUtil.showPopupWindow(mView);

        mParams.alpha = 0.2f;//设置背景颜色
        mWindow.setAttributes(mParams);

        mPopupWindow.showAtLocation(mLlPersonCenter, Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mParams.alpha = 1f;
                mWindow.setAttributes(mParams);
            }
        });
        /**
         * 拍照
         */
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromCameraCapture();
            }
        });
/**
 * 相册
 */
        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
            }
        });

    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (CommomUtil.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 用户没有进行有效的设置操作，返回
        if (resultCode == getActivity().RESULT_CANCELED) {
            Toast.makeText(getContext(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(data.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (CommomUtil.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getContext(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (data != null) {
                    try {
                        setImageToHeadView(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) throws IOException {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            //保存图像
            FileOutputStream mOutPutStream = getActivity().openFileOutput(Constan.USERICONFILE, Context.MODE_PRIVATE);
            mOutPutStream.write(extras.getByte(Constan.USERICONFILEKEY));
            mOutPutStream.close();

            Bitmap photo = extras.getParcelable("data");
            mIvUserIcon.setImageBitmap(photo);
            MyApplication.mSharedPreferences.edit().putBoolean(Constan.SAVEDICON, true).commit();
            mPopupWindow.dismiss();
        }
    }

}
