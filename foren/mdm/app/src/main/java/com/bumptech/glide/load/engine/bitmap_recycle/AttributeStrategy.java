package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import com.hyphenate.util.EMPrivateConstant;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AttributeStrategy implements LruPoolStrategy {
    private final KeyPool keyPool = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public void put(Bitmap bitmap) {
        this.groupedMap.put(this.keyPool.get(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap get(int width, int height, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(width, height, config));
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(width, height, config);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.groupedMap;
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapString(int width, int height, Bitmap.Config config) {
        return "[" + width + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + height + "], " + config;
    }

    /* loaded from: classes.dex */
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int width, int height, Bitmap.Config config) {
            Key result = get();
            result.init(width, height, config);
            return result;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Key create() {
            return new Key(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool pool) {
            this.pool = pool;
        }

        public void init(int width, int height, Bitmap.Config config) {
            this.width = width;
            this.height = height;
            this.config = config;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            return this.width == other.width && this.height == other.height && this.config == other.config;
        }

        public int hashCode() {
            return (((this.width * 31) + this.height) * 31) + (this.config != null ? this.config.hashCode() : 0);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.Poolable
        public void offer() {
            this.pool.offer(this);
        }
    }
}
