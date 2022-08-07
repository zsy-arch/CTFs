package c.i.a;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.i.a.d  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0061d extends AbstractC0066i {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Fragment f959a;

    public C0061d(Fragment fragment) {
        this.f959a = fragment;
    }

    @Override // c.i.a.AbstractC0066i
    public View a(int i) {
        View view = this.f959a.K;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment does not have a view");
    }

    @Override // c.i.a.AbstractC0066i
    public boolean a() {
        return this.f959a.K != null;
    }

    @Override // c.i.a.AbstractC0066i
    public Fragment a(Context context, String str, Bundle bundle) {
        return this.f959a.u.a(context, str, bundle);
    }
}
