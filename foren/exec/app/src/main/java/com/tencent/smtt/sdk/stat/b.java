package com.tencent.smtt.sdk.stat;

import a.a;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.g;
import com.tencent.smtt.utils.m;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static byte[] f1413a;

    static {
        try {
            f1413a = "65dRa93L".getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public static String a(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray != null) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(byteArray);
                byte[] digest = instance.digest();
                if (digest != null) {
                    StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
                    if (digest.length <= 0) {
                        return null;
                    }
                    for (int i = 0; i < digest.length; i++) {
                        String upperCase = Integer.toHexString(digest[i] & 255).toUpperCase();
                        if (i > 0) {
                            sb.append(":");
                        }
                        if (upperCase.length() < 2) {
                            sb.append(0);
                        }
                        sb.append(upperCase);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static void a(final a aVar, final Context context) {
        new Thread("HttpUtils") { // from class: com.tencent.smtt.sdk.stat.b.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String str;
                String str2;
                StringBuilder sb;
                String str3;
                String sb2;
                String str4;
                com.tencent.smtt.utils.b.b(context, aVar.f4e);
                aVar.k = com.tencent.smtt.utils.b.a();
                int i = Build.VERSION.SDK_INT;
                JSONObject jSONObject = null;
                if (b.f1413a == null) {
                    try {
                        b.f1413a = "65dRa93L".getBytes("utf-8");
                    } catch (UnsupportedEncodingException unused) {
                        b.f1413a = null;
                        TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                    }
                }
                if (b.f1413a == null) {
                    sb2 = "Post failed -- POST_DATA_KEY is null!";
                } else {
                    String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, BuildConfig.FLAVOR);
                    if (!TextUtils.isEmpty(string)) {
                        str = string.substring(0, string.indexOf("&"));
                        str2 = string.substring(string.indexOf("&") + 1, string.length());
                    } else {
                        str2 = BuildConfig.FLAVOR;
                        str = str2;
                    }
                    boolean z = TextUtils.isEmpty(str) || str.length() != 96 || TextUtils.isEmpty(str2) || str2.length() != 24;
                    try {
                        m a2 = m.a();
                        if (z) {
                            str4 = a2.b() + g.a().b();
                        } else {
                            str4 = a2.f() + str;
                        }
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str4).openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setConnectTimeout(20000);
                        int i2 = Build.VERSION.SDK_INT;
                        httpURLConnection.setRequestProperty("Connection", "close");
                        try {
                            jSONObject = b.c(aVar, context);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (jSONObject == null) {
                            sb2 = "post -- jsonData is null!";
                        } else {
                            try {
                                byte[] bytes = jSONObject.toString().getBytes("utf-8");
                                byte[] a3 = z ? g.a().a(bytes) : g.a(bytes, str2);
                                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a3.length));
                                try {
                                    OutputStream outputStream = httpURLConnection.getOutputStream();
                                    outputStream.write(a3);
                                    outputStream.flush();
                                    if (httpURLConnection.getResponseCode() == 200) {
                                        TbsLog.i("sdkreport", "Post successful!");
                                        TbsLog.i("sdkreport", "SIGNATURE is " + jSONObject.getString("SIGNATURE"));
                                        b.b(context, b.b(httpURLConnection, str2, z));
                                        new TbsDownloadUpload(context).clearUploadCode();
                                        return;
                                    }
                                    TbsLog.e("sdkreport", "Post failed -- not 200 code is " + httpURLConnection.getResponseCode());
                                    TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                                    tbsLogInfo.setErrorCode(TbsListener.ErrorCode.PV_UPLOAD_ERROR);
                                    tbsLogInfo.setFailDetail(BuildConfig.FLAVOR + httpURLConnection.getResponseCode());
                                    TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                                    return;
                                } catch (Throwable th) {
                                    StringBuilder a4 = e.a.a.a.a.a("Post failed -- exceptions:");
                                    a4.append(th.getMessage());
                                    TbsLog.e("sdkreport", a4.toString());
                                    TbsLogReport.TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(context).tbsLogInfo();
                                    tbsLogInfo2.setErrorCode(TbsListener.ErrorCode.PV_UPLOAD_ERROR);
                                    tbsLogInfo2.setFailDetail(th);
                                    TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo2);
                                    return;
                                }
                            } catch (Throwable unused2) {
                                return;
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        sb = new StringBuilder();
                        str3 = "Post failed -- IOException:";
                        sb.append(str3);
                        sb.append(e);
                        sb2 = sb.toString();
                        TbsLog.e("sdkreport", sb2);
                    } catch (AssertionError e4) {
                        e = e4;
                        sb = new StringBuilder();
                        str3 = "Post failed -- AssertionError:";
                        sb.append(str3);
                        sb.append(e);
                        sb2 = sb.toString();
                        TbsLog.e("sdkreport", sb2);
                    } catch (NoClassDefFoundError e5) {
                        e = e5;
                        sb = new StringBuilder();
                        str3 = "Post failed -- NoClassDefFoundError:";
                        sb.append(str3);
                        sb.append(e);
                        sb2 = sb.toString();
                        TbsLog.e("sdkreport", sb2);
                    }
                }
                TbsLog.e("sdkreport", sb2);
            }
        }.start();
    }

    public static void a(Context context, String str, String str2) {
        if ("reset".equals(str) && "true".equals(str2)) {
            QbSdk.reset(context, false);
        } else if (str.startsWith("rmfile")) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("tbs_status", 0);
                if (!sharedPreferences.getBoolean(str, false)) {
                    File file = new File(str2);
                    if (str2 != null && file.exists()) {
                        TbsLog.i("HttpUtils", "received command,delete" + str2);
                        FileUtil.a(file, false);
                    }
                    sharedPreferences.edit().putBoolean(str, true).apply();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            TbsPVConfig.getInstance(context).putData(str, str2);
        }
    }

    public static String b(HttpURLConnection httpURLConnection, String str, boolean z) {
        Throwable th;
        InputStream inputStream;
        String str2;
        Exception e2;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            InputStream inputStream2 = httpURLConnection.getInputStream();
            String contentEncoding = httpURLConnection.getContentEncoding();
            inputStream = (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip")) ? (contentEncoding == null || !contentEncoding.equalsIgnoreCase("deflate")) ? inputStream2 : new InflaterInputStream(inputStream2, new Inflater(true)) : new GZIPInputStream(inputStream2);
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Exception e3) {
                    e2 = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e2 = e4;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[TbsListener.ErrorCode.DOWNLOAD_INTERRUPT];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            str2 = z ? new String(g.a().c(byteArrayOutputStream.toByteArray())) : new String(g.b(byteArrayOutputStream.toByteArray(), str));
            try {
                byteArrayOutputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        } catch (Exception e7) {
            e2 = e7;
            byteArrayOutputStream2 = byteArrayOutputStream;
            e2.printStackTrace();
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            str2 = BuildConfig.FLAVOR;
            TbsLog.i("HttpUtils", "getResponseFromConnection,response=" + str2 + ";isUseRSA=" + z);
            return str2;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
            }
            throw th;
        }
        TbsLog.i("HttpUtils", "getResponseFromConnection,response=" + str2 + ";isUseRSA=" + z);
        return str2;
    }

    public static void b(Context context, String str) {
        try {
            TbsPVConfig.releaseInstance();
            TbsPVConfig.getInstance(context).clear();
            if (!TextUtils.isEmpty(str)) {
                for (String str2 : str.split("\\|")) {
                    try {
                        String[] split = str2.split("=");
                        if (split.length == 2) {
                            a(context, split[0], split[1]);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                TbsPVConfig.getInstance(context).commit();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:2|46|3|4|(13:11|(1:13)|14|16|17|(1:19)(1:20)|21|(5:23|24|(1:26)(1:27)|28|(1:30))|31|48|32|(4:34|35|(1:37)(2:39|(1:41))|38)|42)|15|14|16|17|(0)(0)|21|(0)|31|48|32|(0)|42|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e7 A[Catch: Exception -> 0x015d, TRY_ENTER, TryCatch #0 {Exception -> 0x015d, blocks: (B:3:0x0006, B:6:0x0079, B:8:0x0081, B:11:0x008c, B:14:0x0092, B:15:0x0096, B:16:0x00d2, B:19:0x00e7, B:20:0x00ef, B:21:0x00ff, B:23:0x0103, B:26:0x0119, B:27:0x0125, B:30:0x012b, B:31:0x0130), top: B:46:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ef A[Catch: Exception -> 0x015d, TryCatch #0 {Exception -> 0x015d, blocks: (B:3:0x0006, B:6:0x0079, B:8:0x0081, B:11:0x008c, B:14:0x0092, B:15:0x0096, B:16:0x00d2, B:19:0x00e7, B:20:0x00ef, B:21:0x00ff, B:23:0x0103, B:26:0x0119, B:27:0x0125, B:30:0x012b, B:31:0x0130), top: B:46:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0103 A[Catch: Exception -> 0x015d, TRY_LEAVE, TryCatch #0 {Exception -> 0x015d, blocks: (B:3:0x0006, B:6:0x0079, B:8:0x0081, B:11:0x008c, B:14:0x0092, B:15:0x0096, B:16:0x00d2, B:19:0x00e7, B:20:0x00ef, B:21:0x00ff, B:23:0x0103, B:26:0x0119, B:27:0x0125, B:30:0x012b, B:31:0x0130), top: B:46:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x013b A[Catch: Exception -> 0x015c, TRY_LEAVE, TryCatch #1 {Exception -> 0x015c, blocks: (B:32:0x0137, B:34:0x013b, B:37:0x0148, B:38:0x014a, B:39:0x0151, B:41:0x0159), top: B:48:0x0137 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject c(a.a r12, android.content.Context r13) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.b.c(a.a, android.content.Context):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x010b, code lost:
        if (r14 != false) goto L_0x010d;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ad A[Catch: Throwable -> 0x0138, TryCatch #2 {Throwable -> 0x0138, blocks: (B:22:0x0069, B:24:0x00ad, B:25:0x00af, B:27:0x00b3, B:28:0x00b6, B:29:0x00bc, B:31:0x00d0, B:33:0x00d6, B:35:0x00da, B:37:0x00e0, B:38:0x00e2, B:40:0x00e8, B:42:0x00ec, B:44:0x00f2, B:45:0x00f4, B:48:0x0101, B:50:0x0105, B:51:0x0109, B:53:0x010d, B:54:0x0110, B:58:0x0117, B:62:0x011e, B:64:0x0128, B:65:0x0130), top: B:73:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b3 A[Catch: Throwable -> 0x0138, TryCatch #2 {Throwable -> 0x0138, blocks: (B:22:0x0069, B:24:0x00ad, B:25:0x00af, B:27:0x00b3, B:28:0x00b6, B:29:0x00bc, B:31:0x00d0, B:33:0x00d6, B:35:0x00da, B:37:0x00e0, B:38:0x00e2, B:40:0x00e8, B:42:0x00ec, B:44:0x00f2, B:45:0x00f4, B:48:0x0101, B:50:0x0105, B:51:0x0109, B:53:0x010d, B:54:0x0110, B:58:0x0117, B:62:0x011e, B:64:0x0128, B:65:0x0130), top: B:73:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6 A[Catch: Throwable -> 0x0138, TryCatch #2 {Throwable -> 0x0138, blocks: (B:22:0x0069, B:24:0x00ad, B:25:0x00af, B:27:0x00b3, B:28:0x00b6, B:29:0x00bc, B:31:0x00d0, B:33:0x00d6, B:35:0x00da, B:37:0x00e0, B:38:0x00e2, B:40:0x00e8, B:42:0x00ec, B:44:0x00f2, B:45:0x00f4, B:48:0x0101, B:50:0x0105, B:51:0x0109, B:53:0x010d, B:54:0x0110, B:58:0x0117, B:62:0x011e, B:64:0x0128, B:65:0x0130), top: B:73:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e8 A[Catch: Throwable -> 0x0138, TryCatch #2 {Throwable -> 0x0138, blocks: (B:22:0x0069, B:24:0x00ad, B:25:0x00af, B:27:0x00b3, B:28:0x00b6, B:29:0x00bc, B:31:0x00d0, B:33:0x00d6, B:35:0x00da, B:37:0x00e0, B:38:0x00e2, B:40:0x00e8, B:42:0x00ec, B:44:0x00f2, B:45:0x00f4, B:48:0x0101, B:50:0x0105, B:51:0x0109, B:53:0x010d, B:54:0x0110, B:58:0x0117, B:62:0x011e, B:64:0x0128, B:65:0x0130), top: B:73:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0128 A[Catch: Throwable -> 0x0138, TryCatch #2 {Throwable -> 0x0138, blocks: (B:22:0x0069, B:24:0x00ad, B:25:0x00af, B:27:0x00b3, B:28:0x00b6, B:29:0x00bc, B:31:0x00d0, B:33:0x00d6, B:35:0x00da, B:37:0x00e0, B:38:0x00e2, B:40:0x00e8, B:42:0x00ec, B:44:0x00f2, B:45:0x00f4, B:48:0x0101, B:50:0x0105, B:51:0x0109, B:53:0x010d, B:54:0x0110, B:58:0x0117, B:62:0x011e, B:64:0x0128, B:65:0x0130), top: B:73:0x0069 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, boolean r11, long r12, boolean r14) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.b.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, int, boolean, long, boolean):void");
    }
}
