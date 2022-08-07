package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable;
import c.g.a.a;

/* loaded from: classes.dex */
public abstract class AbsSavedState implements Parcelable {

    /* renamed from: b */
    public final Parcelable f218b;

    /* renamed from: a */
    public static final AbsSavedState f217a = new AbsSavedState() { // from class: androidx.customview.view.AbsSavedState.1
    };
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new a();

    public AbsSavedState() {
        this.f218b = null;
    }

    public final Parcelable a() {
        return this.f218b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f218b, i);
    }

    public /* synthetic */ AbsSavedState(AnonymousClass1 r1) {
        this.f218b = null;
    }

    public AbsSavedState(Parcelable parcelable) {
        if (parcelable != null) {
            this.f218b = parcelable == f217a ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f218b = readParcelable == null ? f217a : readParcelable;
    }
}
