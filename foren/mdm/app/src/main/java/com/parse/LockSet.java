package com.parse;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LockSet {
    private final Set<Lock> locks = new TreeSet(new Comparator<Lock>() { // from class: com.parse.LockSet.1
        public int compare(Lock lhs, Lock rhs) {
            return LockSet.getStableId(lhs).compareTo(LockSet.getStableId(rhs));
        }
    });
    private static WeakHashMap<Lock, Long> stableIds = new WeakHashMap<>();
    private static long nextStableId = 0;

    public LockSet(Collection<Lock> locks) {
        this.locks.addAll(locks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long getStableId(Lock lock) {
        Long valueOf;
        synchronized (stableIds) {
            if (stableIds.containsKey(lock)) {
                valueOf = stableIds.get(lock);
            } else {
                long id = nextStableId;
                nextStableId = 1 + id;
                stableIds.put(lock, Long.valueOf(id));
                valueOf = Long.valueOf(id);
            }
        }
        return valueOf;
    }

    public void lock() {
        for (Lock l : this.locks) {
            l.lock();
        }
    }

    public void unlock() {
        for (Lock l : this.locks) {
            l.unlock();
        }
    }
}
