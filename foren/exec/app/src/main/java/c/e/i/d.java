package c.e.i;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.widget.NestedScrollView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements Parcelable.Creator<NestedScrollView.SavedState> {
    @Override // android.os.Parcelable.Creator
    public NestedScrollView.SavedState createFromParcel(Parcel parcel) {
        return new NestedScrollView.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public NestedScrollView.SavedState[] newArray(int i) {
        return new NestedScrollView.SavedState[i];
    }
}
