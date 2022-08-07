package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.jpush.android.b.a.a;
import cn.jpush.android.b.a.f;
import cn.jpush.android.data.c;
import cn.jpush.android.data.m;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import java.io.File;

/* loaded from: classes.dex */
public class PopWinActivity extends Activity {
    public static f a;
    private static final String[] z;
    private String b;
    private WebView c;
    private c d = null;

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
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006d;
            case 6: goto L_0x0075;
            case 7: goto L_0x007e;
            case 8: goto L_0x0088;
            case 9: goto L_0x0093;
            case 10: goto L_0x009e;
            case 11: goto L_0x00a9;
            case 12: goto L_0x00b5;
            case 13: goto L_0x00c0;
            case 14: goto L_0x00cc;
            case 15: goto L_0x00d7;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "{~1\u001c2`Y?\u0016\u001bim1,#a\u007f7\u000b\u000e";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "dz)\u0001$|";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "Xn#\u0006\u0010ko9\u00188|bp\t4|;\u001e;\u001dD;9\u0000%mu$O";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "a\u007f";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "Kz>N?gop\t4|;'\u000b3^r5\u0019qaup\u00020qt%\u001aqnr<\u000bp";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "bk%\u001d9Wk?\u001e&au\u000f\u00020qt%\u001a";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "Xw5\u000f\"m;1\n5(w1\u0017>}op\u001c4{t%\u001c2m;:\u001e${s\u000f\u001e>xl9\u0000\u000edz)\u0001$|5(\u0003=(o?N#mh\u007f\u00020qt%\u001aq)";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "Mc$\u001c0(\u007f1\u001a0(r#N?gop\u001d4zr1\u00028rz2\u00024)";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "nr<\u000bk'4";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "BK%\u001d9_~2";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "Xw5\u000f\"m;%\u001d4(\u007f5\b0}w$N2g\u007f5N8f;:\u001e${s\u000f\u001e>xl9\u0000\u000edz)\u0001$|5(\u0003=)";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009e, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "_z\"\u00008f|｜\u0000$dwp\u00034{h1\t4(~>\u001a8|bqN\u0012dt#\u000bqXn#\u0006\u0010ko9\u00188|bq";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "{s?\u0019\u0004zwpSq";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "jt4\u0017";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c0, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u007fm\u0000\u0001!\u007fr>";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cc, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "ni?\u0003\u000e\u007fz)";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
        r3[r2] = r1;
        cn.jpush.android.ui.PopWinActivity.z = r3;
        cn.jpush.android.ui.PopWinActivity.a = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00de, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00df, code lost:
        r9 = '\b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e3, code lost:
        r9 = 27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e7, code lost:
        r9 = 'P';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00eb, code lost:
        r9 = 'n';
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
            case 0: goto L_0x00df;
            case 1: goto L_0x00e3;
            case 2: goto L_0x00e7;
            case 3: goto L_0x00eb;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'Q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.PopWinActivity.<clinit>():void");
    }

    public final void a(String str) {
        if (this.d != null && this.c != null && (this.d instanceof m)) {
            if (!ao.a(str)) {
                ((m) this.d).a = str;
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra(z[14], this.d);
                intent.putExtra(z[16], true);
                intent.setFlags(335544320);
                startActivity(intent);
            }
            finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        k.a(this.b, 1006, this);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                this.d = (c) getIntent().getSerializableExtra(z[14]);
                if (this.d != null) {
                    this.b = this.d.c;
                    int identifier = getResources().getIdentifier(z[6], z[2], getPackageName());
                    if (identifier == 0) {
                        ac.e(z[0], z[7]);
                        finish();
                    } else {
                        setContentView(identifier);
                        int identifier2 = getResources().getIdentifier(z[15], z[4], getPackageName());
                        if (identifier2 == 0) {
                            ac.e(z[0], z[11]);
                            finish();
                        } else {
                            this.c = (WebView) findViewById(identifier2);
                            if (this.c == null) {
                                ac.e(z[0], z[5]);
                                finish();
                            } else {
                                this.c.setScrollbarFadingEnabled(true);
                                this.c.setScrollBarStyle(33554432);
                                WebSettings settings = this.c.getSettings();
                                settings.setDomStorageEnabled(true);
                                b.a(settings);
                                this.c.setBackgroundColor(0);
                                a = new f(this, this.d);
                                this.c.removeJavascriptInterface(z[1]);
                                this.c.setWebChromeClient(new a(z[10], cn.jpush.android.b.a.b.class, null, null));
                                this.c.setWebViewClient(new c(this.d));
                                cn.jpush.android.b.a.b.setWebViewHelper(a);
                            }
                        }
                    }
                    m mVar = (m) this.d;
                    String str = mVar.L;
                    String str2 = mVar.a;
                    new StringBuilder(z[13]).append(str2);
                    ac.b();
                    if (TextUtils.isEmpty(str) || !new File(str.replace(z[9], "")).exists()) {
                        this.c.loadUrl(str2);
                    } else {
                        this.c.loadUrl(str);
                    }
                    k.a(this.b, 1000, this);
                    return;
                }
                ac.d(z[0], z[12]);
                finish();
            } catch (Exception e) {
                ac.e(z[0], z[8]);
                e.printStackTrace();
                finish();
            }
        } else {
            ac.d(z[0], z[3]);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.c != null) {
            this.c.removeAllViews();
            this.c.destroy();
            this.c = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.c != null) {
            this.c.onPause();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.onResume();
            cn.jpush.android.b.a.b.setWebViewHelper(a);
        }
    }
}
