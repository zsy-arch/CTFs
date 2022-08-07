package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InputtipsHandler.java */
/* loaded from: classes.dex */
public class m extends b<InputtipsQuery, ArrayList<Tip>> {
    public m(Context context, InputtipsQuery inputtipsQuery) {
        super(context, inputtipsQuery);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public ArrayList<Tip> a(String str) throws AMapException {
        try {
            return n.m(new JSONObject(str));
        } catch (JSONException e) {
            i.a(e, "InputtipsHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/assistant/inputtips?";
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&keywords=").append(b(((InputtipsQuery) this.a).getKeyword()));
        String city = ((InputtipsQuery) this.a).getCity();
        if (!n.i(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        String type = ((InputtipsQuery) this.a).getType();
        if (!n.i(type)) {
            stringBuffer.append("&type=").append(b(type));
        }
        if (((InputtipsQuery) this.a).getCityLimit()) {
            stringBuffer.append("&citylimit=true");
        } else {
            stringBuffer.append("&citylimit=false");
        }
        LatLonPoint location = ((InputtipsQuery) this.a).getLocation();
        if (location != null) {
            stringBuffer.append("&location=").append(location.getLongitude()).append(",").append(location.getLatitude());
        }
        stringBuffer.append("&key=").append(aw.f(this.d));
        stringBuffer.append("&language=").append(h.c());
        return stringBuffer.toString();
    }
}
