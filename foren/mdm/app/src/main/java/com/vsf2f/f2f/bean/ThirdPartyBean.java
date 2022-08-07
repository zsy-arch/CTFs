package com.vsf2f.f2f.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.cdlinglu.utils.ComUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ThirdPartyBean implements Parcelable {
    public static final Parcelable.Creator<ThirdPartyBean> CREATOR = new Parcelable.Creator<ThirdPartyBean>() { // from class: com.vsf2f.f2f.bean.ThirdPartyBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThirdPartyBean createFromParcel(Parcel in) {
            return new ThirdPartyBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThirdPartyBean[] newArray(int size) {
            return new ThirdPartyBean[size];
        }
    };
    private String authCode;
    private String nickName;
    private String openId;
    private int partyType;
    private String picUrl;
    private String unionID;
    private String userId;

    public String getUnionID() {
        return this.unionID;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public int getPartyType() {
        return this.partyType;
    }

    public void setPartyType(int partyType) {
        this.partyType = partyType;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    protected ThirdPartyBean(Parcel in) {
        this.openId = in.readString();
        this.nickName = in.readString();
        this.picUrl = in.readString();
        this.authCode = in.readString();
        this.userId = in.readString();
        this.unionID = in.readString();
        this.partyType = in.readInt();
    }

    public ThirdPartyBean() {
    }

    public String toString() {
        return "ShareThirdBean{openId='" + this.openId + "', partyType='" + this.partyType + "', nickName='" + this.nickName + "', picUrl='" + this.picUrl + "', userId='" + this.userId + "', authCode='" + this.authCode + "'}";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("openId", ComUtil.UTF(this.openId));
            json.put("userId", ComUtil.UTF(this.userId));
            json.put("authCode", ComUtil.UTF(this.authCode));
            json.put("nickName", ComUtil.UTF(this.nickName));
            json.put("partyType", this.partyType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.partyType);
        dest.writeString(this.openId);
        dest.writeString(this.nickName);
        dest.writeString(this.picUrl);
        dest.writeString(this.userId);
        dest.writeString(this.authCode);
        dest.writeString(this.unionID);
    }
}
