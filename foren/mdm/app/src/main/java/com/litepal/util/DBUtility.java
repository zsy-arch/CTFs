package com.litepal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DBUtility {
    private DBUtility() {
    }

    public static String getTableNameByClassName(String className) {
        if (TextUtils.isEmpty(className) || '.' == className.charAt(className.length() - 1)) {
            return null;
        }
        return className.substring(className.lastIndexOf(".") + 1);
    }

    public static List<String> getTableNameListByClassNameList(List<String> classNames) {
        List<String> tableNames = new ArrayList<>();
        if (classNames != null && !classNames.isEmpty()) {
            for (String className : classNames) {
                tableNames.add(getTableNameByClassName(className));
            }
        }
        return tableNames;
    }

    public static String getTableNameByForeignColumn(String foreignColumnName) {
        if (TextUtils.isEmpty(foreignColumnName) || !foreignColumnName.toLowerCase().endsWith(Field.ID)) {
            return null;
        }
        return foreignColumnName.substring(0, foreignColumnName.length() - Field.ID.length());
    }

    public static String getIntermediateTableName(String tableName, String associatedTableName) {
        if (TextUtils.isEmpty(tableName) || TextUtils.isEmpty(associatedTableName)) {
            return null;
        }
        if (tableName.toLowerCase().compareTo(associatedTableName.toLowerCase()) <= 0) {
            return tableName + "_" + associatedTableName;
        }
        return associatedTableName + "_" + tableName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
        if (r8.getInt(r8.getColumnIndexOrThrow("type")) != 1) goto L_0x004c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isIntermediateTable(java.lang.String r13, android.database.sqlite.SQLiteDatabase r14) {
        /*
            r12 = 1
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L_0x0051
            java.lang.String r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+"
            boolean r0 = r13.matches(r0)
            if (r0 == 0) goto L_0x0051
            r8 = 0
            java.lang.String r1 = "table_schema"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r14
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: Exception -> 0x0053, all -> 0x005d
            boolean r0 = r8.moveToFirst()     // Catch: Exception -> 0x0053, all -> 0x005d
            if (r0 == 0) goto L_0x004c
        L_0x0023:
            java.lang.String r0 = "name"
            int r0 = r8.getColumnIndexOrThrow(r0)     // Catch: Exception -> 0x0053, all -> 0x005d
            java.lang.String r10 = r8.getString(r0)     // Catch: Exception -> 0x0053, all -> 0x005d
            boolean r0 = r13.equalsIgnoreCase(r10)     // Catch: Exception -> 0x0053, all -> 0x005d
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "type"
            int r0 = r8.getColumnIndexOrThrow(r0)     // Catch: Exception -> 0x0053, all -> 0x005d
            int r11 = r8.getInt(r0)     // Catch: Exception -> 0x0053, all -> 0x005d
            if (r11 != r12) goto L_0x004c
            if (r8 == 0) goto L_0x0044
            r8.close()
        L_0x0044:
            r0 = r12
        L_0x0045:
            return r0
        L_0x0046:
            boolean r0 = r8.moveToNext()     // Catch: Exception -> 0x0053, all -> 0x005d
            if (r0 != 0) goto L_0x0023
        L_0x004c:
            if (r8 == 0) goto L_0x0051
            r8.close()
        L_0x0051:
            r0 = 0
            goto L_0x0045
        L_0x0053:
            r9 = move-exception
            r9.printStackTrace()     // Catch: all -> 0x005d
            if (r8 == 0) goto L_0x0051
            r8.close()
            goto L_0x0051
        L_0x005d:
            r0 = move-exception
            if (r8 == 0) goto L_0x0063
            r8.close()
        L_0x0063:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.util.DBUtility.isIntermediateTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    public static boolean isTableExists(String tableName, SQLiteDatabase db) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(db), tableName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isColumnExists(String columnName, String tableName, SQLiteDatabase db) {
        Cursor cursor;
        if (TextUtils.isEmpty(columnName) || TextUtils.isEmpty(tableName)) {
            return false;
        }
        try {
            boolean exist = false;
            cursor = null;
            try {
                cursor = db.rawQuery("pragma table_info(" + tableName + ")", null);
                if (cursor.moveToFirst()) {
                    while (true) {
                        if (!columnName.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        } else {
                            exist = true;
                            break;
                        }
                    }
                }
                if (cursor == null) {
                    return exist;
                }
                cursor.close();
                return exist;
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> findAllTableNames(android.database.sqlite.SQLiteDatabase r8) {
        /*
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r0 = 0
            java.lang.String r4 = "select * from sqlite_master where type = ?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: Exception -> 0x0039, all -> 0x0047
            r6 = 0
            java.lang.String r7 = "table"
            r5[r6] = r7     // Catch: Exception -> 0x0039, all -> 0x0047
            android.database.Cursor r0 = r8.rawQuery(r4, r5)     // Catch: Exception -> 0x0039, all -> 0x0047
            boolean r4 = r0.moveToFirst()     // Catch: Exception -> 0x0039, all -> 0x0047
            if (r4 == 0) goto L_0x0033
        L_0x001a:
            java.lang.String r4 = "tbl_name"
            int r4 = r0.getColumnIndexOrThrow(r4)     // Catch: Exception -> 0x0039, all -> 0x0047
            java.lang.String r2 = r0.getString(r4)     // Catch: Exception -> 0x0039, all -> 0x0047
            boolean r4 = r3.contains(r2)     // Catch: Exception -> 0x0039, all -> 0x0047
            if (r4 != 0) goto L_0x002d
            r3.add(r2)     // Catch: Exception -> 0x0039, all -> 0x0047
        L_0x002d:
            boolean r4 = r0.moveToNext()     // Catch: Exception -> 0x0039, all -> 0x0047
            if (r4 != 0) goto L_0x001a
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            r0.close()
        L_0x0038:
            return r3
        L_0x0039:
            r1 = move-exception
            r1.printStackTrace()     // Catch: all -> 0x0047
            com.litepal.exceptions.DatabaseGenerateException r4 = new com.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x0047
            java.lang.String r5 = r1.getMessage()     // Catch: all -> 0x0047
            r4.<init>(r5)     // Catch: all -> 0x0047
            throw r4     // Catch: all -> 0x0047
        L_0x0047:
            r4 = move-exception
            if (r0 == 0) goto L_0x004d
            r0.close()
        L_0x004d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.util.DBUtility.findAllTableNames(android.database.sqlite.SQLiteDatabase):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0090 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.litepal.tablemanager.model.TableModel findPragmaTableInfo(java.lang.String r14, android.database.sqlite.SQLiteDatabase r15) {
        /*
            r11 = 1
            boolean r12 = isTableExists(r14, r15)
            if (r12 == 0) goto L_0x00ae
            java.util.List r10 = findUniqueColumns(r14, r15)
            com.litepal.tablemanager.model.TableModel r7 = new com.litepal.tablemanager.model.TableModel
            r7.<init>()
            r7.setTableName(r14)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "pragma table_info("
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r13 = ")"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r0 = r12.toString()
            r2 = 0
            r12 = 0
            android.database.Cursor r2 = r15.rawQuery(r0, r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            boolean r12 = r2.moveToFirst()     // Catch: Exception -> 0x0099, all -> 0x00a7
            if (r12 == 0) goto L_0x008e
        L_0x0038:
            com.litepal.tablemanager.model.ColumnModel r1 = new com.litepal.tablemanager.model.ColumnModel     // Catch: Exception -> 0x0099, all -> 0x00a7
            r1.<init>()     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r12 = "name"
            int r12 = r2.getColumnIndexOrThrow(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r5 = r2.getString(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r12 = "type"
            int r12 = r2.getColumnIndexOrThrow(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r8 = r2.getString(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r12 = "notnull"
            int r12 = r2.getColumnIndexOrThrow(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            int r12 = r2.getInt(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            if (r12 == r11) goto L_0x0094
            r6 = r11
        L_0x005e:
            boolean r9 = r10.contains(r5)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r12 = "dflt_value"
            int r12 = r2.getColumnIndexOrThrow(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            java.lang.String r3 = r2.getString(r12)     // Catch: Exception -> 0x0099, all -> 0x00a7
            r1.setColumnName(r5)     // Catch: Exception -> 0x0099, all -> 0x00a7
            r1.setColumnType(r8)     // Catch: Exception -> 0x0099, all -> 0x00a7
            r1.setIsNullable(r6)     // Catch: Exception -> 0x0099, all -> 0x00a7
            r1.setIsUnique(r9)     // Catch: Exception -> 0x0099, all -> 0x00a7
            if (r3 == 0) goto L_0x0096
            java.lang.String r12 = "'"
            java.lang.String r13 = ""
            java.lang.String r3 = r3.replace(r12, r13)     // Catch: Exception -> 0x0099, all -> 0x00a7
        L_0x0082:
            r1.setDefaultValue(r3)     // Catch: Exception -> 0x0099, all -> 0x00a7
            r7.addColumnModel(r1)     // Catch: Exception -> 0x0099, all -> 0x00a7
            boolean r12 = r2.moveToNext()     // Catch: Exception -> 0x0099, all -> 0x00a7
            if (r12 != 0) goto L_0x0038
        L_0x008e:
            if (r2 == 0) goto L_0x0093
            r2.close()
        L_0x0093:
            return r7
        L_0x0094:
            r6 = 0
            goto L_0x005e
        L_0x0096:
            java.lang.String r3 = ""
            goto L_0x0082
        L_0x0099:
            r4 = move-exception
            r4.printStackTrace()     // Catch: all -> 0x00a7
            com.litepal.exceptions.DatabaseGenerateException r11 = new com.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x00a7
            java.lang.String r12 = r4.getMessage()     // Catch: all -> 0x00a7
            r11.<init>(r12)     // Catch: all -> 0x00a7
            throw r11     // Catch: all -> 0x00a7
        L_0x00a7:
            r11 = move-exception
            if (r2 == 0) goto L_0x00ad
            r2.close()
        L_0x00ad:
            throw r11
        L_0x00ae:
            com.litepal.exceptions.DatabaseGenerateException r11 = new com.litepal.exceptions.DatabaseGenerateException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Table doesn't exist when executing "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.util.DBUtility.findPragmaTableInfo(java.lang.String, android.database.sqlite.SQLiteDatabase):com.litepal.tablemanager.model.TableModel");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x007b A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0080 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> findUniqueColumns(java.lang.String r9, android.database.sqlite.SQLiteDatabase r10) {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            r4 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: Exception -> 0x0084, all -> 0x0092
            r7.<init>()     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r8 = "pragma index_list("
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r8 = ")"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r7 = r7.toString()     // Catch: Exception -> 0x0084, all -> 0x0092
            r8 = 0
            android.database.Cursor r2 = r10.rawQuery(r7, r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            boolean r7 = r2.moveToFirst()     // Catch: Exception -> 0x0084, all -> 0x0092
            if (r7 == 0) goto L_0x0079
        L_0x002b:
            java.lang.String r7 = "unique"
            int r7 = r2.getColumnIndexOrThrow(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            int r6 = r2.getInt(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            r7 = 1
            if (r6 != r7) goto L_0x0073
            java.lang.String r7 = "name"
            int r7 = r2.getColumnIndexOrThrow(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r5 = r2.getString(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: Exception -> 0x0084, all -> 0x0092
            r7.<init>()     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r8 = "pragma index_info("
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.StringBuilder r7 = r7.append(r5)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r8 = ")"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r7 = r7.toString()     // Catch: Exception -> 0x0084, all -> 0x0092
            r8 = 0
            android.database.Cursor r4 = r10.rawQuery(r7, r8)     // Catch: Exception -> 0x0084, all -> 0x0092
            boolean r7 = r4.moveToFirst()     // Catch: Exception -> 0x0084, all -> 0x0092
            if (r7 == 0) goto L_0x0073
            java.lang.String r7 = "name"
            int r7 = r4.getColumnIndexOrThrow(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            java.lang.String r0 = r4.getString(r7)     // Catch: Exception -> 0x0084, all -> 0x0092
            r1.add(r0)     // Catch: Exception -> 0x0084, all -> 0x0092
        L_0x0073:
            boolean r7 = r2.moveToNext()     // Catch: Exception -> 0x0084, all -> 0x0092
            if (r7 != 0) goto L_0x002b
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.close()
        L_0x007e:
            if (r4 == 0) goto L_0x0083
            r4.close()
        L_0x0083:
            return r1
        L_0x0084:
            r3 = move-exception
            r3.printStackTrace()     // Catch: all -> 0x0092
            com.litepal.exceptions.DatabaseGenerateException r7 = new com.litepal.exceptions.DatabaseGenerateException     // Catch: all -> 0x0092
            java.lang.String r8 = r3.getMessage()     // Catch: all -> 0x0092
            r7.<init>(r8)     // Catch: all -> 0x0092
            throw r7     // Catch: all -> 0x0092
        L_0x0092:
            r7 = move-exception
            if (r2 == 0) goto L_0x0098
            r2.close()
        L_0x0098:
            if (r4 == 0) goto L_0x009d
            r4.close()
        L_0x009d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.util.DBUtility.findUniqueColumns(java.lang.String, android.database.sqlite.SQLiteDatabase):java.util.List");
    }
}
