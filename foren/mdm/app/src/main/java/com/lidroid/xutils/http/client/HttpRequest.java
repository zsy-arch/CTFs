package com.lidroid.xutils.http.client;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.client.entity.UploadEntity;
import com.lidroid.xutils.http.client.util.URIBuilder;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.protocol.HTTP;

/* loaded from: classes2.dex */
public class HttpRequest extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity entity;
    private HttpMethod method;
    private URIBuilder uriBuilder;
    private Charset uriCharset;

    public HttpRequest(HttpMethod method) {
        this.method = method;
    }

    public HttpRequest(HttpMethod method, String uri) {
        this.method = method;
        setURI(uri);
    }

    public HttpRequest(HttpMethod method, URI uri) {
        this.method = method;
        setURI(uri);
    }

    public HttpRequest addQueryStringParameter(String name, String value) {
        this.uriBuilder.addParameter(name, value);
        return this;
    }

    public HttpRequest addQueryStringParameter(NameValuePair nameValuePair) {
        this.uriBuilder.addParameter(nameValuePair.getName(), nameValuePair.getValue());
        return this;
    }

    public HttpRequest addQueryStringParams(List<NameValuePair> nameValuePairs) {
        if (nameValuePairs != null) {
            for (NameValuePair nameValuePair : nameValuePairs) {
                this.uriBuilder.addParameter(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        return this;
    }

    public void setRequestParams(RequestParams param) {
        if (param != null) {
            if (this.uriCharset == null) {
                this.uriCharset = Charset.forName(param.getCharset());
            }
            List<RequestParams.HeaderItem> headerItems = param.getHeaders();
            if (headerItems != null) {
                for (RequestParams.HeaderItem headerItem : headerItems) {
                    if (headerItem.overwrite) {
                        setHeader(headerItem.header);
                    } else {
                        addHeader(headerItem.header);
                    }
                }
            }
            addQueryStringParams(param.getQueryStringParams());
            setEntity(param.getEntity());
        }
    }

    public void setRequestParams(RequestParams param, RequestCallBackHandler callBackHandler) {
        if (param != null) {
            if (this.uriCharset == null) {
                this.uriCharset = Charset.forName(param.getCharset());
            }
            List<RequestParams.HeaderItem> headerItems = param.getHeaders();
            if (headerItems != null) {
                for (RequestParams.HeaderItem headerItem : headerItems) {
                    if (headerItem.overwrite) {
                        setHeader(headerItem.header);
                    } else {
                        addHeader(headerItem.header);
                    }
                }
            }
            addQueryStringParams(param.getQueryStringParams());
            HttpEntity entity = param.getEntity();
            if (entity != null) {
                if (entity instanceof UploadEntity) {
                    ((UploadEntity) entity).setCallBackHandler(callBackHandler);
                }
                setEntity(entity);
            }
        }
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public URI getURI() {
        try {
            if (this.uriCharset == null) {
                this.uriCharset = OtherUtils.getCharsetFromHttpRequest(this);
            }
            if (this.uriCharset == null) {
                this.uriCharset = Charset.forName("UTF-8");
            }
            return this.uriBuilder.build(this.uriCharset);
        } catch (URISyntaxException e) {
            LogUtils.e(e.getMessage(), e);
            return null;
        }
    }

    @Override // org.apache.http.client.methods.HttpRequestBase
    public void setURI(URI uri) {
        this.uriBuilder = new URIBuilder(uri);
    }

    public void setURI(String uri) {
        this.uriBuilder = new URIBuilder(uri);
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return this.method.toString();
    }

    @Override // org.apache.http.HttpEntityEnclosingRequest
    public HttpEntity getEntity() {
        return this.entity;
    }

    @Override // org.apache.http.HttpEntityEnclosingRequest
    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    @Override // org.apache.http.HttpEntityEnclosingRequest
    public boolean expectContinue() {
        Header expect = getFirstHeader(HTTP.EXPECT_DIRECTIVE);
        return expect != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue());
    }

    @Override // org.apache.http.client.methods.HttpRequestBase
    public Object clone() throws CloneNotSupportedException {
        HttpRequest clone = (HttpRequest) super.clone();
        if (this.entity != null) {
            clone.entity = (HttpEntity) CloneUtils.clone(this.entity);
        }
        return clone;
    }

    /* loaded from: classes2.dex */
    public enum HttpMethod {
        GET("GET"),
        POST("POST"),
        PUT(HttpPut.METHOD_NAME),
        HEAD(HttpHead.METHOD_NAME),
        MOVE("MOVE"),
        COPY("COPY"),
        DELETE(HttpDelete.METHOD_NAME),
        OPTIONS(HttpOptions.METHOD_NAME),
        TRACE(HttpTrace.METHOD_NAME),
        CONNECT("CONNECT");
        
        private final String value;

        HttpMethod(String value) {
            this.value = value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}
