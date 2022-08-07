package com.easeui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import java.util.List;

/* loaded from: classes.dex */
public class EmojiconPagerAdapter extends PagerAdapter {
    private List<View> views;

    public EmojiconPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.views.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(this.views.get(arg1));
        return this.views.get(arg1);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(this.views.get(arg1));
    }
}
