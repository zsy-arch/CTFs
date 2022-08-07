package com.easeui.ui;

import com.hyphenate.EMGroupChangeListener;

/* loaded from: classes.dex */
public abstract class EaseGroupRemoveListener implements EMGroupChangeListener {
    @Override // com.hyphenate.EMGroupChangeListener
    public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onRequestToJoinReceived(String groupId, String groupName, String applyer, String reason) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onRequestToJoinAccepted(String groupId, String groupName, String accepter) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onRequestToJoinDeclined(String groupId, String groupName, String decliner, String reason) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onInvitationAccepted(String groupId, String inviter, String reason) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onInvitationDeclined(String groupId, String invitee, String reason) {
    }

    @Override // com.hyphenate.EMGroupChangeListener
    public void onAutoAcceptInvitationFromGroup(String groupId, String inviter, String inviteMessage) {
    }
}
