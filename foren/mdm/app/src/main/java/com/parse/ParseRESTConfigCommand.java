package com.parse;

import com.parse.http.ParseHttpRequest;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
class ParseRESTConfigCommand extends ParseRESTCommand {
    public ParseRESTConfigCommand(String httpPath, ParseHttpRequest.Method httpMethod, Map<String, ?> parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    public static ParseRESTConfigCommand fetchConfigCommand(String sessionToken) {
        return new ParseRESTConfigCommand("config", ParseHttpRequest.Method.GET, null, sessionToken);
    }

    public static ParseRESTConfigCommand updateConfigCommand(Map<String, ?> configParameters, String sessionToken) {
        Map<String, Map<String, ?>> commandParameters = null;
        if (configParameters != null) {
            commandParameters = new HashMap<>();
            commandParameters.put("params", configParameters);
        }
        return new ParseRESTConfigCommand("config", ParseHttpRequest.Method.PUT, commandParameters, sessionToken);
    }
}
