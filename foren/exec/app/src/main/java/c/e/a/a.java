package c.e.a;

import android.app.Activity;
import android.content.pm.PackageManager;
import c.e.a.b;

/* loaded from: classes.dex */
class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String[] f740a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Activity f741b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f742c;

    public a(String[] strArr, Activity activity, int i) {
        this.f740a = strArr;
        this.f741b = activity;
        this.f742c = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        int[] iArr = new int[this.f740a.length];
        PackageManager packageManager = this.f741b.getPackageManager();
        String packageName = this.f741b.getPackageName();
        int length = this.f740a.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = packageManager.checkPermission(this.f740a[i], packageName);
        }
        ((b.a) this.f741b).onRequestPermissionsResult(this.f742c, this.f740a, iArr);
    }
}
