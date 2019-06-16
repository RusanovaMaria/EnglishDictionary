package com.rusanova.englishdictionary.mainactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rusanova.englishdictionary.pagefragment.PageFragmentFactory;

public class AppBarPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_QUANTITY = 2;

    public AppBarPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return PAGE_QUANTITY;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragmentFactory.form(position, null);
    }

    public CharSequence getPageTitle(int position) {
        return PageFragmentFactory.getTitle(position);
    }
}
