package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.IOException;
import okhttp3.Response;

/* loaded from: classes.dex */
public interface ResponseParser<T extends OSSResult> {
    T parse(Response response) throws IOException;
}
