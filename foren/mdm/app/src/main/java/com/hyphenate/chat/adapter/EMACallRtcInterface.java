package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public interface EMACallRtcInterface {

    /* loaded from: classes2.dex */
    public enum calltype {
    }

    void answer();

    void createOffer();

    String getDefaultConfig(int i);

    String getRtcId();

    String getRtcReport();

    String getSubConfig();

    void hangup();

    void setConfigJson(String str);

    void setRemoteJson(String str);

    void setStatsEnable(boolean z);
}
