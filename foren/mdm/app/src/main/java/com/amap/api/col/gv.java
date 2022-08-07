package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.amap.api.col.hw;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogProcessor.java */
/* loaded from: classes.dex */
public abstract class gv {
    static final List<b> a = Collections.synchronizedList(new ArrayList());
    private gj b;
    private int c;
    private hx d;
    private hw e;

    /* compiled from: LogProcessor.java */
    /* loaded from: classes.dex */
    public interface b {
        void a(Context context);
    }

    protected abstract String a(List<gj> list);

    protected abstract boolean a(Context context);

    public static List<b> a() {
        return a;
    }

    public gv(int i) {
        this.c = i;
    }

    private void f(Context context) {
        try {
            this.e = b(context, b());
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    public void a(gj gjVar, Context context, Throwable th, String str, String str2, String str3) {
        a(gjVar, context, c(th), str, str2, str3);
    }

    public void a(gj gjVar, Context context, String str, String str2, String str3, String str4) {
        a(gjVar);
        String a2 = io.a();
        String a3 = a(context, gjVar);
        String a4 = ga.a(context);
        if (str != null && !"".equals(str)) {
            int c = c();
            StringBuilder sb = new StringBuilder();
            if (str3 != null) {
                sb.append("class:").append(str3);
            }
            if (str4 != null) {
                sb.append(" method:").append(str4).append("$").append("<br/>");
            }
            sb.append(str2);
            String a5 = a(str2);
            String a6 = io.a(a4, a3, a2, c, str, sb.toString());
            if (a6 != null && !"".equals(a6)) {
                String a7 = a(context, a6);
                String b2 = b();
                synchronized (Looper.getMainLooper()) {
                    hf hfVar = new hf(context);
                    a(hfVar, gjVar.a(), a5, c, a(context, a5, b2, a7, hfVar));
                }
            }
        }
    }

    public static String a(Context context, gj gjVar) {
        return io.a(context, gjVar);
    }

    public void a(Context context, Throwable th, String str, String str2) {
        String a2;
        List<gj> g = g(context);
        if (g != null && g.size() != 0 && (a2 = a(th)) != null && !"".equals(a2)) {
            for (gj gjVar : g) {
                if (a(gjVar.g(), a2)) {
                    a(gjVar, context, th, a2.replaceAll("\n", "<br/>"), str, str2);
                    return;
                }
            }
            if (a2.contains("com.amap.api.col")) {
                try {
                    a(gk.a(), context, th, a2, str, str2);
                } catch (fz e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        r2 = com.amap.api.col.io.a();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.content.Context r15) {
        /*
            r14 = this;
            java.util.List r0 = r14.g(r15)
            if (r0 == 0) goto L_0x000c
            int r1 = r0.size()
            if (r1 != 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            java.lang.String r5 = r14.a(r0)
            if (r5 == 0) goto L_0x000c
            java.lang.String r0 = ""
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x000c
            java.lang.String r2 = com.amap.api.col.io.a()
            com.amap.api.col.gj r0 = r14.b
            java.lang.String r1 = com.amap.api.col.io.a(r15, r0)
            java.lang.String r0 = com.amap.api.col.ga.a(r15)
            java.lang.String r4 = "ANR"
            int r3 = r14.c()
            java.lang.String r0 = com.amap.api.col.io.a(r0, r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000c
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x000c
            java.lang.String r6 = r14.a(r5)
            java.lang.String r8 = r14.a(r15, r0)
            java.lang.String r7 = r14.b()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            monitor-enter(r1)
            com.amap.api.col.hf r9 = new com.amap.api.col.hf     // Catch: all -> 0x0067
            r9.<init>(r15)     // Catch: all -> 0x0067
            r4 = r14
            r5 = r15
            boolean r13 = r4.a(r5, r6, r7, r8, r9)     // Catch: all -> 0x0067
            com.amap.api.col.gj r0 = r14.b     // Catch: all -> 0x0067
            java.lang.String r10 = r0.a()     // Catch: all -> 0x0067
            r8 = r14
            r11 = r6
            r12 = r3
            r8.a(r9, r10, r11, r12, r13)     // Catch: all -> 0x0067
            monitor-exit(r1)     // Catch: all -> 0x0067
            goto L_0x000c
        L_0x0067:
            r0 = move-exception
            monitor-exit(r1)     // Catch: all -> 0x0067
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gv.b(android.content.Context):void");
    }

    protected void a(gj gjVar) {
        this.b = gjVar;
    }

    private List<gj> g(Context context) {
        List<gj> list;
        Throwable th;
        Throwable th2;
        List<gj> list2 = null;
        try {
        } catch (Throwable th3) {
            list = null;
            th = th3;
        }
        synchronized (Looper.getMainLooper()) {
            try {
                list = new hh(context, false).a();
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

    private void a(hf hfVar, String str, String str2, int i, boolean z) {
        hg b2 = gp.b(i);
        b2.a(0);
        b2.b(str);
        b2.a(str2);
        hfVar.a(b2);
    }

    protected String a(String str) {
        return gg.c(str);
    }

    protected hx a(hf hfVar) {
        try {
            if (this.d == null) {
                this.d = new a(hfVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.d;
    }

    /* compiled from: LogProcessor.java */
    /* loaded from: classes.dex */
    public class a implements hx {
        private hf b;

        a(hf hfVar) {
            gv.this = r1;
            this.b = hfVar;
        }

        @Override // com.amap.api.col.hx
        public void a(String str) {
            try {
                this.b.b(str, gp.a(gv.this.c()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private String a(Context context, String str) {
        try {
            return gd.e(context, gk.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
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

    private boolean a(Context context, String str, String str2, String str3, hf hfVar) {
        OutputStream outputStream;
        hw hwVar;
        AutoCloseable autoCloseable;
        try {
            outputStream = null;
            hwVar = null;
            autoCloseable = null;
            try {
                File file = new File(gp.a(context, str2));
                if (file.exists() || file.mkdirs()) {
                    hwVar = hw.a(file, 1, 1, 20480L);
                    hwVar.a(a(hfVar));
                    hw.b a2 = hwVar.a(str);
                    if (a2 != null) {
                        if (0 != 0) {
                            try {
                                outputStream.close();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                        if (hwVar == null || hwVar.d()) {
                            return false;
                        }
                        try {
                            hwVar.close();
                            return false;
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                            return false;
                        }
                    } else {
                        byte[] a3 = gk.a(str3);
                        hw.a b2 = hwVar.b(str);
                        outputStream = b2.a(0);
                        outputStream.write(a3);
                        b2.a();
                        hwVar.e();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                            }
                        }
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                        if (hwVar == null || hwVar.d()) {
                            return true;
                        }
                        try {
                            hwVar.close();
                            return true;
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                            return true;
                        }
                    }
                } else {
                    if (0 != 0) {
                        try {
                            outputStream.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    if (0 != 0) {
                        try {
                            autoCloseable.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    if (0 == 0 || hwVar.d()) {
                        return false;
                    }
                    try {
                        hwVar.close();
                        return false;
                    } catch (Throwable th9) {
                        th9.printStackTrace();
                        return false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th10) {
                        th10.printStackTrace();
                    }
                }
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (Throwable th11) {
                        th11.printStackTrace();
                    }
                }
                if (hwVar != null && !hwVar.d()) {
                    try {
                        hwVar.close();
                    } catch (Throwable th12) {
                        th12.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th13) {
                th13.printStackTrace();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th14) {
                        th14.printStackTrace();
                    }
                }
                if (0 != 0) {
                    try {
                        autoCloseable.close();
                    } catch (Throwable th15) {
                        th15.printStackTrace();
                    }
                }
                if (hwVar != null && !hwVar.d()) {
                    try {
                        hwVar.close();
                    } catch (Throwable th16) {
                        th16.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th17) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th18) {
                    th18.printStackTrace();
                }
            }
            if (0 != 0) {
                try {
                    autoCloseable.close();
                } catch (Throwable th19) {
                    th19.printStackTrace();
                }
            }
            if (hwVar != null && !hwVar.d()) {
                try {
                    hwVar.close();
                } catch (Throwable th20) {
                    th20.printStackTrace();
                }
            }
            throw th17;
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
            if (a(context)) {
                synchronized (Looper.getMainLooper()) {
                    f(context);
                    hf hfVar = new hf(context);
                    a(hfVar, c());
                    List<? extends hg> a2 = hfVar.a(0, gp.a(c()));
                    if (a2 != null && a2.size() != 0) {
                        String a3 = a(a2, context);
                        if (a3 != null) {
                            if (b(a3) == 1) {
                                a(a2, hfVar, c());
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "processUpdateLog");
        }
    }

    private boolean c(String str) {
        if (this.e == null) {
            return false;
        }
        try {
            return this.e.c(str);
        } catch (Throwable th) {
            go.a(th, "LogUpdateProcessor", "deleteLogData-" + str);
            return false;
        }
    }

    protected String b() {
        return gp.c(this.c);
    }

    protected int c() {
        return this.c;
    }

    private void a(hf hfVar, int i) {
        try {
            a(hfVar.a(2, gp.a(i)), hfVar, i);
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "processDeleteFail");
        }
    }

    public static int b(String str) {
        int i = 0;
        try {
            byte[] b2 = ia.a().b(new gq(gk.c(gk.a(str))));
            if (b2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(gk.a(b2));
                    if (jSONObject.has("code")) {
                        i = jSONObject.getInt("code");
                    }
                } catch (JSONException e) {
                    go.a(e, "LogProcessor", "processUpdate");
                    i = 1;
                }
            }
        } catch (fz e2) {
            if (e2.e() != 27) {
                i = 1;
            }
            go.a(e2, "LogProcessor", "processUpdate");
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<? extends hg> list, hf hfVar, int i) {
        if (list != null && list.size() > 0) {
            for (hg hgVar : list) {
                if (c(hgVar.b())) {
                    hfVar.a(hgVar.b(), (Class<? extends hg>) hgVar.getClass());
                } else {
                    hgVar.a(2);
                    hfVar.b(hgVar);
                }
            }
        }
    }

    public static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(ga.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(ge.q(context)).append("\",\"pkg\":\"").append(ga.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(ga.b(context)).append("\",\"appversion\":\"").append(ga.d(context)).append("\",\"sysversion\":\"").append(Build.VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            go.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }

    public static String e(Context context) {
        try {
            String d = d(context);
            if ("".equals(d)) {
                return null;
            }
            return gd.b(context, gk.a(d));
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    private String a(List<? extends hg> list, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pinfo\":\"").append(e(context)).append("\",\"els\":[");
        boolean z = true;
        for (hg hgVar : list) {
            String d = d(hgVar.b());
            if (d != null && !"".equals(d)) {
                String str = d + "||" + hgVar.c();
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

    /* JADX WARN: Removed duplicated region for block: B:82:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String d(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gv.d(java.lang.String):java.lang.String");
    }

    public void d() {
        try {
            if (this.e != null && !this.e.d()) {
                this.e.close();
            }
        } catch (IOException e) {
            go.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "closeDiskLru");
        }
    }

    private hw b(Context context, String str) {
        try {
            File file = new File(gp.a(context, str));
            if (file.exists() || file.mkdirs()) {
                return hw.a(file, 1, 1, 20480L);
            }
            return null;
        } catch (IOException e) {
            go.a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable th) {
            go.a(th, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    public static String b(Throwable th) {
        return gk.a(th);
    }
}
