package com.alibaba.fastjson.parser.deserializer;

/* loaded from: classes.dex */
public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    /* JADX INFO: Multiple debug info for r2v13 T: [D('val' long), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v14 T: [D('val' long), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v15 T: [D('val' long), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v16 T: [D('val' long), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v7 ??: [D('val' java.math.BigDecimal), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v8 T: [D('val' java.math.BigDecimal), D('val' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r2v9 T: [D('val' java.math.BigDecimal), D('val' java.lang.String)] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.math.BigDecimal, T] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r8, java.lang.reflect.Type r9, java.lang.Object r10) {
        /*
            r7 = this;
            r6 = 16
            com.alibaba.fastjson.parser.JSONLexer r0 = r8.getLexer()
            int r4 = r0.token()
            r5 = 2
            if (r4 != r5) goto L_0x0063
            java.lang.Class r4 = java.lang.Double.TYPE
            if (r9 == r4) goto L_0x0015
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r9 != r4) goto L_0x0025
        L_0x0015:
            java.lang.String r2 = r0.numberString()
            r0.nextToken(r6)
            double r4 = java.lang.Double.parseDouble(r2)
            java.lang.Double r2 = java.lang.Double.valueOf(r4)
        L_0x0024:
            return r2
        L_0x0025:
            long r2 = r0.longValue()
            r0.nextToken(r6)
            java.lang.Class r4 = java.lang.Short.TYPE
            if (r9 == r4) goto L_0x0034
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            if (r9 != r4) goto L_0x003b
        L_0x0034:
            int r4 = (int) r2
            short r4 = (short) r4
            java.lang.Short r2 = java.lang.Short.valueOf(r4)
            goto L_0x0024
        L_0x003b:
            java.lang.Class r4 = java.lang.Byte.TYPE
            if (r9 == r4) goto L_0x0043
            java.lang.Class<java.lang.Byte> r4 = java.lang.Byte.class
            if (r9 != r4) goto L_0x004a
        L_0x0043:
            int r4 = (int) r2
            byte r4 = (byte) r4
            java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
            goto L_0x0024
        L_0x004a:
            r4 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x005e
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x005e
            int r4 = (int) r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            goto L_0x0024
        L_0x005e:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x0024
        L_0x0063:
            int r4 = r0.token()
            r5 = 3
            if (r4 != r5) goto L_0x00ac
            java.lang.Class r4 = java.lang.Double.TYPE
            if (r9 == r4) goto L_0x0072
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r9 != r4) goto L_0x0082
        L_0x0072:
            java.lang.String r2 = r0.numberString()
            r0.nextToken(r6)
            double r4 = java.lang.Double.parseDouble(r2)
            java.lang.Double r2 = java.lang.Double.valueOf(r4)
            goto L_0x0024
        L_0x0082:
            java.math.BigDecimal r2 = r0.decimalValue()
            r0.nextToken(r6)
            java.lang.Class r4 = java.lang.Short.TYPE
            if (r9 == r4) goto L_0x0091
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            if (r9 != r4) goto L_0x009a
        L_0x0091:
            short r4 = r2.shortValue()
            java.lang.Short r2 = java.lang.Short.valueOf(r4)
            goto L_0x0024
        L_0x009a:
            java.lang.Class r4 = java.lang.Byte.TYPE
            if (r9 == r4) goto L_0x00a2
            java.lang.Class<java.lang.Byte> r4 = java.lang.Byte.class
            if (r9 != r4) goto L_0x0024
        L_0x00a2:
            byte r4 = r2.byteValue()
            java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
            goto L_0x0024
        L_0x00ac:
            java.lang.Object r1 = r8.parse()
            if (r1 != 0) goto L_0x00b5
            r2 = 0
            goto L_0x0024
        L_0x00b5:
            java.lang.Class r4 = java.lang.Double.TYPE
            if (r9 == r4) goto L_0x00bd
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r9 != r4) goto L_0x00c3
        L_0x00bd:
            java.lang.Double r2 = com.alibaba.fastjson.util.TypeUtils.castToDouble(r1)
            goto L_0x0024
        L_0x00c3:
            java.lang.Class r4 = java.lang.Short.TYPE
            if (r9 == r4) goto L_0x00cb
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            if (r9 != r4) goto L_0x00d1
        L_0x00cb:
            java.lang.Short r2 = com.alibaba.fastjson.util.TypeUtils.castToShort(r1)
            goto L_0x0024
        L_0x00d1:
            java.lang.Class r4 = java.lang.Byte.TYPE
            if (r9 == r4) goto L_0x00d9
            java.lang.Class<java.lang.Byte> r4 = java.lang.Byte.class
            if (r9 != r4) goto L_0x00df
        L_0x00d9:
            java.lang.Byte r2 = com.alibaba.fastjson.util.TypeUtils.castToByte(r1)
            goto L_0x0024
        L_0x00df:
            java.math.BigDecimal r2 = com.alibaba.fastjson.util.TypeUtils.castToBigDecimal(r1)
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.NumberDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }
}
