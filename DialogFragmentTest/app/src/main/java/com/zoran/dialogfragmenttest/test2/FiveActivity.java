package com.zoran.dialogfragmenttest.test2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zoran.dialogfragmenttest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqs on 2018/11/5.
 */
public class FiveActivity extends AppCompatActivity {

    @BindView(R.id.btn_replace)
    Button btnReplace;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_add2)
    Button btnAdd2;
    @BindView(R.id.btn_hide)
    Button btnHide;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    FragmentManager fragmentManager;
    Fragment fragment1 = new Fragment1();
    Fragment fragment2 = new Fragment2();
    Fragment fragment3 = new Fragment3();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zqs", "FiveActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zqs", "FiveActivity onResume()");
    }

    @OnClick({R.id.btn_add, R.id.btn_add2, R.id.btn_hide,R.id.btn_replace,R.id.btn_show_1})
    public void onclick(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case  R.id.btn_add:
                transaction.add(R.id.fl_content, fragment1);
                break;
            case  R.id.btn_add2:
                transaction.add(R.id.fl_content, fragment2);
                break;
            case  R.id.btn_hide:
                transaction.hide(fragment1);
                break;
            case  R.id.btn_show_1:
                transaction.show(fragment1);
                break;
            case  R.id.btn_replace:
                transaction.replace(R.id.fl_content, fragment3);
                break;
        }
        transaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    /**
     * replace(R.id.fl_content, fragment3)
     *  eg: 添加1, 添加2, 隐藏1, 替换3
     *  1.replace操作会把fl_content容器里面的其他fragment都会进行remove操作
     *  2. 如果fragment3 replace操作替代fragment3的话, 根据源码不会有相关操作, 不会走fragment3相关生命周期
     */

    /**
     * .add(R.id.fl_content, fragment1);
     * .add(R.id.fl_content, fragment2);
     * eg: 添加1, 添加2
     * add操作相当于在FrameLayout fl_content添加.
     * 如果添加1了,在添加2, 会进行重叠效果. 相当于Framelayout布局效果
     */

    /**
     * hide(fragment1);
     * 不走fragment1任何生命周期, 只是控制fragment view隐藏
     * show(fragment1);
     * 不走fragment1任何生命周期, 只是控制fragment view显示
     */

    /**
     * fragment在xml中的申明形式
     * eg:
     *      <fragment
             android:id="@+id/fragment1"
             class="com.zoran.dialogfragmenttest.test2.Fragment1"
             android:layout_width="100dp"
             android:layout_height="100dp"/>
       fragment的生命周期是有Activity进行对应调用的
     */
}
