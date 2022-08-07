package c.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatDelegateImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z implements Parcelable.ClassLoaderCreator<AppCompatDelegateImpl.PanelFeatureState.SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public AppCompatDelegateImpl.PanelFeatureState.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return AppCompatDelegateImpl.PanelFeatureState.SavedState.a(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new AppCompatDelegateImpl.PanelFeatureState.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return AppCompatDelegateImpl.PanelFeatureState.SavedState.a(parcel, null);
    }
}
