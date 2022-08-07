package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.data.e;
import com.http.config.URLConfig;
import com.parse.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class w {
    private static final String[] z;
    private Context i;
    private int c = 2;
    private int d = 0;
    private long e = 0;
    private long f = 0;
    private long g = 0;
    private long h = 0;
    protected int a = 0;
    protected boolean b = false;
    private ArrayList<e> j = new ArrayList<>();

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
            case 1: goto L_0x004e;
            case 2: goto L_0x0056;
            case 3: goto L_0x005e;
            case 4: goto L_0x0066;
            case 5: goto L_0x006e;
            case 6: goto L_0x0076;
            case 7: goto L_0x007f;
            case 8: goto L_0x008a;
            case 9: goto L_0x0095;
            case 10: goto L_0x00a1;
            case 11: goto L_0x00ad;
            case 12: goto L_0x00b8;
            case 13: goto L_0x00c4;
            case 14: goto L_0x00d0;
            case 15: goto L_0x00db;
            case 16: goto L_0x00e7;
            case 17: goto L_0x00f3;
            case 18: goto L_0x00ff;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "料徶奈尊ｰ";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "刕伸料闱ｰ";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0016\u0011\u001av\u0002#\r\u0000b\r\u0019\u0013";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\b\u0018\u001f`";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0015\u0015\u0006h\u000f";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u001f\u000e\u0001q\u000f\u0012\u0015";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\b\b\u0002`";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\f\u0004\u001dl\u0005\u0018";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007f, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "敊剑杢劤乡厭捦事？";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u0011\u000e\u000b`";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0095, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "覟柱承劚";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a1, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "乶拄吇朚ｰ";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ad, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "\f\u0000\fn\u000f\b";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b8, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "覟柱奞贠ｰ";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c4, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "料徶符红ｰ";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d0, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0010\u0004\u0019`\u0006\u000f";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "麤诅卪夢履ｦP_7^L";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e7, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "彼妪覌枕DRO";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f3, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "乶拄橎弊ｰ";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ff, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.w.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0103, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0104, code lost:
        r9 = '|';
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0108, code lost:
        r9 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x010c, code lost:
        r9 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0110, code lost:
        r9 = 5;
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
            case 0: goto L_0x0104;
            case 1: goto L_0x0108;
            case 2: goto L_0x010c;
            case 3: goto L_0x0110;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'j';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.w.<clinit>():void");
    }

    private void a(String str) {
        int length = str.length();
        while (length > 0) {
            length--;
            switch (str.charAt(length)) {
                case 'd':
                    this.a |= 2;
                    break;
                case 'e':
                    this.a |= 16;
                    break;
                case 'i':
                    this.a |= 4;
                    break;
                case 'v':
                    this.a |= 1;
                    break;
                case ParseException.OPERATION_FORBIDDEN /* 119 */:
                    this.a |= 8;
                    break;
            }
        }
    }

    private void b() {
        JSONObject d = d();
        if (d != null) {
            ac.b(z[0], d.toString());
            ah.b(this.i, d);
        }
    }

    private void c() {
        this.b = false;
        this.d = 0;
        this.h = 0L;
        this.e = 0L;
        this.f = 0L;
        this.a = 0;
        this.g = 0L;
        this.c = 2;
        this.i = null;
        this.j.clear();
    }

    private JSONObject d() {
        int size;
        if (this.j != null && (size = this.j.size()) > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < size; i++) {
                jSONArray.put(this.j.get(i).b());
            }
            if (jSONArray.length() <= 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(z[6], jSONArray);
                jSONObject.put(z[4], z[3]);
                jSONObject.put(z[5], a.m());
                return jSONObject;
            } catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a() {
        if (!this.b) {
            return;
        }
        if (System.currentTimeMillis() - this.g >= this.h) {
            b();
            c();
        } else if (this.c == 1 && System.currentTimeMillis() - this.f >= this.e) {
            synchronized (this.j) {
                b();
                this.j.clear();
                this.d = 0;
                this.f = System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Context context, String str) {
        ac.b(z[0], z[9] + str);
        ac.b(z[0], z[18]);
        try {
            if (this.b) {
                this.b = false;
                b();
                c();
            }
            this.i = context;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(z[10]);
            String string2 = jSONObject.getString(z[16]);
            if (string != null) {
                if (string.equals(z[13])) {
                    this.c = 2;
                } else if (string.equals(z[8])) {
                    this.c = 1;
                }
            }
            a(string2);
            this.h = jSONObject.getLong(z[7]) * 1000;
            ac.b(z[0], z[19] + string);
            ac.b(z[0], z[15] + string2);
            if (this.c == 1) {
                this.e = jSONObject.getLong(z[8]) * 1000;
                this.f = System.currentTimeMillis();
                ac.b(z[0], z[12] + (this.e / 1000) + URLConfig.baidu_url);
                if (this.h < 300000) {
                    this.c = 2;
                }
            } else {
                ac.b(z[0], z[17]);
            }
            this.g = System.currentTimeMillis();
            this.b = true;
            ac.b(z[0], z[11]);
        } catch (JSONException e) {
            c();
            ac.b(z[0], z[14] + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(e eVar) {
        if (this.b) {
            synchronized (this.j) {
                this.d += eVar.a();
                ac.b(z[0], z[1] + eVar.a());
                ac.b(z[0], z[2] + ((this.h - (System.currentTimeMillis() - this.g)) / 1000) + URLConfig.baidu_url);
                switch (this.c) {
                    case 1:
                        if (System.currentTimeMillis() - this.g < this.h) {
                            if (System.currentTimeMillis() - this.f > this.e) {
                                synchronized (this.j) {
                                    b();
                                    this.j.clear();
                                    this.d = 0;
                                    this.f = System.currentTimeMillis();
                                }
                            }
                            this.j.add(eVar);
                            break;
                        } else {
                            b();
                            c();
                            break;
                        }
                    case 2:
                        if (System.currentTimeMillis() - this.g < this.h) {
                            if (this.d >= 10240) {
                                b();
                                this.j.clear();
                                this.d = eVar.a();
                            }
                            this.j.add(eVar);
                            break;
                        } else {
                            b();
                            c();
                            break;
                        }
                }
            }
        }
    }
}
