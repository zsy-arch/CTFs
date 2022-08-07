package com.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import com.litepal.crud.model.AssociationsInfo;
import com.litepal.tablemanager.model.ColumnModel;
import com.litepal.tablemanager.model.TableModel;
import com.litepal.util.DBUtility;
import com.litepal.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public class Upgrader extends AssociationUpdater {
    private boolean hasConstraintChanged;
    protected TableModel mTableModel;
    protected TableModel mTableModelDB;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.litepal.tablemanager.AssociationUpdater, com.litepal.tablemanager.Creator, com.litepal.tablemanager.AssociationCreator, com.litepal.tablemanager.Generator
    public void createOrUpgradeTable(SQLiteDatabase db, boolean force) {
        this.mDb = db;
        for (TableModel tableModel : getAllTableModels()) {
            this.mTableModel = tableModel;
            this.mTableModelDB = getTableModelFromDB(tableModel.getTableName());
            upgradeTable();
        }
    }

    private void upgradeTable() {
        if (hasNewUniqueOrNotNullColumn()) {
            createOrUpgradeTable(this.mTableModel, this.mDb, true);
            for (AssociationsInfo info : getAssociationInfo(this.mTableModel.getClassName())) {
                if (info.getAssociationType() == 2 || info.getAssociationType() == 1) {
                    if (info.getClassHoldsForeignKey().equalsIgnoreCase(this.mTableModel.getClassName())) {
                        addForeignKeyColumn(this.mTableModel.getTableName(), DBUtility.getTableNameByClassName(info.getAssociatedClassName()), this.mTableModel.getTableName(), this.mDb);
                    }
                }
            }
            return;
        }
        this.hasConstraintChanged = false;
        removeColumns(findColumnsToRemove());
        addColumns(findColumnsToAdd());
        changeColumnsType(findColumnTypesToChange());
        changeColumnsConstraints();
    }

    private boolean hasNewUniqueOrNotNullColumn() {
        for (ColumnModel columnModel : this.mTableModel.getColumnModels()) {
            ColumnModel columnModelDB = this.mTableModelDB.getColumnModelByName(columnModel.getColumnName());
            if (columnModel.isUnique() && (columnModelDB == null || !columnModelDB.isUnique())) {
                return true;
            }
            if (!(columnModelDB == null || columnModel.isNullable() || !columnModelDB.isNullable())) {
                return true;
            }
        }
        return false;
    }

    private List<ColumnModel> findColumnsToAdd() {
        List<ColumnModel> columnsToAdd = new ArrayList<>();
        for (ColumnModel columnModel : this.mTableModel.getColumnModels()) {
            if (!this.mTableModelDB.containsColumn(columnModel.getColumnName())) {
                columnsToAdd.add(columnModel);
            }
        }
        return columnsToAdd;
    }

    private List<String> findColumnsToRemove() {
        String tableName = this.mTableModel.getTableName();
        List<String> removeColumns = new ArrayList<>();
        for (ColumnModel columnModel : this.mTableModelDB.getColumnModels()) {
            String dbColumnName = columnModel.getColumnName();
            if (isNeedToRemove(dbColumnName)) {
                removeColumns.add(dbColumnName);
            }
        }
        LogUtil.d(AssociationUpdater.TAG, "remove columns from " + tableName + " >> " + removeColumns);
        return removeColumns;
    }

    private List<ColumnModel> findColumnTypesToChange() {
        List<ColumnModel> columnsToChangeType = new ArrayList<>();
        for (ColumnModel columnModelDB : this.mTableModelDB.getColumnModels()) {
            for (ColumnModel columnModel : this.mTableModel.getColumnModels()) {
                if (columnModelDB.getColumnName().equalsIgnoreCase(columnModel.getColumnName())) {
                    if (!columnModelDB.getColumnType().equalsIgnoreCase(columnModel.getColumnType())) {
                        columnsToChangeType.add(columnModel);
                    }
                    if (!this.hasConstraintChanged) {
                        LogUtil.d(AssociationUpdater.TAG, "default value db is:" + columnModelDB.getDefaultValue() + ", default value is:" + columnModel.getDefaultValue());
                        if (columnModelDB.isNullable() != columnModel.isNullable() || !columnModelDB.getDefaultValue().equalsIgnoreCase(columnModel.getDefaultValue()) || (columnModelDB.isUnique() && !columnModel.isUnique())) {
                            this.hasConstraintChanged = true;
                        }
                    }
                }
            }
        }
        return columnsToChangeType;
    }

    private boolean isNeedToRemove(String columnName) {
        return isRemovedFromClass(columnName) && !isIdColumn(columnName) && !isForeignKeyColumn(this.mTableModel, columnName);
    }

    private boolean isRemovedFromClass(String columnName) {
        return !this.mTableModel.containsColumn(columnName);
    }

    private String generateAddColumnSQL(ColumnModel columnModel) {
        return generateAddColumnSQL(this.mTableModel.getTableName(), columnModel);
    }

    private String[] getAddColumnSQLs(List<ColumnModel> columnModelList) {
        List<String> sqls = new ArrayList<>();
        for (ColumnModel columnModel : columnModelList) {
            sqls.add(generateAddColumnSQL(columnModel));
        }
        return (String[]) sqls.toArray(new String[0]);
    }

    private void removeColumns(List<String> removeColumnNames) {
        LogUtil.d(AssociationUpdater.TAG, "do addColumn");
        removeColumns(removeColumnNames, this.mTableModel.getTableName());
        for (String columnName : removeColumnNames) {
            this.mTableModelDB.removeColumnModelByName(columnName);
        }
    }

    private void addColumns(List<ColumnModel> columnModelList) {
        LogUtil.d(AssociationUpdater.TAG, "do addColumn");
        execute(getAddColumnSQLs(columnModelList), this.mDb);
        for (ColumnModel columnModel : columnModelList) {
            this.mTableModelDB.addColumnModel(columnModel);
        }
    }

    private void changeColumnsType(List<ColumnModel> columnModelList) {
        LogUtil.d(AssociationUpdater.TAG, "do changeColumnsType");
        List<String> columnNames = new ArrayList<>();
        if (columnModelList != null && !columnModelList.isEmpty()) {
            for (ColumnModel columnModel : columnModelList) {
                columnNames.add(columnModel.getColumnName());
            }
        }
        removeColumns(columnNames);
        addColumns(columnModelList);
    }

    private void changeColumnsConstraints() {
        if (this.hasConstraintChanged) {
            LogUtil.d(AssociationUpdater.TAG, "do changeColumnsConstraints");
            execute(getChangeColumnsConstraintsSQL(), this.mDb);
        }
    }

    private String[] getChangeColumnsConstraintsSQL() {
        String alterToTempTableSQL = generateAlterToTempTableSQL(this.mTableModel.getTableName());
        String createNewTableSQL = generateCreateTableSQL(this.mTableModel);
        Collection<? extends String> addForeignKeySQLs = generateAddForeignKeySQL();
        String dataMigrationSQL = generateDataMigrationSQL(this.mTableModelDB);
        String dropTempTableSQL = generateDropTempTableSQL(this.mTableModel.getTableName());
        List<String> sqls = new ArrayList<>();
        sqls.add(alterToTempTableSQL);
        sqls.add(createNewTableSQL);
        sqls.addAll(addForeignKeySQLs);
        sqls.add(dataMigrationSQL);
        sqls.add(dropTempTableSQL);
        LogUtil.d(AssociationUpdater.TAG, "generateChangeConstraintSQL >> ");
        for (String sql : sqls) {
            LogUtil.d(AssociationUpdater.TAG, sql);
        }
        LogUtil.d(AssociationUpdater.TAG, "<< generateChangeConstraintSQL");
        return (String[]) sqls.toArray(new String[0]);
    }

    private List<String> generateAddForeignKeySQL() {
        List<String> addForeignKeySQLs = new ArrayList<>();
        for (String foreignKeyColumn : getForeignKeyColumns(this.mTableModel)) {
            if (!this.mTableModel.containsColumn(foreignKeyColumn)) {
                ColumnModel columnModel = new ColumnModel();
                columnModel.setColumnName(foreignKeyColumn);
                columnModel.setColumnType("integer");
                addForeignKeySQLs.add(generateAddColumnSQL(this.mTableModel.getTableName(), columnModel));
            }
        }
        return addForeignKeySQLs;
    }
}
