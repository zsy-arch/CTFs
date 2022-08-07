package com.yolanda.nohttp;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes2.dex */
public class UserAgent {
    public static String getUserAgent() {
        String webUserAgent = null;
        try {
            webUserAgent = NoHttp.getContext().getString(((Integer) Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
        } catch (Exception e) {
        }
        if (TextUtils.isEmpty(webUserAgent)) {
            webUserAgent = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 %sSafari/533.1";
        }
        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        String version = Build.VERSION.RELEASE;
        if (version.length() > 0) {
            buffer.append(version);
        } else {
            buffer.append("1.0");
        }
        buffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language.toLowerCase(locale));
            String country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                buffer.append("-");
                buffer.append(country.toLowerCase(locale));
            }
        } else {
            buffer.append("en");
        }
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String model = Build.MODEL;
            if (model.length() > 0) {
                buffer.append("; ");
                buffer.append(model);
            }
        }
        String id = Build.ID;
        if (id.length() > 0) {
            buffer.append(" Build/");
            buffer.append(id);
        }
        return String.format(webUserAgent, buffer, "Mobile ");
    }
}
