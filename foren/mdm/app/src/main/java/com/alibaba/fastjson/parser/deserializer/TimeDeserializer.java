package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.sql.Time;

/* loaded from: classes.dex */
public class TimeDeserializer implements ObjectDeserializer {
    public static final TimeDeserializer instance = new TimeDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        long longVal;
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == 16) {
            lexer.nextToken(4);
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            }
            lexer.nextTokenWithColon(2);
            if (lexer.token() != 2) {
                throw new JSONException("syntax error");
            }
            long time = lexer.longValue();
            lexer.nextToken(13);
            if (lexer.token() != 13) {
                throw new JSONException("syntax error");
            }
            lexer.nextToken(16);
            return (T) new Time(time);
        }
        T t = (T) parser.parse();
        if (t == null) {
            return null;
        }
        if (t instanceof Time) {
            return t;
        }
        if (t instanceof Number) {
            return (T) new Time(((Number) t).longValue());
        }
        if (t instanceof String) {
            String strVal = (String) t;
            if (strVal.length() == 0) {
                return null;
            }
            JSONScanner dateLexer = new JSONScanner(strVal);
            if (dateLexer.scanISO8601DateIfMatch()) {
                longVal = dateLexer.getCalendar().getTimeInMillis();
            } else {
                longVal = Long.parseLong(strVal);
            }
            dateLexer.close();
            return (T) new Time(longVal);
        }
        throw new JSONException("parse error");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
