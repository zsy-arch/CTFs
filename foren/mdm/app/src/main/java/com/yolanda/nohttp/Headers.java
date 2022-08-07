package com.yolanda.nohttp;

import com.yolanda.nohttp.tools.MultiValueMap;
import java.net.CookieHandler;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes2.dex */
public interface Headers extends MultiValueMap<String, String> {
    public static final String HEAD_KEY_ACCEPT = "Accept";
    public static final String HEAD_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEAD_KEY_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEAD_KEY_CACHE_CONTROL = "Cache-Control";
    public static final String HEAD_KEY_CONNECTION = "Connection";
    public static final String HEAD_KEY_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEAD_KEY_CONTENT_LENGTH = "Content-Length";
    public static final String HEAD_KEY_CONTENT_RANGE = "Content-Range";
    public static final String HEAD_KEY_CONTENT_TYPE = "Content-Type";
    public static final String HEAD_KEY_COOKIE = "Cookie";
    public static final String HEAD_KEY_COOKIE2 = "Cookie2";
    public static final String HEAD_KEY_DATE = "Date";
    public static final String HEAD_KEY_EXPIRES = "Expires";
    public static final String HEAD_KEY_E_TAG = "ETag";
    public static final String HEAD_KEY_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEAD_KEY_IF_NONE_MATCH = "If-None-Match";
    public static final String HEAD_KEY_LAST_MODIFIED = "Last-Modified";
    public static final String HEAD_KEY_LOCATION = "Location";
    public static final String HEAD_KEY_PRAGMA = "Pragma";
    public static final String HEAD_KEY_RESPONSE_CODE = "ResponseCode";
    public static final String HEAD_KEY_RESPONSE_MESSAGE = "ResponseMessage";
    public static final String HEAD_KEY_SET_COOKIE = "Set-Cookie";
    public static final String HEAD_KEY_SET_COOKIE2 = "Set-Cookie2";
    public static final String HEAD_KEY_USER_AGENT = "User-Agent";
    public static final String HEAD_VALUE_ACCEPT_ENCODING = "gzip, deflate";
    public static final String HEAD_VALUE_CONNECTION_CLOSE = "close";
    public static final String HEAD_VALUE_CONNECTION_KEEP_ALIVE = "keep-alive";

    void addAll(Headers headers);

    void addCookie(URI uri, CookieHandler cookieHandler);

    String getCacheControl();

    String getContentEncoding();

    int getContentLength();

    String getContentType();

    List<HttpCookie> getCookies();

    long getDate();

    String getETag();

    long getExpiration();

    long getLastModified();

    String getLocation();

    int getResponseCode();

    String getResponseMessage();

    void setAll(Headers headers);

    void setJSONString(String str) throws JSONException;

    String toJSONString();

    Map<String, String> toRequestHeaders();

    Map<String, List<String>> toResponseHeaders();
}
