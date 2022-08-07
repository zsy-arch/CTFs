package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class IntegerColumnConverter implements ColumnConverter<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Integer getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return Integer.valueOf(cursor.getInt(index));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Integer getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Integer.valueOf(fieldStringValue);
    }

    public Object fieldValue2ColumnValue(Integer fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
