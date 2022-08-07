package com.parse;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseDeleteOperation implements ParseFieldOperation {
    private static final ParseDeleteOperation defaultInstance = new ParseDeleteOperation();

    public static ParseDeleteOperation getInstance() {
        return defaultInstance;
    }

    private ParseDeleteOperation() {
    }

    @Override // com.parse.ParseFieldOperation
    public JSONObject encode(ParseEncoder objectEncoder) throws JSONException {
        JSONObject output = new JSONObject();
        output.put("__op", "Delete");
        return output;
    }

    @Override // com.parse.ParseFieldOperation
    public ParseFieldOperation mergeWithPrevious(ParseFieldOperation previous) {
        return this;
    }

    @Override // com.parse.ParseFieldOperation
    public Object apply(Object oldValue, String key) {
        return null;
    }
}
