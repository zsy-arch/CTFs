package com.hyphenate.chat;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.adapter.EMAContactListener;
import com.hyphenate.chat.adapter.EMAContactManager;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.exceptions.HyphenateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class EMContactManager {
    EMAContactManager emaObject;
    EMClient mClient;
    private List<EMContactListener> contactListeners = Collections.synchronizedList(new ArrayList());
    private EMAContactListenerImpl contactImpl = new EMAContactListenerImpl();

    /* loaded from: classes2.dex */
    class EMAContactListenerImpl extends EMAContactListener {
        EMAContactListenerImpl() {
        }

        @Override // com.hyphenate.chat.adapter.EMAContactListener, com.hyphenate.chat.adapter.EMAContactListenerInterface
        public void onContactAdded(String str) {
            synchronized (EMContactManager.this.contactListeners) {
                for (EMContactListener eMContactListener : EMContactManager.this.contactListeners) {
                    eMContactListener.onContactAdded(str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAContactListener, com.hyphenate.chat.adapter.EMAContactListenerInterface
        public void onContactAgreed(String str) {
            synchronized (EMContactManager.this.contactListeners) {
                for (EMContactListener eMContactListener : EMContactManager.this.contactListeners) {
                    eMContactListener.onFriendRequestAccepted(str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAContactListener, com.hyphenate.chat.adapter.EMAContactListenerInterface
        public void onContactDeleted(String str) {
            EMClient.getInstance().chatManager().caches.remove(str);
            synchronized (EMContactManager.this.contactListeners) {
                for (EMContactListener eMContactListener : EMContactManager.this.contactListeners) {
                    eMContactListener.onContactDeleted(str);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAContactListener, com.hyphenate.chat.adapter.EMAContactListenerInterface
        public void onContactInvited(String str, String str2) {
            synchronized (EMContactManager.this.contactListeners) {
                for (EMContactListener eMContactListener : EMContactManager.this.contactListeners) {
                    eMContactListener.onContactInvited(str, str2);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAContactListener, com.hyphenate.chat.adapter.EMAContactListenerInterface
        public void onContactRefused(String str) {
            synchronized (EMContactManager.this.contactListeners) {
                for (EMContactListener eMContactListener : EMContactManager.this.contactListeners) {
                    eMContactListener.onFriendRequestDeclined(str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EMContactManager(EMClient eMClient, EMAContactManager eMAContactManager) {
        this.mClient = eMClient;
        this.emaObject = new EMAContactManager(eMAContactManager);
        this.emaObject.registerContactListener(this.contactImpl);
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            throw new HyphenateException(eMAError);
        }
    }

    public void acceptInvitation(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.acceptInvitation(str, eMAError);
        handleError(eMAError);
    }

    public void addContact(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.inviteContact(str, str2, eMAError);
        handleError(eMAError);
    }

    public void addUserToBlackList(String str, boolean z) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.addToBlackList(str, z, eMAError);
        handleError(eMAError);
    }

    public void asyncAcceptInvitation(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.acceptInvitation(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncDeclineInvitation(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.declineInvitation(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncSaveBlackList(final List<String> list, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.saveBlackList(list);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncAddContact(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.addContact(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncAddUserToBlackList(final String str, final boolean z, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.addUserToBlackList(str, z);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncDeleteContact(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.deleteContact(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncGetAllContactsFromServer(final EMValueCallBack<List<String>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMContactManager.this.getAllContactsFromServer());
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncGetBlackListFromServer(final EMValueCallBack<List<String>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMContactManager.this.getBlackListFromServer());
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void aysncRemoveUserFromBlackList(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMContactManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMContactManager.this.removeUserFromBlackList(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void declineInvitation(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.declineInvitation(str, eMAError);
        handleError(eMAError);
    }

    public void deleteContact(String str) throws HyphenateException {
        deleteContact(str, false);
    }

    public void deleteContact(String str, boolean z) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.deleteContact(str, eMAError, z);
        EMClient.getInstance().chatManager().caches.remove(str);
        handleError(eMAError);
    }

    public List<String> getAllContactsFromServer() throws HyphenateException {
        EMAError eMAError = new EMAError();
        List<String> contactsFromServer = this.emaObject.getContactsFromServer(eMAError);
        handleError(eMAError);
        return contactsFromServer;
    }

    public List<String> getBlackListFromServer() throws HyphenateException {
        EMAError eMAError = new EMAError();
        List<String> blackListFromServer = this.emaObject.getBlackListFromServer(eMAError);
        handleError(eMAError);
        return blackListFromServer;
    }

    public List<String> getBlackListUsernames() {
        return this.emaObject.getBlackListFromDB(new EMAError());
    }

    List<String> getContactsFromDB(EMAError eMAError) {
        return this.emaObject.getContactsFromDB(eMAError);
    }

    List<String> getContactsFromServer(EMAError eMAError) {
        return this.emaObject.getContactsFromServer(eMAError);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onLogout() {
    }

    public void removeContactListener(EMContactListener eMContactListener) {
        this.contactListeners.remove(eMContactListener);
    }

    public void removeUserFromBlackList(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.removeFromBlackList(str, eMAError);
        handleError(eMAError);
    }

    public void saveBlackList(List<String> list) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.saveBlackList(list, eMAError);
        handleError(eMAError);
    }

    public void setContactListener(EMContactListener eMContactListener) {
        if (!this.contactListeners.contains(eMContactListener)) {
            this.contactListeners.add(eMContactListener);
        }
    }
}
