package com.example.seoultravel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tabfragment.TabFrag_1;
import com.example.tabfragment.TabFrag_2;
import com.example.tabfragment.TabFrag_3;
import com.example.tabfragment.TabFrag_4;

public class ThemePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ThemePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFrag_1 tab1 = new TabFrag_1();
                return tab1;
            case 1:
                TabFrag_2 tab2 = new TabFrag_2();
                return tab2;
            case 2:
                TabFrag_3 tab3 = new TabFrag_3();
                return tab3;
            case 3:
                TabFrag_4 tab4 = new TabFrag_4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
