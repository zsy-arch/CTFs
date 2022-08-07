package com.android.internal.http.multipart;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class StringPart extends PartBase {
    public static final String DEFAULT_CHARSET = "US-ASCII";
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_TRANSFER_ENCODING = "8bit";

    public StringPart(String name, String value, String charset) {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }

    public StringPart(String name, String value) {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }

    @Override // com.android.internal.http.multipart.Part
    protected void sendData(OutputStream out) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // com.android.internal.http.multipart.Part
    protected long lengthOfData() {
        throw new RuntimeException("Stub!");
    }

    @Override // com.android.internal.http.multipart.PartBase
    public void setCharSet(String charSet) {
        throw new RuntimeException("Stub!");
    }
}
