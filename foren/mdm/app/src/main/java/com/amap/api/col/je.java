package com.amap.api.col;

/* compiled from: Beacon.java */
/* loaded from: classes.dex */
public final class je implements Comparable<je> {
    public String a;
    public String b;
    public byte[] c;
    public String d;
    public String e;
    public int f;
    public int g;
    public long h;
    public int i;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int compareTo(je jeVar) {
        if (this.g < jeVar.g) {
            return 1;
        }
        return (this.g == jeVar.g || this.g <= jeVar.g) ? 0 : -1;
    }

    public final String toString() {
        return "name = " + this.b + ",uuid = " + this.a + ",major = " + this.d + ",minor = " + this.e + ",TxPower = " + this.f + ",rssi = " + this.g + ",time = " + this.h;
    }
}
