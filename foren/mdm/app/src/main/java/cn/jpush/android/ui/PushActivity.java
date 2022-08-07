package cn.jpush.android.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.jpush.android.api.n;
import cn.jpush.android.data.c;
import cn.jpush.android.data.m;
import cn.jpush.android.helpers.h;
import cn.jpush.android.helpers.k;
import cn.jpush.android.util.ac;
import java.io.File;

/* loaded from: classes.dex */
public class PushActivity extends Activity {
    private static final String[] z;
    private String c;
    private int a = 0;
    private boolean b = false;
    private FullScreenView d = null;
    private Handler e = new f(this);

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
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "R^\u0000\u0015'a_\u001a\u000b\u000fvR";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "VC\u0016]\u0016cH\u0018\u001c\u0001g\u000b\u0004\u0014\u0012j\u000b\u0007\u0015\u0003\"L\u001a\u000b\u0003l\u000b\u001d\u001c\u000bg\u000b\u0010\u001c\blD\u0007]\u0004g\u000b\u0015\u0012\u0013lOR";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "RG\u0016\u001c\u0015g\u000b\u0006\u000e\u0003\"O\u0016\u001b\u0007wG\u0007]\u0005mO\u0016]\u000fl\u000b\u0019\r\u0013qC,\n\u0003`]\u001a\u0018\u0011]G\u0012\u0004\tw_]\u0005\u000bn\n";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "RG\u0016\u001c\u0015g\u000b\u0012\u0019\u0002\"G\u0012\u0004\tw_S\u000f\u0003qD\u0006\u000f\u0005g\u000b\u0019\r\u0013qC,\n\u0003`]\u001a\u0018\u0011]G\u0012\u0004\tw_]\u0005\u000bn\u000b\u0007\u0012FpN\u0000R\ncR\u001c\b\u0012\"\n";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "h[\u0006\u000e\u000e]\\\u0016\u001f\u0010kN\u0004\"\ncR\u001c\b\u0012";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "cH\u0007\u0014\tlI\u0012\u000f*cR\u001c\b\u0012KO";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "kO";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "L^\u001f\u0011FoN\u0000\u000e\u0007eNS\u0018\bvB\u0007\u0004G\"h\u001f\u0012\u0015g\u000b#\b\u0015jj\u0010\t\u000ftB\u0007\u0004G";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "dB\u001f\u0018\\-\u0004";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "nJ\n\u0012\u0013v";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "vD\u0003<\u0005vB\u0005\u0014\u0012{\u000bS@F";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "EN\u0007]\u0014wE\u001d\u0014\be\u000b\u0007\u001c\u0015iXS\u001b\u0007kG\u0016\u0019H";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "cH\u0007\u0014\u0010k_\n";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "`J\u0000\u0018'a_\u001a\u000b\u000fvRS][\"";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "`D\u0017\u0004";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "dY\u001c\u00109uJ\n";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "R^\u0000\u0015'a_\u001a\u000b\u000fvRS\u001a\u0003v\u000b=(*N\u000b\u001a\u0013\u0012gE\u0007\\";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "GS\u0007\u000f\u0007\"O\u0012\t\u0007\"B\u0000]\bm_S\u000e\u0003pB\u0012\u0011\u000fxJ\u0011\u0011\u0003#";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "KE\u0005\u001c\nkOS\u0010\u0015e\u000b\u0007\u0004\u0016g\u000b\u0007\u0012FqC\u001c\nF/\u000b";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "UJ\u0001\u0013\u000flLｿ\u0013\u0013nGS\u0010\u0003qX\u0012\u001a\u0003\"N\u001d\t\u000fvRR]%nD\u0000\u0018FR^\u0000\u0015'a_\u001a\u000b\u000fvRR";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        cn.jpush.android.ui.PushActivity.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0104, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0105, code lost:
        r9 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0108, code lost:
        r9 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010c, code lost:
        r9 = 's';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0110, code lost:
        r9 = '}';
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
            case 0: goto L_0x0105;
            case 1: goto L_0x0108;
            case 2: goto L_0x010c;
            case 3: goto L_0x0110;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'f';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.PushActivity.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(PushActivity pushActivity, c cVar) {
        ac.c();
        if (cVar == null) {
            ac.d(z[1], z[8]);
            pushActivity.finish();
        }
        m mVar = (m) cVar;
        if (mVar.H == 0) {
            pushActivity.a = mVar.F;
            int identifier = pushActivity.getResources().getIdentifier(z[5], z[10], pushActivity.getPackageName());
            if (identifier == 0) {
                ac.e(z[1], z[4]);
                pushActivity.finish();
                return;
            }
            pushActivity.setContentView(identifier);
            String str = mVar.a;
            if (!h.a(str)) {
                n.a(pushActivity, cVar, 0);
                pushActivity.finish();
                return;
            }
            String str2 = mVar.L;
            if (mVar.p) {
                int identifier2 = pushActivity.getResources().getIdentifier(z[6], z[7], pushActivity.getPackageName());
                if (identifier2 == 0) {
                    ac.e(z[1], z[3]);
                    pushActivity.finish();
                    return;
                }
                pushActivity.d = (FullScreenView) pushActivity.findViewById(identifier2);
                pushActivity.d.initModule(pushActivity, cVar);
                if (TextUtils.isEmpty(str2) || !new File(str2.replace(z[9], "")).exists() || pushActivity.b) {
                    pushActivity.d.loadUrl(str);
                } else {
                    pushActivity.d.loadUrl(str2);
                }
            }
            if (!pushActivity.b) {
                k.a(pushActivity.c, 1000, pushActivity);
            }
        }
    }

    private void c() {
        PackageManager packageManager = getPackageManager();
        String packageName = getApplicationContext().getPackageName();
        if (!packageName.isEmpty()) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
            if (launchIntentForPackage == null) {
                ac.d(z[1], z[0]);
                return;
            }
            launchIntentForPackage.addFlags(268468224);
            startActivity(launchIntentForPackage);
            return;
        }
        ac.d(z[1], z[2]);
    }

    public final void a() {
        if (this.d != null) {
            this.d.showTitleBar();
        }
    }

    public final void b() {
        if (1 == this.a) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService(z[13]);
                ComponentName componentName = activityManager.getRunningTasks(1).get(0).baseActivity;
                ComponentName componentName2 = activityManager.getRunningTasks(1).get(0).topActivity;
                new StringBuilder(z[14]).append(componentName.toString());
                ac.b();
                new StringBuilder(z[11]).append(componentName2.toString());
                ac.b();
                if (!(componentName == null || componentName2 == null || !componentName2.toString().equals(componentName.toString()))) {
                    c();
                }
            } catch (Exception e) {
                ac.d(z[1], z[12]);
                c();
            }
            finish();
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.d == null || !this.d.webviewCanGoBack()) {
            k.a(this.c, 1006, this);
            b();
            return;
        }
        this.d.webviewGoBack();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                this.b = getIntent().getBooleanExtra(z[16], false);
                c cVar = (c) getIntent().getSerializableExtra(z[15]);
                if (cVar != null) {
                    this.c = cVar.c;
                    if (cVar != null) {
                        switch (cVar.o) {
                            case 0:
                                Message message = new Message();
                                message.what = 1;
                                message.obj = cVar;
                                this.e.sendMessageDelayed(message, 500L);
                                break;
                            default:
                                new StringBuilder(z[19]).append(cVar.o);
                                ac.d();
                                n.a(this, cVar, 0);
                                finish();
                                break;
                        }
                    } else {
                        ac.d(z[1], z[8]);
                        finish();
                    }
                } else {
                    ac.d(z[1], z[20]);
                    finish();
                }
            } catch (Exception e) {
                ac.e(z[1], z[18]);
                e.printStackTrace();
                finish();
            }
        } else {
            ac.d(z[1], z[17]);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.d != null) {
            this.d.destory();
        }
        if (this.e.hasMessages(2)) {
            this.e.removeMessages(2);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.d != null) {
            this.d.pause();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.d != null) {
            this.d.resume();
        }
    }
}
