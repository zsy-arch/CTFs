package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import c.i.a.AbstractC0066i;
import c.i.a.AbstractC0068k;
import c.i.a.s;
import c.i.a.t;
import c.i.a.v;
import c.j.r;
import e.a.a.a.a;

/* loaded from: classes.dex */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new v();

    /* renamed from: a */
    public final String f260a;

    /* renamed from: b */
    public final int f261b;

    /* renamed from: c */
    public final boolean f262c;

    /* renamed from: d */
    public final int f263d;

    /* renamed from: e */
    public final int f264e;
    public final String f;
    public final boolean g;
    public final boolean h;
    public final Bundle i;
    public final boolean j;
    public Bundle k;
    public Fragment l;

    public FragmentState(Fragment fragment) {
        this.f260a = fragment.getClass().getName();
        this.f261b = fragment.g;
        this.f262c = fragment.o;
        this.f263d = fragment.z;
        this.f264e = fragment.A;
        this.f = fragment.B;
        this.g = fragment.E;
        this.h = fragment.D;
        this.i = fragment.i;
        this.j = fragment.C;
    }

    public Fragment a(AbstractC0068k kVar, AbstractC0066i iVar, Fragment fragment, t tVar, r rVar) {
        if (this.l == null) {
            Context context = kVar.f971b;
            Bundle bundle = this.i;
            if (bundle != null) {
                bundle.setClassLoader(context.getClassLoader());
            }
            if (iVar != null) {
                this.l = iVar.a(context, this.f260a, this.i);
            } else {
                this.l = Fragment.a(context, this.f260a, this.i);
            }
            Bundle bundle2 = this.k;
            if (bundle2 != null) {
                bundle2.setClassLoader(context.getClassLoader());
                this.l.f247d = this.k;
            }
            this.l.a(this.f261b, fragment);
            Fragment fragment2 = this.l;
            fragment2.o = this.f262c;
            fragment2.q = true;
            fragment2.z = this.f263d;
            fragment2.A = this.f264e;
            fragment2.B = this.f;
            fragment2.E = this.g;
            fragment2.D = this.h;
            fragment2.C = this.j;
            fragment2.t = kVar.f973d;
            if (s.f987a) {
                StringBuilder a2 = a.a("Instantiated fragment ");
                a2.append(this.l);
                a2.toString();
            }
        }
        Fragment fragment3 = this.l;
        fragment3.w = tVar;
        fragment3.x = rVar;
        return fragment3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f260a);
        parcel.writeInt(this.f261b);
        parcel.writeInt(this.f262c ? 1 : 0);
        parcel.writeInt(this.f263d);
        parcel.writeInt(this.f264e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeBundle(this.i);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
    }

    public FragmentState(Parcel parcel) {
        this.f260a = parcel.readString();
        this.f261b = parcel.readInt();
        boolean z = true;
        this.f262c = parcel.readInt() != 0;
        this.f263d = parcel.readInt();
        this.f264e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt() != 0;
        this.h = parcel.readInt() != 0;
        this.i = parcel.readBundle();
        this.j = parcel.readInt() == 0 ? false : z;
        this.k = parcel.readBundle();
    }
}
