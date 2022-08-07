package com.tencent.smtt.utils;

import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class Base64 {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ boolean f1469a = !Base64.class.desiredAssertionStatus();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f1470a;

        /* renamed from: b  reason: collision with root package name */
        public int f1471b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends a {
        public static final /* synthetic */ boolean g = !Base64.class.desiredAssertionStatus();
        public static final byte[] h = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        public static final byte[] i = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        /* renamed from: c  reason: collision with root package name */
        public int f1472c;

        /* renamed from: d  reason: collision with root package name */
        public final boolean f1473d;

        /* renamed from: e  reason: collision with root package name */
        public final boolean f1474e;
        public final boolean f;
        public final byte[] j;
        public int k;
        public final byte[] l;

        public b(int i2, byte[] bArr) {
            this.f1470a = bArr;
            boolean z = true;
            this.f1473d = (i2 & 1) == 0;
            this.f1474e = (i2 & 2) == 0;
            this.f = (i2 & 4) == 0 ? false : z;
            this.l = (i2 & 8) == 0 ? h : i;
            this.j = new byte[2];
            this.f1472c = 0;
            this.k = this.f1474e ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:101:0x00e9 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0097  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(byte[] r18, int r19, int r20, boolean r21) {
            /*
                Method dump skipped, instructions count: 528
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.Base64.b.a(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, null);
        int i4 = (i2 / 3) * 4;
        int i5 = 2;
        if (!bVar.f1473d) {
            int i6 = i2 % 3;
            if (i6 != 0) {
                if (i6 == 1) {
                    i4 += 2;
                } else if (i6 == 2) {
                    i4 += 3;
                }
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (bVar.f1474e && i2 > 0) {
            int i7 = ((i2 - 1) / 57) + 1;
            if (!bVar.f) {
                i5 = 1;
            }
            i4 += i7 * i5;
        }
        bVar.f1470a = new byte[i4];
        bVar.a(bArr, i, i2, true);
        if (f1469a || bVar.f1471b == i4) {
            return bVar.f1470a;
        }
        throw new AssertionError();
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(a(bArr, 0, bArr.length, i), "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }
}
