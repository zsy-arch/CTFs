package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class StringColumnConverter implements ColumnConverter<String> {
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public String getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return cursor.getString(index);
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public String getFieldValue(String fieldStringValue) {
        return fieldStringValue;
    }

    public Object fieldValue2ColumnValue(String fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
