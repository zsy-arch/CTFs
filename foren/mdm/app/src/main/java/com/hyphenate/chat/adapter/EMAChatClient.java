package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMAChatClient extends EMABase {
    private EMAChatManager chatManager = null;
    private EMAChatRoomManager chatRoomManager = null;
    private EMACallManager callManager = null;
    private EMAGroupManager groupManager = null;
    private EMAContactManager contactManager = null;
    private EMAPushManager pushManager = null;

    /* loaded from: classes2.dex */
    public enum EMANetwork {
        NETWORK_NONE,
        NETWORK_CABLE,
        NETWORK_WIFI,
        NETWORK_MOBILE
    }

    public static EMAChatClient create(EMAChatConfig eMAChatConfig) {
        EMAChatClient eMAChatClient = new EMAChatClient();
        eMAChatClient.nativeHandler = native_create(eMAChatConfig);
        if (eMAChatClient.getChatManager() == null) {
            eMAChatClient.chatManager = new EMAChatManager();
            eMAChatClient.getChatManager().nativeHandler = eMAChatClient.native_getChatManager();
        }
        if (eMAChatClient.getChatRoomManager() == null) {
            eMAChatClient.chatRoomManager = new EMAChatRoomManager();
            eMAChatClient.getChatRoomManager().nativeHandler = eMAChatClient.native_getChatRoomManager();
        }
        if (eMAChatClient.getCallManager() == null) {
            eMAChatClient.callManager = new EMACallManager();
            eMAChatClient.getCallManager().nativeHandler = eMAChatClient.native_getCallManager();
        }
        if (eMAChatClient.getGroupManager() == null) {
            eMAChatClient.groupManager = new EMAGroupManager();
            eMAChatClient.getGroupManager().nativeHandler = eMAChatClient.native_getGroupManager();
        }
        if (eMAChatClient.getContactManager() == null) {
            eMAChatClient.contactManager = new EMAContactManager();
            eMAChatClient.getContactManager().nativeHandler = eMAChatClient.native_getContactManager();
        }
        if (eMAChatClient.getPushMnager() == null) {
            eMAChatClient.pushManager = new EMAPushManager();
            eMAChatClient.getPushMnager().nativeHandler = eMAChatClient.native_getPushManager();
        }
        return eMAChatClient;
    }

    static native long native_create(EMAChatConfig eMAChatConfig);

    public void addConnectionListener(EMAConnectionListener eMAConnectionListener) {
        native_addConnectionListener(eMAConnectionListener);
    }

    public void autoLogin(String str, String str2, EMAError eMAError) {
        native_autoLogin(str, str2, eMAError);
    }

    public EMAError changeAppkey(String str) {
        return native_changeAppkey(str);
    }

    public EMAError createAccount(String str, String str2) {
        return native_createAccount(str, str2);
    }

    public void disconnect() {
        native_disconnect();
    }

    public EMACallManager getCallManager() {
        return this.callManager;
    }

    public EMAChatManager getChatManager() {
        return this.chatManager;
    }

    public EMAChatRoomManager getChatRoomManager() {
        return this.chatRoomManager;
    }

    public EMAContactManager getContactManager() {
        return this.contactManager;
    }

    public EMAGroupManager getGroupManager() {
        return this.groupManager;
    }

    public EMAPushManager getPushMnager() {
        return this.pushManager;
    }

    public native String helloWorld();

    public boolean isConnected() {
        return native_isConnected();
    }

    public void login(String str, String str2, EMAError eMAError) {
        native_login(str, str2, eMAError);
    }

    public void logout() {
        native_logout();
    }

    native void native_addConnectionListener(EMAConnectionListener eMAConnectionListener);

    native void native_autoLogin(String str, String str2, EMAError eMAError);

    native EMAError native_changeAppkey(String str);

    native EMAError native_createAccount(String str, String str2);

    native void native_disconnect();

    native long native_getCallManager();

    native long native_getChatManager();

    native long native_getChatRoomManager();

    native long native_getContactManager();

    native long native_getGroupManager();

    native long native_getPushManager();

    native boolean native_isConnected();

    native void native_login(String str, String str2, EMAError eMAError);

    native void native_logout();

    native void native_onNetworkChanged(int i);

    native void native_removeConnectionListener(EMAConnectionListener eMAConnectionListener);

    native boolean native_sendPing(boolean z, long j);

    native void native_setPresence(int i);

    native void natvie_reconnect();

    public void onNetworkChanged(EMANetwork eMANetwork) {
        native_onNetworkChanged(eMANetwork.ordinal());
    }

    public void reconnect() {
        natvie_reconnect();
    }

    public void removeConnectionListener(EMAConnectionListener eMAConnectionListener) {
        native_removeConnectionListener(eMAConnectionListener);
    }

    public boolean sendPing(boolean z, long j) {
        return native_sendPing(z, j);
    }

    public void setPresence(int i) {
        native_setPresence(i);
    }
}
