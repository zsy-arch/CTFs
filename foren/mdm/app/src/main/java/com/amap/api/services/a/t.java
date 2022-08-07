package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.nearby.UploadInfo;

/* compiled from: NearbyUpdateHandler.java */
/* loaded from: classes.dex */
public class t extends b<UploadInfo, Integer> {
    private Context h;
    private UploadInfo i;

    public t(Context context, UploadInfo uploadInfo) {
        super(context, uploadInfo);
        this.h = context;
        this.i = uploadInfo;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.h));
        stringBuffer.append("&userid=").append(this.i.getUserID());
        LatLonPoint point = this.i.getPoint();
        stringBuffer.append("&location=").append(((int) (point.getLongitude() * 1000000.0d)) / 1000000.0f).append(",").append(((int) (point.getLatitude() * 1000000.0d)) / 1000000.0f);
        stringBuffer.append("&coordtype=").append(this.i.getCoordType());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public Integer a(String str) throws AMapException {
        return 0;
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.b() + "/nearby/data/create";
    }
}
