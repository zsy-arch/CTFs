package com.hyphenate.chat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.analytics.EMActiveCollector;
import com.hyphenate.analytics.EMTimeTag;
import com.hyphenate.chat.a.a;
import com.hyphenate.chat.a.b;
import com.hyphenate.chat.a.c;
import com.hyphenate.chat.adapter.EMAChatClient;
import com.hyphenate.chat.adapter.EMAConnectionListener;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.chat.adapter.EMANetCallback;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.CryptoUtils;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;
import com.hyphenate.util.PathUtil;
import com.hyphenate.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class EMClient {
    private static final int JOB_ID = 11;
    private static final int JOB_INTERVAL = 60000;
    public static final String TAG = "EMClient";
    public static final String VERSION = "3.2.3";
    private static EMClient instance = null;
    static boolean libraryLoaded = false;
    private AppStateListener appStateListener;
    private EMCallManager callManager;
    private EMChatManager chatManager;
    private EMChatRoomManager chatroomManager;
    private EMContactManager contactManager;
    private EMAChatClient emaObject;
    private EMGroupManager groupManager;
    private b mChatConfigPrivate;
    private Context mContext;
    private EMPushManager pushManager;
    boolean stopService;
    private PowerManager.WakeLock wakeLock;
    private ExecutorService executor = null;
    private ExecutorService mainQueue = Executors.newSingleThreadExecutor();
    private EMEncryptProvider encryptProvider = null;
    private CryptoUtils cryptoUtils = new CryptoUtils();
    private boolean sdkInited = false;
    private List<EMConnectionListener> connectionListeners = Collections.synchronizedList(new ArrayList());
    private MyConnectionListener connectionListener = new MyConnectionListener();
    private EMSmartHeartBeat smartHeartbeat = null;
    private BroadcastReceiver connectivityBroadcastReceiver = new BroadcastReceiver() { // from class: com.hyphenate.chat.EMClient.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                EMLog.d(EMClient.TAG, "skip no connectivity action");
                return;
            }
            EMLog.d(EMClient.TAG, "connectivity receiver onReceiver");
            EMClient.this.execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.9.1
                @Override // java.lang.Runnable
                public void run() {
                    EMClient.this.onNetworkChanged();
                }
            });
        }
    };
    private int activitySize = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface AppStateListener {
        void onBackground();

        void onForeground();
    }

    /* loaded from: classes2.dex */
    class MyConnectionListener extends EMAConnectionListener {
        MyConnectionListener() {
        }

        @Override // com.hyphenate.chat.adapter.EMAConnectionListener, com.hyphenate.chat.adapter.EMAConnectionListenerInterface
        public void onConnected() {
            EMClient.this.execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.MyConnectionListener.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (EMClient.this.connectionListeners) {
                        for (EMConnectionListener eMConnectionListener : EMClient.this.connectionListeners) {
                            eMConnectionListener.onConnected();
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.chat.adapter.EMAConnectionListener, com.hyphenate.chat.adapter.EMAConnectionListenerInterface
        public void onDisconnected(final int i) {
            EMClient.this.execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.MyConnectionListener.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (EMClient.this.connectionListeners) {
                        for (EMConnectionListener eMConnectionListener : EMClient.this.connectionListeners) {
                            eMConnectionListener.onDisconnected(i);
                        }
                    }
                }
            });
        }
    }

    private EMClient() {
    }

    static /* synthetic */ int access$408(EMClient eMClient) {
        int i = eMClient.activitySize;
        eMClient.activitySize = i + 1;
        return i;
    }

    static /* synthetic */ int access$410(EMClient eMClient) {
        int i = eMClient.activitySize;
        eMClient.activitySize = i - 1;
        return i;
    }

    public static EMClient getInstance() {
        if (instance == null) {
            loadLibrary();
            instance = new EMClient();
        }
        return instance;
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            throw new HyphenateException(eMAError);
        }
    }

    private void initManagers() {
        EMHttpClient.getInstance().onInit(this.mChatConfigPrivate);
        chatManager();
        contactManager();
        groupManager();
        chatroomManager();
        setNatvieNetworkCallback();
    }

    private static void loadLibrary() {
        if (!libraryLoaded) {
            try {
                System.loadLibrary("hyphenate_av_recorder");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                System.loadLibrary("hyphenate_av");
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            System.loadLibrary("hyphenate");
            libraryLoaded = true;
        }
    }

    @TargetApi(14)
    private void registerActivityLifecycleCallbacks() {
        if (Utils.isSdk14()) {
            ((Application) this.mContext).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.hyphenate.chat.EMClient.10
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    EMClient.access$408(EMClient.this);
                    if (EMClient.this.activitySize == 1 && EMClient.this.appStateListener != null) {
                        EMClient.this.appStateListener.onForeground();
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    EMClient.access$410(EMClient.this);
                    if (EMClient.this.activitySize == 0 && EMClient.this.appStateListener != null) {
                        EMClient.this.appStateListener.onBackground();
                    }
                }
            });
        }
    }

    private void sendEmptyToken() {
        for (int i = 0; i < 3 && !EMPushHelper.getInstance().sendTokenToServer(""); i++) {
        }
    }

    void _login(final String str, final String str2, final EMCallBack eMCallBack, final boolean z) {
        if (getChatConfigPrivate() == null) {
            eMCallBack.onError(1, "");
            return;
        }
        EMLog.e(TAG, "emchat manager login in process:" + Process.myPid());
        execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.6
            @Override // java.lang.Runnable
            public void run() {
                if (str == null) {
                    eMCallBack.onError(101, "Invalid user name");
                    return;
                }
                EMAError eMAError = new EMAError();
                if (z) {
                    EMClient.this.onNewLogin();
                    EMClient.this.emaObject.autoLogin(str, str2, eMAError);
                    if (eMAError.errCode() == 0) {
                        EMClient.this.checkPushAvailable();
                        eMCallBack.onSuccess();
                    } else {
                        eMCallBack.onError(eMAError.errCode(), eMAError.errMsg());
                    }
                } else {
                    EMClient.this.emaObject.login(str, str2, eMAError);
                    if (eMAError.errCode() == 0) {
                        EMSessionManager.getInstance().setLastLoginUser(str);
                        EMSessionManager.getInstance().setLastLoginPwd(str2);
                        EMClient.this.onNewLogin();
                        EMClient.this.checkPushAvailable();
                        eMCallBack.onSuccess();
                    } else {
                        EMClient.this.doStopService();
                        eMCallBack.onError(eMAError.errCode(), eMAError.errMsg());
                    }
                }
                if (eMAError.errCode() == 202) {
                    EMSessionManager.getInstance().clearLastLoginPwd();
                }
            }
        });
    }

    public void addConnectionListener(final EMConnectionListener eMConnectionListener) {
        if (eMConnectionListener != null) {
            synchronized (this.connectionListeners) {
                if (!this.connectionListeners.contains(eMConnectionListener)) {
                    this.connectionListeners.add(eMConnectionListener);
                }
            }
            execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.5
                @Override // java.lang.Runnable
                public void run() {
                    if (EMClient.this.isConnected()) {
                        eMConnectionListener.onConnected();
                    } else {
                        eMConnectionListener.onDisconnected(2);
                    }
                }
            });
        }
    }

    void autoLogin(String str, String str2, EMCallBack eMCallBack) {
        _login(str, str2, eMCallBack, true);
    }

    public EMCallManager callManager() {
        if (this.callManager == null) {
            this.callManager = new EMCallManager(this, this.emaObject.getCallManager());
        }
        return this.callManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public void cancelJob() {
        if (Utils.isSdk21()) {
            try {
                ((JobScheduler) this.mContext.getSystemService("jobscheduler")).cancel(11);
                this.mContext.stopService(new Intent(this.mContext, EMJobService.class));
            } catch (Exception e) {
            }
        }
    }

    public void changeAppkey(String str) throws HyphenateException {
        EMAError changeAppkey = this.emaObject.changeAppkey(str);
        if (changeAppkey.errCode() == 0) {
            getOptions().updatePath(str);
        }
        handleError(changeAppkey);
    }

    public EMChatManager chatManager() {
        if (this.chatManager == null) {
            this.chatManager = new EMChatManager(this, this.emaObject.getChatManager());
        }
        return this.chatManager;
    }

    public EMChatRoomManager chatroomManager() {
        if (this.chatroomManager == null) {
            this.chatroomManager = new EMChatRoomManager(this, this.emaObject.getChatRoomManager());
        }
        return this.chatroomManager;
    }

    void checkPushAvailable() {
        if (EMPushHelper.getInstance().checkAvailablePushService()) {
            EMPushHelper.getInstance().sendDeviceTokenToServer();
        } else {
            sendEmptyToken();
        }
    }

    public EMContactManager contactManager() {
        if (this.contactManager == null) {
            this.contactManager = new EMContactManager(this, this.emaObject.getContactManager());
        }
        return this.contactManager;
    }

    public void createAccount(String str, String str2) throws HyphenateException {
        String lowerCase = str.toLowerCase();
        if (!Pattern.compile("^[a-zA-Z0-9_-]+$").matcher(lowerCase).find()) {
            throw new HyphenateException(205, "illegal user name");
        }
        handleError(this.emaObject.createAccount(lowerCase, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnect() {
        this.emaObject.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doStartService() {
        if (this.mContext != null) {
            EMLog.d(TAG, "do start service: context:" + this.mContext);
            this.stopService = false;
            try {
                this.mContext.startService(new Intent(this.mContext, EMChatService.class));
            } catch (Exception e) {
                EMLog.d(TAG, "exception in start service, e: " + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doStopService() {
        try {
            if (this.mContext == null) {
                EMLog.w(TAG, "applicationContext is null, the server is not started before");
            } else {
                EMLog.d(TAG, "do stop service");
                this.stopService = true;
                this.mContext.stopService(new Intent(this.mContext, EMChatService.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void executeOnMainQueue(Runnable runnable) {
        this.mainQueue.submit(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forceReconnect() {
        disconnect();
        reconnect();
    }

    public String getAccessToken() {
        return getChatConfigPrivate().n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b getChatConfigPrivate() {
        return this.mChatConfigPrivate;
    }

    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CryptoUtils getCryptoUtils() {
        return this.cryptoUtils;
    }

    public String getCurrentUser() {
        return (EMSessionManager.getInstance().currentUser == null || EMSessionManager.getInstance().currentUser.username == null || EMSessionManager.getInstance().currentUser.username.equals("")) ? EMSessionManager.getInstance().getLastLoginUser() : EMSessionManager.getInstance().currentUser.username;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMEncryptProvider getEncryptProvider() {
        if (this.encryptProvider == null) {
            EMLog.d(TAG, "encrypt provider is not set, create default");
            this.encryptProvider = new EMEncryptProvider() { // from class: com.hyphenate.chat.EMClient.8
                @Override // com.hyphenate.chat.EMEncryptProvider
                public byte[] decrypt(byte[] bArr, String str) {
                    try {
                        return EMClient.this.cryptoUtils.decrypt(bArr);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return bArr;
                    }
                }

                @Override // com.hyphenate.chat.EMEncryptProvider
                public byte[] encrypt(byte[] bArr, String str) {
                    try {
                        return EMClient.this.cryptoUtils.encrypt(bArr);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return bArr;
                    }
                }
            };
        }
        return this.encryptProvider;
    }

    public EMOptions getOptions() {
        return this.mChatConfigPrivate.b();
    }

    public List<EMContact> getRobotsFromServer() throws HyphenateException {
        return EMExtraService.getInstance().getRobotsFromServer();
    }

    public EMGroupManager groupManager() {
        if (this.groupManager == null) {
            this.groupManager = new EMGroupManager(this, this.emaObject.getGroupManager());
        }
        return this.groupManager;
    }

    public void init(Context context, EMOptions eMOptions) {
        if (!this.sdkInited) {
            EMTimeTag eMTimeTag = new EMTimeTag();
            eMTimeTag.start();
            this.mContext = context.getApplicationContext();
            registerActivityLifecycleCallbacks();
            this.mChatConfigPrivate = new b();
            this.mChatConfigPrivate.a(context, eMOptions);
            eMOptions.setConfig(this.mChatConfigPrivate);
            this.emaObject = EMAChatClient.create(this.mChatConfigPrivate.a);
            this.emaObject.addConnectionListener(this.connectionListener);
            this.executor = Executors.newCachedThreadPool();
            this.cryptoUtils.init(1);
            initManagers();
            EMActiveCollector.sendActivePacket(this.mContext, getChatConfigPrivate());
            final String lastLoginUser = EMSessionManager.getInstance().getLastLoginUser();
            EMLog.e(TAG, "is autoLogin : " + eMOptions.getAutoLogin());
            EMLog.e(TAG, "lastLoginUser : " + lastLoginUser);
            EMLog.e(TAG, "hyphenate SDK is initialized with version : " + getChatConfigPrivate().e());
            this.wakeLock = ((PowerManager) this.mContext.getSystemService("power")).newWakeLock(1, "emclient");
            this.sdkInited = true;
            if (eMOptions.getAutoLogin() && isLoggedInBefore()) {
                final String lastLoginPwd = EMSessionManager.getInstance().getLastLoginPwd();
                EMSessionManager.getInstance().currentUser = new EMContact(lastLoginUser);
                final EMCallBack eMCallBack = new EMCallBack() { // from class: com.hyphenate.chat.EMClient.1
                    @Override // com.hyphenate.EMCallBack
                    public void onError(int i, String str) {
                        Log.d(EMClient.TAG, "hyphenate login onError");
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onProgress(int i, String str) {
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onSuccess() {
                        Log.d(EMClient.TAG, "hyphenate login onSuccess");
                        EMSessionManager.getInstance().currentUser = new EMContact(lastLoginUser);
                    }
                };
                execute(new Runnable() { // from class: com.hyphenate.chat.EMClient.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EMClient.this.getChatConfigPrivate().b(lastLoginUser);
                        EMClient.this.groupManager().loadAllGroups();
                        EMClient.this.chatManager().loadAllConversationsFromDB();
                        EMClient.this.autoLogin(lastLoginUser, lastLoginPwd, eMCallBack);
                    }
                });
            }
            eMTimeTag.stop();
            EMLog.e(TAG, "[Collector][sdk init]init time is : " + eMTimeTag.timeStr());
        }
    }

    public boolean isConnected() {
        return this.emaObject.isConnected();
    }

    public boolean isLoggedInBefore() {
        EMSessionManager instance2 = EMSessionManager.getInstance();
        String lastLoginUser = instance2.getLastLoginUser();
        String lastLoginPwd = instance2.getLastLoginPwd();
        return lastLoginUser != null && lastLoginPwd != null && !lastLoginUser.equals("") && !lastLoginPwd.equals("");
    }

    public void login(String str, String str2, EMCallBack eMCallBack) {
        if (TextUtils.isEmpty(getChatConfigPrivate().l())) {
            throw new RuntimeException("please setup your appkey either in AndroidManifest.xml or through the EMOptions");
        } else if (eMCallBack == null) {
            throw new IllegalArgumentException("callback is null!");
        } else if (str == null || str2 == null || str.equals("") || str2.equals("")) {
            throw new IllegalArgumentException("username or password is null or empty!");
        } else {
            _login(str.toLowerCase(), str2, eMCallBack, false);
        }
    }

    void loginWithToken(String str, String str2, EMCallBack eMCallBack) {
    }

    public int logout(boolean z) {
        try {
            EMPushHelper.getInstance().onDestroy(z);
            logout();
            return 0;
        } catch (HyphenateException e) {
            e.printStackTrace();
            return 212;
        }
    }

    void logout() {
        EMLog.d(TAG, " SDK Logout");
        try {
            if (this.connectivityBroadcastReceiver != null) {
                this.mContext.unregisterReceiver(this.connectivityBroadcastReceiver);
            }
        } catch (Exception e) {
        }
        EMSessionManager.getInstance().clearLastLoginUser();
        EMSessionManager.getInstance().clearLastLoginPwd();
        if (this.smartHeartbeat != null) {
            this.smartHeartbeat.stop();
        }
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        if (this.emaObject != null) {
            this.emaObject.logout();
        }
        if (this.chatManager != null) {
            this.chatManager.onLogout();
        }
        if (this.groupManager != null) {
            this.groupManager.onLogout();
        }
        if (this.contactManager != null) {
            this.contactManager.onLogout();
        }
        if (this.chatroomManager != null) {
            this.chatroomManager.onLogout();
        }
        try {
            a.a().f();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (b.a()) {
            com.hyphenate.a.a.c();
        }
        if (this.mContext != null) {
            EMMonitor.getInstance().getMonitorDB().b(this.mContext.getPackageName());
        }
        cancelJob();
        doStopService();
    }

    void logout(final EMCallBack eMCallBack) {
        Thread thread = new Thread() { // from class: com.hyphenate.chat.EMClient.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (eMCallBack != null) {
                    eMCallBack.onProgress(0, null);
                }
                EMClient.this.logout();
                if (eMCallBack != null) {
                    eMCallBack.onSuccess();
                }
            }
        };
        thread.setPriority(9);
        thread.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hyphenate.chat.EMClient$3] */
    public void logout(final boolean z, final EMCallBack eMCallBack) {
        new Thread() { // from class: com.hyphenate.chat.EMClient.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int logout = EMClient.this.logout(z);
                if (logout != 0) {
                    if (eMCallBack != null) {
                        eMCallBack.onError(logout, "faild to unbind device token");
                    }
                } else if (eMCallBack != null) {
                    eMCallBack.onSuccess();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNetworkChanged() {
        try {
            if (NetUtils.isWifiConnected(this.mContext)) {
                EMLog.d(TAG, "has wifi connection");
                this.emaObject.onNetworkChanged(EMAChatClient.EMANetwork.NETWORK_WIFI);
            } else if (NetUtils.isMobileConnected(this.mContext)) {
                EMLog.d(TAG, "has mobile connection");
                this.emaObject.onNetworkChanged(EMAChatClient.EMANetwork.NETWORK_MOBILE);
            } else if (NetUtils.isEthernetConnected(this.mContext)) {
                EMLog.d(TAG, "has ethernet connection");
                this.emaObject.onNetworkChanged(EMAChatClient.EMANetwork.NETWORK_CABLE);
            } else {
                EMLog.d(TAG, "no data connection");
                this.emaObject.onNetworkChanged(EMAChatClient.EMANetwork.NETWORK_NONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNetworkChanged(EMAChatClient.EMANetwork eMANetwork) {
        this.emaObject.onNetworkChanged(eMANetwork);
    }

    void onNewLogin() {
        EMLog.d(TAG, "on new login created");
        String lastLoginUser = EMSessionManager.getInstance().getLastLoginUser();
        PathUtil.getInstance().initDirs(getChatConfigPrivate().l(), lastLoginUser, this.mContext);
        c.a(lastLoginUser, this.mChatConfigPrivate);
        c.a().c();
        saveAppname();
        EMPushHelper.getInstance().onInit();
        a.a().a(this.mChatConfigPrivate);
        this.mContext.registerReceiver(this.connectivityBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if (this.smartHeartbeat == null) {
            this.smartHeartbeat = EMSmartHeartBeat.create(this.mContext);
        }
        this.smartHeartbeat.onInit();
        if (getChatConfigPrivate().a.hasHeartBeatCustomizedParams()) {
            this.smartHeartbeat.setCustomizedParams(getChatConfigPrivate().a.getWifiHeartBeatCustomizedParams(), getChatConfigPrivate().a.getMobileHeartBeatCustomizedParams());
        }
        scheduleJob();
    }

    public EMPushManager pushManager() {
        if (this.pushManager == null) {
            this.pushManager = new EMPushManager(this, this.emaObject.getPushMnager());
        }
        return this.pushManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reconnect() {
        this.wakeLock.acquire();
        this.emaObject.reconnect();
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
    }

    public void removeConnectionListener(EMConnectionListener eMConnectionListener) {
        if (eMConnectionListener != null) {
            synchronized (this.connectionListeners) {
                this.connectionListeners.remove(eMConnectionListener);
            }
        }
    }

    void saveAppname() {
        EMMonitor.getInstance().getMonitorDB().a(this.mContext.getPackageName());
    }

    @TargetApi(21)
    void scheduleJob() {
        if (Utils.isSdk21()) {
            try {
                this.mContext.startService(new Intent(getContext(), EMJobService.class));
                JobInfo.Builder builder = new JobInfo.Builder(11, new ComponentName(getContext(), EMJobService.class));
                builder.setPeriodic(60000L);
                builder.setRequiredNetworkType(1);
                builder.setPersisted(true);
                ((JobScheduler) this.mContext.getSystemService("jobscheduler")).schedule(builder.build());
            } catch (Exception e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean sendPing(boolean z, long j) {
        return this.emaObject.sendPing(z, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAppStateListener(AppStateListener appStateListener) {
        this.appStateListener = appStateListener;
    }

    public void setDebugMode(boolean z) {
        String e;
        if (this.sdkInited && (e = a.a().e()) != null) {
            z = Boolean.parseBoolean(e);
        }
        EMLog.debugMode = z;
        getChatConfigPrivate().c(z);
    }

    void setEncryptProvider(EMEncryptProvider eMEncryptProvider) {
        this.encryptProvider = eMEncryptProvider;
    }

    void setNatvieNetworkCallback() {
        this.mChatConfigPrivate.a.setNetCallback(new EMANetCallback() { // from class: com.hyphenate.chat.EMClient.7
            @Override // com.hyphenate.chat.adapter.EMANetCallback
            public int getNetState() {
                return !NetUtils.hasDataConnection(EMClient.this.mContext) ? EMAChatClient.EMANetwork.NETWORK_NONE.ordinal() : NetUtils.isWifiConnected(EMClient.this.mContext) ? EMAChatClient.EMANetwork.NETWORK_WIFI.ordinal() : EMAChatClient.EMANetwork.NETWORK_MOBILE.ordinal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPresence(int i) {
        this.emaObject.setPresence(i);
    }

    @Deprecated
    public boolean updateCurrentUserNick(String str) {
        return pushManager().updatePushNickname(str);
    }

    public void uploadLog(EMCallBack eMCallBack) {
        chatManager().emaObject.uploadLog();
    }
}
