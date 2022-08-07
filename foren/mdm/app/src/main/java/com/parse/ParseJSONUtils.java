package com.parse;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseJSONUtils {
    ParseJSONUtils() {
    }

    public static JSONObject create(JSONObject copyFrom, Collection<String> excludes) {
        JSONObject json = new JSONObject();
        Iterator<String> iterator = copyFrom.keys();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (!excludes.contains(name)) {
                try {
                    json.put(name, copyFrom.opt(name));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return json;
    }

    public static Iterable<String> keys(final JSONObject object) {
        return new Iterable<String>() { // from class: com.parse.ParseJSONUtils.1
            @Override // java.lang.Iterable
            public Iterator<String> iterator() {
                return object.keys();
            }
        };
    }

    public static int getInt(JSONObject object, List<String> keys) throws JSONException {
        for (String key : keys) {
            try {
                return object.getInt(key);
            } catch (JSONException e) {
            }
        }
        throw new JSONException("No value for " + keys);
    }
}
