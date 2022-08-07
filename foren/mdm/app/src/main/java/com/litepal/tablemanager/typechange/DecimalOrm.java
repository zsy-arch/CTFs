package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class DecimalOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || (!fieldType.equals("float") && !fieldType.equals("java.lang.Float") && !fieldType.equals("double") && !fieldType.equals("java.lang.Double"))) {
            return null;
        }
        return "real";
    }
}
