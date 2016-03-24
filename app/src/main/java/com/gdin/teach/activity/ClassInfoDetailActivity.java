package com.gdin.teach.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.ClassInfoDetailFragment;
import com.gdin.teach.util.CommomUtil;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;
import java.util.Map;

import static com.umeng.socialize.bean.SHARE_MEDIA.*;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN_CIRCLE;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课程信息详情界面,饼状图用到开源框架https://github.com/lecho/hellocharts-android
 */
public class ClassInfoDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private static int mCurrentposition;
    private static String mClassInfo;
    private static ArrayList<String> mImageUrlArrayList;
    private UMShareAPI mShareAPI;
    private SHARE_MEDIA mSHARE_media;

    // TODO: 16/3/13 两个onCreate方法，有什么不同点
    /*@Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_base, new ClassInfoDetailFragment(mCurrentposition, mClassInfo, mImageUrlArrayList), Constan.CLASSINFODETAILFRAGMENT)
                .commit();

        mTlBase.setVisibility(View.VISIBLE);
        mTlBase.setNavigationIcon(R.mipmap.back);
        mTlBase.setTitle("");

        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTlBase.setOnMenuItemClickListener(this);
    }

    public void setTitle(String title) {
        if (mTlBase != null) {
            setMyTitle(title);
        }
    }

    /**
     * 跳转到ClassInfoDetailActivity
     *
     * @param context
     * @param position
     * @param item
     * @param imageUrlArrayList
     */
    public static void start2ClassInfoDetailActivity(Context context, int position, Object item, ArrayList<String> imageUrlArrayList) {
        Intent mIntent = new Intent(context, ClassInfoDetailActivity.class);
        mCurrentposition = position;
        mClassInfo = (String) item;
        mImageUrlArrayList = imageUrlArrayList;
        context.startActivity(mIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            // TODO: 16/3/22 友盟分享
            initListenerUmeng();
        }
        return true;
    }

    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getApplicationContext(), " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getApplicationContext(), " 分享失败啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(getApplicationContext(), " 分享取消啦", Toast.LENGTH_SHORT).show();

        }
    };

    private void initListenerUmeng() {

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("分享");
        dialog.setMessage("正在跳转中，客官请稍等");
        Config.dialog = dialog;


        mShareAPI = UMShareAPI.get(this);

        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        WEIXIN, WEIXIN_CIRCLE, QQ, QZONE
                };
        UMImage image = new UMImage(this,
                BitmapFactory.decodeResource(getResources(), R.drawable.school_icon));

        new ShareAction(this).setDisplayList(displaylist)
//                .withText("呵呵")
//                .withTitle("title")
//                .withTargetUrl("http://www.baidu.com")
                .withMedia(image)
                .setListenerList(mUMShareListener)
                .setShareboardclickCallback(shareBoardlistener)
                .open();

    }

    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {
        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

//            UMImage image = new UMImage(getApplicationContext(),
//                    BitmapFactory.decodeResource(getResources(), R.mipmap.launcher));
            Bitmap mBitmap = CommomUtil.takeScreenShot(ClassInfoDetailActivity.this);
            UMImage mImage = new UMImage(getApplicationContext(), mBitmap);

            switch (share_media) {
                case WEIXIN:
                    mSHARE_media = WEIXIN;
                    break;
                case WEIXIN_CIRCLE:
                    mSHARE_media = WEIXIN_CIRCLE;
                    break;
                case QQ:
                    mSHARE_media = QQ;
                    break;
                case QZONE:
                    mSHARE_media = QZONE;
                    break;
            }

            new ShareAction(ClassInfoDetailActivity.this)
                    .setPlatform(mSHARE_media)
                    .setCallback(mUMShareListener)
                    .withText(Constan.SHARECONTENT)
//                    .withTargetUrl("http://user.qzone.qq.com/1140390843/main")
                    .withMedia(mImage)
                    .withTitle(Constan.SHARETITLE)
                    .share();
        }

    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
