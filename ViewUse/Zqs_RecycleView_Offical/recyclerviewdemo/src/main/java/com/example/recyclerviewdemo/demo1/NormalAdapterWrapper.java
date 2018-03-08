package com.example.recyclerviewdemo.demo1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zqs on 2018/3/7.
 */

public class NormalAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private NormalAdapter mAdapter;
    private View mHeaderView;
    private View mFooterView;
    enum ITEM_TYPE {
        HEADER,FOOTER,NORMAL
    }
    public  NormalAdapterWrapper(NormalAdapter adapter){
        mAdapter = adapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.HEADER.ordinal()) {
            return  new RecyclerView.ViewHolder(mHeaderView){};
        }else if(viewType == ITEM_TYPE.FOOTER.ordinal()) {
            return new RecyclerView.ViewHolder(mFooterView) {};
        }else {
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0) {
            return;
        }else if(position == mAdapter.getItemCount() +1) {
            return;
        }else {
            mAdapter.onBindViewHolder((NormalAdapter.VH) holder,position-1);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() +2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        }else if (position == mAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    public void addHeaderView(View view) {
        mHeaderView = view;
    }
    public void addFooterView(View view) {
        mFooterView = view;
    }
}
