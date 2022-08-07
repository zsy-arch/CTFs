package com.tencent.smtt.export.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.tencent.smtt.sdk.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class libwebp {
    public static final int BITMAP_ALPHA_8 = 1;
    public static final int BITMAP_ARGB_4444 = 3;
    public static final int BITMAP_ARGB_8888 = 4;
    public static final int BITMAP_RGB_565 = 2;
    public static final String LOGTAG = "[image]";
    public static boolean isMultiCore;
    public static libwebp mInstance;
    public static boolean mIsLoadLibSuccess;
    public int mBitmapType = 4;

    /* renamed from: com.tencent.smtt.export.external.libwebp$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static int checkIsHuaModel() {
        String lowerCase = Build.BRAND.trim().toLowerCase();
        String lowerCase2 = Build.MODEL.trim().toLowerCase();
        int i = (lowerCase == null || lowerCase.length() <= 0 || !lowerCase.contains("huawei")) ? 0 : 1;
        if (lowerCase2 == null || lowerCase2.length() <= 0 || !lowerCase2.contains("huawei")) {
            return i;
        }
        return 1;
    }

    private String getCPUinfo() {
        String str = BuildConfig.FLAVOR;
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start().getInputStream();
            byte[] bArr = new byte[1024];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return str;
    }

    public static libwebp getInstance(Context context) {
        if (mInstance == null) {
            loadWepLibraryIfNeed(context);
            mInstance = new libwebp();
        }
        return mInstance;
    }

    private boolean isMultiCore() {
        return getCPUinfo().contains("processor");
    }

    public static void loadWepLibraryIfNeed(Context context) {
        if (!mIsLoadLibSuccess) {
            try {
                LibraryLoader.loadLibrary(context, "webp_base");
                mIsLoadLibSuccess = true;
            } catch (UnsatisfiedLinkError unused) {
            }
        }
    }

    public static void loadWepLibraryIfNeed(Context context, String str) {
        if (!mIsLoadLibSuccess) {
            try {
                System.load(str + File.separator + "libwebp_base.so");
                mIsLoadLibSuccess = true;
            } catch (UnsatisfiedLinkError unused) {
            }
        }
    }

    public int[] decodeBase(byte[] bArr, int[] iArr, int[] iArr2) {
        if (!mIsLoadLibSuccess) {
            return null;
        }
        return nativeDecode(bArr, isMultiCore, iArr, iArr2);
    }

    public int[] decodeBase_16bit(byte[] bArr, Bitmap.Config config) {
        if (!mIsLoadLibSuccess) {
            return null;
        }
        if (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()] != 1) {
            this.mBitmapType = 2;
        } else {
            this.mBitmapType = 3;
        }
        return nativeDecode_16bit(bArr, isMultiCore, this.mBitmapType);
    }

    public int[] decodeInto(byte[] bArr, int[] iArr, int[] iArr2) {
        if (!mIsLoadLibSuccess) {
            return null;
        }
        return nativeDecodeInto(bArr, isMultiCore, iArr, iArr2);
    }

    public int getInfo(byte[] bArr, int[] iArr, int[] iArr2) {
        if (!mIsLoadLibSuccess) {
            return 0;
        }
        return nativeGetInfo(bArr, iArr, iArr2);
    }

    public int[] incDecode(byte[] bArr, int[] iArr, int[] iArr2) {
        if (!mIsLoadLibSuccess) {
            return null;
        }
        return nativeIDecode(bArr, isMultiCore, iArr, iArr2);
    }

    public native int[] nativeDecode(byte[] bArr, boolean z, int[] iArr, int[] iArr2);

    public native int[] nativeDecodeInto(byte[] bArr, boolean z, int[] iArr, int[] iArr2);

    public native int[] nativeDecode_16bit(byte[] bArr, boolean z, int i);

    public native int nativeGetInfo(byte[] bArr, int[] iArr, int[] iArr2);

    public native int[] nativeIDecode(byte[] bArr, boolean z, int[] iArr, int[] iArr2);
}
