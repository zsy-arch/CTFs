package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class MyContactsBean implements Serializable {
    private String limit;
    private String pageIndex;
    private String results;
    private List<MyContactsRowsBean> rows;
    private String start;
    private String totalPage;

    public String getLimit() {
        return this.limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getResults() {
        return this.results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public List<MyContactsRowsBean> getRows() {
        return this.rows;
    }

    public void setRows(List<MyContactsRowsBean> rows) {
        this.rows = rows;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }
}
