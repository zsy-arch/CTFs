package c.e.g;

import com.tencent.smtt.sdk.TbsListener;
import java.io.Writer;

/* loaded from: classes.dex */
public class a extends Writer {

    /* renamed from: a  reason: collision with root package name */
    public final String f836a;

    /* renamed from: b  reason: collision with root package name */
    public StringBuilder f837b = new StringBuilder((int) TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);

    public a(String str) {
        this.f836a = str;
    }

    public final void a() {
        if (this.f837b.length() > 0) {
            String str = this.f836a;
            this.f837b.toString();
            StringBuilder sb = this.f837b;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                a();
            } else {
                this.f837b.append(c2);
            }
        }
    }
}
