package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class NullResourceEncoder<T> implements ResourceEncoder<T> {
    private static final NullResourceEncoder<?> NULL_ENCODER = new NullResourceEncoder<>();

    @Override // com.bumptech.glide.load.Encoder
    public /* bridge */ /* synthetic */ boolean encode(Object x0, OutputStream x1) {
        return encode((Resource) ((Resource) x0), x1);
    }

    public static <T> NullResourceEncoder<T> get() {
        return (NullResourceEncoder<T>) NULL_ENCODER;
    }

    public boolean encode(Resource<T> data, OutputStream os) {
        return false;
    }

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }
}
