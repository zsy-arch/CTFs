package u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport.java */
/* loaded from: classes2.dex */
public class dh extends dj {
    protected InputStream a;
    protected OutputStream b;

    protected dh() {
        this.a = null;
        this.b = null;
    }

    public dh(InputStream inputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
    }

    public dh(OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.b = outputStream;
    }

    public dh(InputStream inputStream, OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
        this.b = outputStream;
    }

    @Override // u.aly.dj
    public boolean a() {
        return true;
    }

    @Override // u.aly.dj
    public void b() throws dk {
    }

    @Override // u.aly.dj
    public void c() {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // u.aly.dj
    public int a(byte[] bArr, int i, int i2) throws dk {
        if (this.a == null) {
            throw new dk(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new dk(4);
        } catch (IOException e) {
            throw new dk(0, e);
        }
    }

    @Override // u.aly.dj
    public void b(byte[] bArr, int i, int i2) throws dk {
        if (this.b == null) {
            throw new dk(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (IOException e) {
            throw new dk(0, e);
        }
    }

    @Override // u.aly.dj
    public void d() throws dk {
        if (this.b == null) {
            throw new dk(1, "Cannot flush null outputStream");
        }
        try {
            this.b.flush();
        } catch (IOException e) {
            throw new dk(0, e);
        }
    }
}
