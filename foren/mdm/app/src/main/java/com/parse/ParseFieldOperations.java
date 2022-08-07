package com.parse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ParseFieldOperation.java */
/* loaded from: classes2.dex */
public final class ParseFieldOperations {
    private static Map<String, ParseFieldOperationFactory> opDecoderMap = new HashMap();

    /* compiled from: ParseFieldOperation.java */
    /* loaded from: classes2.dex */
    public interface ParseFieldOperationFactory {
        ParseFieldOperation decode(JSONObject jSONObject, ParseDecoder parseDecoder) throws JSONException;
    }

    private ParseFieldOperations() {
    }

    private static void registerDecoder(String opName, ParseFieldOperationFactory factory) {
        opDecoderMap.put(opName, factory);
    }

    public static void registerDefaultDecoders() {
        registerDecoder("Batch", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.1
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                ParseFieldOperation op = null;
                JSONArray ops = object.getJSONArray("ops");
                for (int i = 0; i < ops.length(); i++) {
                    op = ParseFieldOperations.decode(ops.getJSONObject(i), decoder).mergeWithPrevious(op);
                }
                return op;
            }
        });
        registerDecoder("Delete", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.2
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return ParseDeleteOperation.getInstance();
            }
        });
        registerDecoder("Increment", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.3
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseIncrementOperation((Number) decoder.decode(object.opt("amount")));
            }
        });
        registerDecoder("Add", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.4
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseAddOperation((Collection) decoder.decode(object.opt("objects")));
            }
        });
        registerDecoder("AddUnique", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.5
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseAddUniqueOperation((Collection) decoder.decode(object.opt("objects")));
            }
        });
        registerDecoder("Remove", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.6
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseRemoveOperation((Collection) decoder.decode(object.opt("objects")));
            }
        });
        registerDecoder("AddRelation", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.7
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseRelationOperation(new HashSet((List) decoder.decode(object.optJSONArray("objects"))), null);
            }
        });
        registerDecoder("RemoveRelation", new ParseFieldOperationFactory() { // from class: com.parse.ParseFieldOperations.8
            @Override // com.parse.ParseFieldOperations.ParseFieldOperationFactory
            public ParseFieldOperation decode(JSONObject object, ParseDecoder decoder) throws JSONException {
                return new ParseRelationOperation(null, new HashSet((List) decoder.decode(object.optJSONArray("objects"))));
            }
        });
    }

    public static ParseFieldOperation decode(JSONObject encoded, ParseDecoder decoder) throws JSONException {
        String op = encoded.optString("__op");
        ParseFieldOperationFactory factory = opDecoderMap.get(op);
        if (factory != null) {
            return factory.decode(encoded, decoder);
        }
        throw new RuntimeException("Unable to decode operation of type " + op);
    }

    public static ArrayList<Object> jsonArrayAsArrayList(JSONArray array) {
        ArrayList<Object> result = new ArrayList<>(array.length());
        for (int i = 0; i < array.length(); i++) {
            try {
                result.add(array.get(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
