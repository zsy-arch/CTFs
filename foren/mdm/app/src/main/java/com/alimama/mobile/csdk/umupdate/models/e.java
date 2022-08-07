package com.alimama.mobile.csdk.umupdate.models;

/* compiled from: UFPResType.java */
/* loaded from: classes.dex */
public enum e {
    APP {
        @Override // java.lang.Enum
        public String toString() {
            return "applist";
        }
    },
    TB_ITEM {
        @Override // java.lang.Enum
        public String toString() {
            return "itemlist";
        }
    },
    TUAN {
        @Override // java.lang.Enum
        public String toString() {
            return "tuan";
        }
    },
    LOTTERY {
        @Override // java.lang.Enum
        public String toString() {
            return "lottery";
        }
    };

    public static e a(String str) {
        e[] values = values();
        for (e eVar : values) {
            if (eVar.toString().equals(str)) {
                return eVar;
            }
        }
        return null;
    }
}
