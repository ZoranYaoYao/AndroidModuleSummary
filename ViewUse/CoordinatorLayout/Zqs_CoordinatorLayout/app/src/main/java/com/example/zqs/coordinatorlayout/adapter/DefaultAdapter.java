package com.example.zqs.coordinatorlayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by zqs on 2018/3/12.
 */

public abstract class DefaultAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected List<T> mDatas;
    protected OnItemClickListener mOnItemClickListener;
    protected OnLongItemClickListener mOnLongItemClickListener;

    public boolean isEmpty(){return mDatas==null || mDatas.size() ==0;}

    public void setDatas(List<T> datas) {
        if (datas == null) {
            return;
        }
        this.mDatas =datas;
        notifyDataSetChanged();
    }


    public void addSingleData(T t) {
        if (t == null) {
            return;
        }
        notifyItemInserted(mDatas.size()-1);
    }

    public void addDatas(List<T> datas,int position) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        mDatas.addAll(position,datas);
        notifyItemRangeInserted(position,datas.size());
    }

    public void removeDatas(List<T> datas,int position) {
        if (datas == null || datas.size() ==0) {
            return;
        }
        mDatas.removeAll(datas);
        notifyItemRangeRemoved(position,datas.size());
    }

    public void addSingleDate(T t,int position) {
        mDatas.add(position,t);
        notifyItemInserted(position);
    }

    public interface OnItemClickListener<T> {
        void onClick(View view, RecyclerView.ViewHolder holder, T o, int position);

    }

    public interface OnLongItemClickListener<T> {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T o, int position);
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 设置长按点击事件
     *
     * @param onLongItemClickListener
     */
    public void setonLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.mOnLongItemClickListener = onLongItemClickListener;
    }
}
