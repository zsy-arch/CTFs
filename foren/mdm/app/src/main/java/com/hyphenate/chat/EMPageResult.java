package com.hyphenate.chat;

import java.util.List;

/* loaded from: classes2.dex */
public class EMPageResult<T> extends EMResult<List<T>> {
    private int pageCount;

    public int getPageCount() {
        return this.pageCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPageCount(int i) {
        this.pageCount = i;
    }
}
