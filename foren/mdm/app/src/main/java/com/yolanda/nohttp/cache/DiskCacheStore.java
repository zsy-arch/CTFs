package com.yolanda.nohttp.cache;

import com.yolanda.nohttp.db.DBManager;
import com.yolanda.nohttp.db.Where;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public enum DiskCacheStore implements Cache<CacheEntity> {
    INSTANCE;
    
    private Lock mLock = new ReentrantLock();
    private DBManager<CacheEntity> mManager = CacheDiskManager.getInstance();

    DiskCacheStore() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.yolanda.nohttp.cache.Cache
    public CacheEntity get(String key) {
        this.mLock.lock();
        try {
            List<CacheEntity> cacheEntities = this.mManager.get("*", new Where("key", Where.Options.EQUAL, key).get(), null, null, null);
            return cacheEntities.size() > 0 ? cacheEntities.get(0) : null;
        } finally {
            this.mLock.unlock();
        }
    }

    public CacheEntity replace(String key, CacheEntity entrance) {
        this.mLock.lock();
        try {
            entrance.setKey(key);
            this.mManager.replace(entrance);
            return entrance;
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // com.yolanda.nohttp.cache.Cache
    public boolean remove(String key) {
        if (key == null) {
            return true;
        }
        this.mLock.lock();
        try {
            return this.mManager.delete(new Where("key", Where.Options.EQUAL, key).toString());
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // com.yolanda.nohttp.cache.Cache
    public boolean clear() {
        this.mLock.lock();
        try {
            return this.mManager.deleteAll();
        } finally {
            this.mLock.unlock();
        }
    }
}
