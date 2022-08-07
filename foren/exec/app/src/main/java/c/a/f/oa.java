package c.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class oa implements Parcelable.ClassLoaderCreator<Toolbar.SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public Toolbar.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Toolbar.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Toolbar.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new Toolbar.SavedState(parcel, null);
    }
}
