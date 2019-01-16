package com.zoran.scroller;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zqs on 2019/1/16.
 *
 * refer：
 * https://blog.csdn.net/guolin_blog/article/details/48719871
 *
 * 1. 利用Scoller实现平滑的滑动
 * Scroller的基本用法其实还是比较简单的，主要可以分为以下几个步骤：
 * 创建Scroller的实例
 * 调用startScroll()方法来初始化滚动数据并刷新界面
 * 重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
 *
 * // 第一步，创建Scroller的实例
 *         mScroller = new Scroller(context);
 * // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
 *         mScroller.startScroll(getScrollX(), 0, dx, 0);
 *
 * @Override
 *     public void computeScroll() {
 *         // 第三步，重写computeScroll()方法，并在内部完成平滑滚动的逻辑
 *         if(mScroller.computeScrollOffset()) {
 *             scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
 *             invalidate();
 *         }
 *     }
 */
public class ScrollLayout extends ViewGroup {
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;


    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }

            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                if (diff > mTouchSlop) return true;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zqs", "onTouchEvent event = " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove; //Core 每次移动之后，重置上一次的滚动位置
                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollX() + getWidth()/2)/ getWidth();
                int dx = targetIndex*getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
//        return true; 所有事件都是ScollLayout响应
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在内部完成平滑滚动的逻辑
        if(mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}













































