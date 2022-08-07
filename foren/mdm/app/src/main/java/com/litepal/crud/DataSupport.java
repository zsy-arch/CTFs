package com.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.litepal.exceptions.DataSupportException;
import com.litepal.tablemanager.Connector;
import com.litepal.util.BaseUtility;
import com.litepal.util.DBUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class DataSupport {
    private Map<String, Set<Long>> associatedModelsMapForJoinTable;
    private Map<String, Set<Long>> associatedModelsMapWithFK;
    private Map<String, Long> associatedModelsMapWithoutFK;
    private long baseObjId;
    private List<String> fieldsToSetToDefault;
    private List<String> listToClearAssociatedFK;
    private List<String> listToClearSelfFK;

    public static synchronized ClusterQuery select(String... columns) {
        ClusterQuery cQuery;
        synchronized (DataSupport.class) {
            cQuery = new ClusterQuery();
            cQuery.mColumns = columns;
        }
        return cQuery;
    }

    public static synchronized ClusterQuery where(String... conditions) {
        ClusterQuery cQuery;
        synchronized (DataSupport.class) {
            cQuery = new ClusterQuery();
            cQuery.mConditions = conditions;
        }
        return cQuery;
    }

    public static synchronized ClusterQuery order(String column) {
        ClusterQuery cQuery;
        synchronized (DataSupport.class) {
            cQuery = new ClusterQuery();
            cQuery.mOrderBy = column;
        }
        return cQuery;
    }

    public static synchronized ClusterQuery limit(int value) {
        ClusterQuery cQuery;
        synchronized (DataSupport.class) {
            cQuery = new ClusterQuery();
            cQuery.mLimit = String.valueOf(value);
        }
        return cQuery;
    }

    public static synchronized ClusterQuery offset(int value) {
        ClusterQuery cQuery;
        synchronized (DataSupport.class) {
            cQuery = new ClusterQuery();
            cQuery.mOffset = String.valueOf(value);
        }
        return cQuery;
    }

    public static synchronized int count(Class<?> modelClass) {
        int count;
        synchronized (DataSupport.class) {
            count = count(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())));
        }
        return count;
    }

    public static synchronized int count(String tableName) {
        int count;
        synchronized (DataSupport.class) {
            count = new ClusterQuery().count(tableName);
        }
        return count;
    }

    public static synchronized double average(Class<?> modelClass, String column) {
        double average;
        synchronized (DataSupport.class) {
            average = average(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())), column);
        }
        return average;
    }

    public static synchronized double average(String tableName, String column) {
        double average;
        synchronized (DataSupport.class) {
            average = new ClusterQuery().average(tableName, column);
        }
        return average;
    }

    public static synchronized <T> T max(Class<?> modelClass, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) max(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())), columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T max(String tableName, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new ClusterQuery().max(tableName, columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T min(Class<?> modelClass, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) min(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())), columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T min(String tableName, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new ClusterQuery().min(tableName, columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T sum(Class<?> modelClass, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) sum(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())), columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T sum(String tableName, String columnName, Class<T> columnType) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new ClusterQuery().sum(tableName, columnName, columnType);
        }
        return t;
    }

    public static synchronized <T> T find(Class<T> modelClass, long id) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) find(modelClass, id, false);
        }
        return t;
    }

    public static synchronized <T> T find(Class<T> modelClass, long id, boolean isEager) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new QueryHandler(Connector.getDatabase()).onFind(modelClass, id, isEager);
        }
        return t;
    }

    public static synchronized <T> T findFirst(Class<T> modelClass) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) findFirst(modelClass, false);
        }
        return t;
    }

    public static synchronized <T> T findFirst(Class<T> modelClass, boolean isEager) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new QueryHandler(Connector.getDatabase()).onFindFirst(modelClass, isEager);
        }
        return t;
    }

    public static synchronized <T> T findLast(Class<T> modelClass) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) findLast(modelClass, false);
        }
        return t;
    }

    public static synchronized <T> T findLast(Class<T> modelClass, boolean isEager) {
        T t;
        synchronized (DataSupport.class) {
            t = (T) new QueryHandler(Connector.getDatabase()).onFindLast(modelClass, isEager);
        }
        return t;
    }

    public static synchronized <T> List<T> findAll(Class<T> modelClass, long... ids) {
        List<T> findAll;
        synchronized (DataSupport.class) {
            findAll = findAll(modelClass, false, ids);
        }
        return findAll;
    }

    public static synchronized <T> List<T> findAll(Class<T> modelClass, boolean isEager, long... ids) {
        List<T> onFindAll;
        synchronized (DataSupport.class) {
            onFindAll = new QueryHandler(Connector.getDatabase()).onFindAll(modelClass, isEager, ids);
        }
        return onFindAll;
    }

    public static synchronized Cursor findBySQL(String... sql) {
        String[] selectionArgs;
        Cursor cursor = null;
        synchronized (DataSupport.class) {
            BaseUtility.checkConditionsCorrect(sql);
            if (sql != null && sql.length > 0) {
                if (sql.length == 1) {
                    selectionArgs = null;
                } else {
                    selectionArgs = new String[sql.length - 1];
                    System.arraycopy(sql, 1, selectionArgs, 0, sql.length - 1);
                }
                cursor = Connector.getDatabase().rawQuery(sql[0], selectionArgs);
            }
        }
        return cursor;
    }

    public static synchronized int delete(Class<?> modelClass, long id) {
        int rowsAffected;
        synchronized (DataSupport.class) {
            SQLiteDatabase db = Connector.getDatabase();
            db.beginTransaction();
            rowsAffected = new DeleteHandler(db).onDelete(modelClass, id);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        return rowsAffected;
    }

    public static synchronized int deleteAll(Class<?> modelClass, String... conditions) {
        int onDeleteAll;
        synchronized (DataSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll(modelClass, conditions);
        }
        return onDeleteAll;
    }

    public static synchronized int deleteAll(String tableName, String... conditions) {
        int onDeleteAll;
        synchronized (DataSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll(tableName, conditions);
        }
        return onDeleteAll;
    }

    public static synchronized int update(Class<?> modelClass, ContentValues values, long id) {
        int onUpdate;
        synchronized (DataSupport.class) {
            onUpdate = new UpdateHandler(Connector.getDatabase()).onUpdate(modelClass, id, values);
        }
        return onUpdate;
    }

    public static synchronized int updateAll(Class<?> modelClass, ContentValues values, String... conditions) {
        int updateAll;
        synchronized (DataSupport.class) {
            updateAll = updateAll(BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName())), values, conditions);
        }
        return updateAll;
    }

    public static synchronized int updateAll(String tableName, ContentValues values, String... conditions) {
        int onUpdateAll;
        synchronized (DataSupport.class) {
            onUpdateAll = new UpdateHandler(Connector.getDatabase()).onUpdateAll(tableName, values, conditions);
        }
        return onUpdateAll;
    }

    public static synchronized <T extends DataSupport> void saveAll(Collection<T> collection) {
        synchronized (DataSupport.class) {
            SQLiteDatabase db = Connector.getDatabase();
            db.beginTransaction();
            try {
                new SaveHandler(db).onSaveAll(collection);
                db.setTransactionSuccessful();
                db.endTransaction();
            } catch (Exception e) {
                throw new DataSupportException(e.getMessage(), e);
            }
        }
    }

    public static <T extends DataSupport> void markAsDeleted(Collection<T> collection) {
        for (T t : collection) {
            t.clearSavedState();
        }
    }

    public static <T> boolean isExist(Class<T> modelClass, String... conditions) {
        return conditions != null && where(conditions).count((Class<?>) modelClass) > 0;
    }

    public synchronized int delete() {
        int rowsAffected;
        SQLiteDatabase db = Connector.getDatabase();
        db.beginTransaction();
        rowsAffected = new DeleteHandler(db).onDelete(this);
        this.baseObjId = 0L;
        db.setTransactionSuccessful();
        db.endTransaction();
        return rowsAffected;
    }

    public synchronized int update(long id) {
        int rowsAffected;
        try {
            rowsAffected = new UpdateHandler(Connector.getDatabase()).onUpdate(this, id);
            getFieldsToSetToDefault().clear();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
        return rowsAffected;
    }

    public synchronized int updateAll(String... conditions) {
        int rowsAffected;
        try {
            rowsAffected = new UpdateHandler(Connector.getDatabase()).onUpdateAll(this, conditions);
            getFieldsToSetToDefault().clear();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
        return rowsAffected;
    }

    public synchronized boolean save() {
        boolean z;
        try {
            saveThrows();
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public synchronized void saveThrows() {
        SQLiteDatabase db = Connector.getDatabase();
        db.beginTransaction();
        try {
            new SaveHandler(db).onSave(this);
            clearAssociatedData();
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    public synchronized boolean saveIfNotExist(String... conditions) {
        return !isExist(getClass(), conditions) ? save() : false;
    }

    public synchronized boolean saveFast() {
        boolean z;
        SQLiteDatabase db = Connector.getDatabase();
        db.beginTransaction();
        try {
            new SaveHandler(db).onSaveFast(this);
            db.setTransactionSuccessful();
            z = true;
            db.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
            db.endTransaction();
        }
        return z;
    }

    public boolean isSaved() {
        return this.baseObjId > 0;
    }

    public void clearSavedState() {
        this.baseObjId = 0L;
    }

    public void setToDefault(String fieldName) {
        getFieldsToSetToDefault().add(fieldName);
    }

    public void assignBaseObjId(int baseObjId) {
        this.baseObjId = baseObjId;
    }

    public long getBaseObjId() {
        return this.baseObjId;
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getTableName() {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(getClassName()));
    }

    public List<String> getFieldsToSetToDefault() {
        if (this.fieldsToSetToDefault == null) {
            this.fieldsToSetToDefault = new ArrayList();
        }
        return this.fieldsToSetToDefault;
    }

    public void addAssociatedModelWithFK(String associatedTableName, long associatedId) {
        Set<Long> associatedIdsWithFKSet = getAssociatedModelsMapWithFK().get(associatedTableName);
        if (associatedIdsWithFKSet == null) {
            Set<Long> associatedIdsWithFKSet2 = new HashSet<>();
            associatedIdsWithFKSet2.add(Long.valueOf(associatedId));
            this.associatedModelsMapWithFK.put(associatedTableName, associatedIdsWithFKSet2);
            return;
        }
        associatedIdsWithFKSet.add(Long.valueOf(associatedId));
    }

    public Map<String, Set<Long>> getAssociatedModelsMapWithFK() {
        if (this.associatedModelsMapWithFK == null) {
            this.associatedModelsMapWithFK = new HashMap();
        }
        return this.associatedModelsMapWithFK;
    }

    public void addAssociatedModelForJoinTable(String associatedModelName, long associatedId) {
        Set<Long> associatedIdsM2MSet = getAssociatedModelsMapForJoinTable().get(associatedModelName);
        if (associatedIdsM2MSet == null) {
            Set<Long> associatedIdsM2MSet2 = new HashSet<>();
            associatedIdsM2MSet2.add(Long.valueOf(associatedId));
            this.associatedModelsMapForJoinTable.put(associatedModelName, associatedIdsM2MSet2);
            return;
        }
        associatedIdsM2MSet.add(Long.valueOf(associatedId));
    }

    public void addEmptyModelForJoinTable(String associatedModelName) {
        if (getAssociatedModelsMapForJoinTable().get(associatedModelName) == null) {
            this.associatedModelsMapForJoinTable.put(associatedModelName, new HashSet<>());
        }
    }

    public Map<String, Set<Long>> getAssociatedModelsMapForJoinTable() {
        if (this.associatedModelsMapForJoinTable == null) {
            this.associatedModelsMapForJoinTable = new HashMap();
        }
        return this.associatedModelsMapForJoinTable;
    }

    public void addAssociatedModelWithoutFK(String associatedTableName, long associatedId) {
        getAssociatedModelsMapWithoutFK().put(associatedTableName, Long.valueOf(associatedId));
    }

    public Map<String, Long> getAssociatedModelsMapWithoutFK() {
        if (this.associatedModelsMapWithoutFK == null) {
            this.associatedModelsMapWithoutFK = new HashMap();
        }
        return this.associatedModelsMapWithoutFK;
    }

    public void addFKNameToClearSelf(String foreignKeyName) {
        List<String> list = getListToClearSelfFK();
        if (!list.contains(foreignKeyName)) {
            list.add(foreignKeyName);
        }
    }

    public List<String> getListToClearSelfFK() {
        if (this.listToClearSelfFK == null) {
            this.listToClearSelfFK = new ArrayList();
        }
        return this.listToClearSelfFK;
    }

    public void addAssociatedTableNameToClearFK(String associatedTableName) {
        List<String> list = getListToClearAssociatedFK();
        if (!list.contains(associatedTableName)) {
            list.add(associatedTableName);
        }
    }

    public List<String> getListToClearAssociatedFK() {
        if (this.listToClearAssociatedFK == null) {
            this.listToClearAssociatedFK = new ArrayList();
        }
        return this.listToClearAssociatedFK;
    }

    public void clearAssociatedData() {
        clearIdOfModelWithFK();
        clearIdOfModelWithoutFK();
        clearIdOfModelForJoinTable();
        clearFKNameList();
    }

    private void clearIdOfModelWithFK() {
        for (String associatedModelName : getAssociatedModelsMapWithFK().keySet()) {
            this.associatedModelsMapWithFK.get(associatedModelName).clear();
        }
        this.associatedModelsMapWithFK.clear();
    }

    private void clearIdOfModelWithoutFK() {
        getAssociatedModelsMapWithoutFK().clear();
    }

    private void clearIdOfModelForJoinTable() {
        for (String associatedModelName : getAssociatedModelsMapForJoinTable().keySet()) {
            this.associatedModelsMapForJoinTable.get(associatedModelName).clear();
        }
        this.associatedModelsMapForJoinTable.clear();
    }

    private void clearFKNameList() {
        getListToClearSelfFK().clear();
        getListToClearAssociatedFK().clear();
    }
}
