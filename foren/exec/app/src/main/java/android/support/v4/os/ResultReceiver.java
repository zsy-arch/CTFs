package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new b.a.a.a.b();

    /* renamed from: a */
    public final Handler f5a = null;

    /* renamed from: b */
    public b.a.a.a.a f6b;

    /* loaded from: classes.dex */
    public class a extends a.AbstractBinderC0000a {
        public a() {
            ResultReceiver.this = r1;
        }

        public void a(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f5a;
            if (handler != null) {
                handler.post(new b(i, bundle));
            } else {
                resultReceiver.a(i, bundle);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: a */
        public final int f8a;

        /* renamed from: b */
        public final Bundle f9b;

        public b(int i, Bundle bundle) {
            ResultReceiver.this = r1;
            this.f8a = i;
            this.f9b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f8a, this.f9b);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f6b = a.AbstractBinderC0000a.a(parcel.readStrongBinder());
    }

    public void a(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f6b == null) {
                this.f6b = new a();
            }
            parcel.writeStrongBinder(this.f6b.asBinder());
        }
    }
}
