package b.a.a.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.os.ResultReceiver;

/* loaded from: classes.dex */
public interface a extends IInterface {

    /* renamed from: b.a.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC0000a extends Binder implements a {

        /* renamed from: b.a.a.a.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0001a implements a {

            /* renamed from: a  reason: collision with root package name */
            public IBinder f300a;

            public C0001a(IBinder iBinder) {
                this.f300a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f300a;
            }
        }

        public AbstractBinderC0000a() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new C0001a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                ((ResultReceiver.a) this).a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.support.v4.os.IResultReceiver");
                return true;
            }
        }
    }
}
