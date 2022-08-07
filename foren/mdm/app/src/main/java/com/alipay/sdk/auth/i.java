package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.b;
import com.alipay.sdk.widget.a;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ StringBuilder b;
    final /* synthetic */ APAuthInfo c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Activity activity, StringBuilder sb, APAuthInfo aPAuthInfo) {
        this.a = activity;
        this.b = sb;
        this.c = aPAuthInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar;
        a aVar2;
        a aVar3;
        a aVar4;
        a aVar5;
        String str;
        String str2;
        a aVar6;
        a aVar7;
        String str3;
        a aVar8;
        a aVar9;
        String str4;
        a aVar10;
        a aVar11;
        a aVar12;
        int i = 0;
        try {
            b bVar = null;
            try {
                bVar = new com.alipay.sdk.packet.impl.a().a(this.a, this.b.toString());
            } catch (Throwable th) {
            }
            aVar5 = h.c;
            if (aVar5 != null) {
                aVar12 = h.c;
                aVar12.b();
                a unused = h.c = null;
            }
            if (bVar == null) {
                String unused2 = h.d = this.c.getRedirectUri() + "?resultCode=202";
                Activity activity = this.a;
                str4 = h.d;
                h.a(activity, str4);
                aVar10 = h.c;
                if (aVar10 != null) {
                    aVar11 = h.c;
                    aVar11.b();
                    return;
                }
                return;
            }
            List<com.alipay.sdk.protocol.b> a = com.alipay.sdk.protocol.b.a(bVar.a().optJSONObject(c.c).optJSONObject(c.d));
            while (true) {
                if (i >= a.size()) {
                    break;
                } else if (a.get(i).a == com.alipay.sdk.protocol.a.WapPay) {
                    String unused3 = h.d = a.get(i).b[0];
                    break;
                } else {
                    i++;
                }
            }
            str = h.d;
            if (TextUtils.isEmpty(str)) {
                String unused4 = h.d = this.c.getRedirectUri() + "?resultCode=202";
                Activity activity2 = this.a;
                str3 = h.d;
                h.a(activity2, str3);
                aVar8 = h.c;
                if (aVar8 != null) {
                    aVar9 = h.c;
                    aVar9.b();
                    return;
                }
                return;
            }
            Intent intent = new Intent(this.a, AuthActivity.class);
            str2 = h.d;
            intent.putExtra("params", str2);
            intent.putExtra("redirectUri", this.c.getRedirectUri());
            this.a.startActivity(intent);
            aVar6 = h.c;
            if (aVar6 != null) {
                aVar7 = h.c;
                aVar7.b();
            }
        } catch (Exception e) {
            aVar3 = h.c;
            if (aVar3 != null) {
                aVar4 = h.c;
                aVar4.b();
            }
        } catch (Throwable th2) {
            aVar = h.c;
            if (aVar != null) {
                aVar2 = h.c;
                aVar2.b();
            }
            throw th2;
        }
    }
}
