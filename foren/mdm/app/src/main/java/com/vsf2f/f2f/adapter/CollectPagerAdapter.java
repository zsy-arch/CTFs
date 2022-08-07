package com.vsf2f.f2f.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.vsf2f.f2f.fragment.MySaveFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class CollectPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<MySaveFragment> list;
    private List<String> refresh_title;

    public CollectPagerAdapter(FragmentManager fm, ArrayList<MySaveFragment> list, List<String> refresh_title) {
        super(fm);
        this.list = list;
        this.refresh_title = refresh_title;
    }

    public CollectPagerAdapter(FragmentManager fm, ArrayList<MySaveFragment> list, String[] refresh_title) {
        super(fm);
        this.list = list;
        this.refresh_title = Arrays.asList(refresh_title);
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        return this.list.get(position);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return position > this.refresh_title.size() ? "暂未加载" : this.refresh_title.get(position);
    }
}
