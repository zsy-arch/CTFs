package com.amap.api.col;

import android.content.Context;
import android.os.Handler;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.sys.a;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.TraceLocation;
import com.hyphenate.util.EMPrivateConstant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* compiled from: TraceHandler.java */
/* loaded from: classes.dex */
public class fw extends fu<List<TraceLocation>, List<LatLng>> implements Runnable {
    private List<TraceLocation> i;
    private Handler j;
    private int k;
    private int l;
    private String m;

    public fw(Context context, Handler handler, List<TraceLocation> list, int i, String str, int i2, int i3) {
        super(context, list);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.i = list;
        this.j = handler;
        this.l = i2;
        this.k = i3;
        this.m = str;
    }

    @Override // com.amap.api.col.fu
    protected String f() {
        JSONArray jSONArray = new JSONArray();
        long j = 0;
        for (int i = 0; i < this.i.size(); i++) {
            TraceLocation traceLocation = this.i.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME, traceLocation.getLongitude());
                jSONObject.put("y", traceLocation.getLatitude());
                jSONObject.put(av.aw, (int) traceLocation.getBearing());
                long time = traceLocation.getTime();
                if (i == 0) {
                    if (time == 0) {
                        time = (System.currentTimeMillis() - 10000) / 1000;
                    }
                    jSONObject.put(f.aI, time / 1000);
                    j = time;
                } else if (time == 0 || time - j < 1000) {
                    jSONObject.put(f.aI, 1);
                    j = time;
                } else {
                    jSONObject.put(f.aI, (time - j) / 1000);
                    j = time;
                }
                jSONObject.put("sp", (int) traceLocation.getSpeed());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        this.e = c() + a.b + jSONArray.toString();
        return jSONArray.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public List<LatLng> a(String str) throws fs {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("points");
            if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    arrayList.add(new LatLng(Double.parseDouble(optJSONObject.optString("y")), Double.parseDouble(optJSONObject.optString(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME))));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        new ArrayList();
        try {
            try {
                fy.a().a(this.m, this.k, d());
                fy.a().a(this.m).a(this.j);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (fs e) {
            fy.a().a(this.j, this.l, e.a());
        }
    }
}
