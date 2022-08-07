package com.parse;

import com.parse.ParseObject;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ParseObjectCurrentCoder extends ParseObjectCoder {
    private static final ParseObjectCurrentCoder INSTANCE = new ParseObjectCurrentCoder();
    private static final String KEY_CLASS_NAME = "classname";
    private static final String KEY_CREATED_AT = "createdAt";
    private static final String KEY_DATA = "data";
    private static final String KEY_OBJECT_ID = "objectId";
    private static final String KEY_OLD_CREATED_AT = "created_at";
    private static final String KEY_OLD_OBJECT_ID = "id";
    private static final String KEY_OLD_POINTERS = "pointers";
    private static final String KEY_OLD_UPDATED_AT = "updated_at";
    private static final String KEY_UPDATED_AT = "updatedAt";

    public static ParseObjectCurrentCoder get() {
        return INSTANCE;
    }

    @Override // com.parse.ParseObjectCoder
    public <T extends ParseObject.State> JSONObject encode(T state, ParseOperationSet operations, ParseEncoder encoder) {
        if (operations != null) {
            throw new IllegalArgumentException("Parameter ParseOperationSet is not null");
        }
        JSONObject objectJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        try {
            for (String key : state.keySet()) {
                dataJSON.put(key, encoder.encode(state.get(key)));
            }
            if (state.createdAt() > 0) {
                dataJSON.put(KEY_CREATED_AT, ParseDateFormat.getInstance().format(new Date(state.createdAt())));
            }
            if (state.updatedAt() > 0) {
                dataJSON.put(KEY_UPDATED_AT, ParseDateFormat.getInstance().format(new Date(state.updatedAt())));
            }
            if (state.objectId() != null) {
                dataJSON.put(KEY_OBJECT_ID, state.objectId());
            }
            objectJSON.put("data", dataJSON);
            objectJSON.put(KEY_CLASS_NAME, state.className());
            return objectJSON;
        } catch (JSONException e) {
            throw new RuntimeException("could not serialize object to JSON");
        }
    }

    @Override // com.parse.ParseObjectCoder
    public <T extends ParseObject.State.Init<?>> T decode(T builder, JSONObject json, ParseDecoder decoder) {
        String updatedAtString;
        String createdAtString;
        try {
            if (json.has("id")) {
                builder.objectId(json.getString("id"));
            }
            if (json.has(KEY_OLD_CREATED_AT) && (createdAtString = json.getString(KEY_OLD_CREATED_AT)) != null) {
                builder.createdAt(ParseImpreciseDateFormat.getInstance().parse(createdAtString));
            }
            if (json.has(KEY_OLD_UPDATED_AT) && (updatedAtString = json.getString(KEY_OLD_UPDATED_AT)) != null) {
                builder.updatedAt(ParseImpreciseDateFormat.getInstance().parse(updatedAtString));
            }
            if (json.has(KEY_OLD_POINTERS)) {
                JSONObject newPointers = json.getJSONObject(KEY_OLD_POINTERS);
                Iterator<?> keys = newPointers.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    JSONArray pointerArray = newPointers.getJSONArray(key);
                    builder.put(key, ParseObject.createWithoutData(pointerArray.optString(0), pointerArray.optString(1)));
                }
            }
            JSONObject data = json.optJSONObject("data");
            if (data != null) {
                Iterator<?> keys2 = data.keys();
                while (keys2.hasNext()) {
                    String key2 = keys2.next();
                    if (key2.equals(KEY_OBJECT_ID)) {
                        builder.objectId(data.getString(key2));
                    } else if (key2.equals(KEY_CREATED_AT)) {
                        builder.createdAt(ParseDateFormat.getInstance().parse(data.getString(key2)));
                    } else if (key2.equals(KEY_UPDATED_AT)) {
                        builder.updatedAt(ParseDateFormat.getInstance().parse(data.getString(key2)));
                    } else {
                        builder.put(key2, decoder.decode(data.get(key2)));
                    }
                }
            }
            return builder;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
