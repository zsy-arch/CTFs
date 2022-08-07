package com.parse;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ParseACL {
    private static final String KEY_ROLE_PREFIX = "role:";
    private static final String PUBLIC_KEY = "*";
    private static final String UNRESOLVED_KEY = "*unresolved";
    private static final String UNRESOLVED_USER_JSON_KEY = "unresolvedUser";
    private final Map<String, Permissions> permissionsById;
    private boolean shared;
    private ParseUser unresolvedUser;

    /* loaded from: classes2.dex */
    public static class Permissions {
        private static final String READ_PERMISSION = "read";
        private static final String WRITE_PERMISSION = "write";
        private final boolean readPermission;
        private final boolean writePermission;

        Permissions(boolean readPermission, boolean write) {
            this.readPermission = readPermission;
            this.writePermission = write;
        }

        Permissions(Permissions permissions) {
            this.readPermission = permissions.readPermission;
            this.writePermission = permissions.writePermission;
        }

        JSONObject toJSONObject() {
            JSONObject json = new JSONObject();
            try {
                if (this.readPermission) {
                    json.put(READ_PERMISSION, true);
                }
                if (this.writePermission) {
                    json.put(WRITE_PERMISSION, true);
                }
                return json;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        boolean getReadPermission() {
            return this.readPermission;
        }

        boolean getWritePermission() {
            return this.writePermission;
        }

        static Permissions createPermissionsFromJSONObject(JSONObject object) {
            return new Permissions(object.optBoolean(READ_PERMISSION, false), object.optBoolean(WRITE_PERMISSION, false));
        }
    }

    private static ParseDefaultACLController getDefaultACLController() {
        return ParseCorePlugins.getInstance().getDefaultACLController();
    }

    public static void setDefaultACL(ParseACL acl, boolean withAccessForCurrentUser) {
        getDefaultACLController().set(acl, withAccessForCurrentUser);
    }

    public static ParseACL getDefaultACL() {
        return getDefaultACLController().get();
    }

    public ParseACL() {
        this.permissionsById = new HashMap();
    }

    public ParseACL(ParseACL acl) {
        this.permissionsById = new HashMap();
        for (String id : acl.permissionsById.keySet()) {
            this.permissionsById.put(id, new Permissions(acl.permissionsById.get(id)));
        }
        this.unresolvedUser = acl.unresolvedUser;
        if (this.unresolvedUser != null) {
            this.unresolvedUser.registerSaveListener(new UserResolutionListener(this));
        }
    }

    public ParseACL copy() {
        return new ParseACL(this);
    }

    public boolean isShared() {
        return this.shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public JSONObject toJSONObject(ParseEncoder objectEncoder) {
        JSONObject json = new JSONObject();
        try {
            for (String id : this.permissionsById.keySet()) {
                json.put(id, this.permissionsById.get(id).toJSONObject());
            }
            if (this.unresolvedUser != null) {
                json.put(UNRESOLVED_USER_JSON_KEY, objectEncoder.encode(this.unresolvedUser));
            }
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static ParseACL createACLFromJSONObject(JSONObject object, ParseDecoder decoder) {
        ParseACL acl = new ParseACL();
        for (String key : ParseJSONUtils.keys(object)) {
            if (key.equals(UNRESOLVED_USER_JSON_KEY)) {
                try {
                    acl.unresolvedUser = (ParseUser) decoder.decode(object.getJSONObject(key));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    acl.permissionsById.put(key, Permissions.createPermissionsFromJSONObject(object.getJSONObject(key)));
                } catch (JSONException e2) {
                    throw new RuntimeException("could not decode ACL: " + e2.getMessage());
                }
            }
        }
        return acl;
    }

    public ParseACL(ParseUser owner) {
        this();
        setReadAccess(owner, true);
        setWriteAccess(owner, true);
    }

    void resolveUser(ParseUser user) {
        if (user == this.unresolvedUser) {
            if (this.permissionsById.containsKey(UNRESOLVED_KEY)) {
                this.permissionsById.put(user.getObjectId(), this.permissionsById.get(UNRESOLVED_KEY));
                this.permissionsById.remove(UNRESOLVED_KEY);
            }
            this.unresolvedUser = null;
        }
    }

    public boolean hasUnresolvedUser() {
        return this.unresolvedUser != null;
    }

    public ParseUser getUnresolvedUser() {
        return this.unresolvedUser;
    }

    private void setPermissionsIfNonEmpty(String userId, boolean readPermission, boolean writePermission) {
        if (readPermission || writePermission) {
            this.permissionsById.put(userId, new Permissions(readPermission, writePermission));
        } else {
            this.permissionsById.remove(userId);
        }
    }

    public void setPublicReadAccess(boolean allowed) {
        setReadAccess("*", allowed);
    }

    public boolean getPublicReadAccess() {
        return getReadAccess("*");
    }

    public void setPublicWriteAccess(boolean allowed) {
        setWriteAccess("*", allowed);
    }

    public boolean getPublicWriteAccess() {
        return getWriteAccess("*");
    }

    public void setReadAccess(String userId, boolean allowed) {
        if (userId == null) {
            throw new IllegalArgumentException("cannot setReadAccess for null userId");
        }
        setPermissionsIfNonEmpty(userId, allowed, getWriteAccess(userId));
    }

    public boolean getReadAccess(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("cannot getReadAccess for null userId");
        }
        Permissions permissions = this.permissionsById.get(userId);
        return permissions != null && permissions.getReadPermission();
    }

    public void setWriteAccess(String userId, boolean allowed) {
        if (userId == null) {
            throw new IllegalArgumentException("cannot setWriteAccess for null userId");
        }
        setPermissionsIfNonEmpty(userId, getReadAccess(userId), allowed);
    }

    public boolean getWriteAccess(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("cannot getWriteAccess for null userId");
        }
        Permissions permissions = this.permissionsById.get(userId);
        return permissions != null && permissions.getWritePermission();
    }

    public void setReadAccess(ParseUser user, boolean allowed) {
        if (user.getObjectId() != null) {
            setReadAccess(user.getObjectId(), allowed);
        } else if (user.isLazy()) {
            setUnresolvedReadAccess(user, allowed);
        } else {
            throw new IllegalArgumentException("cannot setReadAccess for a user with null id");
        }
    }

    private void setUnresolvedReadAccess(ParseUser user, boolean allowed) {
        prepareUnresolvedUser(user);
        setReadAccess(UNRESOLVED_KEY, allowed);
    }

    private void setUnresolvedWriteAccess(ParseUser user, boolean allowed) {
        prepareUnresolvedUser(user);
        setWriteAccess(UNRESOLVED_KEY, allowed);
    }

    private void prepareUnresolvedUser(ParseUser user) {
        if (this.unresolvedUser != user) {
            this.permissionsById.remove(UNRESOLVED_KEY);
            this.unresolvedUser = user;
            user.registerSaveListener(new UserResolutionListener(this));
        }
    }

    public boolean getReadAccess(ParseUser user) {
        if (user == this.unresolvedUser) {
            return getReadAccess(UNRESOLVED_KEY);
        }
        if (user.isLazy()) {
            return false;
        }
        if (user.getObjectId() != null) {
            return getReadAccess(user.getObjectId());
        }
        throw new IllegalArgumentException("cannot getReadAccess for a user with null id");
    }

    public void setWriteAccess(ParseUser user, boolean allowed) {
        if (user.getObjectId() != null) {
            setWriteAccess(user.getObjectId(), allowed);
        } else if (user.isLazy()) {
            setUnresolvedWriteAccess(user, allowed);
        } else {
            throw new IllegalArgumentException("cannot setWriteAccess for a user with null id");
        }
    }

    public boolean getWriteAccess(ParseUser user) {
        if (user == this.unresolvedUser) {
            return getWriteAccess(UNRESOLVED_KEY);
        }
        if (user.isLazy()) {
            return false;
        }
        if (user.getObjectId() != null) {
            return getWriteAccess(user.getObjectId());
        }
        throw new IllegalArgumentException("cannot getWriteAccess for a user with null id");
    }

    public boolean getRoleReadAccess(String roleName) {
        return getReadAccess(KEY_ROLE_PREFIX + roleName);
    }

    public void setRoleReadAccess(String roleName, boolean allowed) {
        setReadAccess(KEY_ROLE_PREFIX + roleName, allowed);
    }

    public boolean getRoleWriteAccess(String roleName) {
        return getWriteAccess(KEY_ROLE_PREFIX + roleName);
    }

    public void setRoleWriteAccess(String roleName, boolean allowed) {
        setWriteAccess(KEY_ROLE_PREFIX + roleName, allowed);
    }

    private static void validateRoleState(ParseRole role) {
        if (role == null || role.getObjectId() == null) {
            throw new IllegalArgumentException("Roles must be saved to the server before they can be used in an ACL.");
        }
    }

    public boolean getRoleReadAccess(ParseRole role) {
        validateRoleState(role);
        return getRoleReadAccess(role.getName());
    }

    public void setRoleReadAccess(ParseRole role, boolean allowed) {
        validateRoleState(role);
        setRoleReadAccess(role.getName(), allowed);
    }

    public boolean getRoleWriteAccess(ParseRole role) {
        validateRoleState(role);
        return getRoleWriteAccess(role.getName());
    }

    public void setRoleWriteAccess(ParseRole role, boolean allowed) {
        validateRoleState(role);
        setRoleWriteAccess(role.getName(), allowed);
    }

    /* loaded from: classes2.dex */
    public static class UserResolutionListener implements GetCallback<ParseObject> {
        private final WeakReference<ParseACL> parent;

        public UserResolutionListener(ParseACL parent) {
            this.parent = new WeakReference<>(parent);
        }

        @Override // com.parse.GetCallback
        public void done(ParseObject object, ParseException e) {
            try {
                ParseACL parent = this.parent.get();
                if (parent != null) {
                    parent.resolveUser((ParseUser) object);
                }
            } finally {
                object.unregisterSaveListener(this);
            }
        }
    }

    Map<String, Permissions> getPermissionsById() {
        return this.permissionsById;
    }
}
