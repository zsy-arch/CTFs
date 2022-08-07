package com.hyphenate.chat;

import android.util.Pair;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.cloud.HttpClientConfig;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMExtraService {
    private static final String TAG = EMExtraService.class.getSimpleName();
    private static final EMExtraService me = new EMExtraService();

    EMExtraService() {
    }

    public static EMExtraService getInstance() {
        return me;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<EMContact> getRobotsFromServer() throws HyphenateException {
        ArrayList arrayList = new ArrayList();
        try {
            Pair<Integer, String> sendRequestWithToken = EMHttpClient.getInstance().sendRequestWithToken(HttpClientConfig.getBaseUrlByAppKey() + "/robots", null, EMHttpClient.GET);
            if (sendRequestWithToken != null && ((Integer) sendRequestWithToken.first).intValue() == 200) {
                JSONObject jSONObject = new JSONObject((String) sendRequestWithToken.second);
                if (jSONObject.has("entities")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("entities");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        EMContact eMContact = new EMContact();
                        eMContact.username = jSONObject2.getString("username").toLowerCase();
                        if (jSONObject2.has("name")) {
                            eMContact.nick = jSONObject2.getString("name");
                        }
                        if (jSONObject2.has("activated") ? jSONObject2.getBoolean("activated") : false) {
                            arrayList.add(eMContact);
                        }
                    }
                }
            } else if (sendRequestWithToken != null) {
                EMLog.e(TAG, "getRobotUsers resp statusCode:" + sendRequestWithToken.first);
            } else {
                EMLog.e(TAG, "getRobotUsers resp result is null");
            }
            return arrayList;
        } catch (HyphenateException e) {
            throw e;
        } catch (JSONException e2) {
            throw new HyphenateException(e2.getMessage());
        }
    }
}
