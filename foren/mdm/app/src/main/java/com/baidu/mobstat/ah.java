package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.em.ui.EditActivity;
import com.yolanda.nohttp.db.Field;
import java.util.ArrayList;

/* loaded from: classes.dex */
class ah extends w {
    public ah() {
        super("app_change", "Create table if not exists app_change(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
    }

    private ArrayList<v> a(Cursor cursor) {
        ArrayList<v> arrayList = new ArrayList<>();
        if (!(cursor == null || cursor.getCount() == 0)) {
            int columnIndex = cursor.getColumnIndex(Field.ID);
            int columnIndex2 = cursor.getColumnIndex(f.az);
            int columnIndex3 = cursor.getColumnIndex(EditActivity.CONTENT);
            while (cursor.moveToNext()) {
                arrayList.add(new v(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3)));
            }
        }
        return arrayList;
    }

    @Override // com.baidu.mobstat.w
    public long a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f.az, str);
        contentValues.put(EditActivity.CONTENT, str2);
        return a(contentValues);
    }

    @Override // com.baidu.mobstat.w
    public ArrayList<v> a(int i, int i2) {
        Cursor a = a(f.az, i, i2);
        ArrayList<v> a2 = a(a);
        if (a != null) {
            a.close();
        }
        return a2;
    }

    @Override // com.baidu.mobstat.w
    public boolean b(long j) {
        return a(j);
    }
}
