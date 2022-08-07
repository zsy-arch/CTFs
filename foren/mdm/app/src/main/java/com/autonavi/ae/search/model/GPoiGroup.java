package com.autonavi.ae.search.model;

/* loaded from: classes.dex */
public class GPoiGroup extends GPoiBase {
    private GPoiGroup(String str) {
        this.czName = str;
    }

    @Override // com.autonavi.ae.search.model.GPoiBase
    public String getName() {
        return this.czName;
    }
}
