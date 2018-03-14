package com.example.zqs.coordinatorlayout.recyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zqs on 2018/3/12.
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private View mConvertView;
    Context mContext;

    public BaseRecyclerHolder(View itemView) {
        this(itemView,itemView.getContext());
    }

    public BaseRecyclerHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
        mConvertView.setTag(this);
    }

    public static BaseRecyclerHolder createViewHolder(View itemView) {
        return createViewHolder(itemView.getContext(),itemView);
    }

    private static BaseRecyclerHolder createViewHolder(Context context, View itemView) {
        BaseRecyclerHolder holder = new BaseRecyclerHolder(itemView,context);
        return holder;
    }

    public static BaseRecyclerHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return createViewHolder(itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return  (T) view;
    }

    public View getmConvertView() {return mConvertView;}

    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyclerHolder setImageResource(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseRecyclerHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseRecyclerHolder setBackgroundColor(int viewId,int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseRecyclerHolder setBackgroundRes(int viewId,int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseRecyclerHolder setTextColor(int viewId,int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseRecyclerHolder setAlpha(int viewId,float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value,value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseRecyclerHolder setVisible(int viewId,boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible?View.VISIBLE:View.GONE);
        return this;
    }

    /**
     * 关于事件的
     */
    public BaseRecyclerHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseRecyclerHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseRecyclerHolder setOnLongClickListener(int viewId, View.OnLongClickListener
            listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
