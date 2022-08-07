package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMACallManager;
import com.superrtc.sdk.RtcConnection;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EMCallOptions {
    EMACallManager emaCallManager;
    boolean isUserSetAutoResizing = false;
    boolean isUserSetMaxFrameRate = false;
    boolean userSetAutoResizing = false;
    int userSetMaxFrameRate = -1;
    boolean isChangeVideoResolution = false;
    int changeVideoResolutionWidth = -1;
    int changeVideoResolutionHeight = -1;

    public EMCallOptions(EMACallManager eMACallManager) {
        this.emaCallManager = null;
        this.emaCallManager = eMACallManager;
    }

    public void enableFixedVideoResolution(boolean z) {
        this.isUserSetAutoResizing = true;
        this.userSetAutoResizing = z;
        RtcConnection rtcConnection = EMClient.getInstance().callManager().mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.enableFixedVideoResolution(z);
        }
    }

    public boolean getIsSendPushIfOffline() {
        return this.emaCallManager.getIsSendPushIfOffline();
    }

    public long getMaxVideoKbps() {
        return this.emaCallManager.getVideoKbps();
    }

    public long getVideoResolutionHeight() {
        return this.emaCallManager.getVideoResolutionHeight();
    }

    public long getVideoResolutionWidth() {
        return this.emaCallManager.getVideoResolutionWidth();
    }

    public void setAudioSampleRate(int i) {
        RtcConnection.setAudioSampleRate(i);
    }

    public void setIsSendPushIfOffline(boolean z) {
        this.emaCallManager.setIsSendPushIfOffline(z);
    }

    public void setMaxVideoFrameRate(int i) {
        this.isUserSetMaxFrameRate = true;
        this.userSetMaxFrameRate = i;
        RtcConnection rtcConnection = EMClient.getInstance().callManager().mRtcConnection;
        if (rtcConnection != null) {
            rtcConnection.setMaxVideoFrameRate(i);
        }
    }

    public void setMaxVideoKbps(long j) {
        RtcConnection rtcConnection = EMClient.getInstance().callManager().mRtcConnection;
        if (rtcConnection != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(RtcConnection.RtcKVMaxVideoKbpsLong, j);
                rtcConnection.setConfigure(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.emaCallManager.setVideoKbps(j);
    }

    public void setMinVideoKbps(int i) {
        RtcConnection.setMinVideoKbps(i);
    }

    public void setVideoResolution(int i, int i2) {
        RtcConnection rtcConnection = EMClient.getInstance().callManager().mRtcConnection;
        if (rtcConnection != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("width", i);
                jSONObject.put(RtcConnection.RtcvideoheigthLong, i2);
                rtcConnection.setConfigure(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.emaCallManager.setVideoResolution(i, i2);
    }
}
