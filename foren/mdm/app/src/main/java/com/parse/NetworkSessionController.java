package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetworkSessionController implements ParseSessionController {
    private final ParseHttpClient client;
    private final ParseObjectCoder coder = ParseObjectCoder.get();

    public NetworkSessionController(ParseHttpClient client) {
        this.client = client;
    }

    @Override // com.parse.ParseSessionController
    public Task<ParseObject.State> getSessionAsync(String sessionToken) {
        return ParseRESTSessionCommand.getCurrentSessionCommand(sessionToken).executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseObject.State>() { // from class: com.parse.NetworkSessionController.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseObject.State then(Task<JSONObject> task) throws Exception {
                return ((ParseObject.State.Builder) NetworkSessionController.this.coder.decode(new ParseObject.State.Builder("_Session"), task.getResult(), ParseDecoder.get())).isComplete(true).build();
            }
        });
    }

    @Override // com.parse.ParseSessionController
    public Task<Void> revokeAsync(String sessionToken) {
        return ParseRESTSessionCommand.revoke(sessionToken).executeAsync(this.client).makeVoid();
    }

    @Override // com.parse.ParseSessionController
    public Task<ParseObject.State> upgradeToRevocable(String sessionToken) {
        return ParseRESTSessionCommand.upgradeToRevocableSessionCommand(sessionToken).executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseObject.State>() { // from class: com.parse.NetworkSessionController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseObject.State then(Task<JSONObject> task) throws Exception {
                return ((ParseObject.State.Builder) NetworkSessionController.this.coder.decode(new ParseObject.State.Builder("_Session"), task.getResult(), ParseDecoder.get())).isComplete(true).build();
            }
        });
    }
}
