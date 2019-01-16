package com.zoran.scroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * refer:
 * https://blog.csdn.net/guolin_blog/article/details/48719871
 *
 * 1.View类的 scrollTo scrollBy方法
 * x值：正值向左移动，负值向右移动
 * y值：正值向上移动，负值向下移动
 *
 * 2.✨✨ scrollTo scrollBy方法，滑动时是针对该控件的内容进行滑动！该控件(left,top)(right,bottom)不会改变
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout linearLayout = findViewById(R.id.ll_container);

        Button btnScrollTo = findViewById(R.id.btn_scrollto);
        btnScrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.scrollTo(-60, -100);
            }
        });

        Button btnScrollBy = findViewById(R.id.btn_scrollby);
        btnScrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.scrollBy(-60, -100);
            }
        });

    }
}
