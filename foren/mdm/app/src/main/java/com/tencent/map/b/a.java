package com.tencent.map.b;

/* loaded from: classes2.dex */
public final class a {
    private static a a = null;

    /* renamed from: com.tencent.map.b.a$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        private AnonymousClass1() {
        }

        /* synthetic */ AnonymousClass1(a aVar, byte b) {
            this();
        }

        public boolean a(String str, String str2) {
            int a = a.a(a.this, str);
            if (str2.charAt(4) != i.a.charAt(((((a * 9) + 10) / 3) + 36) & 31)) {
                return false;
            }
            if (str2.charAt(7) != i.a.charAt((((a * 5) + 11) / 5) & 31)) {
                return false;
            }
            if (str2.charAt(12) != i.a.charAt((((a + 10) / 3) << 3) & 31)) {
                return false;
            }
            if (str2.charAt(14) != i.a.charAt((((a * 3) + 19) / 9) & 31)) {
                return false;
            }
            if (str2.charAt(19) != i.a.charAt((((a * 3) + 39) / 8) & 31)) {
                return false;
            }
            if (str2.charAt(21) != i.a.charAt((((a / 23) + 67) / 7) & 31)) {
                return false;
            }
            if (str2.charAt(26) != i.a.charAt(((((a + 23) / 6) + 3) * 7) & 31)) {
                return false;
            }
            int i = 0;
            for (int i2 = 0; i2 < str.length(); i2++) {
                i = i.b[(i ^ i.a(str.charAt(i2))) & 255] ^ ((i >> 8) & 255);
            }
            if (str2.charAt(0) != i.a.charAt(i & 31)) {
                return false;
            }
            return str2.charAt(1) == i.a.charAt((i >> 5) & 31);
        }
    }

    private a() {
    }

    static /* synthetic */ int a(a aVar, String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += i.a(str.charAt(i2));
        }
        return ((length << 7) + length) ^ i;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public final boolean a(String str, String str2) {
        if (!(i.a(str) && i.b(str2) && new AnonymousClass1(this, (byte) 0).a(str, str2))) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < 27; i2++) {
            i = i.b[(i ^ i.a(str2.charAt(i2))) & 255] ^ ((i >> 8) & 255);
        }
        return str2.charAt(27) != i.a.charAt(i & 31) ? false : str2.charAt(28) == i.a.charAt((i >> 5) & 31);
    }
}
