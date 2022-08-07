package internal.org.apache.http.entity.mime.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class InputStreamBody extends AbstractContentBody {
    private final String filename;
    private final InputStream in;

    public InputStreamBody(InputStream inputStream, String str) {
        this(inputStream, "application/octet-stream", str);
    }

    public InputStreamBody(InputStream inputStream, String str, String str2) {
        super(str);
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        this.in = inputStream;
        this.filename = str2;
    }

    @Override // internal.org.apache.http.entity.mime.content.ContentDescriptor
    public String getCharset() {
        return null;
    }

    @Override // internal.org.apache.http.entity.mime.content.ContentDescriptor
    public long getContentLength() {
        return -1L;
    }

    @Override // internal.org.apache.http.entity.mime.content.ContentBody
    public String getFilename() {
        return this.filename;
    }

    public InputStream getInputStream() {
        return this.in;
    }

    @Override // internal.org.apache.http.entity.mime.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    @Override // internal.org.apache.http.entity.mime.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = this.in.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    return;
                }
            }
        } finally {
            this.in.close();
        }
    }
}
