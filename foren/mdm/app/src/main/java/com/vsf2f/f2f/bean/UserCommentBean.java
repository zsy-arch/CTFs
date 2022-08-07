package com.vsf2f.f2f.bean;

import android.text.TextUtils;
import com.vsf2f.f2f.R;
import java.util.List;

/* loaded from: classes2.dex */
public class UserCommentBean {
    private List<DatasBean> datas;
    private int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DatasBean> getDatas() {
        return this.datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    /* loaded from: classes2.dex */
    public static class DatasBean {
        private int bizId;
        private int bizType;
        private String createTime;
        private String creator;
        private CreatorObjBean creatorObj;
        private String description;
        private String imgList;
        private List<String> imgUrlList;
        private double level;
        private int moId;
        private int orderId;
        private String receiver;
        private CreatorObjBean receiverObj;

        public int getBizId() {
            return this.bizId;
        }

        public void setBizId(int bizId) {
            this.bizId = bizId;
        }

        public int getBizTypeStrId() {
            return this.bizType == 0 ? R.string.evaluate_service : R.string.evaluate_demand;
        }

        public int getBizType() {
            return this.bizType;
        }

        public void setBizType(int bizType) {
            this.bizType = bizType;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreator() {
            return this.creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreatorNick() {
            return (this.creatorObj == null || TextUtils.isEmpty(this.creatorObj.getNickName())) ? this.creator : this.creatorObj.getNickName();
        }

        public String getReceiverNick() {
            return (this.receiverObj == null || TextUtils.isEmpty(this.receiverObj.getNickName())) ? this.receiver : this.receiverObj.getNickName();
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImgList() {
            return this.imgList;
        }

        public void setImgList(String imgList) {
            this.imgList = imgList;
        }

        public double getLevel() {
            return this.level;
        }

        public void setLevel(double level) {
            this.level = level;
        }

        public int getMoId() {
            return this.moId;
        }

        public void setMoId(int moId) {
            this.moId = moId;
        }

        public int getOrderId() {
            return this.orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getReceiver() {
            return this.receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public CreatorObjBean getCreatorObj() {
            if (this.creatorObj == null) {
                this.creatorObj = new CreatorObjBean();
            }
            return this.creatorObj;
        }

        public void setCreatorObj(CreatorObjBean creatorObj) {
            this.creatorObj = creatorObj;
        }

        public CreatorObjBean getReceiverObj() {
            return this.receiverObj;
        }

        public void setReceiverObj(CreatorObjBean receiverObj) {
            this.receiverObj = receiverObj;
        }

        public List<String> getImgUrlList() {
            return this.imgUrlList;
        }

        public void setImgUrlList(List<String> imgUrlList) {
            this.imgUrlList = imgUrlList;
        }

        /* loaded from: classes2.dex */
        public static class CreatorObjBean {
            private String lv;
            private String nickName;
            private UserPicBean userPic;

            public String getLv() {
                return this.lv;
            }

            public void setLv(String lv) {
                this.lv = lv;
            }

            public String getNickName() {
                return this.nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
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
            public static class UserPicBean {
                private String path;
                private String spath;

                public String getPath() {
                    return this.path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getSpath() {
                    return this.spath;
                }

                public void setSpath(String spath) {
                    this.spath = spath;
                }
            }
        }
    }
}
