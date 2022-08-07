package com.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class InstallationId {
    private static final String TAG = "InstallationId";
    private final File file;
    private String installationId;
    private final Object lock = new Object();

    public InstallationId(File file) {
        this.file = file;
    }

    public String get() {
        synchronized (this.lock) {
            if (this.installationId == null) {
                try {
                    this.installationId = ParseFileUtils.readFileToString(this.file, "UTF-8");
                } catch (FileNotFoundException e) {
                    PLog.i(TAG, "Couldn't find existing installationId file. Creating one instead.");
                } catch (IOException e2) {
                    PLog.e(TAG, "Unexpected exception reading installation id from disk", e2);
                }
            }
            if (this.installationId == null) {
                setInternal(UUID.randomUUID().toString());
            }
        }
        return this.installationId;
    }

    public void set(String newInstallationId) {
        synchronized (this.lock) {
            if (!ParseTextUtils.isEmpty(newInstallationId) && !newInstallationId.equals(get())) {
                setInternal(newInstallationId);
            }
        }
    }

    private void setInternal(String newInstallationId) {
        synchronized (this.lock) {
            try {
                ParseFileUtils.writeStringToFile(this.file, newInstallationId, "UTF-8");
            } catch (IOException e) {
                PLog.e(TAG, "Unexpected exception writing installation id to disk", e);
            }
            this.installationId = newInstallationId;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        synchronized (this.lock) {
            this.installationId = null;
            ParseFileUtils.deleteQuietly(this.file);
        }
    }
}
