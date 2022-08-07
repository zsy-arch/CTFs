package com.mob.tools.network;

import android.content.Context;
import android.os.Build;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.util.h;
import com.hyphenate.chat.MessageEncoder;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.mob.tools.utils.ReflectHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import u.aly.av;

/* loaded from: classes2.dex */
public class NetworkHelper {
    public static int connectionTimeout;
    public static int readTimout;

    /* loaded from: classes2.dex */
    public static class NetworkTimeOut {
        public int connectionTimeout;
        public int readTimout;
    }

    /* loaded from: classes2.dex */
    public static final class SimpleX509TrustManager implements X509TrustManager {
        private X509TrustManager standardTrustManager;

        private SimpleX509TrustManager(KeyStore keystore) {
            try {
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                tmf.init(keystore);
                TrustManager[] trustManagers = tmf.getTrustManagers();
                if (trustManagers == null || trustManagers.length == 0) {
                    throw new NoSuchAlgorithmException("no trust manager found.");
                }
                this.standardTrustManager = (X509TrustManager) trustManagers[0];
            } catch (Exception e) {
                MobLog.getInstance().d("failed to initialize the standard trust manager: " + e.getMessage(), new Object[0]);
                this.standardTrustManager = null;
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
            if (certificates == null) {
                throw new IllegalArgumentException("there were no certificates.");
            } else if (certificates.length == 1) {
                certificates[0].checkValidity();
            } else if (this.standardTrustManager != null) {
                this.standardTrustManager.checkServerTrusted(certificates, authType);
            } else {
                throw new CertificateException("there were one more certificates but no trust manager found.");
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private HttpURLConnection getConnection(String urlStr, NetworkTimeOut timeout) throws Throwable {
        Object obj;
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        String filedName = "methodTokens";
        boolean staticType = false;
        Object methods = null;
        try {
            methods = 0 != 0 ? ReflectHelper.getStaticField("HttpURLConnection", filedName) : ReflectHelper.getInstanceField(conn, filedName);
        } catch (Throwable th) {
        }
        if (methods == null) {
            filedName = "PERMITTED_USER_METHODS";
            staticType = true;
            try {
                methods = 1 != 0 ? ReflectHelper.getStaticField("HttpURLConnection", filedName) : ReflectHelper.getInstanceField(conn, filedName);
                obj = methods;
            } catch (Throwable th2) {
                obj = methods;
            }
        } else {
            obj = methods;
        }
        if (obj != null) {
            String[] methodTokens = (String[]) obj;
            String[] myMethodTokens = new String[methodTokens.length + 1];
            System.arraycopy(methodTokens, 0, myMethodTokens, 0, methodTokens.length);
            myMethodTokens[methodTokens.length] = HttpPatch.METHOD_NAME;
            if (staticType) {
                ReflectHelper.setStaticField("HttpURLConnection", filedName, myMethodTokens);
            } else {
                ReflectHelper.setInstanceField(conn, filedName, myMethodTokens);
            }
        }
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (conn instanceof HttpsURLConnection) {
            HostnameVerifier hostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
            HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new SimpleX509TrustManager(null)}, new SecureRandom());
            httpsConn.setSSLSocketFactory(sc.getSocketFactory());
            httpsConn.setHostnameVerifier(hostnameVerifier);
        }
        int connectionTimeout2 = timeout == null ? connectionTimeout : timeout.connectionTimeout;
        if (connectionTimeout2 > 0) {
            conn.setConnectTimeout(connectionTimeout2);
        }
        int readTimout2 = timeout == null ? readTimout : timeout.readTimout;
        if (readTimout2 > 0) {
            conn.setReadTimeout(readTimout2);
        }
        return conn;
    }

    private HTTPPart getFilePostHTTPPart(HttpURLConnection conn, String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> files) throws Throwable {
        String boundary = UUID.randomUUID().toString();
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        MultiPart mp = new MultiPart();
        StringPart sp = new StringPart();
        if (values != null) {
            Iterator<KVPair<String>> it = values.iterator();
            while (it.hasNext()) {
                KVPair<String> value = it.next();
                sp.append("--").append(boundary).append("\r\n");
                sp.append("Content-Disposition: form-data; name=\"").append(value.name).append("\"\r\n\r\n");
                sp.append(value.value).append("\r\n");
            }
        }
        mp.append(sp);
        Iterator<KVPair<String>> it2 = files.iterator();
        while (it2.hasNext()) {
            KVPair<String> file = it2.next();
            StringPart sp2 = new StringPart();
            File imageFile = new File(file.value);
            sp2.append("--").append(boundary).append("\r\n");
            sp2.append("Content-Disposition: form-data; name=\"").append(file.name).append("\"; filename=\"").append(imageFile.getName()).append("\"\r\n");
            String mime = URLConnection.getFileNameMap().getContentTypeFor(file.value);
            if (mime == null || mime.length() <= 0) {
                if (file.value.toLowerCase().endsWith("jpg") || file.value.toLowerCase().endsWith("jpeg")) {
                    mime = "image/jpeg";
                } else if (file.value.toLowerCase().endsWith("png")) {
                    mime = "image/png";
                } else if (file.value.toLowerCase().endsWith("gif")) {
                    mime = "image/gif";
                } else {
                    FileInputStream fis = new FileInputStream(file.value);
                    mime = URLConnection.guessContentTypeFromStream(fis);
                    fis.close();
                    if (mime == null || mime.length() <= 0) {
                        mime = "application/octet-stream";
                    }
                }
            }
            sp2.append("Content-Type: ").append(mime).append("\r\n\r\n");
            mp.append(sp2);
            FilePart fp = new FilePart();
            fp.setFile(file.value);
            mp.append(fp);
            StringPart sp3 = new StringPart();
            sp3.append("\r\n");
            mp.append(sp3);
        }
        StringPart sp4 = new StringPart();
        sp4.append("--").append(boundary).append("--\r\n");
        mp.append(sp4);
        return mp;
    }

    private HTTPPart getTextPostHTTPPart(HttpURLConnection conn, String url, ArrayList<KVPair<String>> values) throws Throwable {
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        StringPart sp = new StringPart();
        if (values != null) {
            sp.append(kvPairsToUrl(values));
        }
        return sp;
    }

    private void httpPatchImpl(String url, ArrayList<KVPair<String>> values, KVPair<String> file, long offset, ArrayList<KVPair<String>> headers, OnReadListener listener, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        HttpClient client;
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpPatch: " + url, new Object[0]);
        if (values != null) {
            String param = kvPairsToUrl(values);
            if (param.length() > 0) {
                url = url + "?" + param;
            }
        }
        HttpPatch patch = new HttpPatch(url);
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                patch.setHeader(header.name, header.value);
            }
        }
        FilePart fp = new FilePart();
        fp.setOnReadListener(listener);
        fp.setFile(file.value);
        fp.setOffset(offset);
        InputStreamEntity entity = new InputStreamEntity(fp.toInputStream(), fp.length() - offset);
        entity.setContentEncoding("application/offset+octet-stream");
        patch.setEntity(entity);
        BasicHttpParams httpParams = new BasicHttpParams();
        int connectionTimeout2 = timeout == null ? connectionTimeout : timeout.connectionTimeout;
        if (connectionTimeout2 > 0) {
            HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout2);
        }
        int readTimout2 = timeout == null ? readTimout : timeout.readTimout;
        if (readTimout2 > 0) {
            HttpConnectionParams.setSoTimeout(httpParams, readTimout2);
        }
        patch.setParams(httpParams);
        if (url.startsWith("https://")) {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactoryEx sf = new SSLSocketFactoryEx(trustStore);
            sf.allowAllHostnameVerifier();
            BasicHttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, "UTF-8");
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme(b.a, sf, 443));
            client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, registry), params);
        } else {
            client = new DefaultHttpClient();
        }
        HttpResponse httpResponse = client.execute(patch);
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl(httpResponse));
            } finally {
                client.getConnectionManager().shutdown();
            }
        } else {
            client.getConnectionManager().shutdown();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    private String kvPairsToUrl(ArrayList<KVPair<String>> values) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Iterator<KVPair<String>> it = values.iterator();
        while (it.hasNext()) {
            KVPair<String> value = it.next();
            String encodedName = Data.urlEncode(value.name, "utf-8");
            String encodedValue = value.value != null ? Data.urlEncode(value.value, "utf-8") : "";
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(encodedName).append('=').append(encodedValue);
        }
        return sb.toString();
    }

    public String downloadCache(Context context, String url, String cacheFolder, boolean skipIfCached, NetworkTimeOut timeout) throws Throwable {
        List<String> headers;
        int dot;
        List<String> headers2;
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("downloading: " + url, new Object[0]);
        if (skipIfCached) {
            File cache = new File(R.getCachePath(context, cacheFolder), Data.MD5(url));
            if (skipIfCached && cache.exists()) {
                MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
                return cache.getAbsolutePath();
            }
        }
        HttpURLConnection conn = getConnection(url, timeout);
        conn.connect();
        int status = conn.getResponseCode();
        if (status == 200) {
            String name = null;
            Map<String, List<String>> map = conn.getHeaderFields();
            if (!(map == null || (headers2 = map.get("Content-Disposition")) == null || headers2.size() <= 0)) {
                String[] parts = headers2.get(0).split(h.b);
                for (String part : parts) {
                    if (part.trim().startsWith(MessageEncoder.ATTR_FILENAME)) {
                        name = part.split("=")[1];
                        if (name.startsWith("\"") && name.endsWith("\"")) {
                            name = name.substring(1, name.length() - 1);
                        }
                    }
                }
            }
            if (name == null) {
                name = Data.MD5(url);
                if (!(map == null || (headers = map.get("Content-Type")) == null || headers.size() <= 0)) {
                    String value = headers.get(0);
                    String value2 = value == null ? "" : value.trim();
                    if (value2.startsWith("image/")) {
                        String type = value2.substring("image/".length());
                        StringBuilder append = new StringBuilder().append(name).append(".");
                        if ("jpeg".equals(type)) {
                            type = "jpg";
                        }
                        name = append.append(type).toString();
                    } else {
                        int index = url.lastIndexOf(47);
                        String lastPart = null;
                        if (index > 0) {
                            lastPart = url.substring(index + 1);
                        }
                        if (lastPart != null && lastPart.length() > 0 && (dot = lastPart.lastIndexOf(46)) > 0 && lastPart.length() - dot < 10) {
                            name = name + lastPart.substring(dot);
                        }
                    }
                }
            }
            File cache2 = new File(R.getCachePath(context, cacheFolder), name);
            if (!skipIfCached || !cache2.exists()) {
                if (!cache2.getParentFile().exists()) {
                    cache2.getParentFile().mkdirs();
                }
                if (cache2.exists()) {
                    cache2.delete();
                }
                try {
                    InputStream is = conn.getInputStream();
                    FileOutputStream fos = new FileOutputStream(cache2);
                    byte[] buf = new byte[1024];
                    for (int len = is.read(buf); len > 0; len = is.read(buf)) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    conn.disconnect();
                    MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
                    return cache2.getAbsolutePath();
                } catch (Throwable t) {
                    if (cache2.exists()) {
                        cache2.delete();
                    }
                    throw t;
                }
            } else {
                conn.disconnect();
                MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
                return cache2.getAbsolutePath();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
            for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(txt);
            }
            br.close();
            conn.disconnect();
            HashMap<String, Object> errMap = new HashMap<>();
            errMap.put(av.aG, sb.toString());
            errMap.put("status", Integer.valueOf(status));
            throw new Throwable(new Hashon().fromHashMap(errMap));
        }
    }

    public void getHttpPostResponse(String url, ArrayList<KVPair<String>> values, KVPair<String> file, ArrayList<KVPair<String>> headers, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        HTTPPart part;
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpPost: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        if (file == null || file.value == null || !new File(file.value).exists()) {
            part = getTextPostHTTPPart(conn, url, values);
        } else {
            ArrayList<KVPair<String>> files = new ArrayList<>();
            files.add(file);
            part = getFilePostHTTPPart(conn, url, values, files);
        }
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        InputStream is = part.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl23(conn));
            } finally {
                conn.disconnect();
            }
        } else {
            conn.disconnect();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    public String httpGet(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpGet: " + url, new Object[0]);
        if (values != null) {
            String param = kvPairsToUrl(values);
            if (param.length() > 0) {
                url = url + "?" + param;
            }
        }
        HttpURLConnection conn = getConnection(url, timeout);
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        int status = conn.getResponseCode();
        if (status == 200) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("utf-8")));
            for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(txt);
            }
            br.close();
            conn.disconnect();
            String resp = sb.toString();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
            return resp;
        }
        StringBuilder sb2 = new StringBuilder();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
        for (String txt2 = br2.readLine(); txt2 != null; txt2 = br2.readLine()) {
            if (sb2.length() > 0) {
                sb2.append('\n');
            }
            sb2.append(txt2);
        }
        br2.close();
        conn.disconnect();
        HashMap<String, Object> errMap = new HashMap<>();
        errMap.put(av.aG, sb2.toString());
        errMap.put("status", Integer.valueOf(status));
        throw new Throwable(new Hashon().fromHashMap(errMap));
    }

    public ArrayList<KVPair<String[]>> httpHead(String url, ArrayList<KVPair<String>> values, KVPair<String> file, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpHead: " + url, new Object[0]);
        if (values != null) {
            String param = kvPairsToUrl(values);
            if (param.length() > 0) {
                url = url + "?" + param;
            }
        }
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setRequestMethod(HttpHead.METHOD_NAME);
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        Map<String, List<String>> map = conn.getHeaderFields();
        ArrayList<KVPair<String[]>> list = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, List<String>> ent : map.entrySet()) {
                List<String> value = ent.getValue();
                if (value == null) {
                    list.add(new KVPair<>(ent.getKey(), new String[0]));
                } else {
                    String[] hds = new String[value.size()];
                    for (int i = 0; i < hds.length; i++) {
                        hds[i] = value.get(i);
                    }
                    list.add(new KVPair<>(ent.getKey(), hds));
                }
            }
        }
        conn.disconnect();
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
        return list;
    }

    public void httpPatch(String url, ArrayList<KVPair<String>> values, KVPair<String> file, long offset, ArrayList<KVPair<String>> headers, OnReadListener listener, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        if (Build.VERSION.SDK_INT >= 23) {
            httpPatchImpl23(url, values, file, offset, headers, listener, callback, timeout);
        } else {
            httpPatchImpl(url, values, file, offset, headers, listener, callback, timeout);
        }
    }

    public void httpPatchImpl23(String url, ArrayList<KVPair<String>> values, KVPair<String> file, long offset, ArrayList<KVPair<String>> headers, OnReadListener listener, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpPatch: " + url, new Object[0]);
        if (values != null) {
            String param = kvPairsToUrl(values);
            if (param.length() > 0) {
                url = url + "?" + param;
            }
        }
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        conn.setRequestMethod(HttpPatch.METHOD_NAME);
        conn.setRequestProperty("Content-Type", "application/offset+octet-stream");
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        FilePart fp = new FilePart();
        fp.setOnReadListener(listener);
        fp.setFile(file.value);
        fp.setOffset(offset);
        InputStream is = fp.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl23(conn));
            } finally {
                conn.disconnect();
            }
        } else {
            conn.disconnect();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    public String httpPost(String url, ArrayList<KVPair<String>> values, KVPair<String> file, ArrayList<KVPair<String>> headers, int chunkLength, NetworkTimeOut timeout) throws Throwable {
        ArrayList<KVPair<String>> files = new ArrayList<>();
        if (!(file == null || file.value == null || !new File(file.value).exists())) {
            files.add(file);
        }
        return httpPostFiles(url, values, files, headers, chunkLength, timeout);
    }

    public String httpPost(String url, ArrayList<KVPair<String>> values, KVPair<String> file, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        return httpPost(url, values, file, headers, 0, timeout);
    }

    public void httpPost(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> files, ArrayList<KVPair<String>> headers, int chunkLength, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        HTTPPart part;
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpPost: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        if (files == null || files.size() <= 0) {
            part = getTextPostHTTPPart(conn, url, values);
            conn.setFixedLengthStreamingMode((int) part.length());
        } else {
            part = getFilePostHTTPPart(conn, url, values, files);
            if (chunkLength >= 0) {
                conn.setChunkedStreamingMode(chunkLength);
            }
        }
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        InputStream is = part.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl23(conn));
            } finally {
                conn.disconnect();
            }
        } else {
            conn.disconnect();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    public void httpPost(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> files, ArrayList<KVPair<String>> headers, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        httpPost(url, values, files, headers, 0, callback, timeout);
    }

    public String httpPostFiles(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> files, ArrayList<KVPair<String>> headers, int chunkLength, NetworkTimeOut timeout) throws Throwable {
        final HashMap<String, String> tmpMap = new HashMap<>();
        httpPost(url, values, files, headers, chunkLength, new HttpResponseCallback() { // from class: com.mob.tools.network.NetworkHelper.1
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection conn) throws Throwable {
                int status = conn.getResponseCode();
                if (status == 200 || status == 201) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("utf-8")));
                    for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
                        if (sb.length() > 0) {
                            sb.append('\n');
                        }
                        sb.append(txt);
                    }
                    br.close();
                    tmpMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
                for (String txt2 = br2.readLine(); txt2 != null; txt2 = br2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append('\n');
                    }
                    sb2.append(txt2);
                }
                br2.close();
                HashMap<String, Object> errMap = new HashMap<>();
                errMap.put(av.aG, sb2.toString());
                errMap.put("status", Integer.valueOf(status));
                throw new Throwable(new Hashon().fromHashMap(errMap));
            }
        }, timeout);
        return tmpMap.get("resp");
    }

    public String httpPostFiles(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> files, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        return httpPostFiles(url, values, files, headers, 0, timeout);
    }

    public String httpPut(String url, ArrayList<KVPair<String>> values, KVPair<String> file, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("httpPut: " + url, new Object[0]);
        if (values != null) {
            String param = kvPairsToUrl(values);
            if (param.length() > 0) {
                url = url + "?" + param;
            }
        }
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        conn.setRequestMethod(HttpPut.METHOD_NAME);
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        FilePart fp = new FilePart();
        fp.setFile(file.value);
        InputStream is = fp.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        int status = conn.getResponseCode();
        if (status == 200 || status == 201) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("utf-8")));
            for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(txt);
            }
            br.close();
            conn.disconnect();
            String resp = sb.toString();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
            return resp;
        }
        StringBuilder sb2 = new StringBuilder();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
        for (String txt2 = br2.readLine(); txt2 != null; txt2 = br2.readLine()) {
            if (sb2.length() > 0) {
                sb2.append('\n');
            }
            sb2.append(txt2);
        }
        br2.close();
        HashMap<String, Object> errMap = new HashMap<>();
        errMap.put(av.aG, sb2.toString());
        errMap.put("status", Integer.valueOf(status));
        throw new Throwable(new Hashon().fromHashMap(errMap));
    }

    public String jsonPost(String url, ArrayList<KVPair<String>> values, ArrayList<KVPair<String>> headers, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("jsonPost: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        conn.setRequestProperty("content-type", "application/json");
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        StringPart sp = new StringPart();
        if (values != null) {
            HashMap<String, Object> errMap = new HashMap<>();
            Iterator<KVPair<String>> it2 = values.iterator();
            while (it2.hasNext()) {
                KVPair<String> p = it2.next();
                errMap.put(p.name, p.value);
            }
            sp.append(new Hashon().fromHashMap(errMap));
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        InputStream is = sp.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        int status = conn.getResponseCode();
        if (status == 200 || status == 201) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("utf-8")));
            for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(txt);
            }
            br.close();
            conn.disconnect();
            String resp = sb.toString();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
            return resp;
        }
        StringBuilder sb2 = new StringBuilder();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
        for (String txt2 = br2.readLine(); txt2 != null; txt2 = br2.readLine()) {
            if (sb2.length() > 0) {
                sb2.append('\n');
            }
            sb2.append(txt2);
        }
        br2.close();
        conn.disconnect();
        HashMap<String, Object> errMap2 = new HashMap<>();
        errMap2.put(av.aG, sb2.toString());
        errMap2.put("status", Integer.valueOf(status));
        throw new Throwable(new Hashon().fromHashMap(errMap2));
    }

    public void rawGet(String url, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("rawGet: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.connect();
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl23(conn));
            } finally {
                conn.disconnect();
            }
        } else {
            conn.disconnect();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    public void rawGet(String url, RawNetworkCallback callback, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("rawGet: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.connect();
        int status = conn.getResponseCode();
        if (status == 200) {
            if (callback != null) {
                callback.onResponse(conn.getInputStream());
            }
            conn.disconnect();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
        for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(txt);
        }
        br.close();
        conn.disconnect();
        HashMap<String, Object> errMap = new HashMap<>();
        errMap.put(av.aG, sb.toString());
        errMap.put("status", Integer.valueOf(status));
        throw new Throwable(new Hashon().fromHashMap(errMap));
    }

    public void rawPost(String url, ArrayList<KVPair<String>> headers, HTTPPart data, HttpResponseCallback callback, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("rawpost: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        InputStream is = data.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        if (callback != null) {
            try {
                callback.onResponse(new HttpConnectionImpl23(conn));
            } finally {
                conn.disconnect();
            }
        } else {
            conn.disconnect();
        }
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
    }

    public void rawPost(String url, ArrayList<KVPair<String>> headers, HTTPPart data, RawNetworkCallback callback, NetworkTimeOut timeout) throws Throwable {
        long time = System.currentTimeMillis();
        MobLog.getInstance().i("rawpost: " + url, new Object[0]);
        HttpURLConnection conn = getConnection(url, timeout);
        conn.setDoOutput(true);
        conn.setChunkedStreamingMode(0);
        if (headers != null) {
            Iterator<KVPair<String>> it = headers.iterator();
            while (it.hasNext()) {
                KVPair<String> header = it.next();
                conn.setRequestProperty(header.name, header.value);
            }
        }
        conn.connect();
        OutputStream os = conn.getOutputStream();
        InputStream is = data.toInputStream();
        byte[] buf = new byte[65536];
        for (int len = is.read(buf); len > 0; len = is.read(buf)) {
            os.write(buf, 0, len);
        }
        os.flush();
        is.close();
        os.close();
        int status = conn.getResponseCode();
        if (status == 200) {
            if (callback != null) {
                InputStream in = conn.getInputStream();
                try {
                    callback.onResponse(in);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Throwable th) {
                        }
                    }
                    conn.disconnect();
                }
            } else {
                conn.disconnect();
            }
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - time), new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Charset.forName("utf-8")));
        for (String txt = br.readLine(); txt != null; txt = br.readLine()) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(txt);
        }
        br.close();
        conn.disconnect();
        HashMap<String, Object> errMap = new HashMap<>();
        errMap.put(av.aG, sb.toString());
        errMap.put("status", Integer.valueOf(status));
        throw new Throwable(new Hashon().fromHashMap(errMap));
    }
}
