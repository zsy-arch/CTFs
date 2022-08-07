package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;

/* loaded from: classes.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    public static File f1598a;

    public static long a() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    @TargetApi(9)
    public static boolean a(Context context) {
        File tbsFolderDir;
        if (context == null) {
            return false;
        }
        if (f1598a != null) {
            return true;
        }
        try {
            if (context.getApplicationInfo().processName.contains(TbsConfig.APP_WX) && (tbsFolderDir = QbSdk.getTbsFolderDir(context)) != null && tbsFolderDir.isDirectory()) {
                File file = new File(tbsFolderDir, "share");
                if (!file.isDirectory() && !file.mkdir()) {
                    return false;
                }
                f1598a = file;
                file.setExecutable(true, false);
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
