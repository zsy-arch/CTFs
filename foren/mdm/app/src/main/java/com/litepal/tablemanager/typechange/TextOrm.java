package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class TextOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || (!fieldType.equals("char") && !fieldType.equals("java.lang.Character") && !fieldType.equals("java.lang.String"))) {
            return null;
        }
        return "text";
    }
}
