package com.example.opencurtain.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.opencurtain.Fragment.NonSubscribeFragment;
import com.example.opencurtain.Fragment.SubscribeFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                SubscribeFragment subscribeFragment = new SubscribeFragment();
                return subscribeFragment;
            case 1:
                NonSubscribeFragment nonSubscribeFragment = new NonSubscribeFragment();
                return nonSubscribeFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return  tabCount;
    }
}