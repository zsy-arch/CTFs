package com.yolanda.nohttp.cache;

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
public class CacheDiskManager extends DBManager<CacheEntity> {
    private static DBManager<CacheEntity> _Instance;

    private CacheDiskManager() {
        super(new CacheDisk());
    }

    public static synchronized DBManager<CacheEntity> getInstance() {
        DBManager<CacheEntity> dBManager;
        synchronized (CacheDiskManager.class) {
            if (_Instance == null) {
                _Instance = new CacheDiskManager();
            }
            dBManager = _Instance;
        }
        return dBManager;
    }

    public long replace(CacheEntity cacheEntity) {
        SQLiteDatabase execute = openWriter();
        ContentValues values = new ContentValues();
        values.put("key", cacheEntity.getKey());
        values.put(CacheDisk.HEAD, cacheEntity.getResponseHeadersJson());
        values.put("data", cacheEntity.getData());
        long id = -1;
        try {
            id = execute.replace(getTableName(), null, values);
        } catch (Throwable e) {
            Logger.e(e);
        }
        writeFinish(execute);
        return id;
    }

    @Override // com.yolanda.nohttp.db.DBManager
    public List<CacheEntity> get(String querySql) {
        SQLiteDatabase execute = openReader();
        List<CacheEntity> cacheEntities = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = execute.rawQuery(querySql, null);
            while (!cursor.isClosed() && cursor.moveToNext()) {
                CacheEntity cacheEntity = new CacheEntity();
                int idIndex = cursor.getColumnIndex(Field.ID);
                if (idIndex >= 0) {
                    cacheEntity.setId(cursor.getInt(idIndex));
                }
                int keyIndex = cursor.getColumnIndex("key");
                if (keyIndex >= 0) {
                    cacheEntity.setKey(cursor.getString(keyIndex));
                }
                int headIndex = cursor.getColumnIndex(CacheDisk.HEAD);
                if (headIndex >= 0) {
                    cacheEntity.setResponseHeadersJson(cursor.getString(headIndex));
                }
                int dataIndex = cursor.getColumnIndex("data");
                if (dataIndex >= 0) {
                    cacheEntity.setData(cursor.getBlob(dataIndex));
                }
                cacheEntities.add(cacheEntity);
            }
        } catch (Throwable e) {
            Logger.e(e);
        }
        readFinish(execute, cursor);
        return cacheEntities;
    }

    @Override // com.yolanda.nohttp.db.DBManager
    protected String getTableName() {
        return CacheDisk.TABLE_NAME;
    }
}
