package com.parse;

import com.parse.http.ParseHttpBody;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
class ParseByteArrayHttpBody extends ParseHttpBody {
    final byte[] content;
    final InputStream contentInputStream;

    public ParseByteArrayHttpBody(String content, String contentType) throws UnsupportedEncodingException {
        this(content.getBytes("UTF-8"), contentType);
    }

    public ParseByteArrayHttpBody(byte[] content, String contentType) {
        super(contentType, content.length);
        this.content = content;
        this.contentInputStream = new ByteArrayInputStream(content);
    }

    @Override // com.parse.http.ParseHttpBody
    public InputStream getContent() {
        return this.contentInputStream;
    }

    @Override // com.parse.http.ParseHttpBody
    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        out.write(this.content);
    }
}
