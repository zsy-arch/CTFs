package c.j;

import androidx.lifecycle.LiveData;

/* loaded from: classes.dex */
public class m<T> extends LiveData<T> {
    @Override // androidx.lifecycle.LiveData
    public void a(T t) {
        LiveData.a("setValue");
        this.g++;
        this.f272e = t;
        b(null);
    }
}
