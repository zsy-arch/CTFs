package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: classes.dex */
public class LongFieldDeserializer extends FieldDeserializer {
    private final ObjectDeserializer fieldValueDeserilizer;

    public LongFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo);
        this.fieldValueDeserilizer = mapping.getDeserializer(fieldInfo);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        Long value;
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == 2) {
            long val = lexer.longValue();
            lexer.nextToken(16);
            if (object == null) {
                fieldValues.put(this.fieldInfo.getName(), Long.valueOf(val));
            } else {
                setValue(object, val);
            }
        } else {
            if (lexer.token() == 8) {
                value = null;
                lexer.nextToken(16);
            } else {
                value = TypeUtils.castToLong(parser.parse());
            }
            if (value != null || getFieldClass() != Long.TYPE) {
                if (object == null) {
                    fieldValues.put(this.fieldInfo.getName(), value);
                } else {
                    setValue(object, value);
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return this.fieldValueDeserilizer.getFastMatchToken();
    }
}
