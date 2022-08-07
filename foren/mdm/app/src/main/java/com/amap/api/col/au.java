package com.amap.api.col;

import android.content.Context;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdateItem.java */
@gy(a = "update_item", b = true)
/* loaded from: classes.dex */
public class au extends ax {
    private String n = "";
    private Context o;

    public au() {
    }

    public au(OfflineMapCity offlineMapCity, Context context) {
        this.o = context;
        this.a = offlineMapCity.getCity();
        this.c = offlineMapCity.getAdcode();
        this.b = offlineMapCity.getUrl();
        this.g = offlineMapCity.getSize();
        this.e = offlineMapCity.getVersion();
        this.k = offlineMapCity.getCode();
        this.i = 0;
        this.l = offlineMapCity.getState();
        this.j = offlineMapCity.getcompleteCode();
        this.m = offlineMapCity.getPinyin();
        a();
    }

    public au(OfflineMapProvince offlineMapProvince, Context context) {
        this.o = context;
        this.a = offlineMapProvince.getProvinceName();
        this.c = offlineMapProvince.getProvinceCode();
        this.b = offlineMapProvince.getUrl();
        this.g = offlineMapProvince.getSize();
        this.e = offlineMapProvince.getVersion();
        this.i = 1;
        this.l = offlineMapProvince.getState();
        this.j = offlineMapProvince.getcompleteCode();
        this.m = offlineMapProvince.getPinyin();
        a();
    }

    protected void a() {
        this.d = dt.b(this.o) + this.m + ".zip.tmp";
    }

    public String b() {
        return this.n;
    }

    public void a(String str) {
        this.n = str;
    }

    public void b(String str) {
        JSONObject jSONObject;
        if (str != null) {
            try {
                if (!str.equals("") && (jSONObject = new JSONObject(str).getJSONObject("file")) != null) {
                    this.a = jSONObject.optString("title");
                    this.c = jSONObject.optString("code");
                    this.b = jSONObject.optString("url");
                    this.d = jSONObject.optString("fileName");
                    this.f = jSONObject.optLong("lLocalLength");
                    this.g = jSONObject.optLong("lRemoteLength");
                    this.l = jSONObject.optInt("mState");
                    this.e = jSONObject.optString("version");
                    this.h = jSONObject.optString("localPath");
                    this.n = jSONObject.optString("vMapFileNames");
                    this.i = jSONObject.optInt("isSheng");
                    this.j = jSONObject.optInt("mCompleteCode");
                    this.k = jSONObject.optString("mCityCode");
                    this.m = a(jSONObject, "pinyin");
                    if (this.m.equals("")) {
                        String substring = this.b.substring(this.b.lastIndexOf("/") + 1);
                        this.m = substring.substring(0, substring.lastIndexOf("."));
                    }
                }
            } catch (JSONException e) {
                gr.b(e, "UpdateItem", "readFileToJSONObject");
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public void c() {
        OutputStreamWriter outputStreamWriter;
        IOException e;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("title", this.a);
            jSONObject2.put("code", this.c);
            jSONObject2.put("url", this.b);
            jSONObject2.put("fileName", this.d);
            jSONObject2.put("lLocalLength", this.f);
            jSONObject2.put("lRemoteLength", this.g);
            jSONObject2.put("mState", this.l);
            jSONObject2.put("version", this.e);
            jSONObject2.put("localPath", this.h);
            if (this.n != null) {
                jSONObject2.put("vMapFileNames", this.n);
            }
            jSONObject2.put("isSheng", this.i);
            jSONObject2.put("mCompleteCode", this.j);
            jSONObject2.put("mCityCode", this.k);
            jSONObject2.put("pinyin", this.m);
            jSONObject.put("file", jSONObject2);
            String str = this.d + ".dt";
            File file = new File(str);
            file.delete();
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                try {
                    outputStreamWriter.write(jSONObject.toString());
                    str = outputStreamWriter;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                            str = outputStreamWriter;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            str = outputStreamWriter;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    gr.b(e, "UpdateItem", "saveJSONObjectToFile");
                    e.printStackTrace();
                    str = outputStreamWriter;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                            str = outputStreamWriter;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            str = outputStreamWriter;
                        }
                    }
                }
            } catch (IOException e5) {
                e = e5;
                outputStreamWriter = null;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (JSONException e7) {
            gr.b(e7, "UpdateItem", "saveJSONObjectToFile parseJson");
            e7.printStackTrace();
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals("[]")) {
            return jSONObject.optString(str).trim();
        }
        return "";
    }
}
