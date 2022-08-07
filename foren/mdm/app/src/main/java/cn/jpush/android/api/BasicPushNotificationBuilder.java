package cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.os.Build;
import cn.jpush.android.e;

/* loaded from: classes.dex */
public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder {
    private static final String[] z;
    protected Context a;
    public int notificationDefaults = -1;
    public int notificationFlags = 16;
    public int statusBarDrawable = e.b;
    public String developerArg0 = z[4];

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
            case 4: goto L_0x0064;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "S\u001ad)\f]";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "R\u000ed4\u0000";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "o0H\u0002<";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "T\na8\u000f_\u001fr/\"B\b'";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "~:[\u0011CS\u0000y)\u0006H\u001b";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.BasicPushNotificationBuilder.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
        r9 = '0';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
        r9 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
        r9 = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
        r9 = ']';
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
            case 0: goto L_0x0069;
            case 1: goto L_0x006c;
            case 2: goto L_0x006f;
            case 3: goto L_0x0072;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'c';
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
            java.lang.String r1 = "R\u000ed4\u0000o0H\u0002<"
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
                case 0: goto L_0x0069;
                case 1: goto L_0x006c;
                case 2: goto L_0x006f;
                case 3: goto L_0x0072;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 99
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
                case 4: goto L_0x0064;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "S\u001ad)\f]"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "R\u000ed4\u0000"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "o0H\u0002<"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "T\na8\u000f_\u001fr/\"B\b'"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "~:[\u0011CS\u0000y)\u0006H\u001b"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0064:
            r3[r2] = r1
            cn.jpush.android.api.BasicPushNotificationBuilder.z = r4
            return
        L_0x0069:
            r9 = 48
            goto L_0x001f
        L_0x006c:
            r9 = 111(0x6f, float:1.56E-43)
            goto L_0x001f
        L_0x006f:
            r9 = 23
            goto L_0x001f
        L_0x0072:
            r9 = 93
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.BasicPushNotificationBuilder.<clinit>():void");
    }

    public BasicPushNotificationBuilder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(z[5]);
        }
        this.a = context;
    }

    public static PushNotificationBuilder a(String str) {
        String[] split = str.split(z[3]);
        String str2 = split[0];
        BasicPushNotificationBuilder basicPushNotificationBuilder = z[2].equals(str2) ? new BasicPushNotificationBuilder(e.e) : z[1].equals(str2) ? new CustomPushNotificationBuilder(e.e) : new BasicPushNotificationBuilder(e.e);
        basicPushNotificationBuilder.a(split);
        return basicPushNotificationBuilder;
    }

    @Override // cn.jpush.android.api.DefaultPushNotificationBuilder
    @TargetApi(11)
    final Notification a(Notification.Builder builder) {
        builder.setDefaults(this.notificationDefaults);
        builder.setSmallIcon(this.statusBarDrawable);
        Notification build = Build.VERSION.SDK_INT >= 16 ? builder.build() : builder.getNotification();
        build.flags = this.notificationFlags;
        return build;
    }

    @Override // cn.jpush.android.api.DefaultPushNotificationBuilder, cn.jpush.android.api.PushNotificationBuilder
    public final String a() {
        return this.developerArg0;
    }

    @Override // cn.jpush.android.api.DefaultPushNotificationBuilder
    final void a(Notification notification) {
        notification.defaults = this.notificationDefaults;
        notification.flags = this.notificationFlags;
        notification.icon = this.statusBarDrawable;
    }

    public void a(String[] strArr) {
        this.notificationDefaults = Integer.parseInt(strArr[1]);
        this.notificationFlags = Integer.parseInt(strArr[2]);
        this.statusBarDrawable = Integer.parseInt(strArr[3]);
        if (5 == strArr.length) {
            this.developerArg0 = strArr[4];
        }
    }

    public String b() {
        return this.notificationDefaults + z[3] + this.notificationFlags + z[3] + this.statusBarDrawable + z[3] + this.developerArg0;
    }

    public String toString() {
        return z[0] + b();
    }
}
