package u.aly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;
import u.aly.aw;

/* compiled from: UMCCStorageManager.java */
/* loaded from: classes2.dex */
public class o {
    private static Context a;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UMCCStorageManager.java */
    /* loaded from: classes2.dex */
    public static final class a {
        private static final o a = new o();

        private a() {
        }
    }

    private o() {
        if (a != null) {
        }
    }

    public static o a(Context context) {
        a = context;
        return a.a;
    }

    public void a(f fVar) {
        try {
            SQLiteDatabase a2 = b.a(a).a();
            String a3 = a.a(a2);
            String a4 = q.a(System.currentTimeMillis());
            if (a3.equals("0")) {
                fVar.a("faild", false);
                return;
            }
            if (!a3.equals(a4)) {
                a.a(a2, fVar);
            } else {
                a.b(a2, fVar);
            }
        } catch (Exception e) {
            fVar.a(false, false);
            bo.e("load agg data error");
        } finally {
            b.a(a).c();
        }
    }

    public void a(f fVar, Map<List<String>, i> map) {
        try {
            try {
                a.a(b.a(a).b(), map.values());
                fVar.a("success", false);
                b.a(a).c();
            } catch (Exception e) {
                bo.e("save agg data error");
                b.a(a).c();
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public Map<String, List<aw.e>> a() {
        try {
            try {
                Map<String, List<aw.e>> b = a.b(b.a(a).a());
                b.a(a).c();
                return b;
            } catch (Exception e) {
                bo.e("upload agg date error");
                b.a(a).c();
                return null;
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public Map<String, List<aw.f>> b(f fVar) {
        try {
            try {
                Map<String, List<aw.f>> a2 = a.a(fVar, b.a(a).a());
                b.a(a).c();
                return a2;
            } catch (Exception e) {
                e.printStackTrace();
                b.a(a).c();
                return null;
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public void a(f fVar, boolean z) {
        try {
            try {
                a.a(b.a(a).b(), z, fVar);
                b.a(a).c();
            } catch (Exception e) {
                bo.e("notifyUploadSuccess error");
                b.a(a).c();
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public void a(f fVar, String str, long j, long j2) {
        try {
            try {
                a.a(b.a(a).b(), str, j, j2);
                fVar.a("success", false);
                b.a(a).c();
            } catch (Exception e) {
                bo.e("package size to big or envelopeOverflowPackageCount exception");
                b.a(a).c();
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public void a(f fVar, List<String> list) {
        try {
            try {
                a.a(fVar, b.a(a).b(), list);
                b.a(a).c();
            } catch (Exception e) {
                bo.e("saveToLimitCKTable exception");
                b.a(a).c();
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public void b(f fVar, Map<String, k> map) {
        try {
            try {
                a.a(b.a(a).b(), map, fVar);
                b.a(a).c();
            } catch (Exception e) {
                bo.e("arrgetated system buffer exception");
                b.a(a).c();
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public List<String> b() {
        try {
            try {
                List<String> c = a.c(b.a(a).a());
                b.a(a).c();
                return c;
            } catch (Exception e) {
                bo.e("loadCKToMemory exception");
                b.a(a).c();
                return null;
            }
        } catch (Throwable th) {
            b.a(a).c();
            throw th;
        }
    }

    public void c(f fVar, Map<List<String>, i> map) {
        try {
            a.a(fVar, b.a(a).b(), map.values());
        } catch (Exception e) {
            bo.e("cacheToData error");
        } finally {
            b.a(a).c();
        }
    }
}
