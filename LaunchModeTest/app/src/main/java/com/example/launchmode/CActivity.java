package com.example.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by zqs on 2019/1/23.
 */
public class CActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        Log.e("zqs", "CActivity onCreate");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CActivity.this, BActivity.class);
                /**
                 * 代码上Intent.FLAG_ACTIVITY_SINGLE_TOP
                 * 等同于XML中的singleTop
                 */
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                /**
                 * 代码上的intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 * 等同于xml种的singleTask
                 * 回退栈：A-B
                 */
                //                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                /**
                 * 单独用FLAG_ACTIVITY_NEW_TASK， 只会重新创建BActivity
                 * 回退栈：A-B-C-B
                 * ✨✨当然这里讨论的仍然还是启动其它程序中的Activity。这个flag的作用通常是模拟一种Launcher的行为
                 */
                //                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                /**
                 * 单独用FLAG_ACTIVITY_NEW_TASK， 只会重新创建BActivity
                 * ✨回退栈：A-B(该Activity为新建的！)
                 */
                //                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                /**
                 * 与FLAG_ACTIVITY_SINGLE_TOP效果一致，等同于XML中的singleTop
                 */
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                /**
                 * 单独使用效果
                 * A-B(新建的！)
                 */
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
