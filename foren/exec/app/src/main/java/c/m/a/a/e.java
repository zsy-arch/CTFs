package c.m.a.a;

import android.animation.TypeEvaluator;
import c.a.a.C;
import c.e.c.b;

/* loaded from: classes.dex */
public class e implements TypeEvaluator<b[]> {

    /* renamed from: a  reason: collision with root package name */
    public b[] f1055a;

    @Override // android.animation.TypeEvaluator
    public b[] evaluate(float f, b[] bVarArr, b[] bVarArr2) {
        b[] bVarArr3 = bVarArr;
        b[] bVarArr4 = bVarArr2;
        if (C.a(bVarArr3, bVarArr4)) {
            b[] bVarArr5 = this.f1055a;
            if (bVarArr5 == null || !C.a(bVarArr5, bVarArr3)) {
                this.f1055a = C.a(bVarArr3);
            }
            for (int i = 0; i < bVarArr3.length; i++) {
                this.f1055a[i].a(bVarArr3[i], bVarArr4[i], f);
            }
            return this.f1055a;
        }
        throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
    }
}
