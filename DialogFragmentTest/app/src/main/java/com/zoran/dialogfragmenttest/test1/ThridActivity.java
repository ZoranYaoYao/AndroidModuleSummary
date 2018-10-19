package com.zoran.dialogfragmenttest.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zoran.dialogfragmenttest.R;

/**
 * Created by zqs on 2018/10/18.
 * 单纯的用dialog中 添加fragment不可行
 */
public class ThridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_zz = findViewById(R.id.tv_zz);
        tv_zz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog  = new MyDialog(ThridActivity.this);
                dialog.show();
            }
        });
    }
}
