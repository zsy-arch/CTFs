package cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import cn.jpush.android.e;

/* loaded from: classes.dex */
public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder {
    private static final String[] z;
    public int layout;
    public int layoutContentId;
    public int layoutIconDrawable = e.b;
    public int layoutIconId;
    public int layoutTitleId;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = "U\u001a\u0004C1"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0057
            r8 = r1
        L_0x0013:
            r9 = r2
            r10 = r8
            r13 = r7
            r7 = r2
            r2 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x004b;
                case 1: goto L_0x004e;
                case 2: goto L_0x0051;
                case 3: goto L_0x0054;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 110(0x6e, float:1.54E-43)
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002d:
            r7 = r2
            r2 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r3] = r2
            java.lang.String r0 = "i0(h\u0001g\u001a\u0004C1U"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r3] = r2
            cn.jpush.android.api.CustomPushNotificationBuilder.z = r6
            return
        L_0x004b:
            r11 = 10
            goto L_0x0021
        L_0x004e:
            r11 = 69
            goto L_0x0021
        L_0x0051:
            r11 = 91
            goto L_0x0021
        L_0x0054:
            r11 = 28
            goto L_0x0021
        L_0x0057:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.CustomPushNotificationBuilder.<clinit>():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomPushNotificationBuilder(Context context) {
        super(context);
    }

    public CustomPushNotificationBuilder(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.layout = i;
        this.layoutIconId = i2;
        this.layoutTitleId = i3;
        this.layoutContentId = i4;
    }

    @Override // cn.jpush.android.api.DefaultPushNotificationBuilder
    final RemoteViews a(String str, String str2) {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), this.layout);
        remoteViews.setTextViewText(this.layoutTitleId, str2);
        remoteViews.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
        remoteViews.setTextViewText(this.layoutContentId, str);
        return remoteViews;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.jpush.android.api.BasicPushNotificationBuilder
    public final void a(String[] strArr) {
        super.a(strArr);
        this.layout = Integer.parseInt(strArr[5]);
        this.layoutIconId = Integer.parseInt(strArr[6]);
        this.layoutTitleId = Integer.parseInt(strArr[7]);
        this.layoutContentId = Integer.parseInt(strArr[8]);
        this.layoutIconDrawable = Integer.parseInt(strArr[9]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.jpush.android.api.BasicPushNotificationBuilder
    public final String b() {
        return super.b() + z[0] + this.layout + z[0] + this.layoutIconId + z[0] + this.layoutTitleId + z[0] + this.layoutContentId + z[0] + this.layoutIconDrawable;
    }

    @Override // cn.jpush.android.api.BasicPushNotificationBuilder
    public String toString() {
        return z[1] + b();
    }
}
