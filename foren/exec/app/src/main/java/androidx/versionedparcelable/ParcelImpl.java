package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import c.n.a;
import c.n.b;
import c.n.c;
import c.n.d;
import com.tencent.smtt.sdk.BuildConfig;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new a();

    /* renamed from: a */
    public final d f297a;

    public ParcelImpl(Parcel parcel) {
        c cVar = new c(parcel, parcel.dataPosition(), parcel.dataSize(), BuildConfig.FLAVOR);
        String readString = cVar.f1084b.readString();
        d dVar = null;
        if (readString != null) {
            try {
                dVar = (d) Class.forName(readString, true, b.class.getClassLoader()).getDeclaredMethod("read", b.class).invoke(null, cVar.b());
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
            } catch (InvocationTargetException e5) {
                if (e5.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e5.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
            }
        }
        this.f297a = dVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c cVar = new c(parcel, parcel.dataPosition(), parcel.dataSize(), BuildConfig.FLAVOR);
        d dVar = this.f297a;
        if (dVar == null) {
            cVar.f1084b.writeString(null);
            return;
        }
        try {
            cVar.f1084b.writeString(b.a((Class<? extends d>) dVar.getClass()).getName());
            b b2 = cVar.b();
            try {
                b.a((Class<? extends d>) dVar.getClass()).getDeclaredMethod("write", dVar.getClass(), b.class).invoke(null, dVar, b2);
                b2.a();
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
            } catch (InvocationTargetException e5) {
                if (e5.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e5.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
            }
        } catch (ClassNotFoundException e6) {
            throw new RuntimeException(dVar.getClass().getSimpleName() + " does not have a Parcelizer", e6);
        }
    }
}
