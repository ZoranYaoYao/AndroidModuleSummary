package com.example.demo1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zqs on 2018/3/14.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    private String[] tabTitles = new String[]{
            "","分享","收藏","关注","关注着"
    };
    private Context context;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context =  context;

    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position +1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
