package com.em;

import android.content.Context;
import com.easeui.domain.EaseUser;
import com.easeui.model.EaseAtMessageHelper;
import com.em.db.UserDao;
import com.em.domain.RobotUser;
import com.em.utils.UserShared;
import com.vsf2f.f2f.bean.AccountBean;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.PhoneBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.bean.UserPicBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DemoModel {
    protected Context context;
    private UserDao dao = null;
    protected Map<Key, Object> valueCache = new HashMap();

    /* loaded from: classes.dex */
    public enum Key {
        VibrateAndPlayToneOn,
        VibrateOn,
        PlayToneOn,
        PlayGroupOn,
        SpakerOn,
        DisabledGroups,
        DisabledIds
    }

    public DemoModel(Context context) {
        this.context = null;
        this.context = context;
        UserShared.init(context);
    }

    public void saveCurrentUserInfo(UserInfo userInfo) {
        UserShared.getInstance().save(userInfo);
    }

    public UserInfo getCurrentUserInfo(boolean userPic, boolean thirdParty) {
        return UserShared.getInstance().readUserInfo(userPic, thirdParty);
    }

    public UserPicBean getCurrentUserPic() {
        return UserShared.getInstance().readUserPic();
    }

    public int getCurrentUserId() {
        return UserShared.getInstance().getCurrentUserId();
    }

    public String getCurrentUserName() {
        return UserShared.getInstance().getCurrentUsername();
    }

    public String getCurrentUserNick() {
        return UserShared.getInstance().getCurrentUserNick();
    }

    public void setCurrentUserNick(String userNick) {
        UserShared.getInstance().setCurrentUserNick(userNick);
    }

    public int isOpenStore() {
        return UserShared.getInstance().isOpenStore();
    }

    public void setOpenStore(int openStore) {
        UserShared.getInstance().setOpenStore(openStore);
    }

    public void exitShared() {
        UserShared.getInstance().exit();
    }

    public boolean updateContactList(List<EaseUser> contactList) {
        new UserDao(this.context).updateContactList(contactList);
        return true;
    }

    public Map<String, EaseUser> getContactList() {
        return new UserDao(this.context).getContactList();
    }

    public void saveContact(EaseUser user) {
        new UserDao(this.context).saveContact(user);
    }

    public void deleteContact(String username) {
        new UserDao(this.context).deleteContact(username);
    }

    public boolean saveUsersList(List<EaseUser> userList) {
        new UserDao(this.context).saveUsersList(userList);
        return true;
    }

    public Map<String, EaseUser> getUsersList() {
        return new UserDao(this.context).getUsersList();
    }

    public void saveUser(EaseUser user) {
        new UserDao(this.context).saveUser(user);
    }

    public void deleteUser(String username) {
        new UserDao(this.context).deleteUser(username);
    }

    public boolean saveGroupsList(List<GroupBean> list) {
        new UserDao(this.context).saveGroupsList(list);
        return true;
    }

    public Map<String, GroupBean> getGroupsList() {
        return new UserDao(this.context).getGroupsList();
    }

    public void saveGroup(GroupBean group) {
        new UserDao(this.context).saveGroup(group);
    }

    public void deleteGroup(String groupId) {
        new UserDao(this.context).deleteGroup(groupId);
    }

    public Map<String, RobotUser> getRobotList() {
        return new UserDao(this.context).getRobotUser();
    }

    public boolean saveRobotList(List<RobotUser> robotList) {
        new UserDao(this.context).saveRobotUser(robotList);
        return true;
    }

    public boolean savePhonesList(List<PhoneBean> list) {
        new UserDao(this.context).savePhonesList(list);
        return true;
    }

    public Map<String, PhoneBean> getPhonesList() {
        return new UserDao(this.context).getPhonesList();
    }

    public Map<String, AccountBean> getAccountList() {
        return new UserDao(this.context).getAccountList();
    }

    public void saveAccount(AccountBean account) {
        new UserDao(this.context).saveAccount(account);
    }

    public void deleteAccount(String account) {
        new UserDao(this.context).deleteAccount(account);
    }

    public void setSettingMsgNotification(boolean paramBoolean) {
        UserShared.getInstance().setSettingMsgNotification(paramBoolean);
        this.valueCache.put(Key.VibrateAndPlayToneOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingMsgNotification() {
        Object val = this.valueCache.get(Key.VibrateAndPlayToneOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingMsgNotification());
            this.valueCache.put(Key.VibrateAndPlayToneOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setSettingMsgGroupNotify(boolean paramBoolean) {
        UserShared.getInstance().setSettingMsgNotification(paramBoolean);
        this.valueCache.put(Key.VibrateAndPlayToneOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingMsgGroupNotify() {
        Object val = this.valueCache.get(Key.VibrateAndPlayToneOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingMsgNotification());
            this.valueCache.put(Key.VibrateAndPlayToneOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setSettingMsgSound(boolean paramBoolean) {
        UserShared.getInstance().setSettingMsgSound(paramBoolean);
        this.valueCache.put(Key.PlayToneOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingMsgSound() {
        Object val = this.valueCache.get(Key.PlayToneOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingMsgSound());
            this.valueCache.put(Key.PlayToneOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setSettingGroupMsgSound(boolean paramBoolean) {
        UserShared.getInstance().setSettingGroupMsgSound(paramBoolean);
        this.valueCache.put(Key.PlayGroupOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingGroupMsgSound() {
        Object val = this.valueCache.get(Key.PlayGroupOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingGroupMsgSound());
            this.valueCache.put(Key.PlayGroupOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setSettingMsgVibrate(boolean paramBoolean) {
        UserShared.getInstance().setSettingMsgVibrate(paramBoolean);
        this.valueCache.put(Key.VibrateOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingMsgVibrate() {
        Object val = this.valueCache.get(Key.VibrateOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingMsgVibrate());
            this.valueCache.put(Key.VibrateOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setSettingMsgSpeaker(boolean paramBoolean) {
        UserShared.getInstance().setSettingMsgSpeaker(paramBoolean);
        this.valueCache.put(Key.SpakerOn, Boolean.valueOf(paramBoolean));
    }

    /* JADX INFO: Multiple debug info for r0v4 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.lang.Boolean)] */
    public boolean getSettingMsgSpeaker() {
        Object val = this.valueCache.get(Key.SpakerOn);
        if (val == null) {
            val = Boolean.valueOf(UserShared.getInstance().getSettingMsgSpeaker());
            this.valueCache.put(Key.SpakerOn, val);
        }
        return ((Boolean) val).booleanValue();
    }

    public void setDisabledGroups(List<String> groups) {
        if (this.dao == null) {
            this.dao = new UserDao(this.context);
        }
        List<String> list = new ArrayList<>();
        list.addAll(groups);
        int i = 0;
        while (i < list.size()) {
            if (EaseAtMessageHelper.get().getAtMeGroups().contains(list.get(i))) {
                list.remove(i);
                i--;
            }
            i++;
        }
        this.dao.setDisabledGroups(list);
        this.valueCache.put(Key.DisabledGroups, list);
    }

    /* JADX INFO: Multiple debug info for r0v3 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.util.List)] */
    public List<String> getDisabledGroups() {
        Object val = this.valueCache.get(Key.DisabledGroups);
        if (this.dao == null) {
            this.dao = new UserDao(this.context);
        }
        if (val == null) {
            val = this.dao.getDisabledGroups();
            this.valueCache.put(Key.DisabledGroups, val);
        }
        return (List) val;
    }

    public void setDisabledIds(List<String> ids) {
        if (this.dao == null) {
            this.dao = new UserDao(this.context);
        }
        this.dao.setDisabledIds(ids);
        this.valueCache.put(Key.DisabledIds, ids);
    }

    /* JADX INFO: Multiple debug info for r0v3 'val'  java.lang.Object: [D('val' java.lang.Object), D('val' java.util.List)] */
    public List<String> getDisabledIds() {
        Object val = this.valueCache.get(Key.DisabledIds);
        if (this.dao == null) {
            this.dao = new UserDao(this.context);
        }
        if (val == null) {
            val = this.dao.getDisabledIds();
            this.valueCache.put(Key.DisabledIds, val);
        }
        return (List) val;
    }

    public void setGroupsSynced(boolean synced) {
        UserShared.getInstance().setGroupsSynced(synced);
    }

    public boolean isGroupsSynced() {
        return UserShared.getInstance().isGroupsSynced();
    }

    public void setContactSynced(boolean synced) {
        UserShared.getInstance().setContactSynced(synced);
    }

    public boolean isContactSynced() {
        return UserShared.getInstance().isContactSynced();
    }

    public void setBlacklistSynced(boolean synced) {
        UserShared.getInstance().setBlacklistSynced(synced);
    }

    public boolean isBacklistSynced() {
        return UserShared.getInstance().isBacklistSynced();
    }

    public void allowChatroomOwnerLeave(boolean value) {
        UserShared.getInstance().setSettingAllowChatroomOwnerLeave(value);
    }

    public boolean isChatroomOwnerLeaveAllowed() {
        return UserShared.getInstance().getSettingAllowChatroomOwnerLeave();
    }

    public void setDeleteMessagesAsExitGroup(boolean value) {
        UserShared.getInstance().setDeleteMessagesAsExitGroup(value);
    }

    public boolean isDeleteMessagesAsExitGroup() {
        return UserShared.getInstance().isDeleteMessagesAsExitGroup();
    }

    public void setAutoAcceptGroupInvitation(boolean value) {
        UserShared.getInstance().setAutoAcceptGroupInvitation(value);
    }

    public boolean isAutoAcceptGroupInvitation() {
        return UserShared.getInstance().isAutoAcceptGroupInvitation();
    }

    public void setAdaptiveVideoEncode(boolean value) {
        UserShared.getInstance().setAdaptiveVideoEncode(value);
    }

    public boolean isAdaptiveVideoEncode() {
        return UserShared.getInstance().isAdaptiveVideoEncode();
    }

    public void setPushCall(boolean value) {
        UserShared.getInstance().setPushCall(value);
    }

    public boolean isPushCall() {
        return UserShared.getInstance().isPushCall();
    }

    public void setRestServer(String restServer) {
        UserShared.getInstance().setRestServer(restServer);
    }

    public String getRestServer() {
        return UserShared.getInstance().getRestServer();
    }

    public void setIMServer(String imServer) {
        UserShared.getInstance().setIMServer(imServer);
    }

    public String getIMServer() {
        return UserShared.getInstance().getIMServer();
    }

    public void enableCustomServer(boolean enable) {
        UserShared.getInstance().enableCustomServer(enable);
    }

    public boolean isCustomServerEnable() {
        return UserShared.getInstance().isCustomServerEnable();
    }
}
