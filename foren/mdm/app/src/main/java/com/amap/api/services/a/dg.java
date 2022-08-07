package com.amap.api.services.a;

import com.amap.api.services.a.cp;
import java.io.InputStream;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class dg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(cp cpVar, String str) {
        cp.b bVar;
        InputStream inputStream;
        InputStream inputStream2;
        byte[] bArr;
        Throwable th;
        try {
            inputStream = null;
            inputStream2 = null;
            bArr = new byte[0];
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bVar = cpVar.a(str);
        } catch (Throwable th3) {
            th = th3;
            bVar = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            }
            throw th;
        }
        if (bVar == null) {
            if (0 != 0) {
                try {
                    inputStream2.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (Throwable th7) {
                    th = th7;
                }
            }
            return bArr;
        }
        try {
            inputStream = bVar.a(0);
            if (inputStream == null) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th8) {
                        th8.printStackTrace();
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th9) {
                        th = th9;
                    }
                }
            } else {
                bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                cpVar.c(str);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th10) {
                        th10.printStackTrace();
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th11) {
                        th = th11;
                    }
                }
            }
        } catch (Throwable th12) {
            th = th12;
            bh.a(th, "Utils", "readSingleLog");
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (Throwable th13) {
                    th13.printStackTrace();
                }
            }
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (Throwable th14) {
                    th = th14;
                }
            }
            return bArr;
        }
        return bArr;
        th.printStackTrace();
        return bArr;
    }
}
