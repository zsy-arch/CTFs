package com.amap.api.col;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.alipay.sdk.util.j;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Utility.java */
/* loaded from: classes.dex */
public class bh {
    public static void a(String str) {
    }

    public static long a() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getFreeBlocks() * statFs.getBlockSize();
    }

    public static List<OfflineMapProvince> a(String str, Context context) throws JSONException {
        if (str == null || "".equals(str)) {
            return new ArrayList();
        }
        return a(new JSONObject(str), context);
    }

    public static List<OfflineMapProvince> a(JSONObject jSONObject, Context context) throws JSONException {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        ArrayList arrayList = new ArrayList();
        if (!jSONObject.has(j.c)) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(j.c, new JSONObject().put("offlinemap_with_province_vfour", jSONObject));
                c(jSONObject2.toString(), context);
                optJSONObject = jSONObject2.optJSONObject(j.c);
            } catch (JSONException e) {
                optJSONObject = jSONObject.optJSONObject(j.c);
                gr.b(e, "Utility", "parseJson");
                e.printStackTrace();
            }
        } else {
            optJSONObject = jSONObject.optJSONObject(j.c);
        }
        if (optJSONObject != null) {
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("offlinemap_with_province_vfour");
            if (optJSONObject3 == null) {
                return arrayList;
            }
            optJSONObject2 = optJSONObject3.optJSONObject("offlinemapinfo_with_province");
        } else {
            optJSONObject2 = jSONObject.optJSONObject("offlinemapinfo_with_province");
        }
        if (optJSONObject2 == null) {
            return arrayList;
        }
        if (optJSONObject2.has("version")) {
            ak.d = a(optJSONObject2, "version");
        }
        JSONArray optJSONArray = optJSONObject2.optJSONArray("provinces");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject4 = optJSONArray.optJSONObject(i);
            if (optJSONObject4 != null) {
                arrayList.add(a(optJSONObject4));
            }
        }
        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("others");
        JSONObject jSONObject3 = null;
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            jSONObject3 = optJSONArray2.getJSONObject(0);
        }
        if (jSONObject3 == null) {
            return arrayList;
        }
        arrayList.add(a(jSONObject3));
        return arrayList;
    }

    public static OfflineMapProvince a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setUrl(a(jSONObject, "url"));
        offlineMapProvince.setProvinceName(a(jSONObject, "name"));
        offlineMapProvince.setJianpin(a(jSONObject, "jianpin"));
        offlineMapProvince.setPinyin(a(jSONObject, "pinyin"));
        offlineMapProvince.setProvinceCode(c(a(jSONObject, "adcode")));
        offlineMapProvince.setVersion(a(jSONObject, "version"));
        offlineMapProvince.setSize(Long.parseLong(a(jSONObject, "size")));
        offlineMapProvince.setCityList(b(jSONObject));
        return offlineMapProvince;
    }

    private static String c(String str) {
        if (str.equals("000001")) {
            return "100000";
        }
        return str;
    }

    public static ArrayList<OfflineMapCity> b(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        if (optJSONArray == null) {
            return arrayList;
        }
        if (optJSONArray.length() == 0) {
            arrayList.add(c(jSONObject));
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(c(optJSONObject));
            }
        }
        return arrayList;
    }

    public static OfflineMapCity c(JSONObject jSONObject) throws JSONException {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setAdcode(c(a(jSONObject, "adcode")));
        offlineMapCity.setUrl(a(jSONObject, "url"));
        offlineMapCity.setCity(a(jSONObject, "name"));
        offlineMapCity.setCode(a(jSONObject, "citycode"));
        offlineMapCity.setPinyin(a(jSONObject, "pinyin"));
        offlineMapCity.setJianpin(a(jSONObject, "jianpin"));
        offlineMapCity.setVersion(a(jSONObject, "version"));
        offlineMapCity.setSize(Long.parseLong(a(jSONObject, "size")));
        return offlineMapCity;
    }

    public static long a(File file) {
        long length;
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                length = a(file2);
            } else {
                length = file2.length();
            }
            j += length;
        }
        return j;
    }

    public static boolean b(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!b(listFiles[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String a(Context context, String str) {
        try {
            return dt.a(dq.a(context).open(str));
        } catch (Throwable th) {
            gr.b(th, "MapDownloadManager", "readOfflineAsset");
            th.printStackTrace();
            return null;
        }
    }

    public static String c(File file) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        String str;
        StringBuffer stringBuffer;
        BufferedReader bufferedReader2;
        IOException e;
        try {
            str = null;
            stringBuffer = new StringBuffer();
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e2) {
                e = e2;
                bufferedReader2 = null;
                fileInputStream = null;
            } catch (IOException e3) {
                e = e3;
                bufferedReader2 = null;
                fileInputStream = null;
            } catch (Throwable th) {
                bufferedReader = null;
                fileInputStream = null;
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                } catch (FileNotFoundException e4) {
                    e = e4;
                    gr.b(e, "MapDownloadManager", "readOfflineSD filenotfound");
                    e.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return str;
                } catch (IOException e7) {
                    e = e7;
                    gr.b(e, "MapDownloadManager", "readOfflineSD io");
                    e.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    return str;
                }
            }
            str = stringBuffer.toString();
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
            }
        } catch (FileNotFoundException e12) {
            e = e12;
            bufferedReader2 = null;
        } catch (IOException e13) {
            e = e13;
            bufferedReader2 = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e14) {
                    e14.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e15) {
                    e15.printStackTrace();
                }
            }
            throw th;
        }
        return str;
    }

    public static void b(String str, Context context) throws IOException, Exception {
        File[] listFiles = new File(dt.b(context)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.exists() && file.getName().contains(str)) {
                    b(file);
                }
            }
            b(dt.b(context));
        }
    }

    public static void b(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.exists() && file2.isDirectory()) {
                    String[] list = file2.list();
                    if (list == null) {
                        file2.delete();
                    } else if (list.length == 0) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals("[]")) {
            return jSONObject.optString(str).trim();
        }
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.OutputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(java.lang.String r6, android.content.Context r7) {
        /*
            java.lang.String r0 = com.amap.api.col.dt.b(r7)
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.amap.api.col.dt.b(r7)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "offlinemapv4.png"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0032
            r0.createNewFile()     // Catch: IOException -> 0x0057
        L_0x0032:
            long r2 = a()
            r4 = 1048576(0x100000, double:5.180654E-318)
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x000c
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: FileNotFoundException -> 0x0064, IOException -> 0x007c, all -> 0x0096
            r1.<init>(r0)     // Catch: FileNotFoundException -> 0x0064, IOException -> 0x007c, all -> 0x0096
            java.lang.String r0 = "utf-8"
            byte[] r0 = r6.getBytes(r0)     // Catch: FileNotFoundException -> 0x00a7, IOException -> 0x00a5, all -> 0x00a3
            r1.write(r0)     // Catch: FileNotFoundException -> 0x00a7, IOException -> 0x00a5, all -> 0x00a3
            if (r1 == 0) goto L_0x000c
            r1.close()     // Catch: IOException -> 0x0052
            goto L_0x000c
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0057:
            r1 = move-exception
            java.lang.String r2 = "OfflineUpdateCityHandler"
            java.lang.String r3 = "writeSD dirCreate"
            com.amap.api.col.gr.b(r1, r2, r3)
            r1.printStackTrace()
            goto L_0x0032
        L_0x0064:
            r0 = move-exception
            r1 = r2
        L_0x0066:
            java.lang.String r2 = "OfflineUpdateCityHandler"
            java.lang.String r3 = "writeSD filenotfound"
            com.amap.api.col.gr.b(r0, r2, r3)     // Catch: all -> 0x00a3
            r0.printStackTrace()     // Catch: all -> 0x00a3
            if (r1 == 0) goto L_0x000c
            r1.close()     // Catch: IOException -> 0x0077
            goto L_0x000c
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x007c:
            r0 = move-exception
            r1 = r2
        L_0x007e:
            java.lang.String r2 = "OfflineUpdateCityHandler"
            java.lang.String r3 = "writeSD io"
            com.amap.api.col.gr.b(r0, r2, r3)     // Catch: all -> 0x00a3
            r0.printStackTrace()     // Catch: all -> 0x00a3
            if (r1 == 0) goto L_0x000c
            r1.close()     // Catch: IOException -> 0x0090
            goto L_0x000c
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x000c
        L_0x0096:
            r0 = move-exception
            r1 = r2
        L_0x0098:
            if (r1 == 0) goto L_0x009d
            r1.close()     // Catch: IOException -> 0x009e
        L_0x009d:
            throw r0
        L_0x009e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x009d
        L_0x00a3:
            r0 = move-exception
            goto L_0x0098
        L_0x00a5:
            r0 = move-exception
            goto L_0x007e
        L_0x00a7:
            r0 = move-exception
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.bh.c(java.lang.String, android.content.Context):void");
    }
}
