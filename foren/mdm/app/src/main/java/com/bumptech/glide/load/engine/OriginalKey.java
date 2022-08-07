package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* loaded from: classes.dex */
class OriginalKey implements Key {
    private final String id;
    private final Key signature;

    public OriginalKey(String id, Key signature) {
        this.id = id;
        this.signature = signature;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OriginalKey that = (OriginalKey) o;
        return this.id.equals(that.id) && this.signature.equals(that.signature);
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return (this.id.hashCode() * 31) + this.signature.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.id.getBytes("UTF-8"));
        this.signature.updateDiskCacheKey(messageDigest);
    }
}
