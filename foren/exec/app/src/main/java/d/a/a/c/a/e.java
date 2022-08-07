package d.a.a.c.a;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionDialogActivity;
import d.a.a.d.d;

/* loaded from: classes.dex */
public class e implements d.a {

    /* renamed from: a */
    public final /* synthetic */ PermissionDialogActivity f1608a;

    public e(PermissionDialogActivity permissionDialogActivity) {
        this.f1608a = permissionDialogActivity;
    }

    public void a(View view) {
        PermissionDialogActivity.p.a();
        this.f1608a.finish();
    }

    public void b(View view) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.f1608a.getPackageName(), null));
        this.f1608a.startActivityForResult(intent, 4374);
    }
}
