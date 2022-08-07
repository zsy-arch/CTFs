package com.vsf2f.f2f.bean.result;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class FriendNexusBean {
    private Map<String, String> failed;
    private Map<String, Boolean> seccuse;

    public Map<String, Boolean> getSeccuse() {
        if (this.seccuse == null) {
            this.seccuse = new HashMap();
        }
        return this.seccuse;
    }

    public void setSeccuse(Map<String, Boolean> seccuse) {
        this.seccuse = seccuse;
    }

    public Map<String, String> getFailed() {
        if (this.failed == null) {
            this.failed = new HashMap();
        }
        return this.failed;
    }

    public void setFailed(Map<String, String> failed) {
        this.failed = failed;
    }
}
