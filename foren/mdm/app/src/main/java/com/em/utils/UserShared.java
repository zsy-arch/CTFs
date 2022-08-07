package com.em.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.em.db.UserDao;
import com.em.ui.EditActivity;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PayStyleBean;
import com.vsf2f.f2f.bean.SharingBean;
import com.vsf2f.f2f.bean.UserExtendInfo;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.bean.UserPicBean;
import com.vsf2f.f2f.bean.UserShareBean;
import com.vsf2f.f2f.ui.dialog.ActivateDialog;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.Map;

/* loaded from: classes.dex */
public class UserShared {
    public static final String PREFERENCE_NAME = "user_f2f";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences mSharedPreferences;
    private static UserShared mUserShared;
    private static String SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE = "shared_key_setting_chatroom_owner_leave";
    private static String SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP = "shared_key_setting_delete_messages_when_exit_group";
    private static String SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION = "shared_key_setting_auto_accept_group_invitation";
    private static String SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE = "shared_key_setting_adaptive_video_encode";
    private static String SHARED_KEY_SETTING_OFFLINE_PUSH_CALL = "shared_key_setting_offline_push_call";
    private static String SHARED_KEY_SETTING_GROUPS_SYNCED = "SHARED_KEY_SETTING_GROUPS_SYNCED";
    private static String SHARED_KEY_SETTING_CONTACT_SYNCED = "SHARED_KEY_SETTING_CONTACT_SYNCED";
    private static String SHARED_KEY_SETTING_BALCKLIST_SYNCED = "SHARED_KEY_SETTING_BALCKLIST_SYNCED";
    private static String SHARED_KEY_CURRENTUSER_USERNAME = "SHARED_KEY_CURRENTUSER_USERNAME";
    private static String SHARED_KEY_CURRENTUSER_NICK = "SHARED_KEY_CURRENTUSER_NICK";
    private static String SHARED_KEY_CURRENTUSER_AVATAR = "SHARED_KEY_CURRENTUSER_AVATAR";
    private static String SHARED_KEY_REST_SERVER = "SHARED_KEY_REST_SERVER";
    private static String SHARED_KEY_IM_SERVER = "SHARED_KEY_IM_SERVER";
    private static String SHARED_KEY_ENABLE_CUSTOM_SERVER = "SHARED_KEY_ENABLE_CUSTOM_SERVER";
    private static String SHARED_KEY_CALL_MIN_VIDEO_KBPS = "SHARED_KEY_CALL_MIN_VIDEO_KBPS";
    private static String SHARED_KEY_CALL_MAX_VIDEO_KBPS = "SHARED_KEY_CALL_Max_VIDEO_KBPS";
    private static String SHARED_KEY_CALL_MAX_FRAME_RATE = "SHARED_KEY_CALL_MAX_FRAME_RATE";
    private static String SHARED_KEY_CALL_AUDIO_SAMPLE_RATE = "SHARED_KEY_CALL_AUDIO_SAMPLE_RATE";
    private static String SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION = "SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION";
    private static String SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION = "SHARED_KEY_FRONT_CAMERA_RESOLUTIOIN";
    private static String SHARED_KEY_CALL_FIX_SAMPLE_RATE = "SHARED_KEY_CALL_FIX_SAMPLE_RATE";
    private String SHARED_KEY_SETTING_NOTIFICATION = "shared_key_setting_notification";
    private String SHARED_KEY_SETTING_SOUND = "shared_key_setting_sound";
    private String SHARED_KEY_SETTING_SOUND_GROUP = "shared_key_setting_sound_group";
    private String SHARED_KEY_SETTING_VIBRATE = "shared_key_setting_vibrate";
    private String SHARED_KEY_SETTING_SPEAKER = "shared_key_setting_speaker";

    @SuppressLint({"CommitPrefEdits"})
    private UserShared(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, 0);
        editor = mSharedPreferences.edit();
    }

    public static synchronized void init(Context cxt) {
        synchronized (UserShared.class) {
            if (mUserShared == null) {
                mUserShared = new UserShared(cxt);
            }
        }
    }

    public static synchronized UserShared getInstance() {
        UserShared userShared;
        synchronized (UserShared.class) {
            if (mUserShared == null) {
                throw new RuntimeException("please init PreferenceManager first!");
            }
            userShared = mUserShared;
        }
        return userShared;
    }

    public UserInfo readUserInfo(boolean userPic, boolean thirdParty) {
        UserInfo user = new UserInfo();
        user.setId(mSharedPreferences.getInt("id", 0));
        user.setLv(mSharedPreferences.getInt("lv", 0));
        user.setIsVerify(mSharedPreferences.getInt("isVerify", 1));
        user.setGuid(mSharedPreferences.getString("guid", null));
        user.setSourceGuid(mSharedPreferences.getString("sourceGuid", null));
        user.setSourceName(mSharedPreferences.getString("sourceName", null));
        user.setSourcePhone(mSharedPreferences.getString("sourcePhone", null));
        user.setPassword(mSharedPreferences.getString(Constant.PASSWORD, null));
        user.setPhone(mSharedPreferences.getString("phone", null));
        user.setUserName(mSharedPreferences.getString("userName", null));
        user.setName(mSharedPreferences.getString("name", null));
        user.setNickName(mSharedPreferences.getString("nickName", null));
        user.setEmail(mSharedPreferences.getString(NotificationCompat.CATEGORY_EMAIL, null));
        user.setCountryCode(mSharedPreferences.getString("countryCode", null));
        user.setIdcard(mSharedPreferences.getString("idcard", null));
        user.setGender(mSharedPreferences.getString(UserDao.CONTACT_COLUMN_NAME_GENDER, null));
        user.setAge(mSharedPreferences.getString("age", null));
        user.setContetnt(mSharedPreferences.getString(EditActivity.CONTENT, null));
        user.setStatus(mSharedPreferences.getString("status", null));
        user.setCreatedAt(mSharedPreferences.getLong("createdAt", 0L));
        user.setUpdatedAt(mSharedPreferences.getLong("updateat", 0L));
        user.setVisible(mSharedPreferences.getInt("visible", 0));
        user.setLat(mSharedPreferences.getString("lat", null));
        user.setLng(mSharedPreferences.getString("lng", null));
        if (userPic) {
            user.setUserPic(readUserPic());
        }
        if (thirdParty) {
            return readThirdParty(user);
        }
        return user;
    }

    public UserPicBean readUserPic() {
        UserPicBean userPic = new UserPicBean();
        userPic.setPath(mSharedPreferences.getString("userPicPath", null));
        userPic.setSpath(mSharedPreferences.getString("userPicSpath", null));
        return userPic;
    }

    public UserExtendInfo readExtInfo() {
        UserExtendInfo userExt = new UserExtendInfo();
        userExt.setIsProxy(mSharedPreferences.getInt("extisProxy", 0));
        userExt.setPaypwdVersion(mSharedPreferences.getInt("paypwdVersion", 0));
        userExt.setCertCompany(mSharedPreferences.getInt("extCertCompany", 0));
        userExt.setShareMoney(mSharedPreferences.getInt("extShareMoney", 0));
        userExt.setIsShareholders(mSharedPreferences.getInt("extisShareholders", 0));
        userExt.setShareMoneyEnable(mSharedPreferences.getInt("extShareMoneyEnable", 0));
        userExt.setShareMoneyFrozen(mSharedPreferences.getInt("extShareMoneyFrozen", 0));
        return userExt;
    }

    public void savePaypwdVersion() {
        editor.putInt("paypwdVersion", 2);
        editor.apply();
    }

    public void saveShareMoney(SharingBean sharing) {
        editor.putInt("extShareMoney", sharing.getShareMoney());
        editor.putInt("extShareMoneyEnable", sharing.getShareMoneyEnable());
        editor.putInt("extShareMoneyDisable", sharing.getShareMoneyDisable());
        editor.putInt("extShareMoneyFrozen", sharing.getShareMoneyFrozen());
        editor.putInt("extShareMoneyUnFrozen", sharing.getShareMoneyUnFrozen());
        editor.apply();
    }

    public SharingBean readShareMoney() {
        SharingBean userExt = new SharingBean();
        userExt.setShareMoney(mSharedPreferences.getInt("extShareMoney", 0));
        userExt.setShareMoneyEnable(mSharedPreferences.getInt("extShareMoneyEnable", 0));
        userExt.setShareMoneyDisable(mSharedPreferences.getInt("extShareMoneyDisable", 0));
        userExt.setShareMoneyFrozen(mSharedPreferences.getInt("extShareMoneyFrozen", 0));
        userExt.setShareMoneyUnFrozen(mSharedPreferences.getInt("extShareMoneyUnFrozen", 0));
        return userExt;
    }

    public boolean checkThirdParty() {
        return mSharedPreferences.getBoolean("saveThirdParty", false);
    }

    public UserInfo readThirdParty(UserInfo user) {
        if (user == null) {
            user = new UserInfo();
        }
        if (mSharedPreferences.getString("QqNickName", null) != null) {
            user.setQq(new PayStyleBean(mSharedPreferences.getString("QqNickName", null)));
        }
        if (mSharedPreferences.getString("WxNickName", null) != null) {
            user.setWx(new PayStyleBean(mSharedPreferences.getString("WxNickName", null)));
        }
        if (mSharedPreferences.getString("AliNickName", null) != null) {
            user.setAli(new PayStyleBean(mSharedPreferences.getString("AliNickName", null)));
        }
        return user;
    }

    public void saveThirdParty(UserInfo user) {
        editor.putBoolean("saveThirdParty", true);
        if (user.getQq() != null && !TextUtils.isEmpty(user.getQq().getBankNumber())) {
            editor.putString("QqNickName", TextUtils.isEmpty(user.getQq().getBankNumber()) ? "已绑定" : user.getQq().getBankNickName());
        }
        if (user.getWx() != null && !TextUtils.isEmpty(user.getWx().getBankNumber())) {
            editor.putString("WxNickName", TextUtils.isEmpty(user.getWx().getBankNumber()) ? "已绑定" : user.getWx().getBankNickName());
        }
        if (user.getAli() != null && !TextUtils.isEmpty(user.getAli().getBankNumber())) {
            editor.putString("AliNickName", TextUtils.isEmpty(user.getAli().getBankNumber()) ? "已绑定" : user.getAli().getBankNickName());
        }
        editor.apply();
    }

    public void saveThirdParty(int type, PayStyleBean bind) {
        if (bind != null) {
            editor.putBoolean("saveThirdParty", true);
            switch (type) {
                case 1:
                    editor.putString("QqNickName", bind.getBankNickName());
                    break;
                case 2:
                    editor.putString("WxNickName", bind.getBankNickName());
                    break;
                case 4:
                    editor.putString("AliNickName", bind.getBankNickName());
                    break;
            }
            editor.apply();
        }
    }

    public UserShareBean readShare() {
        UserShareBean userShare = new UserShareBean();
        userShare.setUserName(mSharedPreferences.getString("shareChat", null));
        userShare.setShareType(mSharedPreferences.getString("shareType", null));
        userShare.setShareIcon(mSharedPreferences.getString("shareIcon", null));
        userShare.setShareName(mSharedPreferences.getString("shareName", null));
        userShare.setShareHref(mSharedPreferences.getString("shareHref", null));
        return userShare;
    }

    public String readPrevList() {
        return mSharedPreferences.getString("prevJson", null);
    }

    public void savePrevList(String prevJson) {
        editor.putString("prevJson", prevJson);
        editor.apply();
    }

    public void exit() {
        editor.clear();
    }

    public void saveAll(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.apply();
    }

    public void save(UserInfo user) {
        editor.putString("accessToken", user.getAccessToken());
        editor.putInt("id", user.getId());
        editor.putInt("lv", user.getLv());
        editor.putInt("isVerify", user.getIsVerify());
        editor.putString("guid", user.getGuid());
        editor.putString("sourceGuid", user.getSourceGuid());
        if (!TextUtils.isEmpty(user.getSourceName())) {
            editor.putString("sourceName", user.getSourceName());
        }
        if (!TextUtils.isEmpty(user.getSourcePhone())) {
            editor.putString("sourcePhone", user.getSourcePhone());
        }
        editor.putString(Constant.PASSWORD, user.getPassword());
        editor.putString("phone", user.getPhone());
        editor.putString("userName", user.getUserName());
        editor.putString("name", user.getName());
        editor.putString("nickName", user.getNickName());
        editor.putString(NotificationCompat.CATEGORY_EMAIL, user.getEmail());
        editor.putString("countryCode", user.getCountryCode());
        editor.putString("idcard", user.getIdcard());
        editor.putString(UserDao.CONTACT_COLUMN_NAME_GENDER, user.getGender());
        editor.putString("age", user.getAge());
        editor.putString(EditActivity.CONTENT, user.getContetnt());
        editor.putString("status", user.getStatus());
        editor.putLong("createdAt", user.getCreatedAt());
        editor.putLong("updateat", user.getUpdatedAt());
        editor.putInt("visible", user.getVisible());
        editor.putString("lat", user.getLat());
        editor.putString("lng", user.getLng());
        if (user.getZmScore() != 0) {
            editor.putInt(Constant.ZHIMACODE, user.getZmScore());
        }
        editor.putInt(Constant.CERT_ZHIMA, user.getCertZhima());
        editor.putInt(Constant.CERT_REALNAME, user.getCertRealname());
        editor.putInt(Constant.CERT_MOBILE, user.getCertMobile());
        editor.putInt(Constant.CERT_ALIPAY, user.getCertAlipay());
        editor.putInt(Constant.CERT_WECHAT, user.getCertWechat());
        editor.putInt(Constant.CERT_QQ, user.getCertQq());
        if (user.getUserPic().getSpath() != null) {
            editor.putString("userPicSpath", user.getUserPic().getSpath());
            editor.putString("userPicPath", user.getUserPic().getPath());
        }
        if (user.getUserExtendInfo() != null) {
            editor.putBoolean("openManor", user.getUserExtendInfo().isOpenManor());
            editor.putInt("extisProxy", user.getUserExtendInfo().getIsProxy());
            editor.putInt("extCertCompany", user.getUserExtendInfo().getCertCompany());
            editor.putInt("extisShareholders", user.getUserExtendInfo().getIsShareholders());
            editor.putInt("extShareMoney", user.getUserExtendInfo().getShareMoney());
            editor.putInt("extShareMoneyEnable", user.getUserExtendInfo().getShareMoneyEnable());
            editor.putInt("extShareMoneyFrozen", user.getUserExtendInfo().getShareMoneyFrozen());
            editor.putInt("paypwdVersion", user.getUserExtendInfo().getPaypwdVersion());
        }
        if (user.getShare() != null) {
            editor.putString("shareChat", user.getShare().getUserName());
            editor.putString("shareType", user.getShare().getShareType());
            editor.putString("shareName", user.getShare().getShareName());
            editor.putString("shareIcon", user.getShare().getShareIcon());
            editor.putString("shareHref", user.getShare().getShareHref());
        }
        editor.apply();
        Log.i("currentUser", user.toString());
    }

    public int getIsVerify() {
        return mSharedPreferences.getInt("isVerify", 1);
    }

    public boolean getIsVerifyState(Context context) {
        switch (getIsVerify()) {
            case -1:
                MyToast.show(context, (int) R.string.activate_in_prompt);
                break;
            case 0:
                new ActivateDialog(context).show();
                break;
            case 1:
                return true;
        }
        return false;
    }

    public void setIsVerify(int isVerify) {
        editor.putInt("isVerify", isVerify);
        editor.apply();
    }

    public int isOpenStore() {
        return mSharedPreferences.getInt("isOpenStore", 0);
    }

    public void setOpenStore(int openStore) {
        editor.putInt("isOpenStore", openStore);
        editor.apply();
    }

    public boolean isOpenManor() {
        return mSharedPreferences.getBoolean("openManor", false);
    }

    public void setOpenManor(boolean isOpen) {
        editor.putBoolean("openManor", isOpen);
        editor.apply();
    }

    public int getStoreGoodNum() {
        return mSharedPreferences.getInt("storeGoodNum", 0);
    }

    public void setStoreGoodNum(int num) {
        editor.putInt("storeGoodNum", num);
        editor.apply();
    }

    public void setOpenVip(int lv) {
        editor.putInt("lv", lv);
        editor.apply();
    }

    public void save(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void save(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String str) {
        return mSharedPreferences.getString(str, "");
    }

    public String getStr(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public String getUserRealName() {
        return mSharedPreferences.getString("name", null);
    }

    public int getCurrentUserId() {
        return mSharedPreferences.getInt("id", 0);
    }

    public String getCurrentGuId() {
        return mSharedPreferences.getString("guid", "");
    }

    public String getCurrentUsername() {
        return mSharedPreferences.getString("userName", null);
    }

    public String getCurrentUserNick() {
        return mSharedPreferences.getString("nickName", null);
    }

    public String getCurrentUserAvatar() {
        return mSharedPreferences.getString("userPicSpath", null);
    }

    public void setCurrentUserNick(String nick) {
        editor.putString("nickName", nick);
        editor.apply();
    }

    public void setSettingMsgNotification(boolean paramBoolean) {
        editor.putBoolean(this.SHARED_KEY_SETTING_NOTIFICATION, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgNotification() {
        return mSharedPreferences.getBoolean(this.SHARED_KEY_SETTING_NOTIFICATION, true);
    }

    public void setSettingMsgSound(boolean paramBoolean) {
        editor.putBoolean(this.SHARED_KEY_SETTING_SOUND, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgSound() {
        return mSharedPreferences.getBoolean(this.SHARED_KEY_SETTING_SOUND, true);
    }

    public void setSettingGroupMsgSound(boolean paramBoolean) {
        editor.putBoolean(this.SHARED_KEY_SETTING_SOUND_GROUP, paramBoolean);
        editor.apply();
    }

    public boolean getSettingGroupMsgSound() {
        return mSharedPreferences.getBoolean(this.SHARED_KEY_SETTING_SOUND_GROUP, false);
    }

    public void setSettingMsgVibrate(boolean paramBoolean) {
        editor.putBoolean(this.SHARED_KEY_SETTING_VIBRATE, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgVibrate() {
        return mSharedPreferences.getBoolean(this.SHARED_KEY_SETTING_VIBRATE, true);
    }

    public void setSettingMsgSpeaker(boolean paramBoolean) {
        editor.putBoolean(this.SHARED_KEY_SETTING_SPEAKER, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgSpeaker() {
        return mSharedPreferences.getBoolean(this.SHARED_KEY_SETTING_SPEAKER, true);
    }

    public void setSettingAllowChatroomOwnerLeave(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, value);
        editor.apply();
    }

    public boolean getSettingAllowChatroomOwnerLeave() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, true);
    }

    public void setDeleteMessagesAsExitGroup(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP, value);
        editor.apply();
    }

    public boolean isDeleteMessagesAsExitGroup() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP, true);
    }

    public void setAutoAcceptGroupInvitation(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION, value);
        editor.apply();
    }

    public boolean isAutoAcceptGroupInvitation() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION, true);
    }

    public void setAdaptiveVideoEncode(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, value);
        editor.apply();
    }

    public boolean isAdaptiveVideoEncode() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, false);
    }

    public void setGroupsSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, synced);
        editor.apply();
    }

    public void setPushCall(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, value);
        editor.apply();
    }

    public boolean isPushCall() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, false);
    }

    public boolean isGroupsSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, false);
    }

    public void setContactSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, synced);
        editor.apply();
    }

    public boolean isContactSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, false);
    }

    public void setBlacklistSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, synced);
        editor.apply();
    }

    public boolean isBacklistSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, false);
    }

    public void setRestServer(String restServer) {
        editor.putString(SHARED_KEY_REST_SERVER, restServer).commit();
        editor.apply();
    }

    public String getRestServer() {
        return mSharedPreferences.getString(SHARED_KEY_REST_SERVER, null);
    }

    public void setIMServer(String imServer) {
        editor.putString(SHARED_KEY_IM_SERVER, imServer);
        editor.apply();
    }

    public String getIMServer() {
        return mSharedPreferences.getString(SHARED_KEY_IM_SERVER, null);
    }

    public void enableCustomServer(boolean enable) {
        editor.putBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, enable);
        editor.apply();
    }

    public boolean isCustomServerEnable() {
        return mSharedPreferences.getBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, false);
    }

    public int getCallMinVideoKbps() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, -1);
    }

    public void setCallMinVideoKbps(int minBitRate) {
        editor.putInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, minBitRate);
        editor.apply();
    }

    public int getCallMaxVideoKbps() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, -1);
    }

    public void setCallMaxVideoKbps(int maxBitRate) {
        editor.putInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, maxBitRate);
        editor.apply();
    }

    public int getCallMaxFrameRate() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MAX_FRAME_RATE, -1);
    }

    public void setCallMaxFrameRate(int maxFrameRate) {
        editor.putInt(SHARED_KEY_CALL_MAX_FRAME_RATE, maxFrameRate);
        editor.apply();
    }

    public int getCallAudioSampleRate() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, -1);
    }

    public void setCallAudioSampleRate(int audioSampleRate) {
        editor.putInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, audioSampleRate);
        editor.apply();
    }

    public String getCallBackCameraResolution() {
        return mSharedPreferences.getString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, "");
    }

    public void setCallBackCameraResolution(String resolution) {
        editor.putString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, resolution);
        editor.apply();
    }

    public String getCallFrontCameraResolution() {
        return mSharedPreferences.getString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, "");
    }

    public void setCallFrontCameraResolution(String resolution) {
        editor.putString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, resolution);
        editor.apply();
    }

    public boolean isCallFixedVideoResolution() {
        return mSharedPreferences.getBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, false);
    }

    public void setCallFixedVideoResolution(boolean enable) {
        editor.putBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, enable);
        editor.apply();
    }
}
