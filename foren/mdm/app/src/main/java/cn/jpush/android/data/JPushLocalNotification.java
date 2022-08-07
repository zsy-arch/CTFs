package cn.jpush.android.data;

import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JPushLocalNotification implements Serializable {
    private static final String[] z;
    private String f;
    private String g;
    private String h;
    private long i;
    private int a = 1;
    private String b = "";
    private String c = z[14];
    private String d = z[14];
    private long e = 0;
    private long j = 1;
    private int k = 1;
    private String l = "";
    private String m = "";

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
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0015AU\u0002\u0007\u0018Zd\u0002\u001b\u0006K";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0018q^\u000e\u0016\u0004OH";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0018AO\u001f\u0004\u001fMZ\u001f\r\u0018qO\u000f\u0012\u0013";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0018qX\u0019\f\u0002KU\u0002";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0018qY\u0003\u000b\u001aJ^\u0004=\u001fJ";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0005FT\u0001=\u0002WK\u0013";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0018qT\u0018\u000e\u000f";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0018qO\u001f\u0016\u001aK";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\u0017Jd\u0002";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "\u001bqX\u0019\f\u0002KU\u0002";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0019X^\u0004\u0010\u001fJ^)\u000f\u0005Id\u001f\u0006";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "%KOV\u0016\u001fC^V\u0004\u0017GWWB&B^\u0017\u0011\u0013\u000eX\u001e\u0007\u0015E\u001b\u000f\r\u0003\\\u001b\u0017\u0010\u0011]\u001a";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "<~N\u0005\n:AX\u0017\u000e8AO\u001f\u0004\u001fMZ\u0002\u000b\u0019@";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "F\u001e";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.JPushLocalNotification.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c2, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c3, code lost:
        r9 = 'v';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c7, code lost:
        r9 = '.';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00cb, code lost:
        r9 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cf, code lost:
        r9 = 'v';
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
            case 0: goto L_0x00c3;
            case 1: goto L_0x00c7;
            case 2: goto L_0x00cb;
            case 3: goto L_0x00cf;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.JPushLocalNotification.<clinit>():void");
    }

    private static void a(String str, String str2, JSONObject jSONObject) {
        if (!ao.a(str2)) {
            jSONObject.put(str, str2);
        }
    }

    public boolean equals(Object obj) {
        return this.j == ((JPushLocalNotification) obj).j;
    }

    public long getBroadcastTime() {
        return this.e;
    }

    public long getBuilderId() {
        return this.i;
    }

    public String getContent() {
        return this.f;
    }

    public String getExtras() {
        return this.h;
    }

    public long getNotificationId() {
        return this.j;
    }

    public String getTitle() {
        return this.g;
    }

    public int hashCode() {
        return new StringBuilder().append(this.j).toString().hashCode();
    }

    public void setBroadcastTime(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i < 0 || i2 <= 0 || i2 > 12 || i3 <= 0 || i3 > 31 || i4 < 0 || i4 > 23 || i5 < 0 || i5 > 59 || i6 < 0 || i6 > 59) {
            ac.e(z[13], z[12]);
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3, i4, i5, i6);
        Date time = instance.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (time.getTime() < currentTimeMillis) {
            this.e = currentTimeMillis;
        } else {
            this.e = time.getTime();
        }
    }

    public void setBroadcastTime(long j) {
        this.e = j;
    }

    public void setBroadcastTime(Date date) {
        this.e = date.getTime();
    }

    public void setBuilderId(long j) {
        this.i = j;
    }

    public void setContent(String str) {
        this.f = str;
    }

    public void setExtras(String str) {
        this.h = str;
    }

    public void setNotificationId(long j) {
        this.j = j;
    }

    public void setTitle(String str) {
        this.g = str;
    }

    public String toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (!ao.a(this.h)) {
                jSONObject2.put(z[2], new JSONObject(this.h));
            }
            a(z[4], this.f, jSONObject2);
            a(z[8], this.g, jSONObject2);
            a(z[4], this.f, jSONObject2);
            jSONObject2.put(z[9], 0);
            jSONObject.put(z[10], jSONObject2);
            a(z[0], new StringBuilder().append(this.j).toString(), jSONObject);
            a(z[1], this.m, jSONObject);
            a(z[11], this.l, jSONObject);
            jSONObject.put(z[7], this.k);
            jSONObject.put(z[5], this.i);
            jSONObject.put(z[6], 3);
            jSONObject.put(z[3], 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
