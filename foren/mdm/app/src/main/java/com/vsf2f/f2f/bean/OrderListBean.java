package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OrderListBean implements Serializable {
    private List<OrderDetailBean> datas;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<OrderDetailBean> getDatas() {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        return this.datas;
    }

    public void setDatas(List<OrderDetailBean> datas) {
        this.datas = datas;
    }
}
