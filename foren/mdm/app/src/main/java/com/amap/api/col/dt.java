package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.col.gj;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.CameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FPoint3;
import com.autonavi.amap.mapcore.FileUtil;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: Util.java */
/* loaded from: classes.dex */
public class dt {
    private static FPoint[] a = {FPoint.obtain(), FPoint.obtain(), FPoint.obtain(), FPoint.obtain()};
    private static List<Float> b = new ArrayList(4);
    private static List<Float> c = new ArrayList(4);

    public static Bitmap a(Context context, String str) {
        try {
            InputStream open = dq.a(context).open(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            open.close();
            return decodeStream;
        } catch (Throwable th) {
            gr.b(th, "Util", "fromAsset");
            return null;
        }
    }

    public static void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static String a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    public static float a(float f, float f2) {
        if (f <= 40.0f) {
            return f;
        }
        if (f2 <= 15.0f) {
            return 40.0f;
        }
        if (f2 <= 16.0f) {
            return 50.0f;
        }
        if (f2 <= 17.0f) {
            return 54.0f;
        }
        if (f2 <= 18.0f) {
            return 57.0f;
        }
        return 60.0f;
    }

    public static float a(MapConfig mapConfig, float f) {
        if (mapConfig != null) {
            if (f > mapConfig.maxZoomLevel) {
                return mapConfig.maxZoomLevel;
            }
            if (f < mapConfig.minZoomLevel) {
                return mapConfig.minZoomLevel;
            }
            return f;
        } else if (f > 19.0f) {
            return 19.0f;
        } else {
            if (f < 3.0f) {
                return 3.0f;
            }
            return f;
        }
    }

    public static FloatBuffer a(float[] fArr) {
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
            asFloatBuffer.put(fArr);
            asFloatBuffer.position(0);
            return asFloatBuffer;
        } catch (Throwable th) {
            gr.b(th, "Util", "makeFloatBuffer1");
            th.printStackTrace();
            return null;
        }
    }

    public static FloatBuffer a(float[] fArr, FloatBuffer floatBuffer) {
        try {
            floatBuffer.clear();
            floatBuffer.put(fArr);
            floatBuffer.position(0);
            return floatBuffer;
        } catch (Throwable th) {
            gr.b(th, "Util", "makeFloatBuffer2");
            th.printStackTrace();
            return null;
        }
    }

    public static int a(int i, int i2) {
        return a(0, Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888), true);
    }

    public static int a(Bitmap bitmap) {
        return a(bitmap, false);
    }

    public static int a(Bitmap bitmap, boolean z) {
        return a(0, bitmap, z);
    }

    public static int a(int i, Bitmap bitmap, boolean z) {
        int b2 = b(i, bitmap, z);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return b2;
    }

    public static int b(int i, Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = {0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        if (z) {
            GLES20.glTexParameterf(3553, 10242, 10497.0f);
            GLES20.glTexParameterf(3553, 10243, 10497.0f);
        } else {
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        return i;
    }

    public static int a(int i, Bitmap bitmap, int i2, int i3) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = {0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glBindTexture(3553, i);
        GLUtils.texSubImage2D(3553, 0, i2, i3, bitmap);
        return i;
    }

    public static String a(String... strArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }

    public static int a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f), (int) (bitmap.getHeight() * f), true);
    }

    public static String a(Context context) {
        File file = new File(FileUtil.getMapBaseStorage(context), "data");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.toString() + File.separator);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String b(Context context) {
        String a2 = a(context);
        if (a2 == null) {
            return null;
        }
        File file = new File(a2, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String a(int i) {
        if (i < 1000) {
            return i + "m";
        }
        return (i / 1000) + "km";
    }

    public static boolean c(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (!(context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
            NetworkInfo.State state = activeNetworkInfo.getState();
            if (state == null || state == NetworkInfo.State.DISCONNECTED || state == NetworkInfo.State.DISCONNECTING) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static void b(int i) {
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
    }

    public static String a(InputStream inputStream) {
        try {
            return new String(b(inputStream), "utf-8");
        } catch (Throwable th) {
            gr.b(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr, 0, 2048);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.io.File r6) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.dt.a(java.io.File):java.lang.String");
    }

    public static boolean a(LatLng latLng, List<LatLng> list) {
        int i = 0;
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = latLng.latitude;
        if (list.size() < 3) {
            return false;
        }
        if (!list.get(0).equals(list.get(list.size() - 1))) {
            list.add(list.get(0));
        }
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            double d4 = list.get(i2).longitude;
            double d5 = list.get(i2).latitude;
            double d6 = list.get(i2 + 1).longitude;
            double d7 = list.get(i2 + 1).latitude;
            if (b(d, d2, d4, d5, d6, d7)) {
                return true;
            }
            if (Math.abs(d7 - d5) >= 1.0E-9d) {
                if (b(d4, d5, d, d2, 180.0d, d3)) {
                    if (d5 > d7) {
                        i++;
                    }
                } else if (b(d6, d7, d, d2, 180.0d, d3)) {
                    if (d7 > d5) {
                        i++;
                    }
                } else if (a(d4, d5, d6, d7, d, d2, 180.0d, d3)) {
                    i++;
                }
            }
        }
        if (i % 2 != 0) {
            return true;
        }
        return false;
    }

    public static double a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    public static boolean b(double d, double d2, double d3, double d4, double d5, double d6) {
        if (Math.abs(a(d, d2, d3, d4, d5, d6)) >= 1.0E-9d || (d - d3) * (d - d5) > 0.0d || (d2 - d4) * (d2 - d6) > 0.0d) {
            return false;
        }
        return true;
    }

    public static boolean a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = ((d3 - d) * (d8 - d6)) - ((d4 - d2) * (d7 - d5));
        if (d9 == 0.0d) {
            return false;
        }
        double d10 = (((d2 - d6) * (d7 - d5)) - ((d - d5) * (d8 - d6))) / d9;
        double d11 = (((d2 - d6) * (d3 - d)) - ((d - d5) * (d4 - d2))) / d9;
        if (d10 < 0.0d || d10 > 1.0d || d11 < 0.0d || d11 > 1.0d) {
            return false;
        }
        return true;
    }

    public static List<FPoint> a(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                FPoint fPoint = (FPoint) arrayList2.get(i % size);
                FPoint fPoint2 = (FPoint) arrayList2.get((i + 1) % size);
                if (i == 0 && a(fPoint, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(fPoint);
                }
                if (a(fPoint, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    if (a(fPoint2, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(a(fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length], fPoint, fPoint2));
                    }
                } else if (a(fPoint2, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(a(fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length], fPoint, fPoint2));
                    arrayList.add(fPoint2);
                }
                i++;
            }
            arrayList2.clear();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        return arrayList2;
    }

    public static List<FPoint> b(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                FPoint3 fPoint3 = (FPoint3) arrayList2.get(i % size);
                FPoint3 fPoint32 = (FPoint3) arrayList2.get((i + 1) % size);
                if (i == 0 && a(fPoint3, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(fPoint3);
                }
                if (a(fPoint3, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    if (a(fPoint32, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                        arrayList.add(fPoint32);
                    } else {
                        arrayList.add(a(fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length], fPoint3, fPoint32));
                    }
                } else if (a(fPoint32, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(a(fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length], fPoint3, fPoint32));
                    arrayList.add(fPoint32);
                }
                i++;
            }
            arrayList2.clear();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        return arrayList2;
    }

    private static float a(float f) {
        if (f == 50.0f) {
            return 0.082f;
        }
        if (f == 54.0f) {
            return 0.15f;
        }
        if (f == 57.0f) {
            return 0.2f;
        }
        if (f == 60.0f) {
            return 0.25f;
        }
        return 0.0f;
    }

    public static FPoint[] a(k kVar, boolean z) {
        int i;
        int i2;
        float a2 = (float) (a(kVar.getCameraAngle()) * kVar.getMapHeight());
        if (z) {
            i2 = 100;
            i = 10;
        } else {
            i = 0;
            i2 = 0;
        }
        FPoint obtain = FPoint.obtain();
        kVar.b(-i2, (int) (a2 - i), obtain);
        a[0].set(obtain.x, obtain.y);
        FPoint obtain2 = FPoint.obtain();
        kVar.b(kVar.getMapWidth() + i2, (int) (a2 - i), obtain2);
        a[1].set(obtain2.x, obtain2.y);
        FPoint obtain3 = FPoint.obtain();
        kVar.b(kVar.getMapWidth() + i2, kVar.getMapHeight() + i2, obtain3);
        a[2].set(obtain3.x, obtain3.y);
        FPoint obtain4 = FPoint.obtain();
        kVar.b(-i2, i2 + kVar.getMapHeight(), obtain4);
        a[3].set(obtain4.x, obtain4.y);
        obtain.recycle();
        obtain2.recycle();
        obtain3.recycle();
        obtain4.recycle();
        return a;
    }

    public static FPoint[] a(IAMap iAMap, boolean z, GLMapState gLMapState) {
        int i;
        int i2;
        float a2 = (float) (a(iAMap.getCameraAngle()) * iAMap.getMapHeight());
        if (z) {
            i2 = 100;
            i = 10;
        } else {
            i = 0;
            i2 = 0;
        }
        FPoint obtain = FPoint.obtain();
        gLMapState.win2Map(-i2, (int) (a2 - i), obtain);
        a[0].set(obtain.x, obtain.y);
        FPoint obtain2 = FPoint.obtain();
        gLMapState.win2Map(iAMap.getMapWidth() + i2, (int) (a2 - i), obtain2);
        a[1].set(obtain2.x, obtain2.y);
        FPoint obtain3 = FPoint.obtain();
        gLMapState.win2Map(iAMap.getMapWidth() + i2, iAMap.getMapHeight() + i2, obtain3);
        a[2].set(obtain3.x, obtain3.y);
        FPoint obtain4 = FPoint.obtain();
        gLMapState.win2Map(-i2, i2 + iAMap.getMapHeight(), obtain4);
        a[3].set(obtain4.x, obtain4.y);
        obtain.recycle();
        obtain2.recycle();
        obtain3.recycle();
        obtain4.recycle();
        return a;
    }

    private static FPoint3 a(FPoint fPoint, FPoint fPoint2, FPoint3 fPoint3, FPoint3 fPoint32) {
        FPoint3 fPoint33 = new FPoint3(0.0f, 0.0f, fPoint3.colorIndex);
        double d = ((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y));
        double d2 = ((fPoint2.y - fPoint.y) * (fPoint32.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint32.y - fPoint3.y));
        fPoint33.x = (float) (fPoint3.x + (((fPoint32.x - fPoint3.x) * d) / d2));
        fPoint33.y = (float) (((d * (fPoint32.y - fPoint3.y)) / d2) + fPoint3.y);
        return fPoint33;
    }

    private static FPoint a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3, FPoint fPoint4) {
        FPoint obtain = FPoint.obtain(0.0f, 0.0f);
        double d = ((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y));
        double d2 = ((fPoint2.y - fPoint.y) * (fPoint4.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint4.y - fPoint3.y));
        obtain.x = (float) (fPoint3.x + (((fPoint4.x - fPoint3.x) * d) / d2));
        obtain.y = (float) (((d * (fPoint4.y - fPoint3.y)) / d2) + fPoint3.y);
        return obtain;
    }

    public static boolean a(FPoint fPoint, FPoint[] fPointArr) {
        if (fPointArr == null) {
            return false;
        }
        for (byte b2 = 0; b2 < fPointArr.length; b2 = (byte) (b2 + 1)) {
            if (!a(fPoint, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return ((double) (((fPoint3.x - fPoint2.x) * (fPoint.y - fPoint2.y)) - ((fPoint.x - fPoint2.x) * (fPoint3.y - fPoint2.y)))) >= 0.0d;
    }

    public static List<IPoint> a(IPoint[] iPointArr, List<IPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                IPoint iPoint = (IPoint) arrayList2.get(i % size);
                IPoint iPoint2 = (IPoint) arrayList2.get((i + 1) % size);
                if (i == 0 && a(iPoint, iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length])) {
                    arrayList.add(iPoint);
                }
                if (a(iPoint, iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length])) {
                    if (a(iPoint2, iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length])) {
                        arrayList.add(iPoint2);
                    } else {
                        arrayList.add(a(iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length], iPoint, iPoint2));
                    }
                } else if (a(iPoint2, iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length])) {
                    arrayList.add(a(iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length], iPoint, iPoint2));
                    arrayList.add(iPoint2);
                }
                i++;
            }
            arrayList2.clear();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        return arrayList2;
    }

    private static IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, IPoint iPoint4) {
        IPoint obtain = IPoint.obtain(0, 0);
        double d = ((iPoint2.y - iPoint.y) * (iPoint.x - iPoint3.x)) - ((iPoint2.x - iPoint.x) * (iPoint.y - iPoint3.y));
        double d2 = ((iPoint2.y - iPoint.y) * (iPoint4.x - iPoint3.x)) - ((iPoint2.x - iPoint.x) * (iPoint4.y - iPoint3.y));
        obtain.x = (int) (iPoint3.x + (((iPoint4.x - iPoint3.x) * d) / d2));
        obtain.y = (int) (((d * (iPoint4.y - iPoint3.y)) / d2) + iPoint3.y);
        return obtain;
    }

    public static boolean a(List<IPoint> list, int i, int i2) {
        if (i2 < 3) {
            return false;
        }
        double d = 0.0d;
        int i3 = i2 - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            IPoint iPoint = list.get(i3);
            IPoint iPoint2 = list.get(i4);
            d += ((iPoint.x / 1000000.0d) * (iPoint2.y / 1000000.0d)) - ((iPoint.y / 1000000.0d) * (iPoint2.x / 1000000.0d));
            i3 = i4;
        }
        return d < 0.0d;
    }

    private static boolean a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3) {
        return a(iPoint.x, iPoint.y, iPoint2, iPoint3);
    }

    private static boolean a(int i, int i2, IPoint iPoint, IPoint iPoint2) {
        return (((double) (iPoint2.x - iPoint.x)) * ((double) (i2 - iPoint.y))) - (((double) (i - iPoint.x)) * ((double) (iPoint2.y - iPoint.y))) >= 0.0d;
    }

    public static Bitmap a(int i, int i2, int i3, int i4) {
        try {
            int[] iArr = new int[i3 * i4];
            int[] iArr2 = new int[i3 * i4];
            IntBuffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            GLES20.glReadPixels(i, i2, i3, i4, 6408, 5121, wrap);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = iArr[(i5 * i3) + i6];
                    iArr2[(((i4 - i5) - 1) * i3) + i6] = (i7 & (-16711936)) | ((i7 << 16) & 16711680) | ((i7 >> 16) & 255);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return createBitmap;
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    public static gj e() {
        try {
            if (g.f == null) {
                return new gj.a("3dmap", "5.2.1", g.d).a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"}).a("5.2.1").a();
            }
            return g.f;
        } catch (Throwable th) {
            return null;
        }
    }

    private static void b(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                b(((ViewGroup) view).getChildAt(i));
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setHorizontallyScrolling(false);
        }
    }

    public static Bitmap a(View view) {
        try {
            b(view);
            view.destroyDrawingCache();
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                return drawingCache.copy(Bitmap.Config.ARGB_8888, false);
            }
            return null;
        } catch (Throwable th) {
            gr.b(th, "Utils", "getBitmapFromView");
            th.printStackTrace();
            return null;
        }
    }

    public static DPoint a(LatLng latLng) {
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return DPoint.obtain(((latLng.longitude / 360.0d) + 0.5d) * 1.0d, (((Math.log((1.0d + sin) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * 1.0d);
    }

    public static boolean a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public static Pair<Float, IPoint> a(CameraUpdateMessage cameraUpdateMessage, GLMapState gLMapState, MapConfig mapConfig) {
        return a(mapConfig, Math.max(cameraUpdateMessage.paddingLeft, 1), Math.max(cameraUpdateMessage.paddingRight, 1), Math.max(cameraUpdateMessage.paddingTop, 1), Math.max(cameraUpdateMessage.paddingBottom, 1), cameraUpdateMessage.bounds, cameraUpdateMessage.width, cameraUpdateMessage.height);
    }

    public static Pair<Float, IPoint> a(MapConfig mapConfig, int i, int i2, int i3, int i4, LatLngBounds latLngBounds, int i5, int i6) {
        int i7;
        int i8;
        Point LatLongToPixels = VirtualEarthProjection.LatLongToPixels(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, 20);
        Point LatLongToPixels2 = VirtualEarthProjection.LatLongToPixels(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, 20);
        int i9 = LatLongToPixels.x - LatLongToPixels2.x;
        int i10 = LatLongToPixels2.y - LatLongToPixels.y;
        int i11 = i5 - (i + i2);
        int i12 = i6 - (i3 + i4);
        if (i9 < 0 && i10 < 0) {
            return null;
        }
        int i13 = i9 <= 0 ? 1 : i9;
        int i14 = i10 <= 0 ? 1 : i10;
        if (i11 <= 0) {
            i11 = 1;
        }
        if (i12 <= 0) {
            i12 = 1;
        }
        Pair<Float, Boolean> a2 = a(mapConfig, LatLongToPixels.x, LatLongToPixels.y, LatLongToPixels2.x, LatLongToPixels2.y, i11, i12);
        float floatValue = ((Float) a2.first).floatValue();
        boolean booleanValue = ((Boolean) a2.second).booleanValue();
        float a3 = a(mapConfig.getMapZoomScale(), floatValue, i13);
        float a4 = a(mapConfig.getMapZoomScale(), floatValue, i14);
        if (floatValue >= mapConfig.maxZoomLevel) {
            i8 = (int) (LatLongToPixels.y + ((((i4 - i3) + a4) * i14) / (a4 * 2.0f)));
            i7 = (int) (((((i2 - i) + a3) * i13) / (a3 * 2.0f)) + LatLongToPixels2.x);
        } else if (booleanValue) {
            i7 = (int) (LatLongToPixels2.x + ((((i5 / 2) - i) / a3) * i13));
            i8 = (int) (LatLongToPixels.y + ((((i4 - i3) + a4) * i14) / (a4 * 2.0f)));
        } else {
            i7 = (int) (LatLongToPixels2.x + ((((i2 - i) + a3) * i13) / (a3 * 2.0f)));
            i8 = (int) (LatLongToPixels.y + ((((i6 / 2) - i3) / a4) * i14));
        }
        return new Pair<>(Float.valueOf(floatValue), IPoint.obtain((int) (i7 + a(mapConfig.getMapZoomScale(), floatValue, mapConfig.getAnchorX() - (i5 >> 1))), (int) (i8 + a(mapConfig.getMapZoomScale(), floatValue, mapConfig.getAnchorY() - (i6 >> 1)))));
    }

    private static double a(float f, double d, double d2) {
        return 20.0d - (Math.log(d2 / (f * d)) / Math.log(2.0d));
    }

    private static float a(float f, float f2, double d) {
        return (float) (d / (Math.pow(2.0d, 20.0f - f2) * f));
    }

    private static float a(float f, float f2, float f3) {
        return (float) (f3 * Math.pow(2.0d, 20.0f - f2) * f);
    }

    public static Pair<Float, Boolean> a(MapConfig mapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float min;
        boolean z = true;
        mapConfig.getS_z();
        if (i == i3 && i2 == i4) {
            min = mapConfig.getMaxZoomLevel();
        } else {
            float a2 = (float) a(mapConfig.getMapZoomScale(), i6, Math.abs(i4 - i2));
            float a3 = (float) a(mapConfig.getMapZoomScale(), i5, Math.abs(i3 - i));
            float min2 = Math.min(a3, a2);
            if (min2 != a3) {
                z = false;
            }
            min = Math.min(mapConfig.getMaxZoomLevel(), Math.max(mapConfig.getMinZoomLevel(), min2));
        }
        return new Pair<>(Float.valueOf(min), Boolean.valueOf(z));
    }

    public static boolean b(int i, int i2) {
        if (i > 0 && i2 > 0) {
            return true;
        }
        Log.w("3dmap", "the map must have a size");
        return false;
    }

    private static Float[] a(List<Float> list, List<Float> list2, List<Float> list3, List<Float> list4, float f, float f2) {
        Float[] fArr = new Float[2];
        float floatValue = ((Float) Collections.min(list)).floatValue();
        float floatValue2 = ((Float) Collections.max(list)).floatValue();
        float floatValue3 = ((Float) Collections.min(list2)).floatValue();
        float floatValue4 = ((Float) Collections.max(list2)).floatValue();
        float abs = Math.abs(floatValue2 - floatValue);
        float abs2 = Math.abs(floatValue3 - floatValue4);
        float floatValue5 = ((Float) Collections.min(list3)).floatValue();
        float floatValue6 = ((Float) Collections.max(list3)).floatValue();
        float floatValue7 = ((Float) Collections.min(list4)).floatValue();
        float floatValue8 = ((Float) Collections.max(list4)).floatValue();
        float abs3 = abs > Math.abs(floatValue6 - floatValue5) ? Math.abs(floatValue6 - floatValue5) : abs;
        float abs4 = abs2 > Math.abs(floatValue8 - floatValue7) ? Math.abs(floatValue8 - floatValue7) : abs2;
        float f3 = floatValue5 + (abs3 / 2.0f);
        float f4 = (abs4 / 2.0f) + floatValue7;
        float f5 = floatValue6 - (abs3 / 2.0f);
        float f6 = floatValue8 - (abs4 / 2.0f);
        if (f > f3) {
            f3 = f;
        }
        if (f3 < f5) {
            f5 = f3;
        }
        if (f2 < f6) {
            f6 = f2;
        }
        if (f6 <= f4) {
            f6 = f4;
        }
        fArr[0] = Float.valueOf(f5);
        fArr[1] = Float.valueOf(f6);
        return fArr;
    }

    public static synchronized Integer[] a(IPoint[] iPointArr, IPoint[] iPointArr2, int i, int i2) {
        Integer[] numArr;
        synchronized (dt.class) {
            if (!(iPointArr == null || iPointArr2 == null)) {
                if (iPointArr.length == 2) {
                    ArrayList arrayList = new ArrayList(4);
                    ArrayList arrayList2 = new ArrayList(4);
                    for (int i3 = 0; i3 < iPointArr2.length; i3++) {
                        IPoint iPoint = iPointArr2[i3];
                        arrayList.add(i3, Float.valueOf(iPoint.x));
                        arrayList2.add(i3, Float.valueOf(iPoint.y));
                    }
                    ArrayList arrayList3 = new ArrayList(2);
                    ArrayList arrayList4 = new ArrayList(2);
                    for (int i4 = 0; i4 < iPointArr.length; i4++) {
                        IPoint iPoint2 = iPointArr[i4];
                        arrayList3.add(i4, Float.valueOf(iPoint2.x));
                        arrayList4.add(i4, Float.valueOf(iPoint2.y));
                    }
                    Float[] a2 = a(arrayList, arrayList2, arrayList3, arrayList4, i, i2);
                    numArr = new Integer[]{Integer.valueOf((int) a2[0].floatValue()), Integer.valueOf((int) a2[1].floatValue())};
                }
            }
            numArr = null;
        }
        return numArr;
    }

    public static void a(Rect rect) {
        if (rect != null) {
            rect.set(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public static void b(Rect rect, int i, int i2) {
        if (rect != null) {
            if (i < rect.left) {
                rect.left = i;
            }
            if (i > rect.right) {
                rect.right = i;
            }
            if (i2 > rect.top) {
                rect.top = i2;
            }
            if (i2 < rect.bottom) {
                rect.bottom = i2;
            }
        }
    }
}
