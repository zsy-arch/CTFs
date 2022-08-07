package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.AnalyticsConfig;
import java.io.File;
import org.json.JSONObject;

/* compiled from: Envelope.java */
/* loaded from: classes2.dex */
public class t {
    private String e;
    private int j;
    private int k;
    private byte[] l;
    private byte[] m;
    private final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0};
    private final int b = 1;
    private final int c = 0;
    private String d = "1.0";
    private byte[] f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private boolean n = false;

    private t(byte[] bArr, String str, byte[] bArr2) throws Exception {
        this.e = null;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = bk.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static String a(Context context) {
        SharedPreferences a = aq.a(context);
        if (a == null) {
            return null;
        }
        return a.getString(GameAppOperation.GAME_SIGNATURE, null);
    }

    public void a(String str) {
        this.f = bj.a(str);
    }

    public String a() {
        return bj.a(this.f);
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public static t a(Context context, String str, byte[] bArr) {
        try {
            String q = bl.q(context);
            String c = bl.c(context);
            SharedPreferences a = aq.a(context);
            String string = a.getString(GameAppOperation.GAME_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            t tVar = new t(bArr, str, (c + q).getBytes());
            tVar.a(string);
            tVar.a(i);
            tVar.b();
            a.edit().putInt("serial", i + 1).putString(GameAppOperation.GAME_SIGNATURE, tVar.a()).commit();
            tVar.b(context);
            return tVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static t b(Context context, String str, byte[] bArr) {
        try {
            String q = bl.q(context);
            String c = bl.c(context);
            SharedPreferences a = aq.a(context);
            String string = a.getString(GameAppOperation.GAME_SIGNATURE, null);
            int i = a.getInt("serial", 1);
            t tVar = new t(bArr, str, (c + q).getBytes());
            tVar.a(true);
            tVar.a(string);
            tVar.a(i);
            tVar.b();
            a.edit().putInt("serial", i + 1).putString(GameAppOperation.GAME_SIGNATURE, tVar.a()).commit();
            tVar.b(context);
            return tVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b() {
        if (this.f == null) {
            this.f = d();
        }
        if (this.n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f, 1, bArr, 0, 16);
                this.l = bj.a(this.l, bArr);
            } catch (Exception e) {
            }
        }
        this.g = a(this.f, this.j);
        this.h = e();
    }

    private byte[] a(byte[] bArr, int i) {
        byte[] b = bj.b(this.m);
        byte[] b2 = bj.b(this.l);
        int length = b.length;
        byte[] bArr2 = new byte[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (int i3 = 0; i3 < 2; i3++) {
            bArr2[i3] = bArr[i3];
            bArr2[(bArr2.length - i3) - 1] = bArr[(bArr.length - i3) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i4 = 0; i4 < bArr2.length; i4++) {
            bArr2[i4] = (byte) (bArr2[i4] ^ bArr3[i4 % 4]);
        }
        return bArr2;
    }

    private byte[] d() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] e() {
        return bj.b((bj.a(this.f) + this.i + this.j + this.k + bj.a(this.g)).getBytes());
    }

    public byte[] c() {
        bh bhVar = new bh();
        bhVar.a(this.d);
        bhVar.b(this.e);
        bhVar.c(bj.a(this.f));
        bhVar.a(this.i);
        bhVar.c(this.j);
        bhVar.d(this.k);
        bhVar.a(this.l);
        bhVar.e(this.n ? 1 : 0);
        bhVar.d(bj.a(this.g));
        bhVar.e(bj.a(this.h));
        try {
            return new cf().a(bhVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(Context context) {
        String str = this.e;
        String g = x.a(context).b().g(null);
        String a = bj.a(this.f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f, 2, bArr, 0, 16);
        String a2 = bj.a(bj.b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", str);
            if (g != null) {
                jSONObject.put("umid", g);
            }
            jSONObject.put(GameAppOperation.GAME_SIGNATURE, a);
            jSONObject.put("checksum", a2);
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            bm.a(new File(file, "exchangeIdentity.json"), jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appkey", str);
            jSONObject2.put("channel", AnalyticsConfig.getChannel(context));
            if (g != null) {
                jSONObject2.put("umid", bm.b(g));
            }
            bm.a(new File(context.getFilesDir(), "exid.dat"), jSONObject2.toString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("version : %s\n", this.d));
        sb.append(String.format("address : %s\n", this.e));
        sb.append(String.format("signature : %s\n", bj.a(this.f)));
        sb.append(String.format("serial : %s\n", Integer.valueOf(this.i)));
        sb.append(String.format("timestamp : %d\n", Integer.valueOf(this.j)));
        sb.append(String.format("length : %d\n", Integer.valueOf(this.k)));
        sb.append(String.format("guid : %s\n", bj.a(this.g)));
        sb.append(String.format("checksum : %s ", bj.a(this.h)));
        Object[] objArr = new Object[1];
        if (!this.n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        sb.append(String.format("codex : %d", objArr));
        return sb.toString();
    }
}
