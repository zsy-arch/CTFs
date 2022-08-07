package com.em.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.MyApplication;
import com.easeui.EaseConstant;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseCommonUtils;
import com.em.domain.InviteMessage;
import com.em.domain.RobotUser;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.bean.AccountBean;
import com.vsf2f.f2f.bean.ChatImageBean;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.PhoneBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DemoDBManager {
    private static DemoDBManager dbMgr = new DemoDBManager();
    private DbOpenHelper dbHelper = DbOpenHelper.getInstance(MyApplication.getInstance().getApplicationContext());

    private DemoDBManager() {
    }

    public static synchronized DemoDBManager getInstance() {
        DemoDBManager demoDBManager;
        synchronized (DemoDBManager.class) {
            if (dbMgr == null) {
                dbMgr = new DemoDBManager();
            }
            demoDBManager = dbMgr;
        }
        return demoDBManager;
    }

    public synchronized boolean haveFriend() {
        return this.dbHelper.getReadableDatabase().rawQuery("select username from contacts", null).getCount() > 0;
    }

    public synchronized void updateContactList(List<EaseUser> contactList) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            for (EaseUser user : contactList) {
                ContentValues values = new ContentValues();
                values.put("username", user.getUsername());
                if (user.getNickname() != null) {
                    values.put("nick", user.getNick());
                }
                if (user.getAvatar() != null) {
                    values.put("avatar", user.getAvatar());
                }
                if (user.getFriendsCount() != null) {
                    values.put(UserDao.CONTACT_COLUMN_NAME_FRIEND_COUNT, user.getFriendsCount());
                }
                if (!TextUtils.isEmpty(user.getLat())) {
                    values.put("lat", user.getLat());
                }
                if (!TextUtils.isEmpty(user.getLng())) {
                    values.put("lng", user.getLng());
                }
                values.put(UserDao.CONTACT_COLUMN_NAME_GENDER, Integer.valueOf(user.getGender()));
                values.put("status", Integer.valueOf(user.getStatus()));
                values.put(UserDao.CONTACT_COLUMN_NAME_VISIBLE, Integer.valueOf(user.getVisible()));
                values.put(UserDao.CONTACT_COLUMN_NAME_LASTTIME, Long.valueOf(user.getLastTime()));
                db.replace(UserDao.CONTACT_TABLE_NAME, null, values);
            }
        }
    }

    public synchronized Map<String, EaseUser> getContactList() {
        Map<String, EaseUser> users;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        users = new Hashtable<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from contacts", null);
            MyLog.e("SQLite", "contacts = " + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String nick = cursor.getString(cursor.getColumnIndex("nick"));
                String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
                String friend = cursor.getString(cursor.getColumnIndex(UserDao.CONTACT_COLUMN_NAME_FRIEND_COUNT));
                int gender = cursor.getInt(cursor.getColumnIndex(UserDao.CONTACT_COLUMN_NAME_GENDER));
                String lat = cursor.getString(cursor.getColumnIndex("lat"));
                String lng = cursor.getString(cursor.getColumnIndex("lng"));
                int visible = cursor.getInt(cursor.getColumnIndex(UserDao.CONTACT_COLUMN_NAME_VISIBLE));
                long lastTime = cursor.getLong(cursor.getColumnIndex(UserDao.CONTACT_COLUMN_NAME_LASTTIME));
                int status = cursor.getInt(cursor.getColumnIndex("status"));
                EaseUser user = new EaseUser(username);
                user.setNick(nick);
                user.setAvatar(avatar);
                user.setFriendsCount(friend);
                user.setLastTime(lastTime);
                user.setVisible(visible);
                user.setStatus(status);
                user.setGender(gender);
                user.setLat(lat);
                user.setLng(lng);
                if (username.equals(EaseConstant.NEW_FRIENDS_USERNAME) || username.equals(EaseConstant.GROUP_USERNAME) || username.equals(EaseConstant.CHAT_ROOM) || username.equals(EaseConstant.CHAT_ROBOT)) {
                    user.setInitialLetter("");
                } else {
                    user.setInitialLetter(EaseCommonUtils.setUserInitialLetter(user.getNick()));
                }
                users.put(username, user);
            }
            cursor.close();
        }
        return users;
    }

    public synchronized void deleteContact(String username) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.CONTACT_TABLE_NAME, "username = ?", new String[]{username});
        }
    }

    public synchronized void saveContact(EaseUser user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        if (user.getNickname() != null) {
            values.put("nick", user.getNick());
        }
        if (user.getAvatar() != null) {
            values.put("avatar", user.getAvatar());
        }
        if (!TextUtils.isEmpty(user.getLat())) {
            values.put("lat", user.getLat());
        }
        if (!TextUtils.isEmpty(user.getLng())) {
            values.put("lng", user.getLng());
        }
        values.put(UserDao.CONTACT_COLUMN_NAME_GENDER, Integer.valueOf(user.getGender()));
        values.put("status", Integer.valueOf(user.getStatus()));
        values.put(UserDao.CONTACT_COLUMN_NAME_VISIBLE, Integer.valueOf(user.getVisible()));
        if (db.isOpen()) {
            db.replace(UserDao.CONTACT_TABLE_NAME, null, values);
        }
    }

    public synchronized void saveUsersList(List<EaseUser> usersList) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            for (EaseUser user : usersList) {
                ContentValues values = new ContentValues();
                values.put("username", user.getUsername());
                if (user.getNickname() != null) {
                    values.put("nick", user.getNick());
                }
                if (user.getAvatar() != null) {
                    values.put("avatar", user.getAvatar());
                }
                db.replace(UserDao.USER_TABLE_NAME, null, values);
            }
        }
    }

    public synchronized Map<String, EaseUser> getUsersList() {
        Map<String, EaseUser> users;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        users = new Hashtable<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from uers", null);
            MyLog.e("SQLite", "uers = " + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String nick = cursor.getString(cursor.getColumnIndex("nick"));
                String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
                EaseUser user = new EaseUser(username);
                user.setNick(nick);
                user.setAvatar(avatar);
                users.put(username, user);
            }
            cursor.close();
        }
        return users;
    }

    public synchronized void deleteUser(String username) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.USER_TABLE_NAME, "username = ?", new String[]{username});
        }
    }

    public synchronized void saveUser(EaseUser user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        if (user.getNickname() != null) {
            values.put("nick", user.getNick());
        }
        if (user.getAvatar() != null) {
            values.put("avatar", user.getAvatar());
        }
        if (db.isOpen()) {
            db.replace(UserDao.USER_TABLE_NAME, null, values);
        }
    }

    public synchronized void saveGroupsList(List<GroupBean> groupList) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.GROUP_TABLE_NAME, null, null);
            for (GroupBean group : groupList) {
                ContentValues values = new ContentValues();
                values.put(UserDao.GROUP_COLUMN_NAME_BIZID, group.getBizId());
                if (group.getGroupId() != null) {
                    values.put(UserDao.GROUP_COLUMN_NAME_ID, group.getGroupId());
                }
                if (group.getGroupName() != null) {
                    values.put("name", group.getGroupName());
                }
                if (group.getGroupPicture() != null) {
                    values.put("picture", group.getGroupPicture());
                }
                db.replace(UserDao.GROUP_TABLE_NAME, null, values);
            }
        }
    }

    public synchronized Map<String, GroupBean> getGroupsList() {
        Map<String, GroupBean> groups;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        groups = new Hashtable<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from groups", null);
            MyLog.e("SQLite", "groups = " + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                String biz = cursor.getString(cursor.getColumnIndex(UserDao.GROUP_COLUMN_NAME_BIZID));
                String id = cursor.getString(cursor.getColumnIndex(UserDao.GROUP_COLUMN_NAME_ID));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String picture = cursor.getString(cursor.getColumnIndex("picture"));
                GroupBean groupBean = new GroupBean();
                groupBean.setBizId(biz);
                groupBean.setGroupId(id);
                groupBean.setGroupName(name);
                groupBean.setGroupPicture(picture);
                groups.put(id, groupBean);
            }
            cursor.close();
        }
        return groups;
    }

    public synchronized void saveGroup(GroupBean group) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDao.GROUP_COLUMN_NAME_BIZID, group.getBizId());
        if (group.getGroupId() != null) {
            values.put(UserDao.GROUP_COLUMN_NAME_ID, group.getGroupId());
        }
        if (group.getGroupName() != null) {
            values.put("name", group.getGroupName());
        }
        if (group.getGroupPicture() != null) {
            values.put("picture", group.getGroupPicture());
        }
        db.replace(UserDao.GROUP_TABLE_NAME, null, values);
    }

    public synchronized void deleteGroup(String groupId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.GROUP_TABLE_NAME, "groupid = ?", new String[]{groupId});
        }
    }

    public synchronized void savePhonesList(List<PhoneBean> groupList) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            for (PhoneBean phone : groupList) {
                ContentValues values = new ContentValues();
                values.put(UserDao.PHONE_COLUMN_NAME_NO, phone.getPhone());
                values.put(UserDao.PHONE_COLUMN_NAME_STATE, Integer.valueOf(phone.getType()));
                db.replace(UserDao.PHONE_TABLE_NAME, null, values);
            }
        }
    }

    public synchronized Map<String, PhoneBean> getPhonesList() {
        Map<String, PhoneBean> phones;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        phones = new Hashtable<>();
        if (db.isOpen()) {
            try {
                Cursor cursor = db.rawQuery("select * from phones", null);
                MyLog.e("SQLite", "phones = " + cursor.getColumnCount());
                while (cursor.moveToNext()) {
                    String phone = cursor.getString(cursor.getColumnIndex(UserDao.PHONE_COLUMN_NAME_NO));
                    phones.put(phone, new PhoneBean(phone, "", cursor.getInt(cursor.getColumnIndex(UserDao.PHONE_COLUMN_NAME_STATE))));
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    DbOpenHelper dbOpenHelper = this.dbHelper;
                    db.execSQL(DbOpenHelper.PHONE_TABLE_CREATE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return phones;
    }

    public synchronized String getCircleJson(int tabIndex) {
        String circleJson;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        circleJson = "";
        if (db.isOpen()) {
            try {
                Cursor cursor = db.rawQuery("select * from tab_circles where circle_id=" + tabIndex, null);
                MyLog.e("SQLite", "tab_circles = " + cursor.getColumnCount());
                while (cursor.moveToNext()) {
                    circleJson = cursor.getString(cursor.getColumnIndex(UserDao.CIRCLE_COLUMN_NAME_JSON));
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    DbOpenHelper dbOpenHelper = this.dbHelper;
                    db.execSQL(DbOpenHelper.PHONE_TABLE_CREATE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return circleJson;
    }

    public synchronized void saveCircleJson(int tabIndex, String circleJson) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.CIRCLE_COLUMN_NAME_ID, Integer.valueOf(tabIndex));
            values.put(UserDao.CIRCLE_COLUMN_NAME_JSON, circleJson);
            db.replace(UserDao.CIRCLE_TABLE_NAME, null, values);
        }
    }

    public synchronized void saveAccount(AccountBean account) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.ACCOUNT_TABLE_NAME, null, null);
            ContentValues values = new ContentValues();
            values.put(UserDao.ACCOUNT_COLUMN_NAME_NAME, account.getName());
            values.put(UserDao.ACCOUNT_COLUMN_NAME_PWD, account.getPwd());
            values.put(UserDao.ACCOUNT_COLUMN_NAME_AVATAR, account.getAvatar());
            db.replace(UserDao.ACCOUNT_TABLE_NAME, null, values);
        }
    }

    public synchronized void deleteAccount(String accountName) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.ACCOUNT_TABLE_NAME, "accountname = ?", new String[]{accountName});
        }
    }

    public synchronized Map<String, AccountBean> getAccountList() {
        Map<String, AccountBean> map;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        map = new Hashtable<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from historyaccounts", null);
            MyLog.e("SQLite", "historyaccounts = " + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(UserDao.ACCOUNT_COLUMN_NAME_NAME));
                map.put(name, new AccountBean(name, cursor.getString(cursor.getColumnIndex(UserDao.ACCOUNT_COLUMN_NAME_PWD)), cursor.getString(cursor.getColumnIndex(UserDao.ACCOUNT_COLUMN_NAME_AVATAR))));
            }
            cursor.close();
        }
        return map;
    }

    public synchronized Map<String, ChatImageBean> getImages(String username, String chatname) {
        Map<String, ChatImageBean> images;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        images = new LinkedHashMap<>();
        if (db.isOpen()) {
            try {
                Cursor cursor = db.rawQuery("select * from chat_images where username='" + username + "' and " + UserDao.IMAGE_COLUMN_NAME_CHATNAME + "='" + chatname + "'", null);
                MyLog.e("SQLite", "chat_images = " + cursor.getColumnCount());
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(UserDao.IMAGE_COLUMN_NAME_ID));
                    String url = cursor.getString(cursor.getColumnIndex(UserDao.IMAGE_COLUMN_NAME_HTTPURL));
                    String path = cursor.getString(cursor.getColumnIndex(UserDao.IMAGE_COLUMN_NAME_LOCALPATH));
                    long time = cursor.getLong(cursor.getColumnIndex(UserDao.IMAGE_COLUMN_NAME_SAVETIME));
                    ChatImageBean phoneBean = new ChatImageBean(username, chatname);
                    phoneBean.setMsgid(id);
                    phoneBean.setHttpurl(url);
                    phoneBean.setLocalpath(path);
                    phoneBean.setSavetime(time);
                    images.put(id, phoneBean);
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    db.delete(UserDao.IMAGE_TABLE_NAME, null, null);
                    DbOpenHelper dbOpenHelper = this.dbHelper;
                    db.execSQL(DbOpenHelper.IMAGE_TABLE_CREATE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return images;
    }

    public synchronized void saveImage(ChatImageBean image) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.IMAGE_COLUMN_NAME_ID, image.getMsgid());
            values.put("username", image.getUsername());
            values.put(UserDao.IMAGE_COLUMN_NAME_CHATNAME, image.getChatname());
            values.put(UserDao.IMAGE_COLUMN_NAME_HTTPURL, image.getHttpurl());
            values.put(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, image.getLocalpath());
            values.put(UserDao.IMAGE_COLUMN_NAME_SAVETIME, Long.valueOf(System.currentTimeMillis()));
            db.replace(UserDao.IMAGE_TABLE_NAME, null, values);
        }
    }

    public synchronized void clearImage(String username, String to) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.IMAGE_TABLE_NAME, "username = ? and chatname = ? ", new String[]{username, to});
        }
    }

    public void setDisabledGroups(List<String> groups) {
        setList(UserDao.COLUMN_NAME_DISABLED_GROUPS, groups);
    }

    public List<String> getDisabledGroups() {
        return getList(UserDao.COLUMN_NAME_DISABLED_GROUPS);
    }

    public void setDisabledIds(List<String> ids) {
        setList(UserDao.COLUMN_NAME_DISABLED_IDS, ids);
    }

    public List<String> getDisabledIds() {
        return getList(UserDao.COLUMN_NAME_DISABLED_IDS);
    }

    private synchronized void setList(String column, List<String> strList) {
        StringBuilder strBuilder = new StringBuilder();
        for (String hxid : strList) {
            strBuilder.append(hxid).append("$");
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(column, strBuilder.toString());
            db.update(UserDao.PREF_TABLE_NAME, values, null, null);
        }
    }

    private synchronized List<String> getList(String column) {
        List<String> list = null;
        synchronized (this) {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select " + column + " from " + UserDao.PREF_TABLE_NAME, null);
            MyLog.e("SQLite", "pref = " + cursor.getColumnCount());
            if (!cursor.moveToFirst()) {
                cursor.close();
            } else {
                String strVal = cursor.getString(0);
                if (strVal != null && !strVal.equals("")) {
                    cursor.close();
                    String[] array = strVal.split("$");
                    if (array.length > 0) {
                        list = new ArrayList<>();
                        Collections.addAll(list, array);
                    }
                }
            }
        }
        return list;
    }

    public synchronized Integer saveMessage(InviteMessage message) {
        int id;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        id = -1;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("username", message.getFrom());
            values.put(UserDao.GROUP_COLUMN_NAME_ID, message.getGroupId());
            values.put("groupname", message.getGroupName());
            values.put("reason", message.getReason());
            values.put(f.az, Long.valueOf(message.getTime()));
            values.put("status", Integer.valueOf(message.getStatus().ordinal()));
            values.put("groupinviter", message.getGroupInviter());
            values.put("unreadMsgCount", (Integer) 1);
            db.insert("new_friends_msgs", null, values);
            Cursor cursor = db.rawQuery("select last_insert_rowid() from new_friends_msgs", null);
            MyLog.e("SQLite", "new_friends_msgs = " + cursor.getColumnCount());
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
            cursor.close();
        }
        return Integer.valueOf(id);
    }

    public synchronized boolean hasMessage(String from) {
        boolean has;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        has = false;
        if (db.isOpen()) {
            Cursor cursor1 = db.rawQuery("select * from new_friends_msgs where username='" + from + "'", null);
            if (cursor1.getColumnCount() > 0) {
                has = true;
            }
            cursor1.close();
        }
        return has;
    }

    public synchronized void updateMessage(int msgId, ContentValues values) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.update("new_friends_msgs", values, "id = ?", new String[]{String.valueOf(msgId)});
        }
    }

    public synchronized List<InviteMessage> getMessagesList() {
        List<InviteMessage> msgs;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        msgs = new ArrayList<>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from new_friends_msgs ORDER BY time DESC", null);
            MyLog.e("SQLite", "new_friends_msgs = " + cursor.getCount());
            while (cursor.moveToNext()) {
                InviteMessage msg = new InviteMessage();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int status = cursor.getInt(cursor.getColumnIndex("status"));
                long time = cursor.getLong(cursor.getColumnIndex(f.az));
                String from = cursor.getString(cursor.getColumnIndex("username"));
                String reason = cursor.getString(cursor.getColumnIndex("reason"));
                String groupid = cursor.getString(cursor.getColumnIndex(UserDao.GROUP_COLUMN_NAME_ID));
                String groupname = cursor.getString(cursor.getColumnIndex("groupname"));
                String groupInviter = cursor.getString(cursor.getColumnIndex("groupinviter"));
                msg.setId(id);
                msg.setFrom(from);
                msg.setGroupId(groupid);
                msg.setGroupName(groupname);
                msg.setReason(reason);
                msg.setTime(time);
                msg.setGroupInviter(groupInviter);
                if (status == InviteMessage.InviteMesageStatus.BEINVITEED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEINVITEED);
                } else if (status == InviteMessage.InviteMesageStatus.BEAGREED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
                } else if (status == InviteMessage.InviteMesageStatus.BEREFUSED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEREFUSED);
                } else if (status == InviteMessage.InviteMesageStatus.AGREED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.AGREED);
                } else if (status == InviteMessage.InviteMesageStatus.REFUSED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.REFUSED);
                } else if (status == InviteMessage.InviteMesageStatus.BEAPPLYED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEAPPLYED);
                } else if (status == InviteMessage.InviteMesageStatus.GROUPINVITATION.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION);
                } else if (status == InviteMessage.InviteMesageStatus.GROUPINVITATION_ACCEPTED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_ACCEPTED);
                } else if (status == InviteMessage.InviteMesageStatus.GROUPINVITATION_DECLINED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_DECLINED);
                }
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }

    public synchronized void deleteMessage(String from) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete("new_friends_msgs", "username = ?", new String[]{from});
        }
    }

    public synchronized void clearMessage() {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete("new_friends_msgs", null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int getUnreadNotifyCount() {
        int count;
        count = 0;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select username from new_friends_msgs where unreadMsgCount = 1", null);
            MyLog.e("SQLite", "unreadMsgCount = " + cursor.getColumnCount());
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    synchronized void setUnreadNotifyCount(int count) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("unreadMsgCount", Integer.valueOf(count));
            db.update("new_friends_msgs", values, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setReadAllNotifyCount() {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("unreadMsgCount", (Integer) 0);
            db.update("new_friends_msgs", values, null, null);
        }
    }

    public synchronized void closeDB() {
        if (this.dbHelper != null) {
            this.dbHelper.closeDB();
        }
        dbMgr = null;
    }

    public synchronized void saveRobotList(List<RobotUser> robotList) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.ROBOT_TABLE_NAME, null, null);
            for (RobotUser item : robotList) {
                ContentValues values = new ContentValues();
                values.put("username", item.getUsername());
                if (item.getNickname() != null) {
                    values.put("nick", item.getNick());
                }
                if (item.getAvatar() != null) {
                    values.put("avatar", item.getAvatar());
                }
                db.replace(UserDao.ROBOT_TABLE_NAME, null, values);
            }
        }
    }

    public synchronized Map<String, RobotUser> getRobotList() {
        Map<String, RobotUser> users;
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        users = null;
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from robots", null);
            if (cursor.getCount() > 0) {
                users = new Hashtable<>();
            }
            MyLog.e("SQLite", "robots = " + cursor.getColumnCount());
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String nick = cursor.getString(cursor.getColumnIndex("nick"));
                String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
                RobotUser user = new RobotUser(username);
                user.setNick(nick);
                user.setAvatar(avatar);
                String headerName = user.getNick();
                if (Character.isDigit(headerName.charAt(0))) {
                    user.setInitialLetter("#");
                } else {
                    user.setInitialLetter(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1).toUpperCase());
                    char header = user.getInitialLetter().toLowerCase().charAt(0);
                    if (header < 'a' || header > 'z') {
                        user.setInitialLetter("#");
                    }
                }
                if (users != null) {
                    try {
                        users.put(username, user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            cursor.close();
        }
        return users;
    }
}
