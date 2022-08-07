package com.amap.api.col;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import com.autonavi.ae.gmap.style.StyleElement;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.amap.mapcore.Convert;
import com.autonavi.amap.mapcore.FileUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StyleParser.java */
/* loaded from: classes.dex */
public class cy {
    private Context a;
    private int b = 0;
    private char c = '#';

    public cy(Context context) {
        this.a = context;
    }

    public StyleItem[] a(String str) {
        Map<Integer, StyleItem> b = b(str);
        if (b == null || b.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StyleItem styleItem : b.values()) {
            if (styleItem.isValid()) {
                arrayList.add(styleItem);
            }
        }
        int size = arrayList.size();
        if (size > 0) {
            return (StyleItem[]) arrayList.toArray(new StyleItem[size]);
        }
        return null;
    }

    public Map<Integer, StyleItem> b(String str) {
        HashMap hashMap = new HashMap();
        try {
            byte[] readFileContents = FileUtil.readFileContents(str);
            if (!b(hashMap, readFileContents)) {
                a(hashMap, readFileContents);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    private void a(Map<Integer, StyleItem> map, byte[] bArr) {
        cv a = a(bArr);
        if (a != null && a.a() != null) {
            try {
                JSONObject jSONObject = new JSONObject(a.a());
                if (jSONObject != null) {
                    JSONArray names = jSONObject.names();
                    for (int i = 0; i < names.length(); i++) {
                        String string = names.getString(i);
                        JSONObject jSONObject2 = jSONObject.getJSONObject(string);
                        if (jSONObject2 != null) {
                            a(string, map, jSONObject2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a(String str, Map<Integer, StyleItem> map, JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("subType");
            if (optJSONObject == null) {
                a((String) null, str, map, jSONObject);
                return;
            }
            JSONArray names = optJSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                a(str, string, map, optJSONObject.getJSONObject(string));
            }
        }
    }

    private void a(String str, String str2, Map<Integer, StyleItem> map, JSONObject jSONObject) throws JSONException {
        if (str != null && str.equals("roads") && str2.equals("subway")) {
            str2 = "subwayline";
        }
        int a = cz.a(str2);
        if (!jSONObject.optBoolean("visible", true)) {
            a(map, a, cw.a("visible")).textureId = -1;
            return;
        }
        if (!jSONObject.optBoolean("showIcon", true)) {
            a(map, a, cw.a("textFillColor")).textureId = -1;
        }
        if (!jSONObject.optBoolean("showLabel", true)) {
            a(map, a, cw.a("textFillColor")).opacity = 0.0f;
            a(map, a, cw.a("textStrokeColor")).opacity = 0.0f;
        }
        a(map, jSONObject, "color", "opacity", a);
        a(map, jSONObject, "fillColor", "fillOpacity", a);
        a(map, jSONObject, "strokeColor", "strokeOpacity", a);
        a(map, jSONObject, "textFillColor", "textFillOpacity", a);
        a(map, jSONObject, "textStrokeColor", "textStrokeOpacity", a);
        a(map, jSONObject, "backgroundColor", "backgroundOpacity", a);
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, String str, String str2, int i) {
        try {
            String optString = jSONObject.optString(str, null);
            float f = 1.0f;
            int i2 = 0;
            if (TextUtils.isEmpty(optString)) {
                f = (float) jSONObject.optDouble(str2, 1.0d);
            } else {
                i2 = c("#" + optString);
            }
            if (i2 != 0 || f != 1.0d) {
                int a = cw.a(str);
                StyleElement a2 = a(map, i, a);
                a2.value = i2;
                a2.opacity = f;
                if (i == 39) {
                    a(map, i, 2).opacity = 0.0f;
                } else if (i == 12 && a == 3) {
                    StyleElement a3 = a(map, i, 2);
                    a3.value = i2;
                    a3.opacity = f;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private cv a(byte[] bArr) {
        Throwable th;
        cv cvVar;
        try {
            cvVar = new cv();
        } catch (Throwable th2) {
            cvVar = null;
            th = th2;
        }
        try {
            byte[] bytes = "l".getBytes("utf-8");
            int length = bArr.length;
            int length2 = bytes.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) (bytes[i % length2] ^ bArr[i]);
            }
            cvVar.a(Convert.getString(bArr, 0, 4));
            cvVar.b(Convert.getString(bArr, 4, 32));
            cvVar.c(Convert.getString(bArr, 36, 10));
            cvVar.d(a(Convert.getSubBytes(bArr, 78, length - 78), Convert.getSubBytes(bArr, 46, 16), Convert.getSubBytes(bArr, 62, 16)));
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            return cvVar;
        }
        return cvVar;
    }

    private String a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(bArr), "utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private boolean b(Map<Integer, StyleItem> map, byte[] bArr) {
        int a;
        int c;
        try {
            JSONArray jSONArray = new JSONArray(new String(bArr, "UTF-8"));
            if (jSONArray == null) {
                return false;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("featureType");
                if (!TextUtils.isEmpty(string)) {
                    if (string.equals("background")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("stylers");
                        if (!(jSONObject2 == null || (c = c(jSONObject2.getString("color"))) == 0)) {
                            this.b = c;
                        }
                    } else {
                        int[] a2 = cx.a(string);
                        if (!(a2 == null || a2.length == 0)) {
                            String string2 = jSONObject.getString("elementType");
                            if (!TextUtils.isEmpty(string2) && (a = cw.a(string2)) != -1) {
                                a(map, jSONObject, a2, a);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (JSONException e) {
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, int[] iArr, int i) throws JSONException {
        for (int i2 : iArr) {
            a(map, jSONObject, i2, i);
        }
    }

    private void a(Map<Integer, StyleItem> map, JSONObject jSONObject, int i, int i2) throws JSONException {
        int c;
        StyleElement a = a(map, i, i2);
        JSONObject jSONObject2 = jSONObject.getJSONObject("stylers");
        if (jSONObject2 != null && (c = c(jSONObject2.getString("color"))) != 0) {
            a.value = c;
            a.textureId = jSONObject2.optInt("textureid", 0);
            if (i >= 30 && i <= 38) {
                a(map, i, 4).opacity = 0.1f;
            } else if (i == 12 && i2 == 3) {
                a(map, i, 2).value = c;
            }
        }
    }

    private StyleElement a(Map<Integer, StyleItem> map, int i, int i2) {
        StyleItem styleItem = map.get(Integer.valueOf(i));
        if (styleItem == null) {
            styleItem = new StyleItem(i);
            map.put(Integer.valueOf(i), styleItem);
        }
        StyleElement styleElement = styleItem.get(i2);
        if (styleElement != null) {
            return styleElement;
        }
        StyleElement styleElement2 = new StyleElement();
        styleElement2.styleElementType = i2;
        styleItem.put(i2, styleElement2);
        return styleElement2;
    }

    private int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Color.parseColor(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public int a() {
        return this.b;
    }
}
