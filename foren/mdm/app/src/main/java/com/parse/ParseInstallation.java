package com.parse;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import bolts.Continuation;
import bolts.Task;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.parse.ParseObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@ParseClassName("_Installation")
/* loaded from: classes.dex */
public class ParseInstallation extends ParseObject {
    private static final String KEY_APP_NAME = "appName";
    static final String KEY_CHANNELS = "channels";
    private static final String TAG = "com.parse.ParseInstallation";
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_INSTALLATION_ID = "installationId";
    private static final String KEY_DEVICE_TOKEN = "deviceToken";
    private static final String KEY_PUSH_TYPE = "pushType";
    private static final String KEY_TIME_ZONE = "timeZone";
    private static final String KEY_LOCALE = "localeIdentifier";
    private static final String KEY_APP_VERSION = "appVersion";
    private static final String KEY_PARSE_VERSION = "parseVersion";
    private static final String KEY_APP_IDENTIFIER = "appIdentifier";
    private static final List<String> READ_ONLY_FIELDS = Collections.unmodifiableList(Arrays.asList(KEY_DEVICE_TYPE, KEY_INSTALLATION_ID, KEY_DEVICE_TOKEN, KEY_PUSH_TYPE, KEY_TIME_ZONE, KEY_LOCALE, KEY_APP_VERSION, "appName", KEY_PARSE_VERSION, KEY_APP_IDENTIFIER));

    public static ParseCurrentInstallationController getCurrentInstallationController() {
        return ParseCorePlugins.getInstance().getCurrentInstallationController();
    }

    public static ParseInstallation getCurrentInstallation() {
        try {
            return (ParseInstallation) ParseTaskUtils.wait(getCurrentInstallationController().getAsync());
        } catch (ParseException e) {
            return null;
        }
    }

    public static ParseQuery<ParseInstallation> getQuery() {
        return ParseQuery.getQuery(ParseInstallation.class);
    }

    public String getInstallationId() {
        return getString(KEY_INSTALLATION_ID);
    }

    @Override // com.parse.ParseObject
    boolean needsDefaultACL() {
        return false;
    }

    @Override // com.parse.ParseObject
    boolean isKeyMutable(String key) {
        return !READ_ONLY_FIELDS.contains(key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public void updateBeforeSave() {
        super.updateBeforeSave();
        if (getCurrentInstallationController().isCurrent(this)) {
            updateTimezone();
            updateVersionInfo();
            updateDeviceInfo();
            updateLocaleIdentifier();
        }
    }

    @Override // com.parse.ParseObject
    public <T extends ParseObject> Task<T> fetchAsync(final String sessionToken, final Task<Void> toAwait) {
        Task<Void> result;
        Task<T> task;
        synchronized (this.mutex) {
            if (getObjectId() == null) {
                result = saveAsync(sessionToken, toAwait);
            } else {
                result = Task.forResult(null);
            }
            task = (Task<T>) result.onSuccessTask(new Continuation<Void, Task<T>>() { // from class: com.parse.ParseInstallation.1
                @Override // bolts.Continuation
                public Task<T> then(Task<Void> task2) throws Exception {
                    return ParseInstallation.super.fetchAsync(sessionToken, toAwait);
                }
            });
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public Task<Void> handleSaveResultAsync(ParseObject.State result, ParseOperationSet operationsBeforeSave) {
        Task<Void> task = super.handleSaveResultAsync(result, operationsBeforeSave);
        return result == null ? task : task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseInstallation.2
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task2) throws Exception {
                return ParseInstallation.getCurrentInstallationController().setAsync(ParseInstallation.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public Task<Void> handleFetchResultAsync(ParseObject.State newState) {
        return super.handleFetchResultAsync(newState).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseInstallation.3
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseInstallation.getCurrentInstallationController().setAsync(ParseInstallation.this);
            }
        });
    }

    private void updateTimezone() {
        String zone = TimeZone.getDefault().getID();
        if ((zone.indexOf(47) > 0 || zone.equals("GMT")) && !zone.equals(get(KEY_TIME_ZONE))) {
            performPut(KEY_TIME_ZONE, zone);
        }
    }

    private void updateVersionInfo() {
        synchronized (this.mutex) {
            try {
                Context context = Parse.getApplicationContext();
                String packageName = context.getPackageName();
                PackageManager pm = context.getPackageManager();
                String appVersion = pm.getPackageInfo(packageName, 0).versionName;
                String appName = pm.getApplicationLabel(pm.getApplicationInfo(packageName, 0)).toString();
                if (packageName != null && !packageName.equals(get(KEY_APP_IDENTIFIER))) {
                    performPut(KEY_APP_IDENTIFIER, packageName);
                }
                if (appName != null && !appName.equals(get("appName"))) {
                    performPut("appName", appName);
                }
                if (appVersion != null && !appVersion.equals(get(KEY_APP_VERSION))) {
                    performPut(KEY_APP_VERSION, appVersion);
                }
            } catch (PackageManager.NameNotFoundException e) {
                PLog.w(TAG, "Cannot load package info; will not be saved to installation");
            }
            if (!"1.13.0".equals(get(KEY_PARSE_VERSION))) {
                performPut(KEY_PARSE_VERSION, "1.13.0");
            }
        }
    }

    private void updateLocaleIdentifier() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(language)) {
            if (language.equals("iw")) {
                language = "he";
            }
            if (language.equals("in")) {
                language = "id";
            }
            if (language.equals("ji")) {
                language = "yi";
            }
            String localeString = language;
            if (!TextUtils.isEmpty(country)) {
                localeString = String.format(Locale.US, "%s-%s", language, country);
            }
            if (!localeString.equals(get(KEY_LOCALE))) {
                performPut(KEY_LOCALE, localeString);
            }
        }
    }

    void updateDeviceInfo() {
        updateDeviceInfo(ParsePlugins.get().installationId());
    }

    public void updateDeviceInfo(InstallationId installationId) {
        if (!has(KEY_INSTALLATION_ID)) {
            performPut(KEY_INSTALLATION_ID, installationId.get());
        }
        if (!f.a.equals(get(KEY_DEVICE_TYPE))) {
            performPut(KEY_DEVICE_TYPE, f.a);
        }
    }

    public PushType getPushType() {
        return PushType.fromString(super.getString(KEY_PUSH_TYPE));
    }

    public void setPushType(PushType pushType) {
        if (pushType != null) {
            performPut(KEY_PUSH_TYPE, pushType.toString());
        }
    }

    public void removePushType() {
        performRemove(KEY_PUSH_TYPE);
    }

    public String getDeviceToken() {
        return super.getString(KEY_DEVICE_TOKEN);
    }

    public void setDeviceToken(String deviceToken) {
        if (deviceToken != null && deviceToken.length() > 0) {
            performPut(KEY_DEVICE_TOKEN, deviceToken);
        }
    }

    public void removeDeviceToken() {
        performRemove(KEY_DEVICE_TOKEN);
    }
}
