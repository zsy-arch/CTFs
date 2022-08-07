package com.autonavi.ae.route;

import android.content.Context;
import com.autonavi.ae.route.model.RouteConfig;
import com.autonavi.ae.route.model.RoutePoi;
import com.autonavi.ae.route.model.TmcBarItem;
import com.autonavi.ae.route.model.TmcRoutePath;
import com.autonavi.ae.route.observer.HttpInterface;
import com.autonavi.ae.route.observer.PathRequestObserver;
import com.autonavi.ae.route.observer.RouteObserver;
import com.autonavi.ae.route.route.CalcRouteResult;

/* loaded from: classes.dex */
public class RouteService {
    private Context mContext;
    private HttpInterface mHttpProcess;
    private PathRequestObserver mPathRequestObserver;
    private long mPtr;
    private RouteObserver mRouteObserver;

    public static native CalcRouteResult decodeRouteData(int i, int i2, byte[] bArr);

    public static native TmcBarItem[] decodeRouteTmcBar(byte[] bArr, TmcRoutePath tmcRoutePath);

    public static native String getEngineVersion();

    public static native String getRouteVersion();

    private final native void init(RouteConfig routeConfig);

    public native void abortRoutePlan();

    public native int control(String str, String str2);

    public final native void destroy();

    public native void processHttpData(int i, int i2, byte[] bArr);

    public native void processHttpError(int i, int i2);

    public native int requestRoute(int i, int i2, RoutePoi[] routePoiArr, RoutePoi[] routePoiArr2, RoutePoi[] routePoiArr3, float f, float f2);

    public native int reroute(long j, int i, int i2, int i3);

    public RouteService(RouteConfig routeConfig, Context context) {
        init(routeConfig);
        this.mContext = context;
    }

    public void setRouteObserver(RouteObserver routeObserver) {
        this.mRouteObserver = routeObserver;
    }

    public void setPathRequestObserver(PathRequestObserver pathRequestObserver) {
        this.mPathRequestObserver = pathRequestObserver;
    }

    public void registerHttpProcesser(HttpInterface httpInterface) {
        this.mHttpProcess = httpInterface;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] readAssetsFile(int r7, int r8) {
        /*
            r6 = this;
            r1 = 0
            r0 = 1
            if (r8 != r0) goto L_0x00a4
            java.lang.String r0 = "navi/road_config.bin"
        L_0x0006:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x000d
        L_0x000c:
            return r1
        L_0x000d:
            android.content.Context r2 = r6.mContext     // Catch: Exception -> 0x009d, OutOfMemoryError -> 0x0060, all -> 0x007b
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: Exception -> 0x009d, OutOfMemoryError -> 0x0060, all -> 0x007b
            java.io.InputStream r3 = r2.open(r0)     // Catch: Exception -> 0x009d, OutOfMemoryError -> 0x0060, all -> 0x007b
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Exception -> 0x00a1, OutOfMemoryError -> 0x0098, all -> 0x0093
            r2.<init>()     // Catch: Exception -> 0x00a1, OutOfMemoryError -> 0x0098, all -> 0x0093
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: Exception -> 0x002e, OutOfMemoryError -> 0x009b, all -> 0x0096
        L_0x0020:
            r4 = 0
            r5 = 1024(0x400, float:1.435E-42)
            int r4 = r3.read(r0, r4, r5)     // Catch: Exception -> 0x002e, OutOfMemoryError -> 0x009b, all -> 0x0096
            if (r4 <= 0) goto L_0x0042
            r5 = 0
            r2.write(r0, r5, r4)     // Catch: Exception -> 0x002e, OutOfMemoryError -> 0x009b, all -> 0x0096
            goto L_0x0020
        L_0x002e:
            r0 = move-exception
        L_0x002f:
            r0.printStackTrace()     // Catch: all -> 0x0096
            if (r3 == 0) goto L_0x0037
            r3.close()     // Catch: IOException -> 0x005b
        L_0x0037:
            if (r2 == 0) goto L_0x000c
            r2.close()     // Catch: IOException -> 0x003d
            goto L_0x000c
        L_0x003d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0042:
            byte[] r1 = r2.toByteArray()     // Catch: Exception -> 0x002e, OutOfMemoryError -> 0x009b, all -> 0x0096
            if (r3 == 0) goto L_0x004b
            r3.close()     // Catch: IOException -> 0x0056
        L_0x004b:
            if (r2 == 0) goto L_0x000c
            r2.close()     // Catch: IOException -> 0x0051
            goto L_0x000c
        L_0x0051:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0056:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004b
        L_0x005b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x0060:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0063:
            r0.printStackTrace()     // Catch: all -> 0x0096
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch: IOException -> 0x0076
        L_0x006b:
            if (r2 == 0) goto L_0x000c
            r2.close()     // Catch: IOException -> 0x0071
            goto L_0x000c
        L_0x0071:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0076:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006b
        L_0x007b:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x007e:
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch: IOException -> 0x0089
        L_0x0083:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch: IOException -> 0x008e
        L_0x0088:
            throw r0
        L_0x0089:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0083
        L_0x008e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0088
        L_0x0093:
            r0 = move-exception
            r2 = r1
            goto L_0x007e
        L_0x0096:
            r0 = move-exception
            goto L_0x007e
        L_0x0098:
            r0 = move-exception
            r2 = r1
            goto L_0x0063
        L_0x009b:
            r0 = move-exception
            goto L_0x0063
        L_0x009d:
            r0 = move-exception
            r2 = r1
            r3 = r1
            goto L_0x002f
        L_0x00a1:
            r0 = move-exception
            r2 = r1
            goto L_0x002f
        L_0x00a4:
            r0 = r1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.route.RouteService.readAssetsFile(int, int):byte[]");
    }
}
