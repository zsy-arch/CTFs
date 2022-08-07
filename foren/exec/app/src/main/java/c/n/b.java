package c.n;

import android.os.Parcelable;

/* loaded from: classes.dex */
public abstract class b {
    public int a(int i, int i2) {
        return !a(i2) ? i : ((c) this).f1084b.readInt();
    }

    public abstract void a();

    public void a(boolean z, boolean z2) {
    }

    public abstract boolean a(int i);

    public abstract b b();

    public abstract void b(int i);

    public void b(int i, int i2) {
        b(i2);
        ((c) this).f1084b.writeInt(i);
    }

    public abstract String c();

    public <T extends Parcelable> T a(T t, int i) {
        return !a(i) ? t : (T) ((c) this).f1084b.readParcelable(c.class.getClassLoader());
    }

    public static Class a(Class<? extends d> cls) {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }
}
