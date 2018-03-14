package com.example.zqs.coordinatorlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zqs.coordinatorlayout.recyclerView.BaseRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/3/12.
 */

public abstract class BaseRecyclerAdapter<T> extends DefaultAdapter<T> {

    //mDatas 继承过来的!
    protected Context mContext;
    protected final int mItemLayoutId;

    public BaseRecyclerAdapter(Context context, int mItemLayoutId) {
        this.mItemLayoutId = mItemLayoutId;
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    public BaseRecyclerAdapter(Context context, int itemLayoutId, List<T> datas) {
        mContext = context;
        mItemLayoutId = itemLayoutId;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerHolder holder = new BaseRecyclerHolder(
                LayoutInflater.from(mContext).inflate(mItemLayoutId,parent,false));
        setListener(parent,holder,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseRecyclerHolder baseHolder = (BaseRecyclerHolder) holder;
        convert(baseHolder, (T) mDatas.get(position), position);
    }

    private void setListener(ViewGroup parent, final BaseRecyclerHolder holder, int viewType) {
        if(!isEnabled(viewType)) return;
        holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener !=null) {
                    int position = holder.getAdapterPosition() -1;
                    T t = mDatas.get(position);
                    mOnItemClickListener.onClick(v,holder,t,position);
                }
            }
        });

        holder.getmConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnLongItemClickListener.onItemLongClick(v, holder, mDatas.get
                            (position), position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return isEmpty() ? 0 : mDatas.size();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    public abstract void convert(BaseRecyclerHolder holder,T item, int position);
}
