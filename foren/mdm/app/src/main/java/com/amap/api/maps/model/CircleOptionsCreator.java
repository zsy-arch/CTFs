package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CircleOptionsCreator implements Parcelable.Creator<CircleOptions> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CircleOptions createFromParcel(Parcel parcel) {
        boolean z = true;
        CircleOptions circleOptions = new CircleOptions();
        Bundle readBundle = parcel.readBundle();
        circleOptions.center(new LatLng(readBundle.getDouble("lat"), readBundle.getDouble("lng")));
        circleOptions.radius(parcel.readDouble());
        circleOptions.strokeWidth(parcel.readFloat());
        circleOptions.strokeColor(parcel.readInt());
        circleOptions.fillColor(parcel.readInt());
        circleOptions.zIndex(parcel.readInt());
        if (parcel.readByte() != 1) {
            z = false;
        }
        circleOptions.visible(z);
        circleOptions.a = parcel.readString();
        return circleOptions;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
