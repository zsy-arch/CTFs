package cn.jpush.android.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import cn.jpush.android.a;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.b;
import cn.jpush.android.util.o;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class c implements Thread.UncaughtExceptionHandler {
    private static c b;
    private static final String[] z;
    private FileLock d;
    private Context f;
    private Lock c = new ReentrantLock();
    private Thread.UncaughtExceptionHandler e = null;
    public boolean a = true;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0045;
            case 1: goto L_0x004d;
            case 2: goto L_0x0056;
            case 3: goto L_0x005e;
            case 4: goto L_0x0067;
            case 5: goto L_0x006f;
            case 6: goto L_0x0077;
            case 7: goto L_0x0080;
            case 8: goto L_0x008a;
            case 9: goto L_0x0095;
            case 10: goto L_0x00a0;
            case 11: goto L_0x00ac;
            case 12: goto L_0x00b8;
            case 13: goto L_0x00c3;
            case 14: goto L_0x00ce;
            case 15: goto L_0x00d9;
            case 16: goto L_0x00e4;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "|(cSs}\u0012|Tvw";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "q9|Wu";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "{?tIxt\"rI";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "v(aM\u007fj&JNih(";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "{?tIxG!z]";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0067, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "l4e_";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "v(aM\u007fj&aC`}";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "]?gUb";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "u(fIq\u007f(";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "]5v_`l$zT";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0095, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "k9tY{l?tYu";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a0, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "{?tIxl$x_";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ac, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "{\"`Td";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b8, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "n(gIyw#vUt}";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c3, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "v8yV";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ce, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "n(gIyw#{[}}";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d9, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "u8a_h";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e4, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.c.z = r3;
        cn.jpush.android.api.c.b = new cn.jpush.android.api.c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ef, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f0, code lost:
        r9 = 24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f4, code lost:
        r9 = 'M';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f8, code lost:
        r9 = 21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00fc, code lost:
        r9 = ':';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x00f0;
            case 1: goto L_0x00f4;
            case 2: goto L_0x00f8;
            case 3: goto L_0x00fc;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.c.<clinit>():void");
    }

    private c() {
    }

    public static c a() {
        return b;
    }

    private JSONArray a(Context context, Throwable th, String str) {
        return a(context, g(context), th, str);
    }

    private JSONArray a(Context context, JSONArray jSONArray, Throwable th, String str) {
        PackageInfo packageInfo;
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        String str2 = str + th.toString();
        try {
            String[] split = str2.split(":");
            if (split.length > 1) {
                for (int length = split.length - 1; length >= 0; length--) {
                    if (split[length].endsWith(z[10]) || split[length].endsWith(z[8])) {
                        str2 = split[length];
                        break;
                    }
                    try {
                    } catch (PackageManager.NameNotFoundException e) {
                        return jSONArray;
                    } catch (JSONException e2) {
                        return jSONArray;
                    }
                }
            }
        } catch (NullPointerException e3) {
        } catch (PatternSyntaxException e4) {
        }
        JSONObject jSONObject = null;
        int i = 0;
        while (true) {
            if (i >= jSONArray.length()) {
                i = 0;
                break;
            }
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && stringWriter2.equals(optJSONObject.getString(z[11]))) {
                optJSONObject.put(z[13], optJSONObject.getInt(z[13]) + 1);
                optJSONObject.put(z[12], System.currentTimeMillis());
                jSONObject = optJSONObject;
                break;
            }
            jSONObject = null;
            i++;
        }
        if (jSONObject != null) {
            JSONArray a = a(jSONArray, i);
            a.put(jSONObject);
            return a;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(z[12], System.currentTimeMillis());
        jSONObject2.put(z[11], stringWriter2);
        jSONObject2.put(z[9], str2);
        jSONObject2.put(z[13], 1);
        if (!(this.f == null && context == null)) {
            jSONObject2.put(z[7], b.c(context));
        }
        if (!(this.f == null || (packageInfo = this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 1)) == null)) {
            String str3 = packageInfo.versionName == null ? z[15] : packageInfo.versionName;
            String sb = new StringBuilder().append(packageInfo.versionCode).toString();
            jSONObject2.put(z[16], str3);
            jSONObject2.put(z[14], sb);
        }
        jSONArray.put(jSONObject2);
        return jSONArray;
    }

    private static JSONArray a(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (i2 != i) {
                try {
                    jSONArray2.put(jSONArray.get(i2));
                } catch (JSONException e) {
                }
            }
        }
        return jSONArray2;
    }

    private static void a(Context context, JSONArray jSONArray) {
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null && jSONArray2.length() > 0 && context != null) {
            try {
                FileOutputStream openFileOutput = context.openFileOutput(z[0], 0);
                openFileOutput.write(jSONArray2.getBytes());
                openFileOutput.flush();
                openFileOutput.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e2) {
            }
        }
    }

    public void b() {
        if (this.d != null) {
            try {
                this.d.release();
            } catch (IOException e) {
            }
        }
        this.c.unlock();
    }

    public static void d(Context context) {
        if (context == null) {
            ac.d();
            return;
        }
        File file = new File(context.getFilesDir(), z[0]);
        if (file.exists()) {
            file.delete();
        }
    }

    public static JSONObject f(Context context) {
        JSONArray g = g(context);
        if (g == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[3], g);
            jSONObject.put(z[2], a.m());
            jSONObject.put(z[6], z[5]);
            jSONObject.put(z[4], b.c(context));
            JSONObject jSONObject2 = new JSONObject(o.a(context));
            if (jSONObject2 == null || jSONObject2.length() <= 0) {
                return jSONObject;
            }
            jSONObject.put(z[1], jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return jSONObject;
        } catch (Exception e2) {
            return jSONObject;
        }
    }

    private static JSONArray g(Context context) {
        JSONArray jSONArray;
        StringBuffer stringBuffer;
        if (!new File(context.getFilesDir(), z[0]).exists()) {
            return null;
        }
        try {
            FileInputStream openFileInput = context.openFileInput(z[0]);
            byte[] bArr = new byte[1024];
            stringBuffer = new StringBuffer();
            while (true) {
                int read = openFileInput.read(bArr);
                if (read == -1) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
        } catch (Exception e) {
        }
        if (stringBuffer.toString().length() > 0) {
            jSONArray = new JSONArray(stringBuffer.toString());
            return jSONArray;
        }
        jSONArray = null;
        return jSONArray;
    }

    public boolean h(Context context) {
        if (context == null) {
            ac.d();
            return false;
        } else if (!this.c.tryLock()) {
            return false;
        } else {
            try {
                this.d = context.openFileOutput(z[17], 0).getChannel().tryLock();
                return this.d != null;
            } catch (IOException e) {
                return false;
            }
        }
    }

    public final void a(Context context) {
        this.f = context;
        if (this.e == null) {
            this.e = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public final void a(Throwable th, String str) {
        if (this.a && this.f != null) {
            JSONArray a = a(this.f, th, str);
            d(this.f);
            a(this.f, a);
        }
    }

    public final void b(Context context) {
        this.f = context;
        this.a = true;
    }

    public final void c(Context context) {
        this.f = context;
        this.a = false;
    }

    public final void e(Context context) {
        if (context == null) {
            ac.d();
        } else if (!a.z()) {
        } else {
            if (h(context)) {
                JSONObject f = f(context);
                if (f != null) {
                    ah.a(context, f);
                    d(context);
                }
                b();
                return;
            }
            ac.a();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (this.a) {
            ac.a();
            JSONArray a = a(this.f, th, "");
            d(this.f);
            a(this.f, a);
            d dVar = new d(this);
            dVar.start();
            try {
                dVar.join(2000L);
            } catch (InterruptedException e) {
            }
        } else {
            ac.a();
        }
        if (this.e != this) {
            this.e.uncaughtException(thread, th);
        }
        throw new RuntimeException(th);
    }
}
