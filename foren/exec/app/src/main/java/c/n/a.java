package c.n;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.ParcelImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements Parcelable.Creator<ParcelImpl> {
    @Override // android.os.Parcelable.Creator
    public ParcelImpl createFromParcel(Parcel parcel) {
        return new ParcelImpl(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public ParcelImpl[] newArray(int i) {
        return new ParcelImpl[i];
    }
}
