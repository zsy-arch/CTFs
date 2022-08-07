package com.hyphenate.cloud;

import android.text.format.Time;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes2.dex */
public abstract class CloudFileManager {
    protected static final String TAG = "CloudFileManager";
    public static CloudFileManager instance = null;
    protected Properties sessionContext;

    public abstract boolean authorization();

    public abstract void deleteFileInBackground(String str, String str2, String str3, CloudOperationCallback cloudOperationCallback);

    public abstract void downloadFile(String str, String str2, String str3, Map<String, String> map, CloudOperationCallback cloudOperationCallback);

    public String getRemoteFileName(String str, String str2) {
        Time time = new Time();
        time.setToNow();
        return (str + time.toString().substring(0, 15)) + str2.substring(str2.lastIndexOf("."), str2.length());
    }

    public abstract void uploadFileInBackground(String str, String str2, String str3, String str4, Map<String, String> map, CloudOperationCallback cloudOperationCallback);
}
