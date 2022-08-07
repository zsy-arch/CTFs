package com.vsf2f.f2f.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ShopViewpagerAdapter extends PagerAdapter {
    private ArrayList<View> list;

    public ShopViewpagerAdapter(ArrayList<View> list) {
        this.list = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.list.get(position % this.list.size()));
        return this.list.get(position % this.list.size());
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.list.get(position % this.list.size()));
    }
}
