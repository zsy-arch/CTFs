package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class g {
    private Context a = null;
    private WifiManager b = null;
    private a c = null;
    private Handler d = null;
    private Runnable e = new Runnable() { // from class: com.tencent.map.b.g.1
        @Override // java.lang.Runnable
        public final void run() {
            g.a(g.this);
        }
    };
    private int f = 1;
    private c g = null;
    private b h = null;
    private boolean i = false;
    private byte[] j = new byte[0];

    /* loaded from: classes2.dex */
    public class a extends BroadcastReceiver {
        private int a = 4;
        private List<ScanResult> b = null;
        private boolean c = false;

        public a() {
        }

        private void a(List<ScanResult> list) {
            if (list != null) {
                if (this.c) {
                    if (this.b == null) {
                        this.b = new ArrayList();
                    }
                    int size = this.b.size();
                    for (ScanResult scanResult : list) {
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            } else if (this.b.get(i).BSSID.equals(scanResult.BSSID)) {
                                this.b.remove(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        this.b.add(scanResult);
                    }
                    return;
                }
                if (this.b == null) {
                    this.b = new ArrayList();
                } else {
                    this.b.clear();
                }
                for (ScanResult scanResult2 : list) {
                    this.b.add(scanResult2);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                this.a = intent.getIntExtra("wifi_state", 4);
                if (g.this.g != null) {
                    g.this.g.b(this.a);
                }
            }
            if (intent.getAction().equals("android.net.wifi.SCAN_RESULTS") || intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                List<ScanResult> list = null;
                if (g.this.b != null) {
                    list = g.this.b.getScanResults();
                }
                if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    if (list == null) {
                        return;
                    }
                    if (list != null && list.size() == 0) {
                        return;
                    }
                }
                if (this.c || this.b == null || this.b.size() < 4 || list == null || list.size() > 2) {
                    a(list);
                    this.c = false;
                    g.this.h = new b(g.this, this.b, System.currentTimeMillis(), this.a);
                    if (g.this.g != null) {
                        g.this.g.a(g.this.h);
                    }
                    g.this.a(g.this.f * 20000);
                    return;
                }
                a(list);
                this.c = true;
                g.this.a(0L);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Cloneable {
        private List<ScanResult> a;

        public b(g gVar, List<ScanResult> list, long j, int i) {
            this.a = null;
            if (list != null) {
                this.a = new ArrayList();
                for (ScanResult scanResult : list) {
                    this.a.add(scanResult);
                }
            }
        }

        public final List<ScanResult> a() {
            return this.a;
        }

        public final Object clone() {
            b bVar = null;
            try {
                bVar = (b) super.clone();
            } catch (Exception e) {
            }
            if (this.a != null) {
                bVar.a = new ArrayList();
                bVar.a.addAll(this.a);
            }
            return bVar;
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        void a(b bVar);

        void b(int i);
    }

    static /* synthetic */ void a(g gVar) {
        if (gVar.b != null && gVar.b.isWifiEnabled()) {
            gVar.b.startScan();
        }
    }

    public final void a() {
        synchronized (this.j) {
            if (this.i) {
                if (this.a != null && this.c != null) {
                    try {
                        this.a.unregisterReceiver(this.c);
                    } catch (Exception e) {
                    }
                    this.d.removeCallbacks(this.e);
                    this.i = false;
                }
            }
        }
    }

    public final void a(long j) {
        if (this.d != null && this.i) {
            this.d.removeCallbacks(this.e);
            this.d.postDelayed(this.e, j);
        }
    }

    public final boolean a(Context context, c cVar, int i) {
        synchronized (this.j) {
            if (this.i) {
                return true;
            }
            if (context == null || cVar == null) {
                return false;
            }
            this.d = new Handler(Looper.getMainLooper());
            this.a = context;
            this.g = cVar;
            this.f = 1;
            try {
                this.b = (WifiManager) this.a.getSystemService("wifi");
                IntentFilter intentFilter = new IntentFilter();
                this.c = new a();
                if (this.b == null || this.c == null) {
                    return false;
                }
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
                this.a.registerReceiver(this.c, intentFilter);
                a(0L);
                this.i = true;
                return this.i;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public final boolean b() {
        return this.i;
    }

    public final boolean c() {
        if (this.a == null || this.b == null) {
            return false;
        }
        return this.b.isWifiEnabled();
    }
}
