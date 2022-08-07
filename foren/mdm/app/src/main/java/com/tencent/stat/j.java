package com.tencent.stat;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.tencent.stat.a.i;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class j implements Runnable {
    private Context a;
    private Map<String, Integer> b;

    public j(Context context, Map<String, Integer> map) {
        this.a = null;
        this.b = null;
        this.a = context;
        if (map != null) {
            this.b = map;
        }
    }

    private NetworkMonitor a(String str, int i) {
        StatLogger statLogger;
        StatLogger statLogger2;
        StatLogger statLogger3;
        StatLogger statLogger4;
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        int i2 = 0;
        try {
            try {
                networkMonitor.setDomain(str);
                networkMonitor.setPort(i);
                long currentTimeMillis = System.currentTimeMillis();
                InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
                socket.connect(inetSocketAddress, 30000);
                networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
                networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
                if (socket != null) {
                    socket.close();
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Throwable th) {
                        statLogger4 = StatService.i;
                        statLogger4.e(th);
                    }
                }
            } catch (IOException e) {
                i2 = -1;
                statLogger2 = StatService.i;
                statLogger2.e((Exception) e);
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Throwable th2) {
                        statLogger3 = StatService.i;
                        statLogger3.e(th2);
                    }
                }
            }
            networkMonitor.setStatusCode(i2);
            return networkMonitor;
        } catch (Throwable th3) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Throwable th4) {
                    statLogger = StatService.i;
                    statLogger.e(th4);
                }
            }
            throw th3;
        }
    }

    private Map<String, Integer> a() {
        String str;
        StatLogger statLogger;
        HashMap hashMap = new HashMap();
        String a = StatConfig.a("__MTA_TEST_SPEED__", (String) null);
        if (!(a == null || a.trim().length() == 0)) {
            for (String str2 : a.split(h.b)) {
                String[] split = str2.split(",");
                if (!(split == null || split.length != 2 || (str = split[0]) == null || str.trim().length() == 0)) {
                    try {
                        hashMap.put(str, Integer.valueOf(Integer.valueOf(split[1]).intValue()));
                    } catch (NumberFormatException e) {
                        statLogger = StatService.i;
                        statLogger.e((Exception) e);
                    }
                }
            }
        }
        return hashMap;
    }

    @Override // java.lang.Runnable
    public void run() {
        StatLogger statLogger;
        StatLogger statLogger2;
        StatLogger statLogger3;
        StatLogger statLogger4;
        try {
            if (k.h(this.a)) {
                if (this.b == null) {
                    this.b = a();
                }
                if (this.b == null || this.b.size() == 0) {
                    statLogger2 = StatService.i;
                    statLogger2.w("empty domain list.");
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (Map.Entry<String, Integer> entry : this.b.entrySet()) {
                    String key = entry.getKey();
                    if (key == null || key.length() == 0) {
                        statLogger3 = StatService.i;
                        statLogger3.w("empty domain name.");
                    } else if (entry.getValue() == null) {
                        statLogger4 = StatService.i;
                        statLogger4.w("port is null for " + key);
                    } else {
                        jSONArray.put(a(entry.getKey(), entry.getValue().intValue()).toJSONObject());
                    }
                }
                if (jSONArray.length() != 0) {
                    i iVar = new i(this.a, StatService.a(this.a, false));
                    iVar.a(jSONArray.toString());
                    if (StatService.c(this.a) != null) {
                        StatService.c(this.a).post(new k(iVar));
                    }
                }
            }
        } catch (Throwable th) {
            statLogger = StatService.i;
            statLogger.e(th);
        }
    }
}
