package org.slf4j.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class BasicMarker implements Marker {
    private static final long serialVersionUID = 1803952589649545191L;
    private final String name;
    private List<Marker> referenceList;
    private static String OPEN = "[ ";
    private static String CLOSE = " ]";
    private static String SEP = ", ";

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicMarker(String name) {
        if (name == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = name;
    }

    @Override // org.slf4j.Marker
    public String getName() {
        return this.name;
    }

    @Override // org.slf4j.Marker
    public synchronized void add(Marker reference) {
        if (reference == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        } else if (!contains(reference) && !reference.contains(this)) {
            if (this.referenceList == null) {
                this.referenceList = new Vector();
            }
            this.referenceList.add(reference);
        }
    }

    @Override // org.slf4j.Marker
    public synchronized boolean hasReferences() {
        boolean z;
        if (this.referenceList != null) {
            if (this.referenceList.size() > 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Override // org.slf4j.Marker
    public boolean hasChildren() {
        return hasReferences();
    }

    @Override // org.slf4j.Marker
    public synchronized Iterator<Marker> iterator() {
        Iterator<Marker> it;
        if (this.referenceList != null) {
            it = this.referenceList.iterator();
        } else {
            it = Collections.emptyList().iterator();
        }
        return it;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
        r5.referenceList.remove(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
        r3 = true;
     */
    @Override // org.slf4j.Marker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean remove(org.slf4j.Marker r6) {
        /*
            r5 = this;
            r3 = 0
            monitor-enter(r5)
            java.util.List<org.slf4j.Marker> r4 = r5.referenceList     // Catch: all -> 0x0029
            if (r4 != 0) goto L_0x0008
        L_0x0006:
            monitor-exit(r5)
            return r3
        L_0x0008:
            java.util.List<org.slf4j.Marker> r4 = r5.referenceList     // Catch: all -> 0x0029
            int r2 = r4.size()     // Catch: all -> 0x0029
            r0 = 0
        L_0x000f:
            if (r0 >= r2) goto L_0x0006
            java.util.List<org.slf4j.Marker> r4 = r5.referenceList     // Catch: all -> 0x0029
            java.lang.Object r1 = r4.get(r0)     // Catch: all -> 0x0029
            org.slf4j.Marker r1 = (org.slf4j.Marker) r1     // Catch: all -> 0x0029
            boolean r4 = r6.equals(r1)     // Catch: all -> 0x0029
            if (r4 == 0) goto L_0x0026
            java.util.List<org.slf4j.Marker> r3 = r5.referenceList     // Catch: all -> 0x0029
            r3.remove(r0)     // Catch: all -> 0x0029
            r3 = 1
            goto L_0x0006
        L_0x0026:
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0029:
            r3 = move-exception
            monitor-exit(r5)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.slf4j.helpers.BasicMarker.remove(org.slf4j.Marker):boolean");
    }

    @Override // org.slf4j.Marker
    public boolean contains(Marker other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (equals(other)) {
            return true;
        } else {
            if (hasReferences()) {
                for (Marker ref : this.referenceList) {
                    if (ref.contains(other)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override // org.slf4j.Marker
    public boolean contains(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (this.name.equals(name)) {
            return true;
        } else {
            if (hasReferences()) {
                for (Marker ref : this.referenceList) {
                    if (ref.contains(name)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override // org.slf4j.Marker
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Marker)) {
            return false;
        }
        return this.name.equals(((Marker) obj).getName());
    }

    @Override // org.slf4j.Marker
    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator<Marker> it = iterator();
        StringBuilder sb = new StringBuilder(getName());
        sb.append(' ').append(OPEN);
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(SEP);
            }
        }
        sb.append(CLOSE);
        return sb.toString();
    }
}
