package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

/* loaded from: classes2.dex */
public class ByteArrayColumnConverter implements ColumnConverter<byte[]> {
    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public byte[] getFieldValue(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return cursor.getBlob(index);
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public byte[] getFieldValue(String fieldStringValue) {
        return null;
    }

    public Object fieldValue2ColumnValue(byte[] fieldValue) {
        return fieldValue;
    }

    @Override // com.lidroid.xutils.db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.BLOB;
    }
}
