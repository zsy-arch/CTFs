package com.parse;

import bolts.Task;
import com.parse.ParseQuery;
import java.util.List;

/* loaded from: classes2.dex */
public interface ParseQueryController {
    <T extends ParseObject> Task<Integer> countAsync(ParseQuery.State<T> state, ParseUser parseUser, Task<Void> task);

    <T extends ParseObject> Task<List<T>> findAsync(ParseQuery.State<T> state, ParseUser parseUser, Task<Void> task);

    <T extends ParseObject> Task<T> getFirstAsync(ParseQuery.State<T> state, ParseUser parseUser, Task<Void> task);
}
