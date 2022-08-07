package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jpush.a.a.a.c;
import cn.jpush.a.a.a.g;
import cn.jpush.android.a;
import cn.jpush.android.e;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.helpers.j;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class k implements Runnable {
    public static AtomicLong a;
    public static AtomicBoolean b;
    private static final String[] z;
    private Context c;
    private Handler d;
    private volatile boolean f = false;
    private boolean e = false;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r5 != 0) goto L_0x002d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r5 > r6) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
        switch(r0) {
            case 0: goto L_0x0045;
            case 1: goto L_0x004d;
            case 2: goto L_0x0055;
            case 3: goto L_0x005d;
            case 4: goto L_0x0065;
            case 5: goto L_0x006d;
            case 6: goto L_0x0075;
            case 7: goto L_0x007e;
            case 8: goto L_0x0089;
            case 9: goto L_0x0094;
            case 10: goto L_0x009f;
            case 11: goto L_0x00ab;
            case 12: goto L_0x00b6;
            case 13: goto L_0x00c1;
            case 14: goto L_0x00cc;
            case 15: goto L_0x00d7;
            case 16: goto L_0x00e2;
            case 17: goto L_0x00ed;
            case 18: goto L_0x00f8;
            case 19: goto L_0x0103;
            case 20: goto L_0x010e;
            case 21: goto L_0x0119;
            default: goto L_0x003d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "u.\u0019+/Zm@b4F4>6/Dm@b#[#\u0003'#@$\u0002,z";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "f(\u000e')B(\tb%F?\u00020`F(\u001e2/Z>\bbm\u0014.\u0002&%\u000e";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "w?\b#4Q)M!/Z#\b!4]\"\u0003bm\u0014";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0018m\u001d)'\u000e";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "a#\u0005#.P!\b&`F(\u001e2/Z>\bb#[ \u0000#.Pm@b";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "Y\u000e\u0002,.Q.\u0019+/Zm\u00041`F(\u001e'4\u00149\u0002bp\u0014:\u0005'.\u0014#\b67[?\u0006b,]>\u0019'.]#\nl`v?\b#+\u0014#\u00025n";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "q5\u000e'0@$\u0002,`F(\u000e')B(\tl`z\"\u001ab\"F(\f)`\u0019m\u001f'4\u000e";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "v(\n+.\u00149\u0002b2A#M+.\u0014\u000e\u0002,.Q.\u0019+.S\u0019\u00050%U)Mo`])W";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007e, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "镋输揈女赥\u0018m\u000725G%C1/劔輰彯幺";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0089, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "u.\u0019+/Zm@b/Z\u0001\u0002%)Z\u000b\f+,Q)Mo`F(\u001e2\u0003[)\bx";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0094, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0018m\u000e-.Z(\u000e6)[#W";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009f, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "z(\u00195/F&M.)G9\b,)Z*Cln";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "u.\u0019+/Zm@b2Q.\b+6Q).--Y,\u0003&`\u0019m\u000e/$\u000e";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b6, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "W#C(0A>\u0005l)Yc\f,$F\"\u0004&nU.\u0019+/Zc$\u000f\u001ff\b>\u0012\u000fz\u001e(";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c1, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "v?\b#+\u0014?\b!%];\u0004,'\u0014/\u0014b7U#\u0019\u00114[=Mo`W\"\u0003,%W9\u0004-.\u000e";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cc, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "f(\u000e')B(\tb\"M9\b1`\u0019m\u0001'.\u000e";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "u.\u0019+/Zm@b/Z\u0001\u0002%'Q)$,`\u0019m\u000e-.Z(\u000e6)[#W";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e2, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0018m\b02[?W";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ed, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "a#\u0006,/C#M\u0003#_m\u001f'1A(\u001e6`\u0019m\u000e/$\u000e";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f8, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "] 20%G=\u0002,3Q";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0103, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "f(\u001972Zm\u0002$`w!\u00021%\u0014'\u0003+`W\"\u0003,%W9\u0004-.\u0014`M";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010e, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "u.\u0019+/Zm@b#X\"\u001e'\u0003[#\u0003'#@$\u0002,`\u0019m\u000e-.Z(\u000e6)[#W";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0119, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.k.z = r3;
        cn.jpush.android.service.k.a = new java.util.concurrent.atomic.AtomicLong(0);
        cn.jpush.android.service.k.b = new java.util.concurrent.atomic.AtomicBoolean(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x012e, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012f, code lost:
        r9 = '4';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0133, code lost:
        r9 = 'M';
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0137, code lost:
        r9 = 'm';
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x013b, code lost:
        r9 = 'B';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        if (r5 <= 1) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x012f;
            case 1: goto L_0x0133;
            case 2: goto L_0x0137;
            case 3: goto L_0x013b;
            default: goto L_0x001f;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r9 = '@';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.k.<clinit>():void");
    }

    public k(Context context, Handler handler, boolean z2) {
        this.c = context;
        this.d = handler;
    }

    private void c() {
        ac.b(z[0], z[22] + a.get());
        if (0 != a.get()) {
            try {
                b.set(true);
                a.set(PushProtocol.Close(a.get()));
                ac.b(z[0], z[21] + a.get());
                b.set(false);
            } catch (Exception e) {
                ac.h();
            }
            ConnectingHelper.sendConnectionToHandler(Message.obtain(this.d, 7301), a.get());
            return;
        }
        ac.b();
    }

    public final void a() {
        ac.b(z[0], z[1] + a.get());
        this.f = true;
        PushProtocol.Stop(a.get());
    }

    public final boolean b() {
        return this.f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ac.c(z[0], z[8] + Thread.currentThread().getId());
        try {
            a.set(PushProtocol.InitConn());
            new StringBuilder(z[3]).append(a.get());
            ac.b();
            SisInfo sendSis = ConnectingHelper.sendSis(this.c);
            if (sendSis != null) {
                sendSis.configure();
            }
            if (this.f) {
                ac.d();
                c();
            } else if (!ConnectingHelper.openConnection(this, this.c, a.get(), sendSis)) {
                c();
            } else {
                if (!a.z()) {
                    if (!ConnectingHelper.register(this.c, a.get(), this.e)) {
                        if (86400 == a.p()) {
                            ac.a();
                            this.d.sendEmptyMessageDelayed(1001, 100L);
                        } else {
                            ac.a();
                        }
                        c();
                        return;
                    } else if (ConnectingHelper.isServiceStopedByRegister()) {
                        ConnectingHelper.restoreRtcWhenRegisterSucceed();
                        this.d.sendEmptyMessageDelayed(1031, 100L);
                    }
                }
                int login = ConnectingHelper.login(this.c, a.get());
                if (login < 0) {
                    c();
                } else if (login > 0) {
                    long j = a.get();
                    new StringBuilder(z[10]).append(login);
                    ac.a();
                    ConnectingHelper.sendConnectionToHandler(Message.obtain(this.d, 7306), j);
                    c();
                } else if (0 != a.get()) {
                    long j2 = a.get();
                    ac.a(z[0], z[17] + j2);
                    ConnectingHelper.sendConnectionToHandler(Message.obtain(this.d, 7304), j2);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        if (!this.f) {
                            ac.b(z[0], z[12]);
                            int RecvPush = PushProtocol.RecvPush(a.get(), bArr, 86400);
                            ac.b(z[0], z[16] + RecvPush + z[11] + a.get() + z[4] + e.c);
                            if (0 == a.get()) {
                                ac.d(z[0], z[6]);
                                return;
                            } else if (RecvPush > 0) {
                                Context context = this.c;
                                byte[] bArr2 = new byte[RecvPush];
                                System.arraycopy(bArr, 0, bArr2, 0, RecvPush);
                                g a2 = c.a(bArr2);
                                if (a2 != null) {
                                    ac.b(z[0], z[13] + a2.d());
                                    ac.a(z[0], a2.toString());
                                    if (a2.g != 0) {
                                        ac.d(z[0], z[2] + a2.g + z[18] + a2.h);
                                    } else {
                                        switch (a2.d()) {
                                            case 3:
                                                j.a(context, this.d, a.get(), a2);
                                                continue;
                                            case 10:
                                                ac.d();
                                                continue;
                                            case 19:
                                                long j3 = a.get();
                                                int a3 = ((cn.jpush.a.a.a.a) a2).a();
                                                if (a3 != 2) {
                                                    if (a3 != 10) {
                                                        new StringBuilder(z[19]).append(a3);
                                                        ac.d();
                                                        break;
                                                    } else {
                                                        ac.b();
                                                        break;
                                                    }
                                                } else {
                                                    ac.b();
                                                    ConnectingHelper.sendConnectionToHandler(Message.obtain(this.d, 7303), j3);
                                                    continue;
                                                }
                                            case 25:
                                                j.b(context, this.d, a.get(), a2);
                                                continue;
                                            default:
                                                new StringBuilder(z[5]).append(a2.d());
                                                ac.d();
                                                continue;
                                        }
                                    }
                                } else if (100 == b.b(bArr2)) {
                                    b.a(context, z[14], z[20], bArr2);
                                } else {
                                    ac.e();
                                }
                            } else if (RecvPush == -994) {
                                ac.b();
                            } else {
                                ac.b(z[0], z[7] + RecvPush);
                            }
                        }
                    }
                    if (this.f) {
                        ac.b(z[0], z[15] + a.get());
                    }
                    c();
                } else {
                    ac.d();
                }
            }
        } catch (UnsatisfiedLinkError e) {
            ac.b(z[0], z[9], e);
        }
    }
}
