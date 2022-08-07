package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.OSSRetryType;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.internal.ResponseParsers;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.client.methods.HttpHead;

/* loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    public OSSRequestTask(RequestMessage message, ResponseParser parser, ExecutionContext context, int maxRetry) {
        this.responseParser = parser;
        this.message = message;
        this.context = context;
        this.client = context.getClient();
        this.retryHandler = new OSSRetryHandler(maxRetry);
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        Exception exception;
        Exception exception2;
        OSSRequest ossRequest;
        Request request = null;
        Response response = null;
        Exception exception3 = null;
        Call call = null;
        try {
            OSSLog.logDebug("[call] - ");
            ossRequest = this.context.getRequest();
            OSSUtils.ensureRequestValid(ossRequest, this.message);
            OSSUtils.signRequest(this.message);
        } catch (Exception e) {
            OSSLog.logError("Encounter local execpiton: " + e.toString());
            if (OSSLog.isEnableLog()) {
                e.printStackTrace();
            }
            exception3 = new ClientException(e.getMessage(), e);
        }
        if (this.context.getCancellationHandler().isCancelled()) {
            throw new InterruptedIOException("This task is cancelled!");
        }
        Request.Builder requestBuilder = new Request.Builder().url(this.message.buildCanonicalURL());
        for (String key : this.message.getHeaders().keySet()) {
            requestBuilder = requestBuilder.addHeader(key, this.message.getHeaders().get(key));
        }
        String contentType = this.message.getHeaders().get("Content-Type");
        switch (this.message.getMethod()) {
            case POST:
            case PUT:
                OSSUtils.assertTrue(contentType != null, "Content type can't be null when upload!");
                InputStream inputStream = null;
                long length = 0;
                if (this.message.getUploadData() != null) {
                    inputStream = new ByteArrayInputStream(this.message.getUploadData());
                    length = this.message.getUploadData().length;
                } else if (this.message.getUploadFilePath() != null) {
                    File file = new File(this.message.getUploadFilePath());
                    inputStream = new FileInputStream(file);
                    length = file.length();
                } else if (this.message.getUploadInputStream() != null) {
                    inputStream = this.message.getUploadInputStream();
                    length = this.message.getReadStreamLength();
                }
                if (inputStream != null) {
                    requestBuilder = requestBuilder.method(this.message.getMethod().toString(), NetworkProgressHelper.addProgressRequestBody(inputStream, length, contentType, this.context));
                    break;
                } else {
                    requestBuilder = requestBuilder.method(this.message.getMethod().toString(), RequestBody.create((MediaType) null, new byte[0]));
                    break;
                }
            case GET:
                requestBuilder = requestBuilder.get();
                break;
            case HEAD:
                requestBuilder = requestBuilder.head();
                break;
            case DELETE:
                requestBuilder = requestBuilder.delete();
                break;
        }
        request = requestBuilder.build();
        if (ossRequest instanceof GetObjectRequest) {
            this.client = NetworkProgressHelper.addProgressResponseListener(this.client, this.context);
            OSSLog.logDebug("getObject");
        }
        call = this.client.newCall(request);
        this.context.getCancellationHandler().setCall(call);
        response = call.execute();
        Map<String, List<String>> headerMap = response.headers().toMultimap();
        StringBuilder printRsp = new StringBuilder();
        printRsp.append("response:---------------------\n");
        printRsp.append("response code: " + response.code() + " for url: " + request.url() + "\n");
        printRsp.append("response msg: " + response.message() + "\n");
        for (String key2 : headerMap.keySet()) {
            printRsp.append("responseHeader [" + key2 + "]: ").append(headerMap.get(key2).get(0) + "\n");
        }
        OSSLog.logDebug(printRsp.toString());
        if (response != null) {
            try {
                DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(response.header("Date")).getTime());
            } catch (Exception e2) {
            }
        }
        if (exception3 == null && (response.code() == 203 || response.code() >= 300)) {
            try {
                exception = ResponseParsers.parseResponseErrorXML(response, request.method().equals(HttpHead.METHOD_NAME));
            } catch (IOException e3) {
                exception = new ClientException(e3.getMessage(), e3);
            }
        } else if (exception3 == null) {
            try {
                T result = this.responseParser.parse(response);
                if (this.context.getCompletedCallback() == null) {
                    return result;
                }
                try {
                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), result);
                    return result;
                } catch (Exception e4) {
                    return result;
                }
            } catch (IOException e5) {
                exception = new ClientException(e5.getMessage(), e5);
            }
        } else {
            exception = exception3;
        }
        if ((call == null || !call.isCanceled()) && !this.context.getCancellationHandler().isCancelled()) {
            exception2 = exception;
        } else {
            exception2 = new ClientException("Task is cancelled!", exception.getCause(), true);
        }
        OSSRetryType retryType = this.retryHandler.shouldRetry(exception2, this.currentRetryCount);
        OSSLog.logError("[run] - retry, retry type: " + retryType);
        if (retryType == OSSRetryType.OSSRetryTypeShouldRetry) {
            this.currentRetryCount++;
            return call();
        } else if (retryType == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
            if (response != null) {
                this.message.getHeaders().put("Date", response.header("Date"));
            }
            this.currentRetryCount++;
            return call();
        } else {
            if (exception2 instanceof ClientException) {
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception2, null);
                }
            } else if (this.context.getCompletedCallback() != null) {
                this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception2);
            }
            throw exception2;
        }
    }
}
