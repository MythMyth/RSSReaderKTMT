package ktmt.rssreader.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ktmt.rssreader.Data.Link;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.fragments.ListNewsFragment;

public class ListTitlePagerAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>();
    private int WhatPaper;

    public ListTitlePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ListNewsFragment.newInstance(position,WhatPaper);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    public void setWhatPaper(int whatPaper) {
        WhatPaper = whatPaper;
        notifyDataSetChanged();
    }
}
