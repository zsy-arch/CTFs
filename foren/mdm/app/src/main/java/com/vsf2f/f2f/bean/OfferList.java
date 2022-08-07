package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class OfferList implements Serializable {
    private String createTime;
    private String description;
    private int moId;
    private String reward;
    private String serviceProvider;
    private ServiceUserObjBean serviceUserObj;
    private int shareId;
    private int shareSnapshotId;
    private int status;

    public int getMoId() {
        return this.moId;
    }

    public void setMoId(int id) {
        this.moId = id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShareId() {
        return this.shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getShareSnapshotId() {
        return this.shareSnapshotId;
    }

    public void setShareSnapshotId(int shareSnapshotId) {
        this.shareSnapshotId = shareSnapshotId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsg() {
        if (TextUtils.isEmpty(this.description)) {
            this.description = "";
        }
        return this.description;
    }

    public void setMsg(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReward() {
        return this.reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getServiceProvider() {
        return this.serviceProvider;
    }

    public ServiceUserObjBean getServiceUserObj() {
        if (this.serviceUserObj == null) {
            this.serviceUserObj = new ServiceUserObjBean();
        }
        return this.serviceUserObj;
    }

    public void setServiceUserObj(ServiceUserObjBean serviceUserObj) {
        this.serviceUserObj = serviceUserObj;
    }

    public void setServiceProviderObj(ServiceUserObjBean serviceProviderObj) {
        this.serviceUserObj = serviceProviderObj;
    }

    /* loaded from: classes2.dex */
    public static class ServiceUserObjBean implements Serializable {
        private int certAlipay;
        private int certMobile;
        private int certQq;
        private int certRealname;
        private int certWechat;
        private int certZhima;
        private String fromApp;
        private String gender;
        private int id;
        private String nickName;
        private String userName;
        private UserPicBean userPic;
        private int zmScore;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            if (TextUtils.isEmpty(this.nickName)) {
                this.nickName = this.userName;
                if (TextUtils.isEmpty(this.userName)) {
                    this.nickName = "无服务商";
                }
            }
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFromApp() {
            return this.fromApp;
        }

        public void setFromApp(String fromApp) {
            this.fromApp = fromApp;
        }

        public String getGender() {
            return this.gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getCertAlipay() {
            return this.certAlipay;
        }

        public void setCertAlipay(int certAlipay) {
            this.certAlipay = certAlipay;
        }

        public int getCertWechat() {
            return this.certWechat;
        }

        public void setCertWechat(int certWechat) {
            this.certWechat = certWechat;
        }

        public int getCertQq() {
            return this.certQq;
        }

        public void setCertQq(int certQq) {
            this.certQq = certQq;
        }

        public int getCertMobile() {
            return this.certMobile;
        }

        public void setCertMobile(int certMobile) {
            this.certMobile = certMobile;
        }

        public int getCertRealname() {
            return this.certRealname;
        }

        public void setCertRealname(int certRealname) {
            this.certRealname = certRealname;
        }

        public int getCertZhima() {
            return this.certZhima;
        }

        public void setCertZhima(int certZhima) {
            this.certZhima = certZhima;
        }

        public int getZmScore() {
            return this.zmScore;
        }

        public void setZmScore(int zmScore) {
            this.zmScore = zmScore;
        }

        public UserPicBean getUserPic() {
            if (this.userPic == null) {
                this.userPic = new UserPicBean();
            }
            return this.userPic;
        }

        public void setUserPic(UserPicBean userPic) {
            this.userPic = userPic;
        }

        /* loaded from: classes2.dex */
        public static class UserPicBean implements Serializable {
            private String bpath;
            private String bucket;
            private String etag;
            private int id;
            private String mpath;
            private String path;
            private String spath;
            private String userName;

            public String getMpath() {
                return this.mpath;
            }

            public void setMpath(String mpath) {
                this.mpath = mpath;
            }

            public String getSpath() {
                return this.spath;
            }

            public void setSpath(String spath) {
                this.spath = spath;
            }

            public String getBpath() {
                return this.bpath;
            }

            public void setBpath(String bpath) {
                this.bpath = bpath;
            }

            public String getBucket() {
                return this.bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public String getEtag() {
                return this.etag;
            }

            public void setEtag(String etag) {
                this.etag = etag;
            }

            public int getId() {
                return this.id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getUserName() {
                return this.userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
