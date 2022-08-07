package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: UMCCDBUtils.java */
/* loaded from: classes2.dex */
public class d {
    public static final String a = "/data/data/";
    public static final String b = "/databases/cc/";
    public static final String c = "cc.db";
    public static final int d = 1;
    public static final String e = "Id";
    public static final String f = "INTEGER";

    /* compiled from: UMCCDBUtils.java */
    /* loaded from: classes2.dex */
    public static class a {
        public static final String a = "aggregated";
        public static final String b = "aggregated_cache";

        /* compiled from: UMCCDBUtils.java */
        /* renamed from: u.aly.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0129a {
            public static final String a = "key";
            public static final String b = "totalTimestamp";
            public static final String c = "value";
            public static final String d = "count";
            public static final String e = "label";
            public static final String f = "timeWindowNum";
        }

        /* compiled from: UMCCDBUtils.java */
        /* loaded from: classes2.dex */
        public static class b {
            public static final String a = "TEXT";
            public static final String b = "TEXT";
            public static final String c = "INTEGER";
            public static final String d = "INTEGER";
            public static final String e = "TEXT";
            public static final String f = "TEXT";
        }
    }

    /* compiled from: UMCCDBUtils.java */
    /* loaded from: classes2.dex */
    public static class b {
        public static final String a = "limitedck";

        /* compiled from: UMCCDBUtils.java */
        /* loaded from: classes2.dex */
        public static class a {
            public static final String a = "ck";
        }

        /* compiled from: UMCCDBUtils.java */
        /* renamed from: u.aly.d$b$b  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0130b {
            public static final String a = "TEXT";
        }
    }

    /* compiled from: UMCCDBUtils.java */
    /* loaded from: classes2.dex */
    public static class c {
        public static final String a = "system";

        /* compiled from: UMCCDBUtils.java */
        /* loaded from: classes2.dex */
        public static class a {
            public static final String a = "key";
            public static final String b = "timeStamp";
            public static final String c = "count";
            public static final String d = "label";
        }

        /* compiled from: UMCCDBUtils.java */
        /* loaded from: classes2.dex */
        public static class b {
            public static final String a = "TEXT";
            public static final String b = "INTEGER";
            public static final String c = "INTEGER";
            public static final String d = "TEXT";
        }
    }

    public static String a(Context context) {
        return a + context.getPackageName() + b;
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }
}
