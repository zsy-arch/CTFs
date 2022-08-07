package com.em.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.EMLog;

/* loaded from: classes.dex */
public class DownloadImageTask extends AsyncTask<EMMessage, Integer, Bitmap> {
    private DownloadFileCallback callback;
    EMMessage message;
    Bitmap bitmap = null;
    public boolean downloadThumbnail = false;

    /* loaded from: classes.dex */
    public interface DownloadFileCallback {
        void afterDownload(Bitmap bitmap);

        void beforeDownload();

        void downloadProgress(int i);
    }

    public DownloadImageTask(String remoteDir, DownloadFileCallback callback) {
        this.callback = callback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bitmap doInBackground(EMMessage... params) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPostExecute(Bitmap result) {
        this.callback.afterDownload(result);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.callback.beforeDownload();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onProgressUpdate(Integer... values) {
        this.callback.downloadProgress(values[0].intValue());
    }

    public static String getThumbnailImagePath(String imagePath) {
        String path = imagePath.substring(0, imagePath.lastIndexOf("/") + 1) + "th" + imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
        EMLog.d("msg", "original image path:" + imagePath);
        EMLog.d("msg", "thum image path:" + path);
        return path;
    }
}
