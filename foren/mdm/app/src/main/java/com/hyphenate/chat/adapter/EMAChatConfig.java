package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.message.EMAMessage;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class EMAChatConfig extends EMABase {
    public static void logD(String str, String str2) {
        nativeLogD(str, str2);
    }

    public static void logE(String str, String str2) {
        nativeLogE(str, str2);
    }

    public static void logI(String str, String str2) {
        nativeLogI(str, str2);
    }

    public static void logV(String str, String str2) {
        nativeLogV(str, str2);
    }

    public static void logW(String str, String str2) {
        nativeLogW(str, str2);
    }

    static native void nativeLogD(String str, String str2);

    static native void nativeLogE(String str, String str2);

    static native void nativeLogI(String str, String str2);

    static native void nativeLogV(String str, String str2);

    static native void nativeLogW(String str, String str2);

    public void enableDnsConfig(boolean z) {
        nativeenableDnsConfig(z);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public String getAccessToken() {
        return getAccessToken(false);
    }

    public String getAccessToken(boolean z) {
        return nativegetAccessToken(z);
    }

    public String getAppKey() {
        return nativegetAppKey();
    }

    public boolean getAutoAccept() {
        return nativegetAutoAccept();
    }

    public boolean getAutoAcceptGroupInvitation() {
        return nativegetAutoAcceptGroupInvitation();
    }

    public boolean getAutoConversationLoaded() {
        return nativegetAutoConversationLoaded();
    }

    public boolean getAutoLogin() {
        return nativegetAutoLogin();
    }

    public String getBaseUrl() {
        return nativegetBaseUrl();
    }

    public boolean getCallSendPushNotificaitonIfOffline(EMACallManager eMACallManager) {
        return nativeGetCallSendPushNotificaitonIfOffline(eMACallManager);
    }

    public String getChatAddress() {
        return nativegetChatAddress();
    }

    public String getChatDomain() {
        return nativegetChatDomain();
    }

    public boolean getDeleteMessageAsExitGroup() {
        return nativegetDeleteMessageAsExitGroup();
    }

    public String getDownloadPath() {
        return nativegetDownloadPath();
    }

    public boolean getEnableConsoleLog() {
        return nativegetEnableConsoleLog();
    }

    public String getGaoDeDiscoverKey() {
        return nativeGetGaoDeDiscoverKey();
    }

    public String getGaoDeLocationKey() {
        return nativeGetGaoDeLocationKey();
    }

    public String getGroupDomain() {
        return nativegetGroupDomain();
    }

    public boolean getIsChatroomOwnerLeaveAllowed() {
        return nativegetIsChatroomOwnerLeaveAllowed();
    }

    public boolean getIsSandboxMode() {
        return nativegetIsSandboxMode();
    }

    public EMAHeartBeatCustomizedParams getMobileHeartBeatCustomizedParams() {
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicInteger atomicInteger2 = new AtomicInteger();
        AtomicInteger atomicInteger3 = new AtomicInteger();
        nativeGetMobileHeartBeatCustomizedParams(atomicInteger, atomicInteger2, atomicInteger3);
        EMAHeartBeatCustomizedParams eMAHeartBeatCustomizedParams = new EMAHeartBeatCustomizedParams();
        eMAHeartBeatCustomizedParams.minInterval = atomicInteger.get();
        eMAHeartBeatCustomizedParams.maxInterval = atomicInteger2.get();
        eMAHeartBeatCustomizedParams.defaultInterval = atomicInteger3.get();
        return eMAHeartBeatCustomizedParams;
    }

    public String getNextAvailableBaseUrl() {
        return nativegetNextAvailableBaseUrl();
    }

    public boolean getRequireDeliveryAck() {
        return nativegetRequireDeliveryAck();
    }

    public boolean getRequireReadAck() {
        return nativegetRequireReadAck();
    }

    public String getResourcePath() {
        return nativegetResourcePath();
    }

    public String getRestServer() {
        return nativegetRestServer();
    }

    public boolean getSortMessageByServerTime() {
        return nativeGetSortMessageByServerTime();
    }

    public long getTokenSaveTime() {
        return nativegetTokenSaveTime();
    }

    public boolean getUseAws() {
        return nativeGetUseAws();
    }

    public boolean getUserEncryption() {
        return nativeGetUseEncryption();
    }

    public EMAHeartBeatCustomizedParams getWifiHeartBeatCustomizedParams() {
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicInteger atomicInteger2 = new AtomicInteger();
        AtomicInteger atomicInteger3 = new AtomicInteger();
        nativeGetWifiHeartBeatCustomizedParams(atomicInteger, atomicInteger2, atomicInteger3);
        EMAHeartBeatCustomizedParams eMAHeartBeatCustomizedParams = new EMAHeartBeatCustomizedParams();
        eMAHeartBeatCustomizedParams.minInterval = atomicInteger.get();
        eMAHeartBeatCustomizedParams.maxInterval = atomicInteger2.get();
        eMAHeartBeatCustomizedParams.defaultInterval = atomicInteger3.get();
        return eMAHeartBeatCustomizedParams;
    }

    public String getWorkPath() {
        return nativegetWorkPath();
    }

    public boolean hasHeartBeatCustomizedParams() {
        return nativeHasHeartBeatCustomizedParams();
    }

    public void importBlackList(List<String> list) {
        nativeImportBlackList(list);
    }

    public void importChatRoom(String str, String str2, String str3, String str4, List<String> list, int i) {
        nativeImportChatRoom(str, str2, str3, str4, list, i);
    }

    public void importContacts(List<String> list) {
        nativeImportContacts(list);
    }

    public void importConversation(String str, int i, String str2) {
        nativeImportConversation(str, i, str2);
    }

    public void importGroup(String str, int i, String str2, String str3, String str4, List<String> list, boolean z, int i2) {
        nativeImportGroup(str, i, str2, str3, str4, list, z, i2);
    }

    public void importMessages(List<EMAMessage> list) {
        nativeImportMessages(list);
    }

    public void init(String str, String str2, String str3) {
        nativeInit(str, str2, str3);
    }

    public boolean isEnableDnsConfig() {
        return nativeisEnableDnsConfig();
    }

    public boolean isGcmEnabled() {
        return nativeIsGcmEnabled();
    }

    native void nativeFinalize();

    native boolean nativeGetCallSendPushNotificaitonIfOffline(EMACallManager eMACallManager);

    native String nativeGetGaoDeDiscoverKey();

    native String nativeGetGaoDeLocationKey();

    native void nativeGetMobileHeartBeatCustomizedParams(AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3);

    native boolean nativeGetSortMessageByServerTime();

    native boolean nativeGetUseAws();

    native boolean nativeGetUseEncryption();

    native void nativeGetWifiHeartBeatCustomizedParams(AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3);

    native boolean nativeHasHeartBeatCustomizedParams();

    native void nativeImportBlackList(List<String> list);

    native void nativeImportChatRoom(String str, String str2, String str3, String str4, List<String> list, int i);

    native void nativeImportContacts(List<String> list);

    native void nativeImportConversation(String str, int i, String str2);

    native void nativeImportGroup(String str, int i, String str2, String str3, String str4, List<String> list, boolean z, int i2);

    native void nativeImportMessages(List<EMAMessage> list);

    native void nativeInit(String str, String str2, String str3);

    native boolean nativeIsGcmEnabled();

    native void nativeOpenDatabase(String str);

    native void nativeReloadAll();

    native void nativeSetCallSendPushNotificationIfOffline(EMACallManager eMACallManager, boolean z);

    native void nativeSetCallbackNet(EMANetCallback eMANetCallback);

    native void nativeSetDebugMode(boolean z);

    native void nativeSetSDKVersion(String str);

    native void nativeSetSortMessageByServerTime(boolean z);

    native void nativeSetUseAws(boolean z);

    native void nativeSetUseEncryption(boolean z);

    native void nativeSetUseHttps(boolean z);

    native void nativeUpdateConversationUnreadCount(String str, int i);

    native void nativeUploadLog(EMACallback eMACallback);

    native void nativeenableDnsConfig(boolean z);

    native String nativegetAccessToken(boolean z);

    native String nativegetAppKey();

    native boolean nativegetAutoAccept();

    native boolean nativegetAutoAcceptGroupInvitation();

    native boolean nativegetAutoConversationLoaded();

    native boolean nativegetAutoLogin();

    native String nativegetBaseUrl();

    native String nativegetChatAddress();

    native String nativegetChatDomain();

    native boolean nativegetDeleteMessageAsExitGroup();

    native String nativegetDownloadPath();

    native boolean nativegetEnableConsoleLog();

    native String nativegetGroupDomain();

    native boolean nativegetIsChatroomOwnerLeaveAllowed();

    native boolean nativegetIsSandboxMode();

    native String nativegetNextAvailableBaseUrl();

    native boolean nativegetRequireDeliveryAck();

    native boolean nativegetRequireReadAck();

    native String nativegetResourcePath();

    native String nativegetRestServer();

    native long nativegetTokenSaveTime();

    native String nativegetWorkPath();

    native boolean nativeisEnableDnsConfig();

    native void nativeretrieveDNSConfig();

    native void nativesetAppKey(String str);

    native void nativesetAutoAccept(boolean z);

    native void nativesetAutoAcceptGroupInvitation(boolean z);

    native void nativesetAutoConversationLoaded(boolean z);

    native void nativesetAutoLogin(boolean z);

    native void nativesetChatDomain(String str);

    native void nativesetChatPort(int i);

    native void nativesetChatServer(String str);

    native void nativesetDeleteMessageAsExitGroup(boolean z);

    native void nativesetDownloadPath(String str);

    native void nativesetEnableConsoleLog(boolean z);

    native void nativesetGroupDomain(String str);

    native void nativesetIsChatroomOwnerLeaveAllowed(boolean z);

    native void nativesetIsSandboxMode(boolean z);

    native void nativesetLogPath(String str);

    native void nativesetRequireDeliveryAck(boolean z);

    native void nativesetRequireReadAck(boolean z);

    native void nativesetRestServer(String str);

    native boolean nativeuseHttps();

    public void openDatabase(String str) {
        nativeOpenDatabase(str);
    }

    public void reloadAll() {
        nativeReloadAll();
    }

    public void retrieveDNSConfig() {
        nativeretrieveDNSConfig();
    }

    public void setAppKey(String str) {
        nativesetAppKey(str);
    }

    public void setAutoAccept(boolean z) {
        nativesetAutoAccept(z);
    }

    public void setAutoAcceptGroupInvitation(boolean z) {
        nativesetAutoAcceptGroupInvitation(z);
    }

    public void setAutoConversationLoaded(boolean z) {
        nativesetAutoConversationLoaded(z);
    }

    public void setAutoLogin(boolean z) {
        nativesetAutoLogin(z);
    }

    public void setCallSendPushNotificaitonIfOffline(EMACallManager eMACallManager, boolean z) {
        nativeSetCallSendPushNotificationIfOffline(eMACallManager, z);
    }

    public void setChatDomain(String str) {
        nativesetChatDomain(str);
    }

    public void setChatPort(int i) {
        nativesetChatPort(i);
    }

    public void setChatServer(String str) {
        nativesetChatServer(str);
    }

    public void setDebugMode(boolean z) {
        nativeSetDebugMode(z);
    }

    public void setDeleteMessageAsExitGroup(boolean z) {
        nativesetDeleteMessageAsExitGroup(z);
    }

    public void setDownloadPath(String str) {
        nativesetDownloadPath(str);
    }

    public void setEnableConsoleLog(boolean z) {
        nativesetEnableConsoleLog(z);
    }

    public void setGroupDomain(String str) {
        nativesetGroupDomain(str);
    }

    public void setIsChatroomOwnerLeaveAllowed(boolean z) {
        nativesetIsChatroomOwnerLeaveAllowed(z);
    }

    public void setIsSandboxMode(boolean z) {
        nativesetIsSandboxMode(z);
    }

    public void setLogPath(String str) {
        nativesetLogPath(str);
    }

    public void setNetCallback(EMANetCallback eMANetCallback) {
        nativeSetCallbackNet(eMANetCallback);
    }

    public void setRequireDeliveryAck(boolean z) {
        nativesetRequireDeliveryAck(z);
    }

    public void setRequireReadAck(boolean z) {
        nativesetRequireReadAck(z);
    }

    public void setRestServer(String str) {
        nativesetRestServer(str);
    }

    public void setSDKVersion(String str) {
        nativeSetSDKVersion(str);
    }

    public void setSortMessageByServerTime(boolean z) {
        nativeSetSortMessageByServerTime(z);
    }

    public void setUseAws(boolean z) {
        nativeSetUseAws(z);
    }

    public void setUseEncryption(boolean z) {
        nativeSetUseEncryption(z);
    }

    public void setUseHttps(boolean z) {
        nativeSetUseHttps(z);
    }

    public void updateConversationUnreadCount(String str, int i) {
        nativeUpdateConversationUnreadCount(str, i);
    }

    public void uploadLog(EMACallback eMACallback) {
        nativeUploadLog(eMACallback);
    }

    public boolean useHttps() {
        return false;
    }
}
