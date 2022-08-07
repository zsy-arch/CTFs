package com.hy.frame.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: classes2.dex */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public ViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        if (this.views == null) {
            return 0;
        }
        return this.views.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.views.get(position));
        return this.views.get(position);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(View v, int position, Object obj) {
        ((ViewPager) v).removeView(this.views.get(position));
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

    @Override // android.support.v4.view.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void startUpdate(ViewGroup container) {
    }

    @Override // android.support.v4.view.PagerAdapter
    public void finishUpdate(View arg0) {
    }

    public void refresh(List<View> views) {
        this.views = views;
        notifyDataSetChanged();
    }
}
