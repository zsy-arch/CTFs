package com.vsf2f.f2f.ui.utils.photo;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ImageConfig implements Parcelable {
    public static final Parcelable.Creator<ImageConfig> CREATOR = new Parcelable.Creator<ImageConfig>() { // from class: com.vsf2f.f2f.ui.utils.photo.ImageConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageConfig createFromParcel(Parcel source) {
            return new ImageConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageConfig[] newArray(int size) {
            return new ImageConfig[size];
        }
    };
    public String[] mimeType;
    public int minHeight;
    public long minSize;
    public int minWidth;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.minWidth);
        dest.writeInt(this.minHeight);
        dest.writeLong(this.minSize);
        dest.writeStringArray(this.mimeType);
    }

    public ImageConfig() {
    }

    protected ImageConfig(Parcel in) {
        this.minWidth = in.readInt();
        this.minHeight = in.readInt();
        this.minSize = in.readLong();
        this.mimeType = in.createStringArray();
    }
}
