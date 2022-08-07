package com.tencent.open.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alipay.sdk.packet.d;
import com.alipay.sdk.sys.a;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.yolanda.nohttp.Headers;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class g {
    protected static g a;
    protected HandlerThread e;
    protected Handler f;
    protected Random b = new Random();
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
    protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
    protected Executor g = ThreadManager.newSerialExecutor();
    protected Executor h = ThreadManager.newSerialExecutor();

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    private g() {
        this.e = null;
        if (this.e == null) {
            this.e = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e.start();
        }
        if (this.e.isAlive() && this.e.getLooper() != null) {
            this.f = new Handler(this.e.getLooper()) { // from class: com.tencent.open.b.g.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1000:
                            g.this.b();
                            break;
                        case 1001:
                            g.this.e();
                            break;
                    }
                    super.handleMessage(message);
                }
            };
        }
    }

    public void a(final Bundle bundle, String str, final boolean z) {
        if (bundle != null) {
            f.a("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
            if (a("report_via", str) || z) {
                this.g.execute(new Runnable() { // from class: com.tencent.open.b.g.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("uin", Constants.DEFAULT_UIN);
                            bundle2.putString("imei", c.b(Global.getContext()));
                            bundle2.putString("imsi", c.c(Global.getContext()));
                            bundle2.putString("android_id", c.d(Global.getContext()));
                            bundle2.putString("mac", c.a());
                            bundle2.putString(Constants.PARAM_PLATFORM, "1");
                            bundle2.putString("os_ver", Build.VERSION.RELEASE);
                            bundle2.putString(RequestParameters.POSITION, Util.getLocation(Global.getContext()));
                            bundle2.putString("network", a.a(Global.getContext()));
                            bundle2.putString("language", c.b());
                            bundle2.putString("resolution", c.a(Global.getContext()));
                            bundle2.putString("apn", a.b(Global.getContext()));
                            bundle2.putString("model_name", Build.MODEL);
                            bundle2.putString("timezone", TimeZone.getDefault().getID());
                            bundle2.putString("sdk_ver", Constants.SDK_VERSION);
                            bundle2.putString("qz_ver", Util.getAppVersionName(Global.getContext(), "com.qzone"));
                            bundle2.putString("qq_ver", Util.getVersionName(Global.getContext(), "com.tencent.mobileqq"));
                            bundle2.putString("qua", Util.getQUA3(Global.getContext(), Global.getPackageName()));
                            bundle2.putString("packagename", Global.getPackageName());
                            bundle2.putString("app_ver", Util.getAppVersionName(Global.getContext(), Global.getPackageName()));
                            if (bundle != null) {
                                bundle2.putAll(bundle);
                            }
                            g.this.d.add(new b(bundle2));
                            int size = g.this.d.size();
                            int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                            if (i == 0) {
                                i = 10000;
                            }
                            if (g.this.a("report_via", size) || z) {
                                g.this.e();
                                g.this.f.removeMessages(1001);
                            } else if (!g.this.f.hasMessages(1001)) {
                                Message obtain = Message.obtain();
                                obtain.what = 1001;
                                g.this.f.sendMessageDelayed(obtain, i);
                            }
                        } catch (Exception e) {
                            f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                        }
                    }
                });
            }
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, "", false);
    }

    public void a(final String str, final long j, final long j2, final long j3, final int i, final String str2, final boolean z) {
        f.a("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
        if (a("report_cgi", "" + i) || z) {
            this.h.execute(new Runnable() { // from class: com.tencent.open.b.g.3
                @Override // java.lang.Runnable
                public void run() {
                    int i2;
                    int i3 = 1;
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
                        Bundle bundle = new Bundle();
                        String a2 = a.a(Global.getContext());
                        bundle.putString("apn", a2);
                        bundle.putString("appid", "1000067");
                        bundle.putString("commandid", str);
                        bundle.putString("detail", str2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("network=").append(a2).append('&');
                        StringBuilder append = sb.append("sdcard=");
                        if (Environment.getExternalStorageState().equals("mounted")) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        append.append(i2).append('&');
                        sb.append("wifi=").append(a.e(Global.getContext()));
                        bundle.putString("deviceInfo", sb.toString());
                        int a3 = 100 / g.this.a(i);
                        if (a3 > 0) {
                            i3 = a3 > 100 ? 100 : a3;
                        }
                        bundle.putString("frequency", i3 + "");
                        bundle.putString("reqSize", j2 + "");
                        bundle.putString("resultCode", i + "");
                        bundle.putString("rspSize", j3 + "");
                        bundle.putString("timeCost", elapsedRealtime + "");
                        bundle.putString("uin", Constants.DEFAULT_UIN);
                        g.this.c.add(new b(bundle));
                        int size = g.this.c.size();
                        int i4 = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                        if (i4 == 0) {
                            i4 = 10000;
                        }
                        if (g.this.a("report_cgi", size) || z) {
                            g.this.b();
                            g.this.f.removeMessages(1000);
                        } else if (!g.this.f.hasMessages(1000)) {
                            Message obtain = Message.obtain();
                            obtain.what = 1000;
                            g.this.f.sendMessageDelayed(obtain, i4);
                        }
                    } catch (Exception e) {
                        f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    protected void b() {
        this.h.execute(new Runnable() { // from class: com.tencent.open.b.g.4
            /*  JADX ERROR: IF instruction can be used only in fallback mode
                jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
                	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:644)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:513)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:271)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:241)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:90)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:174)
                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:167)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:122)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:79)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:301)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:266)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:259)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:369)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    r8 = this;
                    r1 = 0
                    com.tencent.open.b.g r0 = com.tencent.open.b.g.this     // Catch: Exception -> 0x00b2
                    android.os.Bundle r4 = r0.c()     // Catch: Exception -> 0x00b2
                    if (r4 != 0) goto L_0x000a
                L_0x0009:
                    return
                L_0x000a:
                    android.content.Context r0 = com.tencent.open.utils.Global.getContext()     // Catch: Exception -> 0x00b2
                    r2 = 0
                    com.tencent.open.utils.OpenConfig r0 = com.tencent.open.utils.OpenConfig.getInstance(r0, r2)     // Catch: Exception -> 0x00b2
                    java.lang.String r2 = "Common_HttpRetryCount"
                    int r0 = r0.getInt(r2)     // Catch: Exception -> 0x00b2
                    if (r0 != 0) goto L_0x00bc
                    r0 = 3
                    r3 = r0
                L_0x001d:
                    java.lang.String r0 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Exception -> 0x00b2
                    r2.<init>()     // Catch: Exception -> 0x00b2
                    java.lang.String r5 = "-->doReportCgi, retryCount: "
                    java.lang.StringBuilder r2 = r2.append(r5)     // Catch: Exception -> 0x00b2
                    java.lang.StringBuilder r2 = r2.append(r3)     // Catch: Exception -> 0x00b2
                    java.lang.String r2 = r2.toString()     // Catch: Exception -> 0x00b2
                    com.tencent.open.a.f.b(r0, r2)     // Catch: Exception -> 0x00b2
                    r0 = r1
                L_0x0036:
                    int r0 = r0 + 1
                    android.content.Context r2 = com.tencent.open.utils.Global.getContext()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r5 = 0
                    java.lang.String r6 = "http://wspeed.qq.com/w.cgi"
                    org.apache.http.client.HttpClient r2 = com.tencent.open.utils.HttpUtils.getHttpClient(r2, r5, r6)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    org.apache.http.client.methods.HttpPost r5 = new org.apache.http.client.methods.HttpPost     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r6 = "http://wspeed.qq.com/w.cgi"
                    r5.<init>(r6)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r6 = "Accept-Encoding"
                    java.lang.String r7 = "gzip"
                    r5.addHeader(r6, r7)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r6 = "Content-Type"
                    java.lang.String r7 = "application/x-www-form-urlencoded"
                    r5.setHeader(r6, r7)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r6 = com.tencent.open.utils.HttpUtils.encodeUrl(r4)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    byte[] r6 = com.tencent.open.utils.Util.getBytesUTF8(r6)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    org.apache.http.entity.ByteArrayEntity r7 = new org.apache.http.entity.ByteArrayEntity     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r7.<init>(r6)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r5.setEntity(r7)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    org.apache.http.HttpResponse r2 = r2.execute(r5)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    org.apache.http.StatusLine r2 = r2.getStatusLine()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    int r2 = r2.getStatusCode()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r5 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r6.<init>()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r7 = "-->doReportCgi, statusCode: "
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.StringBuilder r6 = r6.append(r2)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r6 = r6.toString()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    com.tencent.open.a.f.b(r5, r6)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r5 = 200(0xc8, float:2.8E-43)
                    if (r2 != r5) goto L_0x009a
                    com.tencent.open.b.f r2 = com.tencent.open.b.f.a()     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    java.lang.String r5 = "report_cgi"
                    r2.b(r5)     // Catch: ConnectTimeoutException -> 0x00bf, SocketTimeoutException -> 0x00ca, Exception -> 0x00d3
                    r1 = 1
                L_0x009a:
                    if (r1 != 0) goto L_0x00a9
                    com.tencent.open.b.f r0 = com.tencent.open.b.f.a()     // Catch: Exception -> 0x00b2
                    java.lang.String r1 = "report_cgi"
                    com.tencent.open.b.g r2 = com.tencent.open.b.g.this     // Catch: Exception -> 0x00b2
                    java.util.List<java.io.Serializable> r2 = r2.c     // Catch: Exception -> 0x00b2
                    r0.a(r1, r2)     // Catch: Exception -> 0x00b2
                L_0x00a9:
                    com.tencent.open.b.g r0 = com.tencent.open.b.g.this     // Catch: Exception -> 0x00b2
                    java.util.List<java.io.Serializable> r0 = r0.c     // Catch: Exception -> 0x00b2
                    r0.clear()     // Catch: Exception -> 0x00b2
                    goto L_0x0009
                L_0x00b2:
                    r0 = move-exception
                    java.lang.String r1 = "openSDK_LOG.ReportManager"
                    java.lang.String r2 = "-->doReportCgi, doupload exception out."
                    com.tencent.open.a.f.b(r1, r2, r0)
                    goto L_0x0009
                L_0x00bc:
                    r3 = r0
                    goto L_0x001d
                L_0x00bf:
                    r2 = move-exception
                    java.lang.String r5 = "openSDK_LOG.ReportManager"
                    java.lang.String r6 = "-->doReportCgi, doupload exception"
                    com.tencent.open.a.f.b(r5, r6, r2)     // Catch: Exception -> 0x00b2
                L_0x00c7:
                    if (r0 < r3) goto L_0x0036
                    goto L_0x009a
                L_0x00ca:
                    r2 = move-exception
                    java.lang.String r5 = "openSDK_LOG.ReportManager"
                    java.lang.String r6 = "-->doReportCgi, doupload exception"
                    com.tencent.open.a.f.b(r5, r6, r2)     // Catch: Exception -> 0x00b2
                    goto L_0x00c7
                L_0x00d3:
                    r0 = move-exception
                    java.lang.String r2 = "openSDK_LOG.ReportManager"
                    java.lang.String r3 = "-->doReportCgi, doupload exception"
                    com.tencent.open.a.f.b(r2, r3, r0)     // Catch: Exception -> 0x00b2
                    goto L_0x009a
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass4.run():void");
            }
        });
    }

    protected boolean a(String str, String str2) {
        int i;
        boolean z = true;
        z = false;
        f.b("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("report_cgi")) {
                try {
                    int a2 = a(Integer.parseInt(str2));
                    if (this.b.nextInt(100) >= a2) {
                        z = false;
                    }
                    i = a2;
                } catch (Exception e) {
                }
            } else if (str.equals("report_via")) {
                int a3 = e.a(str2);
                if (this.b.nextInt(100) < a3) {
                    z = true;
                    i = a3;
                } else {
                    i = a3;
                }
            } else {
                i = 100;
            }
            f.b("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i);
        }
        return z;
    }

    protected boolean a(String str, int i) {
        int i2 = 5;
        if (str.equals("report_cgi")) {
            int i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportMaxcount");
            if (i3 != 0) {
                i2 = i3;
            }
        } else if (str.equals("report_via")) {
            int i4 = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportBatchCount");
            if (i4 != 0) {
                i2 = i4;
            }
        } else {
            i2 = 0;
        }
        f.b("openSDK_LOG.ReportManager", "-->availableCount, report: " + str + " | dataSize: " + i + " | maxcount: " + i2);
        return i >= i2;
    }

    protected int a(int i) {
        if (i == 0) {
            int i2 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencySuccess");
            if (i2 == 0) {
                return 10;
            }
            return i2;
        }
        int i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencyFailed");
        if (i3 == 0) {
            return 100;
        }
        return i3;
    }

    protected Bundle c() {
        if (this.c.size() == 0) {
            return null;
        }
        b bVar = (b) this.c.get(0);
        if (bVar == null) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = bVar.a.get("appid");
        List<Serializable> a2 = f.a().a("report_cgi");
        if (a2 != null) {
            this.c.addAll(a2);
        }
        f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.c.size());
        if (this.c.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString("appid", str);
            bundle.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle.putString(d.n, Build.DEVICE);
            bundle.putString("qua", Constants.SDK_QUA);
            bundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                b bVar2 = (b) this.c.get(i);
                bundle.putString(i + "_1", bVar2.a.get("apn"));
                bundle.putString(i + "_2", bVar2.a.get("frequency"));
                bundle.putString(i + "_3", bVar2.a.get("commandid"));
                bundle.putString(i + "_4", bVar2.a.get("resultCode"));
                bundle.putString(i + "_5", bVar2.a.get("timeCost"));
                bundle.putString(i + "_6", bVar2.a.get("reqSize"));
                bundle.putString(i + "_7", bVar2.a.get("rspSize"));
                bundle.putString(i + "_8", bVar2.a.get("detail"));
                bundle.putString(i + "_9", bVar2.a.get("uin"));
                bundle.putString(i + "_10", c.e(Global.getContext()) + a.b + bVar2.a.get("deviceInfo"));
            }
            f.a("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + bundle.toString());
            return bundle;
        } catch (Exception e) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Bundle d() {
        List<Serializable> a2 = f.a().a("report_via");
        if (a2 != null) {
            this.d.addAll(a2);
        }
        f.b("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<Serializable> it = this.d.iterator();
        while (it.hasNext()) {
            JSONObject jSONObject = new JSONObject();
            b bVar = (b) it.next();
            for (String str : bVar.a.keySet()) {
                try {
                    String str2 = bVar.a.get(str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                } catch (JSONException e) {
                    f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        f.a("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            bundle.putString("data", jSONObject2.toString());
            return bundle;
        } catch (JSONException e2) {
            f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void e() {
        this.g.execute(new Runnable() { // from class: com.tencent.open.b.g.5
            /* JADX WARN: Removed duplicated region for block: B:21:0x0077 A[Catch: Exception -> 0x00a3, TryCatch #10 {Exception -> 0x00a3, blocks: (B:2:0x0000, B:5:0x000b, B:19:0x006a, B:21:0x0077, B:22:0x0080, B:29:0x00b2, B:31:0x00c1, B:35:0x00d3, B:42:0x0109, B:45:0x0118, B:8:0x0038, B:9:0x004a, B:11:0x0052, B:14:0x005c, B:15:0x005e), top: B:57:0x0000, inners: #10, #9 }] */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0118 A[Catch: Exception -> 0x00a3, TRY_LEAVE, TryCatch #10 {Exception -> 0x00a3, blocks: (B:2:0x0000, B:5:0x000b, B:19:0x006a, B:21:0x0077, B:22:0x0080, B:29:0x00b2, B:31:0x00c1, B:35:0x00d3, B:42:0x0109, B:45:0x0118, B:8:0x0038, B:9:0x004a, B:11:0x0052, B:14:0x005c, B:15:0x005e), top: B:57:0x0000, inners: #10, #9 }] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 304
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass5.run():void");
            }
        });
    }

    public void a(final String str, final String str2, final Bundle bundle, final boolean z) {
        ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.open.b.g.6
            /* JADX WARN: Removed duplicated region for block: B:23:0x00ac A[Catch: Exception -> 0x00b5, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x00b5, blocks: (B:3:0x0002, B:5:0x0006, B:7:0x000e, B:10:0x0016, B:12:0x0043, B:13:0x0048, B:15:0x0056, B:16:0x006a, B:23:0x00ac, B:27:0x00c2, B:29:0x00d0, B:30:0x00e5, B:34:0x00f8, B:38:0x0103, B:40:0x010c, B:41:0x0114), top: B:49:0x0002 }] */
            /* JADX WARN: Removed duplicated region for block: B:41:0x0114 A[Catch: Exception -> 0x00b5, TRY_LEAVE, TryCatch #6 {Exception -> 0x00b5, blocks: (B:3:0x0002, B:5:0x0006, B:7:0x000e, B:10:0x0016, B:12:0x0043, B:13:0x0048, B:15:0x0056, B:16:0x006a, B:23:0x00ac, B:27:0x00c2, B:29:0x00d0, B:30:0x00e5, B:34:0x00f8, B:38:0x0103, B:40:0x010c, B:41:0x0114), top: B:49:0x0002 }] */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0101 A[SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 297
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass6.run():void");
            }
        });
    }
}
