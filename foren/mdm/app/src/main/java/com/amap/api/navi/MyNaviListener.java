package com.amap.api.navi;

import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AmapCarLocation;

/* loaded from: classes.dex */
public interface MyNaviListener extends AMapNaviListener {
    void carProjectionChange(AmapCarLocation amapCarLocation);

    void hideModeCross();

    void showModeCross(AMapModelCross aMapModelCross);
}
