package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class aq implements FileFilter {
    final /* synthetic */ aj a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aq(aj ajVar) {
        this.a = ajVar;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.getName().endsWith(".jar");
    }
}
