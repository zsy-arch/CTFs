package c.i.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.BackStackState;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.i.a.b  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0059b implements Parcelable.Creator<BackStackState> {
    @Override // android.os.Parcelable.Creator
    public BackStackState createFromParcel(Parcel parcel) {
        return new BackStackState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public BackStackState[] newArray(int i) {
        return new BackStackState[i];
    }
}
