package com.hyphenate.chat.adapter;

import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.chat.EMGroupInfo;
import java.util.List;

/* loaded from: classes2.dex */
public class EMAGroupManager extends EMABase {
    public EMAGroupManager() {
    }

    public EMAGroupManager(EMAGroupManager eMAGroupManager) {
        nativeInit(eMAGroupManager);
    }

    public EMAGroup acceptInvitationFromGroup(String str, String str2, EMAError eMAError) {
        return nativeacceptInvitationFromGroup(str, str2, eMAError);
    }

    public void acceptJoinGroupApplication(String str, String str2, EMAError eMAError) {
        nativeAcceptJoinGroupApplication(str, str2, eMAError);
    }

    public EMAGroup addGroupMembers(String str, List<String> list, String str2, EMAError eMAError) {
        return nativeAddGroupMembers(str, list, str2, eMAError);
    }

    public void addListener(EMAGroupManagerListener eMAGroupManagerListener) {
        nativeAddListener(eMAGroupManagerListener);
    }

    public List<EMAGroup> allMyGroups(EMAError eMAError) {
        return nativeAllMyGroups(eMAError);
    }

    public EMAGroup applyJoinPublicGroup(String str, String str2, String str3, EMAError eMAError) {
        return nativeApplyJoinPublicGroup(str, str2, str3, eMAError);
    }

    public EMAGroup blockGroupMembers(String str, List<String> list, EMAError eMAError, String str2) {
        return nativeBlockGroupMembers(str, list, eMAError, str2);
    }

    public EMAGroup blockGroupMessage(String str, EMAError eMAError) {
        return nativeBlockGroupMessage(str, eMAError);
    }

    public EMAGroup changeGroupDescription(String str, String str2, EMAError eMAError) {
        return nativeChangeGroupDescription(str, str2, eMAError);
    }

    public EMAGroup changeGroupSubject(String str, String str2, EMAError eMAError) {
        return nativeChangeGroupSubject(str, str2, eMAError);
    }

    public void clearListeners() {
        nativeClearListeners();
    }

    public EMAGroup createGroup(String str, String str2, String str3, EMAGroupSetting eMAGroupSetting, List<String> list, EMAError eMAError) {
        return nativeCreateGroup(str, str2, str3, eMAGroupSetting, list, eMAError);
    }

    public void declineInvitationFromGroup(String str, String str2, String str3, EMAError eMAError) {
        nativedeclineInvitationFromGroup(str, str2, str3, eMAError);
    }

    public void declineJoinGroupApplication(String str, String str2, String str3, EMAError eMAError) {
        nativeDeclineJoinGroupApplication(str, str2, str3, eMAError);
    }

    public EMAGroup destroyGroup(String str, EMAError eMAError) {
        return nativeDestroyGroup(str, eMAError);
    }

    public List<EMAGroup> fetchAllMyGroups(EMAError eMAError) {
        return nativeFetchAllMyGroups(eMAError);
    }

    public List<String> fetchGroupBans(String str, EMAError eMAError) {
        return nativeFetchGroupBans(str, eMAError);
    }

    public EMAGroup fetchGroupSpecification(String str, EMAError eMAError, boolean z) {
        return nativeFetchGroupSpecification(str, eMAError, z);
    }

    public EMCursorResult<EMGroupInfo> fetchPublicGroupsWithCursor(String str, int i, EMAError eMAError) {
        return nativeFetchPublicGroupsWithCursor(str, i, eMAError);
    }

    public EMAGroup joinPublicGroup(String str, EMAError eMAError) {
        return nativeJoinPublicGroup(str, eMAError);
    }

    public EMAGroup leaveGroup(String str, EMAError eMAError) {
        return nativeLeaveGroup(str, eMAError);
    }

    public void loadAllMyGroupsFromDB() {
        nativeLoadAllMyGroupsFromDB();
    }

    native void nativeAcceptJoinGroupApplication(String str, String str2, EMAError eMAError);

    native EMAGroup nativeAddGroupMembers(String str, List<String> list, String str2, EMAError eMAError);

    native void nativeAddListener(EMAGroupManagerListener eMAGroupManagerListener);

    native List<EMAGroup> nativeAllMyGroups(EMAError eMAError);

    native EMAGroup nativeApplyJoinPublicGroup(String str, String str2, String str3, EMAError eMAError);

    native EMAGroup nativeBlockGroupMembers(String str, List<String> list, EMAError eMAError, String str2);

    native EMAGroup nativeBlockGroupMessage(String str, EMAError eMAError);

    native EMAGroup nativeChangeGroupDescription(String str, String str2, EMAError eMAError);

    native EMAGroup nativeChangeGroupSubject(String str, String str2, EMAError eMAError);

    native void nativeClearListeners();

    native EMAGroup nativeCreateGroup(String str, String str2, String str3, EMAGroupSetting eMAGroupSetting, List<String> list, EMAError eMAError);

    native void nativeDeclineJoinGroupApplication(String str, String str2, String str3, EMAError eMAError);

    native EMAGroup nativeDestroyGroup(String str, EMAError eMAError);

    native List<EMAGroup> nativeFetchAllMyGroups(EMAError eMAError);

    native List<String> nativeFetchGroupBans(String str, EMAError eMAError);

    native EMAGroup nativeFetchGroupSpecification(String str, EMAError eMAError, boolean z);

    native EMCursorResult<EMGroupInfo> nativeFetchPublicGroupsWithCursor(String str, int i, EMAError eMAError);

    native void nativeInit(EMAGroupManager eMAGroupManager);

    native EMAGroup nativeJoinPublicGroup(String str, EMAError eMAError);

    native EMAGroup nativeLeaveGroup(String str, EMAError eMAError);

    native void nativeLoadAllMyGroupsFromDB();

    native EMAGroup nativeRemoveGroupMembers(String str, List<String> list, EMAError eMAError);

    native void nativeRemoveListener(EMAGroupManagerListener eMAGroupManagerListener);

    native EMAGroup nativeSearchPublicGroup(String str, EMAError eMAError);

    native EMAGroup nativeUnblockGroupMembers(String str, List<String> list, EMAError eMAError);

    native EMAGroup nativeUnblockGroupMessage(String str, EMAError eMAError);

    native EMAGroup nativeacceptInvitationFromGroup(String str, String str2, EMAError eMAError);

    native void nativedeclineInvitationFromGroup(String str, String str2, String str3, EMAError eMAError);

    public EMAGroup removeGroupMembers(String str, List<String> list, EMAError eMAError) {
        return nativeRemoveGroupMembers(str, list, eMAError);
    }

    public void removeListener(EMAGroupManagerListener eMAGroupManagerListener) {
        nativeRemoveListener(eMAGroupManagerListener);
    }

    public EMAGroup searchPublicGroup(String str, EMAError eMAError) {
        return nativeSearchPublicGroup(str, eMAError);
    }

    public EMAGroup unblockGroupMembers(String str, List<String> list, EMAError eMAError) {
        return nativeUnblockGroupMembers(str, list, eMAError);
    }

    public EMAGroup unblockGroupMessage(String str, EMAError eMAError) {
        return nativeUnblockGroupMessage(str, eMAError);
    }
}
