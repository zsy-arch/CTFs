package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class cu {
    private static String a = null;
    private static final Pattern b = Pattern.compile("\\s*|\t|\r|\n");

    /* JADX WARN: Removed duplicated region for block: B:44:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a() {
        /*
            r0 = 0
            r7 = 13
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r1 = 20
            char[] r4 = new char[r1]     // Catch: Exception -> 0x0078, all -> 0x0068
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: Exception -> 0x0078, all -> 0x0068
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Exception -> 0x0078, all -> 0x0068
            java.lang.String r5 = "/sys/class/net/eth0/address"
            r1.<init>(r5)     // Catch: Exception -> 0x0078, all -> 0x0068
            r2.<init>(r1)     // Catch: Exception -> 0x0078, all -> 0x0068
        L_0x0018:
            int r5 = r2.read(r4)     // Catch: Exception -> 0x002f, all -> 0x0076
            r1 = -1
            if (r5 == r1) goto L_0x0048
            int r1 = r4.length     // Catch: Exception -> 0x002f, all -> 0x0076
            if (r5 != r1) goto L_0x0039
            int r1 = r4.length     // Catch: Exception -> 0x002f, all -> 0x0076
            int r1 = r1 + (-1)
            char r1 = r4[r1]     // Catch: Exception -> 0x002f, all -> 0x0076
            if (r1 == r7) goto L_0x0039
            java.io.PrintStream r1 = java.lang.System.out     // Catch: Exception -> 0x002f, all -> 0x0076
            r1.print(r4)     // Catch: Exception -> 0x002f, all -> 0x0076
            goto L_0x0018
        L_0x002f:
            r1 = move-exception
        L_0x0030:
            com.baidu.mobstat.cr.a(r1)     // Catch: all -> 0x0076
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch: IOException -> 0x0063
        L_0x0038:
            return r0
        L_0x0039:
            r1 = 0
        L_0x003a:
            if (r1 >= r5) goto L_0x0018
            char r6 = r4[r1]     // Catch: Exception -> 0x002f, all -> 0x0076
            if (r6 == r7) goto L_0x0045
            char r6 = r4[r1]     // Catch: Exception -> 0x002f, all -> 0x0076
            r3.append(r6)     // Catch: Exception -> 0x002f, all -> 0x0076
        L_0x0045:
            int r1 = r1 + 1
            goto L_0x003a
        L_0x0048:
            java.lang.String r1 = r3.toString()     // Catch: Exception -> 0x002f, all -> 0x0076
            java.lang.String r1 = r1.trim()     // Catch: Exception -> 0x002f, all -> 0x0076
            java.lang.String r3 = ":"
            java.lang.String r4 = ""
            java.lang.String r0 = r1.replaceAll(r3, r4)     // Catch: Exception -> 0x002f, all -> 0x0076
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch: IOException -> 0x005e
            goto L_0x0038
        L_0x005e:
            r1 = move-exception
            com.baidu.mobstat.cr.a(r1)
            goto L_0x0038
        L_0x0063:
            r1 = move-exception
            com.baidu.mobstat.cr.a(r1)
            goto L_0x0038
        L_0x0068:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch: IOException -> 0x0071
        L_0x0070:
            throw r0
        L_0x0071:
            r1 = move-exception
            com.baidu.mobstat.cr.a(r1)
            goto L_0x0070
        L_0x0076:
            r0 = move-exception
            goto L_0x006b
        L_0x0078:
            r1 = move-exception
            r2 = r0
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cu.a():java.lang.String");
    }

    private static String a(byte b2) {
        String str = "00" + Integer.toHexString(b2) + ":";
        return str.substring(str.length() - 3);
    }

    public static String a(int i, Context context) {
        try {
            return ck.c(i, a(context).getBytes());
        } catch (Exception e) {
            cr.a(e);
            return "";
        }
    }

    public static String a(Context context) {
        return b.matcher(cw.a(context)).replaceAll("");
    }

    public static String a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return "";
            }
            Object obj = null;
            if (applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
            if (obj != null) {
                return obj.toString();
            }
            cr.a("null,can't find information for key:" + str);
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public static int b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = d(context);
        } catch (Exception e) {
            cr.a(e);
        }
        return displayMetrics.widthPixels;
    }

    public static String b(int i, Context context) {
        String i2 = i(context);
        return TextUtils.isEmpty(i2) ? "" : ck.c(i, i2.getBytes());
    }

    public static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = d(context);
        } catch (Exception e) {
            cr.a(e);
        }
        return displayMetrics.heightPixels;
    }

    @SuppressLint({"NewApi"})
    public static String c(int i, Context context) {
        byte[] bArr = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isAnyLocalAddress() && (nextElement2 instanceof Inet4Address) && !nextElement2.isLoopbackAddress()) {
                            if (nextElement2.isSiteLocalAddress()) {
                                bArr = nextElement.getHardwareAddress();
                            } else if (!nextElement2.isLinkLocalAddress()) {
                                bArr = nextElement.getHardwareAddress();
                                break;
                            } else {
                                bArr = bArr;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            cr.a(e);
        }
        if (bArr != null) {
            for (byte b2 : bArr) {
                stringBuffer.append(a(b2));
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(":", "");
        }
        String b3 = b(i, context);
        return b3 != null ? b3.replaceAll(":", "") : b3;
    }

    public static DisplayMetrics d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String d(int i, Context context) {
        String j = j(context);
        return TextUtils.isEmpty(j) ? "" : ck.c(i, j.getBytes());
    }

    public static int e(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            cr.b("Get app version code exception");
            return 1;
        }
    }

    public static String e(int i, Context context) {
        String l = l(context);
        return TextUtils.isEmpty(l) ? "" : ck.d(i, l.getBytes());
    }

    public static String f(int i, Context context) {
        String o = o(context);
        if (!TextUtils.isEmpty(o)) {
            try {
                return ck.c(i, o.getBytes());
            } catch (Exception e) {
                cr.b(e);
            }
        }
        return "";
    }

    public static String f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            cr.b("get app version name exception");
            return "";
        }
    }

    public static String g(Context context) {
        String format = String.format("%s_%s_%s", 0, 0, 0);
        try {
            if (cl.e(context, "android.permission.ACCESS_FINE_LOCATION") || cl.e(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                cr.a(cellLocation + "");
                if (cellLocation == null) {
                    return format;
                }
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    return String.format("%s_%s_%s", String.format("%d", Integer.valueOf(gsmCellLocation.getCid())), String.format("%d", Integer.valueOf(gsmCellLocation.getLac())), 0);
                }
                String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(",");
                return String.format("%s_%s_%s", split[0], split[3], split[4]);
            }
        } catch (Exception e) {
            cr.a("Get Location", e);
        }
        return format;
    }

    public static String h(Context context) {
        try {
            if (cl.e(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation(GeocodeSearch.GPS);
                cr.b("location: " + lastKnownLocation);
                if (lastKnownLocation != null) {
                    return String.format("%s_%s_%s", Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude()));
                }
            }
        } catch (Exception e) {
            cr.b(e);
        }
        return "";
    }

    public static String i(Context context) {
        try {
            if (cl.e(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo != null) {
                    String macAddress = connectionInfo.getMacAddress();
                    if (!TextUtils.isEmpty(macAddress)) {
                        return macAddress;
                    }
                }
            } else {
                cr.c("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add: android.permission.ACCESS_WIFI_STATE");
            }
        } catch (Exception e) {
            cr.b(e);
        }
        return "";
    }

    @SuppressLint({"NewApi"})
    public static String j(Context context) {
        BluetoothAdapter defaultAdapter;
        String str = Build.BRAND;
        if ("4.1.1".equals(Build.VERSION.RELEASE) && "TCT".equals(str)) {
            return "";
        }
        try {
            if (cl.e(context, "android.permission.BLUETOOTH") && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
                String address = defaultAdapter.getAddress();
                if (address != null) {
                    return address;
                }
            }
        } catch (Exception e) {
            cr.b(e);
        }
        return "";
    }

    public static String k(Context context) {
        String l = l(context);
        return TextUtils.isEmpty(l) ? "" : cj.a(l.getBytes());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0084 A[Catch: Exception -> 0x00ce, TryCatch #0 {Exception -> 0x00ce, blocks: (B:24:0x005e, B:26:0x0084, B:27:0x008b, B:29:0x00a1, B:32:0x00ae), top: B:54:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String l(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cu.l(android.content.Context):java.lang.String");
    }

    public static boolean m(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isAvailable()) {
                if (networkInfo.isConnected()) {
                    return z;
                }
            }
            z = false;
            return z;
        } catch (Exception e) {
            cr.a(e);
            return false;
        }
    }

    public static String n(Context context) {
        Exception e;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            String typeName = activeNetworkInfo.getTypeName();
            try {
                return (typeName.equals("WIFI") || activeNetworkInfo.getSubtypeName() == null) ? typeName : activeNetworkInfo.getSubtypeName();
            } catch (Exception e2) {
                e = e2;
                cr.a(e);
                return "";
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String o(Context context) {
        return context != null ? context.getPackageName() : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String p(android.content.Context r6) {
        /*
            java.lang.String r1 = com.baidu.mobstat.cu.a
            if (r1 != 0) goto L_0x0058
            if (r6 == 0) goto L_0x0056
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r6.getSystemService(r0)     // Catch: Exception -> 0x0052
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch: Exception -> 0x0052
            java.util.List r3 = r0.getRunningAppProcesses()     // Catch: Exception -> 0x0052
            r0 = 0
            r2 = r0
        L_0x0014:
            if (r3 == 0) goto L_0x0056
            int r0 = r3.size()     // Catch: Exception -> 0x0052
            if (r2 >= r0) goto L_0x0056
            java.lang.Object r0 = r3.get(r2)     // Catch: Exception -> 0x0052
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0     // Catch: Exception -> 0x0052
            if (r0 == 0) goto L_0x004e
            int r4 = r0.pid     // Catch: Exception -> 0x0052
            int r5 = android.os.Process.myPid()     // Catch: Exception -> 0x0052
            if (r4 != r5) goto L_0x004e
            java.lang.String r0 = r0.processName     // Catch: Exception -> 0x0052
            r2 = 58
            int r2 = r0.lastIndexOf(r2)     // Catch: Exception -> 0x0052
            if (r2 <= 0) goto L_0x004b
            int r3 = r2 + 1
            int r4 = r0.length()     // Catch: Exception -> 0x0052
            if (r3 >= r4) goto L_0x004b
            int r2 = r2 + 1
            java.lang.String r0 = r0.substring(r2)     // Catch: Exception -> 0x0052
        L_0x0044:
            if (r0 != 0) goto L_0x0048
            java.lang.String r0 = ""
        L_0x0048:
            com.baidu.mobstat.cu.a = r0
        L_0x004a:
            return r0
        L_0x004b:
            java.lang.String r0 = ""
            goto L_0x0044
        L_0x004e:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x0014
        L_0x0052:
            r0 = move-exception
            com.baidu.mobstat.cr.b(r0)
        L_0x0056:
            r0 = r1
            goto L_0x0044
        L_0x0058:
            r0 = r1
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cu.p(android.content.Context):java.lang.String");
    }

    public static boolean q(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Exception e) {
            cr.b(e);
            return false;
        }
    }

    public static String r(Context context) {
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("m", memoryInfo.availMem);
            jSONObject.put("l", memoryInfo.lowMemory);
            jSONObject.put("t", memoryInfo.threshold);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_mem", jSONArray);
            jSONObject2.put("meta-data", sb.toString());
            return cj.a(jSONObject2.toString().getBytes());
        } catch (Exception e) {
            cr.a(e);
            return "";
        }
    }
}
