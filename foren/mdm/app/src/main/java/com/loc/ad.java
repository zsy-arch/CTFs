package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.amap.apis.utils.core.crash.d;
import com.cdlinglu.utils.FlagUtil;
import com.loc.be;
import com.loc.s;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogProcessor.java */
/* loaded from: classes2.dex */
public abstract class ad {
    static final List<d.b> a = Collections.synchronizedList(new ArrayList());
    private s b;
    private int c;
    private bf d;
    private be e;

    /* compiled from: LogProcessor.java */
    /* loaded from: classes2.dex */
    public class a implements bf {
        private an b;

        a(an anVar) {
            ad.this = r1;
            this.b = anVar;
        }

        @Override // com.loc.bf
        public final void a(String str) {
            try {
                this.b.b(str, x.a(ad.this.b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public ad(int i) {
        this.c = i;
    }

    private static be a(Context context, String str) {
        try {
            File file = new File(x.a(context, str));
            if (file.exists() || file.mkdirs()) {
                return be.a(file, 20480L);
            }
            return null;
        } catch (IOException e) {
            w.a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable th) {
            w.a(th, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    private bf a(an anVar) {
        try {
            if (this.d == null) {
                this.d = new a(anVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.d;
    }

    private static String a(Throwable th) {
        try {
            return t.a(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    public static List<d.b> a() {
        return a;
    }

    private static void a(an anVar, String str, String str2, int i) {
        ao b = x.b(i);
        b.a(0);
        b.b(str);
        b.a(str2);
        anVar.a(b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<? extends ao> list, an anVar) {
        if (list != null && list.size() > 0) {
            for (ao aoVar : list) {
                if (c(aoVar.b())) {
                    anVar.a(aoVar.b(), (Class<? extends ao>) aoVar.getClass());
                } else {
                    aoVar.a(2);
                    anVar.b(aoVar);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, com.loc.an r13) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.loc.an):boolean");
    }

    public static boolean a(String[] strArr, String str) {
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

    private static String b(String str) {
        try {
            return m.c(t.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static boolean b(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : str.split("\n")) {
                if (a(strArr, str2.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean c(String str) {
        if (this.e == null) {
            return false;
        }
        try {
            return this.e.c(str);
        } catch (Throwable th) {
            w.a(th, "LogUpdateProcessor", "deleteLogData");
            return false;
        }
    }

    private static int d(String str) {
        int i = 0;
        y yVar = new y(t.c(t.a(str)));
        try {
            bi.a();
            byte[] a2 = bi.a(yVar);
            if (a2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(t.a(a2));
                    if (jSONObject.has("code")) {
                        i = jSONObject.getInt("code");
                    }
                } catch (JSONException e) {
                    w.a(e, "LogProcessor", "processUpdate");
                    i = 1;
                }
            }
        } catch (j e2) {
            if (e2.b() != 27) {
                i = 1;
            }
            w.a(e2, "LogProcessor", "processUpdate");
        }
        return i;
    }

    private static List<s> d(Context context) {
        Throwable th;
        List<s> list = null;
        try {
        } catch (Throwable th2) {
            th = th2;
            list = null;
        }
        synchronized (Looper.getMainLooper()) {
            try {
                list = new ap(context, false).a();
                try {
                    return list;
                } catch (Throwable th3) {
                    th = th3;
                    list = list;
                    try {
                        throw th;
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
                        return list;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
            }
        }
    }

    private static String e(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"").append(k.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(n.q(context)).append("\",\"pkg\":\"").append(k.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(k.b(context)).append("\",\"appversion\":\"").append(k.d(context)).append("\",\"sysversion\":\"").append(Build.VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            w.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }

    private String e(String str) {
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
                if (this.e == null) {
                    if (0 != 0) {
                        try {
                            byteArrayOutputStream3.close();
                        } catch (IOException e) {
                            w.a(e, "LogProcessor", "readLog1");
                        }
                    }
                    if (0 != 0) {
                        try {
                            inputStream2.close();
                        } catch (IOException e2) {
                            e = e2;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            w.a(e, str2, str3);
                            return str4;
                        }
                    }
                } else {
                    be.b a2 = this.e.a(str);
                    if (a2 == null) {
                        if (0 != 0) {
                            try {
                                byteArrayOutputStream3.close();
                            } catch (IOException e3) {
                                w.a(e3, "LogProcessor", "readLog1");
                            }
                        }
                        if (0 != 0) {
                            try {
                                inputStream2.close();
                            } catch (IOException e4) {
                                e = e4;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                w.a(e, str2, str3);
                                return str4;
                            }
                        }
                    } else {
                        inputStream = a2.a();
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
                                str4 = t.a(byteArrayOutputStream2.toByteArray());
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e5) {
                                        w.a(e5, "LogProcessor", "readLog1");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e6) {
                                        e = e6;
                                        str2 = "LogProcessor";
                                        str3 = "readLog2";
                                        w.a(e, str2, str3);
                                        return str4;
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                w.a(th, "LogProcessor", "readLog");
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e7) {
                                        w.a(e7, "LogProcessor", "readLog1");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e8) {
                                        e = e8;
                                        str2 = "LogProcessor";
                                        str3 = "readLog2";
                                        w.a(e, str2, str3);
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
                th = th3;
                byteArrayOutputStream = null;
                inputStream = null;
            }
            return str4;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static String f(Context context) {
        try {
            String e = e(context);
            if ("".equals(e)) {
                return null;
            }
            return m.b(t.a(e));
        } catch (Throwable th) {
            w.a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    protected String a(String str) {
        return p.c(str);
    }

    protected abstract String a(List<s> list);

    public final void a(Context context, Throwable th, String str, String str2) {
        String a2;
        List<s> d = d(context);
        if (d != null && d.size() != 0 && (a2 = a(th)) != null && !"".equals(a2)) {
            for (s sVar : d) {
                if (b(sVar.f(), a2)) {
                    a(sVar, context, th, a2.replaceAll("\n", "<br/>"), str, str2);
                    return;
                }
            }
            if (a2.contains("com.amap.api.col")) {
                try {
                    a(new s.a(FlagUtil.URL_SAVE, "1.0", "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a(), context, th, a2, str, str2);
                } catch (j e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected final void a(s sVar) {
        this.b = sVar;
    }

    public final void a(s sVar, Context context, String str, String str2, String str3, String str4) {
        this.b = sVar;
        String a2 = bt.a();
        String a3 = bt.a(context, sVar);
        k.a(context);
        if (str != null && !"".equals(str)) {
            int i = this.c;
            StringBuilder sb = new StringBuilder();
            if (str3 != null) {
                sb.append("class:").append(str3);
            }
            if (str4 != null) {
                sb.append(" method:").append(str4).append("$<br/>");
            }
            sb.append(str2);
            String a4 = a(str2);
            String a5 = bt.a(a3, a2, i, str, sb.toString());
            if (a5 != null && !"".equals(a5)) {
                String b = b(a5);
                String c = x.c(this.c);
                synchronized (Looper.getMainLooper()) {
                    an anVar = new an(context);
                    a(context, a4, c, b, anVar);
                    a(anVar, sVar.a(), a4, i);
                }
            }
        }
    }

    public final void a(s sVar, Context context, Throwable th, String str, String str2, String str3) {
        a(sVar, context, th.toString(), str, str2, str3);
    }

    protected abstract boolean a(Context context);

    protected final int b() {
        return this.c;
    }

    public final void b(Context context) {
        String a2;
        List<s> d = d(context);
        if (d != null && d.size() != 0 && (a2 = a(d)) != null && !"".equals(a2)) {
            String a3 = bt.a();
            String a4 = bt.a(context, this.b);
            k.a(context);
            int i = this.c;
            String a5 = bt.a(a4, a3, i, "ANR", a2);
            if (a5 != null && !"".equals(a5)) {
                String a6 = a(a2);
                String b = b(a5);
                String c = x.c(this.c);
                synchronized (Looper.getMainLooper()) {
                    an anVar = new an(context);
                    a(context, a6, c, b, anVar);
                    a(anVar, this.b.a(), a6, i);
                }
            }
        }
    }

    public final void c() {
        try {
            if (this.e != null && !this.e.b()) {
                this.e.close();
            }
        } catch (IOException e) {
            w.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable th) {
            w.a(th, "LogProcessor", "closeDiskLru");
        }
    }

    public final void c(Context context) {
        String sb;
        boolean z = false;
        try {
            if (a(context)) {
                synchronized (Looper.getMainLooper()) {
                    this.e = a(context, x.c(this.c));
                    an anVar = new an(context);
                    a(anVar.a(2, x.a(this.c)), anVar);
                    List<? extends ao> a2 = anVar.a(0, x.a(this.c));
                    if (!(a2 == null || a2.size() == 0)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("{\"pinfo\":\"").append(f(context)).append("\",\"els\":[");
                        z = true;
                        for (ao aoVar : a2) {
                            String e = e(aoVar.b());
                            if (e == null || "".equals(e)) {
                                z = z;
                            } else {
                                String str = e + "||" + aoVar.c();
                                if (!z) {
                                    sb2.append(",");
                                    z = z;
                                }
                                sb2.append("{\"log\":\"").append(str).append("\"}");
                            }
                        }
                        if (z) {
                            sb = null;
                        } else {
                            sb2.append("]}");
                            sb = sb2.toString();
                        }
                        if (sb != null) {
                            if (d(sb) == 1) {
                                int i = this.c;
                                a(a2, anVar);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            w.a(th, "LogProcessor", "processUpdateLog");
        }
    }
}
