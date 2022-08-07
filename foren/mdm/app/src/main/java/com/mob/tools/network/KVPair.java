package com.mob.tools.network;

/* loaded from: classes2.dex */
public class KVPair<T> {
    public final String name;
    public final T value;

    public KVPair(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.name + " = " + this.value;
    }
}
