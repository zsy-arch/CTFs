package c.n;

import android.os.Parcel;
import android.util.SparseIntArray;

/* loaded from: classes.dex */
public class c extends b {

    /* renamed from: b  reason: collision with root package name */
    public final Parcel f1084b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1085c;

    /* renamed from: d  reason: collision with root package name */
    public final int f1086d;

    /* renamed from: e  reason: collision with root package name */
    public final String f1087e;
    public int g;

    /* renamed from: a  reason: collision with root package name */
    public final SparseIntArray f1083a = new SparseIntArray();
    public int f = -1;

    public c(Parcel parcel, int i, int i2, String str) {
        this.g = 0;
        this.f1084b = parcel;
        this.f1085c = i;
        this.f1086d = i2;
        this.g = this.f1085c;
        this.f1087e = str;
    }

    @Override // c.n.b
    public boolean a(int i) {
        int i2;
        while (true) {
            int i3 = this.g;
            if (i3 >= this.f1086d) {
                i2 = -1;
                break;
            }
            this.f1084b.setDataPosition(i3);
            int readInt = this.f1084b.readInt();
            int readInt2 = this.f1084b.readInt();
            this.g += readInt;
            if (readInt2 == i) {
                i2 = this.f1084b.dataPosition();
                break;
            }
        }
        if (i2 == -1) {
            return false;
        }
        this.f1084b.setDataPosition(i2);
        return true;
    }

    @Override // c.n.b
    public void b(int i) {
        a();
        this.f = i;
        this.f1083a.put(i, this.f1084b.dataPosition());
        this.f1084b.writeInt(0);
        this.f1084b.writeInt(i);
    }

    @Override // c.n.b
    public String c() {
        return this.f1084b.readString();
    }

    @Override // c.n.b
    public b b() {
        Parcel parcel = this.f1084b;
        int dataPosition = parcel.dataPosition();
        int i = this.g;
        if (i == this.f1085c) {
            i = this.f1086d;
        }
        return new c(parcel, dataPosition, i, this.f1087e + "  ");
    }

    @Override // c.n.b
    public void a() {
        int i = this.f;
        if (i >= 0) {
            int i2 = this.f1083a.get(i);
            int dataPosition = this.f1084b.dataPosition();
            this.f1084b.setDataPosition(i2);
            this.f1084b.writeInt(dataPosition - i2);
            this.f1084b.setDataPosition(dataPosition);
        }
    }
}
