package com.bumptech.glide.manager;

/* loaded from: classes.dex */
class ApplicationLifecycle implements Lifecycle {
    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(LifecycleListener listener) {
        listener.onStart();
    }
}
