package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import com.hyphenate.util.EMPrivateConstant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private static final String TAG = "LruBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private int currentSize;
    private int evictions;
    private int hits;
    private final int initialMaxSize;
    private int maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    LruBitmapPool(int maxSize, LruPoolStrategy strategy, Set<Bitmap.Config> allowedConfigs) {
        this.initialMaxSize = maxSize;
        this.maxSize = maxSize;
        this.strategy = strategy;
        this.allowedConfigs = allowedConfigs;
        this.tracker = new NullBitmapTracker();
    }

    public LruBitmapPool(int maxSize) {
        this(maxSize, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(int maxSize, Set<Bitmap.Config> allowedConfigs) {
        this(maxSize, getDefaultStrategy(), allowedConfigs);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void setSizeMultiplier(float sizeMultiplier) {
        this.maxSize = Math.round(this.initialMaxSize * sizeMultiplier);
        evict();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized boolean put(Bitmap bitmap) {
        boolean z;
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (!bitmap.isMutable() || this.strategy.getSize(bitmap) > this.maxSize || !this.allowedConfigs.contains(bitmap.getConfig())) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(bitmap.getConfig()));
            }
            z = false;
        } else {
            int size = this.strategy.getSize(bitmap);
            this.strategy.put(bitmap);
            this.tracker.add(bitmap);
            this.puts++;
            this.currentSize += size;
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Put bitmap in pool=" + this.strategy.logBitmap(bitmap));
            }
            dump();
            evict();
            z = true;
        }
        return z;
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized Bitmap get(int width, int height, Bitmap.Config config) {
        Bitmap result;
        result = getDirty(width, height, config);
        if (result != null) {
            result.eraseColor(0);
        }
        return result;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @TargetApi(12)
    public synchronized Bitmap getDirty(int width, int height, Bitmap.Config config) {
        Bitmap result;
        result = this.strategy.get(width, height, config != null ? config : DEFAULT_CONFIG);
        if (result == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Missing bitmap=" + this.strategy.logBitmap(width, height, config));
            }
            this.misses++;
        } else {
            this.hits++;
            this.currentSize -= this.strategy.getSize(result);
            this.tracker.remove(result);
            if (Build.VERSION.SDK_INT >= 12) {
                result.setHasAlpha(true);
            }
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Get bitmap=" + this.strategy.logBitmap(width, height, config));
        }
        dump();
        return result;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "clearMemory");
        }
        trimToSize(0);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int level) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "trimMemory, level=" + level);
        }
        if (level >= 60) {
            clearMemory();
        } else if (level >= 40) {
            trimToSize(this.maxSize / 2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        r4.currentSize = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (android.util.Log.isLoggable(com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.TAG, 5) == false) goto L_0x0020;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        android.util.Log.w(com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.TAG, "Size mismatch, resetting");
        dumpUnchecked();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void trimToSize(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
        L_0x0001:
            int r1 = r4.currentSize     // Catch: all -> 0x0069
            if (r1 <= r5) goto L_0x0023
            com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy r1 = r4.strategy     // Catch: all -> 0x0069
            android.graphics.Bitmap r0 = r1.removeLast()     // Catch: all -> 0x0069
            if (r0 != 0) goto L_0x0025
            java.lang.String r1 = "LruBitmapPool"
            r2 = 5
            boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch: all -> 0x0069
            if (r1 == 0) goto L_0x0020
            java.lang.String r1 = "LruBitmapPool"
            java.lang.String r2 = "Size mismatch, resetting"
            android.util.Log.w(r1, r2)     // Catch: all -> 0x0069
            r4.dumpUnchecked()     // Catch: all -> 0x0069
        L_0x0020:
            r1 = 0
            r4.currentSize = r1     // Catch: all -> 0x0069
        L_0x0023:
            monitor-exit(r4)
            return
        L_0x0025:
            com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool$BitmapTracker r1 = r4.tracker     // Catch: all -> 0x0069
            r1.remove(r0)     // Catch: all -> 0x0069
            int r1 = r4.currentSize     // Catch: all -> 0x0069
            com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy r2 = r4.strategy     // Catch: all -> 0x0069
            int r2 = r2.getSize(r0)     // Catch: all -> 0x0069
            int r1 = r1 - r2
            r4.currentSize = r1     // Catch: all -> 0x0069
            r0.recycle()     // Catch: all -> 0x0069
            int r1 = r4.evictions     // Catch: all -> 0x0069
            int r1 = r1 + 1
            r4.evictions = r1     // Catch: all -> 0x0069
            java.lang.String r1 = "LruBitmapPool"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch: all -> 0x0069
            if (r1 == 0) goto L_0x0065
            java.lang.String r1 = "LruBitmapPool"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x0069
            r2.<init>()     // Catch: all -> 0x0069
            java.lang.String r3 = "Evicting bitmap="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: all -> 0x0069
            com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy r3 = r4.strategy     // Catch: all -> 0x0069
            java.lang.String r3 = r3.logBitmap(r0)     // Catch: all -> 0x0069
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: all -> 0x0069
            java.lang.String r2 = r2.toString()     // Catch: all -> 0x0069
            android.util.Log.d(r1, r2)     // Catch: all -> 0x0069
        L_0x0065:
            r4.dump()     // Catch: all -> 0x0069
            goto L_0x0001
        L_0x0069:
            r1 = move-exception
            monitor-exit(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.trimToSize(int):void");
    }

    private void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        Log.v(TAG, "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
    }

    private static LruPoolStrategy getDefaultStrategy() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new AttributeStrategy();
    }

    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        Set<Bitmap.Config> configs = new HashSet<>();
        configs.addAll(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            configs.add(null);
        }
        return Collections.unmodifiableSet(configs);
    }

    /* loaded from: classes.dex */
    private static class ThrowingBitmapTracker implements BitmapTracker {
        private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());

        private ThrowingBitmapTracker() {
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
            if (this.bitmaps.contains(bitmap)) {
                throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + bitmap.getHeight() + "]");
            }
            this.bitmaps.add(bitmap);
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                throw new IllegalStateException("Cannot remove bitmap not in tracker");
            }
            this.bitmaps.remove(bitmap);
        }
    }

    /* loaded from: classes.dex */
    private static class NullBitmapTracker implements BitmapTracker {
        private NullBitmapTracker() {
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
        }
    }
}
