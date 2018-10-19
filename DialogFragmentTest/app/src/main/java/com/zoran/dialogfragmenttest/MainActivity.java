package com.zoran.dialogfragmenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyDialogFragment myDialogFragment = new MyDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_zz = findViewById(R.id.tv_zz);
        tv_zz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment.show(getSupportFragmentManager(), "zqs");
            }
        });
    }
}
