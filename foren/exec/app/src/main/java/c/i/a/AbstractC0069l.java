package c.i.a;

import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* renamed from: c.i.a.l  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0069l {

    /* renamed from: c.i.a.l$a */
    /* loaded from: classes.dex */
    public interface a {
    }

    /* renamed from: c.i.a.l$b */
    /* loaded from: classes.dex */
    public static abstract class b {
    }

    /* renamed from: c.i.a.l$c */
    /* loaded from: classes.dex */
    public interface c {
        void onBackStackChanged();
    }

    public abstract List<Fragment> a();

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean b();

    public abstract boolean c();
}
