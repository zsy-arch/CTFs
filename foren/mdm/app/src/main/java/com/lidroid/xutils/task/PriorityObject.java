package com.lidroid.xutils.task;

/* loaded from: classes2.dex */
public class PriorityObject<E> {
    public final E obj;
    public final Priority priority;

    public PriorityObject(Priority priority, E obj) {
        this.priority = priority == null ? Priority.DEFAULT : priority;
        this.obj = obj;
    }
}
