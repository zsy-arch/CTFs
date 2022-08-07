package com.em.domain;

/* loaded from: classes.dex */
public class InviteMessage {
    private String from;
    private String groupId;
    private String groupInviter;
    private String groupName;
    private int id;
    private String reason;
    private InviteMesageStatus status;
    private long time;

    /* loaded from: classes.dex */
    public enum InviteMesageStatus {
        BEINVITEED,
        BEREFUSED,
        BEAGREED,
        BEAPPLYED,
        AGREED,
        REFUSED,
        GROUPINVITATION,
        GROUPINVITATION_ACCEPTED,
        GROUPINVITATION_DECLINED
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public InviteMesageStatus getStatus() {
        return this.status;
    }

    public void setStatus(InviteMesageStatus status) {
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupInviter(String inviter) {
        this.groupInviter = inviter;
    }

    public String getGroupInviter() {
        return this.groupInviter;
    }
}
