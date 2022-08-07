package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

/* loaded from: classes.dex */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
        return new BytesResource(toTranscode.get().getData());
    }

    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    public String getId() {
        return "GifDrawableBytesTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
