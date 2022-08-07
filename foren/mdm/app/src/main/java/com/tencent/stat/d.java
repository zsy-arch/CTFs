package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.hy.http.HttpEntity;
import com.tencent.stat.a.e;
import com.tencent.stat.a.f;
import com.tencent.stat.common.StatConstants;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.yolanda.nohttp.Headers;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class d {
    private static StatLogger c = k.b();
    private static long d = -1;
    private static d e = null;
    private static Context f = null;
    DefaultHttpClient a;
    Handler b;

    private d() {
        this.a = null;
        this.b = null;
        try {
            HandlerThread handlerThread = new HandlerThread("StatDispatcher");
            handlerThread.start();
            d = handlerThread.getId();
            this.b = new Handler(handlerThread.getLooper());
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.a = new DefaultHttpClient(basicHttpParams);
            this.a.setKeepAliveStrategy(new e(this));
            if (StatConfig.b() != null) {
                this.a.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, StatConfig.b());
            }
        } catch (Throwable th) {
            c.e(th);
        }
    }

    public static Context a() {
        return f;
    }

    public static void a(Context context) {
        f = context.getApplicationContext();
    }

    public static synchronized d b() {
        d dVar;
        synchronized (d.class) {
            if (e == null) {
                e = new d();
            }
            dVar = e;
        }
        return dVar;
    }

    public void a(e eVar, c cVar) {
        b(Arrays.asList(eVar.d()), cVar);
    }

    public void a(List<String> list, c cVar) {
        boolean z = false;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        String statReportUrl = StatConfig.getStatReportUrl();
        c.i("[" + statReportUrl + "]Send request(" + sb.toString().length() + "bytes):" + sb.toString());
        HttpPost httpPost = new HttpPost(statReportUrl);
        httpPost.addHeader(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        httpPost.setHeader("Connection", HTTP.CONN_KEEP_ALIVE);
        httpPost.removeHeaders("Cache-Control");
        HttpHost a = k.a(f);
        if (a != null) {
            this.a.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, k.a(f));
            httpPost.addHeader("X-Online-Host", StatConstants.MTA_SERVER);
            httpPost.addHeader(Headers.HEAD_KEY_ACCEPT, HttpEntity.WILDCARD);
            httpPost.addHeader("Content-Type", "json");
            z = true;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = sb.toString().getBytes("UTF-8");
        int length = bytes.length;
        if (sb.length() >= 256) {
            if (a == null) {
                httpPost.addHeader("Content-Encoding", "rc4,gzip");
            } else {
                httpPost.addHeader("X-Content-Encoding", "rc4,gzip");
            }
            byteArrayOutputStream.write(new byte[4]);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
            ByteBuffer.wrap(bytes, 0, 4).putInt(length);
            c.d("before Gzip:" + length + " bytes, after Gzip:" + bytes.length + " bytes");
        } else if (a == null) {
            httpPost.addHeader("Content-Encoding", "rc4");
        } else {
            httpPost.addHeader("X-Content-Encoding", "rc4");
        }
        httpPost.setEntity(new ByteArrayEntity(com.tencent.stat.common.e.a(bytes)));
        HttpResponse execute = this.a.execute(httpPost);
        if (z) {
            this.a.getParams().removeParameter(ConnRoutePNames.DEFAULT_PROXY);
        }
        org.apache.http.HttpEntity entity = execute.getEntity();
        int statusCode = execute.getStatusLine().getStatusCode();
        long contentLength = entity.getContentLength();
        c.i("recv response status code:" + statusCode + ", content length:" + contentLength);
        if (contentLength == 0) {
            EntityUtils.toString(entity);
            if (statusCode != 200) {
                c.error("Server response error code:" + statusCode);
            } else if (cVar != null) {
                cVar.a();
            }
        } else if (contentLength > 0) {
            InputStream content = entity.getContent();
            DataInputStream dataInputStream = new DataInputStream(content);
            byte[] bArr = new byte[(int) entity.getContentLength()];
            dataInputStream.readFully(bArr);
            content.close();
            dataInputStream.close();
            Header firstHeader = execute.getFirstHeader("Content-Encoding");
            if (firstHeader != null) {
                if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                    bArr = com.tencent.stat.common.e.b(k.a(bArr));
                } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                    bArr = k.a(com.tencent.stat.common.e.b(bArr));
                } else if (firstHeader.getValue().equalsIgnoreCase("gzip")) {
                    bArr = k.a(bArr);
                } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                    bArr = com.tencent.stat.common.e.b(bArr);
                }
            }
            if (statusCode == 200) {
                try {
                    String str = new String(bArr, "UTF-8");
                    c.d(str);
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.isNull("cfg")) {
                        StatConfig.a(jSONObject.getJSONObject("cfg"));
                    }
                    if (!jSONObject.isNull("et") && !jSONObject.isNull("st")) {
                        c.d("get mid respone:" + str);
                        if (jSONObject.getInt("et") == f.SESSION_ENV.a()) {
                            int i2 = jSONObject.getInt("st");
                            switch (i2) {
                                case -1:
                                case 0:
                                    if (!jSONObject.isNull(DeviceInfo.TAG_MID)) {
                                        StatMid.updateDeviceInfo(f, jSONObject.getString(DeviceInfo.TAG_MID));
                                        break;
                                    }
                                    break;
                                default:
                                    c.e("error type for st:" + i2);
                                    break;
                            }
                        }
                    }
                } catch (Throwable th) {
                    c.i(th.toString());
                }
                if (cVar != null) {
                    cVar.a();
                }
            } else {
                c.error("Server response error code:" + statusCode + ", error:" + new String(bArr, "UTF-8"));
            }
            content.close();
        } else {
            EntityUtils.toString(entity);
        }
        byteArrayOutputStream.close();
    }

    public void b(List<String> list, c cVar) {
        if (!list.isEmpty() && this.b != null) {
            this.b.post(new f(this, list, cVar));
        }
    }
}
