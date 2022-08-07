package com.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* loaded from: classes2.dex */
public class HttpClient {
    private static final String[] CERTIFICATES = {"vjkejiBase64.cer"};
    private static HttpClient httpClient = null;
    private APIService api;
    private OkHttpClient client;

    public static synchronized HttpClient getInstance(String baseUrl) {
        HttpClient httpClient2;
        synchronized (HttpClient.class) {
            if (httpClient == null) {
                httpClient = new HttpClient(baseUrl);
            }
            httpClient2 = httpClient;
        }
        return httpClient2;
    }

    public static synchronized HttpClient getInstance() {
        HttpClient httpClient2;
        synchronized (HttpClient.class) {
            if (httpClient == null) {
                httpClient = new HttpClient();
            }
            httpClient2 = httpClient;
        }
        return httpClient2;
    }

    public HttpClient(String baseUrl) {
        this();
        getHost(baseUrl);
    }

    public HttpClient() {
        this.client = new OkHttpClient.Builder().readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS).followRedirects(true).followSslRedirects(true).build();
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public void get(String url, Map<String, String> map, Observer observer) {
        this.api.get(url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void post(String url, Map<String, String> map, Observer observer) {
        this.api.post(url, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public APIService getApi(String apiUrl) {
        if (this.api == null) {
            synchronized (APIService.class) {
                if (this.api == null) {
                    this.api = create(apiUrl);
                }
            }
        }
        this.client.newBuilder().addInterceptor(new Interceptor() { // from class: com.http.HttpClient.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                builder.method(request.method(), request.body());
                return chain.proceed(builder.build());
            }
        });
        return this.api;
    }

    public HttpClient getHost(String apiUrl) {
        if (this.api == null) {
            synchronized (APIService.class) {
                if (this.api == null) {
                    this.api = create(apiUrl);
                }
            }
        }
        return this;
    }

    private APIService create(String baseUrl) {
        return (APIService) new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(this.client).build().create(APIService.class);
    }

    private <T> T create(Class<T> tClass, String baseUrl) {
        return (T) new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(this.client).build().create(tClass);
    }
}
