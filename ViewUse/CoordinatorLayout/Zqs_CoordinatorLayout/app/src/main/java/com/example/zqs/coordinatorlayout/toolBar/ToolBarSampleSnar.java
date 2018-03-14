package com.example.zqs.coordinatorlayout.toolBar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.zqs.coordinatorlayout.R;
import com.example.zqs.coordinatorlayout.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/3/12.
 *
 *
 */
public class ToolBarSampleSnar extends AppCompatActivity {
    private static final String TAG = "ToolBarSampleSnar";

    RecyclerView mRecyclerView;
    List<String> mDatas;
    ItemAdapter  mAdapter;

    Toolbar mToolbar;
    private RelativeLayout mRlBottomSheet;
    private BottomSheetBehavior<RelativeLayout> mFrom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRlBottomSheet = (RelativeLayout) findViewById(R.id.rl_bottom_sheet);
        mFrom = BottomSheetBehavior.from(mRlBottomSheet);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("toolbar");
        setSupportActionBar(mToolbar);
    
        initListener();
        initData();
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this
                ,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("我是第%d个item", i);
            mDatas.add(s);
        }
        mAdapter = new ItemAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mFrom.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i(TAG, "onStateChanged: newState=" +newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onStateChanged: slideOffset=" +slideOffset);
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
