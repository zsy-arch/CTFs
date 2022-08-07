package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class BitmapDescriptor implements Parcelable, Cloneable {
    public static final BitmapDescriptorCreator CREATOR = new BitmapDescriptorCreator();
    int a;
    int b;
    Bitmap c;

    public BitmapDescriptor(Bitmap bitmap) {
        this.a = 0;
        this.b = 0;
        if (bitmap != null) {
            this.a = bitmap.getWidth();
            this.b = bitmap.getHeight();
            this.c = bitmap.copy(bitmap.getConfig(), true);
        }
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2) {
        this.a = 0;
        this.b = 0;
        this.a = i;
        this.b = i2;
        this.c = bitmap;
    }

    public BitmapDescriptor clone() {
        try {
            return new BitmapDescriptor(this.c.copy(this.c.getConfig(), true), this.a, this.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.c;
    }

    public int getWidth() {
        return this.a;
    }

    public int getHeight() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
    }

    public void recycle() {
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
    }

    public boolean equals(Object obj) {
        if (this.c == null || this.c.isRecycled() || obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) obj;
        if (bitmapDescriptor.c == null || bitmapDescriptor.c.isRecycled() || this.a != bitmapDescriptor.getWidth() || this.b != bitmapDescriptor.getHeight()) {
            return false;
        }
        try {
            return this.c.sameAs(bitmapDescriptor.c);
        } catch (Throwable th) {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode();
    }
}
