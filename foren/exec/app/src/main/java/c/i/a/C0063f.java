package c.i.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.i.a.f  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0063f implements Parcelable.ClassLoaderCreator<Fragment.SavedState> {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new Fragment.SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Fragment.SavedState[i];
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public Fragment.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Fragment.SavedState(parcel, classLoader);
    }
}
