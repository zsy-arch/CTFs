package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@ParseClassName("_User")
/* loaded from: classes.dex */
public class ParseUser extends ParseObject {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "username";
    private static boolean autoUserEnabled;
    private boolean isCurrentUser = false;
    private static final String KEY_SESSION_TOKEN = "sessionToken";
    private static final String KEY_AUTH_DATA = "authData";
    private static final List<String> READ_ONLY_KEYS = Collections.unmodifiableList(Arrays.asList(KEY_SESSION_TOKEN, KEY_AUTH_DATA));
    private static final Object isAutoUserEnabledMutex = new Object();

    public static ParseQuery<ParseUser> getQuery() {
        return ParseQuery.getQuery(ParseUser.class);
    }

    static ParseUserController getUserController() {
        return ParseCorePlugins.getInstance().getUserController();
    }

    public static ParseCurrentUserController getCurrentUserController() {
        return ParseCorePlugins.getInstance().getCurrentUserController();
    }

    static ParseAuthenticationManager getAuthenticationManager() {
        return ParseCorePlugins.getInstance().getAuthenticationManager();
    }

    /* loaded from: classes2.dex */
    public static class State extends ParseObject.State {
        private final boolean isNew;

        /* loaded from: classes2.dex */
        public static class Builder extends ParseObject.State.Init<Builder> {
            private boolean isNew;

            public Builder() {
                super("_User");
            }

            Builder(State state) {
                super(state);
                this.isNew = state.isNew();
            }

            @Override // com.parse.ParseObject.State.Init
            public Builder self() {
                return this;
            }

            @Override // com.parse.ParseObject.State.Init
            public State build() {
                return new State(this);
            }

            @Override // com.parse.ParseObject.State.Init
            public Builder apply(ParseObject.State other) {
                isNew(((State) other).isNew());
                return (Builder) super.apply(other);
            }

            public Builder sessionToken(String sessionToken) {
                return put(ParseUser.KEY_SESSION_TOKEN, sessionToken);
            }

            public Builder authData(Map<String, Map<String, String>> authData) {
                return put(ParseUser.KEY_AUTH_DATA, authData);
            }

            public Builder putAuthData(String authType, Map<String, String> authData) {
                Map<String, Map<String, String>> newAuthData = (Map) this.serverData.get(ParseUser.KEY_AUTH_DATA);
                if (newAuthData == null) {
                    newAuthData = new HashMap<>();
                }
                newAuthData.put(authType, authData);
                this.serverData.put(ParseUser.KEY_AUTH_DATA, newAuthData);
                return this;
            }

            public Builder isNew(boolean isNew) {
                this.isNew = isNew;
                return this;
            }
        }

        private State(Builder builder) {
            super(builder);
            this.isNew = builder.isNew;
        }

        @Override // com.parse.ParseObject.State
        public Builder newBuilder() {
            return new Builder(this);
        }

        public String sessionToken() {
            return (String) get(ParseUser.KEY_SESSION_TOKEN);
        }

        public Map<String, Map<String, String>> authData() {
            Map<String, Map<String, String>> authData = (Map) get(ParseUser.KEY_AUTH_DATA);
            if (authData == null) {
                return new HashMap<>();
            }
            return authData;
        }

        public boolean isNew() {
            return this.isNew;
        }
    }

    @Override // com.parse.ParseObject
    boolean needsDefaultACL() {
        return false;
    }

    @Override // com.parse.ParseObject
    boolean isKeyMutable(String key) {
        return !READ_ONLY_KEYS.contains(key);
    }

    @Override // com.parse.ParseObject
    public State.Builder newStateBuilder(String className) {
        return new State.Builder();
    }

    @Override // com.parse.ParseObject
    public State getState() {
        return (State) super.getState();
    }

    public boolean isLazy() {
        boolean z;
        synchronized (this.mutex) {
            z = getObjectId() == null && ParseAnonymousUtils.isLinked(this);
        }
        return z;
    }

    public boolean isAuthenticated() {
        boolean z;
        synchronized (this.mutex) {
            ParseUser current = getCurrentUser();
            z = isLazy() || !(getState().sessionToken() == null || current == null || !getObjectId().equals(current.getObjectId()));
        }
        return z;
    }

    @Override // com.parse.ParseObject
    public void remove(String key) {
        if ("username".equals(key)) {
            throw new IllegalArgumentException("Can't remove the username key.");
        }
        super.remove(key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public JSONObject toRest(ParseObject.State state, List<ParseOperationSet> operationSetQueue, ParseEncoder objectEncoder) {
        List<ParseOperationSet> cleanOperationSetQueue = operationSetQueue;
        for (int i = 0; i < operationSetQueue.size(); i++) {
            ParseOperationSet operations = operationSetQueue.get(i);
            if (operations.containsKey("password")) {
                if (cleanOperationSetQueue == operationSetQueue) {
                    cleanOperationSetQueue = new LinkedList<>(operationSetQueue);
                }
                ParseOperationSet cleanOperations = new ParseOperationSet(operations);
                cleanOperations.remove("password");
                cleanOperationSetQueue.set(i, cleanOperations);
            }
        }
        return super.toRest(state, cleanOperationSetQueue, objectEncoder);
    }

    Task<Void> cleanUpAuthDataAsync() {
        ParseAuthenticationManager controller = getAuthenticationManager();
        synchronized (this.mutex) {
            Map<String, Map<String, String>> authData = getState().authData();
            if (authData.size() == 0) {
                return Task.forResult(null);
            }
            List<Task<Void>> tasks = new ArrayList<>();
            Iterator<Map.Entry<String, Map<String, String>>> i = authData.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, Map<String, String>> entry = i.next();
                if (entry.getValue() == null) {
                    i.remove();
                    tasks.add(controller.restoreAuthenticationAsync(entry.getKey(), null).makeVoid());
                }
            }
            setState(getState().newBuilder().authData(authData).build());
            return Task.whenAll(tasks);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public Task<Void> handleSaveResultAsync(ParseObject.State result, ParseOperationSet operationsBeforeSave) {
        if (result != null) {
            operationsBeforeSave.remove("password");
        }
        return super.handleSaveResultAsync(result, operationsBeforeSave);
    }

    @Override // com.parse.ParseObject
    void validateSaveEventually() throws ParseException {
        if (isDirty("password")) {
            throw new ParseException(-1, "Unable to saveEventually on a ParseUser with dirty password");
        }
    }

    public boolean isCurrentUser() {
        boolean z;
        synchronized (this.mutex) {
            z = this.isCurrentUser;
        }
        return z;
    }

    public void setIsCurrentUser(boolean isCurrentUser) {
        synchronized (this.mutex) {
            this.isCurrentUser = isCurrentUser;
        }
    }

    public String getSessionToken() {
        return getState().sessionToken();
    }

    public Task<Void> setSessionTokenInBackground(String newSessionToken) {
        Task<Void> saveCurrentUserAsync;
        synchronized (this.mutex) {
            State state = getState();
            if (newSessionToken.equals(state.sessionToken())) {
                saveCurrentUserAsync = Task.forResult(null);
            } else {
                setState(state.newBuilder().sessionToken(newSessionToken).build());
                saveCurrentUserAsync = saveCurrentUserAsync(this);
            }
        }
        return saveCurrentUserAsync;
    }

    Map<String, Map<String, String>> getAuthData() {
        Map<String, Map<String, String>> authData;
        synchronized (this.mutex) {
            authData = getMap(KEY_AUTH_DATA);
            if (authData == null) {
                authData = new HashMap<>();
            }
        }
        return authData;
    }

    public Map<String, String> getAuthData(String authType) {
        return getAuthData().get(authType);
    }

    public void putAuthData(String authType, Map<String, String> authData) {
        synchronized (this.mutex) {
            Map<String, Map<String, String>> newAuthData = getAuthData();
            newAuthData.put(authType, authData);
            performPut(KEY_AUTH_DATA, newAuthData);
        }
    }

    public void removeAuthData(String authType) {
        synchronized (this.mutex) {
            Map<String, Map<String, String>> newAuthData = getAuthData();
            newAuthData.remove(authType);
            performPut(KEY_AUTH_DATA, newAuthData);
        }
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    String getPassword() {
        return getString("password");
    }

    public void setEmail(String email) {
        put("email", email);
    }

    public String getEmail() {
        return getString("email");
    }

    public boolean isNew() {
        return getState().isNew();
    }

    @Override // com.parse.ParseObject
    public void put(String key, Object value) {
        synchronized (this.mutex) {
            if ("username".equals(key)) {
                stripAnonymity();
            }
            super.put(key, value);
        }
    }

    public void stripAnonymity() {
        synchronized (this.mutex) {
            if (ParseAnonymousUtils.isLinked(this)) {
                if (getObjectId() != null) {
                    putAuthData("anonymous", null);
                } else {
                    removeAuthData("anonymous");
                }
            }
        }
    }

    public void restoreAnonymity(Map<String, String> anonymousData) {
        synchronized (this.mutex) {
            if (anonymousData != null) {
                putAuthData("anonymous", anonymousData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public void validateSave() {
        ParseUser current;
        synchronized (this.mutex) {
            if (getObjectId() == null) {
                throw new IllegalArgumentException("Cannot save a ParseUser until it has been signed up. Call signUp first.");
            } else if (!isAuthenticated() && isDirty() && !isCurrentUser()) {
                if (Parse.isLocalDatastoreEnabled() || (current = getCurrentUser()) == null || !getObjectId().equals(current.getObjectId())) {
                    throw new IllegalArgumentException("Cannot save a ParseUser that is not authenticated.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public Task<Void> saveAsync(String sessionToken, Task<Void> toAwait) {
        return saveAsync(sessionToken, isLazy(), toAwait);
    }

    Task<Void> saveAsync(String sessionToken, boolean isLazy, Task<Void> toAwait) {
        Task<Void> task;
        if (isLazy) {
            task = resolveLazinessAsync(toAwait);
        } else {
            task = super.saveAsync(sessionToken, toAwait);
        }
        if (isCurrentUser()) {
            return task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.2
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    return ParseUser.this.cleanUpAuthDataAsync();
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.1
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    return ParseUser.saveCurrentUserAsync(ParseUser.this);
                }
            });
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public void setState(ParseObject.State newState) {
        if (isCurrentUser()) {
            State.Builder newStateBuilder = (State.Builder) newState.newBuilder();
            if (getSessionToken() != null && newState.get(KEY_SESSION_TOKEN) == null) {
                newStateBuilder.put(KEY_SESSION_TOKEN, getSessionToken());
            }
            if (getAuthData().size() > 0 && newState.get(KEY_AUTH_DATA) == null) {
                newStateBuilder.put(KEY_AUTH_DATA, getAuthData());
            }
            newState = newStateBuilder.build();
        }
        super.setState(newState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public void validateDelete() {
        synchronized (this.mutex) {
            super.validateDelete();
            if (!isAuthenticated() && isDirty()) {
                throw new IllegalArgumentException("Cannot delete a ParseUser that is not authenticated.");
            }
        }
    }

    @Override // com.parse.ParseObject
    public ParseUser fetch() throws ParseException {
        return (ParseUser) super.fetch();
    }

    @Override // com.parse.ParseObject
    public <T extends ParseObject> Task<T> fetchAsync(String sessionToken, Task<Void> toAwait) {
        if (isLazy()) {
            return Task.forResult(this);
        }
        Task<T> task = super.fetchAsync(sessionToken, toAwait);
        if (isCurrentUser()) {
            return task.onSuccessTask(new Continuation<T, Task<Void>>() { // from class: com.parse.ParseUser.5
                @Override // bolts.Continuation
                public Task<Void> then(Task<T> fetchAsyncTask) throws Exception {
                    return ParseUser.this.cleanUpAuthDataAsync();
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.4
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    return ParseUser.saveCurrentUserAsync(ParseUser.this);
                }
            }).onSuccess(new Continuation<Void, T>() { // from class: com.parse.ParseUser.3
                @Override // bolts.Continuation
                /* renamed from: then */
                public ParseObject then2(Task<Void> task2) throws Exception {
                    return ParseUser.this;
                }
            });
        }
        return task;
    }

    public Task<Void> signUpInBackground() {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.6
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseUser.this.signUpAsync(task);
            }
        });
    }

    Task<Void> signUpAsync(Task<Void> toAwait) {
        Task<Void> onSuccessTask;
        final ParseUser user = getCurrentUser();
        synchronized (this.mutex) {
            String sessionToken = user != null ? user.getSessionToken() : null;
            if (ParseTextUtils.isEmpty(getUsername())) {
                onSuccessTask = Task.forError(new IllegalArgumentException("Username cannot be missing or blank"));
            } else if (ParseTextUtils.isEmpty(getPassword())) {
                onSuccessTask = Task.forError(new IllegalArgumentException("Password cannot be missing or blank"));
            } else if (getObjectId() != null) {
                Map<String, Map<String, String>> authData = getAuthData();
                if (!authData.containsKey("anonymous") || authData.get("anonymous") != null) {
                    onSuccessTask = Task.forError(new IllegalArgumentException("Cannot sign up a user that has already signed up."));
                } else {
                    onSuccessTask = saveAsync(sessionToken, toAwait);
                }
            } else if (this.operationSetQueue.size() > 1) {
                onSuccessTask = Task.forError(new IllegalArgumentException("Cannot sign up a user that is already signing up."));
            } else if (user == null || !ParseAnonymousUtils.isLinked(user)) {
                onSuccessTask = toAwait.onSuccessTask(new AnonymousClass8(startSave(), sessionToken));
            } else if (this == user) {
                onSuccessTask = Task.forError(new IllegalArgumentException("Attempt to merge currentUser with itself."));
            } else {
                boolean isLazy = user.isLazy();
                final String oldUsername = user.getUsername();
                final String oldPassword = user.getPassword();
                final Map<String, String> anonymousData = user.getAuthData("anonymous");
                user.copyChangesFrom(this);
                user.setUsername(getUsername());
                user.setPassword(getPassword());
                revert();
                onSuccessTask = user.saveAsync(sessionToken, isLazy, toAwait).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.7
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        if (task.isCancelled() || task.isFaulted()) {
                            synchronized (user.mutex) {
                                if (oldUsername != null) {
                                    user.setUsername(oldUsername);
                                } else {
                                    user.revert("username");
                                }
                                if (oldPassword != null) {
                                    user.setPassword(oldPassword);
                                } else {
                                    user.revert("password");
                                }
                                user.restoreAnonymity(anonymousData);
                            }
                            return task;
                        }
                        user.revert("password");
                        ParseUser.this.revert("password");
                        ParseUser.this.mergeFromObject(user);
                        return ParseUser.saveCurrentUserAsync(ParseUser.this);
                    }
                });
            }
        }
        return onSuccessTask;
    }

    /* renamed from: com.parse.ParseUser$8 */
    /* loaded from: classes2.dex */
    public class AnonymousClass8 implements Continuation<Void, Task<Void>> {
        final /* synthetic */ ParseOperationSet val$operations;
        final /* synthetic */ String val$sessionToken;

        AnonymousClass8(ParseOperationSet parseOperationSet, String str) {
            ParseUser.this = r1;
            this.val$operations = parseOperationSet;
            this.val$sessionToken = str;
        }

        @Override // bolts.Continuation
        public Task<Void> then(Task<Void> task) throws Exception {
            return ParseUser.getUserController().signUpAsync(ParseUser.this.getState(), this.val$operations, this.val$sessionToken).continueWithTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseUser.8.1
                @Override // bolts.Continuation
                public Task<Void> then(final Task<State> signUpTask) throws Exception {
                    return ParseUser.this.handleSaveResultAsync(signUpTask.getResult(), AnonymousClass8.this.val$operations).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.8.1.1
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> task2) throws Exception {
                            return (signUpTask.isCancelled() || signUpTask.isFaulted()) ? signUpTask.makeVoid() : ParseUser.saveCurrentUserAsync(ParseUser.this);
                        }
                    });
                }
            });
        }
    }

    public void signUp() throws ParseException {
        ParseTaskUtils.wait(signUpInBackground());
    }

    public void signUpInBackground(SignUpCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(signUpInBackground(), callback);
    }

    public static Task<ParseUser> logInInBackground(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("Must specify a username for the user to log in with");
        } else if (password != null) {
            return getUserController().logInAsync(username, password).onSuccessTask(new Continuation<State, Task<ParseUser>>() { // from class: com.parse.ParseUser.9
                @Override // bolts.Continuation
                public Task<ParseUser> then(Task<State> task) throws Exception {
                    final ParseUser newCurrent = (ParseUser) ParseObject.from(task.getResult());
                    return ParseUser.saveCurrentUserAsync(newCurrent).onSuccess(new Continuation<Void, ParseUser>() { // from class: com.parse.ParseUser.9.1
                        @Override // bolts.Continuation
                        public ParseUser then(Task<Void> task2) throws Exception {
                            return newCurrent;
                        }
                    });
                }
            });
        } else {
            throw new IllegalArgumentException("Must specify a password for the user to log in with");
        }
    }

    public static ParseUser logIn(String username, String password) throws ParseException {
        return (ParseUser) ParseTaskUtils.wait(logInInBackground(username, password));
    }

    public static void logInInBackground(String username, String password, LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logInInBackground(username, password), callback);
    }

    public static Task<ParseUser> becomeInBackground(String sessionToken) {
        if (sessionToken != null) {
            return getUserController().getUserAsync(sessionToken).onSuccessTask(new Continuation<State, Task<ParseUser>>() { // from class: com.parse.ParseUser.10
                @Override // bolts.Continuation
                public Task<ParseUser> then(Task<State> task) throws Exception {
                    final ParseUser user = (ParseUser) ParseObject.from(task.getResult());
                    return ParseUser.saveCurrentUserAsync(user).onSuccess(new Continuation<Void, ParseUser>() { // from class: com.parse.ParseUser.10.1
                        @Override // bolts.Continuation
                        public ParseUser then(Task<Void> task2) throws Exception {
                            return user;
                        }
                    });
                }
            });
        }
        throw new IllegalArgumentException("Must specify a sessionToken for the user to log in with");
    }

    public static ParseUser become(String sessionToken) throws ParseException {
        return (ParseUser) ParseTaskUtils.wait(becomeInBackground(sessionToken));
    }

    public static void becomeInBackground(String sessionToken, LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(becomeInBackground(sessionToken), callback);
    }

    public static Task<ParseUser> getCurrentUserAsync() {
        return getCurrentUserController().getAsync();
    }

    public static ParseUser getCurrentUser() {
        return getCurrentUser(isAutomaticUserEnabled());
    }

    private static ParseUser getCurrentUser(boolean shouldAutoCreateUser) {
        try {
            return (ParseUser) ParseTaskUtils.wait(getCurrentUserController().getAsync(shouldAutoCreateUser));
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getCurrentSessionToken() {
        ParseUser current = getCurrentUser();
        if (current != null) {
            return current.getSessionToken();
        }
        return null;
    }

    public static Task<String> getCurrentSessionTokenAsync() {
        return getCurrentUserController().getCurrentSessionTokenAsync();
    }

    public static Task<Void> saveCurrentUserAsync(ParseUser user) {
        return getCurrentUserController().setAsync(user);
    }

    public static Task<Void> pinCurrentUserIfNeededAsync(ParseUser user) {
        if (Parse.isLocalDatastoreEnabled()) {
            return getCurrentUserController().setIfNeededAsync(user);
        }
        throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
    }

    public static Task<Void> logOutInBackground() {
        return getCurrentUserController().logOutAsync();
    }

    public static void logOutInBackground(LogOutCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logOutInBackground(), callback);
    }

    public static void logOut() {
        try {
            ParseTaskUtils.wait(logOutInBackground());
        } catch (ParseException e) {
        }
    }

    public Task<Void> logOutAsync() {
        return logOutAsync(true);
    }

    public Task<Void> logOutAsync(boolean revoke) {
        String oldSessionToken;
        ParseAuthenticationManager controller = getAuthenticationManager();
        List<Task<Void>> tasks = new ArrayList<>();
        synchronized (this.mutex) {
            oldSessionToken = getState().sessionToken();
            for (Map.Entry<String, Map<String, String>> entry : getAuthData().entrySet()) {
                tasks.add(controller.deauthenticateAsync(entry.getKey()));
            }
            State newState = getState().newBuilder().sessionToken(null).isNew(false).build();
            this.isCurrentUser = false;
            setState(newState);
        }
        if (revoke) {
            tasks.add(ParseSession.revokeAsync(oldSessionToken));
        }
        return Task.whenAll(tasks);
    }

    public static Task<Void> requestPasswordResetInBackground(String email) {
        return getUserController().requestPasswordResetAsync(email);
    }

    public static void requestPasswordReset(String email) throws ParseException {
        ParseTaskUtils.wait(requestPasswordResetInBackground(email));
    }

    public static void requestPasswordResetInBackground(String email, RequestPasswordResetCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(requestPasswordResetInBackground(email), callback);
    }

    @Override // com.parse.ParseObject
    public ParseUser fetchIfNeeded() throws ParseException {
        return (ParseUser) super.fetchIfNeeded();
    }

    public static void registerAuthenticationCallback(String authType, AuthenticationCallback callback) {
        getAuthenticationManager().register(authType, callback);
    }

    public static Task<ParseUser> logInWithInBackground(final String authType, final Map<String, String> authData) {
        if (authType == null) {
            throw new IllegalArgumentException("Invalid authType: " + ((Object) null));
        }
        return getCurrentUserController().getAsync(false).onSuccessTask(new AnonymousClass12(authType, authData, new Continuation<Void, Task<ParseUser>>() { // from class: com.parse.ParseUser.11
            @Override // bolts.Continuation
            public Task<ParseUser> then(Task<Void> task) throws Exception {
                return ParseUser.getUserController().logInAsync(authType, authData).onSuccessTask(new Continuation<State, Task<ParseUser>>() { // from class: com.parse.ParseUser.11.1
                    @Override // bolts.Continuation
                    public Task<ParseUser> then(Task<State> task2) throws Exception {
                        final ParseUser user = (ParseUser) ParseObject.from(task2.getResult());
                        return ParseUser.saveCurrentUserAsync(user).onSuccess(new Continuation<Void, ParseUser>() { // from class: com.parse.ParseUser.11.1.1
                            @Override // bolts.Continuation
                            public ParseUser then(Task<Void> task3) throws Exception {
                                return user;
                            }
                        });
                    }
                });
            }
        }));
    }

    /* renamed from: com.parse.ParseUser$12 */
    /* loaded from: classes2.dex */
    public static class AnonymousClass12 implements Continuation<ParseUser, Task<ParseUser>> {
        final /* synthetic */ Map val$authData;
        final /* synthetic */ String val$authType;
        final /* synthetic */ Continuation val$logInWithTask;

        AnonymousClass12(String str, Map map, Continuation continuation) {
            this.val$authType = str;
            this.val$authData = map;
            this.val$logInWithTask = continuation;
        }

        @Override // bolts.Continuation
        public Task<ParseUser> then(Task<ParseUser> task) throws Exception {
            final ParseUser user = task.getResult();
            if (user != null) {
                synchronized (user.mutex) {
                    if (ParseAnonymousUtils.isLinked(user)) {
                        if (user.isLazy()) {
                            final Map<String, String> oldAnonymousData = user.getAuthData("anonymous");
                            return user.taskQueue.enqueue(new Continuation<Void, Task<ParseUser>>() { // from class: com.parse.ParseUser.12.1
                                @Override // bolts.Continuation
                                public Task<ParseUser> then(Task<Void> toAwait) throws Exception {
                                    return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.12.1.2
                                        @Override // bolts.Continuation
                                        public Task<Void> then(Task<Void> task2) throws Exception {
                                            Task<Void> resolveLazinessAsync;
                                            synchronized (user.mutex) {
                                                user.stripAnonymity();
                                                user.putAuthData(AnonymousClass12.this.val$authType, AnonymousClass12.this.val$authData);
                                                resolveLazinessAsync = user.resolveLazinessAsync(task2);
                                            }
                                            return resolveLazinessAsync;
                                        }
                                    }).continueWithTask(new Continuation<Void, Task<ParseUser>>() { // from class: com.parse.ParseUser.12.1.1
                                        @Override // bolts.Continuation
                                        public Task<ParseUser> then(Task<Void> task2) throws Exception {
                                            Task<ParseUser> forResult;
                                            synchronized (user.mutex) {
                                                if (task2.isFaulted()) {
                                                    user.removeAuthData(AnonymousClass12.this.val$authType);
                                                    user.restoreAnonymity(oldAnonymousData);
                                                    forResult = Task.forError(task2.getError());
                                                } else if (task2.isCancelled()) {
                                                    forResult = Task.cancelled();
                                                } else {
                                                    forResult = Task.forResult(user);
                                                }
                                            }
                                            return forResult;
                                        }
                                    });
                                }
                            });
                        }
                        return user.linkWithInBackground(this.val$authType, this.val$authData).continueWithTask(new Continuation<Void, Task<ParseUser>>() { // from class: com.parse.ParseUser.12.2
                            @Override // bolts.Continuation
                            public Task<ParseUser> then(Task<Void> task2) throws Exception {
                                if (task2.isFaulted()) {
                                    Exception error = task2.getError();
                                    if ((error instanceof ParseException) && ((ParseException) error).getCode() == 208) {
                                        return Task.forResult(null).continueWithTask(AnonymousClass12.this.val$logInWithTask);
                                    }
                                }
                                if (task2.isCancelled()) {
                                    return Task.cancelled();
                                }
                                return Task.forResult(user);
                            }
                        });
                    }
                }
            }
            return Task.forResult(null).continueWithTask(this.val$logInWithTask);
        }
    }

    public boolean isLinked(String authType) {
        Map<String, Map<String, String>> authData = getAuthData();
        return authData.containsKey(authType) && authData.get(authType) != null;
    }

    public Task<Void> synchronizeAllAuthDataAsync() {
        synchronized (this.mutex) {
            if (!isCurrentUser()) {
                return Task.forResult(null);
            }
            Map<String, Map<String, String>> authData = getAuthData();
            List<Task<Void>> tasks = new ArrayList<>(authData.size());
            for (String authType : authData.keySet()) {
                tasks.add(synchronizeAuthDataAsync(authType));
            }
            return Task.whenAll(tasks);
        }
    }

    public Task<Void> synchronizeAuthDataAsync(String authType) {
        synchronized (this.mutex) {
            if (!isCurrentUser()) {
                return Task.forResult(null);
            }
            return synchronizeAuthDataAsync(getAuthenticationManager(), authType, getAuthData(authType));
        }
    }

    private Task<Void> synchronizeAuthDataAsync(ParseAuthenticationManager manager, final String authType, Map<String, String> authData) {
        return manager.restoreAuthenticationAsync(authType, authData).continueWithTask(new Continuation<Boolean, Task<Void>>() { // from class: com.parse.ParseUser.13
            @Override // bolts.Continuation
            public Task<Void> then(Task<Boolean> task) throws Exception {
                if (!(!task.isFaulted() && task.getResult().booleanValue())) {
                    return ParseUser.this.unlinkFromInBackground(authType);
                }
                return task.makeVoid();
            }
        });
    }

    public Task<Void> linkWithAsync(final String authType, Map<String, String> authData, Task<Void> toAwait, String sessionToken) {
        Task continueWithTask;
        synchronized (this.mutex) {
            boolean isLazy = isLazy();
            final Map<String, String> oldAnonymousData = getAuthData("anonymous");
            stripAnonymity();
            putAuthData(authType, authData);
            continueWithTask = saveAsync(sessionToken, isLazy, toAwait).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.14
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    synchronized (ParseUser.this.mutex) {
                        if (task.isFaulted() || task.isCancelled()) {
                            ParseUser.this.restoreAnonymity(oldAnonymousData);
                        } else {
                            task = ParseUser.this.synchronizeAuthDataAsync(authType);
                        }
                    }
                    return task;
                }
            });
        }
        return continueWithTask;
    }

    private Task<Void> linkWithAsync(final String authType, final Map<String, String> authData, final String sessionToken) {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.15
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseUser.this.linkWithAsync(authType, authData, task, sessionToken);
            }
        });
    }

    public Task<Void> linkWithInBackground(String authType, Map<String, String> authData) {
        if (authType != null) {
            return linkWithAsync(authType, authData, getSessionToken());
        }
        throw new IllegalArgumentException("Invalid authType: " + ((Object) null));
    }

    public Task<Void> unlinkFromInBackground(String authType) {
        Task<Void> task;
        if (authType == null) {
            return Task.forResult(null);
        }
        synchronized (this.mutex) {
            if (!getAuthData().containsKey(authType)) {
                task = Task.forResult(null);
            } else {
                putAuthData(authType, null);
                task = saveInBackground();
            }
        }
        return task;
    }

    Task<Void> resolveLazinessAsync(Task<Void> toAwait) {
        Task<Void> onSuccessTask;
        synchronized (this.mutex) {
            if (getAuthData().size() == 0) {
                onSuccessTask = signUpAsync(toAwait);
            } else {
                final ParseOperationSet operations = startSave();
                onSuccessTask = toAwait.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.16
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return ParseUser.getUserController().logInAsync(ParseUser.this.getState(), operations).onSuccessTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseUser.16.1
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<State> task2) throws Exception {
                                Task onSuccess;
                                final State result = task2.getResult();
                                if (!Parse.isLocalDatastoreEnabled() || result.isNew()) {
                                    onSuccess = ParseUser.this.handleSaveResultAsync(result, operations).onSuccess(new Continuation<Void, State>() { // from class: com.parse.ParseUser.16.1.1
                                        @Override // bolts.Continuation
                                        public State then(Task<Void> task3) throws Exception {
                                            return result;
                                        }
                                    });
                                } else {
                                    onSuccess = Task.forResult(result);
                                }
                                return onSuccess.onSuccessTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseUser.16.1.2
                                    @Override // bolts.Continuation
                                    public Task<Void> then(Task<State> task3) throws Exception {
                                        State result2 = task3.getResult();
                                        return !result2.isNew() ? ParseUser.saveCurrentUserAsync((ParseUser) ParseObject.from(result2)) : task3.makeVoid();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        return onSuccessTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseObject
    public <T extends ParseObject> Task<T> fetchFromLocalDatastoreAsync() {
        return isLazy() ? Task.forResult(this) : super.fetchFromLocalDatastoreAsync();
    }

    public static void enableAutomaticUser() {
        synchronized (isAutoUserEnabledMutex) {
            autoUserEnabled = true;
        }
    }

    static void disableAutomaticUser() {
        synchronized (isAutoUserEnabledMutex) {
            autoUserEnabled = false;
        }
    }

    public static boolean isAutomaticUserEnabled() {
        boolean z;
        synchronized (isAutoUserEnabledMutex) {
            z = autoUserEnabled;
        }
        return z;
    }

    public static Task<Void> enableRevocableSessionInBackground() {
        ParseCorePlugins.getInstance().registerUserController(new NetworkUserController(ParsePlugins.get().restClient(), true));
        return getCurrentUserController().getAsync(false).onSuccessTask(new Continuation<ParseUser, Task<Void>>() { // from class: com.parse.ParseUser.17
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParseUser> task) throws Exception {
                ParseUser user = task.getResult();
                return user == null ? Task.forResult(null) : user.upgradeToRevocableSessionAsync();
            }
        });
    }

    Task<Void> upgradeToRevocableSessionAsync() {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseUser.18
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParseUser.this.upgradeToRevocableSessionAsync(toAwait);
            }
        });
    }

    public Task<Void> upgradeToRevocableSessionAsync(Task<Void> toAwait) {
        final String sessionToken = getSessionToken();
        return toAwait.continueWithTask(new Continuation<Void, Task<String>>() { // from class: com.parse.ParseUser.20
            @Override // bolts.Continuation
            public Task<String> then(Task<Void> task) throws Exception {
                return ParseSession.upgradeToRevocableSessionAsync(sessionToken);
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseUser.19
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseUser.this.setSessionTokenInBackground(task.getResult());
            }
        });
    }
}
