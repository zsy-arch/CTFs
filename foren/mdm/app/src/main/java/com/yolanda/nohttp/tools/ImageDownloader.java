package com.yolanda.nohttp.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.yolanda.nohttp.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class ImageDownloader {
    private static ImageDownloader instance;
    private String mCachePath;
    private Poster mPoster = new Poster();
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    /* loaded from: classes2.dex */
    public interface OnImageDownListener {
        void onDownFinish(String str, String str2, boolean z, Object obj);
    }

    private ImageDownloader(Context context) {
        setCachePath(context.getCacheDir().getAbsolutePath());
    }

    public static ImageDownloader getInstance(Context context) {
        if (instance == null) {
            instance = new ImageDownloader(context);
        }
        return instance;
    }

    public void setCachePath(String cachePath) {
        if (TextUtils.isEmpty(cachePath)) {
            throw new NullPointerException("cachePath cann't null");
        }
        this.mCachePath = cachePath;
        File file = new File(cachePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void downloadImage(String imageUrl, OnImageDownListener downListener, boolean deleteOld, Object tag) {
        downloadImage(imageUrl, downListener, deleteOld, tag, 3000);
    }

    public void downloadImage(String imageUrl, OnImageDownListener downListener, boolean deleteOld, Object tag, int timeOut) {
        StringBuffer buffer = new StringBuffer(this.mCachePath);
        buffer.append(File.separator);
        buffer.append(getMa5ForString(imageUrl));
        buffer.append(".png");
        downloadImage(imageUrl, downListener, buffer.toString(), Boolean.valueOf(deleteOld), tag, timeOut);
    }

    public void downloadImage(String imageUrl, OnImageDownListener downListener, String path, boolean deleteOld, Object tag) {
        downloadImage(imageUrl, downListener, path, Boolean.valueOf(deleteOld), tag, 3000);
    }

    public void downloadImage(String imageUrl, OnImageDownListener downListener, String path, Boolean deleteOld, Object tag, int timeOut) {
        Logger.d("ImageDownload url: " + imageUrl);
        Logger.d("ImageDownload path: " + path);
        File file = new File(path);
        if (file.exists() && deleteOld.booleanValue()) {
            file.delete();
        }
        if (file.exists()) {
            ImageHolder holder = new ImageHolder();
            holder.imageUrl = imageUrl;
            holder.isSucceed = true;
            holder.imagePath = path;
            holder.downListener = downListener;
            holder.tag = tag;
            this.mPoster.obtainMessage(0, holder).sendToTarget();
            return;
        }
        this.mExecutorService.execute(new DownImageThread(imageUrl, path, downListener, tag, timeOut));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DownImageThread implements Runnable {
        private OnImageDownListener mDownListener;
        private String mImagePath;
        private String mImageUrl;
        private Object tag;
        private int timeOut;

        public DownImageThread(String imageUrl, String imagePath, OnImageDownListener downListener, Object tag, int timeOut) {
            this.mImageUrl = imageUrl;
            this.mImagePath = imagePath;
            this.mDownListener = downListener;
            this.tag = tag;
            this.timeOut = timeOut;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageHolder holder = new ImageHolder();
            holder.imageUrl = this.mImageUrl;
            holder.downListener = this.mDownListener;
            holder.imagePath = this.mImagePath;
            holder.tag = this.tag;
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(this.mImageUrl).openConnection();
                urlConnection.setConnectTimeout(this.timeOut);
                urlConnection.setReadTimeout(this.timeOut);
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if (200 == responseCode) {
                    OutputStream outputStream = new FileOutputStream(new File(this.mImagePath), false);
                    InputStream inputStream = urlConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int len = inputStream.read(buffer);
                        if (len == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, len);
                    }
                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                    holder.isSucceed = true;
                    Logger.d(this.mImageUrl + " download finished; path: " + this.mImagePath + ".");
                } else {
                    Logger.d(this.mImageUrl + " responseCode: " + responseCode + ".");
                }
            } catch (Exception e) {
                Logger.w(e);
            }
            ImageDownloader.this.mPoster.obtainMessage(0, holder).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Poster extends Handler {
        Poster() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            ((ImageHolder) msg.obj).post();
        }
    }

    private String getMa5ForString(String content) {
        StringBuffer md5str = new StringBuffer();
        try {
            byte[] tempBytes = MessageDigest.getInstance("MD5").digest(content.getBytes());
            for (byte b : tempBytes) {
                int digital = b;
                if (b < 0) {
                    digital = b + 256;
                }
                if (digital < 16) {
                    md5str.append("0");
                }
                md5str.append(Integer.toHexString(digital == 1 ? 1 : 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ImageHolder {
        OnImageDownListener downListener;
        String imagePath;
        String imageUrl;
        boolean isSucceed;
        Object tag;

        private ImageHolder() {
        }

        void post() {
            if (this.downListener != null) {
                this.downListener.onDownFinish(this.imageUrl, this.imagePath, this.isSucceed, this.tag);
            }
        }
    }
}
