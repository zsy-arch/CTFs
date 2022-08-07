package d.a.a.c.a;

import android.content.DialogInterface;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionActivity;

/* loaded from: classes.dex */
public class b implements DialogInterface.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PermissionActivity f1604a;

    public b(PermissionActivity permissionActivity) {
        this.f1604a = permissionActivity;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        d dVar = PermissionActivity.r;
        if (dVar != null) {
            dVar.onClose();
        }
        this.f1604a.finish();
    }
}
