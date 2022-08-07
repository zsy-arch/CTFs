package com.parse;

import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NoObjectsEncoder extends ParseEncoder {
    private static final NoObjectsEncoder INSTANCE = new NoObjectsEncoder();

    NoObjectsEncoder() {
    }

    public static NoObjectsEncoder get() {
        return INSTANCE;
    }

    @Override // com.parse.ParseEncoder
    public JSONObject encodeRelatedObject(ParseObject object) {
        throw new IllegalArgumentException("ParseObjects not allowed here");
    }
}
