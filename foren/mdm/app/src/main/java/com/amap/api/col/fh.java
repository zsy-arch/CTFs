package com.amap.api.col;

import android.content.Context;
import com.hyphenate.util.EMPrivateConstant;
import org.json.JSONObject;

/* compiled from: BaseTask.java */
/* loaded from: classes.dex */
public class fh extends iq {
    protected static int a = 30000;
    protected static int b = 30000;
    protected String c;
    protected int d;
    protected String e;
    protected int f;
    protected int g;
    protected byte[] h;
    protected Cif i;
    protected Context j;
    protected String k;
    protected gj l;
    protected String m;
    protected String n;
    protected String o;
    protected String p;

    public fh(Context context, String str, int i, String str2, int i2, int i3, byte[] bArr) {
        this.c = null;
        this.d = 0;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.l = fn.a();
        this.k = gd.b(context);
        this.c = str;
        this.d = i;
        this.e = str2;
        this.f = i2;
        this.g = i3;
        this.h = bArr;
        this.i = Cif.a(false);
        this.j = context;
        if (i2 == 1) {
            try {
                String str3 = new String(bArr, "UTF-8");
                JSONObject jSONObject = new JSONObject(str3);
                if (jSONObject.has("start")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("start");
                    this.m = jSONObject2.getString(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME);
                    this.n = jSONObject2.getString("y");
                }
                if (jSONObject.has("end")) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("end");
                    this.o = jSONObject3.getString(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME);
                    this.p = jSONObject3.getString("y");
                }
                fr.a("SHIXIN", "bytes:" + str3);
                fr.a("SHIXIN", "bytes:iModuleID=" + i2 + ",iConnectID=" + i3);
                fr.a("SHIXIN", "坐标:start_x=" + this.m + ",start_y=" + this.n + ",end_x=" + this.o + ",end_y=" + this.p);
            } catch (Exception e) {
                e.printStackTrace();
                fr.d("坐标位置解析错误:" + e.getMessage());
            }
        }
    }

    public static void a(int i) {
        a = i;
    }

    public static void b(int i) {
        b = i;
    }

    @Override // com.amap.api.col.iq
    public void a() {
    }
}
