package com.superrtc.call;

import android.content.Context;
import com.superrtc.call.NetworkMonitorAutoDetect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private static NetworkMonitor instance;
    private final Context applicationContext;
    private NetworkMonitorAutoDetect autoDetector;
    private NetworkMonitorAutoDetect.ConnectionType currentConnectionType = NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN;
    private final ArrayList<Long> nativeNetworkObservers;
    private final ArrayList<NetworkObserver> networkObservers;

    /* loaded from: classes2.dex */
    public interface NetworkObserver {
        void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType);
    }

    private native void nativeNotifyConnectionTypeChanged(long j);

    private native void nativeNotifyOfActiveNetworkList(long j, NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr);

    private native void nativeNotifyOfNetworkConnect(long j, NetworkMonitorAutoDetect.NetworkInformation networkInformation);

    private native void nativeNotifyOfNetworkDisconnect(long j, int i);

    private NetworkMonitor(Context context) {
        assertIsTrue(context != null);
        this.applicationContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.nativeNetworkObservers = new ArrayList<>();
        this.networkObservers = new ArrayList<>();
    }

    public static NetworkMonitor init(Context context) {
        if (!isInitialized()) {
            instance = new NetworkMonitor(context);
        }
        return instance;
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public static NetworkMonitor getInstance() {
        return instance;
    }

    public static void setAutoDetectConnectivityState(boolean shouldAutoDetect) {
        getInstance().setAutoDetectConnectivityStateInternal(shouldAutoDetect);
    }

    private static void assertIsTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected to be true");
        }
    }

    private void startMonitoring(long nativeObserver) {
        Logging.d(TAG, "Start monitoring from native observer " + nativeObserver);
        this.nativeNetworkObservers.add(Long.valueOf(nativeObserver));
        setAutoDetectConnectivityStateInternal(true);
    }

    private void stopMonitoring(long nativeObserver) {
        Logging.d(TAG, "Stop monitoring from native observer " + nativeObserver);
        setAutoDetectConnectivityStateInternal(false);
        this.nativeNetworkObservers.remove(Long.valueOf(nativeObserver));
    }

    private NetworkMonitorAutoDetect.ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    private int getCurrentDefaultNetId() {
        if (this.autoDetector == null) {
            return -1;
        }
        return this.autoDetector.getDefaultNetId();
    }

    private void destroyAutoDetector() {
        if (this.autoDetector != null) {
            this.autoDetector.destroy();
            this.autoDetector = null;
        }
    }

    private void setAutoDetectConnectivityStateInternal(boolean shouldAutoDetect) {
        if (!shouldAutoDetect) {
            destroyAutoDetector();
        } else if (this.autoDetector == null) {
            this.autoDetector = new NetworkMonitorAutoDetect(new NetworkMonitorAutoDetect.Observer() { // from class: com.superrtc.call.NetworkMonitor.1
                @Override // com.superrtc.call.NetworkMonitorAutoDetect.Observer
                public void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType newConnectionType) {
                    NetworkMonitor.this.updateCurrentConnectionType(newConnectionType);
                }

                @Override // com.superrtc.call.NetworkMonitorAutoDetect.Observer
                public void onNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInfo) {
                    NetworkMonitor.this.notifyObserversOfNetworkConnect(networkInfo);
                }

                @Override // com.superrtc.call.NetworkMonitorAutoDetect.Observer
                public void onNetworkDisconnect(int networkHandle) {
                    NetworkMonitor.this.notifyObserversOfNetworkDisconnect(networkHandle);
                }
            }, this.applicationContext);
            updateCurrentConnectionType(NetworkMonitorAutoDetect.getConnectionType(this.autoDetector.getCurrentNetworkState()));
            updateActiveNetworkList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(NetworkMonitorAutoDetect.ConnectionType newConnectionType) {
        this.currentConnectionType = newConnectionType;
        notifyObserversOfConnectionTypeChange(newConnectionType);
    }

    private void notifyObserversOfConnectionTypeChange(NetworkMonitorAutoDetect.ConnectionType newConnectionType) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue());
        }
        Iterator<NetworkObserver> it2 = this.networkObservers.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(newConnectionType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInfo) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(it.next().longValue(), networkInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkDisconnect(int networkHandle) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it.next().longValue(), networkHandle);
        }
    }

    private void updateActiveNetworkList() {
        List<NetworkMonitorAutoDetect.NetworkInformation> networkInfoList = this.autoDetector.getActiveNetworkList();
        if (networkInfoList != null && networkInfoList.size() != 0) {
            NetworkMonitorAutoDetect.NetworkInformation[] networkInfos = (NetworkMonitorAutoDetect.NetworkInformation[]) networkInfoList.toArray(new NetworkMonitorAutoDetect.NetworkInformation[networkInfoList.size()]);
            Iterator<Long> it = this.nativeNetworkObservers.iterator();
            while (it.hasNext()) {
                nativeNotifyOfActiveNetworkList(it.next().longValue(), networkInfos);
            }
        }
    }

    public static void addNetworkObserver(NetworkObserver observer) {
        getInstance().addNetworkObserverInternal(observer);
    }

    private void addNetworkObserverInternal(NetworkObserver observer) {
        this.networkObservers.add(observer);
    }

    public static void removeNetworkObserver(NetworkObserver observer) {
        getInstance().removeNetworkObserverInternal(observer);
    }

    private void removeNetworkObserverInternal(NetworkObserver observer) {
        this.networkObservers.remove(observer);
    }

    public static boolean isOnline() {
        NetworkMonitorAutoDetect.ConnectionType connectionType = getInstance().getCurrentConnectionType();
        return (connectionType == NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN || connectionType == NetworkMonitorAutoDetect.ConnectionType.CONNECTION_NONE) ? false : true;
    }

    static void resetInstanceForTests(Context context) {
        instance = new NetworkMonitor(context);
    }

    public static NetworkMonitorAutoDetect getAutoDetectorForTest() {
        return getInstance().autoDetector;
    }
}
