package com.parse;

import com.parse.http.ParseHttpRequest;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseRESTSessionCommand extends ParseRESTCommand {
    public static ParseRESTSessionCommand getCurrentSessionCommand(String sessionToken) {
        return new ParseRESTSessionCommand("sessions/me", ParseHttpRequest.Method.GET, null, sessionToken);
    }

    public static ParseRESTSessionCommand revoke(String sessionToken) {
        return new ParseRESTSessionCommand("logout", ParseHttpRequest.Method.POST, new JSONObject(), sessionToken);
    }

    public static ParseRESTSessionCommand upgradeToRevocableSessionCommand(String sessionToken) {
        return new ParseRESTSessionCommand("upgradeToRevocableSession", ParseHttpRequest.Method.POST, new JSONObject(), sessionToken);
    }

    private ParseRESTSessionCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject jsonParameters, String sessionToken) {
        super(httpPath, httpMethod, jsonParameters, sessionToken);
    }
}
