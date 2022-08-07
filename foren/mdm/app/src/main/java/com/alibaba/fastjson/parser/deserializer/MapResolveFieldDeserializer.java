package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: classes.dex */
public final class MapResolveFieldDeserializer extends FieldDeserializer {
    private final String key;
    private final Map map;

    public MapResolveFieldDeserializer(Map map, String index) {
        super(null, null);
        this.key = index;
        this.map = map;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void setValue(Object object, Object value) {
        this.map.put(this.key, value);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
    }
}
