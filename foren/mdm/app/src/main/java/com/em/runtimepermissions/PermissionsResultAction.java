package com.em.runtimepermissions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private Looper mLooper;
    private final Set<String> mPermissions;

    public abstract void onDenied(String str);

    public abstract void onGranted();

    public PermissionsResultAction() {
        this.mPermissions = new HashSet(1);
        this.mLooper = Looper.getMainLooper();
    }

    public PermissionsResultAction(@NonNull Looper looper) {
        this.mPermissions = new HashSet(1);
        this.mLooper = Looper.getMainLooper();
        this.mLooper = looper;
    }

    public synchronized boolean shouldIgnorePermissionNotFound(String permission) {
        Log.d(TAG, "Permission not found: " + permission);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @CallSuper
    public final synchronized boolean onResult(@NonNull String permission, int result) {
        return result == 0 ? onResult(permission, Permissions.GRANTED) : onResult(permission, Permissions.DENIED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @CallSuper
    public final synchronized boolean onResult(@NonNull final String permission, Permissions result) {
        boolean z = true;
        synchronized (this) {
            this.mPermissions.remove(permission);
            if (result == Permissions.GRANTED) {
                if (this.mPermissions.isEmpty()) {
                    new Handler(this.mLooper).post(new Runnable() { // from class: com.em.runtimepermissions.PermissionsResultAction.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PermissionsResultAction.this.onGranted();
                        }
                    });
                }
                z = false;
            } else if (result == Permissions.DENIED) {
                new Handler(this.mLooper).post(new Runnable() { // from class: com.em.runtimepermissions.PermissionsResultAction.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PermissionsResultAction.this.onDenied(permission);
                    }
                });
            } else {
                if (result == Permissions.NOT_FOUND) {
                    if (!shouldIgnorePermissionNotFound(permission)) {
                        new Handler(this.mLooper).post(new Runnable() { // from class: com.em.runtimepermissions.PermissionsResultAction.4
                            @Override // java.lang.Runnable
                            public void run() {
                                PermissionsResultAction.this.onDenied(permission);
                            }
                        });
                    } else if (this.mPermissions.isEmpty()) {
                        new Handler(this.mLooper).post(new Runnable() { // from class: com.em.runtimepermissions.PermissionsResultAction.3
                            @Override // java.lang.Runnable
                            public void run() {
                                PermissionsResultAction.this.onGranted();
                            }
                        });
                    }
                }
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @CallSuper
    public final synchronized void registerPermissions(@NonNull String[] perms) {
        Collections.addAll(this.mPermissions, perms);
    }
}
