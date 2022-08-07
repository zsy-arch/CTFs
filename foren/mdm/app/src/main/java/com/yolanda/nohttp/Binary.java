package com.yolanda.nohttp;

import com.yolanda.nohttp.able.CancelAble;
import com.yolanda.nohttp.able.FinishAble;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface Binary extends CancelAble, FinishAble {
    String getFileName();

    long getLength();

    String getMimeType();

    void onWriteBinary(OutputStream outputStream);
}
