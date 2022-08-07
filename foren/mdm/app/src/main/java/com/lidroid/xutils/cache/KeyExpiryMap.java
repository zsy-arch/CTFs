package com.lidroid.xutils.cache;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class KeyExpiryMap<K, V> extends ConcurrentHashMap<K, Long> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final long serialVersionUID = 1;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((KeyExpiryMap<K, V>) obj, (Long) obj2);
    }

    public KeyExpiryMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

    public KeyExpiryMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, 16);
    }

    public KeyExpiryMap(int initialCapacity) {
        super(initialCapacity);
    }

    public KeyExpiryMap() {
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized Long get(Object key) {
        return containsKey(key) ? (Long) super.get(key) : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Long put(K key, Long expiryTimestamp) {
        if (containsKey(key)) {
            remove((Object) key);
        }
        return (Long) super.put((KeyExpiryMap<K, V>) key, (K) expiryTimestamp);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized boolean containsKey(Object key) {
        boolean result;
        result = false;
        Long expiryTimestamp = (Long) super.get(key);
        if (expiryTimestamp == null || System.currentTimeMillis() >= expiryTimestamp.longValue()) {
            remove(key);
        } else {
            result = true;
        }
        return result;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized Long remove(Object key) {
        return (Long) super.remove(key);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized void clear() {
        super.clear();
    }
}
