package com.hy.frame.view.swipe;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public class SwipeMenuItem {
    private Drawable background;
    private Drawable icon;
    private int id;
    private Context mContext;
    private String title;
    private int titleColor;
    private int titleSize;
    private int width;

    public SwipeMenuItem(Context context) {
        this.mContext = context;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(int resId) {
        setTitle(this.mContext.getString(resId));
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setIcon(int resId) {
        this.icon = this.mContext.getResources().getDrawable(resId);
    }

    public Drawable getBackground() {
        return this.background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public void setBackground(int resId) {
        this.background = this.mContext.getResources().getDrawable(resId);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
