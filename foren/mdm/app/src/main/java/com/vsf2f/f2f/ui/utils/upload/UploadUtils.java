package com.vsf2f.f2f.ui.utils.upload;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alipay.sdk.data.a;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.EOSSCallbackBody;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.HttpEntity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.BitmapUtils;
import com.vsf2f.f2f.ui.utils.listener.GetOssToken;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UploadUtils {
    private static final String LOG_TAG = "UploadUtils";
    public static final String UPLOAND_URL = "http://oss-cn-hangzhou.aliyuncs.com";
    private boolean isError;
    private List<String> netImageList = new ArrayList();
    private List<String> picIds;
    private List<Map<String, String>> picMaps;
    private List<String> picUrls;

    public void UploadFileGetUrl(Context context, String prex, String userName, String bucketName, int picType, @Nullable List<String> paths, UploadPicListener listener) {
        UploadPicturesGetUrl(context, prex, userName, bucketName, picType, paths, listener);
    }

    public void UploadPicturesGetOSS(Context context, String userName, String bucketName, int picType, @Nullable List<String> paths, @Nullable List<Map<String, String>> list, UploadPicListener listener) {
        UploadPicturesGetOSS(context, userName, bucketName, picType, null, paths, list, listener);
    }

    public void UploadPicturesGetOSS(final Context context, final String userName, String bucketName, final int picType, String prex, @Nullable final List<String> paths, @Nullable final List<Map<String, String>> list, final UploadPicListener listener) {
        if (!HyUtil.isNetworkConnected(context)) {
            MyToast.show(context, (int) R.string.API_FLAG_NET_FAIL);
            return;
        }
        OSSClient ossClient = GetOssToken(context, userName, bucketName, new GetOssToken() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.1
            @Override // com.vsf2f.f2f.ui.utils.listener.GetOssToken
            public void onFinish(boolean isFinish) {
                MyLog.e(UploadUtils.LOG_TAG, "GetOssToken:" + isFinish);
            }
        });
        String bucketName2 = "f2f-" + bucketName;
        boolean isMap = false;
        if (paths != null && list == null) {
            isMap = false;
            this.isError = false;
            listener.onProgress(0L, paths.size());
        } else if (paths == null && list != null) {
            isMap = true;
            listener.onProgress(0L, list.size());
        }
        this.isError = false;
        this.netImageList.clear();
        this.picIds = new ArrayList();
        this.picUrls = new ArrayList();
        this.picMaps = new ArrayList();
        if (!isMap) {
            for (int i = 0; i < paths.size(); i++) {
                String path = paths.get(i);
                if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    this.netImageList.add(path.substring(path.indexOf(".com") + 5, path.indexOf("?")));
                    if (this.netImageList.size() == paths.size()) {
                        listener.onSuccess(null, this.netImageList);
                    }
                } else {
                    if (TextUtils.isEmpty(prex)) {
                        prex = "";
                    }
                    String objectKey = ComUtil.getObjectKey(prex + userName, path);
                    this.picUrls.add(objectKey);
                    MyLog.e(LOG_TAG, "objectKey:" + objectKey);
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType("application/octet-stream");
                    PutObjectRequest put = new PutObjectRequest(bucketName2, objectKey, path);
                    put.setMetadata(metadata);
                    final String cbStr = EOSSCallbackBody.getCallbackBodyStr(EOSSCallbackBody.values());
                    put.setCallbackParam(new HashMap<String, String>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.2
                        {
                            UploadUtils.this = this;
                            put("callbackUrl", context.getString(R.string.API_HOST) + "/app/oss/upload/callback/" + userName + "/" + picType + ".app");
                            put("callbackBody", cbStr);
                            put("callbackBodyType", "application/json");
                        }
                    });
                    put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.3
                        public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                            MyLog.d("PutObject", "progress: " + currentSize + " / " + totalSize);
                        }
                    });
                    ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.4
                        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                            UploadUtils.this.dealResult1(context, result.getServerCallbackReturnBody(), paths.size(), listener);
                        }

                        public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                            UploadUtils.this.isError = true;
                            if (clientExcepion != null) {
                                HyUtil.printException(clientExcepion);
                            }
                            if (serviceException != null) {
                                MyLog.e(serviceException.getErrorCode());
                                MyLog.e(serviceException.getRequestId());
                                MyLog.e(serviceException.getHostId());
                                MyLog.e(serviceException.getRawMessage());
                            }
                            listener.onFailed();
                        }
                    });
                }
            }
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            final Map<String, String> map = list.get(i2);
            if (map.containsKey("id")) {
                String path2 = map.get("id");
                String objectKey2 = ComUtil.getObjectKey(userName, path2);
                final String picUrl = context.getString(R.string.API_HOST) + "/" + objectKey2;
                this.picUrls.add(picUrl);
                MyLog.e(LOG_TAG, "objectKey:" + objectKey2);
                PutObjectRequest put2 = new PutObjectRequest(bucketName2, objectKey2, BitmapUtils.getInputStream(path2));
                ObjectMetadata metadata2 = new ObjectMetadata();
                metadata2.setContentType("image/jpeg/jpg");
                put2.setMetadata(metadata2);
                final String cbStr2 = EOSSCallbackBody.getCallbackBodyStr(EOSSCallbackBody.values());
                put2.setCallbackParam(new HashMap<String, String>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.5
                    {
                        UploadUtils.this = this;
                        put("callbackUrl", context.getString(R.string.API_HOST) + "/app/oss/upload/callback/" + userName + "/" + picType + ".app");
                        put("callbackBody", cbStr2);
                        put("callbackBodyType", "application/json");
                    }
                });
                put2.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.6
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        MyLog.d("PutObject", "progress: " + currentSize + " / " + totalSize);
                    }
                });
                ossClient.asyncPutObject(put2, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.7
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        UploadUtils.this.dealResultMap(context, result.getServerCallbackReturnBody(), list.size(), listener, map, list, picUrl);
                    }

                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        UploadUtils.this.isError = true;
                        if (clientExcepion != null) {
                            HyUtil.printException(clientExcepion);
                        }
                        if (serviceException != null) {
                            MyLog.e(serviceException.getErrorCode());
                            MyLog.e(serviceException.getRequestId());
                            MyLog.e(serviceException.getHostId());
                            MyLog.e(serviceException.getRawMessage());
                        }
                        listener.onFailed();
                    }
                });
            }
        }
    }

    public void UploadPicturesGetUrl(Context context, String prex, String username, String bucketName, int picType, @Nullable final List<String> paths, final UploadPicListener listener) {
        if (!HyUtil.isNetworkConnected(context)) {
            MyToast.show(context, (int) R.string.API_FLAG_NET_FAIL);
            return;
        }
        OSSClient ossClient = GetOssToken(context, username, bucketName, new GetOssToken() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.8
            @Override // com.vsf2f.f2f.ui.utils.listener.GetOssToken
            public void onFinish(boolean isFinish) {
                MyLog.e(UploadUtils.LOG_TAG, "GetOssToken:" + isFinish);
            }
        });
        String bucketName2 = "f2f-" + bucketName;
        this.isError = false;
        listener.onProgress(0L, paths.size());
        this.netImageList.clear();
        this.picIds = new ArrayList();
        this.picUrls = new ArrayList();
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            if (!TextUtils.isEmpty(path)) {
                if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    String netPath = path.substring(path.indexOf(".com") + 5, path.indexOf("?"));
                    if (this.picUrls.size() >= paths.size()) {
                        this.picUrls.add(netPath);
                    }
                } else {
                    if (TextUtils.isEmpty(prex)) {
                        prex = "";
                    }
                    final String objectKey = ComUtil.getObjectKey(prex + username, path);
                    MyLog.e(LOG_TAG, "objectKey:" + objectKey);
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType("application/octet-stream");
                    PutObjectRequest put = new PutObjectRequest(bucketName2, objectKey, path);
                    put.setMetadata(metadata);
                    ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.9
                        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                            UploadUtils.this.picUrls.add(objectKey);
                            if (UploadUtils.this.picUrls.size() >= paths.size()) {
                                listener.onSuccess(null, UploadUtils.this.picUrls);
                            }
                        }

                        public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                            UploadUtils.this.isError = true;
                            if (clientExcepion != null) {
                                if (a.f.equals(clientExcepion.getMessage())) {
                                    MyLog.e("网络超时，请检查网络");
                                }
                                HyUtil.printException(clientExcepion);
                            }
                            if (serviceException != null) {
                                MyLog.e(serviceException.getErrorCode());
                                MyLog.e(serviceException.getRequestId());
                                MyLog.e(serviceException.getHostId());
                                MyLog.e(serviceException.getRawMessage());
                            }
                            listener.onFailed();
                        }
                    });
                }
            }
        }
    }

    public void UploadFileList(final Context context, final String userName, String bucketName, final int picType, String prex, @Nullable final List<String> paths, final UploadPicListener listener) {
        if (!HyUtil.isNetworkConnected(context)) {
            MyToast.show(context, (int) R.string.API_FLAG_NET_FAIL);
            return;
        }
        OSS oss = GetOssToken(context, userName, bucketName, new GetOssToken() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.10
            @Override // com.vsf2f.f2f.ui.utils.listener.GetOssToken
            public void onFinish(boolean isFinish) {
                MyLog.e(UploadUtils.LOG_TAG, "GetOssToken:" + isFinish);
            }
        });
        listener.onProgress(0L, paths.size());
        this.picIds = new ArrayList();
        this.picUrls = new ArrayList();
        String bucketName2 = "f2f-" + bucketName;
        if (TextUtils.isEmpty(prex)) {
            prex = "";
        }
        String prexStr = prex + userName;
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            if (HyUtil.isEmpty(path)) {
                this.picUrls.add("");
                dealResultCom("-1", paths.size(), listener);
            } else if (path.startsWith(prexStr)) {
                this.picUrls.add(path);
                dealResultCom("-1", paths.size(), listener);
            } else {
                String objectKey = ComUtil.getObjectKey(prexStr, path);
                this.picUrls.add(objectKey);
                MyLog.e(LOG_TAG, "objectKey:" + objectKey);
                PutObjectRequest put = new PutObjectRequest(bucketName2, objectKey, BitmapUtils.getInputStream(path));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("application/octet-stream");
                put.setMetadata(metadata);
                final String cbStr = EOSSCallbackBody.getCallbackBodyStr(EOSSCallbackBody.values());
                put.setCallbackParam(new HashMap<String, String>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.11
                    {
                        UploadUtils.this = this;
                        put("callbackUrl", context.getString(R.string.API_HOST) + "/app/oss/upload/callback/" + userName + "/" + picType + ".app");
                        put("callbackBody", cbStr);
                        put("callbackBodyType", "application/json");
                    }
                });
                oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.12
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        String uploadId = "";
                        try {
                            JSONObject jsonBody = new JSONObject(result.getServerCallbackReturnBody());
                            MyLog.e(UploadUtils.LOG_TAG, "object---what=1---" + jsonBody.toString());
                            if ("0".equals(jsonBody.getString("ret"))) {
                                uploadId = jsonBody.getString("obj");
                            } else {
                                MyLog.e("", URLDecoder.decode(jsonBody.getString("message"), "utf-8"));
                                listener.onSuccess(null, null);
                                Toast.makeText(context, "第" + (UploadUtils.this.picIds.size() + 1) + "图片格式错误", 0).show();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        UploadUtils.this.dealResultCom(uploadId, paths.size(), listener);
                    }

                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        UploadUtils.this.isError = true;
                        if (clientExcepion != null) {
                            HyUtil.printException(clientExcepion);
                        }
                        if (serviceException != null) {
                            MyLog.e(serviceException.getErrorCode());
                            MyLog.e(serviceException.getRequestId());
                            MyLog.e(serviceException.getHostId());
                            MyLog.e(serviceException.getRawMessage());
                        }
                        listener.onFailed();
                    }
                });
            }
        }
    }

    public void UploadArtPicOSS(Context context, String userName, String bucketName, int picType, final int imgCount, @Nullable final List<Map<String, String>> list, final UploadPicListener listener) {
        if (!HyUtil.isNetworkConnected(context)) {
            MyToast.show(context, (int) R.string.API_FLAG_NET_FAIL);
            return;
        }
        OSS oss = GetOssToken(context, userName, bucketName, new GetOssToken() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.13
            @Override // com.vsf2f.f2f.ui.utils.listener.GetOssToken
            public void onFinish(boolean isFinish) {
                MyLog.e(UploadUtils.LOG_TAG, "GetOssToken:" + isFinish);
            }
        });
        String bucketName2 = "f2f-" + bucketName;
        if (HyUtil.isEmpty(list) || imgCount == 0) {
            listener.onSuccess(list, null);
            return;
        }
        this.picIds = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            final Map<String, String> map = list.get(i);
            if (map.containsKey("id")) {
                PutObjectRequest put = new PutObjectRequest(bucketName2, map.get("id"), map.get("path"));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("image/jpeg/jpg");
                put.setMetadata(metadata);
                oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.14
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        try {
                            JSONObject jsonBody = new JSONObject(result.getServerCallbackReturnBody());
                            Log.e(UploadUtils.LOG_TAG, "object--what=2---" + jsonBody.toString());
                            if ("0".equals(jsonBody.getString("ret"))) {
                                String requestid = jsonBody.getString("obj");
                                map.put("id", requestid);
                                UploadUtils.this.picIds.add(requestid);
                            }
                            if (UploadUtils.this.picIds.size() == imgCount) {
                                listener.onSuccess(list, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        UploadUtils.this.isError = true;
                        if (clientExcepion != null) {
                            HyUtil.printException(clientExcepion);
                        }
                        if (serviceException != null) {
                            MyLog.e(serviceException.getErrorCode());
                            MyLog.e(serviceException.getRequestId());
                            MyLog.e(serviceException.getHostId());
                            MyLog.e(serviceException.getRawMessage());
                        }
                        listener.onFailed();
                    }
                });
            }
        }
    }

    public void UploadCirclePic(Context context, String userName, String bucketName, String prex, @Nullable final Map<String, String> maps, final UploadPicListener listener) {
        if (!HyUtil.isNetworkConnected(context)) {
            MyToast.show(context, (int) R.string.API_FLAG_NET_FAIL);
            return;
        }
        OSSClient ossClient = GetOssToken(context, userName, bucketName, new GetOssToken() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.15
            @Override // com.vsf2f.f2f.ui.utils.listener.GetOssToken
            public void onFinish(boolean isFinish) {
                MyLog.e(UploadUtils.LOG_TAG, "GetOssToken:" + isFinish);
            }
        });
        this.picUrls = new ArrayList();
        String bucketName2 = "f2f-" + bucketName;
        if (maps != null) {
            for (final String objectKey : maps.keySet()) {
                if (TextUtils.isEmpty(prex)) {
                    prex = "";
                }
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("application/octet-stream");
                PutObjectRequest put = new PutObjectRequest(bucketName2, objectKey, maps.get(objectKey));
                put.setMetadata(metadata);
                ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.16
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        UploadUtils.this.picUrls.add(objectKey);
                        if (UploadUtils.this.picUrls.size() >= maps.size()) {
                            listener.onSuccess(null, UploadUtils.this.picUrls);
                        }
                    }

                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        UploadUtils.this.isError = true;
                        if (clientExcepion != null) {
                            HyUtil.printException(clientExcepion);
                        }
                        if (serviceException != null) {
                            MyLog.e(serviceException.getErrorCode());
                            MyLog.e(serviceException.getRequestId());
                            MyLog.e(serviceException.getHostId());
                            MyLog.e(serviceException.getRawMessage());
                        }
                        listener.onFailed();
                    }
                });
            }
        }
    }

    public synchronized void dealResult1(Context context, String picuploadBody, int sendCount, UploadPicListener listener) {
        Exception e;
        try {
            JSONObject jsonBody = new JSONObject(picuploadBody);
            MyLog.e(LOG_TAG, "result:" + jsonBody.toString());
            if ("0".equals(jsonBody.getString("ret"))) {
                this.picIds.add(jsonBody.getString("obj"));
                MyLog.e(LOG_TAG, "onProgress:已上传=" + this.picIds.size() + "；总上传=" + sendCount + "；网络地址=" + this.netImageList.size());
                if (this.picIds.size() + this.netImageList.size() >= sendCount) {
                    MyLog.e(LOG_TAG, "onProgress:sendallContent");
                    this.picUrls.addAll(this.netImageList);
                    listener.onSuccess(null, this.picIds);
                } else if (!this.isError) {
                    listener.onProgress(this.picIds.size(), sendCount);
                }
            } else {
                MyLog.e("", URLDecoder.decode(jsonBody.getString("message"), "utf-8"));
                listener.onSuccess(null, null);
                Toast.makeText(context, "第" + (this.picIds.size() + 1) + "图片格式错误", 0).show();
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            e.printStackTrace();
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
        }
    }

    public synchronized void dealResultCom(String uploadId, int sendCount, UploadPicListener listener) {
        listener.onProgress(this.picIds.size(), sendCount);
        this.picIds.add(uploadId);
        if (this.picIds.size() >= sendCount) {
            listener.onSuccess(null, this.picUrls);
        }
    }

    public synchronized void dealResultMap(Context context, String picuploadBody, int sendCount, UploadPicListener listener, Map<String, String> map, List<Map<String, String>> list, String url) {
        try {
            JSONObject jsonBody = new JSONObject(picuploadBody);
            MyLog.e(LOG_TAG, "dealResultMap" + jsonBody.toString());
            if ("0".equals(jsonBody.getString("ret"))) {
                String requestid = jsonBody.getString("obj");
                this.picIds.add(requestid);
                map.put("id", requestid);
                MyLog.e(LOG_TAG, "dealResultMap:" + this.picMaps.size() + "/" + sendCount + "  ");
                if (this.picIds.size() == sendCount) {
                    MyLog.e(LOG_TAG, "dealResultMap:sendallContent");
                    listener.onSuccess(list, null);
                }
            } else {
                Toast.makeText(context, "第" + (this.picIds.size() + 1) + "图片格式错误", 0).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static OSSClient GetOssToken(Context context, String userName, String bucketName, final GetOssToken listener) {
        final String uploadURL = context.getString(R.string.API_HOST) + "/app/oss/upload/get_sts/" + userName + "/" + bucketName + "/write.app";
        MyLog.e("uploadURL", uploadURL);
        OSSCredentialProvider credetialProvider = new OSSFederationCredentialProvider() { // from class: com.vsf2f.f2f.ui.utils.upload.UploadUtils.17
            @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider
            public OSSFederationToken getFederationToken() {
                JSONObject jsonObject;
                String ret;
                JSONObject jsonobject;
                try {
                    jsonObject = new JSONObject(IOUtils.readStreamAsString(((HttpURLConnection) new URL(uploadURL).openConnection()).getInputStream(), "utf-8"));
                    MyLog.e(UploadUtils.LOG_TAG, "jsonObject:" + jsonObject.toString());
                    ret = jsonObject.getString("ret");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(ret)) {
                    return null;
                }
                if ("0".equals(ret) && (jsonobject = jsonObject.optJSONObject("obj")) != null) {
                    String sk = jsonobject.optString("AccessKeySecret");
                    String ak = jsonobject.optString("AccessKeyId");
                    String token = jsonobject.optString("SecurityToken");
                    String expiration = jsonobject.optString("Expiration");
                    listener.onFinish(true);
                    return new OSSFederationToken(ak, sk, token, expiration);
                }
                listener.onFinish(false);
                return null;
            }
        };
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15000);
        conf.setSocketTimeout(15000);
        conf.setMaxConcurrentRequest(5);
        conf.setMaxErrorRetry(2);
        OSSLog.enableLog();
        return new OSSClient(context, "http://oss-cn-hangzhou.aliyuncs.com", credetialProvider, conf);
    }

    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") || FilenameExtension.equalsIgnoreCase(".jpg") || FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return HttpEntity.TEXT_HTML;
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") || FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") || FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return HttpEntity.TEXT_XML;
        }
        return "application/octet-stream";
    }
}
