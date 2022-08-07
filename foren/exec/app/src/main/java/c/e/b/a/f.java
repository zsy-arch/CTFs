package c.e.b.a;

import java.util.List;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f759a;

    /* renamed from: b  reason: collision with root package name */
    public final float[] f760b;

    public f(List<Integer> list, List<Float> list2) {
        int size = list.size();
        this.f759a = new int[size];
        this.f760b = new float[size];
        for (int i = 0; i < size; i++) {
            this.f759a[i] = list.get(i).intValue();
            this.f760b[i] = list2.get(i).floatValue();
        }
    }

    public f(int i, int i2) {
        this.f759a = new int[]{i, i2};
        this.f760b = new float[]{0.0f, 1.0f};
    }

    public f(int i, int i2, int i3) {
        this.f759a = new int[]{i, i2, i3};
        this.f760b = new float[]{0.0f, 0.5f, 1.0f};
    }
}
