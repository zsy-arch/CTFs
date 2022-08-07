package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.q;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.ICloudSearch;
import java.util.HashMap;
import java.util.List;

/* compiled from: CloudSearchCore.java */
/* loaded from: classes.dex */
public class al implements ICloudSearch {
    private Context a;
    private CloudSearch.OnCloudSearchListener b;
    private CloudSearch.Query c;
    private int d;
    private HashMap<Integer, CloudResult> e;
    private Handler f = q.a();

    public al(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // com.amap.api.services.interfaces.ICloudSearch
    public void setOnCloudSearchListener(CloudSearch.OnCloudSearchListener onCloudSearchListener) {
        this.b = onCloudSearchListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.amap.api.services.cloud.CloudResult] */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    public CloudResult a(CloudSearch.Query query) throws AMapException {
        CloudResult cloudResult;
        Throwable th;
        CloudResult cloudResult2 = null;
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        if (!b(query)) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        if (!query.queryEquals(this.c)) {
            this.d = 0;
            this.c = query.clone();
            if (this.e != null) {
                this.e.clear();
            }
        }
        cloudResult2 = this.d;
        try {
        } catch (Throwable th3) {
            th = th3;
            i.a(th, "CloudSearch", "searchCloud");
            if (th instanceof AMapException) {
                throw ((AMapException) th);
            }
            th.printStackTrace();
            cloudResult = cloudResult2;
            return cloudResult;
        }
        if (cloudResult2 == 0) {
            CloudResult a = new g(this.a, query).a();
            a(a, query);
            cloudResult2 = a;
        } else {
            CloudResult a2 = a(query.getPageNum());
            cloudResult = a2;
            if (a2 == null) {
                CloudResult a3 = new g(this.a, query).a();
                this.e.put(Integer.valueOf(query.getPageNum()), a3);
                cloudResult2 = a3;
            }
            return cloudResult;
        }
        return cloudResult2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.al$1] */
    @Override // com.amap.api.services.interfaces.ICloudSearch
    public void searchCloudAsyn(final CloudSearch.Query query) {
        try {
            new Thread() { // from class: com.amap.api.services.a.al.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    try {
                        obtainMessage.arg1 = 12;
                        obtainMessage.what = 700;
                        q.d dVar = new q.d();
                        dVar.b = al.this.b;
                        obtainMessage.obj = dVar;
                        dVar.a = al.this.a(query);
                        obtainMessage.arg2 = 1000;
                    } catch (AMapException e) {
                        obtainMessage.arg2 = e.getErrorCode();
                    } finally {
                        al.this.f.sendMessage(obtainMessage);
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CloudItemDetail a(String str, String str2) throws AMapException {
        if (str == null || str.trim().equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } else if (str2 == null || str2.trim().equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } else {
            try {
                return new f(this.a, new y(str, str2)).a();
            } catch (Throwable th) {
                i.a(th, "CloudSearch", "searchCloudDetail");
                if (th instanceof AMapException) {
                    throw ((AMapException) th);
                }
                th.printStackTrace();
                return null;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.al$2] */
    @Override // com.amap.api.services.interfaces.ICloudSearch
    public void searchCloudDetailAsyn(final String str, final String str2) {
        try {
            new Thread() { // from class: com.amap.api.services.a.al.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    try {
                        obtainMessage.arg1 = 12;
                        obtainMessage.what = 701;
                        q.c cVar = new q.c();
                        cVar.b = al.this.b;
                        obtainMessage.obj = cVar;
                        cVar.a = al.this.a(str, str2);
                        obtainMessage.arg2 = 1000;
                    } catch (AMapException e) {
                        obtainMessage.arg2 = e.getErrorCode();
                    } finally {
                        al.this.f.sendMessage(obtainMessage);
                    }
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(CloudResult cloudResult, CloudSearch.Query query) {
        this.e = new HashMap<>();
        if (this.d > 0) {
            this.e.put(Integer.valueOf(query.getPageNum()), cloudResult);
        }
    }

    protected CloudResult a(int i) {
        if (b(i)) {
            return this.e.get(Integer.valueOf(i));
        }
        throw new IllegalArgumentException("page out of range");
    }

    private boolean b(int i) {
        return i <= this.d && i > 0;
    }

    private boolean b(CloudSearch.Query query) {
        if (query == null || i.a(query.getTableID()) || query.getBound() == null) {
            return false;
        }
        if (query.getBound() != null && query.getBound().getShape().equals("Bound") && query.getBound().getCenter() == null) {
            return false;
        }
        if (query.getBound() != null && query.getBound().getShape().equals("Rectangle")) {
            LatLonPoint lowerLeft = query.getBound().getLowerLeft();
            LatLonPoint upperRight = query.getBound().getUpperRight();
            if (lowerLeft == null || upperRight == null || lowerLeft.getLatitude() >= upperRight.getLatitude() || lowerLeft.getLongitude() >= upperRight.getLongitude()) {
                return false;
            }
        }
        if (query.getBound() != null && query.getBound().getShape().equals("Polygon")) {
            List<LatLonPoint> polyGonList = query.getBound().getPolyGonList();
            for (int i = 0; i < polyGonList.size(); i++) {
                if (polyGonList.get(i) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
