package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class DateOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || !fieldType.equals("java.util.Date")) {
            return null;
        }
        return "integer";
    }
}
