package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes2.dex */
class an implements FileFilter {
    final /* synthetic */ am a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public an(am amVar) {
        this.a = amVar;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.getName().endsWith(".dex");
    }
}
