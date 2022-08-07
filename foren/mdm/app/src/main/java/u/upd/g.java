package u.upd;

import com.em.ui.EditActivity;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UClient.java */
/* loaded from: classes2.dex */
public class g {
    private static final String TAG = g.class.getName();
    private Map<String, String> headers;

    public <T extends i> T execute(h hVar, Class<T> cls) {
        JSONObject jSONObject;
        String trim = hVar.getHttpMethod().trim();
        verifyMethod(trim);
        if (h.GET.equals(trim)) {
            jSONObject = HttpRequestGet(hVar.toGetUrl());
        } else if (h.POST.equals(trim)) {
            jSONObject = HttpRequestPost(hVar.baseUrl, hVar.toJson());
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            return cls.getConstructor(JSONObject.class).newInstance(jSONObject);
        } catch (IllegalAccessException e) {
            b.b(TAG, "IllegalAccessException", e);
            return null;
        } catch (IllegalArgumentException e2) {
            b.b(TAG, "IllegalArgumentException", e2);
            return null;
        } catch (InstantiationException e3) {
            b.b(TAG, "InstantiationException", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            b.b(TAG, "NoSuchMethodException", e4);
            return null;
        } catch (SecurityException e5) {
            b.b(TAG, "SecurityException", e5);
            return null;
        } catch (InvocationTargetException e6) {
            b.b(TAG, "InvocationTargetException", e6);
            return null;
        }
    }

    private JSONObject HttpRequestPost(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        int nextInt = new Random().nextInt(1000);
        b.c(TAG, String.valueOf(nextInt) + ":\trequest: " + str + n.a + jSONObject2);
        HttpPost httpPost = new HttpPost(str);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(getHttpParams());
        try {
            if (shouldCompressData()) {
                byte[] a = m.a("content=" + jSONObject2, Charset.defaultCharset().toString());
                httpPost.addHeader("Content-Encoding", "deflate");
                httpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(a), a.length));
            } else {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(new BasicNameValuePair(EditActivity.CONTENT, jSONObject2));
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            }
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity == null) {
                    return null;
                }
                InputStream content = entity.getContent();
                Header firstHeader = execute.getFirstHeader("Content-Encoding");
                String convertStreamToString = convertStreamToString((firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) ? content : new InflaterInputStream(content));
                b.a(TAG, String.valueOf(nextInt) + ":\tresponse: " + n.a + convertStreamToString);
                if (convertStreamToString == null) {
                    return null;
                }
                return new JSONObject(convertStreamToString);
            }
            b.c(TAG, String.valueOf(nextInt) + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + n.a + str);
            return null;
        } catch (ClientProtocolException e) {
            b.c(TAG, String.valueOf(nextInt) + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (IOException e2) {
            b.c(TAG, String.valueOf(nextInt) + ":\tIOException,Failed to send message." + str, e2);
            return null;
        } catch (JSONException e3) {
            b.c(TAG, String.valueOf(nextInt) + ":\tIOException,Failed to send message." + str, e3);
            return null;
        }
    }

    public boolean shouldCompressData() {
        return false;
    }

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        try {
                            inputStream.close();
                            return sb.toString();
                        } catch (IOException e) {
                            b.b(TAG, "Caught IOException in convertStreamToString()", e);
                            return null;
                        }
                    } else {
                        sb.append(String.valueOf(readLine) + "\n");
                    }
                } catch (IOException e2) {
                    b.b(TAG, "Caught IOException in convertStreamToString()", e2);
                    try {
                        inputStream.close();
                        return null;
                    } catch (IOException e3) {
                        b.b(TAG, "Caught IOException in convertStreamToString()", e3);
                        return null;
                    }
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    throw th;
                } catch (IOException e4) {
                    b.b(TAG, "Caught IOException in convertStreamToString()", e4);
                    return null;
                }
            }
        }
    }

    private JSONObject HttpRequestGet(String str) {
        InputStream inputStream;
        int nextInt = new Random().nextInt(1000);
        try {
            String property = System.getProperty("line.separator");
            if (str.length() <= 1) {
                b.b(TAG, String.valueOf(nextInt) + ":\tInvalid baseUrl.");
                return null;
            }
            b.a(TAG, String.valueOf(nextInt) + ":\tget: " + str);
            HttpGet httpGet = new HttpGet(str);
            if (this.headers != null && this.headers.size() > 0) {
                for (String str2 : this.headers.keySet()) {
                    httpGet.addHeader(str2, this.headers.get(str2));
                }
            }
            HttpResponse execute = new DefaultHttpClient(getHttpParams()).execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    InputStream content = entity.getContent();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null && firstHeader.getValue().equalsIgnoreCase("gzip")) {
                        b.a(TAG, String.valueOf(nextInt) + "  Use GZIPInputStream get data....");
                        inputStream = new GZIPInputStream(content);
                    } else if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) {
                        inputStream = content;
                    } else {
                        b.a(TAG, String.valueOf(nextInt) + "  Use InflaterInputStream get data....");
                        inputStream = new InflaterInputStream(content);
                    }
                    String convertStreamToString = convertStreamToString(inputStream);
                    b.a(TAG, String.valueOf(nextInt) + ":\tresponse: " + property + convertStreamToString);
                    if (convertStreamToString == null) {
                        return null;
                    }
                    return new JSONObject(convertStreamToString);
                }
            } else {
                b.c(TAG, String.valueOf(nextInt) + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + n.a + str);
            }
            return null;
        } catch (ClientProtocolException e) {
            b.c(TAG, String.valueOf(nextInt) + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (Exception e2) {
            b.c(TAG, String.valueOf(nextInt) + ":\tIOException,Failed to send message." + str, e2);
            return null;
        }
    }

    private HttpParams getHttpParams() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpProtocolParams.setUserAgent(basicHttpParams, System.getProperty("http.agent"));
        return basicHttpParams;
    }

    public g setHeader(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    private void verifyMethod(String str) {
        if (n.d(str) || !(h.GET.equals(str.trim()) ^ h.POST.equals(str.trim()))) {
            throw new RuntimeException("验证请求方式失败[" + str + "]");
        }
    }
}
