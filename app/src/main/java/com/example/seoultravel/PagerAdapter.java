package com.example.seoultravel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tabfragment.TabFrag1;
import com.example.tabfragment.TabFrag2;
import com.example.tabfragment.TabFrag3;
import com.example.tabfragment.TabFrag4;
import com.example.tabfragment.TabFrag5;
import com.example.tabfragment.TabFrag6;
import com.example.tabfragment.TabFrag7;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFrag1 tab1 = new TabFrag1();
                return tab1;
            case 1:
                TabFrag2 tab2 = new TabFrag2();
                return tab2;
            case 2:
                TabFrag3 tab3 = new TabFrag3();
                return tab3;
            case 3:
                TabFrag4 tab4 = new TabFrag4();
                return tab4;
            case 4:
                TabFrag5 tab5 = new TabFrag5();
                return tab5;
            case 5:
                TabFrag6 tab6 = new TabFrag6();
                return tab6;
            case 6:
                TabFrag7 tab7 = new TabFrag7();
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
