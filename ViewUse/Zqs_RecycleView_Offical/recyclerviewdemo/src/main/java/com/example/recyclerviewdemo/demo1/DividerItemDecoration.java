package com.example.recyclerviewdemo.demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.regex.Matcher;

/**
 * Created by zqs on 2018/3/7.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static  final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;

    public DividerItemDecoration(Context context,int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation!= VERTICAL_LIST) {
            throw  new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    public void setDividerDrawable(Drawable drawable) {
        this.mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST){
            drawHorizhontal(c,parent);
        }else {
            drawVertical(c,parent);
        }
    }

    /**
     * 确定divider的位置，设置在outRect中
     * 这个函数在计算RecyclerView中每个child大小时会用到
     * [Note] 必须设置 不然,分割线不会出现
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            int position =((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
            int count = parent.getAdapter().getItemCount();
            if(position < count -1) {
                outRect.set(0,0,0,mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0,0,mDivider.getIntrinsicHeight(),0);
            }
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDivider.getIntrinsicHeight();
            //设置界限
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    private void drawHorizhontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for(int i=0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
}
