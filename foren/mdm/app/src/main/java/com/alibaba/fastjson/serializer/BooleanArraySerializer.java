package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public class BooleanArraySerializer implements ObjectSerializer {
    public static BooleanArraySerializer instance = new BooleanArraySerializer();

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type boolean[] to java.lang.Object for r0v1 'array'  boolean[]
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:105)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:357)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:143)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:92)
        */
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(com.alibaba.fastjson.serializer.JSONSerializer r5, java.lang.Object r6, java.lang.Object r7, java.lang.reflect.Type r8) throws java.io.IOException {
        /*
            r4 = this;
            com.alibaba.fastjson.serializer.SerializeWriter r2 = r5.getWriter()
            if (r6 != 0) goto L_0x0018
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty
            boolean r3 = r2.isEnabled(r3)
            if (r3 == 0) goto L_0x0014
            java.lang.String r3 = "[]"
            r2.write(r3)
        L_0x0013:
            return
        L_0x0014:
            r2.writeNull()
            goto L_0x0013
        L_0x0018:
            boolean[] r6 = (boolean[]) r6
            r0 = r6
            boolean[] r0 = (boolean[]) r0
            r3 = 91
            r2.write(r3)
            r1 = 0
        L_0x0023:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x0035
            if (r1 == 0) goto L_0x002d
            r3 = 44
            r2.write(r3)
        L_0x002d:
            boolean r3 = r0[r1]
            r2.write(r3)
            int r1 = r1 + 1
            goto L_0x0023
        L_0x0035:
            r3 = 93
            r2.write(r3)
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.BooleanArraySerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }
}
