package com.parse;

import com.parse.http.ParseHttpRequest;
import java.util.Map;

/* loaded from: classes2.dex */
class ParseRESTCloudCommand extends ParseRESTCommand {
    private ParseRESTCloudCommand(String httpPath, ParseHttpRequest.Method httpMethod, Map<String, ?> parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    public static ParseRESTCloudCommand callFunctionCommand(String functionName, Map<String, ?> parameters, String sessionToken) {
        return new ParseRESTCloudCommand(String.format("functions/%s", functionName), ParseHttpRequest.Method.POST, parameters, sessionToken);
    }
}
