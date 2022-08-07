package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class FloatColumnConverter implements ColumnConverter<Float> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Float getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return Float.valueOf(cursor.getFloat(index));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Float getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Float.valueOf(fieldStringValue);
    }

    public Object fieldValue2ColumnValue(Float fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
}
