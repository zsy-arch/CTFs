package com.baidu.mobstat;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bg implements Thread.UncaughtExceptionHandler {
    private static final bg a = new bg();
    private Thread.UncaughtExceptionHandler b = null;
    private Context c = null;
    private bo d = new bo();

    private bg() {
    }

    public static bg a() {
        return a;
    }

    public void a(long j, String str, String str2, int i) {
        if (this.c != null && str != null && !str.trim().equals("")) {
            try {
                String appVersionName = CooperService.a().getAppVersionName(this.c);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("t", j);
                jSONObject.put("c", str);
                jSONObject.put("y", str2);
                jSONObject.put("v", appVersionName);
                jSONObject.put("ct", i);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                JSONObject jSONObject2 = new JSONObject();
                this.d.a(this.c, jSONObject2);
                jSONObject2.put("ss", 0);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("he", jSONObject2);
                jSONObject3.put("pr", new JSONArray());
                jSONObject3.put("ev", new JSONArray());
                jSONObject3.put("ex", jSONArray);
                cl.a(this.c, "__send_data_" + System.currentTimeMillis(), jSONObject3.toString(), false);
                cr.a("Dump exception successlly");
            } catch (Exception e) {
                cr.b(e);
            }
        }
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        if (this.c == null) {
            this.c = context.getApplicationContext();
        }
        this.d.a(this.c);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String th2 = th.toString();
        String str = "";
        if (th2 != null && !th2.equals("")) {
            try {
                str = th2.length() > 1 ? th2.split(":")[0] : th2;
            } catch (Exception e) {
                cr.c(e);
                str = "";
            }
        }
        if (str == null || str.equals("")) {
            str = th2;
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        cr.a(obj);
        a(System.currentTimeMillis(), obj, str, 0);
        if (!this.b.equals(this)) {
            this.b.uncaughtException(thread, th);
        }
    }
}
