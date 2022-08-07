package com.hyphenate.chat.adapter;

import com.hyphenate.chat.EMCallManager;
import com.hyphenate.util.EMLog;
import com.superrtc.sdk.RtcConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EMACallRtcImpl implements EMACallRtcInterface {
    public static final String TAG = "EMACallRtcImpl";
    EMCallManager callManager;
    RtcConnection rtcConnection;

    EMACallRtcImpl() {
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void answer() {
        this.rtcConnection.answer();
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void createOffer() {
        this.rtcConnection.createOffer();
    }

    public RtcConnection getConnection() {
        return this.rtcConnection;
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public String getDefaultConfig(int i) {
        new JSONObject();
        return "{}";
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public String getRtcId() {
        return this.rtcConnection.getName();
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public String getRtcReport() {
        try {
            return this.rtcConnection.getReportString();
        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public String getSubConfig() {
        return "{}";
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void hangup() {
        EMLog.d(TAG, "hangup");
        this.rtcConnection.hangup();
        if (this.callManager != null) {
            this.callManager.clearRtcConnection();
        }
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void setConfigJson(String str) {
        EMLog.d(TAG, "setConfigJson:" + str);
        if (str == null || str.isEmpty()) {
        }
        this.rtcConnection.setConfigure(str);
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void setRemoteJson(String str) {
        try {
            EMLog.d(TAG, "setRemoteJson:" + str);
            this.rtcConnection.setRemoteJson(new String(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRtcConnection(EMCallManager eMCallManager, RtcConnection rtcConnection) {
        this.callManager = eMCallManager;
        this.rtcConnection = rtcConnection;
    }

    @Override // com.hyphenate.chat.adapter.EMACallRtcInterface
    public void setStatsEnable(boolean z) {
        this.rtcConnection.setStatsEnable(z);
    }
}
