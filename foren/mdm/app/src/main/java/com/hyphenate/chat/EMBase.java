package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMABase;

/* loaded from: classes2.dex */
public class EMBase<T> {
    protected T emaObject;

    public boolean equals(Object obj) {
        return (this.emaObject == null || !(this.emaObject instanceof EMABase) || obj == null || !(obj instanceof EMBase)) ? (this.emaObject == null || !(this.emaObject instanceof EMABase) || obj == null || !(obj instanceof EMABase)) ? super.equals(obj) : this.emaObject.equals(obj) : this.emaObject.equals(((EMBase) obj).emaObject);
    }

    public int hashCode() {
        return (this.emaObject == null || !(this.emaObject instanceof EMABase)) ? super.hashCode() : this.emaObject.hashCode();
    }
}
