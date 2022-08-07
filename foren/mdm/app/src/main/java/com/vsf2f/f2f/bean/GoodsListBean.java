package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class GoodsListBean implements Parcelable {
    public static final Parcelable.Creator<GoodsListBean> CREATOR = new Parcelable.Creator<GoodsListBean>() { // from class: com.vsf2f.f2f.bean.GoodsListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GoodsListBean createFromParcel(Parcel in) {
            return new GoodsListBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GoodsListBean[] newArray(int size) {
            return new GoodsListBean[size];
        }
    };
    private int limit;
    private int pageIndex;
    private int results;
    private ArrayList<ProductBean> rows;
    private int start;
    private int totalPage;

    public GoodsListBean() {
    }

    protected GoodsListBean(Parcel in) {
        this.limit = in.readInt();
        this.start = in.readInt();
        this.pageIndex = in.readInt();
        this.totalPage = in.readInt();
        this.results = in.readInt();
        this.rows = in.createTypedArrayList(ProductBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.limit);
        dest.writeInt(this.start);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.results);
        dest.writeTypedList(this.rows);
    }

    public ArrayList<ProductBean> getRows() {
        return this.rows;
    }

    public void setRows(ArrayList<ProductBean> rows) {
        this.rows = rows;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getResults() {
        return this.results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
