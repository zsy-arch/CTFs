package u.aly;

/* compiled from: Gender.java */
/* loaded from: classes2.dex */
public enum ba implements ca {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int d;

    ba(int i) {
        this.d = i;
    }

    @Override // u.aly.ca
    public int a() {
        return this.d;
    }

    public static ba a(int i) {
        switch (i) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            case 2:
                return UNKNOWN;
            default:
                return null;
        }
    }
}
