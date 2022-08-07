package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class NumericOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || (!fieldType.equals("int") && !fieldType.equals("java.lang.Integer") && !fieldType.equals("long") && !fieldType.equals("java.lang.Long") && !fieldType.equals("short") && !fieldType.equals("java.lang.Short"))) {
            return null;
        }
        return "integer";
    }
}
