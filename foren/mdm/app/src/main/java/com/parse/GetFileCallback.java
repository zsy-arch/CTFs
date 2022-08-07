package com.parse;

import java.io.File;

/* loaded from: classes2.dex */
public interface GetFileCallback extends ParseCallback2<File, ParseException> {
    void done(File file, ParseException parseException);
}
