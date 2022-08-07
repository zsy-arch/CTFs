package com.amap.api.maps.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class VisibleRegionCreator implements Parcelable.Creator<VisibleRegion> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4;
        BadParcelableException e;
        LatLng latLng5;
        LatLngBounds latLngBounds = null;
        int readInt = parcel.readInt();
        try {
            LatLng latLng6 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            try {
                LatLng latLng7 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                try {
                    LatLng latLng8 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                    try {
                        latLng5 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                    } catch (BadParcelableException e2) {
                        e = e2;
                        latLng = null;
                        latLng2 = latLng8;
                        latLng3 = latLng7;
                        latLng4 = latLng6;
                    }
                    try {
                        latLngBounds = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
                        latLng = latLng5;
                        latLng2 = latLng8;
                        latLng3 = latLng7;
                        latLng4 = latLng6;
                    } catch (BadParcelableException e3) {
                        e = e3;
                        latLng = latLng5;
                        latLng2 = latLng8;
                        latLng3 = latLng7;
                        latLng4 = latLng6;
                        e.printStackTrace();
                        return new VisibleRegion(readInt, latLng4, latLng3, latLng2, latLng, latLngBounds);
                    }
                } catch (BadParcelableException e4) {
                    e = e4;
                    latLng = null;
                    latLng2 = null;
                    latLng3 = latLng7;
                    latLng4 = latLng6;
                }
            } catch (BadParcelableException e5) {
                latLng = null;
                latLng2 = null;
                latLng3 = null;
                latLng4 = latLng6;
                e = e5;
            }
        } catch (BadParcelableException e6) {
            e = e6;
            latLng = null;
            latLng2 = null;
            latLng3 = null;
            latLng4 = null;
        }
        return new VisibleRegion(readInt, latLng4, latLng3, latLng2, latLng, latLngBounds);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        parcel.writeInt(visibleRegion.a());
        parcel.writeParcelable(visibleRegion.nearLeft, i);
        parcel.writeParcelable(visibleRegion.nearRight, i);
        parcel.writeParcelable(visibleRegion.farLeft, i);
        parcel.writeParcelable(visibleRegion.farRight, i);
        parcel.writeParcelable(visibleRegion.latLngBounds, i);
    }
}
