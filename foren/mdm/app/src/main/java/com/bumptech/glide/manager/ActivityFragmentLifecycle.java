package com.bumptech.glide.manager;

import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ActivityFragmentLifecycle implements Lifecycle {
    private boolean isDestroyed;
    private boolean isStarted;
    private final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(LifecycleListener listener) {
        this.lifecycleListeners.add(listener);
        if (this.isDestroyed) {
            listener.onDestroy();
        } else if (this.isStarted) {
            listener.onStart();
        } else {
            listener.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onStart() {
        this.isStarted = true;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onStop() {
        this.isStarted = false;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDestroy() {
        this.isDestroyed = true;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onDestroy();
        }
    }
}
