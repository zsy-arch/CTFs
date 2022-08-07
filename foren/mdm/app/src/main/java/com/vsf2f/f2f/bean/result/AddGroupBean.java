package com.vsf2f.f2f.bean.result;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class AddGroupBean {
    private Map<String, String> failed;
    private Map<String, SeccuseBean> seccuse;

    public Map<String, SeccuseBean> getSeccuse() {
        if (this.seccuse == null) {
            this.seccuse = new HashMap();
        }
        return this.seccuse;
    }

    public void setSeccuse(Map<String, SeccuseBean> seccuse) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class SeccuseBean {
        private int customersId;
        private int isOwner;
        private int pictureId;
        private String userName;

        SeccuseBean() {
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getCustomersId() {
            return this.customersId;
        }

        public void setCustomersId(int customersId) {
            this.customersId = customersId;
        }

        public int getPictureId() {
            return this.pictureId;
        }

        public void setPictureId(int pictureId) {
            this.pictureId = pictureId;
        }

        public int getIsOwner() {
            return this.isOwner;
        }

        public void setIsOwner(int isOwner) {
            this.isOwner = isOwner;
        }
    }
}
