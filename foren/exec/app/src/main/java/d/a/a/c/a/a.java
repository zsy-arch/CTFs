package d.a.a.c.a;

import android.app.Dialog;
import android.view.View;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionActivity;
import c.e.a.b;

/* loaded from: classes.dex */
public class a implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PermissionActivity f1603a;

    public a(PermissionActivity permissionActivity) {
        this.f1603a = permissionActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Dialog dialog;
        Dialog dialog2;
        Dialog dialog3;
        dialog = this.f1603a.x;
        if (dialog != null) {
            dialog2 = this.f1603a.x;
            if (dialog2.isShowing()) {
                dialog3 = this.f1603a.x;
                dialog3.dismiss();
            }
        }
        b.a(this.f1603a, PermissionActivity.b(this.f1603a), 2);
    }
}
