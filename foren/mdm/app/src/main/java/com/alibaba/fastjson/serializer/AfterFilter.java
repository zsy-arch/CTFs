package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public abstract class AfterFilter implements SerializeFilter {
    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal<>();
    private static final ThreadLocal<Character> seperatorLocal = new ThreadLocal<>();
    private static final Character COMMA = ',';

    public abstract void writeAfter(Object obj);

    public final char writeAfter(JSONSerializer serializer, Object object, char seperator) {
        serializerLocal.set(serializer);
        seperatorLocal.set(Character.valueOf(seperator));
        writeAfter(object);
        serializerLocal.set(null);
        return seperatorLocal.get().charValue();
    }

    protected final void writeKeyValue(String key, Object value) {
        char seperator = seperatorLocal.get().charValue();
        serializerLocal.get().writeKeyValue(seperator, key, value);
        if (seperator != ',') {
            seperatorLocal.set(COMMA);
        }
    }
}
