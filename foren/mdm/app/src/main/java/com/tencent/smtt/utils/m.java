package com.tencent.smtt.utils;

import com.tencent.smtt.utils.k;
import java.io.File;

/* loaded from: classes2.dex */
final class m implements k.a {
    @Override // com.tencent.smtt.utils.k.a
    public boolean a(File file, File file2) {
        return file.length() == file2.length() && file.lastModified() == file2.lastModified();
    }
}
