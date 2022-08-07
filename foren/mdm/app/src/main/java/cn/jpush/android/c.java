package cn.jpush.android;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class c extends Binder implements b {
    private static final String z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r1 > r2) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        cn.jpush.android.c.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
        r5 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        r5 = '`';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
        r5 = '}';
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r1 <= 1) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        r7 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0037;
            case 2: goto L_0x003a;
            case 3: goto L_0x003d;
            default: goto L_0x0017;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        r5 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        r1[r2] = (char) (r5 ^ r7);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r1 != 0) goto L_0x0025;
     */
    static {
        /*
            r6 = 1
            java.lang.String r0 = "b5N\u0017ot(\bS~o?\u0012\u0012veu)9~u:3\u0015~s>"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            if (r1 > r6) goto L_0x0027
        L_0x000b:
            r3 = r0
            r4 = r2
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x0010:
            char r7 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0035;
                case 1: goto L_0x0037;
                case 2: goto L_0x003a;
                case 3: goto L_0x003d;
                default: goto L_0x0017;
            }
        L_0x0017:
            r5 = 31
        L_0x0019:
            r5 = r5 ^ r7
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0025
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0010
        L_0x0025:
            r1 = r0
            r0 = r3
        L_0x0027:
            if (r1 > r2) goto L_0x000b
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.android.c.z = r0
            return
        L_0x0035:
            r5 = r6
            goto L_0x0019
        L_0x0037:
            r5 = 91
            goto L_0x0019
        L_0x003a:
            r5 = 96
            goto L_0x0019
        L_0x003d:
            r5 = 125(0x7d, float:1.75E-43)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.c.<clinit>():void");
    }

    public c() {
        attachInterface(this, z);
    }

    public static b a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(z);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new d(iBinder) : (b) queryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        int i3 = 0;
        boolean z2 = false;
        int i4 = 0;
        boolean z3 = false;
        int i5 = 0;
        int i6 = 0;
        switch (i) {
            case 1:
                parcel.enforceInterface(z);
                int readInt = parcel.readInt();
                long readLong = parcel.readLong();
                if (parcel.readInt() != 0) {
                    z2 = true;
                }
                a(readInt, readLong, z2, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface(z);
                int a = a(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 3:
                parcel.enforceInterface(z);
                b(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface(z);
                long a2 = a(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeLong(a2);
                return true;
            case 5:
                parcel.enforceInterface(z);
                b(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface(z);
                boolean a3 = a(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                if (a3) {
                    i4 = 1;
                }
                parcel2.writeInt(i4);
                return true;
            case 7:
                parcel.enforceInterface(z);
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    z3 = true;
                }
                b(readString, z3);
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface(z);
                String a4 = a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(a4);
                return true;
            case 9:
                parcel.enforceInterface(z);
                b(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface(z);
                int a5 = a();
                parcel2.writeNoException();
                parcel2.writeInt(a5);
                return true;
            case 11:
                parcel.enforceInterface(z);
                boolean a6 = a(parcel.readInt());
                parcel2.writeNoException();
                if (a6) {
                    i5 = 1;
                }
                parcel2.writeInt(i5);
                return true;
            case 12:
                parcel.enforceInterface(z);
                boolean b = b(parcel.readInt());
                parcel2.writeNoException();
                if (b) {
                    i6 = 1;
                }
                parcel2.writeInt(i6);
                return true;
            case 13:
                parcel.enforceInterface(z);
                int b2 = b();
                parcel2.writeNoException();
                parcel2.writeInt(b2);
                return true;
            case 14:
                parcel.enforceInterface(z);
                boolean c = c();
                parcel2.writeNoException();
                if (c) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 1598968902:
                parcel2.writeString(z);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
