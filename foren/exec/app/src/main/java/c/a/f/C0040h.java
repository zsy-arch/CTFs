package c.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.ActionMenuPresenter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.h  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0040h implements Parcelable.Creator<ActionMenuPresenter.SavedState> {
    @Override // android.os.Parcelable.Creator
    public ActionMenuPresenter.SavedState createFromParcel(Parcel parcel) {
        return new ActionMenuPresenter.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public ActionMenuPresenter.SavedState[] newArray(int i) {
        return new ActionMenuPresenter.SavedState[i];
    }
}
