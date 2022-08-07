package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ProductCategory implements Parcelable {
    public static final Parcelable.Creator<ProductCategory> CREATOR = new Parcelable.Creator<ProductCategory>() { // from class: com.vsf2f.f2f.bean.ProductCategory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductCategory createFromParcel(Parcel in) {
            return new ProductCategory(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductCategory[] newArray(int size) {
            return new ProductCategory[size];
        }
    };
    private String guid;
    private int id;
    private boolean leaf;
    private String name;
    private int order;
    private String parentName;
    private int pid;
    private String text;
    private String typeGuid;
    private int typeId;
    private String typeName;
    private int underCount;

    protected ProductCategory(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.parentName = in.readString();
        this.text = in.readString();
        this.leaf = in.readByte() != 0;
        this.underCount = in.readInt();
        this.guid = in.readString();
        this.pid = in.readInt();
        this.order = in.readInt();
        this.typeId = in.readInt();
        this.typeName = in.readString();
        this.typeGuid = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.parentName);
        dest.writeString(this.text);
        dest.writeByte((byte) (this.leaf ? 1 : 0));
        dest.writeInt(this.underCount);
        dest.writeString(this.guid);
        dest.writeInt(this.pid);
        dest.writeInt(this.order);
        dest.writeInt(this.typeId);
        dest.writeString(this.typeName);
        dest.writeString(this.typeGuid);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return this.leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getUnderCount() {
        return this.underCount;
    }

    public void setUnderCount(int underCount) {
        this.underCount = underCount;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeGuid() {
        return this.typeGuid;
    }

    public void setTypeGuid(String typeGuid) {
        this.typeGuid = typeGuid;
    }
}
