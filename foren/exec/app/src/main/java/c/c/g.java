package c.c;

import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class g<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashMap<K, V> f703a;

    /* renamed from: b  reason: collision with root package name */
    public int f704b;

    /* renamed from: c  reason: collision with root package name */
    public int f705c;

    /* renamed from: d  reason: collision with root package name */
    public int f706d;

    /* renamed from: e  reason: collision with root package name */
    public int f707e;
    public int f;
    public int g;

    public g(int i) {
        if (i > 0) {
            this.f705c = i;
            this.f703a = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final V a(K k) {
        if (k != null) {
            synchronized (this) {
                V v = this.f703a.get(k);
                if (v != null) {
                    this.f++;
                    return v;
                }
                this.g++;
                return null;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final synchronized String toString() {
        int i;
        i = this.f + this.g;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f705c), Integer.valueOf(this.f), Integer.valueOf(this.g), Integer.valueOf(i != 0 ? (this.f * 100) / i : 0));
    }

    public final V a(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f706d++;
            this.f704b++;
            put = this.f703a.put(k, v);
            if (put != null) {
                this.f704b--;
            }
        }
        a(this.f705c);
        return put;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r3) {
        /*
            r2 = this;
        L_0x0000:
            monitor-enter(r2)
            int r0 = r2.f704b     // Catch: all -> 0x0069
            if (r0 < 0) goto L_0x004a
            java.util.LinkedHashMap<K, V> r0 = r2.f703a     // Catch: all -> 0x0069
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0069
            if (r0 == 0) goto L_0x0011
            int r0 = r2.f704b     // Catch: all -> 0x0069
            if (r0 != 0) goto L_0x004a
        L_0x0011:
            int r0 = r2.f704b     // Catch: all -> 0x0069
            if (r0 <= r3) goto L_0x0048
            java.util.LinkedHashMap<K, V> r0 = r2.f703a     // Catch: all -> 0x0069
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0069
            if (r0 == 0) goto L_0x001e
            goto L_0x0048
        L_0x001e:
            java.util.LinkedHashMap<K, V> r0 = r2.f703a     // Catch: all -> 0x0069
            java.util.Set r0 = r0.entrySet()     // Catch: all -> 0x0069
            java.util.Iterator r0 = r0.iterator()     // Catch: all -> 0x0069
            java.lang.Object r0 = r0.next()     // Catch: all -> 0x0069
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: all -> 0x0069
            java.lang.Object r1 = r0.getKey()     // Catch: all -> 0x0069
            r0.getValue()     // Catch: all -> 0x0069
            java.util.LinkedHashMap<K, V> r0 = r2.f703a     // Catch: all -> 0x0069
            r0.remove(r1)     // Catch: all -> 0x0069
            int r0 = r2.f704b     // Catch: all -> 0x0069
            int r0 = r0 + (-1)
            r2.f704b = r0     // Catch: all -> 0x0069
            int r0 = r2.f707e     // Catch: all -> 0x0069
            int r0 = r0 + 1
            r2.f707e = r0     // Catch: all -> 0x0069
            monitor-exit(r2)     // Catch: all -> 0x0069
            goto L_0x0000
        L_0x0048:
            monitor-exit(r2)     // Catch: all -> 0x0069
            return
        L_0x004a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch: all -> 0x0069
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x0069
            r0.<init>()     // Catch: all -> 0x0069
            java.lang.Class r1 = r2.getClass()     // Catch: all -> 0x0069
            java.lang.String r1 = r1.getName()     // Catch: all -> 0x0069
            r0.append(r1)     // Catch: all -> 0x0069
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: all -> 0x0069
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x0069
            r3.<init>(r0)     // Catch: all -> 0x0069
            throw r3     // Catch: all -> 0x0069
        L_0x0069:
            r3 = move-exception
            monitor-exit(r2)     // Catch: all -> 0x0069
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.g.a(int):void");
    }
}
