package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.util.j;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.interfaces.IDistrictSearch;
import java.util.HashMap;

/* compiled from: DistrictSearchCore.java */
/* loaded from: classes.dex */
public class am implements IDistrictSearch {
    private static HashMap<Integer, DistrictResult> f;
    private Context a;
    private DistrictSearchQuery b;
    private DistrictSearch.OnDistrictSearchListener c;
    private DistrictSearchQuery d;
    private int e;
    private Handler g = q.a();

    public am(Context context) {
        this.a = context.getApplicationContext();
    }

    private void a(DistrictResult districtResult) {
        f = new HashMap<>();
        if (this.b != null && districtResult != null && this.e > 0 && this.e > this.b.getPageNum()) {
            f.put(Integer.valueOf(this.b.getPageNum()), districtResult);
        }
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public DistrictSearchQuery getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.b = districtSearchQuery;
    }

    private boolean a() {
        return this.b != null;
    }

    protected DistrictResult a(int i) throws AMapException {
        if (b(i)) {
            return f.get(Integer.valueOf(i));
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    private boolean b(int i) {
        return i < this.e && i >= 0;
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public DistrictResult searchDistrict() throws AMapException {
        DistrictResult a;
        try {
            DistrictResult districtResult = new DistrictResult();
            o.a(this.a);
            if (!a()) {
                this.b = new DistrictSearchQuery();
            }
            districtResult.setQuery(this.b.clone());
            if (!this.b.weakEquals(this.d)) {
                this.e = 0;
                this.d = this.b.clone();
                if (f != null) {
                    f.clear();
                }
            }
            if (this.e == 0) {
                a = new j(this.a, this.b.clone()).a();
                if (a != null) {
                    this.e = a.getPageCount();
                    a(a);
                }
            } else {
                a = a(this.b.getPageNum());
                if (a == null) {
                    a = new j(this.a, this.b.clone()).a();
                    if (this.b != null && a != null && this.e > 0 && this.e > this.b.getPageNum()) {
                        f.put(Integer.valueOf(this.b.getPageNum()), a);
                    }
                }
            }
            return a;
        } catch (AMapException e) {
            i.a(e, "DistrictSearch", "searchDistrict");
            throw e;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.am$1] */
    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public void searchDistrictAsyn() {
        try {
            new Thread() { // from class: com.amap.api.services.a.am.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    DistrictResult districtResult;
                    Message obtainMessage;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        districtResult = new DistrictResult();
                        districtResult.setQuery(am.this.b);
                        try {
                            districtResult = am.this.searchDistrict();
                            if (districtResult != null) {
                                districtResult.setAMapException(new AMapException());
                            }
                            obtainMessage.arg1 = 4;
                            obtainMessage.obj = am.this.c;
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(j.c, districtResult);
                            obtainMessage.setData(bundle);
                            if (am.this.g != null) {
                                am.this.g.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e) {
                            districtResult.setAMapException(e);
                            obtainMessage.arg1 = 4;
                            obtainMessage.obj = am.this.c;
                            Bundle bundle2 = new Bundle();
                            bundle2.putParcelable(j.c, districtResult);
                            obtainMessage.setData(bundle2);
                            if (am.this.g != null) {
                                am.this.g.sendMessage(obtainMessage);
                            }
                        } catch (Throwable th) {
                            i.a(th, "DistrictSearch", "searchDistrictAnsyThrowable");
                            obtainMessage.arg1 = 4;
                            obtainMessage.obj = am.this.c;
                            Bundle bundle3 = new Bundle();
                            bundle3.putParcelable(j.c, districtResult);
                            obtainMessage.setData(bundle3);
                            if (am.this.g != null) {
                                am.this.g.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Throwable th2) {
                        obtainMessage.arg1 = 4;
                        obtainMessage.obj = am.this.c;
                        Bundle bundle4 = new Bundle();
                        bundle4.putParcelable(j.c, districtResult);
                        obtainMessage.setData(bundle4);
                        if (am.this.g != null) {
                            am.this.g.sendMessage(obtainMessage);
                        }
                        throw th2;
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public void searchDistrictAnsy() {
        searchDistrictAsyn();
    }

    @Override // com.amap.api.services.interfaces.IDistrictSearch
    public void setOnDistrictSearchListener(DistrictSearch.OnDistrictSearchListener onDistrictSearchListener) {
        this.c = onDistrictSearchListener;
    }
}
