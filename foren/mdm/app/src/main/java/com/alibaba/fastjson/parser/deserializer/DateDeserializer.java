package com.alibaba.fastjson.parser.deserializer;

/* loaded from: classes.dex */
public class DateDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final DateDeserializer instance = new DateDeserializer();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Calendar, T] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected <T> T cast(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.lang.reflect.Type r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            r8 = this;
            r0 = 0
            if (r12 != 0) goto L_0x0005
            r12 = r0
        L_0x0004:
            return r12
        L_0x0005:
            boolean r6 = r12 instanceof java.util.Date
            if (r6 != 0) goto L_0x0004
            boolean r6 = r12 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001a
            java.util.Date r0 = new java.util.Date
            java.lang.Number r12 = (java.lang.Number) r12
            long r6 = r12.longValue()
            r0.<init>(r6)
            r12 = r0
            goto L_0x0004
        L_0x001a:
            boolean r6 = r12 instanceof java.lang.String
            if (r6 == 0) goto L_0x0066
            r3 = r12
            java.lang.String r3 = (java.lang.String) r3
            int r6 = r3.length()
            if (r6 != 0) goto L_0x0029
            r12 = r0
            goto L_0x0004
        L_0x0029:
            com.alibaba.fastjson.parser.JSONScanner r2 = new com.alibaba.fastjson.parser.JSONScanner
            r2.<init>(r3)
            r6 = 0
            boolean r6 = r2.scanISO8601DateIfMatch(r6)     // Catch: all -> 0x0056
            if (r6 == 0) goto L_0x004a
            java.util.Calendar r0 = r2.getCalendar()     // Catch: all -> 0x0056
            java.lang.Class<java.util.Calendar> r6 = java.util.Calendar.class
            if (r10 != r6) goto L_0x0042
            r2.close()
            r12 = r0
            goto L_0x0004
        L_0x0042:
            java.util.Date r12 = r0.getTime()     // Catch: all -> 0x0056
            r2.close()
            goto L_0x0004
        L_0x004a:
            r2.close()
            java.text.DateFormat r1 = r9.getDateFormat()
            java.util.Date r12 = r1.parse(r3)     // Catch: ParseException -> 0x005b
            goto L_0x0004
        L_0x0056:
            r6 = move-exception
            r2.close()
            throw r6
        L_0x005b:
            r6 = move-exception
            long r4 = java.lang.Long.parseLong(r3)
            java.util.Date r12 = new java.util.Date
            r12.<init>(r4)
            goto L_0x0004
        L_0x0066:
            com.alibaba.fastjson.JSONException r6 = new com.alibaba.fastjson.JSONException
            java.lang.String r7 = "parse error"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.DateDeserializer.cast(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
