package c.l.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements Parcelable.ClassLoaderCreator<SlidingPaneLayout.SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public SlidingPaneLayout.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SlidingPaneLayout.SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new SlidingPaneLayout.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new SlidingPaneLayout.SavedState(parcel, null);
    }
}
