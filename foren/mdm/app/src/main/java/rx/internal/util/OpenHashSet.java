package rx.internal.util;

import java.util.Arrays;
import rx.functions.Action1;
import rx.internal.util.unsafe.Pow2;

/* loaded from: classes2.dex */
public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int capacity) {
        this(capacity, 0.75f);
    }

    public OpenHashSet(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        int c = Pow2.roundToPowerOfTwo(capacity);
        this.mask = c - 1;
        this.maxSize = (int) (c * loadFactor);
        this.keys = (T[]) new Object[c];
    }

    public boolean add(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 != null) {
            if (curr2.equals(value)) {
                return false;
            }
            do {
                pos = (pos + 1) & m;
                curr = a[pos];
                if (curr == null) {
                }
            } while (!curr.equals(value));
            return false;
        }
        a[pos] = value;
        int i = this.size + 1;
        this.size = i;
        if (i >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public boolean remove(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 == null) {
            return false;
        }
        if (curr2.equals(value)) {
            return removeEntry(pos, a, m);
        }
        do {
            pos = (pos + 1) & m;
            curr = a[pos];
            if (curr == null) {
                return false;
            }
        } while (!curr.equals(value));
        return removeEntry(pos, a, m);
    }

    boolean removeEntry(int pos, T[] a, int m) {
        T curr;
        this.size--;
        while (true) {
            while (true) {
                pos = (pos + 1) & m;
                curr = a[pos];
                if (curr != null) {
                    int slot = mix(curr.hashCode()) & m;
                    if (pos > pos) {
                        if (pos >= slot && slot > pos) {
                            break;
                        }
                    } else if (pos < slot && slot <= pos) {
                    }
                } else {
                    a[pos] = null;
                    return true;
                }
            }
            a[pos] = curr;
        }
    }

    public void clear(Action1<? super T> clearAction) {
        if (this.size != 0) {
            T[] a = this.keys;
            for (T t : a) {
                Object obj = (Object) t;
                if (obj != 0) {
                    clearAction.call(obj);
                }
            }
            Arrays.fill(a, (Object) null);
            this.size = 0;
        }
    }

    public void terminate() {
        this.size = 0;
        this.keys = (T[]) new Object[0];
    }

    void rehash() {
        T[] a = this.keys;
        int i = a.length;
        int newCap = i << 1;
        int m = newCap - 1;
        T[] b = (T[]) new Object[newCap];
        int j = this.size;
        while (true) {
            j--;
            if (j != 0) {
                do {
                    i--;
                } while (a[i] == null);
                int pos = mix(a[i].hashCode()) & m;
                if (b[pos] != null) {
                    do {
                        pos = (pos + 1) & m;
                    } while (b[pos] != null);
                }
                b[pos] = a[i];
            } else {
                this.mask = m;
                this.maxSize = (int) (newCap * this.loadFactor);
                this.keys = b;
                return;
            }
        }
    }

    static int mix(int x) {
        int h = x * INT_PHI;
        return (h >>> 16) ^ h;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T[] values() {
        return this.keys;
    }
}
