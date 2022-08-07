package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;

/* loaded from: classes.dex */
public class GifBitmapWrapperTransformation implements Transformation<GifBitmapWrapper> {
    private final Transformation<Bitmap> bitmapTransformation;
    private final Transformation<GifDrawable> gifDataTransformation;

    public GifBitmapWrapperTransformation(BitmapPool bitmapPool, Transformation<Bitmap> bitmapTransformation) {
        this(bitmapTransformation, new GifDrawableTransformation(bitmapTransformation, bitmapPool));
    }

    GifBitmapWrapperTransformation(Transformation<Bitmap> bitmapTransformation, Transformation<GifDrawable> gifDataTransformation) {
        this.bitmapTransformation = bitmapTransformation;
        this.gifDataTransformation = gifDataTransformation;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<GifBitmapWrapper> transform(Resource<GifBitmapWrapper> resource, int outWidth, int outHeight) {
        Resource<Bitmap> bitmapResource = resource.get().getBitmapResource();
        Resource<GifDrawable> gifResource = resource.get().getGifResource();
        if (bitmapResource != null && this.bitmapTransformation != null) {
            Resource<Bitmap> transformed = this.bitmapTransformation.transform(bitmapResource, outWidth, outHeight);
            if (!bitmapResource.equals(transformed)) {
                return new GifBitmapWrapperResource(new GifBitmapWrapper(transformed, resource.get().getGifResource()));
            }
            return resource;
        } else if (gifResource == null || this.gifDataTransformation == null) {
            return resource;
        } else {
            Resource<GifDrawable> transformed2 = this.gifDataTransformation.transform(gifResource, outWidth, outHeight);
            if (!gifResource.equals(transformed2)) {
                return new GifBitmapWrapperResource(new GifBitmapWrapper(resource.get().getBitmapResource(), transformed2));
            }
            return resource;
        }
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return this.bitmapTransformation.getId();
    }
}
