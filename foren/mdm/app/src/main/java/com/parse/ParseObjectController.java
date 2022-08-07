package com.parse;

import bolts.Task;
import com.parse.ParseObject;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ParseObjectController {
    List<Task<Void>> deleteAllAsync(List<ParseObject.State> list, String str);

    Task<Void> deleteAsync(ParseObject.State state, String str);

    Task<ParseObject.State> fetchAsync(ParseObject.State state, String str, ParseDecoder parseDecoder);

    List<Task<ParseObject.State>> saveAllAsync(List<ParseObject.State> list, List<ParseOperationSet> list2, String str, List<ParseDecoder> list3);

    Task<ParseObject.State> saveAsync(ParseObject.State state, ParseOperationSet parseOperationSet, String str, ParseDecoder parseDecoder);
}
