package com.tencent.map.a.a;

import com.alimama.mobile.csdk.umupdate.a.f;
import com.hyphenate.util.HanziToPinyin;
import com.tencent.map.b.h;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class d {
    private long A;
    public int a;
    public double b;
    public double c;
    public double d;
    public double e;
    public double f;
    public double g;
    public int h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;

    /* renamed from: u  reason: collision with root package name */
    public String f41u;
    public String v;
    public ArrayList<c> w;
    public boolean x;
    public int y;
    public int z;

    public d() {
        this.a = 1;
        this.b = 0.0d;
        this.c = 0.0d;
        this.d = -1.0d;
        this.e = 0.0d;
        this.f = 0.0d;
        this.g = 0.0d;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.f41u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = 0;
        this.z = -1;
        this.A = -1L;
        this.e = 0.0d;
        this.d = 0.0d;
        this.c = 0.0d;
        this.b = 0.0d;
        this.p = null;
        this.o = null;
        this.n = null;
        this.m = null;
        this.x = false;
        this.A = System.currentTimeMillis();
        this.y = 0;
        this.z = -1;
        this.w = null;
    }

    public d(d dVar) {
        this.a = 1;
        this.b = 0.0d;
        this.c = 0.0d;
        this.d = -1.0d;
        this.e = 0.0d;
        this.f = 0.0d;
        this.g = 0.0d;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.f41u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = 0;
        this.z = -1;
        this.A = -1L;
        this.a = dVar.a;
        this.b = dVar.b;
        this.c = dVar.c;
        this.d = dVar.d;
        this.e = dVar.e;
        this.x = dVar.x;
        this.i = dVar.i;
        this.h = 0;
        this.j = dVar.j;
        this.k = dVar.k;
        this.l = dVar.l;
        this.m = dVar.m;
        this.n = dVar.n;
        this.o = dVar.o;
        this.p = dVar.p;
        this.q = dVar.q;
        this.r = dVar.r;
        this.s = dVar.s;
        this.t = dVar.t;
        this.f41u = dVar.f41u;
        this.v = dVar.v;
        this.A = dVar.a();
        this.y = dVar.y;
        this.z = dVar.z;
        this.w = null;
        if (dVar.w != null) {
            this.w = new ArrayList<>();
            Iterator<c> it = dVar.w.iterator();
            while (it.hasNext()) {
                this.w.add(it.next());
            }
        }
    }

    public long a() {
        return this.A;
    }

    public void a(String str) {
        String[] split;
        this.l = f.c;
        this.k = f.c;
        this.j = f.c;
        this.i = f.c;
        if (str != null && (split = str.split(",")) != null) {
            int length = split.length;
            if (length > 0) {
                this.i = split[0];
            }
            if (length > 1) {
                this.j = split[1];
            }
            if (length == 3) {
                this.k = split[1];
            } else if (length > 3) {
                this.k = split[2];
            }
            if (length == 3) {
                this.l = split[2];
            } else if (length > 3) {
                this.l = split[3];
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.z).append(HanziToPinyin.Token.SEPARATOR).append(this.y).append(HanziToPinyin.Token.SEPARATOR).append(this.x ? "Mars" : "WGS84").append(HanziToPinyin.Token.SEPARATOR).append(this.a == 0 ? "GPS" : "Network").append("\n");
        sb.append(this.b).append(HanziToPinyin.Token.SEPARATOR).append(this.c).append("\n");
        sb.append(this.d).append(HanziToPinyin.Token.SEPARATOR).append(this.e).append("\n");
        sb.append(this.f).append(HanziToPinyin.Token.SEPARATOR).append(this.g).append("\n");
        if (this.z == 3 || this.z == 4) {
            sb.append(this.i).append(HanziToPinyin.Token.SEPARATOR).append(this.j).append(HanziToPinyin.Token.SEPARATOR).append(this.k).append(HanziToPinyin.Token.SEPARATOR).append(this.l).append(HanziToPinyin.Token.SEPARATOR).append(this.m).append(HanziToPinyin.Token.SEPARATOR).append(this.n).append(HanziToPinyin.Token.SEPARATOR).append(this.o).append(HanziToPinyin.Token.SEPARATOR).append(this.p).append("\n");
        }
        if (this.z == 4 && this.w != null) {
            sb.append(this.w.size()).append("\n");
            Iterator<c> it = this.w.iterator();
            while (it.hasNext()) {
                c next = it.next();
                sb.append(next.a).append(",").append(next.b).append(",").append(next.c).append(",").append(next.d).append(",").append(next.e).append(",").append(next.f).append("\n");
            }
        }
        if (this.z == 7) {
            if (this.h == 0) {
                sb.append(this.i).append(HanziToPinyin.Token.SEPARATOR).append(this.j).append(HanziToPinyin.Token.SEPARATOR).append(this.k).append(HanziToPinyin.Token.SEPARATOR).append(this.l).append(HanziToPinyin.Token.SEPARATOR).append(this.m).append(HanziToPinyin.Token.SEPARATOR).append(this.n).append(HanziToPinyin.Token.SEPARATOR).append(this.o).append(HanziToPinyin.Token.SEPARATOR).append(this.p).append("\n");
            } else if (this.h == 1) {
                sb.append(this.i).append(HanziToPinyin.Token.SEPARATOR).append(this.q).append(HanziToPinyin.Token.SEPARATOR).append(this.r).append(HanziToPinyin.Token.SEPARATOR).append(this.s).append(HanziToPinyin.Token.SEPARATOR).append(this.t).append(HanziToPinyin.Token.SEPARATOR).append(this.f41u).append(HanziToPinyin.Token.SEPARATOR).append(this.v).append("\n");
            }
        }
        h.a(sb.toString());
        return sb.toString();
    }
}
