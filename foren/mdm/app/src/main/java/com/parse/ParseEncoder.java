package com.parse;

import android.util.Base64;
import com.parse.ParseQuery;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class ParseEncoder {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract JSONObject encodeRelatedObject(ParseObject parseObject);

    public static boolean isValidType(Object value) {
        return (value instanceof String) || (value instanceof Number) || (value instanceof Boolean) || (value instanceof Date) || (value instanceof List) || (value instanceof Map) || (value instanceof byte[]) || value == JSONObject.NULL || (value instanceof ParseObject) || (value instanceof ParseACL) || (value instanceof ParseFile) || (value instanceof ParseGeoPoint) || (value instanceof ParseRelation);
    }

    public Object encode(Object object) {
        try {
            if (object instanceof ParseObject) {
                return encodeRelatedObject((ParseObject) object);
            }
            if (object instanceof ParseQuery.State.Builder) {
                return encode(((ParseQuery.State.Builder) object).build());
            }
            if (object instanceof ParseQuery.State) {
                return ((ParseQuery.State) object).toJSON(this);
            }
            if (object instanceof Date) {
                return encodeDate((Date) object);
            }
            if (object instanceof byte[]) {
                JSONObject json = new JSONObject();
                json.put("__type", "Bytes");
                json.put("base64", Base64.encodeToString((byte[]) object, 2));
                return json;
            } else if (object instanceof ParseFile) {
                return ((ParseFile) object).encode();
            } else {
                if (object instanceof ParseGeoPoint) {
                    ParseGeoPoint point = (ParseGeoPoint) object;
                    JSONObject json2 = new JSONObject();
                    json2.put("__type", "GeoPoint");
                    json2.put("latitude", point.getLatitude());
                    json2.put("longitude", point.getLongitude());
                    return json2;
                } else if (object instanceof ParseACL) {
                    return ((ParseACL) object).toJSONObject(this);
                } else {
                    if (object instanceof Map) {
                        JSONObject json3 = new JSONObject();
                        for (Map.Entry<String, Object> pair : ((Map) object).entrySet()) {
                            json3.put(pair.getKey(), encode(pair.getValue()));
                        }
                        return json3;
                    } else if (object instanceof Collection) {
                        JSONArray array = new JSONArray();
                        for (Object item : (Collection) object) {
                            array.put(encode(item));
                        }
                        return array;
                    } else if (object instanceof ParseRelation) {
                        return ((ParseRelation) object).encodeToJSON(this);
                    } else {
                        if (object instanceof ParseFieldOperation) {
                            return ((ParseFieldOperation) object).encode(this);
                        }
                        if (object instanceof ParseQuery.RelationConstraint) {
                            return ((ParseQuery.RelationConstraint) object).encode(this);
                        }
                        if (object == null) {
                            return JSONObject.NULL;
                        }
                        if (isValidType(object)) {
                            return object;
                        }
                        throw new IllegalArgumentException("invalid type for ParseObject: " + object.getClass().toString());
                    }
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    protected JSONObject encodeDate(Date date) {
        JSONObject object = new JSONObject();
        String iso = ParseDateFormat.getInstance().format(date);
        try {
            object.put("__type", "Date");
            object.put("iso", iso);
            return object;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
