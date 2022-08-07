package com.em.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.em.DemoHelper;
import com.hy.frame.util.MyLog;

/* loaded from: classes.dex */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE historyaccounts (accountname TEXT PRIMARY KEY, accountpwd TEXT, accountavatar TEXT);";
    public static final String CIRCLE_TABLE_CREATE = "CREATE TABLE tab_circles (circle_id INTEGER PRIMARY KEY, circle_json TEXT);";
    private static final String CONTACT_TABLE_CREATE = "CREATE TABLE contacts (nick TEXT, avatar TEXT, friend TEXT, lastTime TEXT, show TEXT, status TEXT, gender TEXT, lng TEXT, lat TEXT, username TEXT PRIMARY KEY);";
    private static final String CREATE_PREF_TABLE = "CREATE TABLE pref (disabled_groups TEXT, disabled_ids TEXT);";
    private static final int DATABASE_VERSION = 12;
    private static final String GROUP_TABLE_CREATE = "CREATE TABLE groups (picture TEXT, bizid TEXT, name TEXT, groupid TEXT PRIMARY KEY);";
    public static final String IMAGE_TABLE_CREATE = "CREATE TABLE chat_images (msgid TEXT, username TEXT, chatname TEXT, httpurl TEXT, localpath TEXT, savetime TEXT);";
    private static final String INIVTE_MESSAGE_TABLE_CREATE = "CREATE TABLE new_friends_msgs (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, groupid TEXT, groupname TEXT, reason TEXT, status INTEGER, isInviteFromMe INTEGER, unreadMsgCount INTEGER, time TEXT, groupinviter TEXT); ";
    public static final String PHONE_TABLE_CREATE = "CREATE TABLE phones (phone_no TEXT PRIMARY KEY, phone_state INTEGER);";
    private static final String ROBOT_TABLE_CREATE = "CREATE TABLE robots (username TEXT PRIMARY KEY, nick TEXT, avatar TEXT);";
    private static final String USER_TABLE_CREATE = "CREATE TABLE uers (nick TEXT, avatar TEXT, username TEXT PRIMARY KEY);";
    private static DbOpenHelper instance;

    private DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), (SQLiteDatabase.CursorFactory) null, 12);
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static String getUserDatabaseName() {
        return DemoHelper.getInstance().getCurrentUserName() + "_f2f.db";
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        MyLog.e("db_onCreate");
        db.execSQL(INIVTE_MESSAGE_TABLE_CREATE);
        db.execSQL(CONTACT_TABLE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(CREATE_PREF_TABLE);
        db.execSQL(GROUP_TABLE_CREATE);
        db.execSQL(PHONE_TABLE_CREATE);
        db.execSQL(ACCOUNT_TABLE_CREATE);
        db.execSQL(ROBOT_TABLE_CREATE);
        db.execSQL(IMAGE_TABLE_CREATE);
        db.execSQL(CIRCLE_TABLE_CREATE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = "db_onUpgrade=" + oldVersion + "->" + newVersion;
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE contacts ADD COLUMN avatar TEXT ;");
        }
        if (oldVersion < 3) {
            db.execSQL(CREATE_PREF_TABLE);
        }
        if (oldVersion < 4) {
            db.execSQL(ROBOT_TABLE_CREATE);
        }
        if (oldVersion < 5) {
            db.execSQL("ALTER TABLE new_friends_msgs ADD COLUMN unreadMsgCount INTEGER ;");
        }
        if (oldVersion < 6) {
            db.execSQL("ALTER TABLE new_friends_msgs ADD COLUMN groupinviter TEXT;");
        }
        if (oldVersion < 7) {
            db.execSQL("ALTER TABLE contacts ADD COLUMN lastTime TEXT ;");
        }
        if (oldVersion < 8) {
            db.execSQL(IMAGE_TABLE_CREATE);
        }
        if (oldVersion < 10) {
            db.execSQL("ALTER TABLE contacts ADD COLUMN show TEXT ;");
            db.execSQL("ALTER TABLE contacts ADD COLUMN status TEXT ;");
            db.execSQL("ALTER TABLE contacts ADD COLUMN gender TEXT ;");
            db.execSQL("ALTER TABLE contacts ADD COLUMN lat TEXT ;");
            db.execSQL("ALTER TABLE contacts ADD COLUMN lng TEXT ;");
        }
        if (oldVersion < 12) {
            db.execSQL(CIRCLE_TABLE_CREATE);
        }
        MyLog.e(str);
    }

    public void closeDB() {
        if (instance != null) {
            try {
                instance.getWritableDatabase().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
