package com.hy.frame.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class AppShare {
    public static final String SHARE_CACHE = "cache";
    public static final String SHARE_DEFAULT = "default";
    private static AppShare instance;
    private SharedPreferences share;

    public static AppShare get(Context context) {
        if (instance == null) {
            instance = new AppShare(context.getApplicationContext(), false);
        }
        return instance;
    }

    public AppShare(Context context) {
        this(context, false);
    }

    public AppShare(Context context, boolean cache) {
        if (cache) {
            this.share = context.getApplicationContext().getSharedPreferences("cache", 0);
        } else {
            this.share = context.getApplicationContext().getSharedPreferences(SHARE_DEFAULT, 0);
        }
    }

    public SharedPreferences getShared() {
        return this.share;
    }

    public String getString(String key) {
        return this.share.getString(key, null);
    }

    public int getInt(String key) {
        return this.share.getInt(key, 0);
    }

    public long getLong(String key) {
        return this.share.getLong(key, 0L);
    }

    public float getFloat(String key) {
        return this.share.getFloat(key, 0.0f);
    }

    public boolean getBoolean(String key) {
        return this.share.getBoolean(key, false);
    }

    public boolean contains(String key) {
        return this.share.contains(key);
    }

    public void putString(String key, String value) {
        this.share.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value) {
        this.share.edit().putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        this.share.edit().putLong(key, value).apply();
    }

    public void putFloat(String key, float value) {
        this.share.edit().putFloat(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        this.share.edit().putBoolean(key, value).apply();
    }

    public void remove(String key) {
        this.share.edit().remove(key).apply();
    }

    public void clear() {
        this.share.edit().clear().apply();
    }
}
