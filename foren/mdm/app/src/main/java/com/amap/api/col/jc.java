package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* compiled from: MapParser.java */
/* loaded from: classes.dex */
public final class jc {
    private StringBuilder a = new StringBuilder();

    public final it a(String str, Context context, ii iiVar) {
        it itVar = new it("");
        itVar.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status") || !jSONObject.has("info")) {
                this.a.append("json is error " + str);
            }
            String string = jSONObject.getString("status");
            String string2 = jSONObject.getString("info");
            if (string.equals("0")) {
                this.a.append("auth fail:" + string2);
            }
        } catch (Throwable th) {
            this.a.append("json exception error:" + th.getMessage());
            jn.a(th, "parser", "paseAuthFailurJson");
        }
        try {
            this.a.append("#SHA1AndPackage#").append(ga.e(context));
            String str2 = iiVar.b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                this.a.append(" #gsid#").append(str2);
            }
            String str3 = iiVar.c;
            if (!TextUtils.isEmpty(str3)) {
                this.a.append(" #csid#" + str3);
            }
        } catch (Throwable th2) {
        }
        itVar.setLocationDetail(this.a.toString());
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        return itVar;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(48:15|(2:164|16)|17|(2:143|18)|(3:158|19|20)|21|150|22|154|23|24|25|156|26|148|27|28|29|(5:166|30|146|31|32)|33|141|34|35|(2:172|36)|(3:180|37|38)|39|178|40|41|170|42|174|43|44|168|45|46|(1:48)|49|(1:55)|56|(1:58)|59|(1:61)|62|(3:64|(1:66)|67)|68|(1:110)(1:72)) */
    /* JADX WARN: Can't wrap try/catch for region: R(49:15|(2:164|16)|17|143|18|(3:158|19|20)|21|150|22|154|23|24|25|156|26|148|27|28|29|(5:166|30|146|31|32)|33|141|34|35|(2:172|36)|(3:180|37|38)|39|178|40|41|170|42|174|43|44|168|45|46|(1:48)|49|(1:55)|56|(1:58)|59|(1:61)|62|(3:64|(1:66)|67)|68|(1:110)(1:72)) */
    /* JADX WARN: Can't wrap try/catch for region: R(56:15|164|16|17|143|18|158|19|20|21|150|22|154|23|24|25|156|26|148|27|28|29|166|30|146|31|32|33|141|34|35|(2:172|36)|(3:180|37|38)|39|178|40|41|170|42|174|43|44|168|45|46|(1:48)|49|(1:55)|56|(1:58)|59|(1:61)|62|(3:64|(1:66)|67)|68|(1:110)(1:72)) */
    /* JADX WARN: Can't wrap try/catch for region: R(57:15|164|16|17|143|18|158|19|20|21|150|22|154|23|24|25|156|26|148|27|28|29|166|30|146|31|32|33|141|34|35|172|36|(3:180|37|38)|39|178|40|41|170|42|174|43|44|168|45|46|(1:48)|49|(1:55)|56|(1:58)|59|(1:61)|62|(3:64|(1:66)|67)|68|(1:110)(1:72)) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x017f A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x018e A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01ac A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01bb A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ca A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x020f A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0239 A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0248 A[Catch: Throwable -> 0x02dd, all -> 0x031a, TRY_LEAVE, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0278 A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0287 A[Catch: Throwable -> 0x02dd, all -> 0x031a, TryCatch #2 {Throwable -> 0x02dd, blocks: (B:8:0x0036, B:10:0x003c, B:13:0x0050, B:15:0x008e, B:17:0x00af, B:21:0x00c5, B:25:0x00db, B:29:0x00f1, B:33:0x010a, B:35:0x011f, B:39:0x0135, B:41:0x014a, B:44:0x015f, B:46:0x0174, B:48:0x017f, B:49:0x0188, B:51:0x018e, B:53:0x0197, B:55:0x019d, B:56:0x01a6, B:58:0x01ac, B:59:0x01b5, B:61:0x01bb, B:62:0x01c4, B:64:0x01ca, B:66:0x01d0, B:67:0x01d6, B:68:0x01df, B:70:0x020f, B:72:0x0219, B:73:0x0228, B:75:0x0239, B:76:0x0242, B:78:0x0248, B:80:0x025d, B:82:0x0272, B:84:0x0278, B:85:0x0281, B:87:0x0287, B:88:0x028e, B:110:0x02d4), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amap.api.col.it a(byte[] r14) {
        /*
            Method dump skipped, instructions count: 843
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.jc.a(byte[]):com.amap.api.col.it");
    }
}
