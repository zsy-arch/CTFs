package c.i.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class v implements Parcelable.Creator<FragmentState> {
    @Override // android.os.Parcelable.Creator
    public FragmentState createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public FragmentState[] newArray(int i) {
        return new FragmentState[i];
    }
}
