package com.parse;

import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseDefaultACLController {
    ParseACL defaultACL;
    boolean defaultACLUsesCurrentUser;
    ParseACL defaultACLWithCurrentUser;
    WeakReference<ParseUser> lastCurrentUser;

    public void set(ParseACL acl, boolean withAccessForCurrentUser) {
        this.defaultACLWithCurrentUser = null;
        this.lastCurrentUser = null;
        if (acl != null) {
            ParseACL newDefaultACL = acl.copy();
            newDefaultACL.setShared(true);
            this.defaultACL = newDefaultACL;
            this.defaultACLUsesCurrentUser = withAccessForCurrentUser;
            return;
        }
        this.defaultACL = null;
    }

    public ParseACL get() {
        ParseUser currentUser;
        if (!this.defaultACLUsesCurrentUser || this.defaultACL == null || (currentUser = ParseUser.getCurrentUser()) == null) {
            return this.defaultACL;
        }
        if ((this.lastCurrentUser != null ? this.lastCurrentUser.get() : null) != currentUser) {
            ParseACL newDefaultACLWithCurrentUser = this.defaultACL.copy();
            newDefaultACLWithCurrentUser.setShared(true);
            newDefaultACLWithCurrentUser.setReadAccess(currentUser, true);
            newDefaultACLWithCurrentUser.setWriteAccess(currentUser, true);
            this.defaultACLWithCurrentUser = newDefaultACLWithCurrentUser;
            this.lastCurrentUser = new WeakReference<>(currentUser);
        }
        return this.defaultACLWithCurrentUser;
    }
}
