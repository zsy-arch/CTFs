package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMAChatRoom;
import java.util.List;

/* loaded from: classes.dex */
public class EMChatRoom extends EMBase<EMAChatRoom> {
    private EMAChatRoom emaObject;

    public EMChatRoom() {
        this.emaObject = new EMAChatRoom();
    }

    public EMChatRoom(EMAChatRoom eMAChatRoom) {
        this.emaObject = new EMAChatRoom(eMAChatRoom);
    }

    public EMChatRoom(String str) {
        this.emaObject = new EMAChatRoom(str);
    }

    public EMChatRoom(String str, String str2) {
        this.emaObject = new EMAChatRoom(str);
    }

    synchronized void addMember(String str) {
    }

    @Deprecated
    public int getAffiliationsCount() {
        return this.emaObject.getAffiliationsCount();
    }

    public String getDescription() {
        return this.emaObject.getDescription();
    }

    public String getId() {
        return this.emaObject.getId();
    }

    public int getMaxUsers() {
        return this.emaObject.getMaxUsers();
    }

    public int getMemberCount() {
        return this.emaObject.getAffiliationsCount();
    }

    public List<String> getMemberList() {
        return this.emaObject.getMemberList();
    }

    public String getName() {
        return this.emaObject.getName();
    }

    public String getOwner() {
        return this.emaObject.getOwner();
    }

    synchronized void removeMember(String str) {
    }
}
