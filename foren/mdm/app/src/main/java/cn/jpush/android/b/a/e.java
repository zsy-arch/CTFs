package cn.jpush.android.b.a;

import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.api.p;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;

/* loaded from: classes.dex */
public final class e {
    private static final String[] z;
    private Context a;
    private WindowManager b;
    private WebView c;
    private ImageButton d;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u00160\u0005\u00155(\b\u001a\u0004\"1\u001e\u0013\u0003\u0006,,\u0001\"1)%\u0014\u00003.";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "$*\u0002\b?+i[L}e:\u0002\u0000\"1\b\u0015\u001593 \u0002\u0018\u0012<\u0007\u0017\f5hd[L}hd[\u000031 \u0000\b$<\u0007\u0017\f5esV";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "&'X\u000b 0:\u001eO1+-\u0004\u000e9!g7\"\u0004\f\u001f?5\t\u001a\u001973\u0011\b";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0011!\u0013A1&=\u001f\u0017910V\u000f1(,V\b#e'\u0003\r<e&\u0004A5(9\u0002\u0018|e\u000e\u001f\u00175e<\u0006O~";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "hd[L}e9\u0017\u00131(:V[p";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        cn.jpush.android.b.a.e.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
        r9 = 'E';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        r9 = 'I';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
        r9 = 'v';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
        r9 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'P';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u0011!\u0013A1&=\u001f\u0017910V\u000f1(,V\b#e \u0018\u00171) \u0012Mp\u0002 \u0000\u0004p09XO"
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0068;
                case 1: goto L_0x006b;
                case 2: goto L_0x006e;
                case 3: goto L_0x0071;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 80
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "\u00160\u0005\u00155(\b\u001a\u0004\"1\u001e\u0013\u0003\u0006,,\u0001\"1)%\u0014\u00003."
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "$*\u0002\b?+i[L}e:\u0002\u0000\"1\b\u0015\u001593 \u0002\u0018\u0012<\u0007\u0017\f5hd[L}hd[\u000031 \u0000\b$<\u0007\u0017\f5esV"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "&'X\u000b 0:\u001eO1+-\u0004\u000e9!g7\"\u0004\f\u001f?5\t\u001a\u001973\u0011\b"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u0011!\u0013A1&=\u001f\u0017910V\u000f1(,V\b#e'\u0003\r<e&\u0004A5(9\u0002\u0018|e\u000e\u001f\u00175e<\u0006O~"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "hd[L}e9\u0017\u00131(:V[p"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            cn.jpush.android.b.a.e.z = r4
            return
        L_0x0068:
            r9 = 69
            goto L_0x001f
        L_0x006b:
            r9 = 73
            goto L_0x001f
        L_0x006e:
            r9 = 118(0x76, float:1.65E-43)
            goto L_0x001f
        L_0x0071:
            r9 = 97
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.e.<clinit>():void");
    }

    public final void a(String str, String str2) {
        new StringBuilder(z[2]).append(str).append(z[5]).append(str2);
        ac.b();
        if (ao.a(str)) {
            ac.e(z[1], z[4]);
        }
        if (this.a != null) {
            try {
                Class<?> cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(this.a, cls);
                    intent.putExtra(z[3], str2);
                    intent.setFlags(268435456);
                    this.a.startActivity(intent);
                    ac.b();
                    p.a(this.b, this.c, this.d);
                }
            } catch (Exception e) {
                ac.e(z[1], z[0]);
            }
        }
    }
}
