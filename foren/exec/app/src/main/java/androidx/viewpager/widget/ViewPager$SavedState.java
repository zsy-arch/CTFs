package androidx.viewpager.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import c.o.a.a;

/* loaded from: classes.dex */
public class ViewPager$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<ViewPager$SavedState> CREATOR = new a();

    /* renamed from: a */
    public int f298a;

    /* renamed from: b */
    public Parcelable f299b;

    public ViewPager$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        classLoader = classLoader == null ? ViewPager$SavedState.class.getClassLoader() : classLoader;
        this.f298a = parcel.readInt();
        this.f299b = parcel.readParcelable(classLoader);
    }

    public String toString() {
        StringBuilder a2 = e.a.a.a.a.a("FragmentPager.SavedState{");
        a2.append(Integer.toHexString(System.identityHashCode(this)));
        a2.append(" position=");
        a2.append(this.f298a);
        a2.append("}");
        return a2.toString();
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(super.f218b, i);
        parcel.writeInt(this.f298a);
        parcel.writeParcelable(this.f299b, i);
    }
}
