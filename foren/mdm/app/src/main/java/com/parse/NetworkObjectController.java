package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NetworkObjectController implements ParseObjectController {
    private ParseHttpClient client;
    private ParseObjectCoder coder = ParseObjectCoder.get();

    public NetworkObjectController(ParseHttpClient client) {
        this.client = client;
    }

    @Override // com.parse.ParseObjectController
    public Task<ParseObject.State> fetchAsync(final ParseObject.State state, String sessionToken, final ParseDecoder decoder) {
        ParseRESTCommand command = ParseRESTObjectCommand.getObjectCommand(state.objectId(), state.className(), sessionToken);
        command.enableRetrying();
        return command.executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseObject.State>() { // from class: com.parse.NetworkObjectController.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseObject.State then(Task<JSONObject> task) throws Exception {
                ParseObject.State.Init<?> builder = state.newBuilder().clear();
                return NetworkObjectController.this.coder.decode(builder, task.getResult(), decoder).isComplete(true).build();
            }
        });
    }

    @Override // com.parse.ParseObjectController
    public Task<ParseObject.State> saveAsync(final ParseObject.State state, ParseOperationSet operations, String sessionToken, final ParseDecoder decoder) {
        ParseRESTObjectCommand command = ParseRESTObjectCommand.saveObjectCommand(state, this.coder.encode(state, operations, PointerEncoder.get()), sessionToken);
        command.enableRetrying();
        return command.executeAsync(this.client).onSuccess(new Continuation<JSONObject, ParseObject.State>() { // from class: com.parse.NetworkObjectController.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public ParseObject.State then(Task<JSONObject> task) throws Exception {
                ParseObject.State.Init<?> builder = state.newBuilder().clear();
                return NetworkObjectController.this.coder.decode(builder, task.getResult(), decoder).isComplete(false).build();
            }
        });
    }

    @Override // com.parse.ParseObjectController
    public List<Task<ParseObject.State>> saveAllAsync(List<ParseObject.State> states, List<ParseOperationSet> operationsList, String sessionToken, List<ParseDecoder> decoders) {
        int batchSize = states.size();
        List<ParseRESTObjectCommand> commands = new ArrayList<>(batchSize);
        ParseEncoder encoder = PointerEncoder.get();
        for (int i = 0; i < batchSize; i++) {
            ParseObject.State state = states.get(i);
            commands.add(ParseRESTObjectCommand.saveObjectCommand(state, this.coder.encode(state, operationsList.get(i), encoder), sessionToken));
        }
        List<Task<JSONObject>> batchTasks = ParseRESTObjectBatchCommand.executeBatch(this.client, commands, sessionToken);
        ArrayList arrayList = new ArrayList(batchSize);
        for (int i2 = 0; i2 < batchSize; i2++) {
            final ParseObject.State state2 = states.get(i2);
            final ParseDecoder decoder = decoders.get(i2);
            arrayList.add(batchTasks.get(i2).onSuccess(new Continuation<JSONObject, ParseObject.State>() { // from class: com.parse.NetworkObjectController.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public ParseObject.State then(Task<JSONObject> task) throws Exception {
                    ParseObject.State.Init<?> builder = state2.newBuilder().clear();
                    return NetworkObjectController.this.coder.decode(builder, task.getResult(), decoder).isComplete(false).build();
                }
            }));
        }
        return arrayList;
    }

    @Override // com.parse.ParseObjectController
    public Task<Void> deleteAsync(ParseObject.State state, String sessionToken) {
        ParseRESTObjectCommand command = ParseRESTObjectCommand.deleteObjectCommand(state, sessionToken);
        command.enableRetrying();
        return command.executeAsync(this.client).makeVoid();
    }

    @Override // com.parse.ParseObjectController
    public List<Task<Void>> deleteAllAsync(List<ParseObject.State> states, String sessionToken) {
        int batchSize = states.size();
        List<ParseRESTObjectCommand> commands = new ArrayList<>(batchSize);
        for (int i = 0; i < batchSize; i++) {
            ParseRESTObjectCommand command = ParseRESTObjectCommand.deleteObjectCommand(states.get(i), sessionToken);
            command.enableRetrying();
            commands.add(command);
        }
        List<Task<JSONObject>> batchTasks = ParseRESTObjectBatchCommand.executeBatch(this.client, commands, sessionToken);
        List<Task<Void>> tasks = new ArrayList<>(batchSize);
        for (int i2 = 0; i2 < batchSize; i2++) {
            tasks.add(batchTasks.get(i2).makeVoid());
        }
        return tasks;
    }
}
