package c.e.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import c.a.a.C;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class e implements Iterable<Intent> {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<Intent> f744a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    public final Context f745b;

    /* loaded from: classes.dex */
    public interface a {
        Intent c();
    }

    public e(Context context) {
        this.f745b = context;
    }

    public e a(Activity activity) {
        Intent c2 = activity instanceof a ? ((a) activity).c() : null;
        if (c2 == null) {
            c2 = C.a(activity);
        }
        if (c2 != null) {
            ComponentName component = c2.getComponent();
            if (component == null) {
                component = c2.resolveActivity(this.f745b.getPackageManager());
            }
            int size = this.f744a.size();
            try {
                Intent a2 = C.a(this.f745b, component);
                while (a2 != null) {
                    this.f744a.add(size, a2);
                    a2 = C.a(this.f745b, a2.getComponent());
                }
                this.f744a.add(c2);
            } catch (PackageManager.NameNotFoundException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        return this;
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f744a.iterator();
    }
}
