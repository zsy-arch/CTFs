package c.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ba implements Parcelable.ClassLoaderCreator<SearchView.SavedState> {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public SearchView.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SearchView.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new SearchView.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new SearchView.SavedState(parcel, null);
    }
}
