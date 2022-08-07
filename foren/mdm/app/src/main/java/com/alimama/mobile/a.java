package com.alimama.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.c;
import com.alimama.mobile.csdk.umupdate.a.d;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.a.h;
import com.alimama.mobile.csdk.umupdate.a.i;
import com.alimama.mobile.csdk.umupdate.b.d;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.alimama.mobile.csdk.umupdate.models.b;
import java.util.List;

/* compiled from: MMSDK.java */
/* loaded from: classes.dex */
public class a {
    private static a a;
    private com.alimama.mobile.csdk.umupdate.a.a b;
    private Context c;
    private h d;
    private boolean e = false;

    /* compiled from: MMSDK.java */
    /* renamed from: com.alimama.mobile.a$a */
    /* loaded from: classes.dex */
    public interface AbstractC0004a {
        public static final int h = 0;
        public static final int i = 1;
        public static final int j = 2;

        void dataReceived(int i2, List<Promoter> list);
    }

    private a() {
    }

    public void a(Context context) {
        if (!this.e) {
            this.c = context.getApplicationContext();
            c cVar = new c();
            c.a aVar = new c.a();
            aVar.a = "*";
            aVar.c = "2G/3G";
            aVar.d = "Wi-Fi";
            aVar.b = f.c;
            cVar.a(this.c, aVar);
            this.b = cVar;
            this.d = new h(this.c);
            this.e = true;
        }
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public com.alimama.mobile.csdk.umupdate.a.a b() {
        return this.b;
    }

    public Context c() {
        return this.c;
    }

    public h d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public void a(MMEntity mMEntity, Promoter promoter) {
        d.a(promoter, mMEntity, false, 0);
    }

    public void a(MMEntity mMEntity, AbstractC0004a aVar) {
        mMEntity.clear();
        b(mMEntity, aVar);
    }

    public void a(MMEntity mMEntity, Promoter... promoterArr) {
        int length;
        if (promoterArr == null || promoterArr.length == 0) {
            StringBuilder append = new StringBuilder().append("unable send impression report.[promoters=");
            if (promoterArr == null) {
                length = 0;
            } else {
                length = promoterArr.length;
            }
            g.e(append.append(length).append("]").toString(), new Object[0]);
            return;
        }
        new d.a(mMEntity).a(0).b(0).c(3).a(promoterArr).a().a();
    }

    private void b(MMEntity mMEntity, AbstractC0004a aVar) {
        b bVar = new b(mMEntity);
        com.alimama.mobile.csdk.umupdate.models.c a2 = bVar.a();
        a(bVar, aVar, this.c.getSharedPreferences(a2.c(), 0).getInt(a2.d(), 0));
    }

    private void a(b bVar, AbstractC0004a aVar, int i) {
        if (i == 1) {
            a(bVar, aVar);
        } else {
            new i(bVar, aVar, 0, false).a(i.c, new Void[0]);
        }
    }

    private void a(final b bVar, final AbstractC0004a aVar) {
        final boolean z = TextUtils.isEmpty(bVar.b().sid);
        new i(bVar, new AbstractC0004a() { // from class: com.alimama.mobile.a.1
            @Override // com.alimama.mobile.a.AbstractC0004a
            public void dataReceived(int i, List<Promoter> list) {
                if (i == 1) {
                    aVar.dataReceived(i, list);
                    a(z);
                    return;
                }
                new i(bVar, new AbstractC0004a() { // from class: com.alimama.mobile.a.1.1
                    @Override // com.alimama.mobile.a.AbstractC0004a
                    public void dataReceived(int i2, List<Promoter> list2) {
                        aVar.dataReceived(i2, list2);
                        if (i2 == 1) {
                            a(z);
                        }
                    }
                }, 0, false).a(i.c, new Void[0]);
            }

            public void a(boolean z2) {
                try {
                    MMEntity mMEntity = (MMEntity) bVar.b().clone();
                    mMEntity.clear();
                    b bVar2 = new b(mMEntity);
                    if (z2) {
                        com.alimama.mobile.csdk.umupdate.models.c a2 = bVar.a();
                        SharedPreferences sharedPreferences = a.this.c.getSharedPreferences(a2.c(), 0);
                        synchronized (sharedPreferences) {
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.remove(a2.e());
                            edit.commit();
                        }
                        new i(bVar2, null, 0, true).a(i.c, new Void[0]);
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, false).a(i.c, new Void[0]);
    }
}
