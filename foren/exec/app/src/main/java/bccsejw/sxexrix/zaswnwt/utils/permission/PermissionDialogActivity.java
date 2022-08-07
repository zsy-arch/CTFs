package bccsejw.sxexrix.zaswnwt.utils.permission;

import android.content.Intent;
import android.os.Bundle;
import c.a.a.m;
import c.e.b.a;
import d.a.a.c.a.e;
import d.a.a.c.a.g;
import d.a.a.d.b;
import d.a.a.d.c;
import d.a.a.d.d;

/* loaded from: classes.dex */
public class PermissionDialogActivity extends m {
    public static g p;
    public String q;
    public String r;
    public String s;
    public d t;
    public boolean u;

    public void l() {
        super.onBackPressed();
        d dVar = this.t;
        if (dVar != null && dVar.isShowing()) {
            this.t.dismiss();
        }
        g gVar = p;
        if (gVar != null) {
            gVar.a();
        }
        finish();
    }

    public void m() {
        Intent intent = getIntent();
        this.q = intent.getStringExtra("dialogTitle");
        this.r = intent.getStringExtra("dialogContent");
        this.s = intent.getStringExtra("permissionName");
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4374) {
            if (a.a(this, this.s) == 0) {
                p.onSuccess();
            } else {
                p.a();
            }
            finish();
        }
    }

    @Override // c.a.a.m, c.i.a.ActivityC0065h, c.e.a.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m();
    }

    @Override // c.a.a.m, c.i.a.ActivityC0065h, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        p = null;
        d dVar = this.t;
        if (dVar != null && dVar.isShowing()) {
            this.t.dismiss();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            return;
        }
        if (!this.u) {
            this.u = true;
            if (this.t == null) {
                this.t = new d(this);
            }
            d dVar = this.t;
            dVar.f1632e.setVisibility(0);
            dVar.f.setVisibility(8);
            d dVar2 = this.t;
            dVar2.f1628a.setText(this.q);
            d dVar3 = this.t;
            dVar3.f1629b.setText(this.r);
            d dVar4 = this.t;
            e eVar = new e(this);
            dVar4.f1630c.setOnClickListener(new b(dVar4, eVar));
            dVar4.f1631d.setOnClickListener(new c(dVar4, eVar));
            this.t.show();
            return;
        }
        l();
    }
}
