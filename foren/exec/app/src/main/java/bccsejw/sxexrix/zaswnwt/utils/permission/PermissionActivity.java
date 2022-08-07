package bccsejw.sxexrix.zaswnwt.utils.permission;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import c.a.a.m;
import c.e.a.b;
import com.tencent.smtt.sdk.BuildConfig;
import d.a.a.c.a.a;
import d.a.a.c.a.c;
import d.a.a.c.a.d;
import d.a.a.c.a.f;
import e.b.a.g;
import e.b.a.h;
import java.util.List;

/* loaded from: classes.dex */
public class PermissionActivity extends m {
    public static int p = 1;
    public static int q = 2;
    public static d r;
    public int A;
    public String B;
    public String s = "hagan";
    public int t;
    public String u;
    public String v;
    public List<f> w;
    public Dialog x;
    public int y;
    public int z;

    public static /* synthetic */ String[] b(PermissionActivity permissionActivity) {
        String[] strArr = new String[permissionActivity.w.size()];
        for (int i = 0; i < permissionActivity.w.size(); i++) {
            strArr[i] = permissionActivity.w.get(i).f1610b;
        }
        return strArr;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // c.a.a.m, c.i.a.ActivityC0065h, c.e.a.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.t = intent.getIntExtra("data_permission_type", p);
        this.B = intent.getStringExtra("data_permission_title_name");
        this.u = intent.getStringExtra("data_title");
        this.v = intent.getStringExtra("data_msg");
        this.z = intent.getIntExtra("data_color_filter", 0);
        this.y = intent.getIntExtra("data_style_id", -1);
        this.A = intent.getIntExtra("data_anim_style", -1);
        this.w = (List) intent.getSerializableExtra("data_permissions");
        if (this.t == p) {
            List<f> list = this.w;
            if (list != null && list.size() != 0) {
                b.a(this, new String[]{this.w.get(0).f1610b}, 1);
                return;
            }
            return;
        }
        boolean isEmpty = TextUtils.isEmpty(this.u);
        String str3 = BuildConfig.FLAVOR;
        if (isEmpty) {
            String string = getString(g.permission_dialog_title);
            Object[] objArr = new Object[1];
            objArr[0] = TextUtils.isEmpty(this.B) ? str3 : this.B;
            str = String.format(string, objArr);
        } else {
            str = this.u;
        }
        if (TextUtils.isEmpty(this.v)) {
            String string2 = getString(g.permission_dialog_msg);
            Object[] objArr2 = new Object[1];
            if (!TextUtils.isEmpty(this.B)) {
                str3 = this.B;
            }
            objArr2[0] = str3;
            str2 = String.format(string2, objArr2);
        } else {
            str2 = this.v;
        }
        PermissionView permissionView = new PermissionView(this, null, 0);
        int i = 3;
        if (this.w.size() < 3) {
            i = this.w.size();
        }
        permissionView.setGridViewColum(i);
        permissionView.setTitle(str);
        permissionView.setMsg(str2);
        permissionView.setGridViewAdapter(new c(this.w));
        if (this.y == -1) {
            this.y = h.PermissionDefaultNormalStyle;
            this.z = getResources().getColor(e.b.a.c.blue_color);
        }
        permissionView.setStyleId(this.y);
        permissionView.setFilterColor(this.z);
        permissionView.setBtnOnClickListener(new a(this));
        this.x = new Dialog(this);
        this.x.requestWindowFeature(1);
        this.x.setContentView(permissionView);
        if (this.A != -1) {
            this.x.getWindow().setWindowAnimations(this.A);
        }
        this.x.setCanceledOnTouchOutside(false);
        this.x.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.x.setOnCancelListener(new d.a.a.c.a.b(this));
        this.x.show();
    }

    @Override // c.a.a.m, c.i.a.ActivityC0065h, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        r = null;
        Dialog dialog = this.x;
        if (dialog != null && dialog.isShowing()) {
            this.x.dismiss();
        }
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity, c.e.a.b.a
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f963c.c();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String a2 = this.l.a(i3);
            this.l.c(i3);
            if (a2 != null && this.f963c.f969a.f973d.a(a2) == null) {
                String str = "Activity result no fragment exists for who: " + a2;
            }
        }
        boolean z = true;
        if (i == 1) {
            String str2 = a(strArr[0]).f1610b;
            if (iArr[0] == 0) {
                d dVar = r;
                if (dVar != null) {
                    dVar.a(str2, 0, true);
                }
            } else {
                d dVar2 = r;
                if (dVar2 != null) {
                    dVar2.a(str2, 0);
                }
            }
            finish();
        } else if (i == 2) {
            String str3 = this.s;
            int i4 = 0;
            while (true) {
                if (i4 >= iArr.length) {
                    z = false;
                    break;
                } else if (iArr[i4] == 0) {
                    this.w.remove(a(strArr[i4]));
                    String str4 = strArr[i4];
                    boolean z2 = this.w.size() == 0;
                    d dVar3 = r;
                    if (dVar3 != null) {
                        dVar3.a(str4, i4, z2);
                    }
                    if (this.w.size() == 0) {
                        String str5 = this.s;
                        finish();
                    }
                    i4++;
                } else {
                    String str6 = strArr[i4];
                    d dVar4 = r;
                    if (dVar4 != null) {
                        dVar4.a(str6, i4);
                    }
                }
            }
            if (z) {
                String str7 = this.s;
                d dVar5 = r;
                if (dVar5 != null) {
                    dVar5.a(false);
                }
                finish();
            }
        }
    }

    public final f a(String str) {
        for (f fVar : this.w) {
            if (fVar.f1610b.equals(str)) {
                return fVar;
            }
        }
        return null;
    }
}
