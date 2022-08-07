package com.parse;

import org.json.JSONException;

/* loaded from: classes2.dex */
public interface ParseFieldOperation {
    Object apply(Object obj, String str);

    Object encode(ParseEncoder parseEncoder) throws JSONException;

    ParseFieldOperation mergeWithPrevious(ParseFieldOperation parseFieldOperation);
}
