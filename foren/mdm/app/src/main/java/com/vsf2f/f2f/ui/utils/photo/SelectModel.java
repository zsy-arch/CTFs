package com.vsf2f.f2f.ui.utils.photo;

/* loaded from: classes2.dex */
public enum SelectModel {
    SINGLE(0),
    MULTI(1);
    
    private int model;

    SelectModel(int model) {
        this.model = model;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.model);
    }
}
