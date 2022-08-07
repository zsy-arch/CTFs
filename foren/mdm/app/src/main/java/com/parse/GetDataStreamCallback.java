package com.parse;

import java.io.InputStream;

/* loaded from: classes2.dex */
public interface GetDataStreamCallback extends ParseCallback2<InputStream, ParseException> {
    void done(InputStream inputStream, ParseException parseException);
}
