package com.parse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ParseOperationSet extends HashMap<String, ParseFieldOperation> {
    private static final String REST_KEY_IS_SAVE_EVENTUALLY = "__isSaveEventually";
    private static final String REST_KEY_UUID = "__uuid";
    private static final long serialVersionUID = 1;
    private boolean isSaveEventually;
    private final String uuid;

    public ParseOperationSet() {
        this(UUID.randomUUID().toString());
    }

    public ParseOperationSet(ParseOperationSet operations) {
        super(operations);
        this.isSaveEventually = false;
        this.uuid = operations.getUUID();
        this.isSaveEventually = operations.isSaveEventually;
    }

    private ParseOperationSet(String uuid) {
        this.isSaveEventually = false;
        this.uuid = uuid;
    }

    public String getUUID() {
        return this.uuid;
    }

    public void setIsSaveEventually(boolean value) {
        this.isSaveEventually = value;
    }

    public boolean isSaveEventually() {
        return this.isSaveEventually;
    }

    public void mergeFrom(ParseOperationSet other) {
        ParseFieldOperation operation2;
        for (String key : other.keySet()) {
            ParseFieldOperation operation1 = other.get(key);
            ParseFieldOperation operation22 = get(key);
            if (operation22 != null) {
                operation2 = operation22.mergeWithPrevious(operation1);
            } else {
                operation2 = operation1;
            }
            put(key, operation2);
        }
    }

    public JSONObject toRest(ParseEncoder objectEncoder) throws JSONException {
        JSONObject operationSetJSON = new JSONObject();
        for (String key : keySet()) {
            operationSetJSON.put(key, ((ParseFieldOperation) get(key)).encode(objectEncoder));
        }
        operationSetJSON.put(REST_KEY_UUID, this.uuid);
        if (this.isSaveEventually) {
            operationSetJSON.put(REST_KEY_IS_SAVE_EVENTUALLY, true);
        }
        return operationSetJSON;
    }

    public static ParseOperationSet fromRest(JSONObject json, ParseDecoder decoder) throws JSONException {
        ParseFieldOperation fieldOp;
        Iterator<String> keysIter = json.keys();
        String[] keys = new String[json.length()];
        int index = 0;
        while (keysIter.hasNext()) {
            keys[index] = keysIter.next();
            index++;
        }
        JSONObject jsonCopy = new JSONObject(json, keys);
        String uuid = (String) jsonCopy.remove(REST_KEY_UUID);
        ParseOperationSet operationSet = uuid == null ? new ParseOperationSet() : new ParseOperationSet(uuid);
        boolean isSaveEventually = jsonCopy.optBoolean(REST_KEY_IS_SAVE_EVENTUALLY);
        jsonCopy.remove(REST_KEY_IS_SAVE_EVENTUALLY);
        operationSet.setIsSaveEventually(isSaveEventually);
        Iterator<?> opKeys = jsonCopy.keys();
        while (opKeys.hasNext()) {
            String opKey = opKeys.next();
            Object value = decoder.decode(jsonCopy.get(opKey));
            if (opKey.equals("ACL")) {
                value = ParseACL.createACLFromJSONObject(jsonCopy.getJSONObject(opKey), decoder);
            }
            if (value instanceof ParseFieldOperation) {
                fieldOp = (ParseFieldOperation) value;
            } else {
                fieldOp = new ParseSetOperation(value);
            }
            operationSet.put(opKey, fieldOp);
        }
        return operationSet;
    }
}
