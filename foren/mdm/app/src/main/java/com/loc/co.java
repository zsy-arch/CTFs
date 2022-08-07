package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import org.json.JSONObject;

/* compiled from: Parser.java */
/* loaded from: classes2.dex */
public final class co {
    private StringBuilder a = new StringBuilder();

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bb A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d9 A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f6 A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0122 A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0131 A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0140 A[Catch: Throwable -> 0x01ac, TryCatch #0 {Throwable -> 0x01ac, blocks: (B:3:0x0001, B:5:0x004e, B:7:0x0056, B:9:0x005e, B:12:0x0068, B:14:0x006e, B:15:0x0072, B:17:0x00bb, B:18:0x00cd, B:20:0x00d9, B:21:0x00eb, B:23:0x00f6, B:24:0x00ff, B:26:0x0105, B:28:0x010d, B:30:0x0113, B:31:0x011c, B:33:0x0122, B:34:0x012b, B:36:0x0131, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0154, B:45:0x0180, B:47:0x018a, B:49:0x019a, B:51:0x01a4), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.autonavi.aps.amapapi.model.AMapLocationServer a(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.co.a(java.lang.String):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    private static String b(String str) {
        return "[]".equals(str) ? "" : str;
    }

    public final AMapLocationServer a(String str, Context context, bo boVar) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(7);
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
            f.a(th, "parser", "paseAuthFailurJson");
        }
        try {
            this.a.append("#SHA1AndPackage#").append(k.e(context));
            String str2 = boVar.b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                this.a.append(" #gsid#").append(str2);
            }
            String str3 = boVar.c;
            if (!TextUtils.isEmpty(str3)) {
                this.a.append(" #csid#" + str3);
            }
        } catch (Throwable th2) {
        }
        aMapLocationServer.setLocationDetail(this.a.toString());
        if (this.a.length() > 0) {
            this.a.delete(0, this.a.length());
        }
        return aMapLocationServer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(42:17|(2:168|18)|19|(2:147|20)|(3:162|21|22)|23|154|24|158|25|26|27|160|28|152|29|30|31|(5:170|32|150|33|34)|35|145|36|37|(5:176|38|143|39|40)|41|(2:182|42)|43|(3:174|44|(2:178|45))|46|(2:172|47)|48|(1:50)|51|(1:57)|58|(1:60)|61|(1:63)|64|(3:66|(1:68)|69)|70|(1:112)(1:74)) */
    /* JADX WARN: Can't wrap try/catch for region: R(43:17|(2:168|18)|19|147|20|(3:162|21|22)|23|154|24|158|25|26|27|160|28|152|29|30|31|(5:170|32|150|33|34)|35|145|36|37|(5:176|38|143|39|40)|41|(2:182|42)|43|(3:174|44|(2:178|45))|46|(2:172|47)|48|(1:50)|51|(1:57)|58|(1:60)|61|(1:63)|64|(3:66|(1:68)|69)|70|(1:112)(1:74)) */
    /* JADX WARN: Can't wrap try/catch for region: R(66:141|99|23|154|24|158|25|26|27|160|28|152|29|30|31|170|32|(3:150|33|34)|35|145|36|37|176|38|143|39|40|41|182|42|43|174|44|178|45|46|172|47|48|(0)|51|(0)|58|(0)|61|(0)|64|(0)|70|(0)|112|75|(0)|78|(0)|84|(0)|87|(0)|90|156|91|(0)|94|(0)|8) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0184 A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0193 A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b0 A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01bf A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01ce A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0212 A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x023c A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x024b A[Catch: Throwable -> 0x02df, all -> 0x031c, TRY_LEAVE, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x027b A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x028a A[Catch: Throwable -> 0x02df, all -> 0x031c, TryCatch #3 {Throwable -> 0x02df, blocks: (B:10:0x003b, B:12:0x0041, B:15:0x0055, B:17:0x0093, B:19:0x00b4, B:23:0x00ca, B:27:0x00e0, B:31:0x00f6, B:35:0x010f, B:37:0x0124, B:41:0x013a, B:43:0x014f, B:46:0x0164, B:48:0x0179, B:50:0x0184, B:51:0x018d, B:53:0x0193, B:55:0x019b, B:57:0x01a1, B:58:0x01aa, B:60:0x01b0, B:61:0x01b9, B:63:0x01bf, B:64:0x01c8, B:66:0x01ce, B:68:0x01d4, B:69:0x01d9, B:70:0x01e2, B:72:0x0212, B:74:0x021c, B:75:0x022b, B:77:0x023c, B:78:0x0245, B:80:0x024b, B:82:0x0260, B:84:0x0275, B:86:0x027b, B:87:0x0284, B:89:0x028a, B:90:0x0291, B:112:0x02d6), top: B:149:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer a(byte[] r14) {
        /*
            Method dump skipped, instructions count: 845
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.co.a(byte[]):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }
}
