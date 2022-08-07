package com.amap.api.services.a;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.services.a.q;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.INearbySearch;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.nearby.UploadInfoCallback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* compiled from: NearbySearchCore.java */
/* loaded from: classes.dex */
public class ap implements INearbySearch {
    private static long e = 0;
    private String b;
    private Context c;
    private ExecutorService f;
    private UploadInfoCallback k;
    private TimerTask l;
    private List<NearbySearch.NearbyListener> a = new ArrayList();
    private LatLonPoint g = null;
    private String h = null;
    private boolean i = false;
    private Timer j = new Timer();
    private q d = q.a();

    public ap(Context context) {
        this.c = context.getApplicationContext();
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public synchronized void addNearbyListener(NearbySearch.NearbyListener nearbyListener) {
        this.a.add(nearbyListener);
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public synchronized void removeNearbyListener(NearbySearch.NearbyListener nearbyListener) {
        if (nearbyListener != null) {
            this.a.remove(nearbyListener);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.ap$1] */
    @Override // com.amap.api.services.interfaces.INearbySearch
    public void clearUserInfoAsyn() {
        try {
            new Thread() { // from class: com.amap.api.services.a.ap.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage;
                    try {
                        obtainMessage = ap.this.d.obtainMessage();
                        obtainMessage.arg1 = 8;
                        obtainMessage.obj = ap.this.a;
                        try {
                            ap.this.a();
                            obtainMessage.what = 1000;
                            if (ap.this.d != null) {
                                ap.this.d.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e2) {
                            obtainMessage.what = e2.getErrorCode();
                            i.a(e2, "NearbySearch", "clearUserInfoAsyn");
                            if (ap.this.d != null) {
                                ap.this.d.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Throwable th) {
                        if (ap.this.d != null) {
                            ap.this.d.sendMessage(obtainMessage);
                        }
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "NearbySearch", "clearUserInfoAsynThrowable");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() throws AMapException {
        try {
            if (this.i) {
                throw new AMapException(AMapException.AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR);
            } else if (!a(this.b)) {
                throw new AMapException(AMapException.AMAP_CLIENT_USERID_ILLEGAL);
            } else {
                o.a(this.c);
                return new r(this.c, this.b).a().intValue();
            }
        } catch (AMapException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new AMapException(AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public void setUserID(String str) {
        this.b = str;
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public synchronized void startUploadNearbyInfoAuto(UploadInfoCallback uploadInfoCallback, int i) {
        if (i < 7000) {
            i = 7000;
        }
        this.k = uploadInfoCallback;
        if (this.i && this.l != null) {
            this.l.cancel();
        }
        this.i = true;
        this.l = new a();
        this.j.schedule(this.l, 0L, i);
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public synchronized void stopUploadNearbyInfoAuto() {
        if (this.l != null) {
            this.l.cancel();
        }
        this.i = false;
        this.l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(UploadInfo uploadInfo) {
        return this.i ? AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR : b(uploadInfo);
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[a-z0-9A-Z_-]{1,32}$").matcher(str).find();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(UploadInfo uploadInfo) {
        try {
            o.a(this.c);
            if (uploadInfo == null) {
                return AMapException.CODE_AMAP_CLIENT_NEARBY_NULL_RESULT;
            }
            long time = new Date().getTime();
            if (time - e < 6500) {
                return AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT;
            }
            e = time;
            String userID = uploadInfo.getUserID();
            if (!a(userID)) {
                return AMapException.CODE_AMAP_CLIENT_USERID_ILLEGAL;
            }
            if (TextUtils.isEmpty(this.h)) {
                this.h = userID;
            }
            if (!userID.equals(this.h)) {
                return AMapException.CODE_AMAP_CLIENT_USERID_ILLEGAL;
            }
            LatLonPoint point = uploadInfo.getPoint();
            if (point == null || point.equals(this.g)) {
                return AMapException.CODE_AMAP_CLIENT_UPLOAD_LOCATION_ERROR;
            }
            new t(this.c, uploadInfo).a();
            this.g = point.copy();
            return 1000;
        } catch (AMapException e2) {
            return e2.getErrorCode();
        } catch (Throwable th) {
            return 1900;
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public void uploadNearbyInfoAsyn(final UploadInfo uploadInfo) {
        if (this.f == null) {
            this.f = Executors.newSingleThreadExecutor();
        }
        this.f.submit(new Runnable() { // from class: com.amap.api.services.a.ap.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Message obtainMessage = ap.this.d.obtainMessage();
                    obtainMessage.arg1 = 10;
                    obtainMessage.obj = ap.this.a;
                    obtainMessage.what = ap.this.a(uploadInfo);
                    ap.this.d.sendMessage(obtainMessage);
                } catch (Throwable th) {
                    i.a(th, "NearbySearch", "uploadNearbyInfoAsyn");
                }
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.ap$3] */
    @Override // com.amap.api.services.interfaces.INearbySearch
    public void searchNearbyInfoAsyn(final NearbySearch.NearbyQuery nearbyQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.ap.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage;
                    try {
                        obtainMessage = ap.this.d.obtainMessage();
                        obtainMessage.arg1 = 9;
                        q.f fVar = new q.f();
                        fVar.a = ap.this.a;
                        obtainMessage.obj = fVar;
                        try {
                            fVar.b = ap.this.searchNearbyInfo(nearbyQuery);
                            obtainMessage.what = 1000;
                            if (ap.this.d != null) {
                                ap.this.d.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e2) {
                            obtainMessage.what = e2.getErrorCode();
                            i.a(e2, "NearbySearch", "searchNearbyInfoAsyn");
                            if (ap.this.d != null) {
                                ap.this.d.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Throwable th) {
                        if (ap.this.d != null) {
                            ap.this.d.sendMessage(obtainMessage);
                        }
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "NearbySearch", "searchNearbyInfoAsynThrowable");
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public NearbySearchResult searchNearbyInfo(NearbySearch.NearbyQuery nearbyQuery) throws AMapException {
        try {
            o.a(this.c);
            if (a(nearbyQuery)) {
                return new s(this.c, nearbyQuery).a();
            }
            throw new AMapException("无效的参数 - IllegalArgumentException");
        } catch (AMapException e2) {
            throw e2;
        } catch (Throwable th) {
            i.a(th, "NearbySearch", "searchNearbyInfo");
            throw new AMapException(AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        }
    }

    private boolean a(NearbySearch.NearbyQuery nearbyQuery) {
        if (nearbyQuery == null || nearbyQuery.getCenterPoint() == null) {
            return false;
        }
        return true;
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public synchronized void destroy() {
        this.j.cancel();
    }

    /* compiled from: NearbySearchCore.java */
    /* loaded from: classes.dex */
    private class a extends TimerTask {
        private a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                if (ap.this.k != null) {
                    int b = ap.this.b(ap.this.k.OnUploadInfoCallback());
                    Message obtainMessage = ap.this.d.obtainMessage();
                    obtainMessage.arg1 = 10;
                    obtainMessage.obj = ap.this.a;
                    obtainMessage.what = b;
                    ap.this.d.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                i.a(th, "NearbySearch", "UpdateDataTask");
            }
        }
    }
}
