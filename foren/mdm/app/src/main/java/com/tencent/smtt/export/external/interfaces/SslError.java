package com.tencent.smtt.export.external.interfaces;

import android.net.http.SslCertificate;

/* loaded from: classes2.dex */
public interface SslError {
    boolean addError(int i);

    SslCertificate getCertificate();

    int getPrimaryError();

    boolean hasError(int i);
}
