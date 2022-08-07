package u.aly;

import java.util.BitSet;

/* compiled from: TTupleProtocol.java */
/* loaded from: classes2.dex */
public final class db extends cp {

    /* compiled from: TTupleProtocol.java */
    /* loaded from: classes2.dex */
    public static class a implements cx {
        @Override // u.aly.cx
        public cv a(dj djVar) {
            return new db(djVar);
        }
    }

    public db(dj djVar) {
        super(djVar);
    }

    @Override // u.aly.cv
    public Class<? extends dd> D() {
        return dg.class;
    }

    public void a(BitSet bitSet, int i) throws cc {
        for (byte b : b(bitSet, i)) {
            a(b);
        }
    }

    public BitSet b(int i) throws cc {
        int ceil = (int) Math.ceil(i / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = u();
        }
        return a(bArr);
    }

    public static BitSet a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] b(BitSet bitSet, int i) {
        byte[] bArr = new byte[(int) Math.ceil(i / 8.0d)];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        return bArr;
    }
}
