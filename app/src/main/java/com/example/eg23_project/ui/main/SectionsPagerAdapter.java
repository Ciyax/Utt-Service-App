package com.example.eg23_project.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.eg23_project.AddEventCourses;
import com.example.eg23_project.AddEventEvent;
import com.example.eg23_project.AddEventMeeting;
import com.example.eg23_project.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_event_courses, R.string.tab_event_meeting, R.string.tab_event_event};
    private static final int[] TAB_ICONS_ENABLED = new int[]{R.drawable.ic_enabled_courses, R.drawable.ic_enabled_meeting, R.drawable.ic_enabled_event};
    private static final int[] TAB_ICONS_DISABLED = new int[]{R.drawable.ic_disabled_courses, R.drawable.ic_disabled_meeting, R.drawable.ic_disabled_event};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        //return PlaceholderFragment.newInstance(position + 1);
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new AddEventCourses();
                break;
            case 1:
                fragment = new AddEventMeeting();
                break;
            case 2:
                fragment = new AddEventEvent();
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    public Drawable getPageIconEnabled(int position) {
        return mContext.getResources().getDrawable(TAB_ICONS_ENABLED[position]);
    }

    public Drawable getPageIconDisabled(int position) {
        return mContext.getResources().getDrawable(TAB_ICONS_DISABLED[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}