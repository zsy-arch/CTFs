package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DemandServiceBean implements Serializable {
    private List<DemandDetailBean> datas;
    private int totalPage;
    private int totalRecord;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<DemandDetailBean> getDatas() {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        return this.datas;
    }

    public void setDatas(List<DemandDetailBean> datas) {
        this.datas = datas;
    }
}
