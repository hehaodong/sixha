package com.andy.sixha.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.adapter
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-28 14:54
 */
public class TabAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> views;

    public TabAdapter(FragmentManager fm, String[] title, List<Fragment> views) {
        super(fm);
        this.titles = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
