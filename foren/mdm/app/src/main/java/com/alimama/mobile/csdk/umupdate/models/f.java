package com.alimama.mobile.csdk.umupdate.models;

/* compiled from: UFPTemplate.java */
/* loaded from: classes.dex */
public enum f {
    LIST {
        @Override // java.lang.Enum
        public String toString() {
            return "applist";
        }
    },
    WATERFALL {
        @Override // java.lang.Enum
        public String toString() {
            return "waterfall";
        }
    },
    IMGLIST {
        @Override // java.lang.Enum
        public String toString() {
            return "imglist";
        }
    },
    GRID {
        @Override // java.lang.Enum
        public String toString() {
            return "iconlist";
        }
    };

    public static f a(String str) {
        try {
            String str2 = str.split("\\.")[0];
            f[] values = values();
            for (f fVar : values) {
                if (fVar.toString().equals(str2)) {
                    return fVar;
                }
            }
        } catch (Exception e2) {
        }
        return null;
    }
}
