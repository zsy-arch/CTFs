package com.alibaba.sdk.android.oss.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.common.utils.VersionInfoUtils;
import com.alibaba.sdk.android.oss.internal.ResponseParsers;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import com.alibaba.sdk.android.oss.network.OSSRequestTask;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public class InternalRequestOperation {
    private static final int LIST_PART_MAX_RETURNS = 1000;
    private static final int MAX_PART_NUMBER = 10000;
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Context applicationContext;
    private ClientConfiguration conf;
    private OSSCredentialProvider credentialProvider;
    private volatile URI endpoint;
    private OkHttpClient innerClient;
    private int maxRetryCount;

    public InternalRequestOperation(Context context, final URI endpoint, OSSCredentialProvider credentialProvider, ClientConfiguration conf) {
        this.maxRetryCount = 2;
        this.applicationContext = context;
        this.endpoint = endpoint;
        this.credentialProvider = credentialProvider;
        this.conf = conf;
        OkHttpClient.Builder builder = new OkHttpClient.Builder().followRedirects(false).followSslRedirects(false).retryOnConnectionFailure(false).cache(null).hostnameVerifier(new HostnameVerifier() { // from class: com.alibaba.sdk.android.oss.internal.InternalRequestOperation.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(endpoint.getHost(), session);
            }
        });
        if (conf != null) {
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(conf.getMaxConcurrentRequest());
            builder.connectTimeout(conf.getConnectionTimeout(), TimeUnit.MILLISECONDS).readTimeout(conf.getSocketTimeout(), TimeUnit.MILLISECONDS).writeTimeout(conf.getSocketTimeout(), TimeUnit.MILLISECONDS).dispatcher(dispatcher);
            if (!(conf.getProxyHost() == null || conf.getProxyPort() == 0)) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(conf.getProxyHost(), conf.getProxyPort())));
            }
            this.maxRetryCount = conf.getMaxErrorRetry();
        }
        this.innerClient = builder.build();
    }

    public OSSAsyncTask<PutObjectResult> putObject(PutObjectRequest request, OSSCompletedCallback<PutObjectRequest, PutObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        if (request.getUploadData() != null) {
            requestMessage.setUploadData(request.getUploadData());
        }
        if (request.getUploadFilePath() != null) {
            requestMessage.setUploadFilePath(request.getUploadFilePath());
        }
        if (request.getCallbackParam() != null) {
            requestMessage.getHeaders().put("x-oss-callback", OSSUtils.populateMapToBase64JsonString(request.getCallbackParam()));
        }
        if (request.getCallbackVars() != null) {
            requestMessage.getHeaders().put("x-oss-callback-var", OSSUtils.populateMapToBase64JsonString(request.getCallbackVars()));
        }
        OSSUtils.populateRequestMetadata(requestMessage.getHeaders(), request.getMetadata());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<PutObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        executionContext.setProgressCallback(request.getProgressCallback());
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.PutObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<CreateBucketResult> createBucket(CreateBucketRequest request, OSSCompletedCallback<CreateBucketRequest, CreateBucketResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setBucketName(request.getBucketName());
        if (request.getBucketACL() != null) {
            requestMessage.getHeaders().put(OSSHeaders.OSS_CANNED_ACL, request.getBucketACL().toString());
        }
        try {
            requestMessage.createBucketRequestBodyMarshall(request.getLocationConstraint());
            canonicalizeRequestMessage(requestMessage);
            ExecutionContext<CreateBucketRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
            if (completedCallback != null) {
                executionContext.setCompletedCallback(completedCallback);
            }
            return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.CreateBucketResponseParser(), executionContext, this.maxRetryCount)), executionContext);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OSSAsyncTask<DeleteBucketResult> deleteBucket(DeleteBucketRequest request, OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.DELETE);
        requestMessage.setBucketName(request.getBucketName());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<DeleteBucketRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.DeleteBucketResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<GetBucketACLResult> getBucketACL(GetBucketACLRequest request, OSSCompletedCallback<GetBucketACLRequest, GetBucketACLResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        Map<String, String> query = new LinkedHashMap<>();
        query.put(RequestParameters.SUBRESOURCE_ACL, "");
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setParameters(query);
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<GetBucketACLRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.GetBucketACLResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<AppendObjectResult> appendObject(AppendObjectRequest request, OSSCompletedCallback<AppendObjectRequest, AppendObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.POST);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        if (request.getUploadData() != null) {
            requestMessage.setUploadData(request.getUploadData());
        }
        if (request.getUploadFilePath() != null) {
            requestMessage.setUploadFilePath(request.getUploadFilePath());
        }
        requestMessage.getParameters().put(RequestParameters.SUBRESOURCE_APPEND, "");
        requestMessage.getParameters().put(RequestParameters.POSITION, String.valueOf(request.getPosition()));
        OSSUtils.populateRequestMetadata(requestMessage.getHeaders(), request.getMetadata());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<AppendObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        executionContext.setProgressCallback(request.getProgressCallback());
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.AppendObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<HeadObjectResult> headObject(HeadObjectRequest request, OSSCompletedCallback<HeadObjectRequest, HeadObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.HEAD);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<HeadObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.HeadObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<GetObjectResult> getObject(GetObjectRequest request, OSSCompletedCallback<GetObjectRequest, GetObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        if (request.getRange() != null) {
            requestMessage.getHeaders().put(HttpHeaders.RANGE, request.getRange().toString());
        }
        if (request.getxOssProcess() != null) {
            requestMessage.getParameters().put(RequestParameters.X_OSS_PROCESS, request.getxOssProcess());
        }
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<GetObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        executionContext.setProgressCallback(request.getProgressListener());
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.GetObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<CopyObjectResult> copyObject(CopyObjectRequest request, OSSCompletedCallback<CopyObjectRequest, CopyObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setBucketName(request.getDestinationBucketName());
        requestMessage.setObjectKey(request.getDestinationKey());
        OSSUtils.populateCopyObjectHeaders(request, requestMessage.getHeaders());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<CopyObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.CopyObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<DeleteObjectResult> deleteObject(DeleteObjectRequest request, OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.DELETE);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<DeleteObjectRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.DeleteObjectResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<ListObjectsResult> listObjects(ListObjectsRequest request, OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setBucketName(request.getBucketName());
        canonicalizeRequestMessage(requestMessage);
        OSSUtils.populateListObjectsRequestParameters(request, requestMessage.getParameters());
        ExecutionContext<ListObjectsRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.ListObjectsResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<InitiateMultipartUploadResult> initMultipartUpload(InitiateMultipartUploadRequest request, OSSCompletedCallback<InitiateMultipartUploadRequest, InitiateMultipartUploadResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.POST);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        requestMessage.getParameters().put(RequestParameters.SUBRESOURCE_UPLOADS, "");
        OSSUtils.populateRequestMetadata(requestMessage.getHeaders(), request.getMetadata());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<InitiateMultipartUploadRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.InitMultipartResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<UploadPartResult> uploadPart(UploadPartRequest request, OSSCompletedCallback<UploadPartRequest, UploadPartResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.PUT);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        requestMessage.getParameters().put(RequestParameters.UPLOAD_ID, request.getUploadId());
        requestMessage.getParameters().put(RequestParameters.PART_NUMBER, String.valueOf(request.getPartNumber()));
        requestMessage.setUploadData(request.getPartContent());
        if (request.getMd5Digest() != null) {
            requestMessage.getHeaders().put(HttpHeaders.CONTENT_MD5, request.getMd5Digest());
        }
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<UploadPartRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        executionContext.setProgressCallback(request.getProgressCallback());
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.UploadPartResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<CompleteMultipartUploadResult> completeMultipartUpload(CompleteMultipartUploadRequest request, OSSCompletedCallback<CompleteMultipartUploadRequest, CompleteMultipartUploadResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.POST);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        requestMessage.setUploadData(OSSUtils.buildXMLFromPartEtagList(request.getPartETags()).getBytes());
        requestMessage.getParameters().put(RequestParameters.UPLOAD_ID, request.getUploadId());
        if (request.getCallbackParam() != null) {
            requestMessage.getHeaders().put("x-oss-callback", OSSUtils.populateMapToBase64JsonString(request.getCallbackParam()));
        }
        if (request.getCallbackVars() != null) {
            requestMessage.getHeaders().put("x-oss-callback-var", OSSUtils.populateMapToBase64JsonString(request.getCallbackVars()));
        }
        OSSUtils.populateRequestMetadata(requestMessage.getHeaders(), request.getMetadata());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<CompleteMultipartUploadRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.CompleteMultipartUploadResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<AbortMultipartUploadResult> abortMultipartUpload(AbortMultipartUploadRequest request, OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.DELETE);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        requestMessage.getParameters().put(RequestParameters.UPLOAD_ID, request.getUploadId());
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<AbortMultipartUploadRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.AbortMultipartUploadResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    public OSSAsyncTask<ListPartsResult> listParts(ListPartsRequest request, OSSCompletedCallback<ListPartsRequest, ListPartsResult> completedCallback) {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setIsAuthorizationRequired(request.isAuthorizationRequired());
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(HttpMethod.GET);
        requestMessage.setBucketName(request.getBucketName());
        requestMessage.setObjectKey(request.getObjectKey());
        requestMessage.getParameters().put(RequestParameters.UPLOAD_ID, request.getUploadId());
        Integer maxParts = request.getMaxParts();
        if (maxParts != null) {
            if (!OSSUtils.checkParamRange(maxParts.intValue(), 0L, true, 1000L, true)) {
                throw new IllegalArgumentException("MaxPartsOutOfRange: 1000");
            }
            requestMessage.getParameters().put(RequestParameters.MAX_PARTS, maxParts.toString());
        }
        Integer partNumberMarker = request.getPartNumberMarker();
        if (partNumberMarker != null) {
            if (!OSSUtils.checkParamRange(partNumberMarker.intValue(), 0L, false, 10000L, true)) {
                throw new IllegalArgumentException("PartNumberMarkerOutOfRange: 10000");
            }
            requestMessage.getParameters().put(RequestParameters.PART_NUMBER_MARKER, partNumberMarker.toString());
        }
        canonicalizeRequestMessage(requestMessage);
        ExecutionContext<ListPartsRequest> executionContext = new ExecutionContext<>(getInnerClient(), request);
        if (completedCallback != null) {
            executionContext.setCompletedCallback(completedCallback);
        }
        return OSSAsyncTask.wrapRequestTask(executorService.submit(new OSSRequestTask(requestMessage, new ResponseParsers.ListPartsResponseParser(), executionContext, this.maxRetryCount)), executionContext);
    }

    private boolean checkIfHttpdnsAwailable() {
        String proxyHost;
        if (this.applicationContext == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            proxyHost = System.getProperty("http.proxyHost");
        } else {
            proxyHost = android.net.Proxy.getHost(this.applicationContext);
        }
        String confProxyHost = this.conf.getProxyHost();
        if (!TextUtils.isEmpty(confProxyHost)) {
            proxyHost = confProxyHost;
        }
        return TextUtils.isEmpty(proxyHost);
    }

    public OkHttpClient getInnerClient() {
        return this.innerClient;
    }

    private void canonicalizeRequestMessage(RequestMessage message) {
        Map<String, String> header = message.getHeaders();
        if (header.get("Date") == null) {
            header.put("Date", DateUtil.currentFixedSkewedTimeInRFC822Format());
        }
        if ((message.getMethod() == HttpMethod.POST || message.getMethod() == HttpMethod.PUT) && header.get("Content-Type") == null) {
            header.put("Content-Type", OSSUtils.determineContentType(null, message.getUploadFilePath(), message.getObjectKey()));
        }
        message.setIsHttpdnsEnable(checkIfHttpdnsAwailable());
        message.setCredentialProvider(this.credentialProvider);
        message.getHeaders().put("User-Agent", VersionInfoUtils.getUserAgent());
        message.setIsInCustomCnameExcludeList(OSSUtils.isInCustomCnameExcludeList(this.endpoint.getHost(), this.conf.getCustomCnameExcludeList()));
    }

    public void setCredentialProvider(OSSCredentialProvider credentialProvider) {
        this.credentialProvider = credentialProvider;
    }
}
