package com.tencent.smtt.utils;

import com.tencent.smtt.utils.k;
import java.io.InputStream;
import java.util.zip.ZipEntry;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class l implements k.b {
    final /* synthetic */ String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(String str) {
        this.a = str;
    }

    @Override // com.tencent.smtt.utils.k.b
    public boolean a(InputStream inputStream, ZipEntry zipEntry, String str) {
        boolean b;
        try {
            b = k.b(inputStream, zipEntry, this.a, str);
            return b;
        } catch (Exception e) {
            throw new Exception("copyFileIfChanged Exception", e);
        }
    }
}
