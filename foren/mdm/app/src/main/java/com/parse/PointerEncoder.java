package com.parse;

import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PointerEncoder extends PointerOrLocalIdEncoder {
    private static final PointerEncoder INSTANCE = new PointerEncoder();

    PointerEncoder() {
    }

    public static PointerEncoder get() {
        return INSTANCE;
    }

    @Override // com.parse.PointerOrLocalIdEncoder, com.parse.ParseEncoder
    public JSONObject encodeRelatedObject(ParseObject object) {
        if (object.getObjectId() != null) {
            return super.encodeRelatedObject(object);
        }
        throw new IllegalStateException("unable to encode an association with an unsaved ParseObject");
    }
}
