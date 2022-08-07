package com.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.litepal.exceptions.DataSupportException;
import com.litepal.util.BaseUtility;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class UpdateHandler extends DataHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdateHandler(SQLiteDatabase db) {
        this.mDatabase = db;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdate(DataSupport baseObj, long id) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Field> supportedFields = getSupportedFields(baseObj.getClassName());
        ContentValues values = new ContentValues();
        putFieldsValue(baseObj, supportedFields, values);
        putFieldsToDefaultValue(baseObj, values);
        if (values.size() > 0) {
            return this.mDatabase.update(baseObj.getTableName(), values, "id = " + id, null);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdate(Class<?> modelClass, long id, ContentValues values) {
        if (values.size() > 0) {
            return this.mDatabase.update(getTableName(modelClass), values, "id = " + id, null);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdateAll(DataSupport baseObj, String... conditions) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Field> supportedFields = getSupportedFields(baseObj.getClassName());
        ContentValues values = new ContentValues();
        putFieldsValue(baseObj, supportedFields, values);
        putFieldsToDefaultValue(baseObj, values);
        return doUpdateAllAction(baseObj.getTableName(), values, conditions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onUpdateAll(String tableName, ContentValues values, String... conditions) {
        return doUpdateAllAction(tableName, values, conditions);
    }

    private int doUpdateAllAction(String tableName, ContentValues values, String... conditions) {
        BaseUtility.checkConditionsCorrect(conditions);
        if (values.size() > 0) {
            return this.mDatabase.update(tableName, values, getWhereClause(conditions), getWhereArgs(conditions));
        }
        return 0;
    }

    private void putFieldsToDefaultValue(DataSupport baseObj, ContentValues values) {
        String fieldName = null;
        try {
            DataSupport emptyModel = getEmptyModel(baseObj);
            Class<?> emptyModelClass = emptyModel.getClass();
            for (String name : baseObj.getFieldsToSetToDefault()) {
                if (!isIdColumn(name)) {
                    fieldName = name;
                    putContentValuesForUpdate(emptyModel, emptyModelClass.getDeclaredField(fieldName), values);
                }
            }
        } catch (NoSuchFieldException e) {
            throw new DataSupportException(DataSupportException.noSuchFieldExceptioin(baseObj.getClassName(), fieldName), e);
        } catch (Exception e2) {
            throw new DataSupportException(e2.getMessage(), e2);
        }
    }

    private int doUpdateAssociations(DataSupport baseObj, long id, ContentValues values) {
        analyzeAssociations(baseObj);
        updateSelfTableForeignKey(baseObj, values);
        return 0 + updateAssociatedTableForeignKey(baseObj, id);
    }

    private void analyzeAssociations(DataSupport baseObj) {
        try {
            analyzeAssociatedModels(baseObj, getAssociationInfo(baseObj.getClassName()));
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private void updateSelfTableForeignKey(DataSupport baseObj, ContentValues values) {
        Map<String, Long> associatedModelMap = baseObj.getAssociatedModelsMapWithoutFK();
        for (String associatedTable : associatedModelMap.keySet()) {
            values.put(getForeignKeyColumnName(associatedTable), associatedModelMap.get(associatedTable));
        }
    }

    private int updateAssociatedTableForeignKey(DataSupport baseObj, long id) {
        Map<String, Set<Long>> associatedModelMap = baseObj.getAssociatedModelsMapWithFK();
        ContentValues values = new ContentValues();
        for (String associatedTable : associatedModelMap.keySet()) {
            values.clear();
            values.put(getForeignKeyColumnName(baseObj.getTableName()), Long.valueOf(id));
            Set<Long> ids = associatedModelMap.get(associatedTable);
            if (!(ids == null || ids.isEmpty())) {
                return this.mDatabase.update(associatedTable, values, getWhereOfIdsWithOr(ids), null);
            }
        }
        return 0;
    }
}
