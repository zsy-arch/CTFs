package com.em.db;

import android.content.ContentValues;
import android.content.Context;
import com.em.domain.InviteMessage;
import java.util.List;

/* loaded from: classes.dex */
public class InviteMessgeDao {
    static final String COLUMN_NAME_FROM = "username";
    static final String COLUMN_NAME_GROUPINVITER = "groupinviter";
    static final String COLUMN_NAME_GROUP_ID = "groupid";
    static final String COLUMN_NAME_GROUP_NAME = "groupname";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_ISINVITEFROMME = "isInviteFromMe";
    static final String COLUMN_NAME_REASON = "reason";
    public static final String COLUMN_NAME_STATUS = "status";
    static final String COLUMN_NAME_TIME = "time";
    static final String COLUMN_NAME_UNREAD_MSG_COUNT = "unreadMsgCount";
    static final String TABLE_NAME = "new_friends_msgs";

    public InviteMessgeDao(Context context) {
    }

    public Integer saveMessage(InviteMessage message) {
        return DemoDBManager.getInstance().saveMessage(message);
    }

    public boolean hasMessage(String from) {
        return DemoDBManager.getInstance().hasMessage(from);
    }

    public void updateMessage(int msgId, ContentValues values) {
        DemoDBManager.getInstance().updateMessage(msgId, values);
    }

    public List<InviteMessage> getMessagesList() {
        return DemoDBManager.getInstance().getMessagesList();
    }

    public void deleteMessage(String from) {
        DemoDBManager.getInstance().deleteMessage(from);
    }

    public int getUnreadMessagesCount() {
        return DemoDBManager.getInstance().getUnreadNotifyCount();
    }

    public void setReadAllNotifyCount() {
        DemoDBManager.getInstance().setReadAllNotifyCount();
    }
}
