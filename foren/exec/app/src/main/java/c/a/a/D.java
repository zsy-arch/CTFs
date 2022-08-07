package c.a.a;

/* loaded from: classes.dex */
public class D {

    /* renamed from: a  reason: collision with root package name */
    public static D f320a;

    /* renamed from: b  reason: collision with root package name */
    public long f321b;

    /* renamed from: c  reason: collision with root package name */
    public long f322c;

    /* renamed from: d  reason: collision with root package name */
    public int f323d;

    public void a(long j, double d2, double d3) {
        float f = ((float) (j - 946728000000L)) / 8.64E7f;
        float f2 = (0.01720197f * f) + 6.24006f;
        double d4 = f2;
        double sin = (Math.sin(f2 * 3.0f) * 5.236000106378924E-6d) + (Math.sin(2.0f * f2) * 3.4906598739326E-4d) + (Math.sin(d4) * 0.03341960161924362d) + d4 + 1.796593063d + 3.141592653589793d;
        double d5 = (-d3) / 360.0d;
        double sin2 = (Math.sin(2.0d * sin) * (-0.0069d)) + (Math.sin(d4) * 0.0053d) + ((float) Math.round((f - 9.0E-4f) - d5)) + 9.0E-4f + d5;
        double asin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(sin));
        double d6 = 0.01745329238474369d * d2;
        double sin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(asin) * Math.sin(d6))) / (Math.cos(asin) * Math.cos(d6));
        if (sin3 >= 1.0d) {
            this.f323d = 1;
            this.f321b = -1L;
            this.f322c = -1L;
        } else if (sin3 <= -1.0d) {
            this.f323d = 0;
            this.f321b = -1L;
            this.f322c = -1L;
        } else {
            double acos = (float) (Math.acos(sin3) / 6.283185307179586d);
            this.f321b = Math.round((sin2 + acos) * 8.64E7d) + 946728000000L;
            this.f322c = Math.round((sin2 - acos) * 8.64E7d) + 946728000000L;
            if (this.f322c >= j || this.f321b <= j) {
                this.f323d = 1;
            } else {
                this.f323d = 0;
            }
        }
    }
}
