package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;

/* loaded from: classes.dex */
public class CalendarCodec implements ObjectSerializer, ObjectDeserializer {
    public static final CalendarCodec instance = new CalendarCodec();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        serializer.write(((Calendar) object).getTime());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Calendar, T] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r5, java.lang.reflect.Type r6, java.lang.Object r7) {
        /*
            r4 = this;
            com.alibaba.fastjson.parser.deserializer.DateDeserializer r3 = com.alibaba.fastjson.parser.deserializer.DateDeserializer.instance
            java.lang.Object r2 = r3.deserialze(r5, r6, r7)
            boolean r3 = r2 instanceof java.util.Calendar
            if (r3 == 0) goto L_0x000b
        L_0x000a:
            return r2
        L_0x000b:
            r1 = r2
            java.util.Date r1 = (java.util.Date) r1
            if (r1 != 0) goto L_0x0012
            r2 = 0
            goto L_0x000a
        L_0x0012:
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            r0.setTime(r1)
            r2 = r0
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.CalendarCodec.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
