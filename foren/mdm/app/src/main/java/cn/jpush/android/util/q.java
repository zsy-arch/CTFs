package cn.jpush.android.util;

import java.io.File;
import java.util.Comparator;

/* loaded from: classes.dex */
final class q implements Comparator<File> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(File file, File file2) {
        File file3 = file;
        File file4 = file2;
        if (file3.lastModified() > file4.lastModified()) {
            return -1;
        }
        return file3.lastModified() < file4.lastModified() ? 1 : 0;
    }
}
