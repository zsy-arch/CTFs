package com.vsf2f.f2f.bean;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class ImageBean {
    private String id;
    private String remark;

    public ImageBean() {
    }

    public ImageBean(String id, String remark) {
        this.id = id;
        this.remark = remark;
    }

    public String getId() {
        if (TextUtils.isEmpty(this.id)) {
            this.id = "";
        }
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        if (TextUtils.isEmpty(this.remark)) {
            this.remark = "";
        }
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
