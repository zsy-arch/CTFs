package com.parse;

import com.parse.ParseRESTCommand;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseRESTFileCommand extends ParseRESTCommand {
    private final String contentType;
    private final byte[] data;
    private final File file;

    /* loaded from: classes2.dex */
    public static class Builder extends ParseRESTCommand.Init<Builder> {
        private File file;
        private byte[] data = null;
        private String contentType = null;

        public Builder() {
            method(ParseHttpRequest.Method.POST);
        }

        public Builder fileName(String fileName) {
            return httpPath(String.format("files/%s", fileName));
        }

        public Builder data(byte[] data) {
            this.data = data;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.parse.ParseRESTCommand.Init
        public Builder self() {
            return this;
        }

        public ParseRESTFileCommand build() {
            return new ParseRESTFileCommand(this);
        }
    }

    public ParseRESTFileCommand(Builder builder) {
        super(builder);
        if (builder.file == null || builder.data == null) {
            this.data = builder.data;
            this.contentType = builder.contentType;
            this.file = builder.file;
            return;
        }
        throw new IllegalArgumentException("File and data can not be set at the same time");
    }

    @Override // com.parse.ParseRESTCommand, com.parse.ParseRequest
    protected ParseHttpBody newBody(ProgressCallback progressCallback) {
        return progressCallback == null ? this.data != null ? new ParseByteArrayHttpBody(this.data, this.contentType) : new ParseFileHttpBody(this.file, this.contentType) : this.data != null ? new ParseCountingByteArrayHttpBody(this.data, this.contentType, progressCallback) : new ParseCountingFileHttpBody(this.file, this.contentType, progressCallback);
    }
}
