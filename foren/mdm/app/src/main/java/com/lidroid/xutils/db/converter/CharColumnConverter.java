package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class CharColumnConverter implements ColumnConverter<Character> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Character getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return Character.valueOf((char) cursor.getInt(index));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public Character getFieldValue(String fieldStringValue) {
        if (TextUtils.isEmpty(fieldStringValue)) {
            return null;
        }
        return Character.valueOf(fieldStringValue.charAt(0));
    }

    public Object fieldValue2ColumnValue(Character fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        return Integer.valueOf(fieldValue.charValue());
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
