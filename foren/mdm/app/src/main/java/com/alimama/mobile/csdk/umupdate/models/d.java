package com.alimama.mobile.csdk.umupdate.models;

/* compiled from: UFPModule.java */
/* loaded from: classes.dex */
public enum d {
    UMENG {
        @Override // java.lang.Enum
        public String toString() {
            return "um";
        }
    },
    ALI {
        @Override // java.lang.Enum
        public String toString() {
            return "alip4p";
        }
    },
    WAP {
        @Override // java.lang.Enum
        public String toString() {
            return "webview";
        }
    };

    public static d a(String str) {
        d[] values = values();
        for (d dVar : values) {
            if (dVar.toString().equals(str)) {
                return dVar;
            }
        }
        return null;
    }
}
