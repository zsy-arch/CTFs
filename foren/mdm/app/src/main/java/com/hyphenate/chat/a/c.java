package com.hyphenate.chat.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EMPrivateConstant;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class c {
    private static final String A = "isblocked";
    private static final String B = "max_users";
    private static final String C = "chatroom";
    private static final String D = "name";
    private static final String E = "nick";
    private static final String F = "desc";
    private static final String G = "owner";
    private static final String H = "members";
    private static final String I = "members_size";
    private static final String J = "isblocked";
    private static final String K = "max_users";
    private static final String L = "unreadcount";
    private static final String M = "username";
    private static final String N = "count";
    private static final String O = "token";
    private static final String P = "username";
    private static final String Q = "value";
    private static final String R = "saved_time";
    private static final String S = "contact";
    private static final String T = "jid";
    private static final String U = "username";
    private static final String V = "nick";
    private static final String W = "black_list";
    private static final String X = "username";
    private static final String Y = "conversation_list";
    private static final String Z = "username";
    public static final String a = "_emmsg.db";
    private static final String aa = "groupname";
    private static final String ab = "ext";
    private static final String ac = "conversation_type";
    private static final String ad = "porting";
    private static final String ae = "done";
    private static final String af = "create table chat (_id integer primary key autoincrement, msgid text, msgtime integer, msgdir integer, isacked integer, isdelivered integer, status integer,participant text not null, islistened integer, msgbody text not null,msgtype integer, groupname text);";
    private static final String ag = "create table emgroup (name text primary key, jid text not null, nick text not null, owner text not null, modifiedtime integer, ispublic integer, desc text, members_size integer, isblocked integer, members text, max_users integer);";
    private static final String ah = "create table chatroom (name text primary key, nick text, owner text, desc text, members_size integer, isblocked integer, members text, max_users integer);";
    private static final String ai = "create table unreadcount (username text primary key, count integer);";
    private static final String aj = "create table token (username text primary key, value text, saved_time integer);";
    private static final String ak = "create table contact (jid text primary key, username text, nick );";
    private static final String al = "create table black_list (username text primary key);";
    private static final String am = "create table if not exists conversation_list (username text primary key, groupname text, ext text, conversation_type integer);";
    private static final String an = "create table if not exists porting (done integer)";
    public static final String b = "msgbody";
    public static final String c = "status";
    private static final int e = 13;
    private static final String f = "chat";
    private static final String g = "_id";
    private static final String h = "msgid";
    private static final String i = "msgtime";
    private static final String j = "msgdir";
    private static final String k = "participant";
    private static final String l = "groupname";
    private static final String m = "isacked";
    private static final String n = "isdelivered";
    private static final String o = "islistened";
    private static final String p = "msgtype";
    private static final String q = "emgroup";
    private static final String r = "name";
    private static final String s = "nick";
    private static final String t = "desc";

    /* renamed from: u  reason: collision with root package name */
    private static final String f32u = "owner";
    private static final String v = "members";
    private static final String w = "members_size";
    private static final String x = "modifiedtime";
    private static final String y = "jid";
    private static final String z = "ispublic";
    private Context ar;
    private static String d = "EMDBManager";
    private static c ao = null;
    private boolean ap = true;
    private String aq = null;
    private boolean as = false;
    private b at = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class a extends SQLiteOpenHelper {
        private static a instance = null;
        private String username;

        private a(Context context, String str) {
            super(context, str + c.a, (SQLiteDatabase.CursorFactory) null, 13);
            this.username = str;
            EMLog.d(c.d, "created chatdb for :" + str);
        }

        private void addConversation(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
            try {
                EMLog.d(c.d, "add converstion with:" + str);
                String str2 = !z ? "username" : "groupname";
                sQLiteDatabase.execSQL("insert into conversation_list (" + str2 + ") select ? where not exists (select null from " + c.Y + " where " + str2 + " = ?)", new String[]{str, str});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void clearContactTable_v10(SQLiteDatabase sQLiteDatabase) {
            try {
                EMLog.d(c.d, "add converstion with:" + this.username);
                sQLiteDatabase.execSQL("delete from contact", new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static synchronized void closeDB() {
            synchronized (a.class) {
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

        static void createMigrateTable(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(c.an);
            } catch (Exception e) {
            }
        }

        private void dropDB(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("drop table chat");
                sQLiteDatabase.execSQL("drop table emgroup");
                sQLiteDatabase.execSQL("drop table unreadcount");
                sQLiteDatabase.execSQL("drop table token");
                sQLiteDatabase.execSQL("drop table contact");
                sQLiteDatabase.execSQL("drop table black_list");
                sQLiteDatabase.execSQL("drop table conversation_list");
            } catch (Exception e) {
            }
        }

        public static synchronized a getInstance(Context context, String str) {
            a aVar;
            synchronized (a.class) {
                if (instance == null) {
                    instance = new a(context, str);
                }
                aVar = instance;
            }
            return aVar;
        }

        private boolean importBlackList(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = null;
                boolean z = false;
                EMLog.d(c.d, "importBlackList");
                try {
                    cursor = sQLiteDatabase.rawQuery("select * from black_list", null);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        do {
                            arrayList.add(cursor.getString(0));
                        } while (cursor.moveToNext());
                        c.a().at.a(arrayList);
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    }
                    return true;
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        private boolean importChatRooms(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                boolean z = false;
                EMLog.d(c.d, "importChatRooms");
                cursor = null;
                try {
                    cursor = sQLiteDatabase.rawQuery("select * from chatroom", new String[0]);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    } else {
                        do {
                            c.f(cursor);
                        } while (cursor.moveToNext());
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    }
                    return true;
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    e.printStackTrace();
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        private boolean importContacts(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = null;
                boolean z = true;
                EMLog.d(c.d, "importContacts");
                try {
                    cursor = sQLiteDatabase.rawQuery("select * from contact", null);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        do {
                            arrayList.add(cursor.getString(1));
                        } while (cursor.moveToNext());
                        c.a().at.b(arrayList);
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    z = false;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        private boolean importConversations(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = null;
                EMLog.d(c.d, "importConversations");
                try {
                    new ArrayList();
                    Cursor rawQuery = sQLiteDatabase.rawQuery("select * from conversation_list", null);
                    if (!rawQuery.moveToFirst()) {
                        rawQuery.close();
                        if (rawQuery == null || rawQuery.isClosed()) {
                            return true;
                        }
                        rawQuery.close();
                        return true;
                    }
                    new ArrayList();
                    do {
                        boolean z = !rawQuery.isNull(rawQuery.getColumnIndex("groupname"));
                        c.b(!z ? rawQuery.getString(rawQuery.getColumnIndex("username")) : rawQuery.getString(rawQuery.getColumnIndex("groupname")), (!z ? EMConversation.EMConversationType.Chat : EMConversation.EMConversationType.GroupChat).ordinal(), rawQuery.getString(rawQuery.getColumnIndex("ext")));
                    } while (rawQuery.moveToNext());
                    if (rawQuery == null || rawQuery.isClosed()) {
                        return true;
                    }
                    rawQuery.close();
                    return true;
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    if (0 != 0 && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return false;
                }
            } catch (Throwable th) {
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        private boolean importGroups(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                boolean z = false;
                EMLog.d(c.d, "importGroups");
                cursor = null;
                try {
                    cursor = sQLiteDatabase.rawQuery("select * from emgroup", new String[0]);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    } else {
                        do {
                            c.e(cursor);
                        } while (cursor.moveToNext());
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    }
                    return true;
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    e.printStackTrace();
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        private boolean importMessages(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            try {
                cursor = null;
                boolean z = true;
                EMLog.d(c.d, "importMessages");
                try {
                    cursor = sQLiteDatabase.rawQuery("SELECT * FROM chat", null);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                    } else {
                        ArrayList arrayList = new ArrayList();
                        do {
                            arrayList.add(c.d(cursor));
                            if (arrayList.size() >= 1000) {
                                EMClient.getInstance().chatManager().importMessages(arrayList);
                                arrayList.clear();
                            }
                        } while (cursor.moveToNext());
                        if (arrayList.size() > 0) {
                            EMClient.getInstance().chatManager().importMessages(arrayList);
                            arrayList.clear();
                        }
                        cursor.close();
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                    }
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    z = false;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
                return z;
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }

        private boolean importUnread(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor = null;
            boolean z = false;
            EMLog.d(c.d, "importUnread");
            try {
                try {
                    cursor = sQLiteDatabase.rawQuery("select * from unreadcount", null);
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    } else {
                        do {
                            c.a().at.a(cursor.getString(0), cursor.getInt(1));
                        } while (cursor.moveToNext());
                        if (cursor != null && !(z = cursor.isClosed())) {
                            cursor.close();
                        }
                    }
                    return true;
                } catch (Exception e) {
                    EMLog.e(c.d, "migrateToOneSDK..." + e.getMessage());
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x004d A[Catch: Exception -> 0x0082, TRY_LEAVE, TryCatch #0 {Exception -> 0x0082, blocks: (B:3:0x0014, B:5:0x0021, B:7:0x002f, B:9:0x003f, B:11:0x004d), top: B:23:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0078 A[LOOP:2: B:13:0x0072->B:15:0x0078, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0091 A[LOOP:3: B:19:0x008b->B:21:0x0091, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x003f A[Catch: Exception -> 0x0082, LOOP:1: B:9:0x003f->B:10:0x004b, LOOP_START, TryCatch #0 {Exception -> 0x0082, blocks: (B:3:0x0014, B:5:0x0021, B:7:0x002f, B:9:0x003f, B:11:0x004d), top: B:23:0x0014 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void initializeConversation(android.database.sqlite.SQLiteDatabase r7) {
            /*
                r6 = this;
                r5 = 0
                java.lang.String r0 = com.hyphenate.chat.a.c.f()
                java.lang.String r1 = "initializeConversation"
                com.hyphenate.util.EMLog.d(r0, r1)
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.lang.String r0 = "select distinct participant from chat where groupname is null"
                r3 = 0
                android.database.Cursor r0 = r7.rawQuery(r0, r3)     // Catch: Exception -> 0x0082
                boolean r3 = r0.moveToFirst()     // Catch: Exception -> 0x0082
                if (r3 == 0) goto L_0x002f
            L_0x0021:
                r3 = 0
                java.lang.String r3 = r0.getString(r3)     // Catch: Exception -> 0x0082
                r1.add(r3)     // Catch: Exception -> 0x0082
                boolean r3 = r0.moveToNext()     // Catch: Exception -> 0x0082
                if (r3 != 0) goto L_0x0021
            L_0x002f:
                r0.close()     // Catch: Exception -> 0x0082
                java.lang.String r0 = "select distinct groupname from chat where groupname is not null"
                r3 = 0
                android.database.Cursor r0 = r7.rawQuery(r0, r3)     // Catch: Exception -> 0x0082
                boolean r3 = r0.moveToFirst()     // Catch: Exception -> 0x0082
                if (r3 == 0) goto L_0x004d
            L_0x003f:
                r3 = 0
                java.lang.String r3 = r0.getString(r3)     // Catch: Exception -> 0x0082
                r2.add(r3)     // Catch: Exception -> 0x0082
                boolean r3 = r0.moveToNext()     // Catch: Exception -> 0x0082
                if (r3 != 0) goto L_0x003f
            L_0x004d:
                r0.close()     // Catch: Exception -> 0x0082
                java.lang.String r0 = com.hyphenate.chat.a.c.f()     // Catch: Exception -> 0x0082
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Exception -> 0x0082
                r3.<init>()     // Catch: Exception -> 0x0082
                java.lang.String r4 = "load participants size:"
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch: Exception -> 0x0082
                int r4 = r1.size()     // Catch: Exception -> 0x0082
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch: Exception -> 0x0082
                java.lang.String r3 = r3.toString()     // Catch: Exception -> 0x0082
                com.hyphenate.util.EMLog.d(r0, r3)     // Catch: Exception -> 0x0082
            L_0x006e:
                java.util.Iterator r1 = r1.iterator()
            L_0x0072:
                boolean r0 = r1.hasNext()
                if (r0 == 0) goto L_0x0087
                java.lang.Object r0 = r1.next()
                java.lang.String r0 = (java.lang.String) r0
                r6.addConversation(r7, r0, r5)
                goto L_0x0072
            L_0x0082:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x006e
            L_0x0087:
                java.util.Iterator r1 = r2.iterator()
            L_0x008b:
                boolean r0 = r1.hasNext()
                if (r0 == 0) goto L_0x009c
                java.lang.Object r0 = r1.next()
                java.lang.String r0 = (java.lang.String) r0
                r2 = 1
                r6.addConversation(r7, r0, r2)
                goto L_0x008b
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hyphenate.chat.a.c.a.initializeConversation(android.database.sqlite.SQLiteDatabase):void");
        }

        private boolean isColumnExist(SQLiteDatabase sQLiteDatabase, String str, String str2) {
            Cursor cursor;
            try {
                cursor = null;
                boolean z = false;
                try {
                    cursor = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
                    if (cursor != null) {
                        if (cursor.getColumnIndex(str2) != -1) {
                            z = true;
                        }
                    }
                } catch (Exception e) {
                    EMLog.e(c.d, "checkColumnExists..." + e.getMessage());
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
                return z;
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }

        private void migrateFrom10To12(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from conversation_list", null);
            if (!rawQuery.moveToFirst()) {
                rawQuery.close();
                return;
            }
            do {
                boolean z = !rawQuery.isNull(rawQuery.getColumnIndex("groupname"));
                int ordinal = EMConversation.EMConversationType.Chat.ordinal();
                String str = "username";
                if (z) {
                    ordinal = EMConversation.EMConversationType.GroupChat.ordinal();
                    str = "groupname";
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(c.ac, Integer.valueOf(ordinal));
                sQLiteDatabase.update(c.Y, contentValues, str + " = ?", new String[]{rawQuery.getString(rawQuery.getColumnIndex(str))});
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }

        static boolean migrationFinished(SQLiteDatabase sQLiteDatabase) {
            boolean z = true;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("select * from porting", new String[0]);
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        if (rawQuery.getInt(rawQuery.getColumnIndex(c.ae)) != 1) {
                            z = false;
                        }
                        rawQuery.close();
                        return z;
                    }
                    rawQuery.close();
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        static boolean setMigrationDone(SQLiteDatabase sQLiteDatabase) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(c.ae, (Integer) 1);
                return sQLiteDatabase.replace(c.ad, null, contentValues) != -1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public void migrateToOneSDK(SQLiteDatabase sQLiteDatabase) {
            boolean z = true;
            EMLog.d(c.d, "---->start migrate");
            createMigrateTable(sQLiteDatabase);
            if (!migrationFinished(sQLiteDatabase)) {
                boolean z2 = (!importUnread(sQLiteDatabase)) | false | (!importMessages(sQLiteDatabase)) | (!importConversations(sQLiteDatabase)) | (!importGroups(sQLiteDatabase)) | (!importBlackList(sQLiteDatabase)) | (!importContacts(sQLiteDatabase));
                EMClient.getInstance().chatManager().loadAllConversations();
                if (!z2) {
                    EMLog.d(c.d, "---->finished migrate");
                    if (setMigrationDone(sQLiteDatabase)) {
                        z = false;
                    }
                    z2 |= z;
                }
                if (!z2) {
                    dropDB(sQLiteDatabase);
                }
                EMLog.d(c.d, "migration success!");
                c.h();
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w(c.d, "Upgrading from version " + i + " to " + i2);
            if (i < 2) {
                sQLiteDatabase.execSQL(c.ai);
            }
            if (i < 3) {
                sQLiteDatabase.execSQL(c.aj);
                sQLiteDatabase.execSQL(c.ak);
            }
            if (i < 4) {
                try {
                    sQLiteDatabase.delete(c.O, "username = ?", new String[]{this.username});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i < 5) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE chat ADD COLUMN isdelivered integer ;");
                    EMLog.d(c.d, "db upgrade to vervison 5");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (i < 6) {
                sQLiteDatabase.execSQL(c.al);
                sQLiteDatabase.execSQL("ALTER TABLE chat ADD COLUMN islistened integer ;");
            }
            if (i < 7) {
                sQLiteDatabase.execSQL("ALTER TABLE emgroup ADD COLUMN members_size INTEGER ;");
            }
            if (i < 8) {
                sQLiteDatabase.execSQL("ALTER TABLE emgroup ADD COLUMN isblocked INTEGER ;");
            }
            if (i < 9) {
                sQLiteDatabase.execSQL("ALTER TABLE emgroup ADD COLUMN max_users INTEGER ;");
            }
            if (i < 10) {
                sQLiteDatabase.execSQL(c.am);
                initializeConversation(sQLiteDatabase);
                clearContactTable_v10(sQLiteDatabase);
                c.a().as = true;
            }
            if (i < 12) {
                sQLiteDatabase.execSQL(c.ah);
                if (c.a().at.l().equals("dewmobile#kuaiya")) {
                    try {
                        sQLiteDatabase.execSQL(c.am);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (!isColumnExist(sQLiteDatabase, c.Y, c.ac)) {
                    sQLiteDatabase.execSQL("ALTER TABLE conversation_list ADD COLUMN conversation_type INTEGER ;");
                }
                sQLiteDatabase.execSQL("ALTER TABLE chat ADD COLUMN msgtype INTEGER ;");
                migrateFrom10To12(sQLiteDatabase);
            }
            if (i < 13) {
                migrateToOneSDK(sQLiteDatabase);
            }
        }
    }

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (ao == null) {
                EMLog.e(d, "Please login first!");
                throw new IllegalStateException("Please login first!");
            }
            cVar = ao;
        }
        return cVar;
    }

    public static synchronized void a(String str, b bVar) {
        synchronized (c.class) {
            EMLog.d(d, "initDB : " + str);
            if (ao != null) {
                if (ao.aq == null || !ao.aq.equals(str)) {
                    ao.b();
                }
            }
            if (ao == null) {
                ao = new c();
                ao.at = bVar;
                ao.ar = EMClient.getInstance().getContext();
            }
            ao.aq = str;
            ao.ap = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, int i2, String str2) {
        ao.at.a(str, i2, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EMMessage d(Cursor cursor) {
        boolean z2 = true;
        EMMessage msgFromJson = MessageEncoder.getMsgFromJson(cursor.getString(cursor.getColumnIndex(b)));
        msgFromJson.setMsgId(cursor.getString(cursor.getColumnIndex("msgid")));
        msgFromJson.setMsgTime(cursor.getLong(cursor.getColumnIndex(i)));
        msgFromJson.setDirection(cursor.getInt(cursor.getColumnIndex(j)) == 0 ? EMMessage.Direct.SEND : EMMessage.Direct.RECEIVE);
        int i2 = cursor.getInt(cursor.getColumnIndex("status"));
        if (i2 == EMMessage.Status.CREATE.ordinal()) {
            msgFromJson.setStatus(EMMessage.Status.CREATE);
        } else if (i2 == EMMessage.Status.INPROGRESS.ordinal()) {
            msgFromJson.setStatus(EMMessage.Status.INPROGRESS);
        } else if (i2 == EMMessage.Status.SUCCESS.ordinal()) {
            msgFromJson.setStatus(EMMessage.Status.SUCCESS);
        } else if (i2 == EMMessage.Status.FAIL.ordinal()) {
            msgFromJson.setStatus(EMMessage.Status.FAIL);
        }
        msgFromJson.setAcked(cursor.getInt(cursor.getColumnIndex(m)) != 0);
        msgFromJson.setDeliverAcked(cursor.getInt(cursor.getColumnIndex(n)) != 0);
        if (cursor.getInt(cursor.getColumnIndex(o)) != 1) {
            z2 = false;
        }
        msgFromJson.setListened(z2);
        msgFromJson.setUnread(false);
        String string = cursor.getString(cursor.getColumnIndex("groupname"));
        if (string == null) {
            msgFromJson.setChatType(EMMessage.ChatType.Chat);
        } else {
            int i3 = cursor.getInt(cursor.getColumnIndex("msgtype"));
            msgFromJson.setChatType(EMMessage.ChatType.GroupChat);
            if (i3 == EMMessage.ChatType.ChatRoom.ordinal()) {
                msgFromJson.setChatType(EMMessage.ChatType.ChatRoom);
            }
            msgFromJson.setTo(string);
        }
        return msgFromJson;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Cursor cursor) throws Exception {
        String string = cursor.getString(cursor.getColumnIndex("name"));
        int i2 = cursor.getInt(cursor.getColumnIndex(z)) == 0 ? 1 : 2;
        String string2 = cursor.getString(cursor.getColumnIndex("nick"));
        String string3 = cursor.getString(cursor.getColumnIndex(EMPrivateConstant.EMMultiUserConstant.ROOM_OWNER));
        String string4 = cursor.getString(cursor.getColumnIndex(SocialConstants.PARAM_APP_DESC));
        boolean z2 = cursor.getInt(cursor.getColumnIndex("isblocked")) != 0;
        int i3 = cursor.getInt(cursor.getColumnIndex("max_users"));
        StringTokenizer stringTokenizer = new StringTokenizer(cursor.getString(cursor.getColumnIndex("members")), ",");
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        ao.at.a(string, i2, string3, string2, string4, arrayList, z2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Cursor cursor) throws Exception {
        String string = cursor.getString(cursor.getColumnIndex("name"));
        String string2 = cursor.getString(cursor.getColumnIndex("nick"));
        String string3 = cursor.getString(cursor.getColumnIndex(EMPrivateConstant.EMMultiUserConstant.ROOM_OWNER));
        String string4 = cursor.getString(cursor.getColumnIndex(SocialConstants.PARAM_APP_DESC));
        int i2 = cursor.getInt(cursor.getColumnIndex("max_users"));
        StringTokenizer stringTokenizer = new StringTokenizer(cursor.getString(cursor.getColumnIndex("members")), ",");
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        ao.at.a(string, string3, string2, string4, arrayList, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h() {
        ao.at.z();
    }

    public int a(String str) {
        try {
            Cursor rawQuery = a.getInstance(this.ar, this.aq).getReadableDatabase().rawQuery("select count from unreadcount where username = ?", new String[]{str});
            int i2 = rawQuery.moveToFirst() ? rawQuery.getInt(rawQuery.getColumnIndex("count")) : 0;
            rawQuery.close();
            if (i2 < 0) {
                return 0;
            }
            return i2;
        } catch (Exception e2) {
            return 0;
        }
    }

    public long a(String str, boolean z2) {
        long j2 = 0;
        try {
            Cursor rawQuery = a.getInstance(this.ar, this.aq).getWritableDatabase().rawQuery("select count(*) as msgCount from chat where " + (!z2 ? k : "groupname") + " = ?", new String[]{str});
            if (!rawQuery.moveToFirst()) {
                rawQuery.close();
            } else {
                j2 = rawQuery.getLong(0);
                rawQuery.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j2;
    }

    public synchronized void b() {
        try {
            a.closeDB();
            EMLog.d(d, "close msg db");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c() {
        a.getInstance(this.ar, this.aq).getWritableDatabase();
        ao.b();
    }

    public List<String> d() {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = a.getInstance(this.ar, this.aq).getReadableDatabase().rawQuery("select * from unreadcount", new String[0]);
            if (!rawQuery.moveToFirst()) {
                rawQuery.close();
            } else {
                do {
                    String string = rawQuery.getString(0);
                    if (rawQuery.getInt(1) > 0) {
                        arrayList.add(string);
                    }
                } while (rawQuery.moveToNext());
                rawQuery.close();
            }
        } catch (Exception e2) {
        }
        return arrayList;
    }

    public boolean e() {
        return this.as;
    }
}
