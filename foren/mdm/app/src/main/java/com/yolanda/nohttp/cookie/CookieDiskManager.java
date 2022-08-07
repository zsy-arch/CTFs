package com.yolanda.nohttp.cookie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.db.DBManager;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CookieDiskManager extends DBManager<CookieEntity> {
    private static DBManager<CookieEntity> _Instance;

    private CookieDiskManager() {
        super(new CookieDisk());
    }

    public static synchronized DBManager<CookieEntity> getInstance() {
        DBManager<CookieEntity> dBManager;
        synchronized (CookieDiskManager.class) {
            if (_Instance == null) {
                _Instance = new CookieDiskManager();
            }
            dBManager = _Instance;
        }
        return dBManager;
    }

    public long replace(CookieEntity cookie) {
        SQLiteDatabase execute = openWriter();
        ContentValues values = new ContentValues();
        values.put(CookieDisk.URI, cookie.getUri());
        values.put("name", cookie.getName());
        values.put("value", cookie.getValue());
        values.put("comment", cookie.getComment());
        values.put(CookieDisk.COMMENT_URL, cookie.getCommentURL());
        values.put("discard", String.valueOf(cookie.isDiscard()));
        values.put("domain", cookie.getDomain());
        values.put(CookieDisk.EXPIRY, Long.valueOf(cookie.getExpiry()));
        values.put("path", cookie.getPath());
        values.put(CookieDisk.PORT_LIST, cookie.getPortList());
        values.put("secure", String.valueOf(cookie.isSecure()));
        values.put("version", Integer.valueOf(cookie.getVersion()));
        long id = -1;
        try {
            print(values.toString());
            id = execute.replace(CookieDisk.TABLE_NAME, null, values);
        } catch (Throwable e) {
            Logger.w(e);
        }
        writeFinish(execute);
        return id;
    }

    @Override // com.yolanda.nohttp.db.DBManager
    public List<CookieEntity> get(String querySql) {
        SQLiteDatabase execute = openReader();
        List<CookieEntity> cookies = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = execute.rawQuery(querySql, null);
            while (!cursor.isClosed() && cursor.moveToNext()) {
                CookieEntity cookie = new CookieEntity();
                int idIndex = cursor.getColumnIndex(Field.ID);
                if (idIndex >= 0) {
                    cookie.setId(cursor.getInt(idIndex));
                }
                int uriIndex = cursor.getColumnIndex(CookieDisk.URI);
                if (uriIndex >= 0) {
                    cookie.setUri(cursor.getString(uriIndex));
                }
                int nameIndex = cursor.getColumnIndex("name");
                if (nameIndex >= 0) {
                    cookie.setName(cursor.getString(nameIndex));
                }
                int valueIndex = cursor.getColumnIndex("value");
                if (valueIndex >= 0) {
                    cookie.setValue(cursor.getString(valueIndex));
                }
                int commentIndex = cursor.getColumnIndex("comment");
                if (commentIndex >= 0) {
                    cookie.setComment(cursor.getString(commentIndex));
                }
                int commentUriIndex = cursor.getColumnIndex(CookieDisk.COMMENT_URL);
                if (commentUriIndex >= 0) {
                    cookie.setCommentURL(cursor.getString(commentUriIndex));
                }
                int discardIndex = cursor.getColumnIndex("discard");
                if (discardIndex >= 0) {
                    cookie.setDiscard("true".equals(cursor.getString(discardIndex)));
                }
                int domainIndex = cursor.getColumnIndex("domain");
                if (domainIndex >= 0) {
                    cookie.setDomain(cursor.getString(domainIndex));
                }
                int expiryIndex = cursor.getColumnIndex(CookieDisk.EXPIRY);
                if (expiryIndex >= 0) {
                    cookie.setExpiry(cursor.getLong(expiryIndex));
                }
                int pathIndex = cursor.getColumnIndex("path");
                if (pathIndex >= 0) {
                    cookie.setPath(cursor.getString(pathIndex));
                }
                int portListIndex = cursor.getColumnIndex(CookieDisk.PORT_LIST);
                if (portListIndex >= 0) {
                    cookie.setPortList(cursor.getString(portListIndex));
                }
                int secureIndex = cursor.getColumnIndex("secure");
                if (secureIndex >= 0) {
                    cookie.setSecure("true".equals(cursor.getString(secureIndex)));
                }
                int versionIndex = cursor.getColumnIndex("version");
                if (versionIndex >= 0) {
                    cookie.setVersion(cursor.getInt(versionIndex));
                }
                print(cookie.toString());
                cookies.add(cookie);
            }
        } catch (Throwable e) {
            Logger.e(e);
        }
        readFinish(execute, cursor);
        return cookies;
    }

    @Override // com.yolanda.nohttp.db.DBManager
    protected String getTableName() {
        return CookieDisk.TABLE_NAME;
    }
}
