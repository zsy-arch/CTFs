package com.hyphenate.chat;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.adapter.EMAError;
import com.hyphenate.chat.adapter.EMAGroup;
import com.hyphenate.chat.adapter.EMAGroupManager;
import com.hyphenate.chat.adapter.EMAGroupManagerListener;
import com.hyphenate.chat.adapter.EMAGroupSetting;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.tencent.open.wpa.WPA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class EMGroupManager {
    private static String TAG = WPA.CHAT_TYPE_GROUP;
    EMAGroupManager emaObject;
    EMClient mClient;
    EMAGroupManagerListener listenerImpl = new EMAGroupManagerListener() { // from class: com.hyphenate.chat.EMGroupManager.1
        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onAutoAcceptInvitationFromGroup(EMAGroup eMAGroup, String str, String str2) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onAutoAcceptInvitationFromGroup(eMAGroup.groupId(), str, str2);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onLeaveGroup(EMAGroup eMAGroup, int i) {
            EMClient.getInstance().chatManager().caches.remove(eMAGroup.groupId());
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    if (i == EMAGroup.EMGroupLeaveReason.BE_KICKED.ordinal()) {
                        eMGroupChangeListener.onUserRemoved(eMAGroup.groupId(), eMAGroup.groupSubject());
                    } else {
                        eMGroupChangeListener.onGroupDestroyed(eMAGroup.groupId(), eMAGroup.groupSubject());
                    }
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveAcceptionFromGroup(EMAGroup eMAGroup) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onRequestToJoinAccepted(eMAGroup.groupId(), eMAGroup.groupSubject(), eMAGroup.getOwner());
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveInviteAcceptionFromGroup(EMAGroup eMAGroup, String str) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onInvitationAccepted(eMAGroup.groupId(), str, "");
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveInviteDeclineFromGroup(EMAGroup eMAGroup, String str, String str2) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onInvitationDeclined(eMAGroup.groupId(), str, "");
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveInviteFromGroup(String str, String str2, String str3) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onInvitationReceived(str, "", str2, str3);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveJoinGroupApplication(EMAGroup eMAGroup, String str, String str2) {
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onRequestToJoinReceived(eMAGroup.groupId(), eMAGroup.groupSubject(), str, str2);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onReceiveRejectionFromGroup(String str, String str2) {
            EMGroup group = EMGroupManager.this.getGroup(str);
            String groupSubject = group == null ? "" : group.groupSubject();
            String owner = group == null ? "" : group.getOwner();
            synchronized (EMGroupManager.this.groupChangeListeners) {
                for (EMGroupChangeListener eMGroupChangeListener : EMGroupManager.this.groupChangeListeners) {
                    eMGroupChangeListener.onRequestToJoinDeclined(str, groupSubject, owner, str2);
                }
            }
        }

        @Override // com.hyphenate.chat.adapter.EMAGroupManagerListener, com.hyphenate.chat.adapter.EMAGroupManagerListenerInterface
        public void onUpdateMyGroupList(List<EMAGroup> list) {
        }
    };
    List<EMGroupChangeListener> groupChangeListeners = Collections.synchronizedList(new ArrayList());

    /* loaded from: classes2.dex */
    public static class EMGroupOptions {
        public int maxUsers = 200;
        public EMGroupStyle style = EMGroupStyle.EMGroupStylePrivateOnlyOwnerInvite;
    }

    /* loaded from: classes2.dex */
    public enum EMGroupStyle {
        EMGroupStylePrivateOnlyOwnerInvite,
        EMGroupStylePrivateMemberCanInvite,
        EMGroupStylePublicJoinNeedApproval,
        EMGroupStylePublicOpenJoin
    }

    public EMGroupManager(EMClient eMClient, EMAGroupManager eMAGroupManager) {
        this.emaObject = eMAGroupManager;
        this.mClient = eMClient;
        this.emaObject.addListener(this.listenerImpl);
    }

    private EMGroup createGroup(int i, String str, String str2, String[] strArr, int i2, String str3) throws HyphenateException {
        EMAGroupSetting eMAGroupSetting = new EMAGroupSetting(i, i2);
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        EMAError eMAError = new EMAError();
        EMAGroup createGroup = this.emaObject.createGroup(str, str2, str3, eMAGroupSetting, arrayList, eMAError);
        handleError(eMAError);
        return new EMGroup(createGroup);
    }

    private void handleError(EMAError eMAError) throws HyphenateException {
        if (eMAError.errCode() != 0) {
            throw new HyphenateException(eMAError);
        }
    }

    public void acceptApplication(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.acceptJoinGroupApplication(str2, str, eMAError);
        handleError(eMAError);
    }

    public EMGroup acceptInvitation(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMAGroupManager eMAGroupManager = this.emaObject;
        if (str2 == null) {
            str2 = "";
        }
        EMAGroup acceptInvitationFromGroup = eMAGroupManager.acceptInvitationFromGroup(str, str2, eMAError);
        handleError(eMAError);
        return new EMGroup(acceptInvitationFromGroup);
    }

    public void addGroupChangeListener(EMGroupChangeListener eMGroupChangeListener) {
        EMLog.d(TAG, "add group change listener:" + eMGroupChangeListener.getClass().getName());
        if (!this.groupChangeListeners.contains(eMGroupChangeListener)) {
            this.groupChangeListeners.add(eMGroupChangeListener);
        }
    }

    public void addUsersToGroup(String str, String[] strArr) throws HyphenateException {
        EMAError eMAError = new EMAError();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        this.emaObject.addGroupMembers(str, arrayList, "welcome", eMAError);
        handleError(eMAError);
    }

    public void applyJoinToGroup(String str, String str2) throws HyphenateException {
        String currentUser = this.mClient.getCurrentUser();
        EMAError eMAError = new EMAError();
        this.emaObject.applyJoinPublicGroup(str, currentUser, str2, eMAError);
        handleError(eMAError);
    }

    public void asyncAcceptApplication(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.acceptApplication(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncAcceptInvitation(final String str, final String str2, final EMValueCallBack<EMGroup> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.acceptInvitation(str, str2));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncAddUsersToGroup(final String str, final String[] strArr, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.addUsersToGroup(str, strArr);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncApplyJoinToGroup(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.19
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.applyJoinToGroup(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncBlockGroupMessage(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.20
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.blockGroupMessage(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncBlockUser(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.22
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.blockUser(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncChangeGroupDescription(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.changeGroupDescription(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncChangeGroupName(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.changeGroupName(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncCreateGroup(final String str, final String str2, final String[] strArr, final String str3, final EMGroupOptions eMGroupOptions, final EMValueCallBack<EMGroup> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.createGroup(str, str2, strArr, str3, eMGroupOptions));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncDeclineApplication(final String str, final String str2, final String str3, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.declineApplication(str, str2, str3);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncDeclineInvitation(final String str, final String str2, final String str3, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.declineInvitation(str, str2, str3);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncDestroyGroup(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.destroyGroup(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncGetBlockedUsers(final String str, final EMValueCallBack<List<String>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.getBlockedUsers(str));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncGetGroupFromServer(final String str, final EMValueCallBack<EMGroup> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.getGroupFromServer(str));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    void asyncGetGroupsFromServer(final EMValueCallBack<List<EMGroup>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.getGroupsFromServer());
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncGetJoinedGroupsFromServer(final EMValueCallBack<List<EMGroup>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.getJoinedGroupsFromServer());
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncGetPublicGroupsFromServer(final int i, final String str, final EMValueCallBack<EMCursorResult<EMGroupInfo>> eMValueCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    eMValueCallBack.onSuccess(EMGroupManager.this.getPublicGroupsFromServer(i, str));
                } catch (HyphenateException e) {
                    eMValueCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncInviteUser(final String str, final String[] strArr, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.inviteUser(str, strArr, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncJoinGroup(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.joinGroup(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncLeaveGroup(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.leaveGroup(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncRemoveUserFromGroup(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.removeUserFromGroup(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncUnblockGroupMessage(final String str, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.21
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.unblockGroupMessage(str);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void asyncUnblockUser(final String str, final String str2, final EMCallBack eMCallBack) {
        EMClient.getInstance().execute(new Runnable() { // from class: com.hyphenate.chat.EMGroupManager.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMGroupManager.this.unblockUser(str, str2);
                    eMCallBack.onSuccess();
                } catch (HyphenateException e) {
                    eMCallBack.onError(e.getErrorCode(), e.getDescription());
                }
            }
        });
    }

    public void blockGroupMessage(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.blockGroupMessage(str, eMAError);
        handleError(eMAError);
    }

    public void blockUser(String str, String str2) throws HyphenateException {
        EMLog.d(TAG, "block user for groupid:" + str + " username:" + str2);
        EMAError eMAError = new EMAError();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        this.emaObject.blockGroupMembers(str, arrayList, eMAError, "");
        handleError(eMAError);
    }

    public void changeGroupDescription(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.changeGroupDescription(str, str2, eMAError);
        handleError(eMAError);
    }

    public void changeGroupName(String str, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.changeGroupSubject(str, str2, eMAError);
        handleError(eMAError);
    }

    public EMGroup createGroup(String str, String str2, String[] strArr, String str3, EMGroupOptions eMGroupOptions) throws HyphenateException {
        int i = 0;
        switch (eMGroupOptions.style) {
            case EMGroupStylePrivateMemberCanInvite:
                i = 1;
                break;
            case EMGroupStylePublicJoinNeedApproval:
                i = 2;
                break;
            case EMGroupStylePublicOpenJoin:
                i = 3;
                break;
        }
        return createGroup(i, str, str2, strArr, eMGroupOptions.maxUsers, str3);
    }

    public void declineApplication(String str, String str2, String str3) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.declineJoinGroupApplication(str2, str, str3, eMAError);
        handleError(eMAError);
    }

    public void declineInvitation(String str, String str2, String str3) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMAGroupManager eMAGroupManager = this.emaObject;
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        eMAGroupManager.declineInvitationFromGroup(str, str2, str3, eMAError);
        handleError(eMAError);
    }

    public void destroyGroup(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.destroyGroup(str, eMAError);
        EMClient.getInstance().chatManager().caches.remove(str);
        handleError(eMAError);
    }

    public List<EMGroup> getAllGroups() {
        List<EMAGroup> allMyGroups = this.emaObject.allMyGroups(new EMAError());
        ArrayList arrayList = new ArrayList();
        for (EMAGroup eMAGroup : allMyGroups) {
            arrayList.add(new EMGroup(eMAGroup));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<String> getBlockedUsers(String str) throws HyphenateException {
        EMLog.d(TAG, "get blocked users for group:" + str);
        EMAError eMAError = new EMAError();
        List<String> fetchGroupBans = this.emaObject.fetchGroupBans(str, eMAError);
        handleError(eMAError);
        return fetchGroupBans;
    }

    public EMGroup getGroup(String str) {
        for (EMAGroup eMAGroup : this.emaObject.allMyGroups(new EMAError())) {
            if (eMAGroup.groupId().equals(str)) {
                return new EMGroup(eMAGroup);
            }
        }
        return null;
    }

    public EMGroup getGroupFromServer(String str) throws HyphenateException {
        if (str == null || str.isEmpty()) {
            throw new HyphenateException(600, "group id is null or empty");
        }
        EMAError eMAError = new EMAError();
        EMAGroup fetchGroupSpecification = this.emaObject.fetchGroupSpecification(str, eMAError, true);
        handleError(eMAError);
        return new EMGroup(fetchGroupSpecification);
    }

    synchronized List<EMGroup> getGroupsFromServer() throws HyphenateException {
        ArrayList arrayList;
        EMAError eMAError = new EMAError();
        List<EMAGroup> fetchAllMyGroups = this.emaObject.fetchAllMyGroups(eMAError);
        handleError(eMAError);
        arrayList = new ArrayList();
        for (EMAGroup eMAGroup : fetchAllMyGroups) {
            arrayList.add(new EMGroup(eMAGroup));
        }
        return arrayList;
    }

    public synchronized List<EMGroup> getJoinedGroupsFromServer() throws HyphenateException {
        return getGroupsFromServer();
    }

    public EMCursorResult<EMGroupInfo> getPublicGroupsFromServer(int i, String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMCursorResult<EMGroupInfo> fetchPublicGroupsWithCursor = this.emaObject.fetchPublicGroupsWithCursor(str, i, eMAError);
        handleError(eMAError);
        return fetchPublicGroupsWithCursor;
    }

    public void inviteUser(String str, String[] strArr, String str2) throws HyphenateException {
        EMAError eMAError = new EMAError();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        this.emaObject.addGroupMembers(str, arrayList, str2, eMAError);
        handleError(eMAError);
    }

    public void joinGroup(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        EMAGroup fetchGroupSpecification = this.emaObject.fetchGroupSpecification(str, eMAError, false);
        handleError(eMAError);
        if (fetchGroupSpecification.groupSetting() == null) {
            throw new HyphenateException();
        } else if (fetchGroupSpecification.groupSetting().style() == 3) {
            this.emaObject.joinPublicGroup(str, eMAError);
            handleError(eMAError);
        } else if (fetchGroupSpecification.groupSetting().style() == 2) {
            this.emaObject.applyJoinPublicGroup(str, this.mClient.getCurrentUser(), "hello", eMAError);
            handleError(eMAError);
        }
    }

    public void leaveGroup(String str) throws HyphenateException {
        EMAError eMAError = new EMAError();
        this.emaObject.leaveGroup(str, eMAError);
        EMClient.getInstance().chatManager().caches.remove(str);
        handleError(eMAError);
    }

    public synchronized void loadAllGroups() {
        this.emaObject.loadAllMyGroupsFromDB();
    }

    public void onLogout() {
    }

    public void removeGroupChangeListener(EMGroupChangeListener eMGroupChangeListener) {
        EMLog.d(TAG, "remove group change listener:" + eMGroupChangeListener.getClass().getName());
        this.groupChangeListeners.remove(eMGroupChangeListener);
    }

    public void removeUserFromGroup(String str, String str2) throws HyphenateException {
        ArrayList arrayList = new ArrayList();
        EMAError eMAError = new EMAError();
        arrayList.add(str2);
        this.emaObject.removeGroupMembers(str, arrayList, eMAError);
        handleError(eMAError);
        this.emaObject.fetchGroupSpecification(str, eMAError, true);
        handleError(eMAError);
    }

    public void setAutoAcceptInvitation(boolean z) {
    }

    public void unblockGroupMessage(String str) throws HyphenateException {
        EMLog.d(TAG, "try to unblock group msg:" + str);
        EMAError eMAError = new EMAError();
        this.emaObject.unblockGroupMessage(str, eMAError);
        handleError(eMAError);
    }

    public void unblockUser(String str, String str2) throws HyphenateException {
        EMLog.d(TAG, "unblock user groupid:" + str + " username:" + str2);
        EMAError eMAError = new EMAError();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        this.emaObject.unblockGroupMembers(str, arrayList, eMAError);
        handleError(eMAError);
    }
}
