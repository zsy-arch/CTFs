package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

/* loaded from: classes.dex */
public class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> TRANSFORMATION = new UnitTransformation();

    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) TRANSFORMATION;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<T> transform(Resource<T> resource, int outWidth, int outHeight) {
        return resource;
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "";
    }
}
