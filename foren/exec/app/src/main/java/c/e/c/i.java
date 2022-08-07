package c.e.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import c.a.a.C;
import c.e.b.a.c;
import c.e.b.a.d;
import c.e.e.f;
import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes.dex */
public class i {

    /* loaded from: classes.dex */
    public interface a<T> {
        int a(T t);

        boolean b(T t);
    }

    public static <T> T a(T[] tArr, int i, a<T> aVar) {
        int i2 = (i & 1) == 0 ? TbsListener.ErrorCode.INFO_CODE_BASE : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = (Math.abs(aVar.a(t2) - i2) * 2) + (aVar.b(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, f.b[] bVarArr, int i) {
        throw null;
    }

    public f.b a(f.b[] bVarArr, int i) {
        return (f.b) a(bVarArr, i, new h(this));
    }

    public Typeface a(Context context, InputStream inputStream) {
        File a2 = C.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (!C.a(a2, inputStream)) {
                return null;
            }
            return Typeface.createFromFile(a2.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File a2 = C.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (!C.a(a2, resources, i)) {
                return null;
            }
            return Typeface.createFromFile(a2.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    public Typeface a(Context context, c cVar, Resources resources, int i) {
        d[] dVarArr = cVar.f750a;
        int i2 = (i & 1) == 0 ? TbsListener.ErrorCode.INFO_CODE_BASE : 700;
        boolean z = (i & 2) != 0;
        d dVar = null;
        int i3 = Integer.MAX_VALUE;
        for (d dVar2 : dVarArr) {
            int abs = (Math.abs(dVar2.f752b - i2) * 2) + (dVar2.f753c == z ? 0 : 1);
            if (dVar == null || i3 > abs) {
                dVar = dVar2;
                i3 = abs;
            }
        }
        if (dVar == null) {
            return null;
        }
        return c.a(context, resources, dVar.f, dVar.f751a, i);
    }
}
