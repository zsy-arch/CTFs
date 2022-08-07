package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.smtt.sdk.TbsListener;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class NavigateArrowOptions implements Parcelable {
    public static final NavigateArrowOptionsCreator CREATOR = new NavigateArrowOptionsCreator();
    String a;
    private float c = 10.0f;
    private int d = Color.argb((int) TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 87, 235, 204);
    private int e = Color.argb(170, 0, (int) Constant.PICTURE_TYPE_172, 146);
    private float f = 0.0f;
    private boolean g = true;
    private final List<LatLng> b = new ArrayList();

    public NavigateArrowOptions add(LatLng latLng) {
        this.b.add(latLng);
        return this;
    }

    public NavigateArrowOptions add(LatLng... latLngArr) {
        this.b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public NavigateArrowOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            this.b.add(latLng);
        }
        return this;
    }

    public NavigateArrowOptions width(float f) {
        this.c = f;
        return this;
    }

    public NavigateArrowOptions topColor(int i) {
        this.d = i;
        return this;
    }

    @Deprecated
    public NavigateArrowOptions sideColor(int i) {
        this.e = i;
        return this;
    }

    public NavigateArrowOptions zIndex(float f) {
        this.f = f;
        return this;
    }

    public NavigateArrowOptions visible(boolean z) {
        this.g = z;
        return this;
    }

    public List<LatLng> getPoints() {
        return this.b;
    }

    public float getWidth() {
        return this.c;
    }

    public int getTopColor() {
        return this.d;
    }

    @Deprecated
    public int getSideColor() {
        return this.e;
    }

    public float getZIndex() {
        return this.f;
    }

    public boolean isVisible() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.b);
        parcel.writeFloat(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
        parcel.writeByte((byte) (this.g ? 1 : 0));
        parcel.writeString(this.a);
    }
}
