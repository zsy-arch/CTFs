package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes.dex */
public final class Tile implements Parcelable {
    public static final b CREATOR = new b();
    private static final Pools.SynchronizedPool<Tile> b = new Pools.SynchronizedPool<>(18);
    private final int a;
    public final byte[] data;
    public final int height;
    public final int width;

    public static Tile obtain(int i, int i2, byte[] bArr) {
        Tile acquire = b.acquire();
        return acquire != null ? acquire : new Tile(i, i2, bArr);
    }

    public void recycle() {
        b.release(this);
    }

    public Tile(int i, int i2, int i3, byte[] bArr) {
        this.a = i;
        this.width = i2;
        this.height = i3;
        this.data = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeByteArray(this.data);
    }
}
