package com.hyphenate.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class EMPreferenceUtils {
    private static final String PREFERENCE_NAME = "hyphenate.sdk.pref";
    private static SharedPreferences.Editor mEditor;
    private static EMPreferenceUtils mPreferenceUtils;
    private static SharedPreferences mSharedPreferences;
    private long reservedLogoutTime = 0;
    private static String SHARED_KEY_DDVERSION = "shared_key_ddversion";
    private static String SHARED_KEY_DDXML = "shared_key_ddxml";
    private static String SHARED_KEY_DDTIME = "shared_key_ddtime";
    private static String VALID_BEFORE = "valid_before";
    private static String SCHEDULED_LOGOUT_TIME = "scheduled_logout_time";
    private static String SHARED_KEY_GCM_ID = "shared_key_gcm_id";

    /* loaded from: classes2.dex */
    public static class Token {
        long savedTime;
        String token;

        public Token() {
        }

        public Token(String str, long j) {
            this.token = str;
            this.savedTime = j;
        }

        public long getSavedTime() {
            return this.savedTime;
        }

        public String getToken() {
            if (this.savedTime <= 0) {
                this.token = null;
            }
            return this.token;
        }

        public Token setSavedTime(long j) {
            this.savedTime = j;
            return this;
        }

        public Token setToken(String str) {
            this.token = str;
            return this;
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private EMPreferenceUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        mEditor = mSharedPreferences.edit();
    }

    public static synchronized EMPreferenceUtils getInstance() {
        EMPreferenceUtils eMPreferenceUtils;
        synchronized (EMPreferenceUtils.class) {
            if (mPreferenceUtils == null) {
                mPreferenceUtils = new EMPreferenceUtils(EMClient.getInstance().getContext());
            }
            eMPreferenceUtils = mPreferenceUtils;
        }
        return eMPreferenceUtils;
    }

    public String getDNSConfig() {
        return mSharedPreferences.getString(SHARED_KEY_DDXML, "");
    }

    public long getDNSTime() {
        return mSharedPreferences.getLong(SHARED_KEY_DDTIME, -1L);
    }

    public long getDNSValidBefore() {
        return mSharedPreferences.getLong(VALID_BEFORE, -1L);
    }

    public String getDNSVersion() {
        return mSharedPreferences.getString(SHARED_KEY_DDVERSION, "");
    }

    public String getDebugAppkey() {
        return mSharedPreferences.getString("debugAppkey", null);
    }

    public String getDebugIMAddress() {
        return mSharedPreferences.getString("debugIM", null);
    }

    public String getDebugMode() {
        return mSharedPreferences.getString("debugMode", null);
    }

    public String getDebugRestAddress() {
        return mSharedPreferences.getString("debugRest", null);
    }

    public String getGCMToken() {
        return mSharedPreferences.getString(SHARED_KEY_GCM_ID, null);
    }

    public long getLogoutTime() {
        if (this.reservedLogoutTime != 0) {
            return this.reservedLogoutTime;
        }
        this.reservedLogoutTime = mSharedPreferences.getLong(SCHEDULED_LOGOUT_TIME, -1L);
        return this.reservedLogoutTime;
    }

    public boolean hasReservedLogoutTime() {
        if (this.reservedLogoutTime != 0) {
            return true;
        }
        return mSharedPreferences.contains(SCHEDULED_LOGOUT_TIME);
    }

    public void removeLogoutTime() {
        if (hasReservedLogoutTime()) {
            this.reservedLogoutTime = 0L;
            mEditor.remove(SCHEDULED_LOGOUT_TIME);
            mEditor.apply();
        }
    }

    public void setDNSConfig(String str) {
        mEditor.putString(SHARED_KEY_DDXML, str);
        mEditor.apply();
    }

    public void setDNSConfigTime(long j) {
        mEditor.putLong(SHARED_KEY_DDTIME, j);
        mEditor.apply();
    }

    public void setDNSConfigVersion(String str) {
        mEditor.putString(SHARED_KEY_DDVERSION, str);
        mEditor.apply();
    }

    public void setDNSValidBefore(long j) {
        mEditor.putLong(VALID_BEFORE, j);
        mEditor.apply();
    }

    public void setDebugAppkey(String str) {
        mEditor.putString("debugAppkey", str);
        mEditor.apply();
    }

    public void setDebugMode(boolean z) {
        mEditor.putString("debugMode", String.valueOf(z));
        mEditor.apply();
    }

    public void setDebugServer(String str, String str2) {
        if (str == null && str2 == null) {
            mEditor.remove("debugIM");
            mEditor.remove("debugRest");
        } else {
            mEditor.putString("debugIM", str);
            mEditor.putString("debugRest", str2);
        }
        mEditor.apply();
    }

    public void setGCMToken(String str) {
        mEditor.putString(SHARED_KEY_GCM_ID, str);
        mEditor.apply();
    }

    public void setLogoutTime(long j) {
        this.reservedLogoutTime = j;
        mEditor.putLong(SCHEDULED_LOGOUT_TIME, j);
        mEditor.apply();
    }
}
