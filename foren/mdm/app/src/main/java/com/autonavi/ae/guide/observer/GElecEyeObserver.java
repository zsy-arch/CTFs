package com.autonavi.ae.guide.observer;

import com.autonavi.ae.guide.model.NoNaviCongestionInfo;
import com.autonavi.ae.guide.model.NoNaviInfor;
import com.autonavi.ae.guide.model.TrafficFacilityInfo;

/* loaded from: classes.dex */
public interface GElecEyeObserver {
    void onTrafficFacilityUpdate(TrafficFacilityInfo[] trafficFacilityInfoArr);

    void onUpdateNoNaviCongestionInfo(NoNaviCongestionInfo noNaviCongestionInfo);

    void onUpdateNoNaviInfor(NoNaviInfor noNaviInfor);
}
