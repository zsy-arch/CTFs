package com.baidu.mobstat;

import java.io.File;
import java.util.Comparator;

/* loaded from: classes.dex */
class am implements Comparator<File> {
    final /* synthetic */ ak a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(ak akVar) {
        this.a = akVar;
    }

    /* renamed from: a */
    public int compare(File file, File file2) {
        return (int) (file2.lastModified() - file.lastModified());
    }
}
