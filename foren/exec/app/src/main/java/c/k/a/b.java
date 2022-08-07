package c.k.a;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import c.a.a.C;
import c.c.j;
import c.j.h;
import c.j.m;
import c.j.n;
import c.j.p;
import c.j.q;
import c.j.r;
import com.tencent.smtt.sdk.TbsListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class b extends a {

    /* renamed from: a */
    public static boolean f1034a;

    /* renamed from: b */
    public final h f1035b;

    /* renamed from: c */
    public final C0016b f1036c;

    public b(h hVar, r rVar) {
        p put;
        this.f1035b = hVar;
        q qVar = C0016b.f1037a;
        String canonicalName = C0016b.class.getCanonicalName();
        if (canonicalName != null) {
            String a2 = e.a.a.a.a.a("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            p pVar = rVar.f1033a.get(a2);
            if (!C0016b.class.isInstance(pVar) && (put = rVar.f1033a.put(a2, (pVar = ((c) qVar).a(C0016b.class)))) != null) {
                put.a();
            }
            this.f1036c = (C0016b) pVar;
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // c.k.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f1036c.a(str, fileDescriptor, printWriter, strArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        C.a((Object) this.f1035b, sb);
        sb.append("}}");
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public static class a<D> extends m<D> implements c.k.b.a<D> {
        public final int j;
        public final Bundle k;
        public h l;

        @Override // androidx.lifecycle.LiveData
        public void a() {
            if (b.f1034a) {
                e.a.a.a.a.b("  Starting: ", this);
            }
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void b() {
            if (b.f1034a) {
                e.a.a.a.a.b("  Stopping: ", this);
            }
            throw null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.j);
            sb.append(" : ");
            C.a((Object) null, sb);
            sb.append("}}");
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void a(n<? super D> nVar) {
            super.a((n) nVar);
            this.l = null;
        }

        public void a(boolean z) {
            if (b.f1034a) {
                e.a.a.a.a.b("  Destroying: ", this);
            }
            throw null;
        }

        @Override // c.j.m, androidx.lifecycle.LiveData
        public void a(D d2) {
            LiveData.a("setValue");
            this.g++;
            this.f272e = d2;
            b(null);
        }
    }

    /* renamed from: c.k.a.b$b */
    /* loaded from: classes.dex */
    public static class C0016b extends p {

        /* renamed from: a */
        public static final q f1037a = new c();

        /* renamed from: b */
        public j<a> f1038b = new j<>(10);

        @Override // c.j.p
        public void a() {
            if (this.f1038b.b() <= 0) {
                j<a> jVar = this.f1038b;
                int i = jVar.f732e;
                Object[] objArr = jVar.f731d;
                for (int i2 = 0; i2 < i; i2++) {
                    objArr[i2] = null;
                }
                jVar.f732e = 0;
                jVar.f729b = false;
                return;
            }
            this.f1038b.d(0).a(true);
            throw null;
        }

        public void b() {
            int b2 = this.f1038b.b();
            for (int i = 0; i < b2; i++) {
                h hVar = this.f1038b.d(i).l;
            }
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f1038b.b() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                if (this.f1038b.b() > 0) {
                    a d2 = this.f1038b.d(0);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f1038b.b(0));
                    printWriter.print(": ");
                    printWriter.println(d2.toString());
                    printWriter.print(str2);
                    printWriter.print("mId=");
                    printWriter.print(d2.j);
                    printWriter.print(" mArgs=");
                    printWriter.println(d2.k);
                    printWriter.print(str2);
                    printWriter.print("mLoader=");
                    printWriter.println((Object) null);
                    String str3 = str2 + "  ";
                    throw null;
                }
            }
        }
    }
}
