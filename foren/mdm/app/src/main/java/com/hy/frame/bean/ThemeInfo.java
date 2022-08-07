package com.hy.frame.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public class ThemeInfo {
    private Drawable drawTitleBar;
    private int icoBack;
    private int themeBackground;
    private boolean titleBold;
    private int titleColor;
    private int titleSize;

    public int getIcoBack() {
        return this.icoBack;
    }

    public void setIcoBack(int icoBack) {
        this.icoBack = icoBack;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public boolean isTitleBold() {
        return this.titleBold;
    }

    public void setTitleBold(boolean titleBold) {
        this.titleBold = titleBold;
    }

    public Drawable getDrawTitleBar() {
        return this.drawTitleBar;
    }

    public void setDrawTitleBar(Drawable drawTitleBar) {
        this.drawTitleBar = drawTitleBar;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getThemeBackground() {
        return this.themeBackground;
    }

    public void setThemeBackground(int themeBackground) {
        this.themeBackground = themeBackground;
    }
}
