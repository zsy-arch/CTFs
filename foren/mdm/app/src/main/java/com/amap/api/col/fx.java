package com.amap.api.col;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.trace.LBSTraceBase;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceStatusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: TraceManager.java */
/* loaded from: classes.dex */
public class fx implements LocationSource.OnLocationChangedListener, LBSTraceBase {
    private Context a;
    private CoordinateConverter b;
    private TraceStatusListener g;
    private ad h;
    private long e = 2000;
    private int f = 5;
    private List<TraceLocation> i = new ArrayList();
    private int j = 0;
    private int k = 0;
    private long l = 0;
    private ExecutorService c = Executors.newFixedThreadPool(1);
    private c m = new c(Looper.getMainLooper());
    private ExecutorService d = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors() * 2) + 3);

    public fx(Context context) {
        this.a = context.getApplicationContext();
        this.b = new CoordinateConverter(this.a);
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        try {
            this.c.execute(new a(i, list, i2, traceListener));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void setLocationInterval(long j) {
        this.e = j;
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void setTraceStatusInterval(int i) {
        this.f = Math.max(i, 2);
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void startTrace(TraceStatusListener traceStatusListener) {
        if (this.a == null) {
            Log.w("LBSTraceClient", "Context need to be initialized");
            return;
        }
        this.l = System.currentTimeMillis();
        this.g = traceStatusListener;
        if (this.h == null) {
            this.h = new ad(this.a);
            this.h.a(this.e);
            this.h.activate(this);
        }
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public void onLocationChanged(Location location) {
        if (this.g != null) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.l >= 30000 && this.g != null) {
                    this.g.onTraceStatus(null, null, LBSTraceClient.LOCATE_TIMEOUT_ERROR);
                    this.l = currentTimeMillis;
                }
                Bundle extras = location.getExtras();
                int i = extras.getInt(MyLocationStyle.ERROR_CODE);
                if (i != 0) {
                    Log.w("LBSTraceClient", "Locate failed [errorCode:\"" + i + "\"  errorInfo:" + extras.getString(MyLocationStyle.ERROR_INFO) + "\"]");
                    return;
                }
                synchronized (this.i) {
                    this.i.add(new TraceLocation(location.getLatitude(), location.getLongitude(), location.getSpeed(), location.getBearing(), location.getTime()));
                    this.j++;
                    if (this.j == this.f) {
                        this.k += this.j;
                        a(this.k);
                        this.l = System.currentTimeMillis();
                        this.j = 0;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a(int i) {
        ArrayList arrayList = new ArrayList(this.i.subList(0, i));
        queryProcessedTrace(i, arrayList, 1, new b(arrayList));
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void stopTrace() {
        a();
        synchronized (this.i) {
            if (this.i.size() > this.k) {
                int size = ((this.i.size() - this.k) / this.f) + 1;
                for (int i = 0; i < size; i++) {
                    if (i == size - 1) {
                        a(this.i.size());
                    } else {
                        this.k += this.f;
                        a(this.k);
                    }
                }
            }
        }
    }

    private void a() {
        if (this.h != null) {
            this.h.deactivate();
            this.h = null;
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void destroy() {
        try {
            a();
            if (this.c != null && !this.c.isShutdown()) {
                this.c.shutdownNow();
                this.c = null;
            }
            if (this.d != null && !this.d.isShutdown()) {
                this.d.shutdownNow();
                this.d = null;
            }
            synchronized (this.i) {
                if (this.i != null) {
                    this.i.clear();
                    this.i = null;
                }
            }
            this.g = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.a = null;
        this.b = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TraceManager.java */
    /* loaded from: classes.dex */
    public class b implements TraceListener {
        private final List<TraceLocation> b;

        public b(List<TraceLocation> list) {
            this.b = list;
        }

        @Override // com.amap.api.trace.TraceListener
        public void onRequestFailed(int i, String str) {
            fx.this.g.onTraceStatus(this.b, null, str);
        }

        @Override // com.amap.api.trace.TraceListener
        public void onTraceProcessing(int i, int i2, List<LatLng> list) {
        }

        @Override // com.amap.api.trace.TraceListener
        public void onFinished(int i, List<LatLng> list, int i2, int i3) {
            fx.this.g.onTraceStatus(this.b, list, LBSTraceClient.TRACE_SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TraceManager.java */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        private int c;
        private int d;
        private List<TraceLocation> e;
        private TraceListener g;
        private List<TraceLocation> b = new ArrayList();
        private String f = Cdo.a();

        public a(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
            this.c = i2;
            this.d = i;
            this.e = list;
            this.g = traceListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                fx.this.m.a(this.g);
                int a = a();
                if (this.e == null || this.e.size() < 2) {
                    fy.a().a(fx.this.m, this.d, LBSTraceClient.MIN_GRASP_POINT_ERROR);
                    return;
                }
                for (TraceLocation traceLocation : this.e) {
                    TraceLocation copy = traceLocation.copy();
                    if (copy != null && copy.getLatitude() > 0.0d && copy.getLongitude() > 0.0d) {
                        this.b.add(copy);
                    }
                }
                int size = (this.b.size() - 2) / 500;
                fy.a().a(this.f, this.d, size, a);
                int i = 500;
                int i2 = 0;
                while (i2 <= size) {
                    if (i2 == size) {
                        i = this.b.size();
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < i; i3++) {
                        TraceLocation remove = this.b.remove(0);
                        if (remove != null) {
                            if (this.c != 1) {
                                if (this.c == 3) {
                                    fx.this.b.from(CoordinateConverter.CoordType.BAIDU);
                                } else if (this.c == 2) {
                                    fx.this.b.from(CoordinateConverter.CoordType.GPS);
                                }
                                fx.this.b.coord(new LatLng(remove.getLatitude(), remove.getLongitude()));
                                LatLng convert = fx.this.b.convert();
                                if (convert != null) {
                                    remove.setLatitude(convert.latitude);
                                    remove.setLongitude(convert.longitude);
                                }
                            }
                            arrayList.add(remove);
                        }
                    }
                    if (arrayList.size() < 2) {
                        i = i;
                    } else if (arrayList.size() > 500) {
                        i = i;
                    } else {
                        fx.this.d.execute(new fw(fx.this.a, fx.this.m, arrayList, this.c, this.f, this.d, i2));
                        i2++;
                        try {
                            Thread.sleep(50L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i = i;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private int a() {
            if (this.e == null || this.e.size() == 0) {
                return 0;
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (TraceLocation traceLocation : this.e) {
                if (traceLocation != null) {
                    if (traceLocation.getSpeed() < 0.01d) {
                        arrayList.add(traceLocation);
                    } else {
                        i = a(arrayList) + i;
                        arrayList.clear();
                    }
                }
            }
            return i;
        }

        private int a(List<TraceLocation> list) {
            int i;
            int size = list.size();
            if (size <= 1) {
                return 0;
            }
            TraceLocation traceLocation = list.get(0);
            TraceLocation traceLocation2 = list.get(size - 1);
            if (traceLocation == null || traceLocation2 == null) {
                return 0;
            }
            if (traceLocation == null || traceLocation2 == null) {
                i = 0;
            } else {
                i = (int) ((traceLocation2.getTime() - traceLocation.getTime()) / 1000);
            }
            return i;
        }
    }

    /* compiled from: TraceManager.java */
    /* loaded from: classes.dex */
    static class c extends Handler {
        private TraceListener a;

        public c(Looper looper) {
            super(looper);
        }

        public void a(TraceListener traceListener) {
            this.a = traceListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data;
            try {
                if (!(this.a == null || (data = message.getData()) == null)) {
                    int i = data.getInt("lineID");
                    switch (message.what) {
                        case 100:
                            this.a.onTraceProcessing(i, message.arg1, (List) message.obj);
                            break;
                        case 101:
                            this.a.onFinished(i, (List) message.obj, message.arg1, message.arg2);
                            break;
                        case 102:
                            this.a.onRequestFailed(i, (String) message.obj);
                            break;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
