package com.alibaba.sdk.android.oss.model;

/* loaded from: classes.dex */
public class PartETag {
    private String eTag;
    private int partNumber;

    public PartETag(int partNumber, String eTag) {
        setPartNumber(partNumber);
        setETag(eTag);
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
