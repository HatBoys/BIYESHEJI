package com.gdin.teach.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/4/14.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class TakeNotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
    }

    public static void start2TakeNotesActivity(Activity activity) {
        Intent mIntent = new Intent(activity, TakeNotesActivity.class);
        activity.startActivity(mIntent);
    }
}
