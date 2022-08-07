package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.Key;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class EngineResource<Z> implements Resource<Z> {
    private int acquired;
    private final boolean isCacheable;
    private boolean isRecycled;
    private Key key;
    private ResourceListener listener;
    private final Resource<Z> resource;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ResourceListener {
        void onResourceReleased(Key key, EngineResource<?> engineResource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineResource(Resource<Z> toWrap, boolean isCacheable) {
        if (toWrap == null) {
            throw new NullPointerException("Wrapped resource must not be null");
        }
        this.resource = toWrap;
        this.isCacheable = isCacheable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResourceListener(Key key, ResourceListener listener) {
        this.key = key;
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCacheable() {
        return this.isCacheable;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public Z get() {
        return this.resource.get();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.resource.getSize();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
        if (this.acquired > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (this.isRecycled) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        } else {
            this.isRecycled = true;
            this.resource.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acquire() {
        if (this.isRecycled) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        } else {
            this.acquired++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        if (this.acquired <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call release on the main thread");
        } else {
            int i = this.acquired - 1;
            this.acquired = i;
            if (i == 0) {
                this.listener.onResourceReleased(this.key, this);
            }
        }
    }
}
