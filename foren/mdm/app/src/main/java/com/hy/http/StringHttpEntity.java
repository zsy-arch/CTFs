package com.hy.http;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class StringHttpEntity extends HttpEntity {
    private static final char NAME_VALUE_CONNECT = '&';
    private static final String NAME_VALUE_SEPARATOR = "=";

    public StringHttpEntity(ConcurrentHashMap<String, String> params, String charsetName) {
        String charset = TextUtils.isEmpty(charsetName) ? DEFAULT_CHARSET : charsetName;
        String contentStr = format(params, charset);
        if (contentStr != null) {
            try {
                super.setContent(new ByteArrayInputStream(contentStr.getBytes(charset)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String format(ConcurrentHashMap<String, String> params, String charset) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String encodedName = encodeFormFields(entry.getKey(), charset);
            String encodedValue = encodeFormFields(entry.getValue(), charset);
            if (result.length() > 0) {
                result.append(NAME_VALUE_CONNECT);
            }
            result.append(encodedName);
            if (encodedValue != null) {
                result.append(NAME_VALUE_SEPARATOR);
                result.append(encodedValue);
            }
        }
        return result.toString();
    }

    @Override // com.hy.http.HttpEntity
    public String getContentType() {
        return createContentType("application/x-www-form-urlencoded", DEFAULT_CHARSET);
    }

    private String encodeFormFields(String content, String charset) {
        if (content == null) {
            return null;
        }
        try {
            return URLEncoder.encode(content, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
