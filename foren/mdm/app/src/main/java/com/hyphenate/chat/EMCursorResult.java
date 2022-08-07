package com.hyphenate.chat;

import java.util.List;

/* loaded from: classes2.dex */
public class EMCursorResult<T> extends EMResult<List<T>> {
    private String cursor = "";

    public String getCursor() {
        return this.cursor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCursor(String str) {
        this.cursor = str;
    }
}
