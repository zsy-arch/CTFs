package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

/* loaded from: classes.dex */
public class CenterCrop extends BitmapTransformation {
    public CenterCrop(Context context) {
        super(context);
    }

    public CenterCrop(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap toReuse = pool.get(outWidth, outHeight, toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888);
        Bitmap transformed = TransformationUtils.centerCrop(toReuse, toTransform, outWidth, outHeight);
        if (!(toReuse == null || toReuse == transformed || pool.put(toReuse))) {
            toReuse.recycle();
        }
        return transformed;
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }
}
