package c.g.a;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements Parcelable.ClassLoaderCreator<AbsSavedState> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.ClassLoaderCreator
    public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.f217a;
        }
        throw new IllegalStateException("superState must be null");
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new AbsSavedState[i];
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    /* renamed from: createFromParcel  reason: collision with other method in class */
    public AbsSavedState mo5createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.f217a;
        }
        throw new IllegalStateException("superState must be null");
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }
}
