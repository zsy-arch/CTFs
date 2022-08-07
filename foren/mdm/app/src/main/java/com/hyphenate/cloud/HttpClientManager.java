package com.hyphenate.cloud;

import android.util.Pair;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.NetUtils;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/* loaded from: classes2.dex */
public class HttpClientManager {
    private static final String TAG = "HttpClientManager";
    public static final int max_retries_times_on_connection_refused = 3;
    private static final int retriveInterval = 120000;
    public static String Method_GET = "GET";
    public static String Method_POST = "POST";
    public static String Method_PUT = HttpPut.METHOD_NAME;
    public static String Method_DELETE = HttpDelete.METHOD_NAME;
    private static volatile long retrivedTokenTime = 0;
    private static volatile boolean isRetring = false;

    public static Map<String, String> addDomainToHeaders(Map<String, String> map) {
        return map;
    }

    public static void checkAndProcessSSL(String str, DefaultHttpClient defaultHttpClient) throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException, KeyManagementException, UnrecoverableKeyException {
    }

    public static HttpResponse getHttpResponse(String str, Map<String, String> map, String str2, String str3) throws KeyStoreException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
        return httpExecute(str, addDomainToHeaders(map), str2, str3);
    }

    public static String getNewHost(String str, String str2) {
        String substring = str.substring(str.indexOf("/", 8));
        String substring2 = substring.substring(substring.indexOf("/", 1));
        return str2 + substring2.substring(substring2.indexOf("/", 1));
    }

    public static HttpResponse httpExecute(String str, Map<String, String> map, String str2, String str3) throws ClientProtocolException, IOException, KeyStoreException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException {
        HttpUriRequest httpGet;
        DefaultHttpClient defaultHttpClient = HttpClientConfig.getDefaultHttpClient(HttpClientConfig.getTimeout(map));
        if (str3.equals(Method_POST)) {
            HttpPost httpPost = new HttpPost(str);
            httpPost.setEntity(new StringEntity(str2, "UTF-8"));
            httpGet = httpPost;
        } else if (str3.equals(Method_PUT)) {
            HttpPut httpPut = new HttpPut(str);
            httpPut.setEntity(new StringEntity(str2, "UTF-8"));
            httpGet = httpPut;
        } else {
            httpGet = str3.equals(Method_GET) ? new HttpGet(str) : str3.equals(Method_DELETE) ? new HttpDelete(str) : null;
        }
        if (httpGet == null) {
            return null;
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return defaultHttpClient.execute(httpGet);
    }

    public static String sendHttpRequest(String str, Map<String, String> map, String str2, String str3) throws HyphenateException, IOException {
        String str4 = str;
        IOException e = null;
        String str5 = null;
        HyphenateException e2 = null;
        for (int i = 0; i < 3; i++) {
            EMLog.d(TAG, "try send request, request url: " + str4 + " with number: " + i);
            try {
                str5 = sendHttpRequestWithCountDown(str4, map, str2, str3);
                e2 = null;
                e = null;
            } catch (HyphenateException e3) {
                e2 = e3;
                e = null;
            } catch (IOException e4) {
                e = e4;
                e2 = null;
            }
            if (str5 != null) {
                break;
            }
            str4 = getNewHost(str, EMHttpClient.getInstance().chatConfig().m());
        }
        if (e != null) {
            throw e;
        } else if (e2 == null) {
            return str5;
        } else {
            throw e2;
        }
    }

    private static String sendHttpRequestWithCountDown(String str, Map<String, String> map, String str2, String str3) throws HyphenateException, IOException {
        try {
            HttpEntity entity = getHttpResponse(str, map, str2, str3).getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
            return null;
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            if (e2 != null) {
                e2.printStackTrace();
            }
            String str4 = "http request failed : " + str;
            if (!(e2 == null || e2.toString() == null)) {
                str4 = e2.toString();
            }
            if (str4.contains("Unable to resolve host")) {
                throw new HyphenateException(2, "Unable to resolve host");
            }
            throw new HyphenateException(300, str4);
        }
    }

    static Pair<Integer, String> sendHttpRequestWithRetryToken(String str, Map<String, String> map, String str2, String str3) throws HyphenateException, IOException {
        Pair<Integer, String> sendRequest = sendRequest(str, map, str2, str3);
        if (sendRequest != null && ((Integer) sendRequest.first).intValue() == 401 && System.currentTimeMillis() - retrivedTokenTime > 120000 && !isRetring) {
            isRetring = true;
            String accessToken = EMClient.getInstance().getOptions().getAccessToken(true);
            isRetring = false;
            retrivedTokenTime = System.currentTimeMillis();
            if (accessToken != null) {
                map.put("Authorization", "Bearer " + accessToken);
                return sendRequest(str, map, str2, str3);
            }
        }
        return sendRequest;
    }

    public static Pair<Integer, String> sendRequest(String str, Map<String, String> map, String str2, String str3) throws IOException, HyphenateException {
        Pair<Integer, String> pair;
        int i = 0;
        String str4 = str;
        Exception exc = null;
        Pair<Integer, String> pair2 = null;
        Exception exc2 = null;
        while (true) {
            if (i >= 3) {
                pair = pair2;
                break;
            }
            EMLog.d(TAG, "try send request, request url: " + str4 + " with number: " + i);
            try {
                HttpResponse sendRequestWithCountDown = sendRequestWithCountDown(str4, map, str2, str3);
                HttpEntity entity = sendRequestWithCountDown.getEntity();
                if (entity != null) {
                    pair = new Pair<>(Integer.valueOf(sendRequestWithCountDown.getStatusLine().getStatusCode()), EntityUtils.toString(entity, "UTF-8"));
                } else {
                    pair = pair2;
                }
                exc2 = null;
                exc = null;
                e = null;
            } catch (HyphenateException e) {
                e = e;
                exc = null;
                pair = pair2;
                exc2 = e;
            } catch (IOException e2) {
                e = e2;
                exc = e;
                pair = pair2;
                exc2 = null;
            }
            String str5 = "failed to send request, request url: " + str;
            if (e != null) {
                if (e.getMessage() != null) {
                    str5 = e.getMessage();
                } else if (e.toString() != null) {
                    str5 = e.toString();
                }
                if (str5.toLowerCase().contains(EMPrivateConstant.CONNECTION_REFUSED) || !NetUtils.hasNetwork(EMClient.getInstance().getContext())) {
                    break;
                }
                str4 = getNewHost(str, EMHttpClient.getInstance().chatConfig().m());
                i++;
                exc2 = exc2;
                pair2 = pair;
            }
            if (str5.toLowerCase().contains(EMPrivateConstant.CONNECTION_REFUSED)) {
                break;
                break;
            }
            str4 = getNewHost(str, EMHttpClient.getInstance().chatConfig().m());
            i++;
            exc2 = exc2;
            pair2 = pair;
        }
        if (exc != null) {
            throw exc;
        } else if (exc2 == null) {
            return pair;
        } else {
            throw exc2;
        }
    }

    private static HttpResponse sendRequestWithCountDown(String str, Map<String, String> map, String str2, String str3) throws HyphenateException, IOException {
        try {
            return getHttpResponse(str, map, str2, str3);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            if (e2 != null) {
                e2.printStackTrace();
            }
            String str4 = "http request failed : " + str;
            if (!(e2 == null || e2.toString() == null)) {
                str4 = e2.toString();
            }
            if (str4.contains("Unable to resolve host")) {
                throw new HyphenateException(2, "Unable to resolve host");
            }
            throw new HyphenateException(300, str4);
        }
    }

    public static Pair<Integer, String> sendRequestWithToken(String str, String str2, String str3) throws HyphenateException {
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", "Bearer " + EMClient.getInstance().getOptions().getAccessToken());
        try {
            return sendHttpRequestWithRetryToken(str, hashMap, str2, str3);
        } catch (IOException e) {
            String str4 = " send request : " + str + " failed!";
            if (!(e == null || e.toString() == null)) {
                str4 = e.toString();
            }
            EMLog.d(TAG, str4);
            throw new HyphenateException(1, str4);
        }
    }
}
