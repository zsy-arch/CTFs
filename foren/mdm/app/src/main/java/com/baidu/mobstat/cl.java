package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/* loaded from: classes.dex */
public final class cl {
    private static final Proxy a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
    private static final Proxy b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static File a(String str) {
        if (!"mounted".equals(a())) {
            return null;
        }
        return new File(Environment.getExternalStorageDirectory(), str);
    }

    public static String a() {
        try {
            return Environment.getExternalStorageState();
        } catch (Exception e) {
            return null;
        }
    }

    public static String a(Context context, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        byte[] a2;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = context.openFileInput(str);
        } catch (Exception e) {
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            a2 = a(fileInputStream);
        } catch (Exception e2) {
            fileInputStream2 = fileInputStream;
            cq.a(fileInputStream2);
            return "";
        } catch (Throwable th3) {
            th = th3;
            cq.a(fileInputStream);
            throw th;
        }
        if (a2 != null) {
            String str2 = new String(a2, "utf-8");
            cq.a(fileInputStream);
            return str2;
        }
        cq.a(fileInputStream);
        return "";
    }

    @SuppressLint({"DefaultLocale"})
    public static HttpURLConnection a(Context context, String str, int i, int i2) {
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        HttpURLConnection httpURLConnection = null;
        if (networkInfo2 != null && networkInfo2.isAvailable()) {
            cr.a("WIFI is available");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else if (networkInfo != null && networkInfo.isAvailable()) {
            String extraInfo = networkInfo.getExtraInfo();
            String lowerCase = extraInfo != null ? extraInfo.toLowerCase() : "";
            cr.a(lowerCase);
            if (lowerCase.startsWith("cmwap") || lowerCase.startsWith("uniwap") || lowerCase.startsWith("3gwap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(a);
            } else if (lowerCase.startsWith("ctwap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(b);
            }
        }
        if (httpURLConnection == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }

    public static void a(Context context, String str, String str2, boolean z) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream openFileOutput;
        if (context != null) {
            try {
                try {
                    openFileOutput = context.openFileOutput(str, z ? 32768 : 0);
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
                try {
                    cq.a(new ByteArrayInputStream(str2.getBytes("utf-8")), openFileOutput);
                    cq.a(openFileOutput);
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = openFileOutput;
                    cq.a(fileOutputStream);
                    throw th;
                }
            } catch (Exception e) {
                cq.a(null);
            }
        }
    }

    public static void a(String str, String str2, boolean z) {
        Throwable th;
        FileOutputStream fileOutputStream;
        File parentFile;
        FileOutputStream fileOutputStream2 = null;
        try {
            File a2 = a(str);
            if (a2 != null) {
                if (!a2.exists() && (parentFile = a2.getParentFile()) != null) {
                    parentFile.mkdirs();
                }
                fileOutputStream = new FileOutputStream(a2, z);
                try {
                    cq.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStream);
                } catch (Exception e) {
                    fileOutputStream2 = fileOutputStream;
                    cq.a(fileOutputStream2);
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    cq.a(fileOutputStream2);
                    throw th;
                }
            } else {
                fileOutputStream = null;
            }
            cq.a(fileOutputStream);
        } catch (Exception e2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (cq.a(inputStream, byteArrayOutputStream)) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public static String b(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        File a2 = a(str);
        if (a2 != null && a2.exists()) {
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = new FileInputStream(a2);
                try {
                    byte[] a3 = a(fileInputStream);
                    if (a3 != null) {
                        String str2 = new String(a3, "utf-8");
                        cq.a(fileInputStream);
                        return str2;
                    }
                    cq.a(fileInputStream);
                } catch (Exception e) {
                    fileInputStream2 = fileInputStream;
                    cq.a(fileInputStream2);
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    cq.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        }
        return "";
    }

    public static boolean b(Context context, String str) {
        return context.deleteFile(str);
    }

    public static boolean c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    public static boolean c(String str) {
        File a2 = a(str);
        if (a2 == null || !a2.isFile()) {
            return false;
        }
        return a2.delete();
    }

    public static HttpURLConnection d(Context context, String str) {
        return a(context, str, 50000, 50000);
    }

    public static boolean e(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) != -1;
        } catch (Exception e) {
            cr.b("Check permission failed.");
            return false;
        }
    }
}
