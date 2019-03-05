package com.zoran.nestedscrollarc.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * https://blog.csdn.net/al4fun/article/details/53888990 rebase!
 *
 * 解决父类与子类的滑动逻辑问题
 *
 * 核心：
 * NestedScrollingChild
 * NestedScrollingParent
 * NestedScrollingChildHelper
 * NestedScrollingParentHelper
 *
 * 原理：
 * 通过在子View的onTouchEvent()中去检查是否需要dispatchNestedPreScroll()让父视图先进行移动消费距离！
 *
 * NestScroll机制
 * 1.通过子view去实现NestedScrollChild, 父view去实现NestedScrollParent
 * 2.然后通过子类关联的实现类NestedScrollChildHelper去连接父类的回调。 父view关联NestedScrollParentHelper
 * 3.最终达到2个view进行嵌套的滑动效果！
 */
public class MyNestedScrollChild extends LinearLayout implements NestedScrollingChild {
    private NestedScrollingChildHelper mScrollingChildHelper;
    private final int[] offset = new int[2];
    private final int[] consumed = new int[2];
    private int lastY;
    private int showHeight;

    public MyNestedScrollChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //第一次测量，因为布局文件中高度是wrap_content，因此测量模式为ATMOST，即高度不能超过父控件的剩余空间
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        showHeight = getMeasuredHeight();

        //第二次测量，对高度没有任何限制，那么测量出来的就是完全展示内容所需要的高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getRawY();
                //优化1： 将判断是否有联动父控件 放在down处理，避免多次调用
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL); //如果找到了支持嵌套滚动的父类
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) event.getRawY();
                int dy = y - lastY;
                lastY = y;

                //Nice_Core 子view滑动之前，调用preScroll给父View进行业务逻辑运用
                if (dispatchNestedPreScroll(0, dy, consumed, offset)) {
                    int remain = dy - consumed[1];
                    if (remain != 0) {
                        scrollBy(0, -remain);
                    }
                } else {
                    scrollBy(0, -dy);
                }
                break;
        }

        return true;
    }

    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        int MaxY = getMeasuredHeight() - showHeight;
        if (y > MaxY) {
            y = MaxY;
        }
        if (y < 0) {
            y = 0;
        }
        super.scrollTo(x, y);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
            mScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return mScrollingChildHelper;
    }

    //接口实现--------------------------------------------------


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return getScrollingChildHelper().startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }
}
