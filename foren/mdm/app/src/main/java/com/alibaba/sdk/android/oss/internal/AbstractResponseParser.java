package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Response;

/* loaded from: classes.dex */
public abstract class AbstractResponseParser<T extends OSSResult> implements ResponseParser {
    abstract T parseData(Response response, T t) throws Exception;

    public boolean needCloseResponse() {
        return true;
    }

    @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
    public T parse(Response response) throws IOException {
        try {
            try {
                T result = (T) ((OSSResult) ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance());
                if (result != null) {
                    result.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    result.setStatusCode(response.code());
                    result.setResponseHeader(parseResponseHeader(response));
                    result = parseData(response, result);
                }
                return result;
            } catch (Exception e) {
                IOException ioException = new IOException(e.getMessage(), e);
                e.printStackTrace();
                OSSLog.logThrowable2Local(e);
                throw ioException;
            }
        } finally {
            if (needCloseResponse()) {
                safeCloseResponse(response);
            }
        }
    }

    public static void safeCloseResponse(Response response) {
        try {
            response.body().close();
        } catch (Exception e) {
        }
    }

    private Map<String, String> parseResponseHeader(Response response) {
        Map<String, String> result = new HashMap<>();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            result.put(headers.name(i), headers.value(i));
        }
        return result;
    }
}
