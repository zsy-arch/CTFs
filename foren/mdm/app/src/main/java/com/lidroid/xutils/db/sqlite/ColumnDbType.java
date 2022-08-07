package com.lidroid.xutils.db.sqlite;

/* loaded from: classes2.dex */
public enum ColumnDbType {
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    BLOB("BLOB");
    
    private String value;

    ColumnDbType(String value) {
        this.value = value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
