package cn.jpush.android.service;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import cn.jpush.android.a;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.api.b;
import cn.jpush.android.api.n;
import cn.jpush.android.data.c;
import cn.jpush.android.e;
import cn.jpush.android.helpers.d;
import cn.jpush.android.util.ac;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ServiceInterface {
    private static boolean a;
    private static final String[] z;

    /* loaded from: classes.dex */
    public class TagAliasOperator extends BroadcastReceiver {
        private static TagAliasOperator a;
        private static Object c;
        private static final String[] z;
        private ConcurrentHashMap<Long, b> b = new ConcurrentHashMap<>();
        private AtomicBoolean d = new AtomicBoolean(false);

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
                default: goto L_0x003c;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
            r3[r2] = r1;
            r2 = 1;
            r1 = "e\u000e\u0013bos\u0013U&~h\u0004OgvbNTfkc\u000eI&KG'bISO!nW\\G,qJ^E+";
            r0 = 0;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
            r3[r2] = r1;
            r2 = 2;
            r1 = "e\u000e\u0013bos\u0013U&~h\u0004OgvbNTfkc\u000eI&KG'bISO!nWKO-xGJR";
            r0 = 1;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
            r3[r2] = r1;
            r2 = 3;
            r1 = "i\u0014Umm&\u0005Ekzv\u0014Tgq";
            r0 = 2;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
            r3[r2] = r1;
            r2 = 4;
            r1 = "T\u0005^mvp\u0005O(qi\u0014\u001dzza\tN|zt\u0005Y$?e\u0001Sfpr@^isj@Hfmc\u0007T{kc\u0012om|c\tKmm";
            r0 = 3;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
            r3[r2] = r1;
            r2 = 5;
            r1 = "U\u0005O~ve\u0005tfkc\u0012[i|c";
            r0 = 4;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
            r3[r2] = r1;
            r2 = 6;
            r1 = "r\u0001Ziso\u0001NWlc\u0011Tl";
            r0 = 5;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
            r3[r2] = r1;
            r2 = 7;
            r1 = "U\u0005IIso\u0001NIqb4\\ol&\u0014Tezi\u0015I(mo\u0004\u0007";
            r0 = 6;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
            r3[r2] = r1;
            r2 = '\b';
            r1 = "U\u0005IIso\u0001NIqb4\\ol&\u0006Tfvu\b\u001d2?c\u0012OgmE\u000fYm%";
            r0 = 7;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
            r3[r2] = r1;
            r2 = '\t';
            r1 = "&\u0012Tl%";
            r0 = '\b';
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
            r3[r2] = r1;
            r2 = '\n';
            r1 = "r\u0001Ziso\u0001N(|g\fQj~e\u000b\u001dal&\u000eHds=@Oa{;";
            r0 = '\t';
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
            r3[r2] = r1;
            r2 = 11;
            r1 = "R\u0001ZIso\u0001NGoc\u0012\\|pt@RfMc\u0003Xaic@Tfkc\u000eI(vu@S}sj";
            r0 = '\n';
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
            r3[r2] = r1;
            r2 = '\f';
            r1 = "r\u0001Ziso\u0001NWzt\u0012Rz|i\u0004X";
            r0 = 11;
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
            r3[r2] = r1;
            r2 = '\r';
            r1 = "&\u0014\\o^j\t\\{\\g\fQj~e\u000bN(%";
            r0 = '\f';
            r3 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
            r3[r2] = r1;
            cn.jpush.android.service.ServiceInterface.TagAliasOperator.z = r3;
            cn.jpush.android.service.ServiceInterface.TagAliasOperator.c = new java.lang.Object();
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00bf, code lost:
            r9 = 6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00c2, code lost:
            r9 = '`';
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00c6, code lost:
            r9 = '=';
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00ca, code lost:
            r9 = '\b';
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
                case 0: goto L_0x00bf;
                case 1: goto L_0x00c2;
                case 2: goto L_0x00c6;
                case 3: goto L_0x00ca;
                default: goto L_0x001e;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
            r9 = 31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
            r5[r6] = (char) (r9 ^ r10);
            r6 = r8 + 1;
         */
        static {
            /*
                Method dump skipped, instructions count: 248
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.ServiceInterface.TagAliasOperator.<clinit>():void");
        }

        private TagAliasOperator() {
        }

        public static TagAliasOperator a() {
            synchronized (c) {
                if (a == null) {
                    a = new TagAliasOperator();
                }
            }
            return a;
        }

        private b b(long j) {
            return this.b.get(Long.valueOf(j));
        }

        private synchronized void b(Context context) {
            if (this.d.get() && this.b != null && this.b.isEmpty()) {
                try {
                    context.unregisterReceiver(this);
                } catch (IllegalArgumentException e) {
                    ac.a(z[5], z[4], e);
                } catch (Exception e2) {
                    ac.a(z[5], z[3], e2);
                }
                this.d.set(false);
            }
            ac.a();
        }

        public final void a(long j) {
            this.b.remove(Long.valueOf(j));
        }

        public final void a(Context context) {
            if (!this.d.get()) {
                try {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addCategory(e.c);
                    intentFilter.addAction(z[2]);
                    intentFilter.addAction(z[1]);
                    context.registerReceiver(this, intentFilter);
                    this.d.set(true);
                } catch (Exception e) {
                    new StringBuilder(z[0]).append(e.getMessage());
                    ac.e();
                }
            } else {
                ac.b();
            }
        }

        public final void a(Long l, b bVar) {
            this.b.put(l, bVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ac.d(z[5], z[11]);
                return;
            }
            long longExtra = intent.getLongExtra(z[6], -1L);
            int intExtra = intent.getIntExtra(z[12], 0);
            if (longExtra == -1) {
                ac.d();
                return;
            }
            new StringBuilder(z[9]).append(longExtra).append(z[13]).append(this.b.toString());
            ac.a();
            if (z[2].equals(intent.getAction())) {
                new StringBuilder(z[7]).append(longExtra);
                ac.a();
                b b = b(longExtra);
                if (b != null) {
                    TagAliasCallback tagAliasCallback = b.c;
                    a(longExtra);
                    if (tagAliasCallback != null) {
                        tagAliasCallback.gotResult(cn.jpush.android.api.e.b, b.a, b.b);
                    }
                } else {
                    new StringBuilder(z[10]).append(longExtra);
                    ac.d();
                }
            } else {
                new StringBuilder(z[8]).append(intExtra).append(z[9]).append(longExtra);
                ac.a();
                b b2 = b(longExtra);
                if (b2 != null) {
                    TagAliasCallback tagAliasCallback2 = b2.c;
                    a(longExtra);
                    if (tagAliasCallback2 != null) {
                        tagAliasCallback2.gotResult(intExtra, b2.a, b2.b);
                    }
                } else {
                    new StringBuilder(z[10]).append(longExtra);
                    ac.d();
                }
            }
            a().b(context);
        }
    }

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
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            case 34: goto L_0x01a5;
            case 35: goto L_0x01b0;
            case 36: goto L_0x01bb;
            case 37: goto L_0x01c6;
            case 38: goto L_0x01d1;
            case 39: goto L_0x01dc;
            case 40: goto L_0x01e7;
            case 41: goto L_0x01f2;
            case 42: goto L_0x01fd;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "5erf.6dn~\u0001 t";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "(emz\u0018\u001adx~\u0014";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "&|do\u0003\u0004|m@\u001e1ygg\u0012$dha\u001fe=!m\u001e+ddv\u0005eyr.\u001f0|m/";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0016usx\u0018&uH`\u0005 bgo\u0012 ";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "i0rz\u001e5Dx~\u0014\u007f";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "5ye4";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "'\u007few";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "\u0011xd.\u0002 bwg\u0012 0h}Q6dn~\u0001 t-.\u001810vg\u001d)0fg\u0007 0t~Q$|m.\u0005-u!o\u00121yn`\u0002eeoz\u0018)0xa\u0004es`b\u001debd}\u0004(uQ{\u0002-0lk\u0005-\u007fe.\u0005*0sk\u00020}d.\u0005-u!}\u00147fhm\u0014k";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "6ymk\u001f&u^~\u00046x^z\u0018(u";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3[r2] = r1;
        r2 = '\n';
        r1 = "6upQ\u0018!";
        r0 = '\t';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
        r3[r2] = r1;
        r2 = 11;
        r1 = " *";
        r0 = '\n';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
        r3[r2] = r1;
        r2 = '\f';
        r1 = "1qf}";
        r0 = 11;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
        r3[r2] = r1;
        r2 = '\r';
        r1 = "$|ho\u0002";
        r0 = '\f';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r3[r2] = r1;
        r2 = 14;
        r1 = "1qf.\u0010)y`}Q7ye.Le";
        r0 = '\r';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
        r3[r2] = r1;
        r2 = 15;
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u 0\tY@].\u0011QF]";
        r0 = 14;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c9, code lost:
        r3[r2] = r1;
        r2 = 16;
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u 2\n^OK2\u0011YWG%\u001cOBF0\u000bWD";
        r0 = 15;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
        r3[r2] = r1;
        r2 = 17;
        r1 = "&\u007fo`\u0014&dha\u001fhcuo\u0005 ";
        r0 = 16;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
        r3[r2] = r1;
        r2 = 18;
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u #\u0011S";
        r0 = 17;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r3[r2] = r1;
        r2 = 19;
        r1 = "7db";
        r0 = 18;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
        r3[r2] = r1;
        r2 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r1 = "7dbQ\u0015 |`w";
        r0 = 19;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
        r3[r2] = r1;
        r2 = 21;
        r1 = "&x``\u0016 Oqo\u0012.qfk\u001f$}d";
        r0 = com.tencent.open.GameAppOperation.PIC_SYMBOLE;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u000bembQ&\u007foz\u0014=d-.\u0001)u`}\u0014eyog\u0005eZQ{\u0002-1";
        r0 = 21;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0116, code lost:
        r3[r2] = r1;
        r2 = 23;
        r1 = "+\u007fug\u0017,s`z\u0018*~^l\u0004,tmk\u0003";
        r0 = 22;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0121, code lost:
        r3[r2] = r1;
        r2 = 24;
        r1 = "+\u007fug\u0017,s`z\u0018*~^l\u0004,tmk\u0003\u001aye";
        r0 = 23;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012c, code lost:
        r3[r2] = r1;
        r2 = 25;
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u \"\u0011_Q^$\u0016X";
        r0 = 24;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0137, code lost:
        r3[r2] = r1;
        r2 = 26;
        r1 = "$`q";
        r0 = 25;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0142, code lost:
        r3[r2] = r1;
        r2 = 27;
        r1 = "6usx\u0018&u!o\u001d7u`j\becua\u0001";
        r0 = 26;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
        r3[r2] = r1;
        r2 = 28;
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u 8\u000bYU";
        r0 = 27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0158, code lost:
        r3[r2] = r1;
        r2 = 29;
        r1 = " ~ez<,~r";
        r0 = 28;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0163, code lost:
        r3[r2] = r1;
        r2 = 30;
        r1 = "6d`|\u0005\r\u007ft|";
        r0 = 29;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016e, code lost:
        r3[r2] = r1;
        r2 = 31;
        r1 = " ~eF\u001e0b";
        r0 = 30;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0179, code lost:
        r3[r2] = r1;
        r2 = ' ';
        r1 = "6d`|\u0005\byo}";
        r0 = 31;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
        r3[r2] = r1;
        r2 = '!';
        r1 = "6uu@\u001e1ygg\u0012$dha\u001f\u000bell\u001470,.\u0012*~uk\t10h}Q+embP";
        r0 = ' ';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018f, code lost:
        r3[r2] = r1;
        r2 = '\"';
        r1 = "+\u007fug\u0017,s`z\u0018*~^c\u0010=~tc";
        r0 = '!';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019a, code lost:
        r3[r2] = r1;
        r2 = '#';
        r1 = "+ell\u001470h`Q4ed{\u0014\u007f0";
        r0 = '\"';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a5, code lost:
        r3[r2] = r1;
        r2 = '$';
        r1 = "6uu.\u001f*dhh\u0018&qug\u001e+0lo\te~tcQ\u007f0";
        r0 = '#';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b0, code lost:
        r3[r2] = r1;
        r2 = '%';
        r1 = "!ub|\u0014$cd@\u001e1ygg\u0012$dha\u001f\u007f";
        r0 = '$';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
        r3[r2] = r1;
        r2 = '&';
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u #\u0000CUA#\u0000@T]9";
        r0 = '%';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c6, code lost:
        r3[r2] = r1;
        r2 = '\'';
        r1 = "6usx\u0018&u!g\u0002ebt`\u001f,~f.\u0010)bdo\u0015<";
        r0 = '&';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
        r3[r2] = r1;
        r2 = '(';
        r1 = "7uqa\u00031>dv\u00057q/g\u001f#\u007f";
        r0 = '\'';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
        r3[r2] = r1;
        r2 = ')';
        r1 = "&~/d\u00010ci \u0010+tsa\u0018!>h`\u0005 ~u #\u0000@N\\%";
        r0 = '(';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        r3[r2] = r1;
        r2 = '*';
        r1 = "7uqa\u00031";
        r0 = ')';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01f2, code lost:
        r3[r2] = r1;
        r2 = '+';
        r1 = "w>0 H";
        r0 = '*';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01fd, code lost:
        r3[r2] = r1;
        cn.jpush.android.service.ServiceInterface.z = r3;
        cn.jpush.android.service.ServiceInterface.a = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0204, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0205, code lost:
        r9 = 'E';
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0209, code lost:
        r9 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x020d, code lost:
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0210, code lost:
        r9 = 14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0205;
            case 1: goto L_0x0209;
            case 2: goto L_0x020d;
            case 3: goto L_0x0210;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = 'q';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 634
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.ServiceInterface.<clinit>():void");
    }

    public static String a() {
        return z[43];
    }

    public static void a(Context context) {
        if (!e(context)) {
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(z[28]);
                intent.putExtra(z[26], context.getPackageName());
                context.startService(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(Context context, int i) {
        if (1 == h(context)) {
            ac.b(z[4], z[27]);
            return;
        }
        b(context, false);
        a.b(context, 1);
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(z[25]);
        intent.putExtra(z[26], context.getPackageName());
        context.startService(intent);
    }

    public static void a(Context context, c cVar) {
        ac.a();
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(z[7], cVar);
        context.startService(intent);
    }

    public static void a(Context context, Integer num, BasicPushNotificationBuilder basicPushNotificationBuilder) {
        if (context == null) {
            ac.e(z[4], z[22]);
        } else if (b() || !e.n) {
            a.a(context, new StringBuilder().append(num).toString(), basicPushNotificationBuilder.toString());
        } else {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[0]);
            Bundle bundle = new Bundle();
            bundle.putInt(z[2], 1);
            bundle.putString(z[24], new StringBuilder().append(num).toString());
            bundle.putString(z[23], basicPushNotificationBuilder.toString());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void a(Context context, String str) {
        if (context != null && !e(context)) {
            if (b() || !e.n) {
                a.a(context, str);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(z[2], 4);
            bundle.putString(z[9], str);
            intent.setAction(z[0]);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[41]);
            Bundle bundle = new Bundle();
            bundle.putString(z[42], str);
            bundle.putString(z[40], str2);
            intent.putExtras(bundle);
            context.startService(intent);
        } catch (SecurityException e) {
        } catch (Exception e2) {
        }
    }

    public static void a(Context context, String str, String str2, b bVar) {
        if (!e(context)) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            if (e.a(context)) {
                long n = a.n();
                if (!(bVar == null || bVar.c == null)) {
                    TagAliasOperator.a().a(Long.valueOf(n), bVar);
                }
                new StringBuilder(z[14]).append(n);
                ac.c();
                TagAliasOperator.a().a(context);
                try {
                    Intent intent = new Intent(context, PushService.class);
                    intent.setAction(z[15]);
                    intent.putExtra(z[13], str);
                    intent.putExtra(z[12], str2);
                    intent.putExtra(z[10], n);
                    context.startService(intent);
                } catch (SecurityException e) {
                    new StringBuilder(z[11]).append(e.getMessage());
                    ac.d();
                    if (!(bVar == null || bVar.c == null)) {
                        bVar.c.gotResult(cn.jpush.android.api.e.i, bVar.a, bVar.b);
                        TagAliasOperator.a().a(n);
                    }
                } catch (Exception e2) {
                    new StringBuilder(z[11]).append(e2.getMessage());
                    ac.d();
                    if (!(bVar == null || bVar.c == null)) {
                        bVar.c.gotResult(cn.jpush.android.api.e.i, bVar.a, bVar.b);
                        TagAliasOperator.a().a(n);
                    }
                }
            }
        }
    }

    public static void a(Context context, boolean z2) {
        if (context != null) {
            if (b() || !e.n) {
                a.a(context, z2);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[0]);
            Bundle bundle = new Bundle();
            bundle.putInt(z[2], 5);
            bundle.putBoolean(z[1], z2);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static boolean a(Context context, int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(z[30], i);
            jSONObject.put(z[32], i2);
            jSONObject.put(z[31], i3);
            jSONObject.put(z[29], i4);
            a(context, jSONObject.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static void b(Context context) {
        if (!e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[18]);
            Bundle bundle = new Bundle();
            bundle.putString(z[19], z[19]);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void b(Context context, int i) {
        if (h(context) == 0) {
            ac.b(z[4], z[39]);
            return;
        }
        b(context, true);
        a.b(context, 0);
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(z[38]);
        intent.putExtra(z[26], context.getPackageName());
        context.startService(intent);
    }

    public static void b(Context context, String str) {
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(z[0]);
        Bundle bundle = new Bundle();
        bundle.putInt(z[2], 11);
        bundle.putString(z[21], str);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public static void b(Context context, boolean z2) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context.getApplicationContext(), PushReceiver.class);
        ComponentName componentName2 = new ComponentName(context.getApplicationContext(), AlarmReceiver.class);
        if (!z2) {
            ac.a();
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            packageManager.setComponentEnabledSetting(componentName2, 2, 1);
            cn.jpush.android.util.b.l(context.getApplicationContext());
            return;
        }
        ac.a();
        packageManager.setComponentEnabledSetting(componentName, 1, 1);
        packageManager.setComponentEnabledSetting(componentName2, 1, 1);
        cn.jpush.android.util.b.k(context.getApplicationContext());
    }

    public static boolean b() {
        return e.o != null;
    }

    public static void c(Context context) {
        if (context == null) {
            ac.d(z[4], z[3]);
        } else if (b() || !e.n) {
            n.a(context.getApplicationContext());
        } else {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[0]);
            Bundle bundle = new Bundle();
            bundle.putInt(z[2], 10);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void c(Context context, int i) {
        if (!e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[18]);
            Bundle bundle = new Bundle();
            bundle.putString(z[19], z[19]);
            bundle.putInt(z[20], i);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void d(Context context, int i) {
        if (context == null) {
            ac.d(z[4], z[33]);
            return;
        }
        new StringBuilder(z[36]).append(i);
        ac.a();
        if (b() || !e.n) {
            int b = d.b();
            new StringBuilder(z[35]).append(b);
            ac.a();
            if (i < b) {
                int i2 = b - i;
                new StringBuilder(z[37]).append(i2);
                ac.a();
                n.a(context, i2);
            }
            a.a(context, i);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(z[0]);
        Bundle bundle = new Bundle();
        bundle.putInt(z[2], 2);
        bundle.putInt(z[34], i);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public static boolean d(Context context) {
        return h(context) > 0;
    }

    public static boolean e(Context context) {
        boolean d = d(context);
        if (d) {
            ac.b(z[4], z[8]);
        }
        return d;
    }

    public static void f(Context context) {
        if (!e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[16]);
            Bundle bundle = new Bundle();
            bundle.putString(z[17], a.a.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void g(Context context) {
        if (!e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(z[16]);
            Bundle bundle = new Bundle();
            bundle.putString(z[17], a.b.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    private static int h(Context context) {
        int c = a.c(context);
        new StringBuilder(z[6]).append(Process.myPid()).append(z[5]).append(c);
        ac.b();
        return c;
    }
}
