package com.yolanda.nohttp;

import android.app.Application;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.yolanda.nohttp.cache.Cache;
import com.yolanda.nohttp.cache.CacheEntity;
import com.yolanda.nohttp.cache.DiskCacheStore;
import com.yolanda.nohttp.cookie.DiskCookieStore;
import com.yolanda.nohttp.download.DownloadConnection;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.download.Downloader;
import com.yolanda.nohttp.download.RestDownloadRequest;
import com.yolanda.nohttp.tools.PRNGFixes;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NoHttp {
    public static final String CHARSET_UTF8 = "utf-8";
    public static final int DEFAULT_THREAD_SIZE = 3;
    public static final String MIME_TYPE_FILE = "application/octet-stream";
    public static final int TIMEOUT_20S = 20000;
    public static final int TIMEOUT_8S = 10000;
    private static Application sApplication;
    private static CookieHandler sCookieHandler;

    public static void init(Application application) {
        if (sApplication == null) {
            sApplication = application;
            PRNGFixes.apply();
            sCookieHandler = new CookieManager(DiskCookieStore.INSTANCE, CookiePolicy.ACCEPT_ALL);
        }
    }

    public static Application getContext() {
        if (sApplication != null) {
            return sApplication;
        }
        throw new ExceptionInInitializerError("Please invoke NoHttp.init(Application) on Application#onCreate()");
    }

    public static RequestQueue newRequestQueue() {
        return newRequestQueue(3);
    }

    public static RequestQueue newRequestQueue(int threadPoolSize) {
        return newRequestQueue(DiskCacheStore.INSTANCE, HttpRestConnection.getInstance(), threadPoolSize);
    }

    public static RequestQueue newRequestQueue(Cache<CacheEntity> cache, ImplRestConnection implRestConnection, int threadPoolSize) {
        return newRequestQueue(HttpRestExecutor.getInstance(cache, implRestConnection), threadPoolSize);
    }

    public static RequestQueue newRequestQueue(ImplRestExecutor implRestExecutor, int threadPoolSize) {
        return newRequestQueue(HttpRestParser.getInstance(implRestExecutor), threadPoolSize);
    }

    public static RequestQueue newRequestQueue(ImplRestParser implRestParser, int threadPoolSize) {
        RequestQueue requestQueue = new RequestQueue(implRestParser, threadPoolSize);
        requestQueue.start();
        return requestQueue;
    }

    public static Request<String> createStringRequest(String url) {
        return new StringRequest(url);
    }

    public static Request<String> createStringRequest(String url, RequestMethod requestMethod) {
        return new StringRequest(url, requestMethod);
    }

    public static Request<JSONObject> createJsonObjectRequest(String url) {
        return new JsonObjectRequest(url);
    }

    public static Request<JSONObject> createJsonObjectRequest(String url, RequestMethod requestMethod) {
        return new JsonObjectRequest(url, requestMethod);
    }

    public static Request<JSONArray> createJsonArrayRequest(String url) {
        return new JsonArrayRequest(url);
    }

    public static Request<JSONArray> createJsonArrayRequest(String url, RequestMethod requestMethod) {
        return new JsonArrayRequest(url, requestMethod);
    }

    public static Request<Bitmap> createImageRequest(String url) {
        return createImageRequest(url, RequestMethod.GET);
    }

    public static Request<Bitmap> createImageRequest(String url, RequestMethod requestMethod) {
        return createImageRequest(url, requestMethod, 1000, 1000, Bitmap.Config.ARGB_8888, ImageView.ScaleType.CENTER_INSIDE);
    }

    public static Request<Bitmap> createImageRequest(String url, RequestMethod requestMethod, int maxWidth, int maxHeight, Bitmap.Config config, ImageView.ScaleType scaleType) {
        return new ImageRequest(url, requestMethod, maxWidth, maxHeight, config, scaleType);
    }

    public static <T> Response<T> startRequestSync(Cache<CacheEntity> cache, ImplRestConnection implRestConnection, Request<T> request) {
        if (cache == null || implRestConnection == null || request == null) {
            return null;
        }
        return HttpRestParser.getInstance(HttpRestExecutor.getInstance(DiskCacheStore.INSTANCE, HttpRestConnection.getInstance())).parserRequest(request);
    }

    public static <T> Response<T> startRequestSync(Cache<CacheEntity> cache, Request<T> request) {
        return startRequestSync(cache, HttpRestConnection.getInstance(), request);
    }

    public static <T> Response<T> startRequestSync(ImplRestConnection implRestConnection, Request<T> request) {
        return startRequestSync(DiskCacheStore.INSTANCE, implRestConnection, request);
    }

    public static <T> Response<T> startRequestSync(Request<T> request) {
        return startRequestSync(HttpRestConnection.getInstance(), request);
    }

    public static DownloadQueue newDownloadQueue() {
        return newDownloadQueue(3);
    }

    public static DownloadQueue newDownloadQueue(int threadPoolSize) {
        return newDownloadQueue(new DownloadConnection(), threadPoolSize);
    }

    public static DownloadQueue newDownloadQueue(Downloader downloader, int threadPoolSize) {
        DownloadQueue downloadQueue = new DownloadQueue(downloader, threadPoolSize);
        downloadQueue.start();
        return downloadQueue;
    }

    public static DownloadRequest createDownloadRequest(String url, String fileFolder, String filename, boolean isRange, boolean isDeleteOld) {
        return createDownloadRequest(url, RequestMethod.GET, fileFolder, filename, isRange, isDeleteOld);
    }

    public static DownloadRequest createDownloadRequest(String url, RequestMethod requestMethod, String fileFolder, String filename, boolean isRange, boolean isDeleteOld) {
        return new RestDownloadRequest(url, requestMethod, fileFolder, filename, isRange, isDeleteOld);
    }

    public static CookieHandler getDefaultCookieHandler() {
        return sCookieHandler;
    }

    public static void setDefaultCookieHandler(CookieHandler cookieHandler) {
        if (cookieHandler == null) {
            throw new IllegalArgumentException("CookieHandler == null");
        }
        sCookieHandler = cookieHandler;
    }

    private NoHttp() {
    }
}
