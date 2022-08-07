package cn.jpush.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PointerIconCompat;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.jpush.android.e;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class c extends WebViewClient {
    private static final String[] z;
    private final cn.jpush.android.data.c a;

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
            case 1: goto L_0x004d;
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006e;
            case 6: goto L_0x0076;
            case 7: goto L_0x007f;
            case 8: goto L_0x0089;
            case 9: goto L_0x0094;
            case 10: goto L_0x009f;
            case 11: goto L_0x00aa;
            case 12: goto L_0x00b5;
            case 13: goto L_0x00c0;
            case 14: goto L_0x00cb;
            case 15: goto L_0x00d7;
            case 16: goto L_0x00e2;
            case 17: goto L_0x00ed;
            case 18: goto L_0x00f8;
            case 19: goto L_0x0103;
            case 20: goto L_0x010e;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "dO~\u001c";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "xR~\u0000\u00131";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "3_c\u001e\u0013oO7\n\u0017`Ho";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "mUn\u001e\u0019e_$\u0005\u0018x^d\u0018XiC~\u001e\u0017\"oO4\"";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "mUn\u001e\u0019e_$\u0005\u0018x^d\u0018XmX~\u0005\u0019b\u0015\\%3[";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "|Wk\u0005\u0018#Oo\u0014\u0002";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "YIcVV";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "mNn\u0005\u0019#\u0011";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007f, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "\"\bm\u001c";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0089, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "mUn\u001e\u0019e_$\u0005\u0018x^d\u0018XmX~\u0005\u0019b\u0015Y)8H";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0094, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "w\u0019\u007f\u001e\u001a.\u0001(I\u0005.F";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009f, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "\"Vz_";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00aa, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "aZc\u0000\u0002c";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "YIfL\u0000mNf\tVeH*V";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c0, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\"VzX";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cb, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "zRn\t\u0019#\u0011";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "*Xe\u0002\u0002iU~Q";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e2, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "mUn\u001e\u0019e_$\u0005\u0018x^d\u0018XiC~\u001e\u0017\"~G-?@";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ed, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "*_c\u001e\u0013oO7\n\u0017`Ho";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f8, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "mUn\u001e\u0019e_$\u0005\u0018x^d\u0018XiC~\u001e\u0017\"h_.<Ix^";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0103, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "]No\u001e\u000f_Ox\u0005\u0018k\u0001*";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010e, code lost:
        r3[r2] = r1;
        cn.jpush.android.ui.c.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0112, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0113, code lost:
        r9 = '\f';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0117, code lost:
        r9 = ';';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011b, code lost:
        r9 = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011f, code lost:
        r9 = 'l';
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
            case 0: goto L_0x0113;
            case 1: goto L_0x0117;
            case 2: goto L_0x011b;
            case 3: goto L_0x011f;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'v';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.c.<clinit>():void");
    }

    public c(cn.jpush.android.data.c cVar) {
        this.a = cVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String[] split;
        Context context = webView.getContext();
        new StringBuilder(z[14]).append(str);
        ac.c();
        try {
            String format = String.format(z[11], str);
            if (this.a.y) {
                context.startActivity(new Intent(z[5], Uri.parse(str)));
                k.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, e.e);
                return true;
            } else if (str.endsWith(z[12])) {
                Intent intent = new Intent(z[5]);
                intent.setDataAndType(Uri.parse(str), z[8]);
                webView.getContext().startActivity(intent);
                return true;
            } else if (str.endsWith(z[15]) || str.endsWith(z[9])) {
                Intent intent2 = new Intent(z[5]);
                intent2.setDataAndType(Uri.parse(str), z[16]);
                webView.getContext().startActivity(intent2);
                return true;
            } else {
                if (str.startsWith(z[1])) {
                    webView.loadUrl(str);
                    k.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, e.e);
                } else if (str != null && str.startsWith(z[13])) {
                    if (str.lastIndexOf(z[0]) < 0 && !str.startsWith(z[13])) {
                        str = str.indexOf("?") > 0 ? str + z[19] : str + z[3];
                        str.lastIndexOf(z[0]);
                    }
                    int indexOf = str.indexOf("?");
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf);
                    new StringBuilder(z[7]).append(substring);
                    ac.a();
                    new StringBuilder(z[21]).append(substring2);
                    ac.a();
                    Intent intent3 = null;
                    if (substring.startsWith(z[13]) && (split = substring.split(":")) != null && split.length == 2) {
                        int indexOf2 = substring2.indexOf(z[17]);
                        String substring3 = substring2.substring(substring2.indexOf(z[2]) + 6, indexOf2);
                        String substring4 = substring2.substring(indexOf2 + 9);
                        String[] strArr = {split[1]};
                        intent3 = new Intent(z[10]);
                        intent3.setType(z[6]);
                        intent3.putExtra(z[18], strArr);
                        intent3.putExtra(z[20], substring3);
                        intent3.putExtra(z[4], substring4);
                    }
                    if (intent3 != null) {
                        context.startActivity(intent3);
                    }
                    k.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, e.e);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            ac.e();
            return true;
        }
    }
}
