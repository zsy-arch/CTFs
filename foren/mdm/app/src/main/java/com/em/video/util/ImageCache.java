package com.em.video.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import java.io.File;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ImageCache {
    private static final int DEFAULT_COMPRESS_QUALITY = 70;
    private static final boolean DEFAULT_INIT_DISK_CACHE_ON_CREATE = false;
    private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
    private static final int DEFAULT_MEM_CACHE_SIZE = 5120;
    private static final String TAG = "ImageCache";
    private LruCache<String, BitmapDrawable> mMemoryCache;
    private Set<SoftReference<Bitmap>> mReusableBitmaps;

    private ImageCache(ImageCacheParams cacheParams) {
        init(cacheParams);
    }

    public static ImageCache getInstance(FragmentManager fragmentManager, ImageCacheParams cacheParams) {
        RetainFragment mRetainFragment = findOrCreateRetainFragment(fragmentManager);
        ImageCache imageCache = (ImageCache) mRetainFragment.getObject();
        if (imageCache != null) {
            return imageCache;
        }
        ImageCache imageCache2 = new ImageCache(cacheParams);
        mRetainFragment.setObject(imageCache2);
        return imageCache2;
    }

    private void init(ImageCacheParams cacheParams) {
        if (cacheParams.memoryCacheEnabled) {
            if (Utils.hasHoneycomb()) {
                this.mReusableBitmaps = Collections.synchronizedSet(new HashSet());
            }
            this.mMemoryCache = new LruCache<String, BitmapDrawable>(cacheParams.memCacheSize) { // from class: com.em.video.util.ImageCache.1
                public void entryRemoved(boolean evicted, String key, BitmapDrawable oldValue, BitmapDrawable newValue) {
                    if (RecyclingBitmapDrawable.class.isInstance(oldValue)) {
                        ((RecyclingBitmapDrawable) oldValue).setIsCached(false);
                    } else if (Utils.hasHoneycomb()) {
                        ImageCache.this.mReusableBitmaps.add(new SoftReference(oldValue.getBitmap()));
                    }
                }

                public int sizeOf(String key, BitmapDrawable value) {
                    int bitmapSize = ImageCache.getBitmapSize(value) / 1024;
                    if (bitmapSize == 0) {
                        return 1;
                    }
                    return bitmapSize;
                }
            };
        }
    }

    public void addBitmapToCache(String data, BitmapDrawable value) {
        if (data != null && value != null && this.mMemoryCache != null) {
            if (RecyclingBitmapDrawable.class.isInstance(value)) {
                ((RecyclingBitmapDrawable) value).setIsCached(true);
            }
            this.mMemoryCache.put(data, value);
        }
    }

    public BitmapDrawable getBitmapFromMemCache(String data) {
        if (this.mMemoryCache != null) {
            return this.mMemoryCache.get(data);
        }
        return null;
    }

    public Bitmap getBitmapFromReusableSet(BitmapFactory.Options options) {
        Bitmap bitmap = null;
        if (this.mReusableBitmaps != null && !this.mReusableBitmaps.isEmpty()) {
            synchronized (this.mReusableBitmaps) {
                Iterator<SoftReference<Bitmap>> iterator = this.mReusableBitmaps.iterator();
                while (true) {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    Bitmap item = iterator.next().get();
                    if (item == null || !item.isMutable()) {
                        iterator.remove();
                    } else if (canUseForInBitmap(item, options)) {
                        bitmap = item;
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        return bitmap;
    }

    public void clearCache() {
        if (this.mMemoryCache != null) {
            this.mMemoryCache.evictAll();
        }
    }

    /* loaded from: classes.dex */
    public static class ImageCacheParams {
        public int memCacheSize = ImageCache.DEFAULT_MEM_CACHE_SIZE;
        public int compressQuality = 70;
        public boolean memoryCacheEnabled = true;
        public boolean initDiskCacheOnCreate = false;

        public void setMemCacheSizePercent(float percent) {
            if (percent < 0.01f || percent > 0.8f) {
                throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.01 and 0.8 (inclusive)");
            }
            this.memCacheSize = Math.round((((float) Runtime.getRuntime().maxMemory()) * percent) / 1024.0f);
        }
    }

    @TargetApi(19)
    private static boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        return !Utils.hasKitKat() ? candidate.getWidth() == targetOptions.outWidth && candidate.getHeight() == targetOptions.outHeight && targetOptions.inSampleSize == 1 : ((targetOptions.outWidth / targetOptions.inSampleSize) * (targetOptions.outHeight / targetOptions.inSampleSize)) * getBytesPerPixel(candidate.getConfig()) <= candidate.getByteCount();
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        if (config == Bitmap.Config.RGB_565 || config == Bitmap.Config.ARGB_4444) {
            return 2;
        }
        return config == Bitmap.Config.ALPHA_8 ? 1 : 1;
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if ("mounted".equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()) {
            cachePath = getExternalCacheDir(context).getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public static String hashKeyForDisk(String key) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            return bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(key.hashCode());
        }
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 255);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    @TargetApi(19)
    public static int getBitmapSize(BitmapDrawable value) {
        Bitmap bitmap = value.getBitmap();
        return Utils.hasHoneycombMR1() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (Utils.hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if (Utils.hasFroyo()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    private static RetainFragment findOrCreateRetainFragment(FragmentManager fm) {
        RetainFragment mRetainFragment = (RetainFragment) fm.findFragmentByTag(TAG);
        if (mRetainFragment != null) {
            return mRetainFragment;
        }
        RetainFragment mRetainFragment2 = new RetainFragment();
        fm.beginTransaction().add(mRetainFragment2, TAG).commitAllowingStateLoss();
        return mRetainFragment2;
    }

    /* loaded from: classes.dex */
    public static class RetainFragment extends Fragment {
        private Object mObject;

        @Override // android.support.v4.app.Fragment
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public void setObject(Object object) {
            this.mObject = object;
        }

        public Object getObject() {
            return this.mObject;
        }
    }
}
