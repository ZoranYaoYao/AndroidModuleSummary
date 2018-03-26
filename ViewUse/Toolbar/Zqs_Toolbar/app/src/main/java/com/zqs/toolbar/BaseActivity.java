package com.zqs.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zqs on 2018/3/26.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 通用的ToolBar标题
     */
    private TextView commonTitleTv;
    /**
     * 通用的ToolBar
     */
    private Toolbar commonTitleTb;
    /**
     * 内容区域
     */
    private RelativeLayout content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    protected void initView() {
        commonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        commonTitleTb = (Toolbar) findViewById(R.id.common_title_tb);
        content = (RelativeLayout) findViewById(R.id.content);
    }

    //这个写的不好
    public void setToolBar(int layout) {
        hidetoolBar();
        commonTitleTb = (Toolbar) content.findViewById(layout);
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void hidetoolBar() {
        commonTitleTb.setVisibility(View.GONE);
    }

    public void hideOriginTitle(){
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setToolbarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        commonTitleTb.setOnMenuItemClickListener(onclick);
    }

    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(R.drawable.common_back_ic);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(upArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        commonTitleTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            commonTitleTv.setText(title);
        }
    }

    public void setTitle(int resId) {
        commonTitleTv.setText(resId);
    }
}
