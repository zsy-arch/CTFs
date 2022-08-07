package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ParseClassName("_Session")
/* loaded from: classes.dex */
public class ParseSession extends ParseObject {
    private static final String KEY_USER = "user";
    private static final String KEY_SESSION_TOKEN = "sessionToken";
    private static final String KEY_CREATED_WITH = "createdWith";
    private static final String KEY_RESTRICTED = "restricted";
    private static final String KEY_EXPIRES_AT = "expiresAt";
    private static final String KEY_INSTALLATION_ID = "installationId";
    private static final List<String> READ_ONLY_KEYS = Collections.unmodifiableList(Arrays.asList(KEY_SESSION_TOKEN, KEY_CREATED_WITH, KEY_RESTRICTED, "user", KEY_EXPIRES_AT, KEY_INSTALLATION_ID));

    /* JADX INFO: Access modifiers changed from: private */
    public static ParseSessionController getSessionController() {
        return ParseCorePlugins.getInstance().getSessionController();
    }

    public static Task<ParseSession> getCurrentSessionInBackground() {
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<ParseSession>>() { // from class: com.parse.ParseSession.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<ParseSession> then(Task<String> task) throws Exception {
                String sessionToken = task.getResult();
                if (sessionToken == null) {
                    return Task.forResult(null);
                }
                return ParseSession.getSessionController().getSessionAsync(sessionToken).onSuccess(new Continuation<ParseObject.State, ParseSession>() { // from class: com.parse.ParseSession.1.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public ParseSession then(Task<ParseObject.State> task2) throws Exception {
                        return (ParseSession) ParseObject.from(task2.getResult());
                    }
                });
            }
        });
    }

    public static void getCurrentSessionInBackground(GetCallback<ParseSession> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getCurrentSessionInBackground(), callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Task<Void> revokeAsync(String sessionToken) {
        return (sessionToken == null || !isRevocableSessionToken(sessionToken)) ? Task.forResult(null) : getSessionController().revokeAsync(sessionToken);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Task<String> upgradeToRevocableSessionAsync(String sessionToken) {
        return (sessionToken == null || isRevocableSessionToken(sessionToken)) ? Task.forResult(sessionToken) : getSessionController().upgradeToRevocable(sessionToken).onSuccess(new Continuation<ParseObject.State, String>() { // from class: com.parse.ParseSession.2
            @Override // bolts.Continuation
            public String then(Task<ParseObject.State> task) throws Exception {
                return ((ParseSession) ParseObject.from(task.getResult())).getSessionToken();
            }
        });
    }

    static boolean isRevocableSessionToken(String sessionToken) {
        return sessionToken.contains("r:");
    }

    public static ParseQuery<ParseSession> getQuery() {
        return ParseQuery.getQuery(ParseSession.class);
    }

    @Override // com.parse.ParseObject
    boolean needsDefaultACL() {
        return false;
    }

    @Override // com.parse.ParseObject
    boolean isKeyMutable(String key) {
        return !READ_ONLY_KEYS.contains(key);
    }

    public String getSessionToken() {
        return getString(KEY_SESSION_TOKEN);
    }
}
