package com.hyphenate;

/* loaded from: classes2.dex */
public class EMChangeEventData<T> {
    protected T newValue;
    protected T oldValue;

    public T getNewValue() {
        return this.newValue;
    }

    public T getOldValue() {
        return this.oldValue;
    }

    public void setNewValue(T t) {
        this.newValue = t;
    }

    public void setOldValue(T t) {
        this.oldValue = t;
    }
}
