package com.vsf2f.f2f.bean.result;

import com.vsf2f.f2f.bean.FriendInfoBean;
import java.util.Map;

/* loaded from: classes2.dex */
public class AddFriendBean {
    private Map<String, String> failed;
    private Map<String, FriendInfoBean> seccuse;

    public Map<String, FriendInfoBean> getSeccuse() {
        return this.seccuse;
    }

    public void setSeccuse(Map<String, FriendInfoBean> seccuse) {
        this.seccuse = seccuse;
    }

    public Map<String, String> getFailed() {
        return this.failed;
    }

    public void setFailed(Map<String, String> failed) {
        this.failed = failed;
    }
}
