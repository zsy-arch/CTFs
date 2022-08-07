package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class LongColumnConverter implements ColumnConverter<Long> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Long getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(index));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Long getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Long.valueOf(fieldStringValue);
    }

    public Object fieldValue2ColumnValue(Long fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
