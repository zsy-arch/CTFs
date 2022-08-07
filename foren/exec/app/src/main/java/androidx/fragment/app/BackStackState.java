package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import c.i.a.C0058a;
import c.i.a.C0059b;
import c.i.a.s;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new C0059b();

    /* renamed from: a */
    public final int[] f239a;

    /* renamed from: b */
    public final int f240b;

    /* renamed from: c */
    public final int f241c;

    /* renamed from: d */
    public final String f242d;

    /* renamed from: e */
    public final int f243e;
    public final int f;
    public final CharSequence g;
    public final int h;
    public final CharSequence i;
    public final ArrayList<String> j;
    public final ArrayList<String> k;
    public final boolean l;

    public BackStackState(C0058a aVar) {
        int size = aVar.f949b.size();
        this.f239a = new int[size * 6];
        if (aVar.i) {
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                C0058a.C0015a aVar2 = aVar.f949b.get(i2);
                int[] iArr = this.f239a;
                int i3 = i + 1;
                iArr[i] = aVar2.f953a;
                int i4 = i3 + 1;
                Fragment fragment = aVar2.f954b;
                iArr[i3] = fragment != null ? fragment.g : -1;
                int[] iArr2 = this.f239a;
                int i5 = i4 + 1;
                iArr2[i4] = aVar2.f955c;
                int i6 = i5 + 1;
                iArr2[i5] = aVar2.f956d;
                int i7 = i6 + 1;
                iArr2[i6] = aVar2.f957e;
                i = i7 + 1;
                iArr2[i7] = aVar2.f;
            }
            this.f240b = aVar.g;
            this.f241c = aVar.h;
            this.f242d = aVar.j;
            this.f243e = aVar.l;
            this.f = aVar.m;
            this.g = aVar.n;
            this.h = aVar.o;
            this.i = aVar.p;
            this.j = aVar.q;
            this.k = aVar.r;
            this.l = aVar.s;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public C0058a a(s sVar) {
        C0058a aVar = new C0058a(sVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f239a.length) {
            C0058a.C0015a aVar2 = new C0058a.C0015a();
            int i3 = i + 1;
            aVar2.f953a = this.f239a[i];
            if (s.f987a) {
                String str = "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f239a[i3];
            }
            int i4 = i3 + 1;
            int i5 = this.f239a[i3];
            if (i5 >= 0) {
                aVar2.f954b = sVar.i.get(i5);
            } else {
                aVar2.f954b = null;
            }
            int[] iArr = this.f239a;
            int i6 = i4 + 1;
            aVar2.f955c = iArr[i4];
            int i7 = i6 + 1;
            aVar2.f956d = iArr[i6];
            int i8 = i7 + 1;
            aVar2.f957e = iArr[i7];
            i = i8 + 1;
            aVar2.f = iArr[i8];
            aVar.f950c = aVar2.f955c;
            aVar.f951d = aVar2.f956d;
            aVar.f952e = aVar2.f957e;
            aVar.f = aVar2.f;
            aVar.f949b.add(aVar2);
            aVar2.f955c = aVar.f950c;
            aVar2.f956d = aVar.f951d;
            aVar2.f957e = aVar.f952e;
            aVar2.f = aVar.f;
            i2++;
        }
        aVar.g = this.f240b;
        aVar.h = this.f241c;
        aVar.j = this.f242d;
        aVar.l = this.f243e;
        aVar.i = true;
        aVar.m = this.f;
        aVar.n = this.g;
        aVar.o = this.h;
        aVar.p = this.i;
        aVar.q = this.j;
        aVar.r = this.k;
        aVar.s = this.l;
        aVar.a(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f239a);
        parcel.writeInt(this.f240b);
        parcel.writeInt(this.f241c);
        parcel.writeString(this.f242d);
        parcel.writeInt(this.f243e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
        parcel.writeInt(this.l ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f239a = parcel.createIntArray();
        this.f240b = parcel.readInt();
        this.f241c = parcel.readInt();
        this.f242d = parcel.readString();
        this.f243e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
        this.l = parcel.readInt() != 0;
    }
}
