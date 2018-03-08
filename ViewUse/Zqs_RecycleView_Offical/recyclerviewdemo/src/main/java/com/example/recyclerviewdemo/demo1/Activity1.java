package com.example.recyclerviewdemo.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.recyclerviewdemo.R;

import java.util.ArrayList;

/**
 * Created by zqs on 2018/3/7.
 * /**
 * Demo1：RecyclerView的基本使用。
 * - ItemDecoration的范例：DividerItemDecoration。
 * - 为RecyclerView实现Headerview和Footerview。
 * - 添加 删除一行数据
 *
 */
public class Activity1 extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private ArrayList<ObjectModel> mData;
    private NormalAdapterWrapper mAdapter;
    private NormalAdapter mNoHeaderAdapter;
    private DividerItemDecoration mDecoration;
    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        mRecycleView = findViewById(R.id.rv);
        mLayoutManger = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(mLayoutManger);

        mNoHeaderAdapter = new NormalAdapter(mData = initData());
        mAdapter = new NormalAdapterWrapper(mNoHeaderAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header,mRecycleView,false);
        View footView = LayoutInflater.from(this).inflate(R.layout.item_footer,mRecycleView,false);
        mAdapter.addHeaderView(headerView);
        mAdapter.addFooterView(footView);
        mRecycleView.setAdapter(mAdapter);

        mDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST);
        mRecycleView.addItemDecoration(mDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                ObjectModel obj = new ObjectModel();
                obj.number = 0; obj.title = "Insert";
                mData.add(0,obj);
                mAdapter.notifyItemInserted(1);
                break;
            case R.id.item_delete:
                mData.remove(0);
                mAdapter.notifyItemRemoved(1);
                break;
            case R.id.item_change_divider:
                mDecoration.setDividerDrawable(getResources().getDrawable(R.drawable.divider));
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.item_hlistview:
                mRecycleView.removeItemDecoration(mDecoration);
                mLayoutManger = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL
                        ,false);
                mRecycleView.setLayoutManager(mLayoutManger);
                mDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST);
                mRecycleView.addItemDecoration(mDecoration);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<ObjectModel> initData(){
        ArrayList<ObjectModel> models = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.title_array);
        for(int i=0;i<titles.length;i++){
            ObjectModel model = new ObjectModel();
            model.number = i + 1;
            model.title = titles[i];
            models.add(model);
        }
        return models;
    }
}
