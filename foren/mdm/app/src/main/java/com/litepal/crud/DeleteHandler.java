package com.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import com.litepal.crud.model.AssociationsInfo;
import com.litepal.exceptions.DataSupportException;
import com.litepal.util.BaseUtility;
import com.litepal.util.DBUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public class DeleteHandler extends DataHandler {
    private List<String> foreignKeyTableToDelete;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeleteHandler(SQLiteDatabase db) {
        this.mDatabase = db;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onDelete(DataSupport baseObj) {
        if (!baseObj.isSaved()) {
            return 0;
        }
        Collection<AssociationsInfo> associationInfos = analyzeAssociations(baseObj);
        int rowsAffected = deleteCascade(baseObj) + this.mDatabase.delete(baseObj.getTableName(), "id = " + baseObj.getBaseObjId(), null);
        clearAssociatedModelSaveState(baseObj, associationInfos);
        return rowsAffected;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onDelete(Class<?> modelClass, long id) {
        analyzeAssociations(modelClass);
        int rowsAffected = deleteCascade(modelClass, id) + this.mDatabase.delete(getTableName(modelClass), "id = " + id, null);
        getForeignKeyTableToDelete().clear();
        return rowsAffected;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onDeleteAll(String tableName, String... conditions) {
        BaseUtility.checkConditionsCorrect(conditions);
        return this.mDatabase.delete(tableName, getWhereClause(conditions), getWhereArgs(conditions));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int onDeleteAll(Class<?> modelClass, String... conditions) {
        BaseUtility.checkConditionsCorrect(conditions);
        analyzeAssociations(modelClass);
        int rowsAffected = deleteAllCascade(modelClass, conditions) + this.mDatabase.delete(getTableName(modelClass), getWhereClause(conditions), getWhereArgs(conditions));
        getForeignKeyTableToDelete().clear();
        return rowsAffected;
    }

    private void analyzeAssociations(Class<?> modelClass) {
        for (AssociationsInfo associationInfo : getAssociationInfo(modelClass.getName())) {
            String associatedTableName = DBUtility.getTableNameByClassName(associationInfo.getAssociatedClassName());
            if (associationInfo.getAssociationType() == 2 || associationInfo.getAssociationType() == 1) {
                if (!modelClass.getName().equals(associationInfo.getClassHoldsForeignKey())) {
                    getForeignKeyTableToDelete().add(associatedTableName);
                }
            } else if (associationInfo.getAssociationType() == 3) {
                getForeignKeyTableToDelete().add(BaseUtility.changeCase(DBUtility.getIntermediateTableName(getTableName(modelClass), associatedTableName)));
            }
        }
    }

    private int deleteCascade(Class<?> modelClass, long id) {
        int rowsAffected = 0;
        for (String associatedTableName : getForeignKeyTableToDelete()) {
            String fkName = getForeignKeyColumnName(getTableName(modelClass));
            rowsAffected += this.mDatabase.delete(associatedTableName, fkName + " = " + id, null);
        }
        return rowsAffected;
    }

    private int deleteAllCascade(Class<?> modelClass, String... conditions) {
        int rowsAffected = 0;
        for (String associatedTableName : getForeignKeyTableToDelete()) {
            String tableName = getTableName(modelClass);
            String fkName = getForeignKeyColumnName(tableName);
            StringBuilder whereClause = new StringBuilder();
            whereClause.append(fkName).append(" in (select id from ");
            whereClause.append(tableName);
            if (conditions != null && conditions.length > 0) {
                whereClause.append(" where ").append(buildConditionString(conditions));
            }
            whereClause.append(")");
            rowsAffected += this.mDatabase.delete(associatedTableName, BaseUtility.changeCase(whereClause.toString()), null);
        }
        return rowsAffected;
    }

    private String buildConditionString(String... conditions) {
        int argCount = conditions.length - 1;
        String whereClause = conditions[0];
        for (int i = 0; i < argCount; i++) {
            whereClause = whereClause.replaceFirst("\\?", "'" + conditions[i + 1] + "'");
        }
        return whereClause;
    }

    private Collection<AssociationsInfo> analyzeAssociations(DataSupport baseObj) {
        try {
            Collection<AssociationsInfo> associationInfos = getAssociationInfo(baseObj.getClassName());
            analyzeAssociatedModels(baseObj, associationInfos);
            return associationInfos;
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private void clearAssociatedModelSaveState(DataSupport baseObj, Collection<AssociationsInfo> associationInfos) {
        DataSupport model;
        try {
            for (AssociationsInfo associationInfo : associationInfos) {
                if (associationInfo.getAssociationType() == 2 && !baseObj.getClassName().equals(associationInfo.getClassHoldsForeignKey())) {
                    Collection<DataSupport> associatedModels = getAssociatedModels(baseObj, associationInfo);
                    if (associatedModels != null && !associatedModels.isEmpty()) {
                        for (DataSupport model2 : associatedModels) {
                            if (model2 != null) {
                                model2.clearSavedState();
                            }
                        }
                    }
                } else if (associationInfo.getAssociationType() == 1 && (model = getAssociatedModel(baseObj, associationInfo)) != null) {
                    model.clearSavedState();
                }
            }
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    private int deleteCascade(DataSupport baseObj) {
        return deleteAssociatedForeignKeyRows(baseObj) + deleteAssociatedJoinTableRows(baseObj);
    }

    private int deleteAssociatedForeignKeyRows(DataSupport baseObj) {
        int rowsAffected = 0;
        for (String associatedTableName : baseObj.getAssociatedModelsMapWithFK().keySet()) {
            String fkName = getForeignKeyColumnName(baseObj.getTableName());
            rowsAffected += this.mDatabase.delete(associatedTableName, fkName + " = " + baseObj.getBaseObjId(), null);
        }
        return rowsAffected;
    }

    private int deleteAssociatedJoinTableRows(DataSupport baseObj) {
        int rowsAffected = 0;
        for (String associatedTableName : baseObj.getAssociatedModelsMapForJoinTable().keySet()) {
            rowsAffected += this.mDatabase.delete(DBUtility.getIntermediateTableName(baseObj.getTableName(), associatedTableName), getForeignKeyColumnName(baseObj.getTableName()) + " = " + baseObj.getBaseObjId(), null);
        }
        return rowsAffected;
    }

    private List<String> getForeignKeyTableToDelete() {
        if (this.foreignKeyTableToDelete == null) {
            this.foreignKeyTableToDelete = new ArrayList();
        }
        return this.foreignKeyTableToDelete;
    }
}
