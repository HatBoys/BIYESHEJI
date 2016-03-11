package com.gdin.teach.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 通用工具类
 */
public class CommomUtil {
    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
