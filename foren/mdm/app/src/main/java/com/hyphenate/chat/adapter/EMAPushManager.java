package com.hyphenate.chat.adapter;

import java.util.List;

/* loaded from: classes2.dex */
public class EMAPushManager extends EMABase {
    public void disableOfflineNotification(int i, int i2, EMAError eMAError) {
        nativeDisableOfflineNotification(i, i2, eMAError);
    }

    public void enableOfflineNotification(EMAError eMAError) {
        nativeEnableOfflineNotification(eMAError);
    }

    public List<String> getNoPushGroups() {
        return nativeGetNoPushGroups();
    }

    public EMAPushConfigs getPushConfigs() {
        return nativeGetPushConfigs();
    }

    public EMAPushConfigs getPushConfigsFromServer(EMAError eMAError) {
        return nativeGetPushConfigsFromServer(eMAError);
    }

    native void nativeDisableOfflineNotification(int i, int i2, EMAError eMAError);

    native void nativeEnableOfflineNotification(EMAError eMAError);

    native List<String> nativeGetNoPushGroups();

    native EMAPushConfigs nativeGetPushConfigs();

    native EMAPushConfigs nativeGetPushConfigsFromServer(EMAError eMAError);

    native void nativeUpdatePushServiceForGroup(List<String> list, boolean z, EMAError eMAError);

    public void updatePushServiceForGroup(List<String> list, boolean z, EMAError eMAError) {
        nativeUpdatePushServiceForGroup(list, z, eMAError);
    }
}
