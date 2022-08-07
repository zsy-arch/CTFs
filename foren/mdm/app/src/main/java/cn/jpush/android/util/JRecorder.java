package cn.jpush.android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.jpush.android.a;
import com.http.config.URLConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JRecorder {
    private static ExecutorService a;
    private static Context c;
    private static Handler d;
    private static ArrayList<aa> e;
    private static volatile boolean f;
    private static final String[] z;
    private ArrayList<ab> b;

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
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001ekpD\u000f\bQgR\r\t";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0006|vH\u0012\u001ejvY";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u001ekpD\u000f\b{}B\t\u001f";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\b{aJ\t\u0005a}";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001eo}L\u0018";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0003xvY\u0011\rw";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0005zzF\u0018";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0005`pY\u0018\rcvE\t";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u001ekcD\u000f\u0018.wB\u000e\rl\u007fN\u0019Llj\u000bG";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u001ekcD\u000f\u0018.vE\u001c\u000ebvO]V";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u001ekpD\u000f\b~vY\u0014\u0003j";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.JRecorder.z = r3;
        cn.jpush.android.util.JRecorder.a = java.util.concurrent.Executors.newSingleThreadExecutor();
        cn.jpush.android.util.JRecorder.d = null;
        cn.jpush.android.util.JRecorder.e = new java.util.ArrayList<>();
        cn.jpush.android.util.JRecorder.f = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b4, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
        r9 = 'l';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b9, code lost:
        r9 = 14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00bd, code lost:
        r9 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c1, code lost:
        r9 = '+';
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
            case 0: goto L_0x00b5;
            case 1: goto L_0x00b9;
            case 2: goto L_0x00bd;
            case 3: goto L_0x00c1;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '}';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.JRecorder.<clinit>():void");
    }

    private JRecorder() {
        if (d == null) {
            d = new Handler(Looper.getMainLooper());
        }
    }

    private JRecorder(int i, Context context) {
        this();
        c = context.getApplicationContext();
        this.b = new ArrayList<>();
        aa aaVar = new aa(this, (byte) 0);
        aaVar.a = i;
        aaVar.b = this.b;
        e.add(aaVar);
    }

    private static JSONObject a(ArrayList<ab> arrayList) {
        int size;
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        long j = arrayList.get(size - 1).a - arrayList.get(0).a;
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i = (int) (i + arrayList.get(i2).b);
        }
        jSONObject.put(z[1], z[8]);
        jSONObject.put(z[4], j);
        jSONObject.put(z[5], i - arrayList.get(0).b);
        return jSONObject;
    }

    public static /* synthetic */ void a(Context context) {
        JSONArray jSONArray;
        JSONObject jSONObject;
        try {
            if (e == null || e.size() <= 0) {
                jSONArray = null;
            } else {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<aa> it = e.iterator();
                while (it.hasNext()) {
                    aa next = it.next();
                    if (next.a == 0) {
                        jSONArray2.put(a(next.b));
                    } else if (next.a == 1) {
                        ArrayList<ab> arrayList = next.b;
                        if (arrayList == null) {
                            jSONObject = null;
                        } else {
                            int size = arrayList.size();
                            if (size <= 0) {
                                jSONObject = null;
                            } else {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put(z[1], z[6]);
                                jSONObject2.put(z[4], arrayList.get(size - 1).a - arrayList.get(0).a);
                                jSONObject2.put(z[5], arrayList.get(size - 1).b - arrayList.get(0).b);
                                jSONObject = jSONObject2;
                            }
                        }
                        jSONArray2.put(jSONObject);
                    }
                }
                b();
                jSONArray = jSONArray2;
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(z[0], z[2]);
                jSONObject3.put(z[7], a.m());
                jSONObject3.put(z[3], jSONArray);
                a.execute(new z(jSONObject3));
            }
        } catch (JSONException e2) {
        }
    }

    public static /* synthetic */ void a(JRecorder jRecorder, double d2) {
        synchronized (jRecorder.b) {
            ab abVar = new ab(jRecorder, (byte) 0);
            abVar.b = d2;
            abVar.a = System.currentTimeMillis();
            jRecorder.b.add(abVar);
        }
    }

    private static void b() {
        Iterator<aa> it = e.iterator();
        while (it.hasNext()) {
            it.next().b.clear();
        }
        e.clear();
    }

    public static JRecorder getIncreamentsRecorder(Context context) {
        return new JRecorder(0, context);
    }

    public static JRecorder getSuperpositionRecorder(Context context) {
        return new JRecorder(1, context);
    }

    public static void parseRecordCommand(String str) {
        if (f) {
            ac.b();
            return;
        }
        try {
            int i = new JSONObject(str).getInt(z[11]);
            f = true;
            new StringBuilder(z[10]).append(i).append(URLConfig.baidu_url);
            ac.b();
            if (d == null) {
                d = new Handler(Looper.getMainLooper());
            }
            d.postDelayed(new y(), i * 1000);
        } catch (JSONException e2) {
            f = false;
            new StringBuilder(z[9]).append(e2.getMessage());
            ac.b();
        }
    }

    public void record(int i) {
        if (!f) {
            ac.b();
        } else {
            a.execute(new x(this, i));
        }
    }
}
