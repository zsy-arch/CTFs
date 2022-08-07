package c.a.f;

/* loaded from: classes.dex */
public class N {

    /* renamed from: a  reason: collision with root package name */
    public int f544a = 0;

    /* renamed from: b  reason: collision with root package name */
    public int f545b = 0;

    /* renamed from: c  reason: collision with root package name */
    public int f546c = Integer.MIN_VALUE;

    /* renamed from: d  reason: collision with root package name */
    public int f547d = Integer.MIN_VALUE;

    /* renamed from: e  reason: collision with root package name */
    public int f548e = 0;
    public int f = 0;
    public boolean g = false;
    public boolean h = false;

    public void a(int i, int i2) {
        this.f546c = i;
        this.f547d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f544a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f545b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f544a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f545b = i2;
        }
    }
}
