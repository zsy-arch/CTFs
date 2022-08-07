package com.amap.api.col;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: LruCache.java */
/* loaded from: classes.dex */
public class dl<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public dl(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i;
        this.a = new LinkedHashMap<>(0, 0.75f, true);
    }

    public final V a(K k) {
        V put;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.a.get(k);
            if (v != null) {
                this.g++;
                return v;
            }
            this.h++;
            V b = b(k);
            if (b == null) {
                return null;
            }
            synchronized (this) {
                this.e++;
                put = this.a.put(k, b);
                if (put != null) {
                    this.a.put(k, put);
                } else {
                    this.b += c(k, b);
                }
            }
            if (put != null) {
                a(false, k, b, put);
                return put;
            }
            a(this.c);
            return b;
        }
    }

    public final V a(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += c(k, v);
            put = this.a.put(k, v);
            if (put != null) {
                this.b -= c(k, put);
            }
        }
        if (put != null) {
            a(false, k, put, v);
        }
        a(this.c);
        return put;
    }

    private void a(int i) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.b < 0 || !this.a.isEmpty() || this.b != 0) {
                }
                if (this.b > i) {
                    Iterator<Map.Entry<K, V>> it = this.a.entrySet().iterator();
                    Map.Entry<K, V> entry = null;
                    while (it.hasNext()) {
                        entry = it.next();
                    }
                    if (entry != null) {
                        key = entry.getKey();
                        value = entry.getValue();
                        this.a.remove(key);
                        this.b -= c(key, value);
                        this.f++;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            a(true, key, value, null);
        }
    }

    protected void a(boolean z, K k, V v, V v2) {
    }

    protected V b(K k) {
        return null;
    }

    private int c(K k, V v) {
        int b = b(k, v);
        if (b >= 0) {
            return b;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int b(K k, V v) {
        return 1;
    }

    public final void a() {
        a(-1);
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.g + this.h;
            if (i2 != 0) {
                i = (this.g * 100) / i2;
            }
            format = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i));
        }
        return format;
    }
}
