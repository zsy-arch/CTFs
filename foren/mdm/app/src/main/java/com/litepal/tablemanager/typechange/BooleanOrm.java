package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class BooleanOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || (!fieldType.equals("boolean") && !fieldType.equals("java.lang.Boolean"))) {
            return null;
        }
        return "integer";
    }
}
