package c.k.a;

import c.j.h;
import c.j.s;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class a {
    public static <T extends h & s> a a(T t) {
        return new b(t, t.b());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);
}
