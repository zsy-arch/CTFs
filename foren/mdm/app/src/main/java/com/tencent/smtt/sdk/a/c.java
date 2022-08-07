package com.tencent.smtt.sdk.a;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.p;
import com.tencent.smtt.utils.x;
import com.yolanda.nohttp.Headers;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class c extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ ThirdAppInfoNew b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(String str, Context context, ThirdAppInfoNew thirdAppInfoNew) {
        super(str);
        this.a = context;
        this.b = thirdAppInfoNew;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        JSONObject jSONObject;
        if (Build.VERSION.SDK_INT >= 8) {
            if (b.a == null) {
                try {
                    b.a = "65dRa93L".getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    b.a = null;
                    TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                }
            }
            if (b.a == null) {
                TbsLog.e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                return;
            }
            String string = TbsDownloadConfig.getInstance(this.a).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
            String str = "";
            String str2 = "";
            if (!TextUtils.isEmpty(string)) {
                str = string.substring(0, string.indexOf(a.b));
                str2 = string.substring(string.indexOf(a.b) + 1, string.length());
            }
            boolean z = TextUtils.isEmpty(str) || str.length() != 96 || TextUtils.isEmpty(str2) || str2.length() != 24;
            try {
                x a = x.a();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(z ? a.b() + p.a().b() : a.f() + str).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", Headers.HEAD_VALUE_CONNECTION_CLOSE);
                }
                try {
                    jSONObject = b.c(this.b, this.a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    jSONObject = null;
                }
                if (jSONObject == null) {
                    TbsLog.e("sdkreport", "post -- jsonData is null!");
                    return;
                }
                try {
                    byte[] bytes = jSONObject.toString().getBytes("utf-8");
                    byte[] a2 = z ? p.a().a(bytes) : p.a(bytes, str2);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a2.length));
                    try {
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        outputStream.write(a2);
                        outputStream.flush();
                        if (httpURLConnection.getResponseCode() != 200) {
                            TbsLog.e("sdkreport", "Post failed -- not 200");
                        }
                    } catch (Throwable th) {
                        TbsLog.e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                    }
                } catch (Throwable th2) {
                }
            } catch (IOException e3) {
                TbsLog.e("sdkreport", "Post failed -- IOException:" + e3);
            } catch (AssertionError e4) {
                TbsLog.e("sdkreport", "Post failed -- AssertionError:" + e4);
            } catch (NoClassDefFoundError e5) {
                TbsLog.e("sdkreport", "Post failed -- NoClassDefFoundError:" + e5);
            }
        }
    }
}
