package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseQuery;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractQueryController implements ParseQueryController {
    @Override // com.parse.ParseQueryController
    public <T extends ParseObject> Task<T> getFirstAsync(ParseQuery.State<T> state, ParseUser user, Task<Void> cancellationToken) {
        return (Task<T>) findAsync(state, user, cancellationToken).continueWith(new Continuation<List<T>, T>() { // from class: com.parse.AbstractQueryController.1
            @Override // bolts.Continuation
            public ParseObject then(Task task) throws Exception {
                if (task.isFaulted()) {
                    throw task.getError();
                } else if (task.getResult() != null && ((List) task.getResult()).size() > 0) {
                    return (ParseObject) ((List) task.getResult()).get(0);
                } else {
                    throw new ParseException(101, "no results found for query");
                }
            }
        });
    }
}
