package com.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import com.litepal.tablemanager.model.TableModel;
import com.litepal.util.BaseUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.litepal.tablemanager.AssociationUpdater, com.litepal.tablemanager.Creator, com.litepal.tablemanager.AssociationCreator, com.litepal.tablemanager.Generator
    public void createOrUpgradeTable(SQLiteDatabase db, boolean force) {
        this.mTableModels = getAllTableModels();
        this.mDb = db;
        dropTables();
    }

    private void dropTables() {
        List<String> tableNamesToDrop = findTablesToDrop();
        dropTables(tableNamesToDrop, this.mDb);
        clearCopyInTableSchema(tableNamesToDrop);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0057 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.lang.String> findTablesToDrop() {
        /*
            r13 = this;
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r0 = r13.mDb     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.String r1 = "table_schema"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: Exception -> 0x005b, all -> 0x0065
            boolean r0 = r8.moveToFirst()     // Catch: Exception -> 0x005b, all -> 0x0065
            if (r0 == 0) goto L_0x0055
        L_0x001a:
            java.lang.String r0 = "name"
            int r0 = r8.getColumnIndexOrThrow(r0)     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.String r11 = r8.getString(r0)     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.String r0 = "type"
            int r0 = r8.getColumnIndexOrThrow(r0)     // Catch: Exception -> 0x005b, all -> 0x0065
            int r12 = r8.getInt(r0)     // Catch: Exception -> 0x005b, all -> 0x0065
            boolean r0 = r13.shouldDropThisTable(r11, r12)     // Catch: Exception -> 0x005b, all -> 0x0065
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = "AssociationUpdater"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: Exception -> 0x005b, all -> 0x0065
            r1.<init>()     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.String r2 = "need to drop "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch: Exception -> 0x005b, all -> 0x0065
            java.lang.String r1 = r1.toString()     // Catch: Exception -> 0x005b, all -> 0x0065
            com.litepal.util.LogUtil.d(r0, r1)     // Catch: Exception -> 0x005b, all -> 0x0065
            r9.add(r11)     // Catch: Exception -> 0x005b, all -> 0x0065
        L_0x004f:
            boolean r0 = r8.moveToNext()     // Catch: Exception -> 0x005b, all -> 0x0065
            if (r0 != 0) goto L_0x001a
        L_0x0055:
            if (r8 == 0) goto L_0x005a
            r8.close()
        L_0x005a:
            return r9
        L_0x005b:
            r10 = move-exception
            r10.printStackTrace()     // Catch: all -> 0x0065
            if (r8 == 0) goto L_0x005a
            r8.close()
            goto L_0x005a
        L_0x0065:
            r0 = move-exception
            if (r8 == 0) goto L_0x006b
            r8.close()
        L_0x006b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.tablemanager.Dropper.findTablesToDrop():java.util.List");
    }

    private List<String> pickTableNamesFromTableModels() {
        List<String> tableNames = new ArrayList<>();
        for (TableModel tableModel : this.mTableModels) {
            tableNames.add(tableModel.getTableName());
        }
        return tableNames;
    }

    private boolean shouldDropThisTable(String tableName, int tableType) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), tableName) && tableType == 0;
    }
}
