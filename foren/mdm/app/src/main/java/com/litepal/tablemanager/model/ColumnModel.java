package com.litepal.tablemanager.model;

import android.text.TextUtils;
import com.yolanda.nohttp.db.Field;

/* loaded from: classes2.dex */
public class ColumnModel {
    private String columnName;
    private String columnType;
    private boolean isNullable = true;
    private boolean isUnique = false;
    private String defaultValue = "";

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public boolean isNullable() {
        return this.isNullable;
    }

    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        if (!"text".equalsIgnoreCase(this.columnType)) {
            this.defaultValue = defaultValue;
        } else if (!TextUtils.isEmpty(defaultValue)) {
            this.defaultValue = "'" + defaultValue + "'";
        }
    }

    public boolean isIdColumn() {
        return Field.ID.equalsIgnoreCase(this.columnName) || "id".equalsIgnoreCase(this.columnName);
    }
}
