package com.yolanda.nohttp;

import android.text.TextUtils;
import com.yolanda.nohttp.tools.HttpDateTime;
import com.yolanda.nohttp.tools.LinkedMultiValueMap;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.HttpCookie;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HttpHeaders extends LinkedMultiValueMap<String, String> implements Headers {
    @Override // com.yolanda.nohttp.Headers
    public void addAll(Headers headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                add((HttpHeaders) key, (List) headers.getValues(key));
            }
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public void setAll(Headers headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                set((HttpHeaders) key, (List) headers.getValues(key));
            }
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public void addCookie(URI uri, CookieHandler cookieHandler) {
        try {
            for (Map.Entry<String, List<String>> entry : cookieHandler.get(uri, new HashMap()).entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if ("Cookie".equalsIgnoreCase(key) || "Cookie2".equalsIgnoreCase(key)) {
                    add((HttpHeaders) key, TextUtils.join("; ", value));
                }
            }
        } catch (IOException e) {
            Logger.e(e);
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public void setJSONString(String jsonString) throws JSONException {
        this.mSource.clear();
        JSONObject jsonObject = new JSONObject(jsonString);
        Iterator<String> keySet = jsonObject.keys();
        while (keySet.hasNext()) {
            String key = keySet.next();
            JSONArray values = new JSONArray(jsonObject.optString(key));
            if (values != null) {
                for (int i = 0; i < values.length(); i++) {
                    add((HttpHeaders) key, values.optString(i));
                }
            }
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public final String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, List<String>> entry : this.mSource.entrySet()) {
            try {
                jsonObject.put(entry.getKey(), new JSONArray((Collection) entry.getValue()));
            } catch (JSONException e) {
                Logger.w(e);
            }
        }
        return jsonObject.toString();
    }

    @Override // com.yolanda.nohttp.Headers
    public Map<String, String> toRequestHeaders() {
        Map<String, String> singleMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : this.mSource.entrySet()) {
            singleMap.put(entry.getKey(), TextUtils.join("; ", entry.getValue()));
        }
        return singleMap;
    }

    @Override // com.yolanda.nohttp.Headers
    public Map<String, List<String>> toResponseHeaders() {
        return this.mSource;
    }

    @Override // com.yolanda.nohttp.Headers
    public List<HttpCookie> getCookies() {
        List<HttpCookie> cookies = new ArrayList<>();
        for (String key : keySet()) {
            if (key.equalsIgnoreCase("Set-Cookie") || key.equalsIgnoreCase("Set-Cookie2")) {
                for (String cookieStr : getValues(key)) {
                    for (HttpCookie cookie : HttpCookie.parse(cookieStr)) {
                        cookies.add(cookie);
                    }
                }
            }
        }
        return cookies;
    }

    @Override // com.yolanda.nohttp.Headers
    public String getCacheControl() {
        List<String> cacheControls = getValues("Cache-Control");
        if (cacheControls == null) {
            cacheControls = getValues(Headers.HEAD_KEY_PRAGMA);
        }
        if (cacheControls == null) {
            cacheControls = new ArrayList<>();
        }
        return TextUtils.join(",", cacheControls);
    }

    @Override // com.yolanda.nohttp.Headers
    public String getContentEncoding() {
        return getValue("Content-Encoding", 0);
    }

    @Override // com.yolanda.nohttp.Headers
    public int getContentLength() {
        try {
            return Integer.parseInt(getValue("Content-Length", 0));
        } catch (Exception e) {
            return 0;
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public int getResponseCode() {
        try {
            return Integer.parseInt(getValue(Headers.HEAD_KEY_RESPONSE_CODE, 0));
        } catch (Exception e) {
            return 0;
        }
    }

    @Override // com.yolanda.nohttp.Headers
    public String getResponseMessage() {
        return getValue(Headers.HEAD_KEY_RESPONSE_MESSAGE, 0);
    }

    @Override // com.yolanda.nohttp.Headers
    public String getContentType() {
        return getValue("Content-Type", 0);
    }

    @Override // com.yolanda.nohttp.Headers
    public long getDate() {
        return getDateField("Date");
    }

    @Override // com.yolanda.nohttp.Headers
    public String getETag() {
        return getValue("ETag", 0);
    }

    @Override // com.yolanda.nohttp.Headers
    public long getExpiration() {
        return getDateField("Expires");
    }

    @Override // com.yolanda.nohttp.Headers
    public long getLastModified() {
        return getDateField("Last-Modified");
    }

    @Override // com.yolanda.nohttp.Headers
    public String getLocation() {
        return getValue("Location", 0);
    }

    private long getDateField(String key) {
        String value = getValue(key, 0);
        if (value != null) {
            try {
                return HttpDateTime.parseGMTToMillis(value);
            } catch (ParseException e) {
                Logger.w(e);
            }
        }
        return 0L;
    }

    public String toString() {
        return toJSONString();
    }
}
