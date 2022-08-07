package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class SubscriptionMenuBean {
    private List<SubscriptionMenuBean> childs;
    private String name;
    private int type;
    private String urlCode;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrlCode() {
        return this.urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    public List<SubscriptionMenuBean> getChilds() {
        return this.childs;
    }

    public void setChilds(List<SubscriptionMenuBean> childs) {
        this.childs = childs;
    }
}
