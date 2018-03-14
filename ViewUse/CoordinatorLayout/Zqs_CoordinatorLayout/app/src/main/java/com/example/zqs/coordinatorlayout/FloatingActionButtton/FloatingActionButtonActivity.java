package com.example.zqs.coordinatorlayout.FloatingActionButtton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zqs.coordinatorlayout.R;
import com.example.zqs.coordinatorlayout.adapter.ItemAdapter;

import java.util.ArrayList;

/**
 * Created by zqs on 2018/3/13.
 */

public class FloatingActionButtonActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private ItemAdapter mItemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("I am %d item",i);
            mDatas.add(s);
        }
        mItemAdapter = new ItemAdapter(this,mDatas);
        mRecyclerView.setAdapter(mItemAdapter);
    }
}
