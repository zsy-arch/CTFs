package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class DateFormatDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final DateFormatDeserializer instance = new DateFormatDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() != 0) {
                return (T) new SimpleDateFormat(strVal);
            }
            return null;
        }
        throw new JSONException("parse error");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 4;
    }
}
