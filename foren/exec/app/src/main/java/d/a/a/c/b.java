package d.a.a.c;

import android.os.Environment;
import bccsejw.sxexrix.zaswnwt.MyApplication;
import c.a.a.C;
import java.io.File;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1612a = Environment.getExternalStorageDirectory().getAbsolutePath();

    /* renamed from: b  reason: collision with root package name */
    public static final String f1613b = MyApplication.f301a.getApplicationContext().getFilesDir().getAbsolutePath();

    /* renamed from: c  reason: collision with root package name */
    public static final String f1614c = MyApplication.f301a.getApplicationContext().getPackageName();

    /* renamed from: d  reason: collision with root package name */
    public static final String f1615d = C.a() + "test/";

    /* renamed from: e  reason: collision with root package name */
    public static final String f1616e = f1615d + f1614c + "/pic" + File.separator;

    public static /* synthetic */ String a() {
        return f1612a;
    }
}
