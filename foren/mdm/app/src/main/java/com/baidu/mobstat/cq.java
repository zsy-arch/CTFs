package com.baidu.mobstat;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class cq {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                cr.b(th);
            }
        }
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null || outputStream == null) {
            return false;
        }
        byte[] bArr = new byte[4048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return true;
                }
                outputStream.write(bArr, 0, read);
            } catch (IOException e) {
                cr.b(e);
                return false;
            }
        }
    }
}
