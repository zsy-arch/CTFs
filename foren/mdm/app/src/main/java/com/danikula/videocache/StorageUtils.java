package com.danikula.videocache;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import u.aly.d;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class StorageUtils {
    private static final String INDIVIDUAL_DIR_NAME = "video-cache";
    private static final Logger LOG = LoggerFactory.getLogger("StorageUtils");

    StorageUtils() {
    }

    public static File getIndividualCacheDirectory(Context context) {
        return new File(getCacheDirectory(context, true), INDIVIDUAL_DIR_NAME);
    }

    private static File getCacheDirectory(Context context, boolean preferExternal) {
        String externalStorageState;
        File appCacheDir = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if (preferExternal && "mounted".equals(externalStorageState)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir != null) {
            return appCacheDir;
        }
        String cacheDirPath = d.a + context.getPackageName() + "/cache/";
        LOG.warn("Can't define system cache directory! '" + cacheDirPath + "%s' will be used.");
        return new File(cacheDirPath);
    }

    private static File getExternalCacheDir(Context context) {
        File appCacheDir = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (appCacheDir.exists() || appCacheDir.mkdirs()) {
            return appCacheDir;
        }
        LOG.warn("Unable to create external cache directory");
        return null;
    }
}
