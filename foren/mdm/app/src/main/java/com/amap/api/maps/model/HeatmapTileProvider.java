package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import android.util.Log;
import com.amap.api.col.dd;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.DPoint;
import com.tencent.smtt.sdk.TbsListener;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public class HeatmapTileProvider implements TileProvider {
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    private a c;
    private Collection<WeightedLatLng> d;
    private dd e;
    private int f;
    private Gradient g;
    private int[] h;
    private double[] i;
    private double j;
    private double[] k;
    private static final int[] a = {Color.rgb(102, (int) TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 0), Color.rgb(255, 0, 0)};
    private static final float[] b = {0.2f, 1.0f};
    public static final Gradient DEFAULT_GRADIENT = new Gradient(a, b);

    /* loaded from: classes.dex */
    public static class Builder {
        private Collection<WeightedLatLng> a;
        private int b = 12;
        private Gradient c = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double d = 0.6d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.d(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.a = collection;
            return this;
        }

        public Builder radius(int i) {
            this.b = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.c = gradient;
            return this;
        }

        public Builder transparency(double d) {
            this.d = Math.max(0.0d, Math.min(d, 1.0d));
            return this;
        }

        public HeatmapTileProvider build() {
            if (this.a == null || this.a.size() == 0) {
                try {
                    throw new AMapException("No input points.");
                } catch (AMapException e) {
                    Log.e("amap", e.getErrorMessage());
                    e.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return new HeatmapTileProvider(this);
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.d = builder.a;
        this.f = builder.b;
        this.g = builder.c;
        if (this.g == null || !this.g.isAvailable()) {
            this.g = DEFAULT_GRADIENT;
        }
        this.j = builder.d;
        this.i = a(this.f, this.f / 3.0d);
        a(this.g);
        c(this.d);
    }

    private void c(Collection<WeightedLatLng> collection) {
        try {
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                    arrayList.add(weightedLatLng);
                }
            }
            this.d = arrayList;
            this.e = a(this.d);
            this.c = new a(this.e);
            for (WeightedLatLng weightedLatLng2 : this.d) {
                this.c.a(weightedLatLng2);
            }
            this.k = a(this.f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> d(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : collection) {
            arrayList.add(new WeightedLatLng(latLng));
        }
        return arrayList;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public Tile getTile(int i, int i2, int i3) {
        Collection<WeightedLatLng> collection;
        double d;
        double pow = 1.0d / Math.pow(2.0d, i3);
        double d2 = (this.f * pow) / 256.0d;
        double d3 = ((2.0d * d2) + pow) / ((this.f * 2) + 256);
        double d4 = (i * pow) - d2;
        double d5 = ((i + 1) * pow) + d2;
        double d6 = (i2 * pow) - d2;
        double d7 = (pow * (i2 + 1)) + d2;
        ArrayList arrayList = new ArrayList();
        if (d4 < 0.0d) {
            collection = this.c.a(new dd(1.0d + d4, 1.0d, d6, d7));
            d = -1.0d;
        } else if (d5 > 1.0d) {
            collection = this.c.a(new dd(0.0d, d5 - 1.0d, d6, d7));
            d = 1.0d;
        } else {
            collection = arrayList;
            d = 0.0d;
        }
        dd ddVar = new dd(d4, d5, d6, d7);
        if (!ddVar.a(new dd(this.e.a - d2, this.e.c + d2, this.e.b - d2, d2 + this.e.d))) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> a2 = this.c.a(ddVar);
        if (a2.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, (this.f * 2) + 256, (this.f * 2) + 256);
        for (WeightedLatLng weightedLatLng : a2) {
            DPoint point = weightedLatLng.getPoint();
            int i4 = (int) ((point.y - d6) / d3);
            double[] dArr2 = dArr[(int) ((point.x - d4) / d3)];
            dArr2[i4] = dArr2[i4] + weightedLatLng.intensity;
        }
        for (WeightedLatLng weightedLatLng2 : collection) {
            DPoint point2 = weightedLatLng2.getPoint();
            int i5 = (int) ((point2.y - d6) / d3);
            double[] dArr3 = dArr[(int) (((point2.x + d) - d4) / d3)];
            dArr3[i5] = dArr3[i5] + weightedLatLng2.intensity;
        }
        return a(a(a(dArr, this.i), this.h, this.k[i3]));
    }

    private void a(Gradient gradient) {
        this.g = gradient;
        this.h = gradient.generateColorMap(this.j);
    }

    private double[] a(int i) {
        double[] dArr = new double[21];
        for (int i2 = 5; i2 < 11; i2++) {
            dArr[i2] = a(this.d, this.e, i, (int) (1280.0d * Math.pow(2.0d, i2)));
            if (i2 == 5) {
                for (int i3 = 0; i3 < i2; i3++) {
                    dArr[i3] = dArr[i2];
                }
            }
        }
        for (int i4 = 11; i4 < 21; i4++) {
            dArr[i4] = dArr[10];
        }
        return dArr;
    }

    private static Tile a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Tile.obtain(256, 256, byteArrayOutputStream.toByteArray());
    }

    static dd a(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d = next.getPoint().x;
        double d2 = next.getPoint().x;
        double d3 = next.getPoint().y;
        double d4 = next.getPoint().y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d5 = next2.getPoint().x;
            double d6 = next2.getPoint().y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new dd(d, d2, d3, d4);
    }

    static double[] a(int i, double d) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((-i2) * i2) / ((2.0d * d) * d));
        }
        return dArr;
    }

    static double[][] a(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(dArr2.length / 2.0d);
        int length = dArr.length;
        int i = length - (floor * 2);
        int i2 = (floor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, length, length);
        for (int i3 = 0; i3 < length; i3++) {
            for (int i4 = 0; i4 < length; i4++) {
                double d = dArr[i3][i4];
                if (d != 0.0d) {
                    int i5 = (i2 < i3 + floor ? i2 : i3 + floor) + 1;
                    for (int i6 = floor > i3 - floor ? floor : i3 - floor; i6 < i5; i6++) {
                        double[] dArr4 = dArr3[i6];
                        dArr4[i4] = dArr4[i4] + (dArr2[i6 - (i3 - floor)] * d);
                    }
                }
            }
        }
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, i, i);
        for (int i7 = floor; i7 < i2 + 1; i7++) {
            for (int i8 = 0; i8 < length; i8++) {
                double d2 = dArr3[i7][i8];
                if (d2 != 0.0d) {
                    int i9 = (i2 < i8 + floor ? i2 : i8 + floor) + 1;
                    for (int i10 = floor > i8 - floor ? floor : i8 - floor; i10 < i9; i10++) {
                        double[] dArr6 = dArr5[i7 - floor];
                        int i11 = i10 - floor;
                        dArr6[i11] = dArr6[i11] + (dArr2[i10 - (i8 - floor)] * d2);
                    }
                }
            }
        }
        return dArr5;
    }

    static Bitmap a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = (iArr.length - 1) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static double a(Collection<WeightedLatLng> collection, dd ddVar, int i, int i2) {
        LongSparseArray longSparseArray;
        double d = ddVar.a;
        double d2 = ddVar.c;
        double d3 = ddVar.b;
        double d4 = ddVar.d;
        double d5 = ((int) ((i2 / (i * 2)) + 0.5d)) / (d2 - d > d4 - d3 ? d2 - d : d4 - d3);
        LongSparseArray longSparseArray2 = new LongSparseArray();
        double d6 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            int i3 = (int) ((weightedLatLng.getPoint().x - d) * d5);
            int i4 = (int) ((weightedLatLng.getPoint().y - d3) * d5);
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray2.get(i3);
            if (longSparseArray3 == null) {
                LongSparseArray longSparseArray4 = new LongSparseArray();
                longSparseArray2.put(i3, longSparseArray4);
                longSparseArray = longSparseArray4;
            } else {
                longSparseArray = longSparseArray3;
            }
            Double d7 = (Double) longSparseArray.get(i4);
            if (d7 == null) {
                d7 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(weightedLatLng.intensity + d7.doubleValue());
            longSparseArray.put(i4, valueOf);
            d6 = valueOf.doubleValue() > d6 ? valueOf.doubleValue() : d6;
        }
        return d6;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileHeight() {
        return 256;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileWidth() {
        return 256;
    }
}
