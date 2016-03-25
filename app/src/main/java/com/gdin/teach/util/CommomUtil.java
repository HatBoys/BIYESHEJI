package com.gdin.teach.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 通用工具类
 */
public class CommomUtil {

    /**
     * toast提示信息
     *
     * @param context
     * @param message
     */
    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast提示信息
     *
     * @param context
     * @param message
     */
    public static void toastApplicationMessage(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义Dialog的初始化
     *
     * @param context
     * @return
     */
    public static AlertDialog showCustomDialog(Context context) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        final AlertDialog mAlertDialog = mBuilder.create();
        return mAlertDialog;
    }

    public static PopupWindow showPopupWindow(View view) {
        //实例化pw对话框并且设置布局，大小，是否能获得焦点，第三个参数为true可以获得焦点，一般设置为true即可
        final PopupWindow mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        //对话框是否获得焦点，这是为true时，点击pw以外的地方不响应，否则点击响应。
        mPopupWindow.setFocusable(true);
        //原本是点击pw之外窗口消失，但是实验发现无效，只有设置了setBackgroundDrawable时才有效。
//        mPopupWindow.setOutsideTouchable(true);
        //设置pw对话框的动画效果
        mPopupWindow.setAnimationStyle(android.support.v7.appcompat.R.style.Animation_AppCompat_Dialog);
        //这个很重要，设置pw对话框背景为全透明，只有设置这个，点击pw对话框以外内容时，对话框消失，并且对话框能响应back还回键。
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置对话框的位置偏移量

//        showAsDropDown(View anchor)：相对某个控件的位置（正左下方），无偏移

//        showAsDropDown(View anchor, int xoff, int yoff)：相对某个控件的位置，有偏移

//        showAtLocation(View parent, int gravity, int x, int y)：相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        return mPopupWindow;

    }

    /**
     * 屏幕截图
     *
     * @param act
     * @return
     */
    public static Bitmap takeScreenShot(Activity act) {
        if (act == null || act.isFinishing()) {
            return null;
        }

        // 获取当前视图的view
        View scrView = act.getWindow().getDecorView();
        scrView.setDrawingCacheEnabled(true);
        scrView.buildDrawingCache(true);

        // 获取状态栏高度
        Rect statuBarRect = new Rect();
        scrView.getWindowVisibleDisplayFrame(statuBarRect);
        int statusBarHeight = statuBarRect.top;
        int width = act.getWindowManager().getDefaultDisplay().getWidth();
        int height = act.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap scrBmp = null;
        try {
            // 去掉标题栏的截图
            scrBmp = Bitmap.createBitmap(scrView.getDrawingCache(), 0, statusBarHeight,
                    width, height - statusBarHeight);
        } catch (IllegalArgumentException e) {
            Log.d("", "#### 旋转屏幕导致去掉状态栏失败");
        }
        scrView.setDrawingCacheEnabled(false);
        scrView.destroyDrawingCache();
        return scrBmp;
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

}
