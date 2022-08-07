package com.litepal.tablemanager.typechange;

/* loaded from: classes2.dex */
public class BlobOrm extends OrmChange {
    @Override // com.litepal.tablemanager.typechange.OrmChange
    public String object2Relation(String fieldType) {
        if (fieldType == null || (!fieldType.equals("byte") && !fieldType.equals("java.lang.Byte"))) {
            return null;
        }
        return "blob";
    }
}
