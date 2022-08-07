package com.tencent.smtt.export.external;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.util.ArrayList;
import u.aly.d;

/* loaded from: classes2.dex */
public class LibraryLoader {
    private static String[] sLibrarySearchPaths = null;

    public static String[] getLibrarySearchPaths(Context context) {
        if (sLibrarySearchPaths != null) {
            return sLibrarySearchPaths;
        }
        if (context == null) {
            return new String[]{"/system/lib"};
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(getNativeLibraryDir(context));
        arrayList.add("/system/lib");
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        sLibrarySearchPaths = strArr;
        return sLibrarySearchPaths;
    }

    public static String getNativeLibraryDir(Context context) {
        int i = Build.VERSION.SDK_INT;
        return i >= 9 ? context.getApplicationInfo().nativeLibraryDir : i >= 4 ? context.getApplicationInfo().dataDir + "/lib" : d.a + context.getPackageName() + "/lib";
    }

    public static void loadLibrary(Context context, String str) {
        String[] librarySearchPaths = getLibrarySearchPaths(context);
        String mapLibraryName = System.mapLibraryName(str);
        int length = librarySearchPaths.length;
        for (int i = 0; i < length; i++) {
            String str2 = librarySearchPaths[i] + "/" + mapLibraryName;
            if (new File(str2).exists()) {
                try {
                    System.load(str2);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        try {
            System.loadLibrary(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
