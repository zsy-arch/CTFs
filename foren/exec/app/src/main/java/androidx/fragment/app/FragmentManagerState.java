package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import c.i.a.u;

/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new u();

    /* renamed from: a */
    public FragmentState[] f255a;

    /* renamed from: b */
    public int[] f256b;

    /* renamed from: c */
    public BackStackState[] f257c;

    /* renamed from: d */
    public int f258d;

    /* renamed from: e */
    public int f259e;

    public FragmentManagerState() {
        this.f258d = -1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.f255a, i);
        parcel.writeIntArray(this.f256b);
        parcel.writeTypedArray(this.f257c, i);
        parcel.writeInt(this.f258d);
        parcel.writeInt(this.f259e);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f258d = -1;
        this.f255a = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.f256b = parcel.createIntArray();
        this.f257c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f258d = parcel.readInt();
        this.f259e = parcel.readInt();
    }
}
