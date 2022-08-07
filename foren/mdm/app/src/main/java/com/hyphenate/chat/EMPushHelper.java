package com.hyphenate.chat;

import android.os.Build;
import android.util.Pair;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.huawei.android.pushagent.api.PushManager;
import com.hyphenate.chat.a.b;
import com.hyphenate.chat.a.e;
import com.hyphenate.cloud.EMHttpClient;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.DeviceUuidFactory;
import com.hyphenate.util.EMLog;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMPushHelper {
    public static final String TAG = EMPushHelper.class.getSimpleName();
    private static EMPushHelper instance;
    private String notifyDeviceToken;
    private Thread pushThread = null;
    private Object sendTokenLock = new Object();
    private boolean isLogout = false;
    private EMPushType pushType = EMPushType.NORMAL;

    /* loaded from: classes2.dex */
    public enum EMPushType {
        GCM,
        MIPUSH,
        HUAWEIPUSH,
        NORMAL
    }

    EMPushHelper() {
    }

    public static EMPushHelper getInstance() {
        if (instance == null) {
            instance = new EMPushHelper();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean checkAvailablePushService() {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hyphenate.chat.EMPushHelper.checkAvailablePushService():boolean");
    }

    String getDeviceToken() {
        String pushToken = getPushToken();
        if (pushToken == null) {
            try {
                EMPushType pushType = getPushType();
                if (pushType == EMPushType.GCM) {
                    if (EMClient.getInstance().getOptions().getGCMNumber() != null) {
                        GoogleCloudMessaging googleCloudMessaging = null;
                        if (0 == 0) {
                            googleCloudMessaging = GoogleCloudMessaging.getInstance(EMClient.getInstance().getContext());
                        }
                        pushToken = googleCloudMessaging.register(new String[]{EMClient.getInstance().getOptions().getGCMNumber()});
                    }
                } else if (pushType == EMPushType.MIPUSH) {
                    b.C0043b mipushConfig = EMClient.getInstance().getOptions().getMipushConfig();
                    if (mipushConfig != null) {
                        MiPushClient.registerPush(EMClient.getInstance().getContext(), mipushConfig.a, mipushConfig.b);
                        synchronized (this.sendTokenLock) {
                            try {
                                this.sendTokenLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        pushToken = this.notifyDeviceToken;
                    }
                } else if (pushType == EMPushType.HUAWEIPUSH) {
                    PushManager.requestToken(EMClient.getInstance().getContext());
                    synchronized (this.sendTokenLock) {
                        try {
                            this.sendTokenLock.wait(60000L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                    pushToken = this.notifyDeviceToken;
                }
            } catch (Exception e3) {
                EMLog.e(TAG, "get device token with error: " + e3.toString());
            }
            EMLog.d(TAG, "devicetoken = " + pushToken);
            if (pushToken == null || "".equals(pushToken)) {
                setPushType(EMPushType.NORMAL);
            }
        }
        return pushToken;
    }

    public String getPushToken() {
        return e.a().m();
    }

    public EMPushType getPushType() {
        return this.pushType;
    }

    boolean isPushServiceEnabled() {
        return this.pushType != EMPushType.NORMAL;
    }

    public void onDestroy(boolean z) throws HyphenateException {
        EMLog.d(TAG, "push notification helper ondestory");
        onReceiveToken(null);
        if (this.pushThread != null) {
            this.pushThread.interrupt();
            this.pushThread = null;
        }
        this.isLogout = true;
        if (z && isPushServiceEnabled()) {
            if (!sendTokenToServer("")) {
                EMLog.d(TAG, "unbind device token faild");
                throw new HyphenateException(212, "unbind device token failed");
            } else {
                setPushType(EMPushType.NORMAL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onInit() {
        this.isLogout = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onReceiveToken(String str) {
        this.notifyDeviceToken = str;
        synchronized (this.sendTokenLock) {
            try {
                this.sendTokenLock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean sendDeviceInfo(String str) {
        int intValue;
        String str2;
        String str3 = EMClient.getInstance().getChatConfigPrivate().f() + "/devices";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", f.a);
            jSONObject.put("name", str);
            jSONObject.put("token", str);
            jSONObject.put("sdk_version", EMClient.getInstance().getChatConfigPrivate().e());
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            jSONObject.put("device_uuid", new DeviceUuidFactory(EMClient.getInstance().getContext()).getDeviceUuid().toString());
            Pair<Integer, String> sendRequest = EMHttpClient.getInstance().sendRequest(str3, null, jSONObject.toString(), EMHttpClient.POST);
            intValue = ((Integer) sendRequest.first).intValue();
            str2 = (String) sendRequest.second;
        } catch (Exception e) {
            EMLog.e(TAG, e.toString());
        }
        switch (intValue) {
            case 200:
                setPushToken(str);
                EMLog.d(TAG, "sendDeviceToServer SC_OK:");
                return true;
            default:
                if (str2.contains("duplicate_unique_property_exists")) {
                    EMLog.d(TAG, "device token already exists");
                    setPushToken(str);
                    return true;
                }
                EMLog.d(TAG, "sendDeviceToServer error : " + str2);
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendDeviceTokenToServer() {
        if (isPushServiceEnabled()) {
            EMLog.d(TAG, "third-party push available");
            if (!this.isLogout) {
                if ((this.pushThread == null || !this.pushThread.isAlive()) && this.pushThread == null) {
                    this.pushThread = new Thread() { // from class: com.hyphenate.chat.EMPushHelper.1
                        /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
                            if (r0 != false) goto L_0x008a;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
                            new com.hyphenate.chat.EMPushHelper.AnonymousClass1.AnonymousClass1HandleSendFail(r10).onSendFail();
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:38:0x009c, code lost:
                            if (r0 != true) goto L_?;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:39:0x009e, code lost:
                            com.hyphenate.chat.EMClient.getInstance().cancelJob();
                            com.hyphenate.chat.EMClient.getInstance().doStopService();
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
                            return;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
                            return;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
                            return;
                         */
                        /* JADX WARN: Type inference failed for: r0v29, types: [com.hyphenate.chat.EMPushHelper$1$1HandleSendFail] */
                        /* JADX WARN: Type inference failed for: r0v40, types: [com.hyphenate.chat.EMPushHelper$1$1HandleSendFail] */
                        @Override // java.lang.Thread, java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public void run() {
                            /*
                                r10 = this;
                                r9 = 3
                                r8 = 1
                                r1 = 0
                                r0 = 0
                                r3 = r1
                            L_0x0005:
                                if (r3 >= r9) goto L_0x00c8
                                com.hyphenate.chat.EMPushHelper r0 = com.hyphenate.chat.EMPushHelper.this     // Catch: Exception -> 0x003c
                                java.lang.String r2 = r0.getDeviceToken()     // Catch: Exception -> 0x003c
                                if (r2 == 0) goto L_0x001b
                                r4 = r2
                            L_0x0010:
                                if (r4 != 0) goto L_0x0047
                                com.hyphenate.chat.EMPushHelper$1$1HandleSendFail r0 = new com.hyphenate.chat.EMPushHelper$1$1HandleSendFail     // Catch: Exception -> 0x003c
                                r0.<init>()     // Catch: Exception -> 0x003c
                                r0.onSendFail()     // Catch: Exception -> 0x003c
                            L_0x001a:
                                return
                            L_0x001b:
                                java.util.Random r0 = new java.util.Random     // Catch: Exception -> 0x0037
                                r0.<init>()     // Catch: Exception -> 0x0037
                                r4 = 10
                                int r0 = r0.nextInt(r4)     // Catch: Exception -> 0x0037
                                int r0 = r0 * 1000
                                long r4 = (long) r0     // Catch: Exception -> 0x0037
                                sleep(r4)     // Catch: Exception -> 0x0037
                            L_0x002c:
                                boolean r0 = r10.isInterrupted()     // Catch: Exception -> 0x003c
                                if (r0 != 0) goto L_0x001a
                                int r0 = r3 + 1
                                r3 = r0
                                r0 = r2
                                goto L_0x0005
                            L_0x0037:
                                r0 = move-exception
                                r0.printStackTrace()     // Catch: Exception -> 0x003c
                                goto L_0x002c
                            L_0x003c:
                                r0 = move-exception
                                java.lang.String r1 = com.hyphenate.chat.EMPushHelper.TAG
                                java.lang.String r0 = r0.toString()
                                com.hyphenate.util.EMLog.e(r1, r0)
                                goto L_0x001a
                            L_0x0047:
                                com.hyphenate.chat.EMPushHelper r0 = com.hyphenate.chat.EMPushHelper.this     // Catch: Exception -> 0x003c
                                java.lang.String r0 = r0.getPushToken()     // Catch: Exception -> 0x003c
                                if (r0 != 0) goto L_0x008a
                                r3 = r1
                                r0 = r1
                            L_0x0051:
                                if (r3 >= r9) goto L_0x005c
                                com.hyphenate.chat.EMPushHelper r0 = com.hyphenate.chat.EMPushHelper.this     // Catch: Exception -> 0x003c
                                boolean r2 = r0.sendDeviceInfo(r4)     // Catch: Exception -> 0x003c
                                if (r2 != r8) goto L_0x0067
                                r0 = r2
                            L_0x005c:
                                if (r0 != 0) goto L_0x008a
                                com.hyphenate.chat.EMPushHelper$1$1HandleSendFail r0 = new com.hyphenate.chat.EMPushHelper$1$1HandleSendFail     // Catch: Exception -> 0x003c
                                r0.<init>()     // Catch: Exception -> 0x003c
                                r0.onSendFail()     // Catch: Exception -> 0x003c
                                goto L_0x001a
                            L_0x0067:
                                java.util.Random r0 = new java.util.Random     // Catch: Exception -> 0x0085
                                r0.<init>()     // Catch: Exception -> 0x0085
                                r5 = 10
                                int r0 = r0.nextInt(r5)     // Catch: Exception -> 0x0085
                                int r0 = r0 + 20
                                int r0 = r0 * 1000
                                long r6 = (long) r0     // Catch: Exception -> 0x0085
                                sleep(r6)     // Catch: Exception -> 0x0085
                            L_0x007a:
                                boolean r0 = r10.isInterrupted()     // Catch: Exception -> 0x003c
                                if (r0 != 0) goto L_0x001a
                                int r0 = r3 + 1
                                r3 = r0
                                r0 = r2
                                goto L_0x0051
                            L_0x0085:
                                r0 = move-exception
                                r0.printStackTrace()     // Catch: Exception -> 0x003c
                                goto L_0x007a
                            L_0x008a:
                                com.hyphenate.chat.EMRandomDelay r5 = new com.hyphenate.chat.EMRandomDelay     // Catch: Exception -> 0x003c
                                r5.<init>()     // Catch: Exception -> 0x003c
                                r2 = r1
                                r0 = r1
                            L_0x0091:
                                if (r0 != 0) goto L_0x009c
                                com.hyphenate.chat.EMPushHelper r0 = com.hyphenate.chat.EMPushHelper.this     // Catch: Exception -> 0x003c
                                boolean r1 = r0.sendTokenToServer(r4)     // Catch: Exception -> 0x003c
                                if (r1 != r8) goto L_0x00ae
                                r0 = r1
                            L_0x009c:
                                if (r0 != r8) goto L_0x001a
                                com.hyphenate.chat.EMClient r0 = com.hyphenate.chat.EMClient.getInstance()     // Catch: Exception -> 0x003c
                                r0.cancelJob()     // Catch: Exception -> 0x003c
                                com.hyphenate.chat.EMClient r0 = com.hyphenate.chat.EMClient.getInstance()     // Catch: Exception -> 0x003c
                                r0.doStopService()     // Catch: Exception -> 0x003c
                                goto L_0x001a
                            L_0x00ae:
                                int r3 = r2 + 1
                                int r0 = r5.timeDelay(r2)     // Catch: Exception -> 0x00c3
                                int r0 = r0 * 1000
                                long r6 = (long) r0     // Catch: Exception -> 0x00c3
                                sleep(r6)     // Catch: Exception -> 0x00c3
                            L_0x00ba:
                                boolean r0 = r10.isInterrupted()     // Catch: Exception -> 0x003c
                                if (r0 != 0) goto L_0x001a
                                r2 = r3
                                r0 = r1
                                goto L_0x0091
                            L_0x00c3:
                                r0 = move-exception
                                r0.printStackTrace()     // Catch: Exception -> 0x003c
                                goto L_0x00ba
                            L_0x00c8:
                                r4 = r0
                                goto L_0x0010
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.hyphenate.chat.EMPushHelper.AnonymousClass1.run():void");
                        }
                    };
                    this.pushThread.start();
                    return;
                }
                return;
            }
            return;
        }
        EMLog.d(TAG, "GCM and mipush not available");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean sendTokenToServer(String str) {
        boolean z;
        int intValue;
        String str2;
        synchronized (this.sendTokenLock) {
            String str3 = EMClient.getInstance().getChatConfigPrivate().f() + "/users/" + EMClient.getInstance().getCurrentUser();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("device_token", str);
                String str4 = null;
                switch (this.pushType) {
                    case GCM:
                        str4 = EMClient.getInstance().getOptions().getGCMNumber();
                        break;
                    case MIPUSH:
                        str4 = EMClient.getInstance().getOptions().getMipushConfig().a;
                        break;
                    case HUAWEIPUSH:
                        str4 = EMClient.getInstance().getOptions().getHuaweiPushAppId();
                        break;
                }
                if (str4 == null) {
                    str4 = "";
                }
                jSONObject.put("notifier_name", str4);
                EMLog.d(TAG, "send device token to server, token = " + str + ",url = " + str3 + ",notifier_name = " + str4);
                Pair<Integer, String> sendRequestWithToken = EMHttpClient.getInstance().sendRequestWithToken(str3, jSONObject.toString(), EMHttpClient.PUT);
                intValue = ((Integer) sendRequestWithToken.first).intValue();
                str2 = (String) sendRequestWithToken.second;
            } catch (Exception e) {
                EMLog.e(TAG, e.toString());
            }
            switch (intValue) {
                case 200:
                    EMLog.d(TAG, "sendTokenToServer SC_OK:");
                    z = true;
                    break;
                default:
                    EMLog.d(TAG, "sendTokenToServer error:" + str2);
                    z = false;
                    break;
            }
        }
        return z;
    }

    public void setPushToken(String str) {
        e.a().d(str);
    }

    public void setPushType(EMPushType eMPushType) {
        this.pushType = eMPushType;
    }
}
