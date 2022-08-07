package com.tencent.stat;

import android.database.Cursor;
import com.tencent.stat.common.StatLogger;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class t implements Runnable {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(n nVar) {
        this.a = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cursor cursor;
        Cursor cursor2;
        StatLogger statLogger;
        w wVar;
        try {
            cursor = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            wVar = this.a.d;
            cursor2 = wVar.getReadableDatabase().query("config", null, null, null, null, null, null);
            while (cursor2.moveToNext()) {
                try {
                    int i = cursor2.getInt(0);
                    String string = cursor2.getString(1);
                    String string2 = cursor2.getString(2);
                    int i2 = cursor2.getInt(3);
                    b bVar = new b(i);
                    bVar.a = i;
                    bVar.b = new JSONObject(string);
                    bVar.c = string2;
                    bVar.d = i2;
                    StatConfig.a(bVar);
                } catch (Throwable th2) {
                    th = th2;
                    statLogger = n.e;
                    statLogger.e(th);
                    if (cursor2 != null) {
                        cursor2.close();
                        return;
                    }
                    return;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }
}
