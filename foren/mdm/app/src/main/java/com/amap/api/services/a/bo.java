package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.amap.api.services.a.be;
import com.amap.api.services.a.cp;
import com.cdlinglu.utils.FlagUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogProcessor.java */
/* loaded from: classes.dex */
public abstract class bo {
    private be a;
    private int b;
    private cq c;
    private cp d;

    protected abstract String a(List<be> list);

    protected abstract boolean a(Context context);

    public bo(int i) {
        this.b = i;
    }

    private void e(Context context) {
        try {
            this.d = b(context, a());
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    void a(be beVar, Context context, Throwable th, String str, String str2, String str3) {
        a(beVar, context, c(th), str, str2, str3);
    }

    void a(be beVar, Context context, String str, String str2, String str3, String str4) {
        a(beVar);
        String d = d();
        String a2 = a(context, beVar);
        String a3 = aw.a(context);
        if (str != null && !"".equals(str)) {
            int b = b();
            StringBuilder sb = new StringBuilder();
            if (str3 != null) {
                sb.append("class:").append(str3);
            }
            if (str4 != null) {
                sb.append(" method:").append(str4).append("$").append("<br/>");
            }
            sb.append(str2);
            String a4 = a(str2);
            String a5 = a(a3, a2, d, b, str, sb.toString());
            if (a5 != null && !"".equals(a5)) {
                String a6 = a(context, a5);
                String a7 = a();
                synchronized (Looper.getMainLooper()) {
                    by byVar = new by(context);
                    a(byVar, beVar.a(), a4, b, a(context, a4, a7, a6, byVar));
                }
            }
        }
    }

    public void a(Context context, Throwable th, String str, String str2) {
        String a2;
        List<be> f = f(context);
        if (f != null && f.size() != 0 && (a2 = a(th)) != null && !"".equals(a2)) {
            for (be beVar : f) {
                if (a(beVar.e(), a2)) {
                    a(beVar, context, th, a2.replaceAll("\n", "<br/>"), str, str2);
                    return;
                }
            }
            if (a2.contains("com.amap.api.col")) {
                try {
                    a(new be.a(FlagUtil.URL_SAVE, "1.0", "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a(), context, th, a2, str, str2);
                } catch (av e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String a(Context context, be beVar) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sim\":\"").append(ba.e(context)).append("\",\"sdkversion\":\"").append(beVar.c()).append("\",\"product\":\"").append(beVar.a()).append("\",\"ed\":\"").append(beVar.d()).append("\",\"nt\":\"").append(ba.c(context)).append("\",\"np\":\"").append(ba.a(context)).append("\",\"mnc\":\"").append(ba.b(context)).append("\",\"ant\":\"").append(ba.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        r5 = d();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.content.Context r18) {
        /*
            r17 = this;
            java.util.List r2 = r17.f(r18)
            if (r2 == 0) goto L_0x000c
            int r3 = r2.size()
            if (r3 != 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            r0 = r17
            java.lang.String r8 = r0.a(r2)
            if (r8 == 0) goto L_0x000c
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x000c
            java.lang.String r5 = r17.d()
            r0 = r17
            com.amap.api.services.a.be r2 = r0.a
            r0 = r18
            java.lang.String r4 = a(r0, r2)
            java.lang.String r3 = com.amap.api.services.a.aw.a(r18)
            java.lang.String r7 = "ANR"
            int r6 = r17.b()
            r2 = r17
            java.lang.String r2 = r2.a(r3, r4, r5, r6, r7, r8)
            if (r2 == 0) goto L_0x000c
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x000c
            r0 = r17
            java.lang.String r9 = r0.a(r8)
            r0 = r17
            r1 = r18
            java.lang.String r11 = r0.a(r1, r2)
            java.lang.String r10 = r17.a()
            android.os.Looper r3 = android.os.Looper.getMainLooper()
            monitor-enter(r3)
            com.amap.api.services.a.by r12 = new com.amap.api.services.a.by     // Catch: all -> 0x007c
            r0 = r18
            r12.<init>(r0)     // Catch: all -> 0x007c
            r7 = r17
            r8 = r18
            boolean r16 = r7.a(r8, r9, r10, r11, r12)     // Catch: all -> 0x007c
            r0 = r17
            com.amap.api.services.a.be r2 = r0.a     // Catch: all -> 0x007c
            java.lang.String r13 = r2.a()     // Catch: all -> 0x007c
            r11 = r17
            r14 = r9
            r15 = r6
            r11.a(r12, r13, r14, r15, r16)     // Catch: all -> 0x007c
            monitor-exit(r3)     // Catch: all -> 0x007c
            goto L_0x000c
        L_0x007c:
            r2 = move-exception
            monitor-exit(r3)     // Catch: all -> 0x007c
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bo.b(android.content.Context):void");
    }

    protected void a(be beVar) {
        this.a = beVar;
    }

    private List<be> f(Context context) {
        List<be> list;
        Throwable th;
        Throwable th2;
        List<be> list2 = null;
        try {
        } catch (Throwable th3) {
            list = null;
            th = th3;
        }
        synchronized (Looper.getMainLooper()) {
            try {
                list = new ca(context, false).a();
                try {
                    return list;
                } catch (Throwable th4) {
                    list2 = list;
                    th2 = th4;
                    try {
                        throw th2;
                    } catch (Throwable th5) {
                        list = list2;
                        th = th5;
                        th.printStackTrace();
                        return list;
                    }
                }
            } catch (Throwable th6) {
                th2 = th6;
            }
        }
    }

    private void a(by byVar, String str, String str2, int i, boolean z) {
        bz b = bi.b(i);
        b.a(0);
        b.b(str);
        b.a(str2);
        byVar.a(b);
    }

    protected String a(String str) {
        return bc.c(str);
    }

    protected cq a(by byVar) {
        try {
            if (this.c == null) {
                this.c = new a(byVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.c;
    }

    /* compiled from: LogProcessor.java */
    /* loaded from: classes.dex */
    public class a implements cq {
        private by b;

        a(by byVar) {
            bo.this = r1;
            this.b = byVar;
        }

        @Override // com.amap.api.services.a.cq
        public void a(String str) {
            try {
                this.b.b(str, bi.a(bo.this.b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    private String a(Context context, String str) {
        try {
            return az.e(context, bf.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String d() {
        return bf.a(new Date().getTime());
    }

    protected String a(Throwable th) {
        try {
            return b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    private String c(Throwable th) {
        return th.toString();
    }

    private boolean a(Context context, String str, String str2, String str3, by byVar) {
        OutputStream outputStream;
        cp cpVar;
        AutoCloseable autoCloseable;
        Throwable th;
        boolean z;
        try {
            outputStream = null;
            cpVar = null;
            autoCloseable = null;
            try {
                File file = new File(bi.a(context, str2));
                if (file.exists() || file.mkdirs()) {
                    cpVar = cp.a(file, 1, 1, 20480L);
                    cpVar.a(a(byVar));
                    cp.b a2 = cpVar.a(str);
                    if (a2 != null) {
                        z = false;
                        if (0 != 0) {
                            try {
                                outputStream.close();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                        if (cpVar == null || cpVar.a()) {
                            return false;
                        }
                        try {
                            cpVar.close();
                            return false;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } else {
                        byte[] a3 = bf.a(str3);
                        cp.a b = cpVar.b(str);
                        outputStream = b.a(0);
                        outputStream.write(a3);
                        b.a();
                        cpVar.b();
                        z = true;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Throwable th6) {
                                th6.printStackTrace();
                            }
                        }
                        if (cpVar == null || cpVar.a()) {
                            return true;
                        }
                        try {
                            cpVar.close();
                            return true;
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    }
                } else {
                    z = false;
                    if (0 != 0) {
                        try {
                            outputStream.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    if (0 != 0) {
                        try {
                            autoCloseable.close();
                        } catch (Throwable th9) {
                            th9.printStackTrace();
                        }
                    }
                    if (0 == 0 || cpVar.a()) {
                        return false;
                    }
                    try {
                        cpVar.close();
                        return false;
                    } catch (Throwable th10) {
                        th = th10;
                    }
                }
                th.printStackTrace();
                return z;
            } catch (IOException e) {
                e.printStackTrace();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th11) {
                        th11.printStackTrace();
                    }
                }
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (Throwable th12) {
                        th12.printStackTrace();
                    }
                }
                if (cpVar != null && !cpVar.a()) {
                    try {
                        cpVar.close();
                    } catch (Throwable th13) {
                        th13.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th14) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th15) {
                    th15.printStackTrace();
                }
            }
            if (0 != 0) {
                try {
                    autoCloseable.close();
                } catch (Throwable th16) {
                    th16.printStackTrace();
                }
            }
            if (cpVar != null && !cpVar.a()) {
                try {
                    cpVar.close();
                } catch (Throwable th17) {
                    th17.printStackTrace();
                }
            }
            throw th14;
        }
    }

    public static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : str.split("\n")) {
                if (b(strArr, str2.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : strArr) {
                str = str.trim();
                if (str.startsWith("at ") && str.contains(str2 + ".") && str.endsWith(")")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void c(Context context) {
        try {
            e(context);
            if (a(context)) {
                synchronized (Looper.getMainLooper()) {
                    by byVar = new by(context);
                    a(byVar, b());
                    List<? extends bz> a2 = byVar.a(0, bi.a(b()));
                    if (a2 != null && a2.size() != 0) {
                        String a3 = a(a2, context);
                        if (a3 != null) {
                            if (c(a3) == 1) {
                                a(a2, byVar, b());
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "processUpdateLog");
        }
    }

    private boolean b(String str) {
        if (this.d == null) {
            return false;
        }
        try {
            return this.d.c(str);
        } catch (Throwable th) {
            bh.a(th, "LogUpdateProcessor", "deleteLogData");
            return false;
        }
    }

    protected String a() {
        return bi.c(this.b);
    }

    protected int b() {
        return this.b;
    }

    private void a(by byVar, int i) {
        try {
            a(byVar.a(2, bi.a(i)), byVar, i);
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "processDeleteFail");
        }
    }

    private int c(String str) {
        int i = 0;
        try {
            byte[] b = ct.a().b(new bj(bf.c(bf.a(str))));
            if (b != null) {
                try {
                    JSONObject jSONObject = new JSONObject(bf.a(b));
                    if (jSONObject.has("code")) {
                        i = jSONObject.getInt("code");
                    }
                } catch (JSONException e) {
                    bh.a(e, "LogProcessor", "processUpdate");
                    i = 1;
                }
            }
        } catch (av e2) {
            if (e2.b() != 27) {
                i = 1;
            }
            bh.a(e2, "LogProcessor", "processUpdate");
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<? extends bz> list, by byVar, int i) {
        if (list != null && list.size() > 0) {
            for (bz bzVar : list) {
                if (b(bzVar.b())) {
                    byVar.a(bzVar.b(), (Class<? extends bz>) bzVar.getClass());
                } else {
                    bzVar.a(2);
                    byVar.b(bzVar);
                }
            }
        }
    }

    public static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(aw.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(ba.q(context)).append("\",\"pkg\":\"").append(aw.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(aw.b(context)).append("\",\"appversion\":\"").append(aw.d(context)).append("\",\"sysversion\":\"").append(Build.VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            bh.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }

    private String g(Context context) {
        try {
            String d = d(context);
            if ("".equals(d)) {
                return null;
            }
            return az.b(context, bf.a(d));
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    private String a(List<? extends bz> list, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pinfo\":\"").append(g(context)).append("\",\"els\":[");
        boolean z = true;
        for (bz bzVar : list) {
            String d = d(bzVar.b());
            if (d != null && !"".equals(d)) {
                String str = d + "||" + bzVar.c();
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append("{\"log\":\"").append(str).append("\"}");
            }
        }
        if (z) {
            return null;
        }
        sb.append("]}");
        return sb.toString();
    }

    private String d(String str) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        String str2;
        String str3;
        try {
            String str4 = null;
            InputStream inputStream2 = null;
            ByteArrayOutputStream byteArrayOutputStream3 = null;
            try {
                if (this.d == null) {
                    if (0 != 0) {
                        try {
                            byteArrayOutputStream3.close();
                        } catch (IOException e) {
                            bh.a(e, "LogProcessor", "readLog1");
                        }
                    }
                    if (0 != 0) {
                        try {
                            inputStream2.close();
                        } catch (IOException e2) {
                            e = e2;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            bh.a(e, str2, str3);
                            return str4;
                        }
                    }
                } else {
                    cp.b a2 = this.d.a(str);
                    if (a2 == null) {
                        if (0 != 0) {
                            try {
                                byteArrayOutputStream3.close();
                            } catch (IOException e3) {
                                bh.a(e3, "LogProcessor", "readLog1");
                            }
                        }
                        if (0 != 0) {
                            try {
                                inputStream2.close();
                            } catch (IOException e4) {
                                e = e4;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                bh.a(e, str2, str3);
                                return str4;
                            }
                        }
                    } else {
                        inputStream = a2.a(0);
                        try {
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream2.write(bArr, 0, read);
                                }
                                str4 = bf.a(byteArrayOutputStream2.toByteArray());
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e5) {
                                        bh.a(e5, "LogProcessor", "readLog1");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e6) {
                                        e = e6;
                                        str2 = "LogProcessor";
                                        str3 = "readLog2";
                                        bh.a(e, str2, str3);
                                        return str4;
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                bh.a(th, "LogProcessor", "readLog");
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e7) {
                                        bh.a(e7, "LogProcessor", "readLog1");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e8) {
                                        e = e8;
                                        str2 = "LogProcessor";
                                        str3 = "readLog2";
                                        bh.a(e, str2, str3);
                                        return str4;
                                    }
                                }
                                return str4;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            byteArrayOutputStream2 = null;
                        }
                    }
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                inputStream = null;
                th = th3;
            }
            return str4;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public void c() {
        try {
            if (this.d != null && !this.d.a()) {
                this.d.close();
            }
        } catch (IOException e) {
            bh.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "closeDiskLru");
        }
    }

    private cp b(Context context, String str) {
        try {
            File file = new File(bi.a(context, str));
            if (file.exists() || file.mkdirs()) {
                return cp.a(file, 1, 1, 20480L);
            }
            return null;
        } catch (IOException e) {
            bh.a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable th) {
            bh.a(th, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    public static String b(Throwable th) {
        return bf.a(th);
    }
}
