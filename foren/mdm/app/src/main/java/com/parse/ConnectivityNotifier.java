package com.parse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
class ConnectivityNotifier extends BroadcastReceiver {
    private static final String TAG = "com.parse.ConnectivityNotifier";
    private static final ConnectivityNotifier singleton = new ConnectivityNotifier();
    private Set<ConnectivityListener> listeners = new HashSet();
    private boolean hasRegisteredReceiver = false;
    private final Object lock = new Object();

    /* loaded from: classes2.dex */
    public interface ConnectivityListener {
        void networkConnectivityStatusChanged(Context context, Intent intent);
    }

    ConnectivityNotifier() {
    }

    public static ConnectivityNotifier getNotifier(Context context) {
        singleton.tryToRegisterForNetworkStatusNotifications(context);
        return singleton;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo network;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (network = connectivityManager.getActiveNetworkInfo()) == null || !network.isConnected()) ? false : true;
    }

    public void addListener(ConnectivityListener delegate) {
        synchronized (this.lock) {
            this.listeners.add(delegate);
        }
    }

    public void removeListener(ConnectivityListener delegate) {
        synchronized (this.lock) {
            this.listeners.remove(delegate);
        }
    }

    private boolean tryToRegisterForNetworkStatusNotifications(Context context) {
        synchronized (this.lock) {
            if (this.hasRegisteredReceiver) {
                return true;
            }
            if (context == null) {
                return false;
            }
            try {
                context.getApplicationContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.hasRegisteredReceiver = true;
                return true;
            } catch (ReceiverCallNotAllowedException e) {
                PLog.v(TAG, "Cannot register a broadcast receiver because the executing thread is currently in a broadcast receiver. Will try again later.");
                return false;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        List<ConnectivityListener> listenersCopy;
        synchronized (this.lock) {
            listenersCopy = new ArrayList<>(this.listeners);
        }
        for (ConnectivityListener delegate : listenersCopy) {
            delegate.networkConnectivityStatusChanged(context, intent);
        }
    }
}
