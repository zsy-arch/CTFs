package com.easeui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import com.easeui.model.EaseImageCache;
import com.easeui.widget.photoview.EasePhotoView;
import com.hyphenate.util.ImageUtils;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseLoadLocalBigImgTask extends AsyncTask<Void, Void, Bitmap> {
    private Context context;
    private int height;
    private String path;
    private ProgressBar pb;
    private EasePhotoView photoView;
    private int width;

    public EaseLoadLocalBigImgTask(Context context, String path, EasePhotoView photoView, ProgressBar pb, int width, int height) {
        this.context = context;
        this.path = path;
        this.photoView = photoView;
        this.pb = pb;
        this.width = width;
        this.height = height;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (ImageUtils.readPictureDegree(this.path) != 0) {
            this.pb.setVisibility(0);
            this.photoView.setVisibility(4);
            return;
        }
        this.pb.setVisibility(4);
        this.photoView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bitmap doInBackground(Void... params) {
        return ImageUtils.decodeScaleImage(this.path, this.width, this.height);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPostExecute(Bitmap result) {
        super.onPostExecute((EaseLoadLocalBigImgTask) result);
        this.pb.setVisibility(4);
        this.photoView.setVisibility(0);
        if (result != null) {
            EaseImageCache.getInstance().put(this.path, result);
        } else {
            result = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ease_default_image);
        }
        this.photoView.setImageBitmap(result);
    }
}
