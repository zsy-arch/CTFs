package com.hyphenate.chat;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.a.a;
import com.hyphenate.analytics.EMCollector;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMPushHelper;
import com.hyphenate.chat.adapter.EMAChatClient;
import com.hyphenate.chat.adapter.EMAHeartBeatCustomizedParams;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;
import com.hyphenate.util.Utils;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class EMSmartHeartBeat {
    static boolean Debug = false;
    private static final int GCM_DISCONNECT_CHECK_INTERVAL = 180000;
    private static final int PING_PONG_TIMEOUT = 8000;
    private static final String TAG = "smart ping";
    private AlarmManager alarmManager;
    private int currentInterval;
    private Context mContext;
    IParams params;
    private int succeededInterval;
    ExecutorService threadPool;
    private PowerManager.WakeLock wakeLock;
    boolean useCustomizedParams = false;
    EMAHeartBeatCustomizedParams mCustomizedWifiParams = null;
    EMAHeartBeatCustomizedParams mCustomizedMobileParams = null;
    private boolean dataReceivedDuringInterval = false;
    private EMHeartBeatReceiver alarmIntentReceiver = null;
    private PendingIntent alarmIntent = null;
    private EMConnectionListener cnnListener = null;
    private EMMessageListener messageListener = null;
    private Object stateLock = new Object();
    private boolean inited = false;
    private boolean prevWifi = false;
    private String prevWIFISSID = "";
    private Timer disconnectTimer = null;
    private TimerTask disconnectTask = null;
    private boolean isInBackground = false;
    private EMSmartPingState pingState = EMSmartPingState.EMReady;
    long lastPacketReceivedTime = 0;
    private Runnable heartBeatRunnable = new Runnable() { // from class: com.hyphenate.chat.EMSmartHeartBeat.5
        @Override // java.lang.Runnable
        public void run() {
            EMLog.d(EMSmartHeartBeat.TAG, "has network connection:" + NetUtils.hasNetwork(EMSmartHeartBeat.this.mContext) + " has data conn:" + NetUtils.hasDataConnection(EMSmartHeartBeat.this.mContext) + " isConnected to hyphenate server : " + EMClient.getInstance().isConnected());
            if (EMSmartHeartBeat.this.hasDataConnection()) {
                EMSmartHeartBeat.this.wakeLock.acquire();
                EMLog.d(EMSmartHeartBeat.TAG, "acquire wake lock");
                EMSmartHeartBeat.this.checkPingPong();
                EMSmartHeartBeat.this.releaseWakelock();
            } else {
                EMLog.d(EMSmartHeartBeat.TAG, "....no connection to server");
                if (!NetUtils.hasDataConnection(EMSmartHeartBeat.this.mContext) && EMClient.getInstance().isConnected()) {
                    if (Utils.isSdk14()) {
                        EMLog.d(EMSmartHeartBeat.TAG, "no data connection but im connection is connected, reconnect");
                        EMClient.getInstance().onNetworkChanged(EMAChatClient.EMANetwork.NETWORK_NONE);
                    } else {
                        EMClient.getInstance().forceReconnect();
                    }
                }
            }
            a.d();
            EMSmartHeartBeat.this.scheduleNextAlarm();
        }
    };

    /* loaded from: classes2.dex */
    public class EMParams extends IParams {
        static final int MAX_INTERVAL = 270000;
        static final int MAX_MIN_INTERVAL_COUNTER = 3;
        static final int MIN_INTERVAL = 30000;
        static final int MOBILE_DEFAULT_INTERVAL = 180000;
        static final int PING_PONG_CHECK_INTERVAL = 900000;
        static final int PING_PONG_TIMEOUT = 8000;
        static final int WIFI_DEFAULT_INTERVAL = 120000;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        EMParams() {
            super();
            EMSmartHeartBeat.this = r1;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getDefaultInterval() {
            return NetUtils.isWifiConnected(EMSmartHeartBeat.this.mContext) ? WIFI_DEFAULT_INTERVAL : MOBILE_DEFAULT_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMaxInterval() {
            return MAX_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMinInterval() {
            return MIN_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongCheckInterval() {
            return PING_PONG_CHECK_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongTimeout() {
            return PING_PONG_TIMEOUT;
        }
    }

    /* loaded from: classes2.dex */
    public class EMParamsCustomized extends IParams {
        static final int PING_PONG_CHECK_INTERVAL = 900000;
        static final int PING_PONG_TIMEOUT = 8000;
        private final int defaultInterval;
        private final int maxInterval;
        private final int minInterval;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        EMParamsCustomized(EMAHeartBeatCustomizedParams eMAHeartBeatCustomizedParams) {
            super();
            EMSmartHeartBeat.this = r2;
            this.defaultInterval = eMAHeartBeatCustomizedParams.defaultInterval;
            this.minInterval = eMAHeartBeatCustomizedParams.minInterval;
            this.maxInterval = eMAHeartBeatCustomizedParams.maxInterval;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getDefaultInterval() {
            return this.defaultInterval;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMaxInterval() {
            return this.maxInterval;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMinInterval() {
            return this.minInterval;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongCheckInterval() {
            return PING_PONG_CHECK_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongTimeout() {
            return PING_PONG_TIMEOUT;
        }
    }

    /* loaded from: classes2.dex */
    public class EMParamsQuickTest extends IParams {
        static final int MAX_INTERVAL = 30000;
        static final int MAX_MIN_INTERVAL_COUNTER = 3;
        static final int MIN_INTERVAL = 10000;
        static final int MOBILE_DEFAULT_INTERVAL = 20000;
        static final int PING_PONG_CHECK_INTERVAL = 900000;
        static final int PING_PONG_TIMEOUT = 8000;
        static final int WIFI_DEFAULT_INTERVAL = 20000;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        EMParamsQuickTest() {
            super();
            EMSmartHeartBeat.this = r1;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getDefaultInterval() {
            if (NetUtils.isWifiConnected(EMSmartHeartBeat.this.mContext)) {
            }
            return 20000;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMaxInterval() {
            return MAX_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getMinInterval() {
            return 10000;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getNextInterval(int i, boolean z) {
            int i2 = ((z ? 1 : -1) * 5 * 1000) + i;
            if (i2 > getMaxInterval()) {
                i2 = getMaxInterval();
            }
            return i2 < getMinInterval() ? getMinInterval() : i2;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongCheckInterval() {
            return PING_PONG_CHECK_INTERVAL;
        }

        @Override // com.hyphenate.chat.EMSmartHeartBeat.IParams
        int getPingPongTimeout() {
            return PING_PONG_TIMEOUT;
        }
    }

    /* loaded from: classes2.dex */
    public enum EMSmartPingState {
        EMReady,
        EMEvaluating,
        EMReevaluating,
        EMHitted,
        EMStopped
    }

    /* loaded from: classes2.dex */
    public abstract class IParams {
        IParams() {
            EMSmartHeartBeat.this = r1;
        }

        abstract int getDefaultInterval();

        abstract int getMaxInterval();

        abstract int getMinInterval();

        int getNextInterval(int i, boolean z) {
            int i2 = z ? 1 : -1;
            int i3 = i2 < 0 ? i <= 60000 ? (i2 * 10 * 1000) + i : i <= 120000 ? (i2 * 30 * 1000) + i : (i2 * 45 * 1000) + i : i < 60000 ? (i2 * 10 * 1000) + i : i < 120000 ? (i2 * 30 * 1000) + i : (i2 * 45 * 1000) + i;
            if (i3 > getMaxInterval()) {
                i3 = getMaxInterval();
            }
            return i3 < getMinInterval() ? getMinInterval() : i3;
        }

        abstract int getPingPongCheckInterval();

        abstract int getPingPongTimeout();
    }

    private EMSmartHeartBeat(Context context) {
        this.mContext = context;
    }

    public void calcDisconnectedInterval() {
        IParams eMParamsCustomized;
        EMLog.d(TAG, "reset interval...");
        boolean isWifiConnected = NetUtils.isWifiConnected(this.mContext);
        boolean isEthernetConnected = NetUtils.isEthernetConnected(this.mContext);
        String str = "";
        if (isWifiConnected) {
            str = NetUtils.getWiFiSSID(this.mContext);
        }
        if (Debug) {
            this.params = new EMParamsQuickTest();
        } else if (this.useCustomizedParams) {
            if (isEthernetConnected) {
                eMParamsCustomized = new EMParams();
            } else {
                eMParamsCustomized = new EMParamsCustomized(isWifiConnected ? this.mCustomizedWifiParams : this.mCustomizedMobileParams);
            }
            this.params = eMParamsCustomized;
        } else {
            this.params = new EMParams();
        }
        boolean isSameNet = isSameNet(isWifiConnected, str);
        this.prevWifi = isWifiConnected;
        this.prevWIFISSID = str;
        if (!isSameNet || this.currentInterval == 0) {
            this.currentInterval = this.params.getDefaultInterval();
            this.succeededInterval = 0;
            changeState(EMSmartPingState.EMEvaluating);
        } else {
            this.currentInterval = this.params.getNextInterval(this.currentInterval, false);
            if (this.pingState == EMSmartPingState.EMHitted) {
                changeState(EMSmartPingState.EMEvaluating);
            } else {
                changeState(EMSmartPingState.EMReevaluating);
            }
            this.succeededInterval = 0;
        }
        this.dataReceivedDuringInterval = false;
        EMLog.d(TAG, "reset currentInterval:" + EMCollector.timeToString(this.currentInterval));
    }

    private void changeState(EMSmartPingState eMSmartPingState) {
        EMLog.d(TAG, "change smart ping state from : " + this.pingState + " to : " + eMSmartPingState);
        synchronized (this.stateLock) {
            this.pingState = eMSmartPingState;
        }
    }

    public void checkPingPong() {
        EMLog.d(TAG, "check pingpong ...");
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                Thread.sleep(1000L);
                try {
                    if (!this.dataReceivedDuringInterval) {
                        z = sendPingPong();
                        if (z) {
                            EMLog.d(TAG, "success to send ping pong ... with current heartbeat interval : " + EMCollector.timeToString(this.currentInterval));
                            this.succeededInterval = this.currentInterval;
                            EMLog.d(TAG, "send ping-pong successes");
                            if (this.pingState == EMSmartPingState.EMHitted) {
                                EMLog.d(TAG, "that's already in the EMHitted state, just return...");
                                return;
                            } else if (this.succeededInterval == this.params.getMaxInterval() || this.pingState == EMSmartPingState.EMReevaluating) {
                                if (this.succeededInterval == this.params.getMaxInterval()) {
                                    EMLog.d(TAG, "Find the best interval, interval is the max interval");
                                }
                                if (this.pingState == EMSmartPingState.EMReevaluating) {
                                    EMLog.d(TAG, "success to pingping and current state is EMSmartPingState.EMReevaluating, so use current interval as final interval");
                                }
                                EMLog.d(TAG, "enter the ping state : " + this.pingState);
                                changeState(EMSmartPingState.EMHitted);
                                return;
                            } else {
                                this.currentInterval = this.params.getNextInterval(this.currentInterval, true);
                            }
                        } else {
                            i++;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            } catch (InterruptedException e2) {
                EMLog.e(TAG, "heartbeat thread be interrupt");
                return;
            }
        }
        if (!z) {
            EMLog.d(TAG, "failed to send ping pong ... with current heartbeat interval : " + EMCollector.timeToString(this.currentInterval));
            if (hasDataConnection()) {
                EMLog.d(TAG, "failed to send ping pong ... with current heartbeat interval : " + EMCollector.timeToString(this.currentInterval));
                if (this.pingState == EMSmartPingState.EMEvaluating || this.pingState == EMSmartPingState.EMHitted) {
                    EMLog.d(TAG, "send ping-pong failed, but has success interval candidate with ping state : " + this.pingState + " enter EMSmartPingState.EMReevaluating");
                    changeState(EMSmartPingState.EMReevaluating);
                }
                this.succeededInterval = 0;
                EMClient.getInstance().forceReconnect();
            }
        }
    }

    public static EMSmartHeartBeat create(Context context) {
        return new EMSmartHeartBeat(context);
    }

    public TimerTask getTask() {
        return new TimerTask() { // from class: com.hyphenate.chat.EMSmartHeartBeat.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                EMLog.d(EMSmartHeartBeat.TAG, "enter the disconnect task");
                if (EMClient.getInstance().isConnected()) {
                    EMClient.getInstance().disconnect();
                }
                try {
                    EMSmartHeartBeat.this.alarmManager.cancel(EMSmartHeartBeat.this.alarmIntent);
                    EMSmartHeartBeat.this.mContext.unregisterReceiver(EMSmartHeartBeat.this.alarmIntentReceiver);
                    EMSmartHeartBeat.this.alarmIntentReceiver = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public boolean hasDataConnection() {
        return NetUtils.hasDataConnection(this.mContext) && EMClient.getInstance().isConnected();
    }

    private boolean isSameNet(boolean z, String str) {
        EMLog.d(TAG, "prevWifi:" + this.prevWifi + " isWifi:" + z + " prevWIFISSID:" + this.prevWIFISSID + " SSID" + str);
        if (!z) {
            return this.prevWifi == z;
        }
        if (!str.isEmpty()) {
            return str.equals(this.prevWIFISSID);
        }
        return false;
    }

    public void releaseWakelock() {
        synchronized (this) {
            if (this.wakeLock != null && this.wakeLock.isHeld()) {
                this.wakeLock.release();
                EMLog.d(TAG, "released the wake lock");
            }
        }
    }

    private void reset() {
        EMLog.d(TAG, "reset interval...");
        this.currentInterval = 0;
        this.succeededInterval = 0;
        this.dataReceivedDuringInterval = false;
        changeState(EMSmartPingState.EMEvaluating);
    }

    private boolean sendPingPong() {
        EMLog.d(TAG, "send ping-pong type heartbeat");
        if (!EMClient.getInstance().isConnected()) {
            return false;
        }
        return EMClient.getInstance().sendPing(true, 8000L);
    }

    public void onInit() {
        this.threadPool = Executors.newSingleThreadExecutor();
        changeState(EMSmartPingState.EMEvaluating);
        reset();
        this.disconnectTimer = new Timer();
        this.alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (this.cnnListener == null) {
            this.cnnListener = new EMConnectionListener() { // from class: com.hyphenate.chat.EMSmartHeartBeat.1
                @Override // com.hyphenate.EMConnectionListener
                public void onConnected() {
                    EMLog.d(EMSmartHeartBeat.TAG, " onConnectred ...");
                    if (EMPushHelper.getInstance().getPushType() != EMPushHelper.EMPushType.GCM || !EMSmartHeartBeat.this.isInBackground) {
                        EMSmartHeartBeat.this.calcDisconnectedInterval();
                        EMSmartHeartBeat.this.scheduleNextAlarm();
                        return;
                    }
                    EMClient.getInstance().disconnect();
                }

                @Override // com.hyphenate.EMConnectionListener
                public void onDisconnected(int i) {
                    EMLog.d(EMSmartHeartBeat.TAG, " onDisconnected ..." + i);
                }
            };
        }
        if (this.messageListener == null) {
            this.messageListener = new EMMessageListener() { // from class: com.hyphenate.chat.EMSmartHeartBeat.2
                @Override // com.hyphenate.EMMessageListener
                public void onCmdMessageReceived(List<EMMessage> list) {
                    EMSmartHeartBeat.this.dataReceivedDuringInterval = true;
                }

                @Override // com.hyphenate.EMMessageListener
                public void onMessageChanged(EMMessage eMMessage, Object obj) {
                }

                @Override // com.hyphenate.EMMessageListener
                public void onMessageDelivered(List<EMMessage> list) {
                    EMSmartHeartBeat.this.dataReceivedDuringInterval = true;
                }

                @Override // com.hyphenate.EMMessageListener
                public void onMessageRead(List<EMMessage> list) {
                    EMSmartHeartBeat.this.dataReceivedDuringInterval = true;
                }

                @Override // com.hyphenate.EMMessageListener
                public void onMessageReceived(List<EMMessage> list) {
                    EMSmartHeartBeat.this.dataReceivedDuringInterval = true;
                }
            };
        }
        EMClient.getInstance().addConnectionListener(this.cnnListener);
        EMClient.getInstance().chatManager().addMessageListener(this.messageListener);
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        if (this.wakeLock == null) {
            this.wakeLock = powerManager.newWakeLock(1, "heartbeatlock");
        }
        if (Utils.isSdk14()) {
            EMClient.getInstance().setAppStateListener(new EMClient.AppStateListener() { // from class: com.hyphenate.chat.EMSmartHeartBeat.3
                @Override // com.hyphenate.chat.EMClient.AppStateListener
                public void onBackground() {
                    EMSmartHeartBeat.this.isInBackground = true;
                    EMLog.d(EMSmartHeartBeat.TAG, "app onBackground");
                    new Thread(new Runnable() { // from class: com.hyphenate.chat.EMSmartHeartBeat.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EMPushHelper.getInstance().getPushType() == EMPushHelper.EMPushType.GCM) {
                                EMSmartHeartBeat.this.disconnectTask = EMSmartHeartBeat.this.getTask();
                                try {
                                    EMSmartHeartBeat.this.disconnectTimer.schedule(EMSmartHeartBeat.this.disconnectTask, 180000L);
                                    EMLog.d(EMSmartHeartBeat.TAG, "schedule disconnect task");
                                } catch (Exception e) {
                                }
                            }
                        }
                    }).start();
                }

                @Override // com.hyphenate.chat.EMClient.AppStateListener
                public void onForeground() {
                    EMLog.d(EMSmartHeartBeat.TAG, "app onForeground");
                    EMSmartHeartBeat.this.isInBackground = false;
                    new Thread(new Runnable() { // from class: com.hyphenate.chat.EMSmartHeartBeat.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EMPushHelper.getInstance().getPushType() == EMPushHelper.EMPushType.GCM && EMSmartHeartBeat.this.disconnectTask != null) {
                                EMSmartHeartBeat.this.disconnectTask.cancel();
                            }
                            if (!EMClient.getInstance().isConnected() && NetUtils.hasDataConnection(EMSmartHeartBeat.this.mContext)) {
                                EMClient.getInstance().onNetworkChanged();
                            }
                            if (!EMClient.getInstance().isConnected()) {
                                EMClient.getInstance().reconnect();
                            }
                        }
                    }).start();
                }
            });
        }
        this.inited = true;
    }

    @TargetApi(19)
    public void scheduleNextAlarm() {
        Long valueOf;
        try {
            EMLog.d(TAG, "schedule next alarm");
            EMLog.d(TAG, "current heartbeat interval : " + EMCollector.timeToString(this.currentInterval) + " smart ping state : " + this.pingState);
            this.dataReceivedDuringInterval = false;
            if (this.alarmIntent == null) {
                this.alarmIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent("hyphenate.chat.heatbeat." + EMClient.getInstance().getChatConfigPrivate().l()), 0);
            }
            if (this.alarmIntentReceiver == null) {
                this.alarmIntentReceiver = new EMHeartBeatReceiver(this);
                this.mContext.registerReceiver(this.alarmIntentReceiver, new IntentFilter("hyphenate.chat.heatbeat." + EMClient.getInstance().getChatConfigPrivate().l()));
            }
            Long.valueOf(System.currentTimeMillis() + 180000);
            if (hasDataConnection()) {
                if (this.currentInterval <= 0) {
                    this.currentInterval = this.params.getDefaultInterval();
                    EMLog.d(TAG, "current heartbeat interval is not set, use default interval : " + EMCollector.timeToString(this.currentInterval));
                }
                valueOf = Long.valueOf(System.currentTimeMillis() + this.currentInterval);
            } else {
                valueOf = Long.valueOf(System.currentTimeMillis() + 180000);
                EMLog.d(TAG, "is not connected to server, so use idle interval : 3 mins");
            }
            if (Build.VERSION.SDK_INT >= 19) {
                this.alarmManager.setExact(0, valueOf.longValue(), this.alarmIntent);
            } else {
                this.alarmManager.set(0, valueOf.longValue(), this.alarmIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCustomizedParams(EMAHeartBeatCustomizedParams eMAHeartBeatCustomizedParams, EMAHeartBeatCustomizedParams eMAHeartBeatCustomizedParams2) {
        if (eMAHeartBeatCustomizedParams != null && eMAHeartBeatCustomizedParams2 != null) {
            this.useCustomizedParams = true;
            this.mCustomizedWifiParams = eMAHeartBeatCustomizedParams;
            this.mCustomizedMobileParams = eMAHeartBeatCustomizedParams2;
        }
    }

    public void start() {
        if (this.pingState != EMSmartPingState.EMStopped) {
            if (!EMClient.getInstance().isConnected() && NetUtils.hasDataConnection(this.mContext)) {
                EMClient.getInstance().onNetworkChanged();
            }
            if (!EMClient.getInstance().isConnected() || !NetUtils.hasNetwork(this.mContext)) {
                if (this.dataReceivedDuringInterval) {
                    this.dataReceivedDuringInterval = false;
                }
                scheduleNextAlarm();
                return;
            }
            if (this.dataReceivedDuringInterval) {
                this.dataReceivedDuringInterval = false;
                if ((System.currentTimeMillis() - this.lastPacketReceivedTime) - this.currentInterval < 100000) {
                    scheduleNextAlarm();
                    return;
                }
            }
            EMLog.d(TAG, "post heartbeat runnable");
            synchronized (this) {
                if (!this.threadPool.isShutdown()) {
                    this.threadPool.execute(this.heartBeatRunnable);
                }
            }
        }
    }

    public void stop() {
        EMLog.d(TAG, "stop heart beat timer");
        if (!this.inited) {
            EMLog.w(TAG, "smart heartbeat is not inited!");
            return;
        }
        changeState(EMSmartPingState.EMStopped);
        synchronized (this) {
            this.threadPool.shutdownNow();
        }
        reset();
        releaseWakelock();
        this.disconnectTimer.cancel();
        if (this.cnnListener != null) {
            EMClient.getInstance().removeConnectionListener(this.cnnListener);
        }
        if (this.messageListener != null) {
            EMClient.getInstance().chatManager().removeMessageListener(this.messageListener);
            this.messageListener = null;
        }
        try {
            this.alarmManager.cancel(this.alarmIntent);
            this.mContext.unregisterReceiver(this.alarmIntentReceiver);
            this.alarmIntentReceiver = null;
        } catch (Exception e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                e.printStackTrace();
            }
        }
    }
}
