package com.vsf2f.f2f.ui.view.richEditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes2.dex */
public class DataImageView extends ImageView {
    private String absolutePath;
    private Bitmap bitmap;

    public DataImageView(Context context) {
        this(context, null);
    }

    public DataImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
