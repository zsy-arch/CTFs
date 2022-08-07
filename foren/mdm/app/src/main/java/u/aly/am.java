package u.aly;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

/* compiled from: NetworkHelper.java */
/* loaded from: classes2.dex */
public class am {
    private String a;
    private String b = "10.0.0.172";
    private int c = 80;
    private Context d;
    private ak e;

    public am(Context context) {
        this.d = context;
        this.a = a(context);
    }

    public void a(ak akVar) {
        this.e = akVar;
    }

    private void a() {
        bo.b("constructURLS");
        String d = x.a(this.d).b().d("");
        String c = x.a(this.d).b().c("");
        if (!TextUtils.isEmpty(d)) {
            a.f = bj.b(d);
        }
        if (!TextUtils.isEmpty(c)) {
            a.g = bj.b(c);
        }
        a.i = new String[]{a.f, a.g};
        if (bl.n(this.d)) {
            a.i = new String[]{a.g, a.f};
        } else {
            int b = ax.a(this.d).b();
            if (b != -1) {
                if (b == 0) {
                    a.i = new String[]{a.f, a.g};
                } else if (b == 1) {
                    a.i = new String[]{a.g, a.f};
                }
            }
        }
        bo.b("constructURLS list size:" + a.i.length);
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        int i = 0;
        while (true) {
            if (i >= a.i.length) {
                break;
            }
            bArr2 = a(bArr, a.i[i]);
            if (bArr2 == null) {
                if (this.e != null) {
                    this.e.d();
                }
                i++;
            } else if (this.e != null) {
                this.e.c();
            }
        }
        return bArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r0.equals("uniwap") != false) goto L_0x0058;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b() {
        /*
            r5 = this;
            r2 = 1
            r1 = 0
            android.content.Context r0 = r5.d
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            java.lang.String r3 = "android.permission.ACCESS_NETWORK_STATE"
            android.content.Context r4 = r5.d
            java.lang.String r4 = r4.getPackageName()
            int r0 = r0.checkPermission(r3, r4)
            if (r0 == 0) goto L_0x0018
            r0 = r1
        L_0x0017:
            return r0
        L_0x0018:
            android.content.Context r0 = r5.d     // Catch: Exception -> 0x005a
            java.lang.String r3 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r3)     // Catch: Exception -> 0x005a
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch: Exception -> 0x005a
            android.content.Context r3 = r5.d     // Catch: Exception -> 0x005a
            java.lang.String r4 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r3 = u.aly.bl.a(r3, r4)     // Catch: Exception -> 0x005a
            if (r3 != 0) goto L_0x002e
            r0 = r1
            goto L_0x0017
        L_0x002e:
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch: Exception -> 0x005a
            if (r0 == 0) goto L_0x005e
            int r3 = r0.getType()     // Catch: Exception -> 0x005a
            if (r3 == r2) goto L_0x005e
            java.lang.String r0 = r0.getExtraInfo()     // Catch: Exception -> 0x005a
            if (r0 == 0) goto L_0x005e
            java.lang.String r3 = "cmwap"
            boolean r3 = r0.equals(r3)     // Catch: Exception -> 0x005a
            if (r3 != 0) goto L_0x0058
            java.lang.String r3 = "3gwap"
            boolean r3 = r0.equals(r3)     // Catch: Exception -> 0x005a
            if (r3 != 0) goto L_0x0058
            java.lang.String r3 = "uniwap"
            boolean r0 = r0.equals(r3)     // Catch: Exception -> 0x005a
            if (r0 == 0) goto L_0x005e
        L_0x0058:
            r0 = r2
            goto L_0x0017
        L_0x005a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005e:
            r0 = r1
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.am.b():boolean");
    }

    private byte[] a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        boolean z = true;
        try {
            if (this.e != null) {
                this.e.a();
            }
            if (b()) {
                httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.b, this.c)));
            } else {
                httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection2 = null;
        }
        try {
            httpURLConnection2.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpURLConnection2.setRequestProperty("X-Umeng-Sdk", this.a);
            httpURLConnection2.setRequestProperty("Msg-Type", "envelope/json");
            httpURLConnection2.setRequestProperty("Content-Type", "envelope/json");
            httpURLConnection2.setConnectTimeout(10000);
            httpURLConnection2.setReadTimeout(30000);
            httpURLConnection2.setRequestMethod("POST");
            httpURLConnection2.setDoOutput(true);
            httpURLConnection2.setDoInput(true);
            httpURLConnection2.setUseCaches(false);
            if (Build.VERSION.SDK_INT < 8) {
                System.setProperty("http.keepAlive", "false");
            }
            OutputStream outputStream = httpURLConnection2.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
            outputStream.close();
            if (this.e != null) {
                this.e.b();
            }
            int responseCode = httpURLConnection2.getResponseCode();
            String headerField = httpURLConnection2.getHeaderField("Content-Type");
            if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                z = false;
            }
            if (responseCode != 200 || !z) {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            }
            bo.c("Send message to " + str);
            InputStream inputStream = httpURLConnection2.getInputStream();
            byte[] b = bm.b(inputStream);
            bm.c(inputStream);
            if (httpURLConnection2 == null) {
                return b;
            }
            httpURLConnection2.disconnect();
            return b;
        } catch (Throwable th3) {
            th = th3;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append(a.c);
        stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(bl.v(context));
            stringBuffer2.append("/");
            stringBuffer2.append(bl.b(context));
            stringBuffer2.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(Build.VERSION.RELEASE);
            stringBuffer2.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer2.append(bm.a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }
}
