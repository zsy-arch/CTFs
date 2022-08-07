package com.tencent.connect.dataprovider;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class DataType {
    public static final int CONTENT_AND_IMAGE_PATH = 1;
    public static final int CONTENT_AND_VIDEO_PATH = 2;
    public static final int CONTENT_ONLY = 4;

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class TextAndMediaPath implements Parcelable {
        public static final Parcelable.Creator<TextAndMediaPath> CREATOR = new Parcelable.Creator<TextAndMediaPath>() { // from class: com.tencent.connect.dataprovider.DataType.TextAndMediaPath.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TextAndMediaPath createFromParcel(Parcel parcel) {
                return new TextAndMediaPath(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TextAndMediaPath[] newArray(int i) {
                return new TextAndMediaPath[i];
            }
        };
        private String a;
        private String b;

        public TextAndMediaPath(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getText() {
            return this.a;
        }

        public String getMediaPath() {
            return this.b;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
        }

        private TextAndMediaPath(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class TextOnly implements Parcelable {
        public static final Parcelable.Creator<TextOnly> CREATOR = new Parcelable.Creator<TextOnly>() { // from class: com.tencent.connect.dataprovider.DataType.TextOnly.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TextOnly createFromParcel(Parcel parcel) {
                return new TextOnly(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TextOnly[] newArray(int i) {
                return new TextOnly[i];
            }
        };
        private String a;

        public TextOnly(String str) {
            this.a = str;
        }

        public String getText() {
            return this.a;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
        }

        private TextOnly(Parcel parcel) {
            this.a = parcel.readString();
        }
    }
}
