package c.e.c;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

/* loaded from: classes.dex */
public class d extends i {
    public final File a(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0055 A[Catch: Throwable -> 0x005c, all -> 0x0059, TryCatch #2 {Throwable -> 0x005c, blocks: (B:7:0x0016, B:9:0x001c, B:12:0x0023, B:16:0x002d, B:18:0x003a, B:30:0x0051, B:31:0x0055, B:32:0x0058), top: B:45:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // c.e.c.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface a(android.content.Context r5, android.os.CancellationSignal r6, c.e.e.f.b[] r7, int r8) {
        /*
            r4 = this;
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L_0x0006
            return r1
        L_0x0006:
            c.e.e.f$b r7 = r4.a(r7, r8)
            android.content.ContentResolver r8 = r5.getContentResolver()
            android.net.Uri r7 = r7.f806a     // Catch: IOException -> 0x0073
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r6 = r8.openFileDescriptor(r7, r0, r6)     // Catch: IOException -> 0x0073
            java.io.File r7 = r4.a(r6)     // Catch: Throwable -> 0x005c, all -> 0x0059
            if (r7 == 0) goto L_0x002d
            boolean r8 = r7.canRead()     // Catch: Throwable -> 0x005c, all -> 0x0059
            if (r8 != 0) goto L_0x0023
            goto L_0x002d
        L_0x0023:
            android.graphics.Typeface r5 = android.graphics.Typeface.createFromFile(r7)     // Catch: Throwable -> 0x005c, all -> 0x0059
            if (r6 == 0) goto L_0x002c
            r6.close()     // Catch: IOException -> 0x0073
        L_0x002c:
            return r5
        L_0x002d:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: Throwable -> 0x005c, all -> 0x0059
            java.io.FileDescriptor r8 = r6.getFileDescriptor()     // Catch: Throwable -> 0x005c, all -> 0x0059
            r7.<init>(r8)     // Catch: Throwable -> 0x005c, all -> 0x0059
            android.graphics.Typeface r5 = super.a(r5, r7)     // Catch: Throwable -> 0x0044, all -> 0x0041
            r7.close()     // Catch: Throwable -> 0x005c, all -> 0x0059
            r6.close()     // Catch: IOException -> 0x0073
            return r5
        L_0x0041:
            r5 = move-exception
            r8 = r1
            goto L_0x004a
        L_0x0044:
            r5 = move-exception
            throw r5     // Catch: all -> 0x0046
        L_0x0046:
            r8 = move-exception
            r3 = r8
            r8 = r5
            r5 = r3
        L_0x004a:
            if (r8 == 0) goto L_0x0055
            r7.close()     // Catch: Throwable -> 0x0050, all -> 0x0059
            goto L_0x0058
        L_0x0050:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch: Throwable -> 0x005c, all -> 0x0059
            goto L_0x0058
        L_0x0055:
            r7.close()     // Catch: Throwable -> 0x005c, all -> 0x0059
        L_0x0058:
            throw r5     // Catch: Throwable -> 0x005c, all -> 0x0059
        L_0x0059:
            r5 = move-exception
            r7 = r1
            goto L_0x0062
        L_0x005c:
            r5 = move-exception
            throw r5     // Catch: all -> 0x005e
        L_0x005e:
            r7 = move-exception
            r3 = r7
            r7 = r5
            r5 = r3
        L_0x0062:
            if (r6 == 0) goto L_0x0072
            if (r7 == 0) goto L_0x006f
            r6.close()     // Catch: Throwable -> 0x006a
            goto L_0x0072
        L_0x006a:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch: IOException -> 0x0073
            goto L_0x0072
        L_0x006f:
            r6.close()     // Catch: IOException -> 0x0073
        L_0x0072:
            throw r5     // Catch: IOException -> 0x0073
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.c.d.a(android.content.Context, android.os.CancellationSignal, c.e.e.f$b[], int):android.graphics.Typeface");
    }
}
