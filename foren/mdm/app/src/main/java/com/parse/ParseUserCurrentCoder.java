package com.parse;

import com.parse.ParseObject;
import com.parse.ParseUser;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseUserCurrentCoder extends ParseObjectCurrentCoder {
    private static final ParseUserCurrentCoder INSTANCE = new ParseUserCurrentCoder();
    private static final String KEY_AUTH_DATA = "auth_data";
    private static final String KEY_SESSION_TOKEN = "session_token";

    public static ParseUserCurrentCoder get() {
        return INSTANCE;
    }

    ParseUserCurrentCoder() {
    }

    @Override // com.parse.ParseObjectCurrentCoder, com.parse.ParseObjectCoder
    public <T extends ParseObject.State> JSONObject encode(T state, ParseOperationSet operations, ParseEncoder encoder) {
        JSONObject objectJSON = super.encode(state, operations, encoder);
        String sessionToken = ((ParseUser.State) state).sessionToken();
        if (sessionToken != null) {
            try {
                objectJSON.put(KEY_SESSION_TOKEN, sessionToken);
            } catch (JSONException e) {
                throw new RuntimeException("could not encode value for key: session_token");
            }
        }
        Map<String, Map<String, String>> authData = ((ParseUser.State) state).authData();
        if (authData.size() > 0) {
            try {
                objectJSON.put(KEY_AUTH_DATA, encoder.encode(authData));
            } catch (JSONException e2) {
                throw new RuntimeException("could not attach key: auth_data");
            }
        }
        return objectJSON;
    }

    @Override // com.parse.ParseObjectCurrentCoder, com.parse.ParseObjectCoder
    public <T extends ParseObject.State.Init<?>> T decode(T builder, JSONObject json, ParseDecoder decoder) {
        ParseUser.State.Builder userBuilder = (ParseUser.State.Builder) builder;
        String newSessionToken = json.optString(KEY_SESSION_TOKEN, null);
        if (newSessionToken != null) {
            userBuilder.sessionToken(newSessionToken);
            json.remove(KEY_SESSION_TOKEN);
        }
        JSONObject newAuthData = json.optJSONObject(KEY_AUTH_DATA);
        if (newAuthData != null) {
            try {
                Iterator i = newAuthData.keys();
                while (i.hasNext()) {
                    String key = i.next();
                    if (!newAuthData.isNull(key)) {
                        userBuilder.putAuthData(key, (Map) ParseDecoder.get().decode(newAuthData.getJSONObject(key)));
                    }
                }
                json.remove(KEY_AUTH_DATA);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return (T) super.decode(builder, json, decoder);
    }
}
