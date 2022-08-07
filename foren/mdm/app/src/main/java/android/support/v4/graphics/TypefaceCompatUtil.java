package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), prefix + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.nio.ByteBuffer mmap(java.io.File r11) {
        /*
            r8 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: IOException -> 0x0024
            r7.<init>(r11)     // Catch: IOException -> 0x0024
            r9 = 0
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch: Throwable -> 0x002b, all -> 0x0042
            long r4 = r0.size()     // Catch: Throwable -> 0x002b, all -> 0x0042
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: Throwable -> 0x002b, all -> 0x0042
            r2 = 0
            java.nio.MappedByteBuffer r1 = r0.map(r1, r2, r4)     // Catch: Throwable -> 0x002b, all -> 0x0042
            if (r7 == 0) goto L_0x001e
            if (r8 == 0) goto L_0x0027
            r7.close()     // Catch: Throwable -> 0x001f, IOException -> 0x0024
        L_0x001e:
            return r1
        L_0x001f:
            r2 = move-exception
            r9.addSuppressed(r2)     // Catch: IOException -> 0x0024
            goto L_0x001e
        L_0x0024:
            r6 = move-exception
            r1 = r8
            goto L_0x001e
        L_0x0027:
            r7.close()     // Catch: IOException -> 0x0024
            goto L_0x001e
        L_0x002b:
            r1 = move-exception
            throw r1     // Catch: all -> 0x002d
        L_0x002d:
            r2 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
        L_0x0031:
            if (r7 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x003e
            r7.close()     // Catch: Throwable -> 0x0039, IOException -> 0x0024
        L_0x0038:
            throw r1     // Catch: IOException -> 0x0024
        L_0x0039:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch: IOException -> 0x0024
            goto L_0x0038
        L_0x003e:
            r7.close()     // Catch: IOException -> 0x0024
            goto L_0x0038
        L_0x0042:
            r1 = move-exception
            r2 = r8
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.nio.ByteBuffer mmap(android.content.Context r13, android.os.CancellationSignal r14, android.net.Uri r15) {
        /*
            android.content.ContentResolver r9 = r13.getContentResolver()
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r8 = r9.openFileDescriptor(r15, r1, r14)     // Catch: IOException -> 0x0047
            r11 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: Throwable -> 0x0039, all -> 0x004e
            java.io.FileDescriptor r1 = r8.getFileDescriptor()     // Catch: Throwable -> 0x0039, all -> 0x004e
            r7.<init>(r1)     // Catch: Throwable -> 0x0039, all -> 0x004e
            r10 = 0
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch: Throwable -> 0x005a, all -> 0x007a
            long r4 = r0.size()     // Catch: Throwable -> 0x005a, all -> 0x007a
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: Throwable -> 0x005a, all -> 0x007a
            r2 = 0
            java.nio.MappedByteBuffer r1 = r0.map(r1, r2, r4)     // Catch: Throwable -> 0x005a, all -> 0x007a
            if (r7 == 0) goto L_0x002c
            if (r10 == 0) goto L_0x004a
            r7.close()     // Catch: Throwable -> 0x0034, all -> 0x004e
        L_0x002c:
            if (r8 == 0) goto L_0x0033
            if (r11 == 0) goto L_0x0056
            r8.close()     // Catch: Throwable -> 0x0051, IOException -> 0x0047
        L_0x0033:
            return r1
        L_0x0034:
            r2 = move-exception
            r10.addSuppressed(r2)     // Catch: Throwable -> 0x0039, all -> 0x004e
            goto L_0x002c
        L_0x0039:
            r1 = move-exception
            throw r1     // Catch: all -> 0x003b
        L_0x003b:
            r2 = move-exception
            r12 = r2
            r2 = r1
            r1 = r12
        L_0x003f:
            if (r8 == 0) goto L_0x0046
            if (r2 == 0) goto L_0x0076
            r8.close()     // Catch: Throwable -> 0x0071, IOException -> 0x0047
        L_0x0046:
            throw r1     // Catch: IOException -> 0x0047
        L_0x0047:
            r6 = move-exception
            r1 = 0
            goto L_0x0033
        L_0x004a:
            r7.close()     // Catch: Throwable -> 0x0039, all -> 0x004e
            goto L_0x002c
        L_0x004e:
            r1 = move-exception
            r2 = r11
            goto L_0x003f
        L_0x0051:
            r2 = move-exception
            r11.addSuppressed(r2)     // Catch: IOException -> 0x0047
            goto L_0x0033
        L_0x0056:
            r8.close()     // Catch: IOException -> 0x0047
            goto L_0x0033
        L_0x005a:
            r1 = move-exception
            throw r1     // Catch: all -> 0x005c
        L_0x005c:
            r2 = move-exception
            r12 = r2
            r2 = r1
            r1 = r12
        L_0x0060:
            if (r7 == 0) goto L_0x0067
            if (r2 == 0) goto L_0x006d
            r7.close()     // Catch: Throwable -> 0x0068, all -> 0x004e
        L_0x0067:
            throw r1     // Catch: Throwable -> 0x0039, all -> 0x004e
        L_0x0068:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch: Throwable -> 0x0039, all -> 0x004e
            goto L_0x0067
        L_0x006d:
            r7.close()     // Catch: Throwable -> 0x0039, all -> 0x004e
            goto L_0x0067
        L_0x0071:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch: IOException -> 0x0047
            goto L_0x0046
        L_0x0076:
            r8.close()     // Catch: IOException -> 0x0047
            goto L_0x0046
        L_0x007a:
            r1 = move-exception
            r2 = r10
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        ByteBuffer byteBuffer = null;
        File tmpFile = getTempFile(context);
        if (tmpFile != null) {
            try {
                if (copyToFile(tmpFile, res, id)) {
                    byteBuffer = mmap(tmpFile);
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream is) {
        Throwable th;
        FileOutputStream os;
        FileOutputStream os2;
        try {
            os = null;
            try {
                os2 = new FileOutputStream(file, false);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int readLen = is.read(buffer);
                if (readLen != -1) {
                    os2.write(buffer, 0, readLen);
                } else {
                    closeQuietly(os2);
                    return true;
                }
            }
        } catch (IOException e2) {
            e = e2;
            os = os2;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(os);
            return false;
        } catch (Throwable th3) {
            th = th3;
            os = os2;
            closeQuietly(os);
            throw th;
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            return copyToFile(file, is);
        } finally {
            closeQuietly(is);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
