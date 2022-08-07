package com.hyphenate.chat;

import com.amap.api.discover.Discover;
import com.amap.api.discover.DiscoverResult;
import com.amap.api.discover.Poi;
import com.amap.api.netlocation.AMapNetworkLocationClient;
import com.hyphenate.util.CryptoUtils;
import com.hyphenate.util.EMLog;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class EMGDLocation {
    public static final String TAG = "EMGDLocation";
    static EMGDLocation sInstance;
    Timer discoverTimer = null;

    static synchronized EMGDLocation getInstance() {
        EMGDLocation eMGDLocation;
        synchronized (EMGDLocation.class) {
            if (sInstance == null) {
                sInstance = new EMGDLocation();
            }
            eMGDLocation = sInstance;
        }
        return eMGDLocation;
    }

    void init() {
        EMLog.d(TAG, "init");
        this.discoverTimer = new Timer();
        final String currentUser = EMClient.getInstance().getCurrentUser();
        this.discoverTimer.schedule(new TimerTask() { // from class: com.hyphenate.chat.EMGDLocation.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (EMClient.getInstance().isConnected() && EMClient.getInstance().getCurrentUser().equals(currentUser)) {
                    Discover discover = new Discover(EMClient.getInstance().getContext());
                    try {
                        EMEncryptProvider encryptProvider = EMClient.getInstance().getEncryptProvider();
                        String B = EMClient.getInstance().getChatConfigPrivate().B();
                        String C = EMClient.getInstance().getChatConfigPrivate().C();
                        if (!B.isEmpty()) {
                            discover.setApiKey(new String(encryptProvider.decrypt(CryptoUtils.fromHexString(B), "")));
                            final EMGDLocation eMGDLocation = EMGDLocation.this;
                            final String str = currentUser;
                            discover.setDiscoverListener(new Discover.AMapDiscoverListener() { // from class: com.hyphenate.chat.EMGDLocation.1DiscoverResult
                                public void onDiscovered(DiscoverResult discoverResult, int i) {
                                    EMLog.d(EMGDLocation.TAG, "onDiscovered");
                                    try {
                                        if (EMClient.getInstance().isConnected() && EMClient.getInstance().getCurrentUser().equals(str)) {
                                            List pois = discoverResult.getPois();
                                            if (pois.size() > 0) {
                                                try {
                                                    EMClient.getInstance().setPresence(Integer.valueOf(((Poi) pois.get(0)).getTypeCode()).intValue());
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            });
                            discover.getDiscover();
                        }
                        if (!C.isEmpty()) {
                            String str2 = new String(encryptProvider.decrypt(CryptoUtils.fromHexString(C), ""));
                            AMapNetworkLocationClient aMapNetworkLocationClient = new AMapNetworkLocationClient(EMClient.getInstance().getContext());
                            aMapNetworkLocationClient.setApiKey(str2);
                            EMLog.d(EMGDLocation.TAG, "network location result:" + aMapNetworkLocationClient.getNetworkLocation());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 30000L);
    }

    void onDestroy() {
        if (this.discoverTimer != null) {
            this.discoverTimer.cancel();
            this.discoverTimer = null;
        }
    }
}
