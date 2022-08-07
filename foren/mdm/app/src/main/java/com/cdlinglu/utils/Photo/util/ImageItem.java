package com.cdlinglu.utils.Photo.util;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

/* loaded from: classes.dex */
public class ImageItem implements Parcelable {
    public static final Parcelable.Creator<ImageItem> CREATOR = new Parcelable.Creator<ImageItem>() { // from class: com.cdlinglu.utils.Photo.util.ImageItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageItem createFromParcel(Parcel source) {
            return new ImageItem(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };
    public String imageId;
    public String imagePath;
    public boolean select;
    public String thumbnailPath;

    public ImageItem() {
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Bitmap getBitmap() {
        try {
            return Bimp.revitionImageSize(this.imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageId);
        dest.writeString(this.thumbnailPath);
        dest.writeString(this.imagePath);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
    }

    protected ImageItem(Parcel in) {
        this.imageId = in.readString();
        this.thumbnailPath = in.readString();
        this.imagePath = in.readString();
        this.select = in.readByte() != 0;
    }
}
