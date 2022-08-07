package com.hyphenate.chat.adapter;

import java.util.List;

/* loaded from: classes2.dex */
public class EMAGroup extends EMABase {
    public static final int EMGroupLeaveReason_BE_KICKED = 0;
    public static final int EMGroupLeaveReason_DESTROYED = 1;

    /* loaded from: classes2.dex */
    public enum EMGroupLeaveReason {
        BE_KICKED,
        DESTROYED
    }

    public EMAGroup() {
        nativeInit();
    }

    public EMAGroup(EMAGroup eMAGroup) {
        nativeInit(eMAGroup);
    }

    public void addMember(String str) {
        nativeaddMember(str);
    }

    public void finalize() throws Throwable {
        nativeFinalize();
        super.finalize();
    }

    public int getAffiliationsCount() {
        return nativeGroupMembersCount();
    }

    public String getDescription() {
        return nativeGroupDescription();
    }

    public List<String> getMembers() {
        return nativeGroupMembers();
    }

    public String getOwner() {
        return nativeGroupOwner();
    }

    public List<String> groupBans() {
        return nativeGroupBans();
    }

    public String groupId() {
        return nativeGroupId();
    }

    public EMAGroupSetting groupSetting() {
        return nativeGroupSetting();
    }

    public String groupSubject() {
        return nativegroupSubject();
    }

    public boolean isMsgBlocked() {
        return nativeIsMessageBlocked();
    }

    public boolean isPushEnabled() {
        return nativeIsPushEnabled();
    }

    native void nativeFinalize();

    native List<String> nativeGroupBans();

    native String nativeGroupDescription();

    native String nativeGroupId();

    native List<String> nativeGroupMembers();

    native int nativeGroupMembersCount();

    native String nativeGroupOwner();

    native EMAGroupSetting nativeGroupSetting();

    native void nativeInit();

    native void nativeInit(EMAGroup eMAGroup);

    native boolean nativeIsMessageBlocked();

    native boolean nativeIsPushEnabled();

    native void nativeaddMember(String str);

    native String nativegroupSubject();

    native void nativesetAffiliationsCount(int i);

    native void nativesetDescription(String str);

    native void nativesetGroupName(String str);

    native void nativesetMaxUsers(int i);

    native void nativesetMsgBlocked(boolean z);

    native void nativesetOwner(String str);

    public void setAffiliationsCount(int i) {
        nativesetAffiliationsCount(i);
    }

    public void setDescription(String str) {
        nativesetDescription(str);
    }

    public void setGroupName(String str) {
        nativesetGroupName(str);
    }

    public void setMaxUsers(int i) {
        nativesetMaxUsers(i);
    }

    public void setMsgBlocked(boolean z) {
        nativesetMsgBlocked(z);
    }

    public void setOwner(String str) {
        nativesetOwner(str);
    }
}
