package u.aly;

/* compiled from: TMessage.java */
/* loaded from: classes2.dex */
public final class ct {
    public final String a;
    public final byte b;
    public final int c;

    public ct() {
        this("", (byte) 0, 0);
    }

    public ct(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + ((int) this.b) + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof ct) {
            return a((ct) obj);
        }
        return false;
    }

    public boolean a(ct ctVar) {
        return this.a.equals(ctVar.a) && this.b == ctVar.b && this.c == ctVar.c;
    }
}
