package com.example.recyclerviewdemo.demo5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Created by zqs on 2018/3/8.
 */

/**
 * RecyclerView实现setEmptyView()
 * 运用到观察者模式 来实现
 */
public class Activity5 extends AppCompatActivity {

      private EmptyRecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        mRv = findViewById(R.id.rv);
        //没有Layoutmanager 就不没有onmeasure onlayout ondraw 的执行
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<String>();
        mAdapter = new NormalAdapter(mData);
        View view = findViewById(R.id.text_empty);
        mRv.setEmptyView(view);
        mRv.setAdapter(mAdapter);
    }

    private List<String> mData;
    private NormalAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                mData.add(0, "hello");
                mAdapter.notifyItemInserted(0);
                break;
            case R.id.item_delete:
                if (!mData.isEmpty()) {
                    mData.remove(0);
                    mAdapter.notifyItemRemoved(0);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
