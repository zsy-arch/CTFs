package c.i.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManagerState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u implements Parcelable.Creator<FragmentManagerState> {
    @Override // android.os.Parcelable.Creator
    public FragmentManagerState createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public FragmentManagerState[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}
