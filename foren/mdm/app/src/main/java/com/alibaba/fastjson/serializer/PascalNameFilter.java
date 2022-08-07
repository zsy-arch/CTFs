package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public class PascalNameFilter implements NameFilter {
    @Override // com.alibaba.fastjson.serializer.NameFilter
    public String process(Object source, String name, Object value) {
        if (name == null || name.length() == 0) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
