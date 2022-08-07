package com.hyphenate.cloud;

import android.content.Context;
import android.text.TextUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.cloud.CustomMultiPartEntity;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.NetUtils;
import com.yolanda.nohttp.Headers;
import internal.org.apache.http.entity.mime.content.FileBody;
import internal.org.apache.http.entity.mime.content.StringBody;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class HttpFileManager extends CloudFileManager {
    private static final long MAX_ALLOWED_FILE_SIZE = 10485760;
    private static final int max_retry_times_on_connection_refused = 20;
    private Context appContext;
    boolean tokenRetrieved;
    private long totalSize;

    public HttpFileManager() {
        this.tokenRetrieved = false;
        this.appContext = EMClient.getInstance().getContext();
    }

    public HttpFileManager(Context context) {
        this.tokenRetrieved = false;
        this.appContext = context.getApplicationContext();
    }

    public HttpFileManager(Context context, String str) {
        this.tokenRetrieved = false;
        this.appContext = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.hyphenate.cloud.HttpFileManager$7] */
    /* JADX WARN: Type inference failed for: r4v3, types: [com.hyphenate.cloud.HttpFileManager$8] */
    public void downloadFileWithCountDown(String str, final String str2, Map<String, String> map, final CloudOperationCallback cloudOperationCallback, int i) {
        if (str == null || str.length() <= 0) {
            cloudOperationCallback.onError("invalid remoteUrl");
            return;
        }
        final Map<String, String> addDomainToHeaders = HttpClientManager.addDomainToHeaders(map);
        String fileRemoteUrl = HttpClientConfig.getFileRemoteUrl(str);
        EMLog.d("CloudFileManager", "remoteUrl:" + fileRemoteUrl + " localFilePath:" + str2);
        final String processUrl = processUrl(fileRemoteUrl);
        EMLog.d("CloudFileManager", "download file: remote url : " + processUrl + " , local file : " + str2);
        File file = new File(str2);
        EMLog.d("CloudFileManager", "local exists:" + file.exists());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        DefaultHttpClient defaultHttpClient = HttpClientConfig.getDefaultHttpClient(HttpClientConfig.getTimeout(addDomainToHeaders));
        try {
            HttpGet httpGet = new HttpGet(processUrl);
            processHeaders(httpGet, addDomainToHeaders);
            HttpClientManager.checkAndProcessSSL(processUrl, defaultHttpClient);
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            switch (statusCode) {
                case 200:
                    if (onDownloadCompleted(execute, cloudOperationCallback, str2) > 0) {
                        if (cloudOperationCallback != null) {
                            cloudOperationCallback.onSuccess("download successfully");
                            break;
                        }
                    } else if (cloudOperationCallback != null) {
                        cloudOperationCallback.onError("downloaded content size is zero!");
                        break;
                    }
                    break;
                case 401:
                    if (System.currentTimeMillis() - EMHttpClient.getInstance().chatConfig().o() > 600000) {
                        if (this.tokenRetrieved) {
                            if (cloudOperationCallback != null) {
                                cloudOperationCallback.onError("unauthorized file");
                                break;
                            }
                        } else {
                            new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.7
                                @Override // java.lang.Thread, java.lang.Runnable
                                public void run() {
                                    String accessToken = EMClient.getInstance().getOptions().getAccessToken(true);
                                    if (accessToken == null) {
                                        cloudOperationCallback.onError("unauthorized token is null");
                                        return;
                                    }
                                    HttpFileManager.this.tokenRetrieved = true;
                                    if (addDomainToHeaders != null) {
                                        addDomainToHeaders.put("Authorization", "Bearer " + accessToken);
                                        HttpFileManager.this.downloadFile(processUrl, str2, addDomainToHeaders, cloudOperationCallback);
                                        return;
                                    }
                                    HttpFileManager.this.tokenRetrieved = false;
                                    if (cloudOperationCallback != null) {
                                        cloudOperationCallback.onError("unauthorized token is null");
                                    }
                                }
                            }.start();
                            break;
                        }
                    } else if (cloudOperationCallback != null) {
                        cloudOperationCallback.onError("unauthorized file");
                        break;
                    }
                    break;
                default:
                    EMLog.e("CloudFileManager", "error response code is :" + statusCode);
                    if (cloudOperationCallback != null) {
                        cloudOperationCallback.onError(String.valueOf(statusCode));
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null && (message = e.toString()) == null) {
                message = "failed to download file";
            }
            if (!message.toLowerCase().contains(EMPrivateConstant.CONNECTION_REFUSED) || !NetUtils.hasNetwork(this.appContext) || i <= 0) {
                EMLog.e("CloudFileManager", message);
                if (cloudOperationCallback != null) {
                    cloudOperationCallback.onError(message);
                    return;
                }
                return;
            }
            final String newHost = HttpClientManager.getNewHost(processUrl, EMHttpClient.getInstance().chatConfig().m());
            final int i2 = i - 1;
            new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.8
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        HttpFileManager.this.downloadFileWithCountDown(newHost, str2, addDomainToHeaders, cloudOperationCallback, i2);
                    } catch (Exception e2) {
                        if (e2 == null || e2.toString() == null) {
                            cloudOperationCallback.onError("failed to download the file : " + newHost);
                        } else {
                            cloudOperationCallback.onError(e2.toString());
                        }
                    }
                }
            }.start();
        }
    }

    public static String getMimeType(File file) {
        String name = file.getName();
        return (name.endsWith(".3gp") || name.endsWith(".amr")) ? "audio/3gp" : (name.endsWith(".jpe") || name.endsWith(".jpeg") || name.endsWith(".jpg")) ? "image/jpeg" : name.endsWith(".amr") ? "audio/amr" : name.endsWith(".mp4") ? "video/mp4" : "image/png";
    }

    private long onDownloadCompleted(HttpResponse httpResponse, CloudOperationCallback cloudOperationCallback, String str) throws IOException, IllegalStateException {
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
                byte[] bArr = new byte[NetUtils.getDownloadBufSize(this.appContext)];
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
                            EMLog.d("HttpFileManager", i2 + "");
                            if (i2 == 100 || i2 > i + 5) {
                                if (cloudOperationCallback != null) {
                                    cloudOperationCallback.onProgress(i2);
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

    private void processHeaders(HttpGet httpGet, Map<String, String> map) {
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

    private String processUrl(String str) {
        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
            str = str.replaceAll("\\+", "%2B");
        }
        return str.contains("#") ? str.replaceAll("#", "%23") : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFiletoServerHttp(String str, String str2, String str3, String str4, Map<String, String> map, CloudOperationCallback cloudOperationCallback) {
        sendFiletoServerHttpWithCountDown(str, str2, str3, str4, map, cloudOperationCallback, -1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.hyphenate.cloud.HttpFileManager$5] */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.hyphenate.cloud.HttpFileManager$4] */
    /* JADX WARN: Type inference failed for: r2v40, types: [com.hyphenate.cloud.HttpFileManager$3] */
    /* JADX WARN: Type inference failed for: r2v41, types: [com.hyphenate.cloud.HttpFileManager$2] */
    public void sendFiletoServerHttpWithCountDown(final String str, final String str2, final String str3, final String str4, Map<String, String> map, final CloudOperationCallback cloudOperationCallback, int i, boolean z) {
        Exception e;
        String str5;
        EMLog.d("CloudFileManager", "sendFiletoServerHttpWithCountDown .....");
        File file = new File(str);
        if (!file.isFile()) {
            EMLog.e("CloudFileManager", "Source file doesn't exist");
            cloudOperationCallback.onError("Source file doesn't exist");
        } else if (file.length() > MAX_ALLOWED_FILE_SIZE) {
            cloudOperationCallback.onError("file doesn't bigger than 10 M");
        } else {
            final Map<String, String> addDomainToHeaders = HttpClientManager.addDomainToHeaders(map);
            String fileRemoteUrl = HttpClientConfig.getFileRemoteUrl(str2);
            EMLog.d("CloudFileManager", " remote path url : " + fileRemoteUrl + " --countDown: " + i);
            DefaultHttpClient defaultHttpClient = HttpClientConfig.getDefaultHttpClient(HttpClientConfig.getTimeout(addDomainToHeaders));
            try {
                HttpPost httpPost = new HttpPost(fileRemoteUrl);
                CustomMultiPartEntity customMultiPartEntity = new CustomMultiPartEntity(new CustomMultiPartEntity.ProgressListener() { // from class: com.hyphenate.cloud.HttpFileManager.1
                    @Override // com.hyphenate.cloud.CustomMultiPartEntity.ProgressListener
                    public void transferred(long j) {
                        int i2 = (int) ((((float) j) / ((float) HttpFileManager.this.totalSize)) * 100.0f);
                        if (i2 != 100 && cloudOperationCallback != null) {
                            cloudOperationCallback.onProgress(i2);
                        }
                    }
                });
                if (str3 != null) {
                    customMultiPartEntity.addPart("app", new StringBody(str3));
                }
                if (str4 != null) {
                    customMultiPartEntity.addPart("id", new StringBody(str4));
                }
                if (addDomainToHeaders != null) {
                    for (Map.Entry<String, String> entry : addDomainToHeaders.entrySet()) {
                        httpPost.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                if (str2.indexOf("/") > 0) {
                    String substring = str2.substring(0, str2.lastIndexOf("/"));
                    str5 = str2.substring(str2.lastIndexOf("/"));
                    customMultiPartEntity.addPart("path", new StringBody(substring));
                } else {
                    str5 = str2;
                }
                String mimeType = getMimeType(file);
                EMLog.d("CloudFileManager", " remote file name : " + str5);
                customMultiPartEntity.addPart("file", new FileBody(file, str5, mimeType, "UTF-8"));
                this.totalSize = customMultiPartEntity.getContentLength();
                httpPost.setEntity(customMultiPartEntity);
                if (EMHttpClient.getInstance().chatConfig().g()) {
                    HttpClientManager.checkAndProcessSSL(fileRemoteUrl, defaultHttpClient);
                }
                HttpResponse execute = defaultHttpClient.execute(httpPost);
                int statusCode = execute.getStatusLine().getStatusCode();
                EMLog.d("CloudFileManager", "server responseCode:" + statusCode + " localFilePath : " + str);
                switch (statusCode) {
                    case 200:
                        cloudOperationCallback.onProgress(100);
                        cloudOperationCallback.onSuccess(EntityUtils.toString(execute.getEntity()));
                        return;
                    case 401:
                        if (System.currentTimeMillis() - EMHttpClient.getInstance().chatConfig().o() <= 600000) {
                            if (cloudOperationCallback != null) {
                                cloudOperationCallback.onError("unauthorized file");
                                return;
                            }
                            return;
                        } else if (this.tokenRetrieved) {
                            cloudOperationCallback.onError("unauthorized file");
                            return;
                        } else {
                            String accessToken = EMClient.getInstance().getOptions().getAccessToken(true);
                            this.tokenRetrieved = true;
                            if (accessToken == null) {
                                cloudOperationCallback.onError("unauthorized token is null");
                                return;
                            }
                            addDomainToHeaders.put("Authorization", "Bearer " + accessToken);
                            if (!z) {
                                new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.2
                                    @Override // java.lang.Thread, java.lang.Runnable
                                    public void run() {
                                        HttpFileManager.this.sendFiletoServerHttpWithCountDown(str, str2, str3, str4, addDomainToHeaders, cloudOperationCallback, 3, true);
                                    }
                                }.start();
                                return;
                            } else if (i > 0) {
                                final int i2 = i - 1;
                                try {
                                    new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.3
                                        @Override // java.lang.Thread, java.lang.Runnable
                                        public void run() {
                                            HttpFileManager.this.sendFiletoServerHttpWithCountDown(str, str2, str3, str4, addDomainToHeaders, cloudOperationCallback, i2, true);
                                        }
                                    }.start();
                                    return;
                                } catch (Exception e2) {
                                    e = e2;
                                    i = i2;
                                    String message = (e == null || e.getMessage() == null) ? "failed to upload the files" : e.getMessage();
                                    EMLog.e("CloudFileManager", "sendFiletoServerHttp:" + message);
                                    if (message.toLowerCase().contains(EMPrivateConstant.CONNECTION_REFUSED) && NetUtils.hasNetwork(this.appContext)) {
                                        if (!z) {
                                            final String newHost = HttpClientManager.getNewHost(str2, EMHttpClient.getInstance().chatConfig().m());
                                            new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.4
                                                @Override // java.lang.Thread, java.lang.Runnable
                                                public void run() {
                                                    HttpFileManager.this.sendFiletoServerHttpWithCountDown(str, newHost, str3, str4, addDomainToHeaders, cloudOperationCallback, 20, true);
                                                }
                                            }.start();
                                            return;
                                        } else if (i > 0) {
                                            final String newHost2 = HttpClientManager.getNewHost(str2, EMHttpClient.getInstance().chatConfig().m());
                                            final int i3 = i - 1;
                                            new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.5
                                                @Override // java.lang.Thread, java.lang.Runnable
                                                public void run() {
                                                    HttpFileManager.this.sendFiletoServerHttpWithCountDown(str, newHost2, str3, str4, addDomainToHeaders, cloudOperationCallback, i3, true);
                                                }
                                            }.start();
                                            return;
                                        }
                                    }
                                    if (cloudOperationCallback != null) {
                                        cloudOperationCallback.onError(message);
                                        return;
                                    }
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    default:
                        String str6 = "Http response error : " + statusCode + " error msg : " + EntityUtils.toString(execute.getEntity());
                        EMLog.e("CloudFileManager", str6);
                        if (cloudOperationCallback != null) {
                            cloudOperationCallback.onError(str6);
                            return;
                        }
                        return;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
    }

    @Override // com.hyphenate.cloud.CloudFileManager
    public boolean authorization() {
        return true;
    }

    @Override // com.hyphenate.cloud.CloudFileManager
    public void deleteFileInBackground(final String str, final String str2, String str3, final CloudOperationCallback cloudOperationCallback) {
        new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.9
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(HttpClientConfig.getFileRemoteUrl(str)).openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
                    httpURLConnection.setRequestProperty("ENCTYPE", com.hy.http.HttpEntity.MULTIPART_FORM_DATA);
                    httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
                    httpURLConnection.setRequestProperty("file", str);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.writeBytes("--*****\r\n");
                    if (str2 != null) {
                        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"app\"\r\n\r\n");
                        dataOutputStream.writeBytes(str2 + "\r\n");
                        dataOutputStream.writeBytes("--*****\r\n");
                    }
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + str + "\"\r\n");
                    dataOutputStream.writeBytes("\r\n");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        EMLog.d("CloudFileManager", "RESULT Message: " + readLine);
                    }
                    bufferedReader.close();
                    dataOutputStream.close();
                    httpURLConnection.disconnect();
                    if (cloudOperationCallback != null) {
                        cloudOperationCallback.onSuccess(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (cloudOperationCallback != null) {
                        cloudOperationCallback.onError(e.toString());
                    }
                }
            }
        }.start();
    }

    @Override // com.hyphenate.cloud.CloudFileManager
    public void downloadFile(String str, String str2, String str3, Map<String, String> map, CloudOperationCallback cloudOperationCallback) {
        if (TextUtils.isEmpty(str)) {
            if (cloudOperationCallback != null) {
                cloudOperationCallback.onError("remotefilepath is null or empty");
            }
            EMLog.e("CloudFileManager", "remotefilepath is null or empty");
            return;
        }
        downloadFile(HttpClientConfig.getFileRemoteUrl(str), str2, map, cloudOperationCallback);
    }

    public void downloadFile(String str, String str2, Map<String, String> map, CloudOperationCallback cloudOperationCallback) {
        try {
            downloadFileWithCountDown(str, str2, map, cloudOperationCallback, 20);
        } catch (Exception e) {
            String str3 = "failed to download file : " + str;
            if (!(e == null || e.toString() == null)) {
                str3 = e.toString();
            }
            if (cloudOperationCallback != null) {
                cloudOperationCallback.onError(str3);
            }
        }
    }

    public void uploadFile(String str, String str2, String str3, String str4, Map<String, String> map, CloudOperationCallback cloudOperationCallback) {
        try {
            sendFiletoServerHttp(str, str2, str3, str4, map, cloudOperationCallback);
        } catch (Exception e) {
            EMLog.e("CloudFileManager", "uploadFile error:" + e.toString());
            cloudOperationCallback.onError(e.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hyphenate.cloud.HttpFileManager$6] */
    @Override // com.hyphenate.cloud.CloudFileManager
    public void uploadFileInBackground(final String str, final String str2, final String str3, final String str4, final Map<String, String> map, final CloudOperationCallback cloudOperationCallback) {
        new Thread() { // from class: com.hyphenate.cloud.HttpFileManager.6
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    HttpFileManager.this.sendFiletoServerHttp(str, str2, str3, str4, map, cloudOperationCallback);
                } catch (Exception e) {
                    if (e == null || e.toString() == null) {
                        cloudOperationCallback.onError("failed to upload the file : " + str + " remote path : " + str2);
                        return;
                    }
                    EMLog.e("CloudFileManager", e.toString());
                    cloudOperationCallback.onError(e.toString());
                }
            }
        }.start();
    }
}
