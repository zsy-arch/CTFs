package com.yolanda.nohttp.cookie;

import android.text.TextUtils;
import com.yolanda.nohttp.db.DBManager;
import com.yolanda.nohttp.db.Where;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public enum DiskCookieStore implements CookieStore {
    INSTANCE;
    
    private static final int MAX_COOKIE_SIZE = 8888;
    private CookieStoreListener mCookieStoreListener;
    private volatile boolean firstDeleteExpiry = true;
    private Lock mLock = new ReentrantLock();
    private DBManager<CookieEntity> mManager = CookieDiskManager.getInstance();

    DiskCookieStore() {
    }

    public void setCookieStoreListener(CookieStoreListener mCookieStoreListener) {
        this.mCookieStoreListener = mCookieStoreListener;
    }

    @Override // java.net.CookieStore
    public void add(URI uri, HttpCookie cookie) {
        if (uri != null && cookie != null) {
            this.mLock.lock();
            try {
                URI uri2 = getEffectiveURI(uri);
                if (this.mCookieStoreListener != null) {
                    this.mCookieStoreListener.onSaveCookie(uri2, cookie);
                }
                this.mManager.replace(new CookieEntity(uri2, cookie));
                trimSize();
            } finally {
                this.mLock.unlock();
            }
        }
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> get(URI uri) {
        int lastDot;
        if (uri == null) {
            return Collections.emptyList();
        }
        this.mLock.lock();
        try {
            URI uri2 = getEffectiveURI(uri);
            deleteExpiryCookies();
            Where where = new Where();
            String host = uri2.getHost();
            if (!TextUtils.isEmpty(host)) {
                Where subWhere = new Where("domain", Where.Options.EQUAL, host);
                int lastDot2 = host.lastIndexOf(".");
                if (lastDot2 > 1 && (lastDot = host.lastIndexOf(".", lastDot2 - 1)) > 0) {
                    String domain = host.substring(lastDot, host.length());
                    if (!TextUtils.isEmpty(domain)) {
                        subWhere.or("domain", Where.Options.EQUAL, domain).bracket();
                    }
                }
                where.set(subWhere.get());
            }
            String path = uri2.getPath();
            if (!TextUtils.isEmpty(path)) {
                Where subWhere2 = new Where("path", Where.Options.EQUAL, path).or("path", Where.Options.EQUAL, "/").orNull("path");
                int lastSplit = path.lastIndexOf("/");
                while (lastSplit > 0) {
                    path = path.substring(0, lastSplit);
                    subWhere2.or("path", Where.Options.EQUAL, path);
                    lastSplit = path.lastIndexOf("/");
                }
                subWhere2.bracket();
                where.and(subWhere2);
            }
            where.or(CookieDisk.URI, Where.Options.EQUAL, uri2.toString());
            List<CookieEntity> cookieList = this.mManager.get("*", where.get(), null, null, null);
            List<HttpCookie> returnedCookies = new ArrayList<>();
            for (CookieEntity cookieEntity : cookieList) {
                returnedCookies.add(cookieEntity.toHttpCookie());
            }
            return returnedCookies;
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> getCookies() {
        this.mLock.lock();
        try {
            List<HttpCookie> rt = new ArrayList<>();
            deleteExpiryCookies();
            for (CookieEntity cookieEntity : this.mManager.getAll()) {
                rt.add(cookieEntity.toHttpCookie());
            }
            return rt;
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // java.net.CookieStore
    public List<URI> getURIs() {
        this.mLock.lock();
        try {
            List<URI> uris = new ArrayList<>();
            for (CookieEntity cookie : this.mManager.getAll(CookieDisk.URI)) {
                String uri = cookie.getUri();
                if (!TextUtils.isEmpty(uri)) {
                    uris.add(new URI(uri));
                }
            }
            return uris;
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // java.net.CookieStore
    public boolean remove(URI uri, HttpCookie httpCookie) {
        boolean z = true;
        if (httpCookie != null) {
            this.mLock.lock();
            try {
                if (this.mCookieStoreListener != null) {
                    this.mCookieStoreListener.onRemoveCookie(uri, httpCookie);
                }
                Where where = new Where("name", Where.Options.EQUAL, httpCookie.getName());
                String domain = httpCookie.getDomain();
                if (!TextUtils.isEmpty(domain)) {
                    where.and("domain", Where.Options.EQUAL, domain);
                }
                String path = httpCookie.getPath();
                if (!TextUtils.isEmpty(path)) {
                    if (path.length() > 1 && path.endsWith("/")) {
                        path = path.substring(0, path.length() - 1);
                    }
                    where.and("path", Where.Options.EQUAL, path);
                }
                z = this.mManager.delete(where.toString());
            } finally {
                this.mLock.unlock();
            }
        }
        return z;
    }

    @Override // java.net.CookieStore
    public boolean removeAll() {
        this.mLock.lock();
        try {
            return this.mManager.deleteAll();
        } finally {
            this.mLock.unlock();
        }
    }

    private void deleteExpiryCookies() {
        if (this.firstDeleteExpiry) {
            this.firstDeleteExpiry = false;
            deleteTempCookie();
        }
        this.mManager.delete(new Where(CookieDisk.EXPIRY, Where.Options.THAN_SMALL, Long.valueOf(System.currentTimeMillis())).and(CookieDisk.EXPIRY, Where.Options.NO_EQUAL, -1L).get());
    }

    private void deleteTempCookie() {
        this.mManager.delete(new Where(CookieDisk.EXPIRY, Where.Options.EQUAL, -1L).get());
    }

    private void trimSize() {
        List<CookieEntity> rmList;
        int count = this.mManager.count();
        if (count > 8898 && (rmList = this.mManager.get("*", null, null, Integer.toString(count - 8888), null)) != null) {
            this.mManager.delete(rmList);
        }
    }

    private URI getEffectiveURI(URI uri) {
        try {
            return new URI(HttpHost.DEFAULT_SCHEME_NAME, uri.getHost(), uri.getPath(), null, null);
        } catch (URISyntaxException e) {
            return uri;
        }
    }
}
