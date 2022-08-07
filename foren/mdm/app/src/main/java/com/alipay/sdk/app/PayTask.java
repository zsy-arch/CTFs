package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.data.c;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.tencent.open.GameAppOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PayTask {
    static final Object a = e.class;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();

    public PayTask(Activity activity) {
        this.b = activity;
        b a2 = b.a();
        Activity activity2 = this.b;
        c.a();
        a2.a(activity2);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.b);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:(2:50|5)|6|(2:10|(11:12|(1:14)|15|52|16|(3:26|(4:29|(2:33|(2:34|(1:1)(2:36|(2:42|(3:60|44|59)(1:45))(3:61|40|55))))(0)|41|27)|54)|18|(1:20)|21|22|23))|25|15|52|16|(0)|18|(0)|21|22|23) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00f7, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f8, code lost:
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.y, r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a A[Catch: Throwable -> 0x00f7, all -> 0x0124, TRY_LEAVE, TryCatch #4 {, blocks: (B:5:0x0005, B:21:0x005f, B:6:0x0008, B:8:0x001b, B:10:0x0023, B:12:0x003e, B:14:0x0044, B:15:0x0048, B:16:0x004e, B:18:0x0054, B:20:0x005a, B:25:0x007b, B:26:0x0080, B:27:0x0087, B:29:0x008a, B:31:0x0094, B:33:0x009f, B:34:0x00b6, B:36:0x00b9, B:38:0x00c3, B:40:0x00cd, B:41:0x00dd, B:42:0x00e1, B:44:0x00eb, B:47:0x00f8), top: B:50:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[Catch: Throwable -> 0x00f7, all -> 0x0124, TRY_ENTER, TryCatch #4 {, blocks: (B:5:0x0005, B:21:0x005f, B:6:0x0008, B:8:0x001b, B:10:0x0023, B:12:0x003e, B:14:0x0044, B:15:0x0048, B:16:0x004e, B:18:0x0054, B:20:0x005a, B:25:0x007b, B:26:0x0080, B:27:0x0087, B:29:0x008a, B:31:0x0094, B:33:0x009f, B:34:0x00b6, B:36:0x00b9, B:38:0x00c3, B:40:0x00cd, B:41:0x00dd, B:42:0x00e1, B:44:0x00eb, B:47:0x00f8), top: B:50:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String pay(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.pay(java.lang.String, boolean):java.lang.String");
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return j.a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return i.b(this.b.getApplicationContext(), h.a, "");
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            if (trim.startsWith("https://" + this.d) || trim.startsWith("http://" + this.d)) {
                String trim2 = trim.replaceFirst("(http|https)://" + this.d + "\\?", "").trim();
                if (!TextUtils.isEmpty(trim2)) {
                    str2 = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", l.a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative") + "\"";
                }
            }
            if (trim.startsWith("https://" + this.e) || trim.startsWith("http://" + this.e)) {
                String trim3 = trim.replaceFirst("(http|https)://" + this.e + "\\?", "").trim();
                if (!TextUtils.isEmpty(trim3)) {
                    str2 = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", l.a(trim3).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative") + "\"";
                }
            }
            if ((trim.startsWith("https://" + this.f) || trim.startsWith("http://" + this.f)) && trim.contains("alipay.wap.create.direct.pay.by.user") && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://" + this.f + "\\?", "").trim())) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", str);
                    jSONObject.put("bizcontext", new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative"));
                    str2 = "new_external_info==" + jSONObject.toString();
                } catch (Throwable th) {
                }
            }
            if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                String a2 = l.a("?", "", str);
                if (!TextUtils.isEmpty(a2)) {
                    Map<String, String> a3 = l.a(a2);
                    StringBuilder sb = new StringBuilder();
                    if (a(false, true, com.alipay.sdk.app.statistic.c.H, sb, a3, com.alipay.sdk.app.statistic.c.H, "alipay_trade_no")) {
                        a(true, false, "pay_phase_id", sb, a3, "payPhaseId", "pay_phase_id", "out_relation_id");
                        sb.append("&biz_sub_type=\"TRADE\"");
                        sb.append("&biz_type=\"trade\"");
                        String str3 = a3.get(GameAppOperation.QQFAV_DATALINE_APPNAME);
                        if (TextUtils.isEmpty(str3) && !TextUtils.isEmpty(a3.get("cid"))) {
                            str3 = "ali1688";
                        } else if (TextUtils.isEmpty(str3) && (!TextUtils.isEmpty(a3.get(f.o)) || !TextUtils.isEmpty(a3.get("s_id")))) {
                            str3 = "tb";
                        }
                        sb.append("&app_name=\"" + str3 + "\"");
                        if (!a(true, true, "extern_token", sb, a3, "extern_token", "cid", f.o, "s_id")) {
                            str2 = "";
                        } else {
                            a(true, false, "appenv", sb, a3, "appenv");
                            sb.append("&pay_channel_id=\"alipay_sdk\"");
                            a aVar = new a(this, (byte) 0);
                            aVar.a = a3.get("return_url");
                            aVar.b = a3.get("pay_order_id");
                            str2 = sb.toString() + "&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative") + "\"";
                            this.g.put(str2, aVar);
                        }
                    }
                }
            }
        }
        str2 = "";
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a {
        String a;
        String b;

        private a() {
            PayTask.this = r2;
            this.a = "";
            this.b = "";
        }

        /* synthetic */ a(PayTask payTask, byte b) {
            this();
        }

        private String a() {
            return this.a;
        }

        private void a(String str) {
            this.a = str;
        }

        private String b() {
            return this.b;
        }

        private void b(String str) {
            this.b = str;
        }
    }

    private static boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            sb.append(com.alipay.sdk.sys.a.b).append(str).append("=\"").append(str2).append("\"");
        } else {
            sb.append(str).append("=\"").append(str2).append("\"");
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x011b A[Catch: Throwable -> 0x00c7, all -> 0x012c, TryCatch #0 {Throwable -> 0x00c7, blocks: (B:19:0x0087, B:21:0x0097, B:23:0x00a1, B:25:0x00a9, B:26:0x00ae, B:27:0x00b5, B:30:0x00c9, B:32:0x00d7, B:34:0x00e5, B:36:0x00f3, B:38:0x0107, B:39:0x0115, B:41:0x011b, B:42:0x0121, B:45:0x012f, B:47:0x0139), top: B:51:0x0087 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.alipay.sdk.util.H5PayResultModel h5Pay(java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.h5Pay(java.lang.String, boolean):com.alipay.sdk.util.H5PayResultModel");
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(h.d));
    }

    private e.a a() {
        return new g(this);
    }

    private void b() {
        if (this.c != null) {
            this.c.a();
        }
    }

    public void c() {
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
    }

    private String a(String str) {
        String a2 = new com.alipay.sdk.sys.a(this.b).a(str);
        if (a2.contains("paymethod=\"expressGateway\"")) {
            return b(a2);
        }
        if (!l.c(this.b)) {
            return b(a2);
        }
        e eVar = new e(this.b, new g(this));
        String a3 = eVar.a(a2);
        eVar.a = null;
        if (TextUtils.equals(a3, e.b)) {
            return b(a2);
        }
        if (TextUtils.isEmpty(a3)) {
            return h.a();
        }
        return a3;
    }

    public String getVersion() {
        return com.alipay.sdk.cons.a.f;
    }

    private String b(String str) {
        i iVar;
        try {
            b();
            try {
                List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(new d().a(this.b.getApplicationContext(), str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                for (int i = 0; i < a2.size(); i++) {
                    if (a2.get(i).a == com.alipay.sdk.protocol.a.Update) {
                        String[] strArr = a2.get(i).b;
                        if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.cons.b.c, strArr[0])) {
                            Context context = b.a().a;
                            com.alipay.sdk.tid.b a3 = com.alipay.sdk.tid.b.a();
                            if (!TextUtils.isEmpty(strArr[1]) && !TextUtils.isEmpty(strArr[2])) {
                                a3.a = strArr[1];
                                a3.b = strArr[2];
                                com.alipay.sdk.tid.a aVar = new com.alipay.sdk.tid.a(context);
                                try {
                                    aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a3.a, a3.b);
                                } catch (Exception e) {
                                } finally {
                                    aVar.close();
                                }
                            }
                        }
                    }
                }
                c();
                for (int i2 = 0; i2 < a2.size(); i2++) {
                    if (a2.get(i2).a == com.alipay.sdk.protocol.a.WapPay) {
                        return a(a2.get(i2));
                    }
                }
                c();
                iVar = null;
            } catch (IOException e2) {
                iVar = i.a(i.NETWORK_ERROR.h);
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e2);
            }
            if (iVar == null) {
                iVar = i.a(i.FAILED.h);
            }
            return h.a(iVar.h, iVar.i, "");
        } finally {
            c();
        }
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }
}
