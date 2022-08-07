package com.cdlinglu.utils.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class PermissionsManager {
    private static final String TAG = PermissionsManager.class.getSimpleName();
    private static PermissionsManager mInstance = null;
    private final Set<String> mPendingRequests = new HashSet(1);
    private final Set<String> mPermissions = new HashSet(1);
    private final List<WeakReference<PermissionsResultAction>> mPendingActions = new ArrayList(1);

    public static PermissionsManager getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionsManager();
        }
        return mInstance;
    }

    private PermissionsManager() {
        initializePermissionsMap();
    }

    private synchronized void initializePermissionsMap() {
        for (Field field : Manifest.permission.class.getFields()) {
            String name = null;
            try {
                name = (String) field.get("");
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Could not access field", e);
            }
            this.mPermissions.add(name);
        }
    }

    @NonNull
    private synchronized String[] getManifestPermissions(@NonNull Activity activity) {
        List<String> list;
        String[] permissions;
        PackageInfo packageInfo = null;
        list = new ArrayList<>(1);
        try {
            Log.d(TAG, activity.getPackageName());
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "A problem occurred when retrieving permissions", e);
        }
        if (!(packageInfo == null || (permissions = packageInfo.requestedPermissions) == null)) {
            for (String perm : permissions) {
                Log.d(TAG, "Manifest contained permission: " + perm);
                list.add(perm);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    private synchronized void addPendingAction(@NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        if (action != null) {
            action.registerPermissions(permissions);
            this.mPendingActions.add(new WeakReference<>(action));
        }
    }

    private synchronized void removePendingAction(@Nullable PermissionsResultAction action) {
        Iterator<WeakReference<PermissionsResultAction>> iterator = this.mPendingActions.iterator();
        while (iterator.hasNext()) {
            WeakReference<PermissionsResultAction> weakRef = iterator.next();
            if (weakRef.get() == action || weakRef.get() == null) {
                iterator.remove();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
        if (r1.mPermissions.contains(r3) == false) goto L_0x0011;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasPermission(@android.support.annotation.Nullable android.content.Context r2, @android.support.annotation.NonNull java.lang.String r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L_0x0014
            int r0 = android.support.v4.app.ActivityCompat.checkSelfPermission(r2, r3)     // Catch: all -> 0x0016
            if (r0 == 0) goto L_0x0011
            java.util.Set<java.lang.String> r0 = r1.mPermissions     // Catch: all -> 0x0016
            boolean r0 = r0.contains(r3)     // Catch: all -> 0x0016
            if (r0 != 0) goto L_0x0014
        L_0x0011:
            r0 = 1
        L_0x0012:
            monitor-exit(r1)
            return r0
        L_0x0014:
            r0 = 0
            goto L_0x0012
        L_0x0016:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cdlinglu.utils.permissions.PermissionsManager.hasPermission(android.content.Context, java.lang.String):boolean");
    }

    public synchronized boolean hasAllPermissions(@Nullable Context context, @NonNull String[] permissions) {
        boolean hasAllPermissions;
        synchronized (this) {
            if (context == null) {
                hasAllPermissions = false;
            } else {
                hasAllPermissions = true;
                for (String perm : permissions) {
                    hasAllPermissions &= hasPermission(context, perm);
                }
            }
        }
        return hasAllPermissions;
    }

    public synchronized void requestAllManifestPermissionsIfNecessary(@Nullable Activity activity, @Nullable PermissionsResultAction action) {
        if (activity != null) {
            requestPermissionsIfNecessaryForResult(activity, getManifestPermissions(activity), action);
        }
    }

    public synchronized void requestPermissionsIfNecessaryForResult(@Nullable Activity activity, @NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        if (activity != null) {
            addPendingAction(permissions, action);
            if (Build.VERSION.SDK_INT < 23) {
                doPermissionWorkBeforeAndroidM(activity, permissions, action);
            } else {
                List<String> permList = getPermissionsListToRequest(activity, permissions, action);
                if (permList.isEmpty()) {
                    removePendingAction(action);
                } else {
                    this.mPendingRequests.addAll(permList);
                    ActivityCompat.requestPermissions(activity, (String[]) permList.toArray(new String[permList.size()]), 1);
                }
            }
        }
    }

    public synchronized void requestPermissionsIfNecessaryForResult(@NonNull Fragment fragment, @NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            addPendingAction(permissions, action);
            if (Build.VERSION.SDK_INT < 23) {
                doPermissionWorkBeforeAndroidM(activity, permissions, action);
            } else {
                List<String> permList = getPermissionsListToRequest(activity, permissions, action);
                if (permList.isEmpty()) {
                    removePendingAction(action);
                } else {
                    this.mPendingRequests.addAll(permList);
                    fragment.requestPermissions((String[]) permList.toArray(new String[permList.size()]), 1);
                }
            }
        }
    }

    public synchronized void notifyPermissionsChange(@NonNull String[] permissions, @NonNull int[] results) {
        int size = permissions.length;
        if (results.length < size) {
            size = results.length;
        }
        Iterator<WeakReference<PermissionsResultAction>> iterator = this.mPendingActions.iterator();
        while (iterator.hasNext()) {
            PermissionsResultAction action = iterator.next().get();
            for (int n = 0; n < size; n++) {
                if (action == null || action.onResult(permissions[n], results[n])) {
                    iterator.remove();
                    break;
                }
            }
        }
        for (int n2 = 0; n2 < size; n2++) {
            this.mPendingRequests.remove(permissions[n2]);
        }
    }

    private void doPermissionWorkBeforeAndroidM(@NonNull Activity activity, @NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        for (String perm : permissions) {
            if (action != null) {
                if (!this.mPermissions.contains(perm)) {
                    action.onResult(perm, Permissions.NOT_FOUND);
                } else if (ActivityCompat.checkSelfPermission(activity, perm) != 0) {
                    action.onResult(perm, Permissions.DENIED);
                } else {
                    action.onResult(perm, Permissions.GRANTED);
                }
            }
        }
    }

    @NonNull
    private List<String> getPermissionsListToRequest(@NonNull Activity activity, @NonNull String[] permissions, @Nullable PermissionsResultAction action) {
        List<String> permList = new ArrayList<>(permissions.length);
        for (String perm : permissions) {
            if (!this.mPermissions.contains(perm)) {
                if (action != null) {
                    action.onResult(perm, Permissions.NOT_FOUND);
                }
            } else if (ActivityCompat.checkSelfPermission(activity, perm) != 0) {
                if (!this.mPendingRequests.contains(perm)) {
                    permList.add(perm);
                }
            } else if (action != null) {
                action.onResult(perm, Permissions.GRANTED);
            }
        }
        return permList;
    }
}
