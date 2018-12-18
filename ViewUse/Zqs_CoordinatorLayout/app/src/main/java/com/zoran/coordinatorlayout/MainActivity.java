package com.zoran.coordinatorlayout;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 1.AppBarLayout的内容能与下面的内容进行上下布局的条件
 *  1.上面布局是AppBarLayout
 *  2.设置属性app:layout_behavior="@string/appbar_scrolling_view_behavior"
 *
 * refer:https://blog.csdn.net/losingcarryjie/article/details/78917423
 * 2.AppBarLayout 子view的layout_scrollFlags属性介绍
 *     （1）app:layout_scrollFlags="scroll"
 *          效果是： 能跟着下面视图进行联动
 *
 *      (2)app:layout_scrollFlags="scroll|enterAlways"
 *          效果是：当AppBarLayout隐藏时，总是先显示，然后在滑动下面（Scrollview）控件
 *
 *      (3)android:minHeight="50dp"
 *         app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed"
 *          效果是：当AppBarLayout隐藏时，总是先显示minHeight高度AppBarLayout，
 *          然后滑动显示完下面（Scrollview）控件，最后在显示完整AppBarLayout高度
 *          【注意】此效果必须设置android:minHeight 和 scroll|enterAlways|enterAlwaysCollapsed3个元素
 *
 *      (4)android:minHeight="50dp"
 *         app:layout_scrollFlags="scroll|exitUntilCollapsed"
 *          效果是： 当AppBarLayout完全显示时，滑动下面（Scrollview）控件，将退出minHeight高度
 *          最后滑到顶部才能显示完全
 *          【现象】AppBarLayout退出时，都至少会显示minHeigth高度
 *          【注意】此效果必须设置android:minHeight
 *
 *      (5)app:layout_scrollFlags="scroll|snap"
 *          效果是：吸附性效果
 *
 * refer: https://www.jianshu.com/p/8ce727308f29
 * 3.expandedTitle属性
 *             app:expandedTitleGravity="left"
 *             app:expandedTitleMarginStart="10dp"
 *             app:expandedTitleMarginTop="10dp"
 *             app:expandedTitleMargin="10dp"
 *             app:expandedTitleMarginBottom="10dp"
 *             app:expandedTitleMarginEnd="10dp"
 * expandedTitleGravity：指定toolbar展开时，title所在的位置。类似的还有expandedTitleMargin、collapsedTitleGravity这些属性。
 * 当与Toolbar关联时，将与title(TextView)这个空间进行关联， 达到toolbar上面的title有变大缩小的效果
 *
 * refer:https://www.jianshu.com/p/419487584dde
 * 4. app:expandedTitleTextAppearance
 * ToolBar 默认的mTitleTextView（TextView）样式
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("我是压缩的title");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLUE);
    }
}
