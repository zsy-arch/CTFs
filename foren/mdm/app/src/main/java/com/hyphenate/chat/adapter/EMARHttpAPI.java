package com.hyphenate.chat.adapter;

import com.hyphenate.chat.EMClient;
import com.hyphenate.cloud.CustomMultiPartEntity;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.cloud.HttpClientConfig;
import com.hyphenate.util.EMLog;
import com.yolanda.nohttp.Headers;
import internal.org.apache.http.entity.mime.content.FileBody;
import internal.org.apache.http.entity.mime.content.StringBody;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class EMARHttpAPI {
    public static final String TAG = "EMARHttpAPI";
    final boolean tokenRetrieved = false;
    public static String Method_GET = "GET";
    public static String Method_POST = "POST";
    public static String Method_PUT = HttpPut.METHOD_NAME;
    public static String Method_DELETE = HttpDelete.METHOD_NAME;
    public static int REQUEST_FAILED_CODE = 408;
    public static int REQUEST_AUTHENTICATION_FAILED = 400;
    public static int HIGH_SPEED_DOWNLOAD_BUF_SIZE = 30720;

    private static HttpResponse _httpExecute(String str, Map<String, String> map, String str2, String str3) throws ClientProtocolException, IOException, KeyStoreException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, ConnectTimeoutException {
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

    public static int download(String str, String str2, Map<String, String> map, EMARHttpCallback eMARHttpCallback) {
        int i = REQUEST_FAILED_CODE;
        if (str == null || str.length() <= 0) {
            EMLog.e(TAG, "invalid remoteUrl");
            return i;
        }
        String processUrl = processUrl(HttpClientConfig.getFileRemoteUrl(str));
        EMLog.d(TAG, "download file: remote url : " + processUrl + " , local file : " + str2);
        File file = new File(str2);
        EMLog.d(TAG, "local exists:" + file.exists());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        DefaultHttpClient defaultHttpClient = HttpClientConfig.getDefaultHttpClient(HttpClientConfig.getTimeout(map));
        try {
            HttpGet httpGet = new HttpGet(processUrl);
            processHeaders(httpGet, map);
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            i = execute.getStatusLine().getStatusCode();
            switch (i) {
                case 200:
                    if (onDownloadCompleted(execute, eMARHttpCallback, str2) > 0) {
                        if (eMARHttpCallback != null) {
                            EMLog.e(TAG, "download successfully");
                            break;
                        }
                    } else {
                        i = REQUEST_FAILED_CODE;
                        break;
                    }
                    break;
                case 401:
                    if (System.currentTimeMillis() - EMHttpClient.getInstance().chatConfig().o() <= 600000 && eMARHttpCallback != null) {
                        EMLog.e(TAG, "unauthorized file");
                        break;
                    }
                    break;
                default:
                    EMLog.e(TAG, "error response code is :" + i);
                    break;
            }
            return i;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null && (message = e.toString()) == null) {
                message = "failed to download file";
            }
            EMLog.e(TAG, message);
            return i;
        }
    }

    private static String getMimeType(File file) {
        String name = file.getName();
        return (name.endsWith(".3gp") || name.endsWith(".amr")) ? "audio/3gp" : (name.endsWith(".jpe") || name.endsWith(".jpeg") || name.endsWith(".jpg")) ? "image/jpeg" : name.endsWith(".amr") ? "audio/amr" : name.endsWith(".mp4") ? "video/mp4" : "image/png";
    }

    private static ByteArrayBuffer getResponseContent(HttpResponse httpResponse) throws Exception {
        InputStream inputStream;
        try {
            inputStream = null;
            int i = HIGH_SPEED_DOWNLOAD_BUF_SIZE;
            byte[] bArr = new byte[i];
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
            try {
                inputStream = httpResponse.getEntity().getContent();
                while (true) {
                    long read = inputStream.read(bArr);
                    if (read == -1) {
                        return byteArrayBuffer;
                    }
                    byteArrayBuffer.append(bArr, 0, (int) read);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                throw e2;
            }
        } finally {
            inputStream.close();
        }
    }

    public static int httpExecute(String str, Map<String, String> map, String str2, String str3, StringBuilder sb) {
        int i = REQUEST_FAILED_CODE;
        EMLog.d(TAG, "httpExecute");
        try {
            HttpResponse _httpExecute = _httpExecute(str, map, str2, str3);
            ByteArrayBuffer responseContent = getResponseContent(_httpExecute);
            sb.delete(0, sb.length());
            sb.append(new String(responseContent.buffer(), 0, responseContent.length()));
            i = _httpExecute.getStatusLine().getStatusCode();
            EMLog.d(TAG, "httpExecute code: " + i);
            return i;
        } catch (ConnectTimeoutException e) {
            EMLog.e(TAG, "ConnectTimeoutException");
            EMLog.e(TAG, "can't catch exceptions");
            return i;
        } catch (Exception e2) {
            EMLog.e(TAG, e2.getMessage());
            e2.printStackTrace();
            EMLog.e(TAG, "can't catch exceptions");
            return i;
        }
    }

    private static long onDownloadCompleted(HttpResponse httpResponse, EMARHttpCallback eMARHttpCallback, String str) throws IOException, IllegalStateException {
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
            return 0L;
        }
        int i = 0;
        long contentLength = entity.getContentLength();
        try {
            InputStream content = entity.getContent();
            File file = new File(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[HIGH_SPEED_DOWNLOAD_BUF_SIZE];
                long j = 0;
                while (true) {
                    try {
                        try {
                            int read = content.read(bArr);
                            if (read == -1) {
                                return file.length();
                            }
                            j += read;
                            int i2 = (int) ((100 * j) / contentLength);
                            EMLog.d(TAG, i2 + "");
                            if (i2 == 100 || i2 > i + 5) {
                                if (eMARHttpCallback != null) {
                                    eMARHttpCallback.onProgress(contentLength, j);
                                }
                                i = i2;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw e;
                        }
                    } finally {
                        fileOutputStream.close();
                        content.close();
                    }
                }
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                content.close();
                throw e2;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            throw e3;
        } catch (IllegalStateException e4) {
            e4.printStackTrace();
            throw e4;
        }
    }

    private static void processHeaders(HttpGet httpGet, Map<String, String> map) {
        httpGet.addHeader("Authorization", "Bearer " + EMClient.getInstance().getOptions().getAccessToken());
        httpGet.addHeader(Headers.HEAD_KEY_ACCEPT, "application/octet-stream");
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!entry.getKey().equals("Authorization") && !entry.getKey().equals(Headers.HEAD_KEY_ACCEPT)) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private static String processUrl(String str) {
        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
            str = str.replaceAll("\\+", "%2B");
        }
        return str.contains("#") ? str.replaceAll("#", "%23") : str;
    }

    public static int upload(String str, String str2, Map<String, String> map, StringBuilder sb, final EMARHttpCallback eMARHttpCallback) {
        int i;
        Exception e;
        String appKey = EMClient.getInstance().getOptions().getAppKey();
        String currentUser = EMClient.getInstance().getCurrentUser();
        File file = new File(str);
        int i2 = REQUEST_FAILED_CODE;
        EMLog.d(TAG, "upload");
        CustomMultiPartEntity customMultiPartEntity = new CustomMultiPartEntity(null);
        if (appKey != null) {
            try {
                customMultiPartEntity.addPart("app", new StringBody(appKey));
            } catch (Exception e2) {
                EMLog.e(TAG, e2.getMessage());
                return i2;
            }
        }
        if (currentUser != null) {
            customMultiPartEntity.addPart("id", new StringBody(currentUser));
        }
        customMultiPartEntity.addPart("file", new FileBody(file, str.indexOf("/") > 0 ? str2.substring(str.lastIndexOf("/")) : str2, getMimeType(file), "UTF-8"));
        final long contentLength = customMultiPartEntity.getContentLength();
        customMultiPartEntity.setListener(new CustomMultiPartEntity.ProgressListener() { // from class: com.hyphenate.chat.adapter.EMARHttpAPI.1
            @Override // com.hyphenate.cloud.CustomMultiPartEntity.ProgressListener
            public void transferred(long j) {
                if (((int) ((((float) j) / ((float) contentLength)) * 100.0f)) != 100 && eMARHttpCallback != null) {
                    eMARHttpCallback.onProgress(contentLength, j);
                }
            }
        });
        HttpPost httpPost = new HttpPost(HttpClientConfig.getFileRemoteUrl(str2));
        httpPost.setEntity(customMultiPartEntity);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            HttpResponse execute = HttpClientConfig.getDefaultHttpClient().execute(httpPost);
            i = execute.getStatusLine().getStatusCode();
            try {
                switch (i) {
                    case 200:
                        try {
                            ByteArrayBuffer responseContent = getResponseContent(execute);
                            EMLog.d(TAG, "getBackupInfo result:" + new String(responseContent.buffer(), 0, responseContent.length()));
                            sb.append(new String(responseContent.buffer(), 0, responseContent.length()));
                        } catch (Exception e3) {
                            try {
                                EMLog.e("sendImageMessage", "json parse exception remotefilepath:" + str2);
                            } catch (Exception e4) {
                                e = e4;
                                EMLog.e(TAG, e.getMessage());
                                return i;
                            }
                        }
                        return i;
                    default:
                        return i;
                }
            } catch (ConnectTimeoutException e5) {
                EMLog.e(TAG, "ConnectTimeoutException");
                return i;
            }
        } catch (ConnectTimeoutException e6) {
            i = i2;
        } catch (Exception e7) {
            e = e7;
            i = i2;
        }
    }
}
