package com.parse;

import android.support.v4.app.NotificationCompat;
import bolts.Task;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseRESTUserCommand extends ParseRESTCommand {
    private static final String HEADER_REVOCABLE_SESSION = "X-Parse-Revocable-Session";
    private static final String HEADER_TRUE = "1";
    private boolean isRevocableSessionEnabled;
    private int statusCode;

    public static ParseRESTUserCommand getCurrentUserCommand(String sessionToken) {
        return new ParseRESTUserCommand("users/me", ParseHttpRequest.Method.GET, null, sessionToken);
    }

    public static ParseRESTUserCommand signUpUserCommand(JSONObject parameters, String sessionToken, boolean revocableSession) {
        return new ParseRESTUserCommand("classes/_User", ParseHttpRequest.Method.POST, parameters, sessionToken, revocableSession);
    }

    public static ParseRESTUserCommand logInUserCommand(String username, String password, boolean revocableSession) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put(Constant.PASSWORD, password);
        return new ParseRESTUserCommand("login", ParseHttpRequest.Method.GET, parameters, (String) null, revocableSession);
    }

    public static ParseRESTUserCommand serviceLogInUserCommand(String authType, Map<String, String> authData, boolean revocableSession) {
        try {
            JSONObject authenticationData = new JSONObject();
            authenticationData.put(authType, PointerEncoder.get().encode(authData));
            JSONObject parameters = new JSONObject();
            parameters.put("authData", authenticationData);
            return serviceLogInUserCommand(parameters, (String) null, revocableSession);
        } catch (JSONException e) {
            throw new RuntimeException("could not serialize object to JSON");
        }
    }

    public static ParseRESTUserCommand serviceLogInUserCommand(JSONObject parameters, String sessionToken, boolean revocableSession) {
        return new ParseRESTUserCommand("users", ParseHttpRequest.Method.POST, parameters, sessionToken, revocableSession);
    }

    public static ParseRESTUserCommand resetPasswordResetCommand(String email) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(NotificationCompat.CATEGORY_EMAIL, email);
        return new ParseRESTUserCommand("requestPasswordReset", ParseHttpRequest.Method.POST, parameters, null);
    }

    private ParseRESTUserCommand(String httpPath, ParseHttpRequest.Method httpMethod, Map<String, ?> parameters, String sessionToken) {
        this(httpPath, httpMethod, parameters, sessionToken, false);
    }

    private ParseRESTUserCommand(String httpPath, ParseHttpRequest.Method httpMethod, Map<String, ?> parameters, String sessionToken, boolean isRevocableSessionEnabled) {
        super(httpPath, httpMethod, parameters, sessionToken);
        this.isRevocableSessionEnabled = isRevocableSessionEnabled;
    }

    private ParseRESTUserCommand(String httpPath, ParseHttpRequest.Method httpMethod, JSONObject parameters, String sessionToken, boolean isRevocableSessionEnabled) {
        super(httpPath, httpMethod, parameters, sessionToken);
        this.isRevocableSessionEnabled = isRevocableSessionEnabled;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.parse.ParseRESTCommand
    public void addAdditionalHeaders(ParseHttpRequest.Builder requestBuilder) {
        super.addAdditionalHeaders(requestBuilder);
        if (this.isRevocableSessionEnabled) {
            requestBuilder.addHeader(HEADER_REVOCABLE_SESSION, "1");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.parse.ParseRESTCommand, com.parse.ParseRequest
    public Task<JSONObject> onResponseAsync(ParseHttpResponse response, ProgressCallback progressCallback) {
        this.statusCode = response.getStatusCode();
        return super.onResponseAsync(response, progressCallback);
    }
}
