package c.h.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.drawerlayout.widget.DrawerLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements Parcelable.ClassLoaderCreator<DrawerLayout.SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public DrawerLayout.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new DrawerLayout.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new DrawerLayout.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new DrawerLayout.SavedState(parcel, null);
    }
}
