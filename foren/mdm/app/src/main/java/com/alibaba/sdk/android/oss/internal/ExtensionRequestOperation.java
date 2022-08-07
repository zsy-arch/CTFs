package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class ExtensionRequestOperation {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    private InternalRequestOperation apiOperation;

    public ExtensionRequestOperation(InternalRequestOperation apiOperation) {
        this.apiOperation = apiOperation;
    }

    public boolean doesObjectExist(String bucketName, String objectKey) throws ClientException, ServiceException {
        try {
            this.apiOperation.headObject(new HeadObjectRequest(bucketName, objectKey), null).getResult();
            return true;
        } catch (ServiceException e) {
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    public void abortResumableUpload(ResumableUploadRequest request) throws IOException {
        String uploadFilePath = request.getUploadFilePath();
        if (request.getRecordDirectory() != null) {
            File recordFile = new File(request.getRecordDirectory() + "/" + BinaryUtil.calculateMd5Str((BinaryUtil.calculateMd5Str(uploadFilePath) + request.getBucketName() + request.getObjectKey() + String.valueOf(request.getPartSize())).getBytes()));
            if (recordFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(recordFile));
                String uploadId = br.readLine();
                br.close();
                OSSLog.logDebug("[initUploadId] - Found record file, uploadid: " + uploadId);
                this.apiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(request.getBucketName(), request.getObjectKey(), uploadId), null);
            }
            if (recordFile != null) {
                recordFile.delete();
            }
        }
    }

    public OSSAsyncTask<ResumableUploadResult> resumableUpload(ResumableUploadRequest request, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> completedCallback) {
        ExecutionContext<ResumableUploadRequest> executionContext = new ExecutionContext<>(this.apiOperation.getInnerClient(), request);
        return OSSAsyncTask.wrapRequestTask(executor.submit(new ResumableUploadTask(request, completedCallback, executionContext)), executionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ResumableUploadTask implements Callable<ResumableUploadResult> {
        private OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> completedCallback;
        private ExecutionContext context;
        private long currentUploadLength;
        private long fileLength;
        private List<PartETag> partETags = new ArrayList();
        private File recordFile;
        private ResumableUploadRequest request;
        private String uploadId;

        public ResumableUploadTask(ResumableUploadRequest request, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> completedCallback, ExecutionContext context) {
            this.request = request;
            this.completedCallback = completedCallback;
            this.context = context;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ResumableUploadResult call() throws Exception {
            try {
                initUploadId();
                ResumableUploadResult result = doMultipartUpload();
                if (this.completedCallback != null) {
                    this.completedCallback.onSuccess(this.request, result);
                }
                return result;
            } catch (ServiceException e) {
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, null, e);
                }
                throw e;
            } catch (Exception e2) {
                ClientException temp = new ClientException(e2.toString(), e2);
                if (this.completedCallback != null) {
                    this.completedCallback.onFailure(this.request, temp, null);
                }
                throw temp;
            }
        }

        private void initUploadId() throws IOException, ServiceException, ClientException {
            String uploadFilePath = this.request.getUploadFilePath();
            if (this.request.getRecordDirectory() != null) {
                this.recordFile = new File(this.request.getRecordDirectory() + "/" + BinaryUtil.calculateMd5Str((BinaryUtil.calculateMd5Str(uploadFilePath) + this.request.getBucketName() + this.request.getObjectKey() + String.valueOf(this.request.getPartSize())).getBytes()));
                if (this.recordFile.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(this.recordFile));
                    this.uploadId = br.readLine();
                    br.close();
                    OSSLog.logDebug("[initUploadId] - Found record file, uploadid: " + this.uploadId);
                    try {
                        for (PartSummary part : ExtensionRequestOperation.this.apiOperation.listParts(new ListPartsRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).getResult().getParts()) {
                            this.partETags.add(new PartETag(part.getPartNumber(), part.getETag()));
                        }
                        return;
                    } catch (ClientException e) {
                        throw e;
                    } catch (ServiceException e2) {
                        if (e2.getStatusCode() == 404) {
                            this.uploadId = null;
                        } else {
                            throw e2;
                        }
                    }
                }
                if (!this.recordFile.exists() && !this.recordFile.createNewFile()) {
                    throw new ClientException("Can't create file at path: " + this.recordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
                }
            }
            this.uploadId = ExtensionRequestOperation.this.apiOperation.initMultipartUpload(new InitiateMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.request.getMetadata()), null).getResult().getUploadId();
            if (this.recordFile != null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.recordFile));
                bw.write(this.uploadId);
                bw.close();
            }
        }

        private ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException {
            if (this.context.getCancellationHandler().isCancelled()) {
                if (this.request.deleteUploadOnCancelling().booleanValue()) {
                    abortThisResumableUpload();
                    if (this.recordFile != null) {
                        this.recordFile.delete();
                    }
                }
                throwOutInterruptClientException();
            }
            long blockSize = this.request.getPartSize();
            int currentUploadIndex = this.partETags.size() + 1;
            File uploadFile = new File(this.request.getUploadFilePath());
            this.fileLength = uploadFile.length();
            final OSSProgressCallback progressCallback = this.request.getProgressCallback();
            int totalBlockNum = ((int) (this.fileLength / blockSize)) + (this.fileLength % blockSize == 0 ? 0 : 1);
            if (currentUploadIndex <= totalBlockNum) {
                this.currentUploadLength = (currentUploadIndex - 1) * blockSize;
            } else {
                this.currentUploadLength = this.fileLength;
            }
            InputStream in = new FileInputStream(uploadFile);
            long at = 0;
            while (at < this.currentUploadLength) {
                long realSkip = in.skip(this.currentUploadLength - at);
                if (realSkip == -1) {
                    throw new IOException("Skip failed! [fileLength]: " + this.fileLength + " [needSkip]: " + this.currentUploadLength);
                }
                at += realSkip;
            }
            while (currentUploadIndex <= totalBlockNum) {
                UploadPartRequest uploadPart = new UploadPartRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, currentUploadIndex);
                uploadPart.setProgressCallback(new OSSProgressCallback<UploadPartRequest>() { // from class: com.alibaba.sdk.android.oss.internal.ExtensionRequestOperation.ResumableUploadTask.1
                    public void onProgress(UploadPartRequest request, long currentSize, long totalSize) {
                        if (progressCallback != null) {
                            progressCallback.onProgress(ResumableUploadTask.this.request, ResumableUploadTask.this.currentUploadLength + currentSize, ResumableUploadTask.this.fileLength);
                        }
                    }
                });
                int toUpload = (int) Math.min(blockSize, this.fileLength - this.currentUploadLength);
                byte[] partContent = IOUtils.readStreamAsBytesArray(in, toUpload);
                uploadPart.setPartContent(partContent);
                uploadPart.setMd5Digest(BinaryUtil.calculateBase64Md5(partContent));
                this.partETags.add(new PartETag(currentUploadIndex, ExtensionRequestOperation.this.apiOperation.uploadPart(uploadPart, null).getResult().getETag()));
                this.currentUploadLength += toUpload;
                currentUploadIndex++;
                if (this.context.getCancellationHandler().isCancelled()) {
                    if (this.request.deleteUploadOnCancelling().booleanValue()) {
                        abortThisResumableUpload();
                        if (this.recordFile != null) {
                            this.recordFile.delete();
                        }
                    }
                    throwOutInterruptClientException();
                }
            }
            CompleteMultipartUploadRequest complete = new CompleteMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId, this.partETags);
            complete.setMetadata(this.request.getMetadata());
            if (this.request.getCallbackParam() != null) {
                complete.setCallbackParam(this.request.getCallbackParam());
            }
            if (this.request.getCallbackVars() != null) {
                complete.setCallbackVars(this.request.getCallbackVars());
            }
            CompleteMultipartUploadResult completeResult = ExtensionRequestOperation.this.apiOperation.completeMultipartUpload(complete, null).getResult();
            if (this.recordFile != null) {
                this.recordFile.delete();
            }
            return new ResumableUploadResult(completeResult);
        }

        private void abortThisResumableUpload() {
            if (this.uploadId != null) {
                ExtensionRequestOperation.this.apiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(this.request.getBucketName(), this.request.getObjectKey(), this.uploadId), null).waitUntilFinished();
            }
        }

        private void throwOutInterruptClientException() throws ClientException {
            IOException e = new IOException();
            throw new ClientException(e.getMessage(), e);
        }
    }
}
