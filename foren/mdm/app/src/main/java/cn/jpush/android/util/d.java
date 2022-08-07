package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.service.o;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class d extends Thread {
    private static Object c;
    private static Object d;
    private static final String[] z;
    private String a;
    private Context b;

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
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "U\u001d^By\u0006\bA@LG\nZQ{C'P]yUS";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "S\u0007X^oR\b]\\yBIP@lv\bR[}A\f\u007fQqCS";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "t\fA_nR<BUns\u0019UQhC(A@o\u0006\u0006]T]V\u0019]YoRGBYfCA\u0018\n";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = ",IX^oR\b]\\yBIP@lv\bR[}A\f\u007fQqCS";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "E\b_\u0017h\u0006\u001bTQx\u0006\bA@LG\nZQ{C'P]yUE\u0011WuP\f\u0011El\u0006\u001bTQx\u0006S";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "E\b_\u0017h\u0006\f_SsB\u0000_W<G\u0019A`}E\u0002PWyh\b\\Uo\nIVYjCID@<T\fPT<\u001c";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "E\b_\u0017h\u0006\u0006AUr\u0006\bA@LG\nZQ{C'P]yUIX^lS\u001dbDnC\b\\\u001c<A\u0000GU<S\u0019\u0011ByG\r\u0011\u0010&";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "s=w\u001d$";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "G\u0019A`}E\u0002PWyh\b\\Uo";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "U\bGU<J\u0006V\u0010uHIFBuR\fyYoR\u0006CIPI\u000e\u000b:";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "`\u0000]UlG\u001dY\u0010yT\u001b^B<I\u000f\u0011k}V\u0019aQ\u007fM\bVURG\u0004TCA\u0006E\u0011WuP\f\u0011El\u0006\u001aPFy\u0006S";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "E\b_\u0017h\u0006\u001eCYhCIP@lv\bR[}A\f\u007fQqC\u001a\u0011\u001c<A\u0000GU<S\u0019\u0011C}P\f\u0011\n";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "E\b_\u0017h\u0006\f_SsB\u0000_W<G\u0019A`}E\u0002PWyh\b\\Uo\u0006E\u0011WuP\f\u0011El\u0006\u001aPFy\u0006S";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "E\b_\u0017h\u0006\u0006AUr\u0006\bA@LG\nZQ{C'P]yUI^EhV\u001cEchT\fP]0\u0006\u000eXFy\u0006\u001cA\u0010oG\u001fT\u0010&";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "E\u0001TSws\u001aTB]V\u0019B\u0010y^\fR@hO\u0006_\n";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "G\nEYsHIRXyE\u0002\u0011_pBIP@l\u0006\u0019PSwG\u000eT\u0010pO\u001aE\n";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.d.z = r3;
        cn.jpush.android.util.d.c = new java.lang.Object();
        cn.jpush.android.util.d.d = new java.lang.Object();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e6, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e7, code lost:
        r9 = '&';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00eb, code lost:
        r9 = 'i';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ef, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f3, code lost:
        r9 = '0';
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
            case 0: goto L_0x00e7;
            case 1: goto L_0x00eb;
            case 2: goto L_0x00ef;
            case 3: goto L_0x00f3;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.d.<clinit>():void");
    }

    public d(Context context, String str) {
        this.b = context;
        this.a = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String a(Context context) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        IOException e;
        FileNotFoundException e2;
        String str = null;
        try {
            if (context == null) {
                ac.d();
            } else {
                try {
                    fileInputStream2 = context.openFileInput(z[9]);
                    try {
                        byte[] bArr = new byte[fileInputStream2.available() + 1];
                        fileInputStream2.read(bArr);
                        ah.a(fileInputStream2);
                        try {
                            String str2 = new String(bArr, z[8]);
                            boolean a = ao.a(str2);
                            if (a != 0) {
                                ac.b();
                                fileInputStream = a;
                            } else {
                                str = str2;
                                fileInputStream = a;
                            }
                        } catch (UnsupportedEncodingException e3) {
                            StringBuilder sb = new StringBuilder(z[6]);
                            sb.append(e3.getMessage());
                            ac.b();
                            fileInputStream = sb;
                        }
                    } catch (FileNotFoundException e4) {
                        e2 = e4;
                        new StringBuilder(z[7]).append(e2.getMessage());
                        ac.b();
                        ah.a(fileInputStream2);
                        fileInputStream = fileInputStream2;
                        return str;
                    } catch (IOException e5) {
                        e = e5;
                        new StringBuilder(z[5]).append(e.getMessage());
                        ac.b();
                        ah.a(fileInputStream2);
                        fileInputStream = fileInputStream2;
                        return str;
                    }
                } catch (FileNotFoundException e6) {
                    e2 = e6;
                    fileInputStream2 = null;
                } catch (IOException e7) {
                    e = e7;
                    fileInputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = null;
                    ah.a(fileInputStream);
                    throw th;
                }
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static HashSet<String> a(String str) {
        if (str == null) {
            ac.a();
        }
        String[] split = str.replace("\u0000", "").split(z[0]);
        HashSet<String> hashSet = new HashSet<>();
        for (String str2 : split) {
            hashSet.add(str2);
        }
        return hashSet;
    }

    private void a(ArrayList<ad> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            ac.a();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            sb.append(arrayList.get(i).b);
            if (i != arrayList.size() - 1) {
                sb.append(z[0]);
            }
        }
        new StringBuilder(z[1]).append(sb.toString());
        ac.b();
        if (a(this.b, sb.toString())) {
            ac.b();
        }
    }

    private void a(HashSet<String> hashSet) {
        if (this.b == null) {
            ac.d();
        } else if (hashSet == null || hashSet.isEmpty()) {
            ac.d();
        } else {
            new StringBuilder(z[3]).append(hashSet.size());
            ac.b();
            JSONArray jSONArray = new JSONArray();
            ArrayList<ad> a = v.a(this.b, true);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a);
            Iterator<ad> it = a.iterator();
            while (it.hasNext()) {
                ad next = it.next();
                if (hashSet.remove(next.b)) {
                    arrayList.remove(next);
                }
                if (!ao.a(this.a) && next.b.equals(this.a)) {
                    arrayList.remove(next);
                }
            }
            if (!ao.a(this.a)) {
                hashSet.remove(this.a);
            }
            new StringBuilder(z[2]).append(hashSet.toString()).append(z[4]).append(arrayList.toString());
            ac.b();
            Iterator<String> it2 = hashSet.iterator();
            while (it2.hasNext()) {
                JSONObject b = o.b(it2.next());
                if (b != null) {
                    jSONArray.put(b);
                }
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                JSONObject a2 = o.a(((ad) it3.next()).b);
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            if (jSONArray.length() > 0) {
                ah.a(this.b, jSONArray);
            }
            if (jSONArray.length() > 0 || !ao.a(this.a)) {
                a(a);
            }
        }
    }

    private static boolean a(Context context, String str) {
        if (context == null) {
            ac.d();
            return false;
        }
        synchronized (d) {
            if (str != null) {
                try {
                    new StringBuilder(z[10]).append(str);
                    ac.a();
                } catch (Exception e) {
                    ac.h();
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    try {
                        fileOutputStream = context.openFileOutput(z[9], 0);
                        fileOutputStream.write(str.getBytes(z[8]));
                        ah.a(fileOutputStream);
                    } catch (NullPointerException e2) {
                        new StringBuilder(z[11]).append(e2.getMessage());
                        ac.b();
                        ah.a(fileOutputStream);
                        return false;
                    }
                } catch (UnsupportedEncodingException e3) {
                    new StringBuilder(z[13]).append(e3.getMessage());
                    ac.b();
                    ah.a(fileOutputStream);
                    return false;
                }
            } catch (FileNotFoundException e4) {
                new StringBuilder(z[14]).append(e4.getMessage());
                ac.b();
                ah.a(fileOutputStream);
                return false;
            } catch (IOException e5) {
                new StringBuilder(z[12]).append(e5.getMessage());
                ac.b();
                ah.a(fileOutputStream);
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            synchronized (c) {
                if (this.b == null) {
                    ac.d();
                    return;
                }
                String a = a(this.b);
                new StringBuilder(z[16]).append(a);
                ac.b();
                if (a == null) {
                    a(v.a(this.b, true));
                } else if (a.z()) {
                    a.e();
                    a(a(a));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new StringBuilder(z[15]).append(e.getMessage());
            ac.b();
        }
    }
}
