package com.alimama.mobile.csdk.umupdate.a;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.alimama.mobile.a;
import com.alimama.mobile.csdk.umupdate.b.d;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;

/* compiled from: DisplayManager.java */
/* loaded from: classes.dex */
public class d {
    public static void a(Promoter promoter, MMEntity mMEntity, boolean z, int i) {
        MMEntity mMEntity2;
        try {
            mMEntity2 = (MMEntity) mMEntity.clone();
        } catch (CloneNotSupportedException e) {
            mMEntity2 = null;
        }
        if (a.a().b().c(promoter.app_package_name)) {
            c(mMEntity2, promoter, i);
            return;
        }
        int i2 = promoter.landing_type;
        if (z && i2 == 0) {
            i2 = 1;
        }
        switch (i2) {
            case 0:
            case 1:
                a(mMEntity2, promoter, i);
                return;
            case 2:
            case 3:
            case 4:
                b(mMEntity2, promoter, i);
                return;
            default:
                Toast.makeText(a.a().c(), "不支持该点击.", 0).show();
                return;
        }
    }

    private static void a(MMEntity mMEntity, Promoter promoter, int i) {
        new k(promoter, new d.a(mMEntity).a(7).b(i).c(3).a(promoter)).a();
    }

    private static void b(MMEntity mMEntity, Promoter promoter, int i) {
        new d.a(mMEntity).a(2).b(i).c(3).a(promoter).a().a();
        try {
            a.a().c().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(promoter.url)));
        } catch (ActivityNotFoundException e) {
            g.d(e.e, e.toString());
            Toast.makeText(a.a().c(), "无法找到浏览器.", 0).show();
        }
    }

    private static void c(MMEntity mMEntity, Promoter promoter, int i) {
        new d.a(mMEntity).a(5).b(i).c(0).a(promoter).a().a();
        if (!TextUtils.isEmpty(promoter.url_in_app)) {
            j.b(a.a().c(), promoter.url_in_app);
        } else {
            j.a(a.a().c(), promoter.app_package_name);
        }
    }
}
