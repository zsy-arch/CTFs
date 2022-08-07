package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ConfigInfoBean implements Serializable {
    private String key;
    private List<ConfigInfoBean> obj;
    private String valType;
    private String value;
    private String version;

    public List<ConfigInfoBean> getObj() {
        if (this.obj == null) {
            this.obj = new ArrayList();
        }
        return this.obj;
    }

    public void setObj(List<ConfigInfoBean> obj) {
        this.obj = obj;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValType() {
        return this.valType;
    }

    public void setValType(String valType) {
        this.valType = valType;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
