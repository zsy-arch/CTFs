package d.a.a;

import android.content.Intent;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionDialogActivity;
import d.a.a.c.a.d;
import e.b.a.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e implements d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ h f1653a;

    public e(h hVar) {
        this.f1653a = hVar;
    }

    @Override // d.a.a.c.a.d
    public void a(boolean z) {
        if (z) {
            this.f1653a.n();
        }
    }

    @Override // d.a.a.c.a.d
    public void onClose() {
        h.c(this.f1653a);
    }

    @Override // d.a.a.c.a.d
    public void a(String str, int i) {
        h hVar = this.f1653a;
        String string = this.f1653a.getString(g.permission_apply);
        String string2 = this.f1653a.getString(g.open_camera_permission_tip);
        PermissionDialogActivity.p = new d(this);
        Intent intent = new Intent(hVar, PermissionDialogActivity.class);
        intent.putExtra("dialogTitle", string);
        intent.putExtra("dialogContent", string2);
        intent.putExtra("permissionName", "android.permission.CAMERA");
        hVar.startActivity(intent);
    }

    @Override // d.a.a.c.a.d
    public void a(String str, int i, boolean z) {
        String str2 = "onGuarantee onGuarantee lastOne:" + z;
        if (z) {
            this.f1653a.n();
        }
    }
}
