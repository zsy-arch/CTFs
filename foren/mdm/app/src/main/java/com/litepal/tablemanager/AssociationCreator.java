package com.litepal.tablemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.hyphenate.util.HanziToPinyin;
import com.litepal.exceptions.DatabaseGenerateException;
import com.litepal.tablemanager.model.AssociationsModel;
import com.litepal.tablemanager.model.ColumnModel;
import com.litepal.util.BaseUtility;
import com.litepal.util.Const;
import com.litepal.util.DBUtility;
import com.litepal.util.LogUtil;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AssociationCreator extends Generator {
    @Override // com.litepal.tablemanager.Generator
    protected abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    @Override // com.litepal.tablemanager.Generator
    public void addOrUpdateAssociation(SQLiteDatabase db, boolean force) {
        addAssociations(getAllAssociations(), db, force);
    }

    protected String generateCreateTableSQL(String tableName, List<ColumnModel> columnModels, boolean autoIncrementId) {
        StringBuilder createTableSQL = new StringBuilder("create table ");
        createTableSQL.append(tableName).append(" (");
        if (autoIncrementId) {
            createTableSQL.append("id integer primary key autoincrement,");
        }
        if (columnModels.size() == 0) {
            createTableSQL.deleteCharAt(createTableSQL.length() - 1);
        }
        boolean needSeparator = false;
        for (ColumnModel columnModel : columnModels) {
            if (!columnModel.isIdColumn()) {
                if (needSeparator) {
                    createTableSQL.append(", ");
                }
                needSeparator = true;
                createTableSQL.append(columnModel.getColumnName()).append(HanziToPinyin.Token.SEPARATOR).append(columnModel.getColumnType());
                if (!columnModel.isNullable()) {
                    createTableSQL.append(" not null");
                }
                if (columnModel.isUnique()) {
                    createTableSQL.append(" unique");
                }
                String defaultValue = columnModel.getDefaultValue();
                if (!TextUtils.isEmpty(defaultValue)) {
                    createTableSQL.append(" default ").append(defaultValue);
                }
            }
        }
        createTableSQL.append(")");
        LogUtil.d(Generator.TAG, "create table sql is >> " + ((Object) createTableSQL));
        return createTableSQL.toString();
    }

    protected String generateDropTableSQL(String tableName) {
        return "drop table if exists " + tableName;
    }

    protected String generateAddColumnSQL(String tableName, ColumnModel columnModel) {
        StringBuilder addColumnSQL = new StringBuilder();
        addColumnSQL.append("alter table ").append(tableName);
        addColumnSQL.append(" add column ").append(columnModel.getColumnName());
        addColumnSQL.append(HanziToPinyin.Token.SEPARATOR).append(columnModel.getColumnType());
        if (!columnModel.isNullable()) {
            addColumnSQL.append(" not null");
        }
        if (columnModel.isUnique()) {
            addColumnSQL.append(" unique");
        }
        String defaultValue = columnModel.getDefaultValue();
        if (!TextUtils.isEmpty(defaultValue)) {
            addColumnSQL.append(" default ").append(defaultValue);
        } else if (!columnModel.isNullable()) {
            if ("integer".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0";
            } else if ("text".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "''";
            } else if ("real".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0.0";
            }
            addColumnSQL.append(" default ").append(defaultValue);
        }
        LogUtil.d(Generator.TAG, "add column sql is >> " + ((Object) addColumnSQL));
        return addColumnSQL.toString();
    }

    protected boolean isForeignKeyColumnFormat(String columnName) {
        return !TextUtils.isEmpty(columnName) && columnName.toLowerCase().endsWith(Field.ID) && !columnName.equalsIgnoreCase(Field.ID);
    }

    protected void giveTableSchemaACopy(String tableName, int tableType, SQLiteDatabase db) {
        Cursor cursor;
        try {
            StringBuilder sql = new StringBuilder("select * from ");
            sql.append(Const.TableSchema.TABLE_NAME);
            LogUtil.d(Generator.TAG, "giveTableSchemaACopy SQL is >> " + ((Object) sql));
            cursor = null;
            try {
                cursor = db.rawQuery(sql.toString(), null);
                if (isNeedtoGiveACopy(cursor, tableName)) {
                    ContentValues values = new ContentValues();
                    values.put("name", BaseUtility.changeCase(tableName));
                    values.put("type", Integer.valueOf(tableType));
                    db.insert(Const.TableSchema.TABLE_NAME, null, values);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private boolean isNeedtoGiveACopy(Cursor cursor, String tableName) {
        return !isValueExists(cursor, tableName) && !isSpecialTable(tableName);
    }

    private boolean isValueExists(Cursor cursor, String tableName) {
        if (!cursor.moveToFirst()) {
            return false;
        }
        while (!cursor.getString(cursor.getColumnIndexOrThrow("name")).equalsIgnoreCase(tableName)) {
            if (!cursor.moveToNext()) {
                return false;
            }
        }
        return true;
    }

    private boolean isSpecialTable(String tableName) {
        return Const.TableSchema.TABLE_NAME.equalsIgnoreCase(tableName);
    }

    private void addAssociations(Collection<AssociationsModel> associatedModels, SQLiteDatabase db, boolean force) {
        for (AssociationsModel associationModel : associatedModels) {
            if (2 == associationModel.getAssociationType() || 1 == associationModel.getAssociationType()) {
                addForeignKeyColumn(associationModel.getTableName(), associationModel.getAssociatedTableName(), associationModel.getTableHoldsForeignKey(), db);
            } else if (3 == associationModel.getAssociationType()) {
                createIntermediateTable(associationModel.getTableName(), associationModel.getAssociatedTableName(), db, force);
            }
        }
    }

    private void createIntermediateTable(String tableName, String associatedTableName, SQLiteDatabase db, boolean force) {
        List<ColumnModel> columnModelList = new ArrayList<>();
        ColumnModel column1 = new ColumnModel();
        column1.setColumnName(tableName + Field.ID);
        column1.setColumnType("integer");
        ColumnModel column2 = new ColumnModel();
        column2.setColumnName(associatedTableName + Field.ID);
        column2.setColumnType("integer");
        columnModelList.add(column1);
        columnModelList.add(column2);
        String intermediateTableName = DBUtility.getIntermediateTableName(tableName, associatedTableName);
        List<String> sqls = new ArrayList<>();
        if (!DBUtility.isTableExists(intermediateTableName, db)) {
            sqls.add(generateCreateTableSQL(intermediateTableName, columnModelList, false));
        } else if (force) {
            sqls.add(generateDropTableSQL(intermediateTableName));
            sqls.add(generateCreateTableSQL(intermediateTableName, columnModelList, false));
        }
        execute((String[]) sqls.toArray(new String[0]), db);
        giveTableSchemaACopy(intermediateTableName, 1, db);
    }

    protected void addForeignKeyColumn(String tableName, String associatedTableName, String tableHoldsForeignKey, SQLiteDatabase db) {
        if (!DBUtility.isTableExists(tableName, db)) {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + tableName);
        } else if (DBUtility.isTableExists(associatedTableName, db)) {
            String foreignKeyColumn = null;
            if (tableName.equals(tableHoldsForeignKey)) {
                foreignKeyColumn = getForeignKeyColumnName(associatedTableName);
            } else if (associatedTableName.equals(tableHoldsForeignKey)) {
                foreignKeyColumn = getForeignKeyColumnName(tableName);
            }
            if (!DBUtility.isColumnExists(foreignKeyColumn, tableHoldsForeignKey, db)) {
                ColumnModel columnModel = new ColumnModel();
                columnModel.setColumnName(foreignKeyColumn);
                columnModel.setColumnType("integer");
                execute(new String[]{generateAddColumnSQL(tableHoldsForeignKey, columnModel)}, db);
                return;
            }
            LogUtil.d(Generator.TAG, "column " + foreignKeyColumn + " is already exist, no need to add one");
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + associatedTableName);
        }
    }
}
