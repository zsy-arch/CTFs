package com.bumptech.glide.load.model;

import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* loaded from: classes.dex */
public class ModelCache<A, B> {
    private static final int DEFAULT_SIZE = 250;
    private final LruCache<ModelKey<A>, B> cache;

    public ModelCache() {
        this(250);
    }

    public ModelCache(int size) {
        this.cache = new LruCache<ModelKey<A>, B>(size) { // from class: com.bumptech.glide.load.model.ModelCache.1
            @Override // com.bumptech.glide.util.LruCache
            protected /* bridge */ /* synthetic */ void onItemEvicted(Object x0, Object x1) {
                onItemEvicted((ModelKey) ((ModelKey) x0), (ModelKey<A>) x1);
            }

            protected void onItemEvicted(ModelKey<A> key, B item) {
                key.release();
            }
        };
    }

    public B get(A model, int width, int height) {
        ModelKey<A> key = ModelKey.get(model, width, height);
        B result = this.cache.get(key);
        key.release();
        return result;
    }

    public void put(A model, int width, int height, B value) {
        this.cache.put(ModelKey.get(model, width, height), value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ModelKey<A> {
        private static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        private int height;
        private A model;
        private int width;

        static <A> ModelKey<A> get(A model, int width, int height) {
            ModelKey<A> modelKey = (ModelKey<A>) KEY_QUEUE.poll();
            if (modelKey == null) {
                modelKey = new ModelKey<>();
            }
            modelKey.init(model, width, height);
            return modelKey;
        }

        private ModelKey() {
        }

        private void init(A model, int width, int height) {
            this.model = model;
            this.width = width;
            this.height = height;
        }

        public void release() {
            KEY_QUEUE.offer(this);
        }

        public boolean equals(Object o) {
            if (!(o instanceof ModelKey)) {
                return false;
            }
            ModelKey other = (ModelKey) o;
            return this.width == other.width && this.height == other.height && this.model.equals(other.model);
        }

        public int hashCode() {
            return (((this.height * 31) + this.width) * 31) + this.model.hashCode();
        }
    }
}
