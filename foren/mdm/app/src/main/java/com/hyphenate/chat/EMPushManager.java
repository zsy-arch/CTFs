package com.hyphenate.chat;

import android.text.TextUtils;
import com.easeui.EaseConstant;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.chat.adapter.EMAPushConfigs;
import com.hyphenate.chat.adapter.EMAPushManager;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
public class EMPushManager {
    private static final String TAG = EMPushManager.class.getSimpleName();
    EMAPushManager emaObject;
    EMClient mClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMPushManager(EMClient eMClient, EMAPushManager eMAPushManager) {
        this.emaObject = eMAPushManager;
        this.mClient = eMClient;
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            throw new HyphenateException(eMAError);
        }
    }

    public void disableOfflinePush(int i, int i2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.disableOfflineNotification(i, i2, eMAError);
        handleError(eMAError);
    }

    public void enableOfflinePush() throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.enableOfflineNotification(eMAError);
        handleError(eMAError);
    }

    public List<String> getNoPushGroups() {
        return this.emaObject.getNoPushGroups();
    }

    public EMPushConfigs getPushConfigs() {
        EMAPushConfigs pushConfigs = this.emaObject.getPushConfigs();
        if (pushConfigs == null) {
            return null;
        }
        return new EMPushConfigs(pushConfigs);
    }

    public EMPushConfigs getPushConfigsFromServer() throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMAPushConfigs pushConfigsFromServer = this.emaObject.getPushConfigsFromServer(eMAError);
        handleError(eMAError);
        return new EMPushConfigs(pushConfigsFromServer);
    }

    public boolean updatePushNickname(String str) {
        if (TextUtils.isEmpty(str)) {
            EMLog.e(TAG, "nick name is null or empty");
            return false;
        }
        String currentUser = EMClient.getInstance().getCurrentUser();
        if (TextUtils.isEmpty(currentUser)) {
            EMLog.e(TAG, "currentUser is null or empty");
            return false;
        }
        String accessToken = EMClient.getInstance().getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            EMLog.e(TAG, "token is null or empty");
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", "Bearer " + accessToken);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(EaseConstant.EXTRA_NICK_NAME, str);
            String str2 = (String) EMHttpClient.getInstance().sendRequest(EMClient.getInstance().getChatConfigPrivate().f() + "/users/" + currentUser, hashMap, jSONObject.toString(), EMHttpClient.PUT).second;
            if (!str2.contains(av.aG)) {
                return true;
            }
            EMLog.e(TAG, "response error : " + str2);
            return false;
        } catch (Exception e) {
            EMLog.e(TAG, "error:" + e.getMessage());
            return false;
        }
    }

    public void updatePushServiceForGroup(List<String> list, boolean z) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.updatePushServiceForGroup(list, z, eMAError);
        handleError(eMAError);
    }
}
