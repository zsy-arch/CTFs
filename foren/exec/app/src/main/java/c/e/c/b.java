package c.e.c;

import android.graphics.Path;
import c.a.a.C;
import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public char f774a;

    /* renamed from: b  reason: collision with root package name */
    public float[] f775b;

    public b(char c2, float[] fArr) {
        this.f774a = c2;
        this.f775b = fArr;
    }

    public static void a(b[] bVarArr, Path path) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        b[] bVarArr2 = bVarArr;
        float[] fArr = new float[6];
        char c2 = 'm';
        char c3 = 0;
        char c4 = 'm';
        int i3 = 0;
        while (i3 < bVarArr2.length) {
            char c5 = bVarArr2[i3].f774a;
            float[] fArr2 = bVarArr2[i3].f775b;
            float f11 = fArr[c3];
            float f12 = fArr[1];
            float f13 = fArr[2];
            float f14 = fArr[3];
            float f15 = fArr[4];
            float f16 = fArr[5];
            switch (c5) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR /* 122 */:
                    path.close();
                    path.moveTo(f15, f16);
                    f11 = f15;
                    f13 = f11;
                    f12 = f16;
                    f14 = f12;
                default:
                    i = 2;
                    break;
            }
            float f17 = f15;
            float f18 = f16;
            int i4 = 0;
            while (i4 < fArr2.length) {
                if (c5 != 'A') {
                    if (c5 == 'C') {
                        i2 = i4;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                        int i5 = i2 + 2;
                        int i6 = i2 + 3;
                        int i7 = i2 + 4;
                        int i8 = i2 + 5;
                        path.cubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i5], fArr2[i6], fArr2[i7], fArr2[i8]);
                        f11 = fArr2[i7];
                        f12 = fArr2[i8];
                        float f19 = fArr2[i5];
                        f14 = fArr2[i6];
                        f13 = f19;
                    } else if (c5 == 'H') {
                        i2 = i4;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                        int i9 = i2 + 0;
                        path.lineTo(fArr2[i9], f12);
                        f11 = fArr2[i9];
                    } else if (c5 == 'Q') {
                        i2 = i4;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                        int i10 = i2 + 0;
                        int i11 = i2 + 1;
                        int i12 = i2 + 2;
                        int i13 = i2 + 3;
                        path.quadTo(fArr2[i10], fArr2[i11], fArr2[i12], fArr2[i13]);
                        float f20 = fArr2[i10];
                        f14 = fArr2[i11];
                        float f21 = fArr2[i12];
                        f12 = fArr2[i13];
                        f13 = f20;
                        f11 = f21;
                    } else if (c5 == 'V') {
                        i2 = i4;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                        int i14 = i2 + 0;
                        path.lineTo(f11, fArr2[i14]);
                        f12 = fArr2[i14];
                    } else if (c5 != 'a') {
                        if (c5 != 'c') {
                            if (c5 == 'h') {
                                i2 = i4;
                                int i15 = i2 + 0;
                                path.rLineTo(fArr2[i15], 0.0f);
                                f11 += fArr2[i15];
                            } else if (c5 != 'q') {
                                if (c5 == 'v') {
                                    f11 = f11;
                                    f4 = f12;
                                    i2 = i4;
                                    int i16 = i2 + 0;
                                    path.rLineTo(0.0f, fArr2[i16]);
                                    f3 = fArr2[i16];
                                } else if (c5 != 'L') {
                                    if (c5 == 'M') {
                                        i2 = i4;
                                        int i17 = i2 + 0;
                                        float f22 = fArr2[i17];
                                        int i18 = i2 + 1;
                                        f12 = fArr2[i18];
                                        if (i2 > 0) {
                                            path.lineTo(fArr2[i17], fArr2[i18]);
                                            f11 = f22;
                                        } else {
                                            path.moveTo(fArr2[i17], fArr2[i18]);
                                            f17 = f22;
                                            f18 = f12;
                                        }
                                    } else if (c5 == 'S') {
                                        i2 = i4;
                                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                            f6 = (f11 * 2.0f) - f13;
                                            f5 = (f12 * 2.0f) - f14;
                                        } else {
                                            f5 = f12;
                                            f6 = f11;
                                        }
                                        int i19 = i2 + 0;
                                        int i20 = i2 + 1;
                                        int i21 = i2 + 2;
                                        int i22 = i2 + 3;
                                        path.cubicTo(f6, f5, fArr2[i19], fArr2[i20], fArr2[i21], fArr2[i22]);
                                        float f23 = fArr2[i19];
                                        f14 = fArr2[i20];
                                        f11 = fArr2[i21];
                                        f12 = fArr2[i22];
                                        f13 = f23;
                                    } else if (c5 == 'T') {
                                        float f24 = f11;
                                        float f25 = f12;
                                        i2 = i4;
                                        if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                            f24 = (f24 * 2.0f) - f13;
                                            f25 = (f25 * 2.0f) - f14;
                                        }
                                        int i23 = i2 + 0;
                                        int i24 = i2 + 1;
                                        path.quadTo(f24, f25, fArr2[i23], fArr2[i24]);
                                        f11 = fArr2[i23];
                                        f12 = fArr2[i24];
                                        f14 = f25;
                                        fArr2 = fArr2;
                                        c4 = c5;
                                        i3 = i3;
                                        f13 = f24;
                                    } else if (c5 == 'l') {
                                        f4 = f12;
                                        i2 = i4;
                                        int i25 = i2 + 0;
                                        int i26 = i2 + 1;
                                        path.rLineTo(fArr2[i25], fArr2[i26]);
                                        f11 += fArr2[i25];
                                        f3 = fArr2[i26];
                                    } else if (c5 == c2) {
                                        i2 = i4;
                                        int i27 = i2 + 0;
                                        f11 += fArr2[i27];
                                        int i28 = i2 + 1;
                                        f12 += fArr2[i28];
                                        if (i2 > 0) {
                                            path.rLineTo(fArr2[i27], fArr2[i28]);
                                        } else {
                                            path.rMoveTo(fArr2[i27], fArr2[i28]);
                                            f17 = f11;
                                            f18 = f12;
                                        }
                                    } else if (c5 != 's') {
                                        if (c5 == 't') {
                                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                f9 = f11 - f13;
                                                f10 = f12 - f14;
                                            } else {
                                                f10 = 0.0f;
                                                f9 = 0.0f;
                                            }
                                            int i29 = i4 + 0;
                                            int i30 = i4 + 1;
                                            path.rQuadTo(f9, f10, fArr2[i29], fArr2[i30]);
                                            f13 = f9 + f11;
                                            f14 = f10 + f12;
                                            f11 += fArr2[i29];
                                            f12 += fArr2[i30];
                                        }
                                        i2 = i4;
                                    } else {
                                        if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                            f8 = f11 - f13;
                                            f7 = f12 - f14;
                                        } else {
                                            f8 = 0.0f;
                                            f7 = 0.0f;
                                        }
                                        int i31 = i4 + 0;
                                        int i32 = i4 + 1;
                                        int i33 = i4 + 2;
                                        int i34 = i4 + 3;
                                        f2 = f12;
                                        i2 = i4;
                                        path.rCubicTo(f8, f7, fArr2[i31], fArr2[i32], fArr2[i33], fArr2[i34]);
                                        f13 = fArr2[i31] + f11;
                                        f14 = fArr2[i32] + f2;
                                        f11 += fArr2[i33];
                                        f = fArr2[i34];
                                    }
                                    f11 = f17;
                                    f12 = f18;
                                } else {
                                    i2 = i4;
                                    int i35 = i2 + 0;
                                    int i36 = i2 + 1;
                                    path.lineTo(fArr2[i35], fArr2[i36]);
                                    f11 = fArr2[i35];
                                    f12 = fArr2[i36];
                                }
                                f12 = f4 + f3;
                            } else {
                                f2 = f12;
                                i2 = i4;
                                int i37 = i2 + 0;
                                int i38 = i2 + 1;
                                int i39 = i2 + 2;
                                int i40 = i2 + 3;
                                path.rQuadTo(fArr2[i37], fArr2[i38], fArr2[i39], fArr2[i40]);
                                f13 = fArr2[i37] + f11;
                                f14 = fArr2[i38] + f2;
                                f11 += fArr2[i39];
                                f = fArr2[i40];
                            }
                            fArr2 = fArr2;
                            c4 = c5;
                            i3 = i3;
                        } else {
                            f2 = f12;
                            i2 = i4;
                            int i41 = i2 + 2;
                            int i42 = i2 + 3;
                            int i43 = i2 + 4;
                            int i44 = i2 + 5;
                            path.rCubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i41], fArr2[i42], fArr2[i43], fArr2[i44]);
                            f13 = fArr2[i41] + f11;
                            f14 = fArr2[i42] + f2;
                            f11 += fArr2[i43];
                            f = fArr2[i44];
                        }
                        f12 = f + f2;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                    } else {
                        i2 = i4;
                        int i45 = i2 + 5;
                        int i46 = i2 + 6;
                        fArr2 = fArr2;
                        c4 = c5;
                        i3 = i3;
                        a(path, f11, f12, fArr2[i45] + f11, fArr2[i46] + f12, fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                        f11 += fArr2[i45];
                        f12 += fArr2[i46];
                    }
                    i4 = i2 + i;
                    c2 = 'm';
                    c5 = c4;
                } else {
                    i2 = i4;
                    fArr2 = fArr2;
                    c4 = c5;
                    i3 = i3;
                    int i47 = i2 + 5;
                    int i48 = i2 + 6;
                    a(path, f11, f12, fArr2[i47], fArr2[i48], fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                    f11 = fArr2[i47];
                    f12 = fArr2[i48];
                }
                f13 = f11;
                f14 = f12;
                i4 = i2 + i;
                c2 = 'm';
                c5 = c4;
            }
            fArr[0] = f11;
            fArr[1] = f12;
            fArr[2] = f13;
            fArr[3] = f14;
            fArr[4] = f17;
            fArr[5] = f18;
            c4 = bVarArr[i3].f774a;
            i3++;
            c2 = 'm';
            c3 = 0;
            bVarArr2 = bVarArr;
        }
    }

    public b(b bVar) {
        this.f774a = bVar.f774a;
        float[] fArr = bVar.f775b;
        this.f775b = C.a(fArr, 0, fArr.length);
    }

    public void a(b bVar, b bVar2, float f) {
        int i = 0;
        while (true) {
            float[] fArr = bVar.f775b;
            if (i < fArr.length) {
                this.f775b[i] = (bVar2.f775b[i] * f) + ((1.0f - f) * fArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public static void a(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
        double d2;
        double d3;
        double radians = Math.toRadians(f7);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d4 = f;
        double d5 = f2;
        double d6 = (d5 * sin) + (d4 * cos);
        double d7 = d4;
        double d8 = f5;
        double d9 = d6 / d8;
        double d10 = f6;
        double d11 = ((d5 * cos) + ((-f) * sin)) / d10;
        double d12 = d5;
        double d13 = f4;
        double d14 = ((d13 * sin) + (f3 * cos)) / d8;
        double d15 = ((d13 * cos) + ((-f3) * sin)) / d10;
        double d16 = d9 - d14;
        double d17 = d11 - d15;
        double d18 = (d9 + d14) / 2.0d;
        double d19 = (d11 + d15) / 2.0d;
        double d20 = (d17 * d17) + (d16 * d16);
        if (d20 != 0.0d) {
            double d21 = (1.0d / d20) - 0.25d;
            if (d21 < 0.0d) {
                String str = "Points are too far apart " + d20;
                float sqrt = (float) (Math.sqrt(d20) / 1.99999d);
                a(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d21);
            double d22 = d16 * sqrt2;
            double d23 = sqrt2 * d17;
            if (z == z2) {
                d3 = d18 - d23;
                d2 = d19 + d22;
            } else {
                d3 = d18 + d23;
                d2 = d19 - d22;
            }
            double atan2 = Math.atan2(d11 - d2, d9 - d3);
            double atan22 = Math.atan2(d15 - d2, d14 - d3) - atan2;
            int i = 0;
            int i2 = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            if (z2 != (i2 >= 0)) {
                atan22 = i2 > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d24 = d3 * d8;
            double d25 = d2 * d10;
            double d26 = (d24 * cos) - (d25 * sin);
            double d27 = (d25 * cos) + (d24 * sin);
            int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
            double cos2 = Math.cos(radians);
            double sin2 = Math.sin(radians);
            double cos3 = Math.cos(atan2);
            double sin3 = Math.sin(atan2);
            double d28 = -d8;
            double d29 = d28 * cos2;
            double d30 = d10 * sin2;
            double d31 = (d29 * sin3) - (d30 * cos3);
            double d32 = d28 * sin2;
            double d33 = d10 * cos2;
            double d34 = (cos3 * d33) + (sin3 * d32);
            double d35 = atan22 / ceil;
            double d36 = atan2;
            while (i < ceil) {
                double d37 = d36 + d35;
                double sin4 = Math.sin(d37);
                double cos4 = Math.cos(d37);
                double d38 = (((d8 * cos2) * cos4) + d26) - (d30 * sin4);
                double d39 = (d33 * sin4) + (d8 * sin2 * cos4) + d27;
                double d40 = (d29 * sin4) - (d30 * cos4);
                double d41 = (cos4 * d33) + (sin4 * d32);
                double d42 = d37 - d36;
                double tan = Math.tan(d42 / 2.0d);
                double sqrt3 = ((Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d) * Math.sin(d42)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) ((d31 * sqrt3) + d7), (float) ((d34 * sqrt3) + d12), (float) (d38 - (sqrt3 * d40)), (float) (d39 - (sqrt3 * d41)), (float) d38, (float) d39);
                i++;
                d33 = d33;
                d32 = d32;
                ceil = ceil;
                cos2 = cos2;
                d36 = d37;
                d8 = d8;
                d34 = d41;
                d31 = d40;
                d7 = d38;
                d12 = d39;
                d35 = d35;
                d26 = d26;
            }
        }
    }
}
