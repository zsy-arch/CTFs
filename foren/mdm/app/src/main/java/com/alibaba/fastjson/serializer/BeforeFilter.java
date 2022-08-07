package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public abstract class BeforeFilter implements SerializeFilter {
    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal<>();
    private static final ThreadLocal<Character> seperatorLocal = new ThreadLocal<>();
    private static final Character COMMA = ',';

    public abstract void writeBefore(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char writeBefore(JSONSerializer serializer, Object object, char seperator) {
        serializerLocal.set(serializer);
        seperatorLocal.set(Character.valueOf(seperator));
        writeBefore(object);
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
