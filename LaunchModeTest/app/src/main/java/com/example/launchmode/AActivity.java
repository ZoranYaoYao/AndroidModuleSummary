package com.example.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * refer：
 * https://blog.csdn.net/guolin_blog/article/details/41087993
 * https://stackoverflow.com/questions/3217942/android-intent-flag-activity-single-top-and-intent-flag-activity-clear-top
 *
 * 1.代码上Intent.FLAG_ACTIVITY_SINGLE_TOP
 * 等同于XML中的singleTop
 *
 * 2.代码上的intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
 * 等同于xml种的singleTask
 */
public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        findViewById(R.id.tv_dump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this, BActivity.class);
                startActivity(intent);
            }
        });
    }
}
