package com.lidroid.xutils.http;

import android.os.SystemClock;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler;
import com.lidroid.xutils.http.callback.FileDownloadHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.callback.StringDownloadHandler;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.util.OtherUtils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class HttpHandler<T> extends PriorityAsyncTask<Object, Object, Void> implements RequestCallBackHandler {
    private static final int UPDATE_FAILURE = 3;
    private static final int UPDATE_LOADING = 2;
    private static final int UPDATE_START = 1;
    private static final int UPDATE_SUCCESS = 4;
    private static final NotUseApacheRedirectHandler notUseApacheRedirectHandler = new NotUseApacheRedirectHandler();
    private RequestCallBack<T> callback;
    private String charset;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private HttpRedirectHandler httpRedirectHandler;
    private long lastUpdateTime;
    private HttpRequestBase request;
    private String requestMethod;
    private String requestUrl;
    private boolean isUploading = true;
    private int retriedCount = 0;
    private String fileSavePath = null;
    private boolean isDownloadingFile = false;
    private boolean autoResume = false;
    private boolean autoRename = false;
    private State state = State.WAITING;
    private long expiry = HttpCache.getDefaultExpiryTime();

    public void setHttpRedirectHandler(HttpRedirectHandler httpRedirectHandler) {
        if (httpRedirectHandler != null) {
            this.httpRedirectHandler = httpRedirectHandler;
        }
    }

    public HttpHandler(AbstractHttpClient client, HttpContext context, String charset, RequestCallBack<T> callback) {
        this.client = client;
        this.context = context;
        this.callback = callback;
        this.charset = charset;
        this.client.setRedirectHandler(notUseApacheRedirectHandler);
    }

    public State getState() {
        return this.state;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public void setRequestCallBack(RequestCallBack<T> callback) {
        this.callback = callback;
    }

    public RequestCallBack<T> getRequestCallBack() {
        return this.callback;
    }

    private ResponseInfo<T> sendRequest(HttpRequestBase request) throws HttpException {
        IOException exception;
        boolean retry;
        String result;
        HttpRequestRetryHandler retryHandler = this.client.getHttpRequestRetryHandler();
        do {
            if (this.autoResume && this.isDownloadingFile) {
                File downloadFile = new File(this.fileSavePath);
                long fileLen = 0;
                if (downloadFile.isFile() && downloadFile.exists()) {
                    fileLen = downloadFile.length();
                }
                if (fileLen > 0) {
                    request.setHeader("RANGE", "bytes=" + fileLen + "-");
                }
            }
            try {
                this.requestMethod = request.getMethod();
                if (HttpUtils.sHttpCache.isEnabled(this.requestMethod) && (result = HttpUtils.sHttpCache.get(this.requestUrl)) != null) {
                    return new ResponseInfo<>(null, result, true);
                }
                if (!isCancelled()) {
                    return handleResponse(this.client.execute(request, this.context));
                }
                return null;
            } catch (HttpException e) {
                throw e;
            } catch (IOException e2) {
                exception = e2;
                int i = this.retriedCount + 1;
                this.retriedCount = i;
                retry = retryHandler.retryRequest(exception, i, this.context);
                continue;
            } catch (NullPointerException e3) {
                exception = new IOException(e3.getMessage());
                exception.initCause(e3);
                int i2 = this.retriedCount + 1;
                this.retriedCount = i2;
                retry = retryHandler.retryRequest(exception, i2, this.context);
                continue;
            } catch (UnknownHostException e4) {
                exception = e4;
                int i3 = this.retriedCount + 1;
                this.retriedCount = i3;
                retry = retryHandler.retryRequest(exception, i3, this.context);
                continue;
            } catch (Throwable e5) {
                exception = new IOException(e5.getMessage());
                exception.initCause(e5);
                int i4 = this.retriedCount + 1;
                this.retriedCount = i4;
                retry = retryHandler.retryRequest(exception, i4, this.context);
                continue;
            }
        } while (retry);
        throw new HttpException(exception);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lidroid.xutils.task.PriorityAsyncTask
    public Void doInBackground(Object... params) {
        if (!(this.state == State.CANCELLED || params == null || params.length == 0)) {
            if (params.length > 3) {
                this.fileSavePath = String.valueOf(params[1]);
                this.isDownloadingFile = this.fileSavePath != null;
                this.autoResume = ((Boolean) params[2]).booleanValue();
                this.autoRename = ((Boolean) params[3]).booleanValue();
            }
            try {
                if (this.state != State.CANCELLED) {
                    this.request = (HttpRequestBase) params[0];
                    this.requestUrl = this.request.getURI().toString();
                    if (this.callback != null) {
                        this.callback.setRequestUrl(this.requestUrl);
                    }
                    publishProgress(1);
                    this.lastUpdateTime = SystemClock.uptimeMillis();
                    ResponseInfo<T> responseInfo = sendRequest(this.request);
                    if (responseInfo != null) {
                        publishProgress(4, responseInfo);
                    }
                }
            } catch (HttpException e) {
                publishProgress(3, e, e.getMessage());
            }
        }
        return null;
    }

    @Override // com.lidroid.xutils.task.PriorityAsyncTask
    protected void onProgressUpdate(Object... values) {
        if (this.state != State.CANCELLED && values != null && values.length != 0 && this.callback != null) {
            switch (((Integer) values[0]).intValue()) {
                case 1:
                    this.state = State.STARTED;
                    this.callback.onStart();
                    return;
                case 2:
                    if (values.length == 3) {
                        this.state = State.LOADING;
                        this.callback.onLoading(Long.valueOf(String.valueOf(values[1])).longValue(), Long.valueOf(String.valueOf(values[2])).longValue(), this.isUploading);
                        return;
                    }
                    return;
                case 3:
                    if (values.length == 3) {
                        this.state = State.FAILURE;
                        this.callback.onFailure((HttpException) values[1], (String) values[2]);
                        return;
                    }
                    return;
                case 4:
                    if (values.length == 2) {
                        this.state = State.SUCCESS;
                        this.callback.onSuccess((ResponseInfo) values[1]);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Multiple debug info for r7v2 'result'  java.lang.Object: [D('result' java.lang.Object), D('result' java.lang.String)] */
    private ResponseInfo<T> handleResponse(HttpResponse response) throws HttpException, IOException {
        String responseFileName = null;
        if (response == null) {
            throw new HttpException("response is null");
        } else if (isCancelled()) {
            return null;
        } else {
            StatusLine status = response.getStatusLine();
            int statusCode = status.getStatusCode();
            if (statusCode < 300) {
                Object result = null;
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    this.isUploading = false;
                    if (this.isDownloadingFile) {
                        this.autoResume = this.autoResume && OtherUtils.isSupportRange(response);
                        if (this.autoRename) {
                            responseFileName = OtherUtils.getFileNameFromHttpResponse(response);
                        }
                        result = new FileDownloadHandler().handleEntity(entity, this, this.fileSavePath, this.autoResume, responseFileName);
                    } else {
                        result = new StringDownloadHandler().handleEntity(entity, this, this.charset);
                        if (HttpUtils.sHttpCache.isEnabled(this.requestMethod)) {
                            HttpUtils.sHttpCache.put(this.requestUrl, (String) result, this.expiry);
                        }
                    }
                }
                return new ResponseInfo<>(response, result, false);
            } else if (statusCode == 301 || statusCode == 302) {
                if (this.httpRedirectHandler == null) {
                    this.httpRedirectHandler = new DefaultHttpRedirectHandler();
                }
                HttpRequestBase request = this.httpRedirectHandler.getDirectRequest(response);
                if (request != null) {
                    return sendRequest(request);
                }
                return null;
            } else if (statusCode == 416) {
                throw new HttpException(statusCode, "maybe the file has downloaded completely");
            } else {
                throw new HttpException(statusCode, status.getReasonPhrase());
            }
        }
    }

    @Override // com.lidroid.xutils.task.PriorityAsyncTask, com.lidroid.xutils.task.TaskHandler
    public void cancel() {
        this.state = State.CANCELLED;
        if (this.request != null && !this.request.isAborted()) {
            try {
                this.request.abort();
            } catch (Throwable th) {
            }
        }
        if (!isCancelled()) {
            try {
                cancel(true);
            } catch (Throwable th2) {
            }
        }
        if (this.callback != null) {
            this.callback.onCancelled();
        }
    }

    @Override // com.lidroid.xutils.http.callback.RequestCallBackHandler
    public boolean updateProgress(long total, long current, boolean forceUpdateUI) {
        if (!(this.callback == null || this.state == State.CANCELLED)) {
            if (forceUpdateUI) {
                publishProgress(2, Long.valueOf(total), Long.valueOf(current));
            } else {
                long currTime = SystemClock.uptimeMillis();
                if (currTime - this.lastUpdateTime >= this.callback.getRate()) {
                    this.lastUpdateTime = currTime;
                    publishProgress(2, Long.valueOf(total), Long.valueOf(current));
                }
            }
        }
        return this.state != State.CANCELLED;
    }

    /* loaded from: classes2.dex */
    public enum State {
        WAITING(0),
        STARTED(1),
        LOADING(2),
        FAILURE(3),
        CANCELLED(4),
        SUCCESS(5);
        
        private int value;

        State(int value) {
            this.value = 0;
            this.value = value;
        }

        public static State valueOf(int value) {
            switch (value) {
                case 0:
                    return WAITING;
                case 1:
                    return STARTED;
                case 2:
                    return LOADING;
                case 3:
                    return FAILURE;
                case 4:
                    return CANCELLED;
                case 5:
                    return SUCCESS;
                default:
                    return FAILURE;
            }
        }

        public int value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    private static final class NotUseApacheRedirectHandler implements RedirectHandler {
        private NotUseApacheRedirectHandler() {
        }

        @Override // org.apache.http.client.RedirectHandler
        public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
            return false;
        }

        @Override // org.apache.http.client.RedirectHandler
        public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
            return null;
        }
    }
}
