package com.amap.api.col;

import android.os.Environment;
import java.io.File;
import java.io.IOException;

/* compiled from: LogWriter.java */
/* loaded from: classes.dex */
public class fm {
    private final String a;

    public fm() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "yplog.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.a = file.getAbsolutePath();
    }
}
