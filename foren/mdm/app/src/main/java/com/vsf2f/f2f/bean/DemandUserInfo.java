package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import com.vsf2f.f2f.bean.result.CertUserInfoBean;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class DemandUserInfo extends CertUserInfoBean implements Serializable {
    private int age;
    private String fromApp;
    private int id;
    private String lv;
    private HeaderUrl userPic;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLv() {
        return this.lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getFromApp() {
        return this.fromApp;
    }

    public void setFromApp(String fromApp) {
        this.fromApp = fromApp;
    }

    public HeaderUrl getUserPic() {
        if (this.userPic == null) {
            this.userPic = new HeaderUrl();
        }
        return this.userPic;
    }

    public void setUserPic(HeaderUrl userPic) {
        this.userPic = userPic;
    }

    /* loaded from: classes2.dex */
    public static class HeaderUrl implements Serializable {
        private String bpath;
        private String id;
        private String mpath;
        private String path;
        private String spath;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getSpath() {
            if (TextUtils.isEmpty(this.spath)) {
                this.spath = this.path;
                if (TextUtils.isEmpty(this.spath)) {
                    this.spath = this.bpath;
                }
            }
            return this.spath;
        }

        public void setSpath(String spath) {
            this.spath = spath;
        }

        public String getMpath() {
            return this.mpath;
        }

        public void setMpath(String mpath) {
            this.mpath = mpath;
        }

        public String getBpath() {
            return this.bpath;
        }

        public void setBpath(String bpath) {
            this.bpath = bpath;
        }
    }
}
