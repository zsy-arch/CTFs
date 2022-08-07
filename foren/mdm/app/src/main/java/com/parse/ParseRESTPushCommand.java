package com.parse;

import com.parse.ParseQuery;
import com.parse.http.ParseHttpRequest;
import java.util.Collection;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseRESTPushCommand extends ParseRESTCommand {
    static final String KEY_CHANNELS = "channels";
    static final String KEY_DATA = "data";
    static final String KEY_DEVICE_TYPE = "deviceType";
    static final String KEY_EXPIRATION_INTERVAL = "expiration_interval";
    static final String KEY_EXPIRATION_TIME = "expiration_time";
    static final String KEY_WHERE = "where";

    public ParseRESTPushCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    public static ParseRESTPushCommand sendPushCommand(ParseQuery.State<ParseInstallation> query, Set<String> targetChannels, String targetDeviceType, Long expirationTime, Long expirationInterval, JSONObject payload, String sessionToken) {
        JSONObject parameters = new JSONObject();
        try {
            if (targetChannels != null) {
                parameters.put(KEY_CHANNELS, new JSONArray((Collection) targetChannels));
            } else {
                JSONObject whereJSON = null;
                if (query != null) {
                    whereJSON = (JSONObject) PointerEncoder.get().encode(query.constraints());
                }
                if (targetDeviceType != null) {
                    whereJSON = new JSONObject();
                    whereJSON.put(KEY_DEVICE_TYPE, targetDeviceType);
                }
                if (whereJSON == null) {
                    whereJSON = new JSONObject();
                }
                parameters.put(KEY_WHERE, whereJSON);
            }
            if (expirationTime != null) {
                parameters.put(KEY_EXPIRATION_TIME, expirationTime);
            } else if (expirationInterval != null) {
                parameters.put(KEY_EXPIRATION_INTERVAL, expirationInterval);
            }
            if (payload != null) {
                parameters.put("data", payload);
            }
            return new ParseRESTPushCommand("push", ParseHttpRequest.Method.POST, parameters, sessionToken);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
