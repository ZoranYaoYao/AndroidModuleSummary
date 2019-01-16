package com.zoran.constrainslayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * https://blog.csdn.net/guolin_blog/article/details/53122387 rebase
 * https://www.jianshu.com/p/38ee0aa654a8
 * https://www.jianshu.com/p/b884b8c46584
 *
 * refer: https://www.jianshu.com/p/38ee0aa654a8
 * 1. app:layout_constraintVertical_bias app:layout_constraintHorizontal_bias 定义：
 * 倾向
 * 在这种约束是同向相反的情况下，默认控件是居中的，但是也可以像拔河一样，让两个约束的力大小不等，这样就产生了倾向
 * 不写的话，默认值是0.5
 * 【zqs】意思就是作用剩余空间的一个比例分配
 *
 * 2.android:layout_width="0dp" app:layout_constraintHorizontal_weight="1.5"
 * 设置控件之间的宽高占比
 *
 * 3.<android.support.constraint.Guideline
 * 锚向指示线
 *
 * 4. layout_constraintHorizontal_chainStyle layout_constraintVertical_chainStyle
 *
 * 5. app:layout_constraintDimensionRatio="2:1"
 * 固定公式： 宽高比： 2：1
 * 如果加字母， 字母表示的分母的比
 * https://blog.csdn.net/u013394527/article/details/78875913
 *
 * eg: 宽高 9：16
 * android:layout_width="0dp"
 * android:layout_height="160dp"
 * app:layout_constraintDimensionRatio="9:16"  // 宽高比：9：16 ，高是160dp. 所以求得宽是100dp
 *
 * 6. 链条样式
 * https://blog.csdn.net/zxt0601/article/details/72683379/
 * 注意： 需要将链上的每一个控件前后都要关联起来，就向一个双向链表插入一样
 *
 * 7. tools:layout_editor_absoluteX="164dp" tools:layout_editor_absoluteY="234dp"
 *   拖动布局中X,Y的绝对距离
 *
 * =============
 * 1. 左对齐的方式!!
 *      app:layout_constraintStart_toStartOf="@+id/textView2"
 *
 * 2. 快速创建链式结构：选中所有，右键添加链式结构
 *      https://segmentfault.com/a/1190000009536640?utm_source=tuicool&utm_medium=referral
 *      eg:  app:layout_constraintHorizontal_chainStyle="spread_inside" 内部平均张开
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }
}
