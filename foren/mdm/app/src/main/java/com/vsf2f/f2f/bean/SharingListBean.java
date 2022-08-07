package com.vsf2f.f2f.bean;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SharingListBean {
    private int lastId;
    private List<SharingRecordBean> tradeRecord;

    public int getLastId() {
        return this.lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public List<SharingRecordBean> getTradeRecord() {
        if (this.tradeRecord == null) {
            this.tradeRecord = new ArrayList();
        }
        return this.tradeRecord;
    }

    public void setTradeRecord(List<SharingRecordBean> tradeRecord) {
        this.tradeRecord = tradeRecord;
    }
}
