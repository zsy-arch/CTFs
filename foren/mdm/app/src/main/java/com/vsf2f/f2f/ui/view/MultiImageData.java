package com.vsf2f.f2f.ui.view;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class MultiImageData {
    private static final int maxSize = 9;
    private Map<Integer, Bitmap> bitmapMap;
    private int defaultImageResId;
    private List<String> imageUrls;
    private String savePath;

    public MultiImageData() {
    }

    public MultiImageData(int defaultImageResId) {
        this.defaultImageResId = defaultImageResId;
    }

    public MultiImageData(List<String> imageUrls, int defaultImageResId) {
        this.imageUrls = imageUrls;
        this.defaultImageResId = defaultImageResId;
    }

    public void setDefaultImageResId(int defaultImageResId) {
        this.defaultImageResId = defaultImageResId;
    }

    public int getDefaultImageResId() {
        return this.defaultImageResId;
    }

    public List<String> getImageUrls() {
        return this.imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void putBitmap(Bitmap bitmap, int position) {
        if (this.bitmapMap != null) {
            synchronized (this.bitmapMap) {
                this.bitmapMap.put(Integer.valueOf(position), bitmap);
            }
            return;
        }
        this.bitmapMap = new HashMap();
        synchronized (this.bitmapMap) {
            this.bitmapMap.put(Integer.valueOf(position), bitmap);
        }
    }

    public Bitmap getBitmap(int position) {
        Bitmap bitmap;
        if (this.bitmapMap == null) {
            return null;
        }
        synchronized (this.bitmapMap) {
            bitmap = this.bitmapMap.get(Integer.valueOf(position));
        }
        return bitmap;
    }

    public int size() {
        if (this.imageUrls == null) {
            return 0;
        }
        if (this.imageUrls.size() > 9) {
            return 9;
        }
        return this.imageUrls.size();
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
