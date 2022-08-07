package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class UserNeedsBean {
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
        return this.datas;
    }

    public void setDatas(List<DemandDetailBean> datas) {
        this.datas = datas;
    }
}
