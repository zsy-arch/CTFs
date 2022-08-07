package com.parse;

import android.content.Intent;
import bolts.Capture;
import bolts.Continuation;
import bolts.Task;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ParseAnalytics {
    private static final String TAG = "com.parse.ParseAnalytics";
    private static final Map<String, Boolean> lruSeenPushes = new LinkedHashMap<String, Boolean>() { // from class: com.parse.ParseAnalytics.3
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
            return size() > 10;
        }
    };

    static ParseAnalyticsController getAnalyticsController() {
        return ParseCorePlugins.getInstance().getAnalyticsController();
    }

    public static Task<Void> trackAppOpenedInBackground(Intent intent) {
        String pushHashStr = getPushHashFromIntent(intent);
        final Capture<String> pushHash = new Capture<>();
        if (pushHashStr != null && pushHashStr.length() > 0) {
            synchronized (lruSeenPushes) {
                if (lruSeenPushes.containsKey(pushHashStr)) {
                    return Task.forResult(null);
                }
                lruSeenPushes.put(pushHashStr, true);
                pushHash.set(pushHashStr);
            }
        }
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseAnalytics.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseAnalytics.getAnalyticsController().trackAppOpenedInBackground((String) Capture.this.get(), task.getResult());
            }
        });
    }

    @Deprecated
    public static void trackAppOpened(Intent intent) {
        trackAppOpenedInBackground(intent);
    }

    public static void trackAppOpenedInBackground(Intent intent, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(trackAppOpenedInBackground(intent), callback);
    }

    @Deprecated
    public static void trackEvent(String name) {
        trackEventInBackground(name);
    }

    public static void trackEventInBackground(String name, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(trackEventInBackground(name), callback);
    }

    @Deprecated
    public static void trackEvent(String name, Map<String, String> dimensions) {
        trackEventInBackground(name, dimensions);
    }

    public static void trackEventInBackground(String name, Map<String, String> dimensions, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(trackEventInBackground(name, dimensions), callback);
    }

    public static Task<Void> trackEventInBackground(String name) {
        return trackEventInBackground(name, (Map<String, String>) null);
    }

    public static Task<Void> trackEventInBackground(final String name, Map<String, String> dimensions) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("A name for the custom event must be provided.");
        }
        final Map<String, String> dimensionsCopy = dimensions != null ? Collections.unmodifiableMap(new HashMap(dimensions)) : null;
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseAnalytics.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseAnalytics.getAnalyticsController().trackEventInBackground(name, dimensionsCopy, task.getResult());
            }
        });
    }

    static void clear() {
        synchronized (lruSeenPushes) {
            lruSeenPushes.clear();
        }
    }

    static String getPushHashFromIntent(Intent intent) {
        String pushData = null;
        if (!(intent == null || intent.getExtras() == null)) {
            pushData = intent.getExtras().getString(ParsePushBroadcastReceiver.KEY_PUSH_DATA);
        }
        if (pushData == null) {
            return null;
        }
        try {
            return new JSONObject(pushData).optString("push_hash");
        } catch (JSONException e) {
            PLog.e(TAG, "Failed to parse push data: " + e.getMessage());
            return null;
        }
    }
}
