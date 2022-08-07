package com.parse;

/* loaded from: classes2.dex */
enum PushType {
    NONE("none"),
    PPNS("ppns"),
    GCM("gcm");
    
    private final String pushType;

    PushType(String pushType) {
        this.pushType = pushType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PushType fromString(String pushType) {
        if ("none".equals(pushType)) {
            return NONE;
        }
        if ("ppns".equals(pushType)) {
            return PPNS;
        }
        if ("gcm".equals(pushType)) {
            return GCM;
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.pushType;
    }
}
