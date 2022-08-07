package com.amap.api.col;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.col.bb;
import com.amap.api.col.bj;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import java.io.File;

/* compiled from: CityObject.java */
/* loaded from: classes.dex */
public class aj extends OfflineMapCity implements as, bi {
    public static final Parcelable.Creator<aj> o = new Parcelable.Creator<aj>() { // from class: com.amap.api.col.aj.2
        /* renamed from: a */
        public aj createFromParcel(Parcel parcel) {
            return new aj(parcel);
        }

        /* renamed from: a */
        public aj[] newArray(int i) {
            return new aj[i];
        }
    };
    public bn a;
    public bn b;
    public bn c;
    public bn d;
    public bn e;
    public bn f;
    public bn g;
    public bn h;
    public bn i;
    public bn j;
    public bn k;
    bn l;
    Context m;
    boolean n;
    private String p;
    private String q;
    private long r;

    public void a(String str) {
        this.q = str;
    }

    public String a() {
        return this.q;
    }

    @Override // com.amap.api.col.as
    public String b() {
        return getUrl();
    }

    public aj(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        t();
    }

    public aj(Context context, int i) {
        this.a = new bp(6, this);
        this.b = new bv(2, this);
        this.c = new br(0, this);
        this.d = new bt(3, this);
        this.e = new bu(1, this);
        this.f = new bo(4, this);
        this.g = new bs(7, this);
        this.h = new bq(-1, this);
        this.i = new bq(101, this);
        this.j = new bq(102, this);
        this.k = new bq(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0L;
        this.m = context;
        a(i);
    }

    public void a(int i) {
        switch (i) {
            case -1:
                this.l = this.h;
                break;
            case 0:
                this.l = this.c;
                break;
            case 1:
                this.l = this.e;
                break;
            case 2:
                this.l = this.b;
                break;
            case 3:
                this.l = this.d;
                break;
            case 4:
                this.l = this.f;
                break;
            case 6:
                this.l = this.a;
                break;
            case 7:
                this.l = this.g;
                break;
            case 101:
                this.l = this.i;
                break;
            case 102:
                this.l = this.j;
                break;
            case 103:
                this.l = this.k;
                break;
            default:
                if (i < 0) {
                    this.l = this.h;
                    break;
                }
                break;
        }
        setState(i);
    }

    public void a(bn bnVar) {
        this.l = bnVar;
        setState(bnVar.b());
    }

    public bn c() {
        return this.l;
    }

    public void d() {
        ak a = ak.a(this.m);
        if (a != null) {
            a.c(this);
        }
    }

    public void e() {
        ak a = ak.a(this.m);
        if (a != null) {
            a.e(this);
            d();
        }
    }

    public void f() {
        bh.a("CityOperation current State==>" + c().b());
        if (this.l.equals(this.d)) {
            this.l.e();
        } else if (this.l.equals(this.c)) {
            this.l.f();
        } else if (this.l.equals(this.g) || this.l.equals(this.h)) {
            k();
            this.n = true;
        } else if (this.l.equals(this.j) || this.l.equals(this.i) || this.l.a(this.k)) {
            this.l.d();
        } else {
            c().c();
        }
    }

    public void g() {
        this.l.f();
    }

    public void h() {
        this.l.a(this.k.b());
    }

    public void i() {
        this.l.a();
        if (this.n) {
            this.l.c();
        }
        this.n = false;
    }

    public void j() {
        if (!this.l.equals(this.f)) {
        }
        this.l.g();
    }

    public void k() {
        ak a = ak.a(this.m);
        if (a != null) {
            a.a(this);
        }
    }

    public void l() {
        ak a = ak.a(this.m);
        if (a != null) {
            a.b(this);
        }
    }

    public void m() {
        ak a = ak.a(this.m);
        if (a != null) {
            a.d(this);
        }
    }

    @Override // com.amap.api.col.bj
    public void n() {
        this.r = 0L;
        if (!this.l.equals(this.b)) {
            bh.a("state must be waiting when download onStart");
        }
        this.l.d();
    }

    @Override // com.amap.api.col.bj
    public void a(long j, long j2) {
        long j3 = (100 * j2) / j;
        if (((int) j3) != getcompleteCode()) {
            setCompleteCode((int) j3);
            d();
        }
    }

    @Override // com.amap.api.col.bj
    public void o() {
        if (!this.l.equals(this.c)) {
            bh.a("state must be Loading when download onFinish");
        }
        this.l.h();
    }

    @Override // com.amap.api.col.bj
    public void a(bj.a aVar) {
        int i = 6;
        switch (aVar) {
            case amap_exception:
                i = this.j.b();
                break;
            case file_io_exception:
                i = this.k.b();
                break;
            case network_exception:
                i = this.i.b();
                break;
        }
        if (this.l.equals(this.c) || this.l.equals(this.b)) {
            this.l.a(i);
        }
    }

    @Override // com.amap.api.col.bj
    public void p() {
        e();
    }

    @Override // com.amap.api.col.bc
    public void q() {
        this.r = 0L;
        setCompleteCode(0);
        if (!this.l.equals(this.e)) {
        }
        this.l.d();
    }

    @Override // com.amap.api.col.bc
    public void r() {
        if (!this.l.equals(this.e)) {
        }
        this.l.a(this.h.b());
    }

    @Override // com.amap.api.col.bc
    public void a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.r > 500) {
            if (((int) j) > getcompleteCode()) {
                setCompleteCode((int) j);
                d();
            }
            this.r = currentTimeMillis;
        }
    }

    @Override // com.amap.api.col.bc
    public void b(String str) {
        if (!this.l.equals(this.e)) {
        }
        this.q = str;
        String u2 = u();
        String v = v();
        if (TextUtils.isEmpty(u2) || TextUtils.isEmpty(v)) {
            r();
            return;
        }
        File file = new File(v + "/");
        File file2 = new File(dt.a(this.m) + File.separator + "map/");
        File file3 = new File(dt.a(this.m));
        if (!file3.exists()) {
            file3.mkdir();
        }
        if (!file2.exists()) {
            file2.mkdir();
        }
        a(file, file2, u2);
    }

    @Override // com.amap.api.col.bc
    public void s() {
        e();
    }

    protected void t() {
        this.p = ak.a + getPinyin() + ".zip.tmp";
    }

    public String u() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        return this.p.substring(0, this.p.lastIndexOf("."));
    }

    public String v() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        String u2 = u();
        return u2.substring(0, u2.lastIndexOf(46));
    }

    private void a(final File file, File file2, final String str) {
        new bb().a(file, file2, -1L, bh.a(file), new bb.a() { // from class: com.amap.api.col.aj.1
            @Override // com.amap.api.col.bb.a
            public void a(String str2, String str3, float f) {
                int i = (int) (60.0d + (f * 0.39d));
                if (i - aj.this.getcompleteCode() > 0 && System.currentTimeMillis() - aj.this.r > 1000) {
                    aj.this.setCompleteCode(i);
                    aj.this.r = System.currentTimeMillis();
                }
            }

            @Override // com.amap.api.col.bb.a
            public void a(String str2, String str3) {
            }

            @Override // com.amap.api.col.bb.a
            public void b(String str2, String str3) {
                try {
                    new File(str).delete();
                    bh.b(file);
                    aj.this.setCompleteCode(100);
                    aj.this.l.h();
                } catch (Exception e) {
                    aj.this.l.a(aj.this.k.b());
                }
            }

            @Override // com.amap.api.col.bb.a
            public void a(String str2, String str3, int i) {
                aj.this.l.a(aj.this.k.b());
            }
        });
    }

    public boolean w() {
        if (bh.a() < (getSize() * 2.5d) - (getcompleteCode() * getSize())) {
        }
        return false;
    }

    public au x() {
        setState(this.l.b());
        au auVar = new au(this, this.m);
        auVar.a(a());
        bh.a("vMapFileNames: " + a());
        return auVar;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.q);
    }

    public aj(Parcel parcel) {
        super(parcel);
        this.a = new bp(6, this);
        this.b = new bv(2, this);
        this.c = new br(0, this);
        this.d = new bt(3, this);
        this.e = new bu(1, this);
        this.f = new bo(4, this);
        this.g = new bs(7, this);
        this.h = new bq(-1, this);
        this.i = new bq(101, this);
        this.j = new bq(102, this);
        this.k = new bq(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0L;
        this.q = parcel.readString();
    }

    @Override // com.amap.api.col.bi
    public boolean y() {
        return w();
    }

    @Override // com.amap.api.col.bi
    public String z() {
        StringBuffer stringBuffer = new StringBuffer(getPinyin());
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    @Override // com.amap.api.col.bi
    public String A() {
        return getAdcode();
    }

    @Override // com.amap.api.col.bd
    public String B() {
        return u();
    }

    @Override // com.amap.api.col.bd
    public String C() {
        return v();
    }

    public bn b(int i) {
        switch (i) {
            case 101:
                return this.i;
            case 102:
                return this.j;
            case 103:
                return this.k;
            default:
                return this.h;
        }
    }
}
