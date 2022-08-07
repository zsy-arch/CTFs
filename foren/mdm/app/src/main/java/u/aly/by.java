package u.aly;

import java.io.ByteArrayOutputStream;

/* compiled from: TByteArrayOutputStream.java */
/* loaded from: classes2.dex */
public class by extends ByteArrayOutputStream {
    public by(int i) {
        super(i);
    }

    public by() {
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
