package com.parse;

import android.net.Uri;
import com.parse.ParseObject;
import com.parse.http.ParseHttpRequest;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseRESTObjectCommand extends ParseRESTCommand {
    public ParseRESTObjectCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    public static ParseRESTObjectCommand getObjectCommand(String objectId, String className, String sessionToken) {
        return new ParseRESTObjectCommand(String.format("classes/%s/%s", Uri.encode(className), Uri.encode(objectId)), ParseHttpRequest.Method.GET, null, sessionToken);
    }

    public static ParseRESTObjectCommand saveObjectCommand(ParseObject.State state, JSONObject operations, String sessionToken) {
        return state.objectId() == null ? createObjectCommand(state.className(), operations, sessionToken) : updateObjectCommand(state.objectId(), state.className(), operations, sessionToken);
    }

    private static ParseRESTObjectCommand createObjectCommand(String className, JSONObject changes, String sessionToken) {
        return new ParseRESTObjectCommand(String.format("classes/%s", Uri.encode(className)), ParseHttpRequest.Method.POST, changes, sessionToken);
    }

    private static ParseRESTObjectCommand updateObjectCommand(String objectId, String className, JSONObject changes, String sessionToken) {
        return new ParseRESTObjectCommand(String.format("classes/%s/%s", Uri.encode(className), Uri.encode(objectId)), ParseHttpRequest.Method.PUT, changes, sessionToken);
    }

    public static ParseRESTObjectCommand deleteObjectCommand(ParseObject.State state, String sessionToken) {
        String httpPath = String.format("classes/%s", Uri.encode(state.className()));
        String objectId = state.objectId();
        if (objectId != null) {
            httpPath = httpPath + String.format("/%s", Uri.encode(objectId));
        }
        return new ParseRESTObjectCommand(httpPath, ParseHttpRequest.Method.DELETE, null, sessionToken);
    }
}
