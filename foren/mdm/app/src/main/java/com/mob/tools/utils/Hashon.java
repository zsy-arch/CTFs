package com.mob.tools.utils;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.mob.tools.MobLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Hashon {
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type byte[] to java.lang.Object for r15v18 byte[]
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:105)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:357)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:143)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:92)
        */
    private java.util.ArrayList<?> arrayToList(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.Hashon.arrayToList(java.lang.Object):java.util.ArrayList");
    }

    private String format(String sepStr, ArrayList<Object> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("[\n");
        String mySepStr = sepStr + "\t";
        int i = 0;
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Object value = it.next();
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(mySepStr);
            if (value instanceof HashMap) {
                sb.append(format(mySepStr, (HashMap) value));
            } else if (value instanceof ArrayList) {
                sb.append(format(mySepStr, (ArrayList) value));
            } else if (value instanceof String) {
                sb.append('\"').append(value).append('\"');
            } else {
                sb.append(value);
            }
            i++;
        }
        sb.append('\n').append(sepStr).append(']');
        return sb.toString();
    }

    private String format(String sepStr, HashMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        String mySepStr = sepStr + "\t";
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(mySepStr).append('\"').append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                sb.append(format(mySepStr, (HashMap) value));
            } else if (value instanceof ArrayList) {
                sb.append(format(mySepStr, (ArrayList) value));
            } else if (value instanceof String) {
                sb.append('\"').append(value).append('\"');
            } else {
                sb.append(value);
            }
            i++;
        }
        sb.append('\n').append(sepStr).append('}');
        return sb.toString();
    }

    private ArrayList<Object> fromJson(JSONArray array) throws JSONException {
        ArrayList<Object> list = new ArrayList<>();
        int size = array.length();
        for (int i = 0; i < size; i++) {
            Object value = array.opt(i);
            if (value instanceof JSONObject) {
                value = fromJson((JSONObject) value);
            } else if (value instanceof JSONArray) {
                value = fromJson((JSONArray) value);
            }
            list.add(value);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> HashMap<String, T> fromJson(JSONObject json) throws JSONException {
        HashMap<String, T> map = (HashMap<String, T>) new HashMap();
        Iterator<String> iKey = json.keys();
        while (iKey.hasNext()) {
            String key = iKey.next();
            Object value = json.opt(key);
            if (JSONObject.NULL.equals(value)) {
                value = null;
            }
            if (value != null) {
                if (value instanceof JSONObject) {
                    value = fromJson((JSONObject) value);
                } else if (value instanceof JSONArray) {
                    value = fromJson((JSONArray) value);
                }
                map.put(key, value);
            }
        }
        return map;
    }

    private JSONArray getJSONArray(ArrayList<Object> list) throws JSONException {
        JSONArray array = new JSONArray();
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Object value = it.next();
            if (value instanceof HashMap) {
                value = getJSONObject((HashMap) value);
            } else if (value instanceof ArrayList) {
                value = getJSONArray((ArrayList) value);
            }
            array.put(value);
        }
        return array;
    }

    private <T> JSONObject getJSONObject(HashMap<String, T> map) throws JSONException {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                value = getJSONObject((HashMap) value);
            } else if (value instanceof ArrayList) {
                value = getJSONArray((ArrayList) value);
            } else if (isBasicArray(value)) {
                value = getJSONArray(arrayToList(value));
            }
            json.put(entry.getKey(), value);
        }
        return json;
    }

    private boolean isBasicArray(Object value) {
        return (value instanceof byte[]) || (value instanceof short[]) || (value instanceof int[]) || (value instanceof long[]) || (value instanceof float[]) || (value instanceof double[]) || (value instanceof char[]) || (value instanceof boolean[]) || (value instanceof String[]);
    }

    public String format(String jsonStr) {
        try {
            return format("", fromJson(jsonStr));
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return "";
        }
    }

    public <T> String fromHashMap(HashMap<String, T> map) {
        try {
            JSONObject obj = getJSONObject(map);
            return obj == null ? "" : obj.toString();
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return "";
        }
    }

    public <T> HashMap<String, T> fromJson(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return new HashMap<>();
        }
        try {
            if (jsonStr.startsWith("[") && jsonStr.endsWith("]")) {
                jsonStr = "{\"fakelist\":" + jsonStr + h.d;
            }
            return fromJson(new JSONObject(jsonStr));
        } catch (Throwable t) {
            MobLog.getInstance().w(jsonStr, new Object[0]);
            MobLog.getInstance().w(t);
            return new HashMap<>();
        }
    }
}
