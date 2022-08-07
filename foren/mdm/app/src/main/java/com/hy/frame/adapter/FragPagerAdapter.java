package com.hy.frame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hy.frame.common.BaseFragment;
import java.util.List;

/* loaded from: classes2.dex */
public class FragPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private String[] titles;

    public FragPagerAdapter(FragmentManager manager, List<BaseFragment> fragments) {
        this(manager, fragments, null);
    }

    public FragPagerAdapter(FragmentManager manager, List<BaseFragment> fragments, String[] titles) {
        super(manager);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        if (this.fragments == null) {
            return 0;
        }
        return this.fragments.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int position) {
        if (this.titles != null) {
            return this.titles[position];
        }
        return null;
    }
}
