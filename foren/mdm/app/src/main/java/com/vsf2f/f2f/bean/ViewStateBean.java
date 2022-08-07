package com.vsf2f.f2f.bean;

import android.view.View;

/* loaded from: classes2.dex */
public class ViewStateBean<T extends View> {
    private boolean check;
    private boolean enabled;
    private boolean select;
    private T view;

    public ViewStateBean(T view) {
        this.view = view;
    }

    public ViewStateBean(T view, boolean check, boolean select) {
        this.view = view;
        this.check = check;
        this.select = select;
    }

    public T getView() {
        return this.view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return "ViewStateBean{view='" + this.view + "', check='" + this.check + "', select='" + this.select + "'}";
    }
}
