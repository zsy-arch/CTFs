package com.parse;

import bolts.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class ParseAnonymousUtils {
    static final String AUTH_TYPE = "anonymous";

    public static boolean isLinked(ParseUser user) {
        return user.isLinked(AUTH_TYPE);
    }

    public static Task<ParseUser> logInInBackground() {
        return ParseUser.logInWithInBackground(AUTH_TYPE, getAuthData());
    }

    public static void logIn(LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logInInBackground(), callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getAuthData() {
        Map<String, String> authData = new HashMap<>();
        authData.put("id", UUID.randomUUID().toString());
        return authData;
    }

    private ParseAnonymousUtils() {
    }
}
