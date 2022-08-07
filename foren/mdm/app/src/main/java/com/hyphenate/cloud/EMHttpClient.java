package com.hyphenate.cloud;

import android.util.Pair;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.a.b;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;

/* loaded from: classes2.dex */
public class EMHttpClient {
    private static final String TAG = "EMHttpClient";
    private b configPrivate = null;
    public static String GET = "GET";
    public static String POST = "POST";
    public static String PUT = HttpPut.METHOD_NAME;
    public static String DELETE = HttpDelete.METHOD_NAME;
    private static EMHttpClient instance = null;

    private EMHttpClient() {
    }

    public static synchronized EMHttpClient getInstance() {
        EMHttpClient eMHttpClient;
        synchronized (EMHttpClient.class) {
            if (instance == null) {
                instance = new EMHttpClient();
            }
            eMHttpClient = instance;
        }
        return eMHttpClient;
    }

    public b chatConfig() {
        return this.configPrivate;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hyphenate.cloud.EMHttpClient$1] */
    public void downloadFile(final String str, final String str2, final Map<String, String> map, final EMCloudOperationCallback eMCloudOperationCallback) {
        new Thread() { // from class: com.hyphenate.cloud.EMHttpClient.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    new HttpFileManager().downloadFile(str, str2, map, eMCloudOperationCallback);
                } catch (Exception e) {
                    if (eMCloudOperationCallback != null) {
                        eMCloudOperationCallback.onError((e == null || e.getMessage() == null) ? "failed to download the file : " + str : e.getMessage());
                    }
                }
            }
        }.start();
    }

    public HttpResponse httpExecute(String str, Map<String, String> map, String str2, String str3) throws KeyManagementException, UnrecoverableKeyException, ClientProtocolException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        return HttpClientManager.httpExecute(str, map, str2, str3);
    }

    public void onInit(b bVar) {
        this.configPrivate = bVar;
    }

    public Pair<Integer, String> sendRequest(String str, Map<String, String> map, String str2, String str3) throws IOException, HyphenateException {
        return HttpClientManager.sendRequest(str, map, str2, str3);
    }

    public Pair<Integer, String> sendRequestWithToken(String str, String str2, String str3) throws HyphenateException {
        return HttpClientManager.sendRequestWithToken(str, str2, str3);
    }

    public void uploadFile(final String str, final String str2, final Map<String, String> map, final EMCloudOperationCallback eMCloudOperationCallback) {
        EMLog.d(TAG, "upload file :  localFilePath : " + str + " remoteUrl : " + str2);
        new Thread() { // from class: com.hyphenate.cloud.EMHttpClient.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    EMLog.d(EMHttpClient.TAG, "run HttpFileManager().uploadFile");
                    new HttpFileManager().uploadFile(str, str2, EMClient.getInstance().getOptions().getAppKey(), EMClient.getInstance().getCurrentUser(), map, eMCloudOperationCallback);
                } catch (Exception e) {
                    if (eMCloudOperationCallback != null) {
                        eMCloudOperationCallback.onError((e == null || e.getMessage() == null) ? "failed to upload the file : " + str2 : e.getMessage());
                    }
                }
            }
        }.start();
    }
}
