package cn.jpush.android.api;

import android.app.Notification;
import android.os.Build;
import android.widget.RemoteViews;
import cn.jpush.android.e;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import java.util.Map;

/* loaded from: classes.dex */
public class DefaultPushNotificationBuilder implements PushNotificationBuilder {
    private static final String[] z;

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
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "7=\u0004'^\r;B R\u0018&M&_Y1K'E\u001c<PiE\u0016rW!^\u000e|\u0004\u000eX\u000f7\u0004<AW";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001d E>P\u001b>A";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u001a<\n#A\f!LgP\u00176V&X\u001d|e\u0005t+\u0006";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001a<\n#A\f!LgP\u00176V&X\u001d|j\u0006e0\u0014m\np-\u001bk\u0007n:\u001dj\u001dt7\u0006{\u001dx-\u001ea";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001f3M%T\u001drP&\u0011\u001e7PiP\t\"H R\u0018&M&_Y;J/^Y3J-\u0011\u00101K'\u001f";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "=7B(D\u0015&t<B\u0011\u001cK=X\u001f;G(E\u0010=J\u000bD\u0010>@,C";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        cn.jpush.android.api.DefaultPushNotificationBuilder.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0070, code lost:
        r9 = 'y';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0073, code lost:
        r9 = 'R';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
        r9 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
        r9 = 'I';
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
            case 0: goto L_0x0070;
            case 1: goto L_0x0073;
            case 2: goto L_0x0076;
            case 3: goto L_0x0079;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = '1';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 7
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u0013\"Q:Y&<K=X\u001f;G(E\u0010=J\u0016X\u001a=J"
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
                case 0: goto L_0x0070;
                case 1: goto L_0x0073;
                case 2: goto L_0x0076;
                case 3: goto L_0x0079;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 49
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
                case 5: goto L_0x006b;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "7=\u0004'^\r;B R\u0018&M&_Y1K'E\u001c<PiE\u0016rW!^\u000e|\u0004\u000eX\u000f7\u0004<AW"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "\u001d E>P\u001b>A"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "\u001a<\n#A\f!LgP\u00176V&X\u001d|e\u0005t+\u0006"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u001a<\n#A\f!LgP\u00176V&X\u001d|j\u0006e0\u0014m\np-\u001bk\u0007n:\u001dj\u001dt7\u0006{\u001dx-\u001ea"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "\u001f3M%T\u001drP&\u0011\u001e7PiP\t\"H R\u0018&M&_Y;J/^Y3J-\u0011\u00101K'\u001f"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "=7B(D\u0015&t<B\u0011\u001cK=X\u001f;G(E\u0010=J\u000bD\u0010>@,C"
            r0 = 5
            r3 = r4
            goto L_0x0008
        L_0x006b:
            r3[r2] = r1
            cn.jpush.android.api.DefaultPushNotificationBuilder.z = r4
            return
        L_0x0070:
            r9 = 121(0x79, float:1.7E-43)
            goto L_0x001f
        L_0x0073:
            r9 = 82
            goto L_0x001f
        L_0x0076:
            r9 = 36
            goto L_0x001f
        L_0x0079:
            r9 = 73
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.DefaultPushNotificationBuilder.<clinit>():void");
    }

    Notification a(Notification.Builder builder) {
        return Build.VERSION.SDK_INT >= 16 ? builder.build() : builder.getNotification();
    }

    @Override // cn.jpush.android.api.PushNotificationBuilder
    public final Notification a(Map<String, String> map) {
        String str = e.d;
        String str2 = "";
        if (map.containsKey(z[3])) {
            str2 = map.get(z[3]);
        }
        if (ao.a(str2)) {
            ac.d(z[6], z[1]);
            return null;
        }
        if (map.containsKey(z[4])) {
            str = map.get(z[4]);
        }
        if (e.e != null) {
            int identifier = e.e.getResources().getIdentifier(z[0], z[2], e.e.getPackageName());
            if (identifier == 0) {
                if (e.b != 0) {
                    identifier = e.b;
                } else {
                    try {
                        identifier = e.e.getPackageManager().getApplicationInfo(e.e.getPackageName(), 0).icon;
                        ac.c();
                    } catch (Exception e) {
                        ac.b(z[6], z[5], e);
                        return null;
                    }
                }
            }
            RemoteViews a = a(str2, str);
            if (Build.VERSION.SDK_INT >= 11) {
                Notification.Builder smallIcon = new Notification.Builder(e.e).setContentTitle(str).setContentText(str2).setTicker(str2).setSmallIcon(identifier);
                if (a != null) {
                    smallIcon.setContent(a);
                } else {
                    ac.c();
                }
                return a(smallIcon);
            }
            Notification notification = new Notification(identifier, str2, System.currentTimeMillis());
            a(notification);
            if (str == null) {
                str = e.d;
            }
            if (a != null) {
                notification.contentView = a;
            } else {
                n.a(notification, e.e, str, str2, null);
            }
            return notification;
        }
        ac.d();
        return null;
    }

    RemoteViews a(String str, String str2) {
        return null;
    }

    @Override // cn.jpush.android.api.PushNotificationBuilder
    public String a() {
        return null;
    }

    void a(Notification notification) {
    }
}
