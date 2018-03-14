package com.example.zqs.coordinatorlayout.dida;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.zqs.coordinatorlayout.R;
import com.example.zqs.coordinatorlayout.base.BaseFragmentAdapter;
import com.example.zqs.coordinatorlayout.ui.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/3/14.
 *   实现一个中间折叠的效果
 */
public class DidaSample  extends AppCompatActivity{


    ViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{
            "主页","微博","相册"
    };

    TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dida);
        // 第一步，初始化ViewPager和TabLayout
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }

        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);

        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

}
