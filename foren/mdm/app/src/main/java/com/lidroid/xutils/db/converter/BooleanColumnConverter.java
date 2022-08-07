package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class BooleanColumnConverter implements ColumnConverter<Boolean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Boolean getFieldValue(Cursor cursor, int index) {
        boolean z = true;
        if (cursor.isNull(index)) {
            return null;
        }
        if (cursor.getInt(index) != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Boolean getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Boolean.valueOf(fieldStringValue.length() == 1 ? "1".equals(fieldStringValue) : Boolean.valueOf(fieldStringValue).booleanValue());
    }

    public Object fieldValue2ColumnValue(Boolean fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        return Integer.valueOf(fieldValue.booleanValue() ? 1 : 0);
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
