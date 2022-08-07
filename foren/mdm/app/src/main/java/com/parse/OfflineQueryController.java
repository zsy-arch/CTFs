package com.parse;

import bolts.Task;
import com.parse.ParseQuery;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class OfflineQueryController extends AbstractQueryController {
    private final ParseQueryController networkController;
    private final OfflineStore offlineStore;

    public OfflineQueryController(OfflineStore store, ParseQueryController network) {
        this.offlineStore = store;
        this.networkController = network;
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<List<T>> findAsync(ParseQuery.State<T> state, ParseUser user, Task<Void> cancellationToken) {
        return state.isFromLocalDatastore() ? this.offlineStore.findFromPinAsync(state.pinName(), state, user) : this.networkController.findAsync(state, user, cancellationToken);
    }

    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<Integer> countAsync(ParseQuery.State<T> state, ParseUser user, Task<Void> cancellationToken) {
        return state.isFromLocalDatastore() ? this.offlineStore.countFromPinAsync(state.pinName(), state, user) : this.networkController.countAsync(state, user, cancellationToken);
    }
}
