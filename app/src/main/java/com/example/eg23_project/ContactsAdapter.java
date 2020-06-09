package com.example.eg23_project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ContactsAdapter extends FragmentStatePagerAdapter {

    public ContactsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
            case 1:
            case 2:
                fragment = new ContactItemFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence = null;

        switch (position){
            case 0:
                charSequence = "TC";
                break;
            case 1:
                charSequence = "A2I";
                break;
            case 2:
                charSequence = "GI";
                break;
        }

        return charSequence;
    }
}
