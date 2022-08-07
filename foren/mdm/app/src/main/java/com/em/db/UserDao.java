package com.em.db;

import android.content.Context;
import com.easeui.domain.EaseUser;
import com.em.domain.RobotUser;
import com.vsf2f.f2f.bean.AccountBean;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.PhoneBean;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class UserDao {
    public static final String ACCOUNT_COLUMN_NAME_AVATAR = "accountavatar";
    public static final String ACCOUNT_COLUMN_NAME_NAME = "accountname";
    public static final String ACCOUNT_COLUMN_NAME_PWD = "accountpwd";
    public static final String ACCOUNT_TABLE_NAME = "historyaccounts";
    public static final String CIRCLE_COLUMN_NAME_ID = "circle_id";
    public static final String CIRCLE_COLUMN_NAME_JSON = "circle_json";
    public static final String CIRCLE_TABLE_NAME = "tab_circles";
    public static final String COLUMN_NAME_DISABLED_GROUPS = "disabled_groups";
    public static final String COLUMN_NAME_DISABLED_IDS = "disabled_ids";
    public static final String CONTACT_COLUMN_NAME_AVATAR = "avatar";
    public static final String CONTACT_COLUMN_NAME_FRIEND_COUNT = "friend";
    public static final String CONTACT_COLUMN_NAME_GENDER = "gender";
    public static final String CONTACT_COLUMN_NAME_ID = "username";
    public static final String CONTACT_COLUMN_NAME_LASTTIME = "lastTime";
    public static final String CONTACT_COLUMN_NAME_LAT = "lat";
    public static final String CONTACT_COLUMN_NAME_LNG = "lng";
    public static final String CONTACT_COLUMN_NAME_NICK = "nick";
    public static final String CONTACT_COLUMN_NAME_STATUS = "status";
    public static final String CONTACT_COLUMN_NAME_VISIBLE = "show";
    public static final String CONTACT_TABLE_NAME = "contacts";
    public static final String GROUP_COLUMN_NAME_BIZID = "bizid";
    public static final String GROUP_COLUMN_NAME_ID = "groupid";
    public static final String GROUP_COLUMN_NAME_NAME = "name";
    public static final String GROUP_COLUMN_NAME_PICTURE = "picture";
    public static final String GROUP_TABLE_NAME = "groups";
    public static final String IMAGE_COLUMN_NAME_CHATNAME = "chatname";
    public static final String IMAGE_COLUMN_NAME_HTTPURL = "httpurl";
    public static final String IMAGE_COLUMN_NAME_ID = "msgid";
    public static final String IMAGE_COLUMN_NAME_LOCALPATH = "localpath";
    public static final String IMAGE_COLUMN_NAME_SAVETIME = "savetime";
    public static final String IMAGE_COLUMN_NAME_USERNAME = "username";
    public static final String IMAGE_TABLE_NAME = "chat_images";
    public static final String PHONE_COLUMN_NAME_NO = "phone_no";
    public static final String PHONE_COLUMN_NAME_STATE = "phone_state";
    public static final String PHONE_TABLE_NAME = "phones";
    public static final String PREF_TABLE_NAME = "pref";
    public static final String ROBOT_COLUMN_NAME_AVATAR = "avatar";
    public static final String ROBOT_COLUMN_NAME_ID = "username";
    public static final String ROBOT_COLUMN_NAME_NICK = "nick";
    public static final String ROBOT_TABLE_NAME = "robots";
    public static final String USER_COLUMN_NAME_AVATAR = "avatar";
    public static final String USER_COLUMN_NAME_ID = "username";
    public static final String USER_COLUMN_NAME_NICK = "nick";
    public static final String USER_TABLE_NAME = "uers";

    public UserDao(Context context) {
    }

    public void updateContactList(List<EaseUser> contactList) {
        DemoDBManager.getInstance().updateContactList(contactList);
    }

    public Map<String, EaseUser> getContactList() {
        return DemoDBManager.getInstance().getContactList();
    }

    public void deleteContact(String username) {
        DemoDBManager.getInstance().deleteContact(username);
    }

    public void saveContact(EaseUser user) {
        DemoDBManager.getInstance().saveContact(user);
    }

    public void saveUsersList(List<EaseUser> userList) {
        DemoDBManager.getInstance().saveUsersList(userList);
    }

    public Map<String, EaseUser> getUsersList() {
        return DemoDBManager.getInstance().getUsersList();
    }

    public void deleteUser(String username) {
        DemoDBManager.getInstance().deleteUser(username);
    }

    public void saveUser(EaseUser user) {
        DemoDBManager.getInstance().saveUser(user);
    }

    public void saveGroupsList(List<GroupBean> groupList) {
        DemoDBManager.getInstance().saveGroupsList(groupList);
    }

    public Map<String, GroupBean> getGroupsList() {
        return DemoDBManager.getInstance().getGroupsList();
    }

    public void saveGroup(GroupBean group) {
        DemoDBManager.getInstance().saveGroup(group);
    }

    public void deleteGroup(String groupId) {
        DemoDBManager.getInstance().deleteGroup(groupId);
    }

    public void savePhonesList(List<PhoneBean> phoneList) {
        DemoDBManager.getInstance().savePhonesList(phoneList);
    }

    public Map<String, PhoneBean> getPhonesList() {
        return DemoDBManager.getInstance().getPhonesList();
    }

    public void setDisabledGroups(List<String> groups) {
        DemoDBManager.getInstance().setDisabledGroups(groups);
    }

    public List<String> getDisabledGroups() {
        return DemoDBManager.getInstance().getDisabledGroups();
    }

    public void setDisabledIds(List<String> ids) {
        DemoDBManager.getInstance().setDisabledIds(ids);
    }

    public List<String> getDisabledIds() {
        return DemoDBManager.getInstance().getDisabledIds();
    }

    public Map<String, RobotUser> getRobotUser() {
        return DemoDBManager.getInstance().getRobotList();
    }

    public void saveRobotUser(List<RobotUser> robotList) {
        DemoDBManager.getInstance().saveRobotList(robotList);
    }

    public void saveAccount(AccountBean account) {
        DemoDBManager.getInstance().saveAccount(account);
    }

    public void deleteAccount(String account) {
        DemoDBManager.getInstance().deleteAccount(account);
    }

    public Map<String, AccountBean> getAccountList() {
        return DemoDBManager.getInstance().getAccountList();
    }
}
