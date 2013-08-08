package com.gamalinda.practice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringArrayRes;
import com.viewpagerindicator.TitlePageIndicator;

@EActivity(R.layout.tab_navigation_view)
public class TabNavigationActivity extends SherlockFragmentActivity {

    @StringArrayRes
    String[] tabs;

    @ViewById(R.id.view_pager)
    ViewPager viewPager;

    @ViewById(R.id.indicator)
    TitlePageIndicator titlePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews() {
        viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager()));
        titlePageIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public CustomFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new TabNavigationFragment_();
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
