package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import com.parse.ParseUser;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetworkUserController implements ParseUserController {
    private static final int STATUS_CODE_CREATED = 201;
    private final ParseHttpClient client;
    private final ParseObjectCoder coder;
    private final boolean revocableSession;

    public NetworkUserController(ParseHttpClient client) {
        this(client, false);
    }

    public NetworkUserController(ParseHttpClient client, boolean revocableSession) {
        this.client = client;
        this.coder = ParseObjectCoder.get();
        this.revocableSession = revocableSession;
    }

    @Override // com.parse.ParseUserController
    public Task<ParseUser.State> signUpAsync(ParseObject.State state, ParseOperationSet operations, String sessionToken) {
        return ParseRESTUserCommand.signUpUserCommand(this.coder.encode(state, operations, PointerEncoder.get()), sessionToken, this.revocableSession).executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseUser.State>() { // from class: com.parse.NetworkUserController.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseUser.State then(Task<JSONObject> task) throws Exception {
                return ((ParseUser.State.Builder) NetworkUserController.this.coder.decode(new ParseUser.State.Builder(), task.getResult(), ParseDecoder.get())).isComplete(false).isNew(true).build();
            }
        });
    }

    @Override // com.parse.ParseUserController
    public Task<ParseUser.State> logInAsync(String username, String password) {
        return ParseRESTUserCommand.logInUserCommand(username, password, this.revocableSession).executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseUser.State>() { // from class: com.parse.NetworkUserController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseUser.State then(Task<JSONObject> task) throws Exception {
                return ((ParseUser.State.Builder) NetworkUserController.this.coder.decode(new ParseUser.State.Builder(), task.getResult(), ParseDecoder.get())).isComplete(true).build();
            }
        });
    }

    @Override // com.parse.ParseUserController
    public Task<ParseUser.State> logInAsync(ParseUser.State state, ParseOperationSet operations) {
        final ParseRESTUserCommand command = ParseRESTUserCommand.serviceLogInUserCommand(this.coder.encode(state, operations, PointerEncoder.get()), state.sessionToken(), this.revocableSession);
        return command.executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseUser.State>() { // from class: com.parse.NetworkUserController.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseUser.State then(Task<JSONObject> task) throws Exception {
                boolean isNew;
                boolean isComplete = true;
                JSONObject result = task.getResult();
                if (command.getStatusCode() == 201) {
                    isNew = true;
                } else {
                    isNew = false;
                }
                if (isNew) {
                    isComplete = false;
                }
                return ((ParseUser.State.Builder) NetworkUserController.this.coder.decode(new ParseUser.State.Builder(), result, ParseDecoder.get())).isComplete(isComplete).isNew(isNew).build();
            }
        });
    }

    @Override // com.parse.ParseUserController
    public Task<ParseUser.State> logInAsync(final String authType, final Map<String, String> authData) {
        final ParseRESTUserCommand command = ParseRESTUserCommand.serviceLogInUserCommand(authType, authData, this.revocableSession);
        return command.executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseUser.State>() { // from class: com.parse.NetworkUserController.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseUser.State then(Task<JSONObject> task) throws Exception {
                boolean z = true;
                ParseUser.State.Builder isComplete = ((ParseUser.State.Builder) NetworkUserController.this.coder.decode(new ParseUser.State.Builder(), task.getResult(), ParseDecoder.get())).isComplete(true);
                if (command.getStatusCode() != 201) {
                    z = false;
                }
                return isComplete.isNew(z).putAuthData(authType, authData).build();
            }
        });
    }

    @Override // com.parse.ParseUserController
    public Task<ParseUser.State> getUserAsync(String sessionToken) {
        return ParseRESTUserCommand.getCurrentUserCommand(sessionToken).executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseUser.State>() { // from class: com.parse.NetworkUserController.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseUser.State then(Task<JSONObject> task) throws Exception {
                return ((ParseUser.State.Builder) NetworkUserController.this.coder.decode(new ParseUser.State.Builder(), task.getResult(), ParseDecoder.get())).isComplete(true).build();
            }
        });
    }

    @Override // com.parse.ParseUserController
    public Task<Void> requestPasswordResetAsync(String email) {
        return ParseRESTUserCommand.resetPasswordResetCommand(email).executeAsync(this.client).makeVoid();
    }
}
