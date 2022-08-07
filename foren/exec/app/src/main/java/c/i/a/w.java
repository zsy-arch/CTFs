package c.i.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.fragment.app.FragmentTabHost$SavedState;
import c.i.a.w;
import e.a.a.a.a;

/* loaded from: classes.dex */
class w implements Parcelable.Creator<FragmentTabHost$SavedState> {
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.fragment.app.FragmentTabHost$SavedState] */
    @Override // android.os.Parcelable.Creator
    public FragmentTabHost$SavedState createFromParcel(final Parcel parcel) {
        return new View.BaseSavedState(parcel) { // from class: androidx.fragment.app.FragmentTabHost$SavedState
            public static final Parcelable.Creator<FragmentTabHost$SavedState> CREATOR = new w();

            /* renamed from: a  reason: collision with root package name */
            public String f265a;

            {
                super(parcel);
                this.f265a = parcel.readString();
            }

            public String toString() {
                StringBuilder a2 = a.a("FragmentTabHost.SavedState{");
                a2.append(Integer.toHexString(System.identityHashCode(this)));
                a2.append(" curTab=");
                a2.append(this.f265a);
                a2.append("}");
                return a2.toString();
            }

            @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                super.writeToParcel(parcel2, i);
                parcel2.writeString(this.f265a);
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public FragmentTabHost$SavedState[] newArray(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}
