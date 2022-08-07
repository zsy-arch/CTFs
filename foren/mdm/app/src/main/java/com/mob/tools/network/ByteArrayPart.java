package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class ByteArrayPart extends HTTPPart {
    private BufferedByteArrayOutputStream buffer;

    public ByteArrayPart append(byte[] array) throws Throwable {
        if (this.buffer == null) {
            this.buffer = new BufferedByteArrayOutputStream(array.length);
        }
        this.buffer.write(array);
        this.buffer.flush();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    public InputStream getInputStream() throws Throwable {
        if (this.buffer == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        byte[] body = this.buffer.getBuffer();
        return (body == null || this.buffer.size() <= 0) ? new ByteArrayInputStream(new byte[0]) : new ByteArrayInputStream(body, 0, this.buffer.size());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    public long length() throws Throwable {
        if (this.buffer == null) {
            return 0L;
        }
        return this.buffer.size();
    }

    public String toString() {
        byte[] body;
        if (this.buffer == null || (body = this.buffer.getBuffer()) == null) {
            return null;
        }
        return Data.byteToHex(body, 0, this.buffer.size());
    }
}
