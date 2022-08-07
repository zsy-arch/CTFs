package com.yolanda.nohttp.download;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.yolanda.nohttp.BasicConnection;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.error.ArgumentError;
import com.yolanda.nohttp.error.ClientError;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.ServerError;
import com.yolanda.nohttp.error.StorageReadWriteError;
import com.yolanda.nohttp.error.StorageSpaceNotEnoughError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.tools.FileUtil;
import com.yolanda.nohttp.tools.HeaderParser;
import com.yolanda.nohttp.tools.NetUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;

/* loaded from: classes2.dex */
public class DownloadConnection extends BasicConnection implements Downloader {
    @Override // com.yolanda.nohttp.download.Downloader
    public void download(int what, DownloadRequest downloadRequest, DownloadListener downloadListener) {
        Throwable th;
        Exception e;
        IOException e2;
        SocketException e3;
        SocketTimeoutException e4;
        UnknownHostException e5;
        MalformedURLException e6;
        InputStream inputStream;
        RandomAccessFile randomAccessFile;
        HttpURLConnection httpConnection = null;
        InputStream inputStream2 = null;
        if (downloadRequest == null) {
            throw new IllegalArgumentException("downloadRequest == null.");
        } else if (downloadListener == null) {
            throw new IllegalArgumentException("downloadListener == null.");
        } else {
            try {
                RandomAccessFile randomAccessFile2 = null;
                String savePathDir = downloadRequest.getFileDir();
                try {
                    try {
                        try {
                            if (TextUtils.isEmpty(savePathDir) || TextUtils.isEmpty(downloadRequest.getFileName())) {
                                throw new ArgumentError("Destination folder creation failed, please check folder parameters and storage devices.");
                            } else if (!NetUtil.isNetworkAvailable(NoHttp.getContext())) {
                                throw new NetworkError("Network is not available.");
                            } else if (!FileUtil.createFolder(savePathDir)) {
                                throw new StorageReadWriteError("Failed to create the folder " + savePathDir + ", please check storage devices.");
                            } else {
                                File tempFile = new File(savePathDir, downloadRequest.getFileName() + ".nohttp");
                                long rangeSize = 0;
                                if (tempFile.exists()) {
                                    if (downloadRequest.isRange()) {
                                        rangeSize = tempFile.length();
                                        downloadRequest.setHeader(HttpHeaders.RANGE, "bytes=" + rangeSize + "-");
                                    } else {
                                        tempFile.delete();
                                    }
                                }
                                httpConnection = getHttpConnection(downloadRequest);
                                Logger.i("----------Response Start----------");
                                int responseCode = httpConnection.getResponseCode();
                                Headers httpHeaders = parseResponseHeaders(new URI(downloadRequest.url()), responseCode, httpConnection.getResponseMessage(), httpConnection.getHeaderFields());
                                long contentLength = 0;
                                if (responseCode == 206) {
                                    String range = httpHeaders.getValue(Headers.HEAD_KEY_CONTENT_RANGE, 0);
                                    try {
                                        contentLength = Long.parseLong(range.substring(range.indexOf(47) + 1));
                                    } catch (Exception e7) {
                                        throw new ServerError("ResponseCode is 206, but content-Range error in Server HTTP header information: " + range + ".");
                                    }
                                } else if (responseCode == 200) {
                                    contentLength = httpHeaders.getContentLength();
                                    rangeSize = 0;
                                }
                                File lastFile = new File(savePathDir, downloadRequest.getFileName());
                                if (lastFile.exists()) {
                                    if (downloadRequest.isDeleteOld()) {
                                        lastFile.delete();
                                    } else if ((responseCode == 200 || responseCode == 206) && (lastFile.length() == contentLength || contentLength == 0)) {
                                        downloadListener.onStart(what, true, lastFile.length(), new com.yolanda.nohttp.HttpHeaders(), lastFile.length());
                                        downloadListener.onProgress(what, 100, lastFile.length());
                                        Logger.d("-------Download finish-------");
                                        downloadListener.onFinish(what, lastFile.getAbsolutePath());
                                        Logger.i("----------Response End----------");
                                        if (0 != 0) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e8) {
                                            }
                                        }
                                        if (0 != 0) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e9) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                            return;
                                        }
                                        return;
                                    } else {
                                        lastFile.delete();
                                    }
                                }
                                if (responseCode == 200 && !FileUtil.createNewFile(tempFile)) {
                                    throw new StorageReadWriteError("Failed to create the file, please check storage devices.");
                                } else if (FileUtil.getDirSize(savePathDir) < contentLength) {
                                    throw new StorageSpaceNotEnoughError("The folder is not enough space to save the downloaded file: " + savePathDir + ".");
                                } else if (downloadRequest.isCanceled()) {
                                    Log.i("NoHttpDownloader", "Download request is canceled.");
                                    downloadListener.onCancel(what);
                                    Logger.i("----------Response End----------");
                                    if (0 != 0) {
                                        try {
                                            randomAccessFile2.close();
                                        } catch (IOException e10) {
                                        }
                                    }
                                    if (0 != 0) {
                                        try {
                                            inputStream2.close();
                                        } catch (IOException e11) {
                                        }
                                    }
                                    if (httpConnection != null) {
                                        httpConnection.disconnect();
                                    }
                                } else {
                                    try {
                                        inputStream = httpConnection.getInputStream();
                                    } catch (IOException e12) {
                                        if (responseCode >= 500) {
                                            throw new ServerError(e12.getMessage());
                                        } else if (responseCode <= 400) {
                                            throw new ClientError(e12.getMessage());
                                        } else {
                                            inputStream = null;
                                        }
                                    }
                                    try {
                                        if (HeaderParser.isGzipContent(httpHeaders.getContentEncoding())) {
                                            inputStream2 = new GZIPInputStream(inputStream);
                                        } else {
                                            inputStream2 = inputStream;
                                        }
                                        Logger.d("-------Download start-------");
                                        downloadListener.onStart(what, rangeSize > 0, rangeSize, httpHeaders, contentLength);
                                        randomAccessFile = new RandomAccessFile(tempFile, "rw");
                                    } catch (MalformedURLException e13) {
                                        e6 = e13;
                                        inputStream2 = inputStream;
                                    } catch (SocketException e14) {
                                        e3 = e14;
                                        inputStream2 = inputStream;
                                    } catch (SocketTimeoutException e15) {
                                        e4 = e15;
                                        inputStream2 = inputStream;
                                    } catch (UnknownHostException e16) {
                                        e5 = e16;
                                        inputStream2 = inputStream;
                                    } catch (IOException e17) {
                                        e2 = e17;
                                        inputStream2 = inputStream;
                                    } catch (Exception e18) {
                                        e = e18;
                                        inputStream2 = inputStream;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream2 = inputStream;
                                    }
                                    try {
                                        randomAccessFile.seek(rangeSize);
                                        byte[] buffer = new byte[1024];
                                        int oldProgress = 0;
                                        long count = rangeSize;
                                        while (true) {
                                            int len = inputStream2.read(buffer);
                                            if (len == -1) {
                                                break;
                                            } else if (downloadRequest.isCanceled()) {
                                                Log.i("NoHttpDownloader", "Download request is canceled.");
                                                downloadListener.onCancel(what);
                                                break;
                                            } else {
                                                randomAccessFile.write(buffer, 0, len);
                                                count += len;
                                                if (contentLength != 0) {
                                                    int progress = (int) ((100 * count) / contentLength);
                                                    if (progress % 2 == 0 || progress % 3 == 0 || progress % 5 == 0 || progress % 7 == 0) {
                                                        if (oldProgress != progress) {
                                                            oldProgress = progress;
                                                            downloadListener.onProgress(what, oldProgress, count);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!downloadRequest.isCanceled() && (tempFile.length() == contentLength || contentLength == 0)) {
                                            tempFile.renameTo(lastFile);
                                            Logger.d("-------Download finish-------");
                                            downloadListener.onFinish(what, lastFile.getAbsolutePath());
                                        }
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile != null) {
                                            try {
                                                randomAccessFile.close();
                                            } catch (IOException e19) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e20) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (MalformedURLException e21) {
                                        e6 = e21;
                                        randomAccessFile2 = randomAccessFile;
                                        Logger.e(e6);
                                        downloadListener.onDownloadError(what, new URLError(e6.getMessage()));
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e22) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e23) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (SocketException e24) {
                                        e3 = e24;
                                        randomAccessFile2 = randomAccessFile;
                                        if (NetUtil.isNetworkAvailable(NoHttp.getContext())) {
                                            downloadListener.onDownloadError(what, e3);
                                        } else {
                                            Logger.e(e3, "The network is not available.");
                                            downloadListener.onDownloadError(what, new NetworkError("The network is not available."));
                                        }
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e25) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e26) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (SocketTimeoutException e27) {
                                        e4 = e27;
                                        randomAccessFile2 = randomAccessFile;
                                        Logger.e(e4);
                                        downloadListener.onDownloadError(what, new TimeoutError(e4.getMessage()));
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e28) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e29) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (UnknownHostException e30) {
                                        e5 = e30;
                                        randomAccessFile2 = randomAccessFile;
                                        Logger.e(e5);
                                        downloadListener.onDownloadError(what, new UnKnownHostError(e5.getMessage()));
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e31) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e32) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (IOException e33) {
                                        e2 = e33;
                                        randomAccessFile2 = randomAccessFile;
                                        if (!FileUtil.canWrite(savePathDir)) {
                                            downloadListener.onDownloadError(what, new StorageReadWriteError("This folder cannot be written to the file: " + savePathDir + "."));
                                        } else if (FileUtil.getDirSize(savePathDir) < 1024) {
                                            downloadListener.onDownloadError(what, new StorageSpaceNotEnoughError("The folder is not enough space to save the downloaded file: " + savePathDir + "."));
                                        } else {
                                            downloadListener.onDownloadError(what, e2);
                                        }
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e34) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e35) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (Exception e36) {
                                        e = e36;
                                        randomAccessFile2 = randomAccessFile;
                                        Logger.e(e);
                                        downloadListener.onDownloadError(what, e);
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e37) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e38) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        randomAccessFile2 = randomAccessFile;
                                        Logger.i("----------Response End----------");
                                        if (randomAccessFile2 != null) {
                                            try {
                                                randomAccessFile2.close();
                                            } catch (IOException e39) {
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e40) {
                                            }
                                        }
                                        if (httpConnection != null) {
                                            httpConnection.disconnect();
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Exception e41) {
                            e = e41;
                        }
                    } catch (IOException e42) {
                        e2 = e42;
                    }
                } catch (MalformedURLException e43) {
                    e6 = e43;
                } catch (SocketException e44) {
                    e3 = e44;
                } catch (SocketTimeoutException e45) {
                    e4 = e45;
                } catch (UnknownHostException e46) {
                    e5 = e46;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }
}
