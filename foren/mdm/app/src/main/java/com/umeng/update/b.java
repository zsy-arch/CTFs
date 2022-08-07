package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import u.upd.g;

/* compiled from: UpdateClient.java */
/* loaded from: classes2.dex */
public class b extends g {
    private static final String c = b.class.getName();
    Context a;
    private final String[] b = {"http://au.umeng.com/api/check_app_update", "http://au.umeng.co/api/check_app_update"};

    public b(Context context) {
        this.a = context;
    }

    @Override // u.upd.g
    public boolean shouldCompressData() {
        return false;
    }

    public UpdateResponse a() {
        u.upd.b.c(c, String.format("is .so file ready: %b", Boolean.valueOf(DeltaUpdate.a())));
        d dVar = new d(this.a);
        UpdateResponse updateResponse = null;
        for (int i = 0; i < this.b.length; i++) {
            dVar.setBaseUrl(this.b[i]);
            updateResponse = (UpdateResponse) execute(dVar, UpdateResponse.class);
            if (updateResponse != null) {
                break;
            }
        }
        return updateResponse;
    }
}
