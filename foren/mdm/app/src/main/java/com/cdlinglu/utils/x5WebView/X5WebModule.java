package com.cdlinglu.utils.x5WebView;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class X5WebModule {
    private Context mContext;
    private X5WebModule mX5WebModule;

    private X5WebModule() {
    }

    public X5WebModule getInstance(Context context) {
        this.mContext = context;
        if (this.mX5WebModule == null) {
            this.mX5WebModule = new X5WebModule();
        }
        return this.mX5WebModule;
    }

    public static void simulateWebPage(WebPageSettings webPageSettings) {
    }

    /* loaded from: classes.dex */
    public class WebPageSettings {
        public static final String CACHE_CONTROL = "cache_control";
        private static final int CAPACITY = 10;
        public static final String CONTENT_LENGTH = "content-length";
        public static final String CONTENT_RANGE = "content-range";
        public static final String CONTENT_TYPE = "content-type";
        public static final String DATE = "date";
        public static final String LAST_MODEIFIED = "Last-modified";
        public static final String SERVER = "server";
        Map<String, String> settings;

        public WebPageSettings() {
        }

        public void putSettings(String key, String value) throws Exception {
            if (this.settings != null) {
                this.settings = new HashMap(10);
            }
            if (key.equals(CACHE_CONTROL) || key.equals("content-length") || key.equals(CONTENT_RANGE) || key.equals("content-type") || key.equals(LAST_MODEIFIED) || key.equals(SERVER) || key.equals("date")) {
                this.settings.put(key, value);
                return;
            }
            throw new Exception("illegal http header format");
        }
    }
}
