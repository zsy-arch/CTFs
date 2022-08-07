package com.hyphenate.chat.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class EMAContactManager extends EMABase {
    private Set<EMAContactListener> listeners = new HashSet();

    public EMAContactManager() {
    }

    public EMAContactManager(EMAContactManager eMAContactManager) {
        nativeInit(eMAContactManager);
    }

    public void acceptInvitation(String str, EMAError eMAError) {
        nativeAcceptInvitation(str, eMAError);
    }

    public void addToBlackList(String str, boolean z, EMAError eMAError) {
        nativeAddToBlackList(str, z, eMAError);
    }

    public void declineInvitation(String str, EMAError eMAError) {
        nativeDeclineInvitation(str, eMAError);
    }

    public void deleteContact(String str, EMAError eMAError, boolean z) {
        nativeDeleteContact(str, eMAError, z);
    }

    public List<String> getBlackListFromDB(EMAError eMAError) {
        return nativeGetBlackListFromDB(eMAError);
    }

    public List<String> getBlackListFromServer(EMAError eMAError) {
        return nativeGetBlackListFromServer(eMAError);
    }

    public List<String> getContactsFromDB(EMAError eMAError) {
        return nativeGetContactsFromDB(eMAError);
    }

    public List<String> getContactsFromServer(EMAError eMAError) {
        return nativeGetContactsFromServer(eMAError);
    }

    public void inviteContact(String str, String str2, EMAError eMAError) {
        nativeInviteContact(str, str2, eMAError);
    }

    native void nativeAcceptInvitation(String str, EMAError eMAError);

    native void nativeAddToBlackList(String str, boolean z, EMAError eMAError);

    native void nativeDeclineInvitation(String str, EMAError eMAError);

    native void nativeDeleteContact(String str, EMAError eMAError, boolean z);

    native List<String> nativeGetBlackListFromDB(EMAError eMAError);

    native List<String> nativeGetBlackListFromServer(EMAError eMAError);

    native List<String> nativeGetContactsFromDB(EMAError eMAError);

    native List<String> nativeGetContactsFromServer(EMAError eMAError);

    native void nativeInit(EMAContactManager eMAContactManager);

    native void nativeInviteContact(String str, String str2, EMAError eMAError);

    native void nativeRegisterContactListener(EMAContactListener eMAContactListener);

    native void nativeRemoveContactListener(EMAContactListener eMAContactListener);

    native void nativeRemoveFromBlackList(String str, EMAError eMAError);

    native void nativeSaveBlackList(List<String> list, EMAError eMAError);

    native void nativeSetSupportRosterVersion(boolean z);

    public void registerContactListener(EMAContactListener eMAContactListener) {
        this.listeners.add(eMAContactListener);
        nativeRegisterContactListener(eMAContactListener);
    }

    public void removeContactListener(EMAContactListener eMAContactListener) {
        this.listeners.remove(eMAContactListener);
        nativeRemoveContactListener(eMAContactListener);
    }

    public void removeFromBlackList(String str, EMAError eMAError) {
        nativeRemoveFromBlackList(str, eMAError);
    }

    public void saveBlackList(List<String> list, EMAError eMAError) {
        nativeSaveBlackList(list, eMAError);
    }

    public void setSupportRosterVersion(boolean z) {
        nativeSetSupportRosterVersion(z);
    }
}
