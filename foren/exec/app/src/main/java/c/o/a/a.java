package c.o.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager.widget.ViewPager$SavedState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements Parcelable.ClassLoaderCreator<ViewPager$SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public ViewPager$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager$SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new ViewPager$SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new ViewPager$SavedState(parcel, null);
    }
}
