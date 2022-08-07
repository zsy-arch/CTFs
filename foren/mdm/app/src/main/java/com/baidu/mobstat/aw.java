package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.http.config.URLConfig;
import dalvik.system.DexClassLoader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class aw extends Thread {
    private Context a;
    private k b;

    public aw(Context context, k kVar) {
        this.a = context;
        this.b = kVar;
    }

    private synchronized void a() {
        FileOutputStream fileOutputStream = null;
        synchronized (this) {
            bb.a("start get config and download jar");
            Context context = this.a;
            k kVar = this.b;
            String b = b(context);
            bb.c("update req url is:" + b);
            HttpURLConnection d = cl.d(context, b);
            d.connect();
            String headerField = d.getHeaderField("X-CONFIG");
            bb.a("config is: " + headerField);
            String headerField2 = d.getHeaderField("X-SIGN");
            bb.a("sign is: " + headerField2);
            int responseCode = d.getResponseCode();
            bb.a("update response code is: " + responseCode);
            int contentLength = d.getContentLength();
            bb.a("update response content length is: " + contentLength);
            if (responseCode == 200 && contentLength > 0) {
                try {
                    fileOutputStream = context.openFileOutput(".remote.jar", 0);
                    if (cq.a(d.getInputStream(), fileOutputStream)) {
                        bb.a("save remote jar success");
                    }
                    cq.a(fileOutputStream);
                } catch (IOException e) {
                    bb.b(e);
                    cq.a(fileOutputStream);
                }
            }
            DexClassLoader unused = av.a = null;
            as.a();
            if (!TextUtils.isEmpty(headerField)) {
                kVar.a(context, headerField);
            }
            if (!TextUtils.isEmpty(headerField2)) {
                kVar.b(context, headerField2);
            }
            d.disconnect();
            bb.a("finish get config and download jar");
        }
    }

    private void a(Context context) {
        this.b.a(context, System.currentTimeMillis());
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x003a, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x003c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = ".remote.jar"
            java.io.File r0 = r7.getFileStreamPath(r0)
            java.lang.String r1 = "8"
            if (r0 == 0) goto L_0x00f1
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x00f1
            java.lang.String r0 = ".remote.jar"
            java.io.File r0 = r7.getFileStreamPath(r0)
            if (r0 == 0) goto L_0x00f1
            java.lang.String r0 = r0.getAbsolutePath()
            java.lang.String r0 = com.baidu.mobstat.av.a(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "startDownload remote jar file version = "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.baidu.mobstat.bb.a(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x00f1
        L_0x003c:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.apache.http.message.BasicNameValuePair r2 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r3 = "dynamicVersion"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ""
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r0 = r0.toString()
            r2.<init>(r3, r0)
            r1.add(r2)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "packageName"
            java.lang.String r3 = com.baidu.mobstat.cu.o(r7)
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "appVersion"
            java.lang.String r3 = com.baidu.mobstat.cu.f(r7)
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "cuid"
            java.lang.String r3 = com.baidu.mobstat.cu.a(r7)
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "platform"
            java.lang.String r3 = "Android"
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "m"
            java.lang.String r3 = android.os.Build.MODEL
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "s"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = android.os.Build.VERSION.SDK_INT
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ""
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "o"
            java.lang.String r3 = android.os.Build.VERSION.RELEASE
            r0.<init>(r2, r3)
            r1.add(r0)
            org.apache.http.message.BasicNameValuePair r0 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "i"
            java.lang.String r3 = "8"
            r0.<init>(r2, r3)
            r1.add(r0)
            java.lang.String r0 = "utf-8"
            java.lang.String r0 = org.apache.http.client.utils.URLEncodedUtils.format(r1, r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "https://dxp.baidu.com/upgrade?"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x00f1:
            r0 = r1
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.aw.b(android.content.Context):java.lang.String");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            int i = az.a ? 3 : 10;
            bb.a("start version check in " + i + URLConfig.baidu_url);
            sleep(i * 1000);
            a();
            a(this.a);
        } catch (Exception e) {
            bb.a(e);
        }
        boolean unused = av.b = false;
    }
}
