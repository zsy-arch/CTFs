package com.cdlinglu.common;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.jpush.android.api.JPushInterface;
import com.amap.api.maps.model.LatLng;
import com.cdlinglu.utils.IDUtil;
import com.cdlinglu.utils.Photo.util.Res;
import com.danikula.videocache.HttpProxyCacheServer;
import com.em.DemoHelper;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hyphenate.chat.EMOptions;
import com.lidroid.xutils.util.LogUtils;
import com.litepal.LitePalApplication;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyLocation;
import com.yolanda.nohttp.NoHttp;

/* loaded from: classes.dex */
public class MyApplication extends LitePalApplication {
    private static LatLng currentLatlnt;
    public static int heightCm;
    private static MyApplication instance;
    private static String lastUser;
    public static int unreadMsgCount;
    private static MyLocation userLocation;
    public static int widthCm;
    public static IWXAPI wxApi;
    private NotificationManager notifyManager;
    private HttpProxyCacheServer proxy;
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.cdlinglu.common.MyApplication.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MyLog.i("MyApplication::接收到消息");
        }
    };
    public static int wxErrCode = 1;
    private static String currentMapClickUserId = "";

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        if (app.proxy != null) {
            return app.proxy;
        }
        HttpProxyCacheServer newProxy = app.newProxy();
        app.proxy = newProxy;
        return newProxy;
    }

    public static MyLocation getMyLocation() {
        return userLocation;
    }

    public static void setMyLocation(MyLocation myLocation) {
        userLocation = myLocation;
        setCurrentLatlnt(new LatLng(Double.parseDouble(myLocation.getLatitude()), Double.parseDouble(myLocation.getLongitude())));
    }

    public static String getCurrentMapClickUserId() {
        return currentMapClickUserId;
    }

    public static void setCurrentMapClickUserId(String currentMapClickUserId2) {
        currentMapClickUserId = currentMapClickUserId2;
    }

    public static String isLoginChange() {
        return lastUser;
    }

    public static void setIsLoginChange(String lastUser2) {
        lastUser = lastUser2;
    }

    public static int getWidthCm() {
        return widthCm;
    }

    public static void setWidthCm(int widthCm2) {
        widthCm = widthCm2;
    }

    public static int getHeightCm() {
        return heightCm;
    }

    public static void setHeightCm(int heightCm2) {
        heightCm = heightCm2;
    }

    public static LatLng getCurrentLatlnt() {
        if (currentLatlnt == null) {
            currentLatlnt = new LatLng(23.117055306224895d, 113.2759952545166d);
        }
        return currentLatlnt;
    }

    public static void setCurrentLatlnt(LatLng currentLatlnt1) {
        currentLatlnt = currentLatlnt1;
    }

    @Override // com.hy.frame.common.BaseApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(false);
        instance = this;
        String processAppName = getAppName(Process.myPid());
        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            MyLog.e("enter the service process!");
            return;
        }
        NoHttp.init(this);
        Res.init(getApplicationContext());
        wxApi = WXAPIFactory.createWXAPI(this, IDUtil.WX_APPID);
        wxApi.registerApp(IDUtil.WX_APPID);
        String processName = HyUtil.getProcessName(this, Process.myPid());
        if (processName != null) {
            MyLog.e("process:" + processName);
            if (TextUtils.equals(processName, getPackageName())) {
                initAppForMainProcess();
            } else if (processName.contains(":xxxx")) {
                initOtherForMainProcess();
            }
        }
        MultiDex.install(this);
        DemoHelper.getInstance().init(this);
    }

    private String getAppName(int pID) {
        getPackageManager();
        for (ActivityManager.RunningAppProcessInfo info : ((ActivityManager) getSystemService("activity")).getRunningAppProcesses()) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (info.pid == pID) {
                return info.processName;
            }
            continue;
        }
        return null;
    }

    private void initAppForMainProcess() {
        boolean debug = getResources().getBoolean(R.bool.LOG_DEBUG);
        MyLog.isLoggable = debug;
        LogUtils.allowD = debug;
        LogUtils.allowE = debug;
        LogUtils.allowI = debug;
        LogUtils.allowV = debug;
        LogUtils.allowW = debug;
        LogUtils.allowWtf = debug;
        initPush();
    }

    private void initOtherForMainProcess() {
    }

    private void initPush() {
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void showNotify(Context context, Intent intent, String title, String content) {
        intent.addFlags(268435456);
        Notification notification = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ico_logo).setTicker(title + content).setContentTitle(title).setContentText(content).setContentIntent(PendingIntent.getActivity(context, 0, intent, 134217728)).build();
        notification.flags |= 16;
        notification.flags |= 1;
        notification.defaults |= -1;
        if (this.notifyManager == null) {
            this.notifyManager = (NotificationManager) context.getSystemService("notification");
        }
        this.notifyManager.cancelAll();
        this.notifyManager.notify(0, notification);
    }
}
