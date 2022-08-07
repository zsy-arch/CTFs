package b.a.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements Parcelable.Creator<ResultReceiver> {
    @Override // android.os.Parcelable.Creator
    public ResultReceiver createFromParcel(Parcel parcel) {
        return new ResultReceiver(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public ResultReceiver[] newArray(int i) {
        return new ResultReceiver[i];
    }
}
