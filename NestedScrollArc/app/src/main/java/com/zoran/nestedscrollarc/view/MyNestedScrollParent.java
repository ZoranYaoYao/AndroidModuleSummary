package com.zoran.nestedscrollarc.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zqs on 2019/3/5.
 */
public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent {
    private ImageView img;
    private TextView tv;
    private MyNestedScrollChild nsc;
    private NestedScrollingParentHelper mParentHelper;
    private int imgHeight;
    private int tvHeight;

    public MyNestedScrollParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        img = (ImageView) getChildAt(0);
        tv = (TextView) getChildAt(1);
        nsc = (MyNestedScrollChild) getChildAt(2);

        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imgHeight = img.getMeasuredHeight();
                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvHeight = tv.getMeasuredHeight();
                tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        super.onFinishInflate();
    }

    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }

        super.scrollTo(x, y);
    }

    //接口实现--------------------------------------------------
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if (target instanceof MyNestedScrollChild)
            return true;
        return false;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (showImg(dy) || hideImg(dy)) {
            scrollBy(0, -dy);
            consumed[1] = dy;
        }
    }



    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
    }

    //后于child滚动
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    //返回值：是否消费了fling
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }
    //接口实现--------------------------------------------------

    //下拉的时候是否要向下滚动以显示图片
    private boolean showImg(int dy) {
        Log.e("zqs", "dy = " + dy);
        Log.e("zqs", "getScrollY() = " + getScrollY());
        if (dy > 0) {
            if (getScrollY() > 0 && nsc.getScrollY() == 0) {
                return true;
            }
        }
        return false;
    }

    //上拉的时候，是否要向上滚动，隐藏图片
    public boolean hideImg(int dy) {
        if (dy < 0) {
            if (getScrollY() < imgHeight) {
                return true;
            }
        }
        return false;
    }
}
