package com.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
class ParseCountingFileHttpBody extends ParseFileHttpBody {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final int EOF = -1;
    private final ProgressCallback progressCallback;

    public ParseCountingFileHttpBody(File file, ProgressCallback progressCallback) {
        this(file, null, progressCallback);
    }

    public ParseCountingFileHttpBody(File file, String contentType, ProgressCallback progressCallback) {
        super(file, contentType);
        this.progressCallback = progressCallback;
    }

    @Override // com.parse.ParseFileHttpBody, com.parse.http.ParseHttpBody
    public void writeTo(OutputStream output) throws IOException {
        if (output == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        FileInputStream fileInput = new FileInputStream(this.file);
        try {
            byte[] buffer = new byte[4096];
            long totalLength = this.file.length();
            long position = 0;
            while (true) {
                int n = fileInput.read(buffer);
                if (-1 != n) {
                    output.write(buffer, 0, n);
                    position += n;
                    if (this.progressCallback != null) {
                        this.progressCallback.done(Integer.valueOf((int) ((100 * position) / totalLength)));
                    }
                } else {
                    return;
                }
            }
        } finally {
            ParseIOUtils.closeQuietly((InputStream) fileInput);
        }
    }
}
