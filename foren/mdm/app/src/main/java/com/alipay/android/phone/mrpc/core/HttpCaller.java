package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* loaded from: classes.dex */
public class HttpCaller extends AbstractRpcCaller {
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private Config mConfig;

    public HttpCaller(Config config, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.mConfig = config;
    }

    private void addHeader(HttpUrlRequest httpUrlRequest) {
        httpUrlRequest.addHeader(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> headers = this.mConfig.getRpcParams().getHeaders();
        if (!(headers == null || headers.isEmpty())) {
            for (Header header : headers) {
                httpUrlRequest.addHeader(header);
            }
        }
    }

    private Transport getTransport() {
        return this.mConfig.getTransport();
    }

    private int transferCode(int i) {
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 15;
            case 9:
                return 16;
            default:
                return i;
        }
    }

    @Override // com.alipay.android.phone.mrpc.core.RpcCaller
    public Object call() {
        HttpUrlRequest httpUrlRequest = new HttpUrlRequest(this.mConfig.getUrl());
        httpUrlRequest.setReqData(this.mReqData);
        httpUrlRequest.setContentType(this.mContentType);
        httpUrlRequest.setResetCookie(this.mResetCookie);
        httpUrlRequest.addTags("id", String.valueOf(this.mId));
        httpUrlRequest.addTags("operationType", this.mOperationType);
        httpUrlRequest.addTags("gzip", String.valueOf(this.mConfig.isGzip()));
        addHeader(httpUrlRequest);
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(httpUrlRequest.toString());
        try {
            Response response = getTransport().execute(httpUrlRequest).get();
            if (response != null) {
                return response.getResData();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (CancellationException e2) {
            throw new RpcException(13, "", e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e3);
            }
            HttpException httpException = (HttpException) cause;
            throw new RpcException(Integer.valueOf(transferCode(httpException.getCode())), httpException.getMsg());
        }
    }
}
