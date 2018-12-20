package com.zoran.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.tv_custom);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                DisplayMetrics dm = MainActivity.this.getResources().getDisplayMetrics();
                Log.e("zqs"," density = " + dm.density); //2.75
                Log.e("zqs", "textView getHeight()=" + textView.getHeight());
                Log.e("zqs", "textView getMeasuredHeight()=" + textView.getMeasuredHeight());
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
