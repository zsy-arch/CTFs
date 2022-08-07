package u.aly;

import android.content.Context;
import android.text.TextUtils;
import u.aly.x;

/* compiled from: ABTest.java */
/* loaded from: classes2.dex */
public class ax implements ap {
    private static ax h = null;
    private boolean a = false;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private float e = 0.0f;
    private float f = 0.0f;
    private Context g;

    public static synchronized ax a(Context context) {
        ax axVar;
        synchronized (ax.class) {
            if (h == null) {
                x.a b = x.a(context).b();
                h = new ax(context, b.f(null), b.d(0));
            }
            axVar = h;
        }
        return axVar;
    }

    private ax(Context context, String str, int i) {
        this.g = null;
        this.g = context;
        a(str, i);
    }

    private float b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue() / 1048576.0f;
    }

    public void a(String str, int i) {
        this.c = i;
        String a = t.a(this.g);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.a = false;
            return;
        }
        try {
            this.e = b(a, 12);
            this.f = b(a, 6);
            if (str.startsWith("SIG7")) {
                b(str);
            } else if (str.startsWith("FIXED")) {
                c(str);
            }
        } catch (Exception e) {
            this.a = false;
            bo.e("v:" + str, e);
        }
    }

    public static boolean a(String str) {
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (!split[0].startsWith("SIG7") || split[1].split(",").length != split[5].split(",").length) {
            return split[0].startsWith("FIXED") && split[5].split(",").length >= (parseInt = Integer.parseInt(split[1])) && parseInt >= 1;
        }
        return true;
    }

    private void b(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            if (this.e > (split[2].equals("SIG13") ? Float.valueOf(split[3]).floatValue() : 0.0f)) {
                this.a = false;
                return;
            }
            float[] fArr = null;
            if (split[0].equals("SIG7")) {
                String[] split2 = split[1].split(",");
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                String[] split3 = split[5].split(",");
                int[] iArr2 = new int[split3.length];
                for (int i2 = 0; i2 < split3.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split3[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (bl.n(this.g)) {
                    this.a = false;
                    return;
                }
                try {
                    String[] split4 = split[5].split(",");
                    iArr = new int[split4.length];
                    for (int i3 = 0; i3 < split4.length; i3++) {
                        iArr[i3] = Integer.valueOf(split4[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            float f = 0.0f;
            int i4 = 0;
            while (true) {
                if (i4 >= fArr.length) {
                    i4 = -1;
                    break;
                }
                f += fArr[i4];
                if (this.f < f) {
                    break;
                }
                i4++;
            }
            if (i4 != -1) {
                this.a = true;
                this.d = i4 + 1;
                if (iArr != null) {
                    this.b = iArr[i4];
                    return;
                }
                return;
            }
            this.a = false;
        }
    }

    private void c(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.e > f) {
                this.a = false;
                return;
            }
            int intValue = split[0].equals("FIXED") ? Integer.valueOf(split[1]).intValue() : -1;
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                String[] split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (bl.n(this.g)) {
                    this.a = false;
                    return;
                }
                try {
                    String[] split3 = split[5].split(",");
                    iArr = new int[split3.length];
                    for (int i2 = 0; i2 < split3.length; i2++) {
                        iArr[i2] = Integer.valueOf(split3[i2]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (intValue != -1) {
                this.a = true;
                this.d = intValue;
                if (iArr != null) {
                    this.b = iArr[intValue - 1];
                    return;
                }
                return;
            }
            this.a = false;
        }
    }

    public boolean a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public void a(aw awVar) {
        if (this.a) {
            awVar.b.f.put("client_test", Integer.valueOf(this.d));
        }
    }

    @Override // u.aly.ap
    public void a(x.a aVar) {
        a(aVar.f(null), aVar.d(0));
    }

    public String toString() {
        return " p13:" + this.e + " p07:" + this.f + " policy:" + this.b + " interval:" + this.c;
    }
}
