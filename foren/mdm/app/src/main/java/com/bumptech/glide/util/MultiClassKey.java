package com.bumptech.glide.util;

/* loaded from: classes.dex */
public class MultiClassKey {
    private Class<?> first;
    private Class<?> second;

    public MultiClassKey() {
    }

    public MultiClassKey(Class<?> first, Class<?> second) {
        set(first, second);
    }

    public void set(Class<?> first, Class<?> second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "MultiClassKey{first=" + this.first + ", second=" + this.second + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MultiClassKey that = (MultiClassKey) o;
        return this.first.equals(that.first) && this.second.equals(that.second);
    }

    public int hashCode() {
        return (this.first.hashCode() * 31) + this.second.hashCode();
    }
}
