package c.i.a;

import androidx.fragment.app.Fragment;
import c.j.f;
import c.j.h;
import c.j.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.i.a.e  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0062e implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Fragment f960a;

    public C0062e(Fragment fragment) {
        this.f960a = fragment;
    }

    @Override // c.j.h
    public f a() {
        Fragment fragment = this.f960a;
        if (fragment.V == null) {
            fragment.V = new i(fragment.W);
        }
        return this.f960a.V;
    }
}
