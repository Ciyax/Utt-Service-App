package com.example.eg23_project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PracticalInfAdapter extends FragmentStatePagerAdapter {

    public PracticalInfAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new PracticalInfSchedules();
                break;
            case 1:
                fragment = new PracticalInfScheme();
                break;
            case 2:
                fragment = new PracticalInfHealth();
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
                charSequence = "Horaires";
                break;
            case 1:
                charSequence = "Plan de l\'UTT";
                break;
            case 2:
                charSequence = "Santé";
                break;
        }

        return charSequence;
    }
}
