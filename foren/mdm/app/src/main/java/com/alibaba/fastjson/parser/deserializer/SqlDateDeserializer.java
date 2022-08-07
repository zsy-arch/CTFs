package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/* loaded from: classes.dex */
public class SqlDateDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final SqlDateDeserializer instance = new SqlDateDeserializer();

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        long longVal;
        Object val2;
        if (val == null) {
            return null;
        }
        if (val instanceof Date) {
            val2 = (T) new java.sql.Date(((Date) val).getTime());
        } else if (val instanceof Number) {
            val2 = (T) new java.sql.Date(((Number) val).longValue());
        } else if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            JSONScanner dateLexer = new JSONScanner(strVal);
            try {
                if (dateLexer.scanISO8601DateIfMatch()) {
                    longVal = dateLexer.getCalendar().getTimeInMillis();
                } else {
                    try {
                        T t = (T) new java.sql.Date(parser.getDateFormat().parse(strVal).getTime());
                        dateLexer.close();
                        return t;
                    } catch (ParseException e) {
                        longVal = Long.parseLong(strVal);
                    }
                }
                dateLexer.close();
                return (T) new java.sql.Date(longVal);
            } catch (Throwable th) {
                dateLexer.close();
                throw th;
            }
        } else {
            throw new JSONException("parse error : " + val);
        }
        return (T) val2;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
