package c.e.e;

import java.util.Comparator;

/* loaded from: classes.dex */
class e implements Comparator<byte[]> {
    @Override // java.util.Comparator
    public int compare(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length != bArr4.length) {
            i2 = bArr3.length;
            i = bArr4.length;
        } else {
            for (int i3 = 0; i3 < bArr3.length; i3++) {
                if (bArr3[i3] != bArr4[i3]) {
                    i2 = bArr3[i3];
                    i = bArr4[i3];
                }
            }
            return 0;
        }
        return (i2 == 1 ? 1 : 0) - (i == 1 ? 1 : 0);
    }
}
