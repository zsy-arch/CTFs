package u.aly;

import android.content.Context;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import java.io.File;
import u.aly.bs;
import u.aly.co;

/* compiled from: Sender.java */
/* loaded from: classes2.dex */
public class ar {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static Context g;
    private v d;
    private x e;
    private at h;
    private am i;
    private aw j;
    private boolean l;
    private final int f = 1;
    private boolean k = false;

    static /* synthetic */ int a(ar arVar, byte[] bArr) {
        return arVar.a(bArr);
    }

    static /* synthetic */ am a(ar arVar) {
        return arVar.i;
    }

    static /* synthetic */ boolean b(ar arVar) {
        return arVar.l;
    }

    public ar(Context context, at atVar) {
        this.d = v.a(context);
        this.e = x.a(context);
        g = context;
        this.h = atVar;
        this.i = new am(context);
        this.i.a(this.h);
    }

    public void a(aw awVar) {
        this.j = awVar;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void a(ap apVar) {
        this.e.a(apVar);
    }

    public void a() {
        try {
            if (this.j != null) {
                c();
            } else {
                b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        bs.a(g).j().a(new bs.b() { // from class: u.aly.ar.1
            @Override // u.aly.bs.b
            public void a(File file) {
            }

            /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
                jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x002c: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:16:0x002c
                	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
                	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
                	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
                	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
                */
            @Override // u.aly.bs.b
            public boolean b(
            /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
                jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 1, insn: 0x002c: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:16:0x002c
                	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
                	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
                	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
                */
            /*  JADX ERROR: Method generation error
                jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r5v0 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:233)
                	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:209)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:162)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:364)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                */

            @Override // u.aly.bs.b
            public void c(File file) {
                ar.this.h.k();
            }
        });
    }

    private void c() {
        t b2;
        int a2;
        this.d.a();
        aw awVar = this.j;
        try {
            byte[] a3 = new cf().a(this.d.b());
            awVar.a.T = Base64.encodeToString(a3, 0);
        } catch (Exception e) {
            bo.e(e);
        }
        byte[] b3 = bs.a(g).b(awVar);
        if (!bj.a(g, b3)) {
            if (b3 == null) {
                bo.e("message is null");
                return;
            }
            if (!this.k) {
                b2 = t.a(g, AnalyticsConfig.getAppkey(g), b3);
            } else {
                b2 = t.b(g, AnalyticsConfig.getAppkey(g), b3);
            }
            byte[] c2 = b2.c();
            bs.a(g).h();
            byte[] a4 = this.i.a(c2);
            if (a4 == null) {
                a2 = 1;
            } else {
                a2 = a(a4);
            }
            switch (a2) {
                case 1:
                    if (!this.l) {
                        bs.a(g).a(c2);
                        return;
                    }
                    return;
                case 2:
                    this.d.d();
                    this.h.k();
                    aw.c = 0L;
                    return;
                case 3:
                    this.h.k();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(byte[] bArr) {
        bg bgVar = new bg();
        try {
            new bz(new co.a()).a(bgVar, bArr);
            if (bgVar.a == 1) {
                this.e.b(bgVar.i());
                this.e.d();
            }
            bo.c("send log:" + bgVar.f());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bgVar.a == 1) {
            return 2;
        }
        return 3;
    }
}
