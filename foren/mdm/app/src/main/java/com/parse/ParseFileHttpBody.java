package com.parse;

import com.parse.http.ParseHttpBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
class ParseFileHttpBody extends ParseHttpBody {
    final File file;

    public ParseFileHttpBody(File file) {
        this(file, null);
    }

    public ParseFileHttpBody(File file, String contentType) {
        super(contentType, file.length());
        this.file = file;
    }

    @Override // com.parse.http.ParseHttpBody
    public InputStream getContent() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // com.parse.http.ParseHttpBody
    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream can not be null");
        }
        FileInputStream fileInput = new FileInputStream(this.file);
        try {
            ParseIOUtils.copy(fileInput, out);
        } finally {
            ParseIOUtils.closeQuietly((InputStream) fileInput);
        }
    }
}
