package com.rusanova.englishdictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rusanova.englishdictionary.pagefragment.DictionaryListFragment;
import com.rusanova.englishdictionary.pagefragment.WordListFragment;

public class AppBarPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_QUANTITY = 3;

    public AppBarPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return PAGE_QUANTITY;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DictionaryListFragment();
            case 1:
                return new WordListFragment();
        }
        return new DictionaryListFragment();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Dictionaries";
            case 1:
                return "Words";
            case 2:
                return "Tasks";
        }
        return "";
    }
}
