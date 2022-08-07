package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "TB_UserInfo")
/* loaded from: classes.dex */
public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.vsf2f.f2f.bean.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    private long accessTimestamp;
    private String accessToken;
    private String age;
    private PayStyleBean ali;
    private String aliAccount;
    private int certAlipay;
    private int certMobile;
    private int certQq;
    private int certRealname;
    private int certWechat;
    private int certZhima;
    private String content;
    private String countryCode;
    private long createdAt;
    private String createdAtStr;
    @Id(column = "dbId")
    private int dbId;
    private String email;
    private String gender;
    private String guid;
    private int id;
    private String idcard;
    private String ip;
    private int isVerify;
    private String lat;
    private String lng;
    private int lv;
    private String name;
    private String nickName;
    private String oauthResult;
    private String operatorId;
    private String password;
    private String payPassword;
    private String phone;
    private String picture;
    private PayStyleBean qq;
    private UserSellerBean seller;
    private UserShareBean share;
    private String sourceGuid;
    private String sourceName;
    private String sourceNickName;
    private String sourcePhone;
    private String sourceUser;
    private String status;
    private String sumInvestmentMoneyStr;
    private String sumProfitMoneyStr;
    private long updatedAt;
    private String updatedAtStr;
    private UserExtendInfo userExtendInfo;
    private String userName;
    private UserPicBean userPic;
    private int visible;
    private PayStyleBean wx;
    private int zmScore;

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

    public UserInfo() {
    }

    public long getAccessTimestamp() {
        return this.accessTimestamp;
    }

    public void setAccessTimestamp(long accessTimestamp) {
        this.accessTimestamp = accessTimestamp;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAliAccount() {
        return this.aliAccount;
    }

    public void setAliAccount(String aliAccount) {
        this.aliAccount = aliAccount;
    }

    public String getContetnt() {
        return this.content;
    }

    public void setContetnt(String contetnt) {
        this.content = contetnt;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtStr() {
        return this.createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public int getDbId() {
        return this.dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLv() {
        return this.lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getIsVerify() {
        return this.isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOperatorId() {
        return this.operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return this.payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public UserSellerBean getSeller() {
        return this.seller;
    }

    public void setSeller(UserSellerBean seller) {
        this.seller = seller;
    }

    public UserShareBean getShare() {
        return this.share;
    }

    public void setShare(UserShareBean share) {
        this.share = share;
    }

    public UserExtendInfo getUserExtendInfo() {
        return this.userExtendInfo;
    }

    public void setUserExtendInfo(UserExtendInfo userExtendInfo) {
        this.userExtendInfo = userExtendInfo;
    }

    public String getSourceGuid() {
        return this.sourceGuid;
    }

    public void setSourceGuid(String sourceGuid) {
        this.sourceGuid = sourceGuid;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUser() {
        return this.sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getSourceNickName() {
        return this.sourceNickName;
    }

    public void setSourceNickName(String sourceNickName) {
        this.sourceNickName = sourceNickName;
    }

    public String getSourcePhone() {
        return this.sourcePhone;
    }

    public void setSourcePhone(String sourcePhone) {
        this.sourcePhone = sourcePhone;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSumInvestmentMoneyStr() {
        return this.sumInvestmentMoneyStr;
    }

    public void setSumInvestmentMoneyStr(String sumInvestmentMoneyStr) {
        this.sumInvestmentMoneyStr = sumInvestmentMoneyStr;
    }

    public String getSumProfitMoneyStr() {
        return this.sumProfitMoneyStr;
    }

    public void setSumProfitMoneyStr(String sumProfitMoneyStr) {
        this.sumProfitMoneyStr = sumProfitMoneyStr;
    }

    public String getOauthResult() {
        return this.oauthResult;
    }

    public void setOauthResult(String oauthResult) {
        this.oauthResult = oauthResult;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAtStr() {
        return this.updatedAtStr;
    }

    public void setUpdatedAtStr(String updatedAtStr) {
        this.updatedAtStr = updatedAtStr;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisible() {
        return this.visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public PayStyleBean getAli() {
        return this.ali;
    }

    public void setAli(PayStyleBean ali) {
        this.ali = ali;
    }

    public PayStyleBean getWx() {
        return this.wx;
    }

    public void setWx(PayStyleBean wx) {
        this.wx = wx;
    }

    public PayStyleBean getQq() {
        return this.qq;
    }

    public void setQq(PayStyleBean qq) {
        this.qq = qq;
    }

    public String toString() {
        return "UserInfo{accessTimestamp=" + this.accessTimestamp + ", dbId=" + this.dbId + ", id=" + this.id + ", guid='" + this.guid + "', sourceGuid='" + this.sourceGuid + "', password='" + this.password + "', payPassword='" + this.payPassword + "', userName='" + this.userName + "', lv=" + this.lv + ", countryCode='" + this.countryCode + "', phone='" + this.phone + "', status='" + this.status + "', gender='" + this.gender + "', ip='" + this.ip + "', accessToken='" + this.accessToken + "', createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", createdAtStr='" + this.createdAtStr + "', updatedAtStr='" + this.updatedAtStr + "', sourceUser='" + this.sourceUser + "', sourceName='" + this.sourceName + "', sourcePhone='" + this.sourcePhone + "', sourceNickName='" + this.sourceNickName + "', sumInvestmentMoneyStr='" + this.sumInvestmentMoneyStr + "', sumProfitMoneyStr='" + this.sumProfitMoneyStr + "', share=" + this.share + ", seller=" + this.seller + ", userPic=" + this.userPic + ", picture='" + this.picture + "', content='" + this.content + "', name='" + this.name + "', idcard='" + this.idcard + "', operatorId='" + this.operatorId + "', nickName='" + this.nickName + "', age='" + this.age + "', email='" + this.email + "', aliAccount='" + this.aliAccount + "', ali=" + this.ali + ", wx=" + this.wx + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dbId);
        dest.writeInt(this.id);
        dest.writeInt(this.lv);
        dest.writeInt(this.isVerify);
        dest.writeString(this.guid);
        dest.writeString(this.password);
        dest.writeString(this.payPassword);
        dest.writeString(this.userName);
        dest.writeString(this.countryCode);
        dest.writeString(this.phone);
        dest.writeString(this.status);
        dest.writeString(this.gender);
        dest.writeString(this.ip);
        dest.writeString(this.accessToken);
        dest.writeString(this.createdAtStr);
        dest.writeString(this.updatedAtStr);
        dest.writeString(this.sourceUser);
        dest.writeString(this.sourceName);
        dest.writeString(this.sourceGuid);
        dest.writeString(this.sourcePhone);
        dest.writeString(this.sourceNickName);
        dest.writeString(this.sumInvestmentMoneyStr);
        dest.writeString(this.sumProfitMoneyStr);
        dest.writeString(this.oauthResult);
        dest.writeString(this.picture);
        dest.writeString(this.content);
        dest.writeString(this.name);
        dest.writeString(this.idcard);
        dest.writeString(this.operatorId);
        dest.writeString(this.nickName);
        dest.writeString(this.age);
        dest.writeString(this.email);
        dest.writeString(this.aliAccount);
        dest.writeLong(this.accessTimestamp);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.updatedAt);
        dest.writeInt(this.visible);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeParcelable(this.userPic, flags);
        dest.writeParcelable(this.share, flags);
        dest.writeParcelable(this.seller, flags);
        dest.writeParcelable(this.userExtendInfo, flags);
        dest.writeParcelable(this.ali, flags);
        dest.writeParcelable(this.wx, flags);
        dest.writeParcelable(this.qq, flags);
        dest.writeInt(this.certAlipay);
        dest.writeInt(this.certWechat);
        dest.writeInt(this.certQq);
        dest.writeInt(this.certMobile);
        dest.writeInt(this.certRealname);
        dest.writeInt(this.certZhima);
        dest.writeInt(this.zmScore);
    }

    protected UserInfo(Parcel in) {
        this.dbId = in.readInt();
        this.id = in.readInt();
        this.lv = in.readInt();
        this.isVerify = in.readInt();
        this.guid = in.readString();
        this.password = in.readString();
        this.payPassword = in.readString();
        this.userName = in.readString();
        this.countryCode = in.readString();
        this.phone = in.readString();
        this.status = in.readString();
        this.gender = in.readString();
        this.ip = in.readString();
        this.accessToken = in.readString();
        this.createdAtStr = in.readString();
        this.updatedAtStr = in.readString();
        this.sourceUser = in.readString();
        this.sourceName = in.readString();
        this.sourceGuid = in.readString();
        this.sourcePhone = in.readString();
        this.sourceNickName = in.readString();
        this.sumInvestmentMoneyStr = in.readString();
        this.sumProfitMoneyStr = in.readString();
        this.oauthResult = in.readString();
        this.picture = in.readString();
        this.content = in.readString();
        this.name = in.readString();
        this.idcard = in.readString();
        this.operatorId = in.readString();
        this.nickName = in.readString();
        this.age = in.readString();
        this.email = in.readString();
        this.aliAccount = in.readString();
        this.accessTimestamp = in.readLong();
        this.createdAt = in.readLong();
        this.updatedAt = in.readLong();
        this.visible = in.readInt();
        this.lat = in.readString();
        this.lng = in.readString();
        this.userPic = (UserPicBean) in.readParcelable(UserPicBean.class.getClassLoader());
        this.share = (UserShareBean) in.readParcelable(UserShareBean.class.getClassLoader());
        this.seller = (UserSellerBean) in.readParcelable(UserSellerBean.class.getClassLoader());
        this.userExtendInfo = (UserExtendInfo) in.readParcelable(UserExtendInfo.class.getClassLoader());
        this.ali = (PayStyleBean) in.readParcelable(PayStyleBean.class.getClassLoader());
        this.wx = (PayStyleBean) in.readParcelable(PayStyleBean.class.getClassLoader());
        this.qq = (PayStyleBean) in.readParcelable(PayStyleBean.class.getClassLoader());
        this.certAlipay = in.readInt();
        this.certWechat = in.readInt();
        this.certQq = in.readInt();
        this.certMobile = in.readInt();
        this.certRealname = in.readInt();
        this.certZhima = in.readInt();
        this.zmScore = in.readInt();
    }
}
