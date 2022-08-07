package com.baidu.mobstat;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public class ak {
    private static ak a;
    private Handler b;

    private ak() {
        HandlerThread handlerThread = new HandlerThread("LogSender");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
    }

    public static ak a() {
        if (a == null) {
            synchronized (ak.class) {
                if (a == null) {
                    a = new ak();
                }
            }
        }
        return a;
    }

    /* JADX WARN: Finally extract failed */
    private String a(Context context, String str, String str2) {
        HttpURLConnection d;
        byte[] bytes;
        try {
            d = cl.d(context, str);
            d.setDoOutput(true);
            d.setInstanceFollowRedirects(false);
            d.setUseCaches(false);
            d.setRequestProperty("Content-Encoding", "gzip");
            d.connect();
            try {
                OutputStream outputStream = d.getOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
                gZIPOutputStream.write(new byte[]{0, 0, 0, 1});
                gZIPOutputStream.write(new byte[]{0, 0, 3, -14});
                gZIPOutputStream.write(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
                gZIPOutputStream.write(new byte[]{0, 2});
                if (ba.b) {
                    gZIPOutputStream.write(new byte[]{0, 1});
                } else {
                    gZIPOutputStream.write(new byte[]{0, 0});
                }
                gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
                if (ba.b) {
                    byte[] a2 = cj.a();
                    byte[] a3 = cs.a(false, cn.a(), a2);
                    gZIPOutputStream.write(a(a3.length, 4));
                    gZIPOutputStream.write(a3);
                    bytes = cj.a(a2, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, str2.getBytes("utf-8"));
                    gZIPOutputStream.write(a(bytes.length, 2));
                } else {
                    bytes = str2.getBytes("utf-8");
                }
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                outputStream.close();
                int responseCode = d.getResponseCode();
                int contentLength = d.getContentLength();
                bb.c("code: " + responseCode + "; len: " + contentLength);
                if (responseCode == 200 && contentLength == 0) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            String sb2 = sb.toString();
                            d.disconnect();
                            return sb2;
                        }
                        sb.append(readLine);
                    }
                } else {
                    throw new IOException("Response code = " + d.getResponseCode());
                }
            } catch (Exception e) {
                bb.b(e);
                d.disconnect();
                return "";
            }
        } catch (Throwable th) {
            d.disconnect();
            throw th;
        }
    }

    public void a(Context context) {
        File[] listFiles;
        if ("mounted".equals(cl.a())) {
            File file = new File(Environment.getExternalStorageDirectory(), "backups/system");
            if (!(!file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0)) {
                try {
                    Arrays.sort(listFiles, new am(this));
                } catch (Exception e) {
                    bb.b(e);
                }
                int i = 0;
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        String name = file2.getName();
                        if (!TextUtils.isEmpty(name) && name.startsWith("__send_log_data_")) {
                            String str = "backups/system" + File.separator + name;
                            if (b(context, cl.b(str))) {
                                cl.c(str);
                                i = 0;
                            } else {
                                i++;
                                if (i >= 5) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(String str) {
        cl.a("backups/system" + File.separator + "__send_log_data_" + System.currentTimeMillis(), str, false);
    }

    private static byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[(i - i2) - 1] = (byte) (255 & j);
            j >>= 8;
        }
        return bArr;
    }

    private boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            a(context, "https://openrcv.baidu.com/1010/bplus.gif", str);
            return true;
        } catch (Exception e) {
            bb.c(e);
            return false;
        }
    }

    public void a(Context context, String str) {
        bb.a("data = " + str);
        if (str != null && !"".equals(str)) {
            this.b.post(new al(this, str, context));
        }
    }
}
