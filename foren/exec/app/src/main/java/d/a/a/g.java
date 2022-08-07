package d.a.a;

import android.content.Intent;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionDialogActivity;
import d.a.a.c.a.d;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g implements d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ h f1655a;

    public g(h hVar) {
        this.f1655a = hVar;
    }

    @Override // d.a.a.c.a.d
    public void a(boolean z) {
        if (z) {
            this.f1655a.o();
        }
    }

    @Override // d.a.a.c.a.d
    public void onClose() {
        h.c(this.f1655a);
    }

    @Override // d.a.a.c.a.d
    public void a(String str, int i) {
        h hVar = this.f1655a;
        String string = this.f1655a.getString(e.b.a.g.permission_apply);
        String string2 = this.f1655a.getString(e.b.a.g.open_photo_permission_tip);
        PermissionDialogActivity.p = new f(this);
        Intent intent = new Intent(hVar, PermissionDialogActivity.class);
        intent.putExtra("dialogTitle", string);
        intent.putExtra("dialogContent", string2);
        intent.putExtra("permissionName", "android.permission.READ_EXTERNAL_STORAGE");
        hVar.startActivity(intent);
    }

    @Override // d.a.a.c.a.d
    public void a(String str, int i, boolean z) {
        if (z) {
            this.f1655a.o();
        }
    }
}
