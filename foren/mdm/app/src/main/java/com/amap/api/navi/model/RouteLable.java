package com.amap.api.navi.model;

import com.autonavi.ae.route.model.LabelInfo;

/* loaded from: classes.dex */
public class RouteLable {
    private String label;

    public RouteLable(LabelInfo labelInfo) {
        this.label = labelInfo.mContent;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }
}
