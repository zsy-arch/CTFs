package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class ShortColumnConverter implements ColumnConverter<Short> {
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Short getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return Short.valueOf(cursor.getShort(index));
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Short getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Short.valueOf(fieldStringValue);
    }

    public Object fieldValue2ColumnValue(Short fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
