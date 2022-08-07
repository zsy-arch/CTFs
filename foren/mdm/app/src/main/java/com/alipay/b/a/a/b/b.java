package com.alipay.b.a.a.b;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.b.a.a.a.a;
import com.umeng.update.UpdateConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;
import u.aly.d;

/* loaded from: classes.dex */
public final class b {
    private static b a = new b();

    private b() {
    }

    public static b a() {
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r2) {
        /*
            r1 = 0
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x000c
            java.lang.String r0 = ""
        L_0x000b:
            return r0
        L_0x000c:
            if (r2 == 0) goto L_0x0022
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r2.getSystemService(r0)     // Catch: Throwable -> 0x0021
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: Throwable -> 0x0021
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = r0.getDeviceId()     // Catch: Throwable -> 0x0021
        L_0x001c:
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = ""
            goto L_0x000b
        L_0x0021:
            r0 = move-exception
        L_0x0022:
            r0 = r1
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.a(android.content.Context):java.lang.String");
    }

    private static boolean a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String b() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r2) {
        /*
            java.lang.String r1 = ""
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x000d
            java.lang.String r0 = ""
        L_0x000c:
            return r0
        L_0x000d:
            if (r2 == 0) goto L_0x0023
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r2.getSystemService(r0)     // Catch: Throwable -> 0x0022
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: Throwable -> 0x0022
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r0.getSubscriberId()     // Catch: Throwable -> 0x0022
        L_0x001d:
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = ""
            goto L_0x000c
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.b(android.content.Context):java.lang.String");
    }

    public static String c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(a.a().getPath());
                j = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    public static String c(Context context) {
        int i = 0;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable th) {
        }
        return i == 1 ? "1" : "0";
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004d A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String d() {
        /*
            Method dump skipped, instructions count: 169
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.d():java.lang.String");
    }

    public static String d(Context context) {
        int i = 1;
        i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager.getRingerMode() != 0) {
            }
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put(d.c.a, String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable th) {
        }
        return jSONObject.toString();
    }

    public static String e(Context context) {
        TelephonyManager telephonyManager;
        String networkOperatorName = null;
        if (context != null) {
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable th) {
            }
            if (telephonyManager != null) {
                networkOperatorName = telephonyManager.getNetworkOperatorName();
                return (networkOperatorName != null || f.b.equals(networkOperatorName)) ? "" : networkOperatorName;
            }
        }
        if (networkOperatorName != null) {
        }
    }

    public static String f() {
        String w = w();
        return !a.a(w) ? w : x();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String f(android.content.Context r2) {
        /*
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = ""
        L_0x000a:
            return r0
        L_0x000b:
            java.lang.String r1 = ""
            if (r2 == 0) goto L_0x0023
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r2.getSystemService(r0)     // Catch: Throwable -> 0x0022
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: Throwable -> 0x0022
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r0.getLine1Number()     // Catch: Throwable -> 0x0022
        L_0x001d:
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = ""
            goto L_0x000a
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.f(android.content.Context):java.lang.String");
    }

    public static String g() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader2;
        String[] split;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    split = bufferedReader.readLine().split(":\\s+", 2);
                } catch (Throwable th2) {
                    bufferedReader2 = bufferedReader;
                    fileReader2 = fileReader;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (Throwable th3) {
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable th4) {
                        }
                    }
                    return "";
                }
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = null;
            }
        } catch (Throwable th6) {
            fileReader2 = null;
        }
        if (split == null || split.length <= 1) {
            try {
                fileReader.close();
            } catch (Throwable th7) {
            }
            try {
                bufferedReader.close();
            } catch (Throwable th8) {
            }
            return "";
        }
        String str = split[1];
        try {
            fileReader.close();
        } catch (Throwable th9) {
        }
        try {
            bufferedReader.close();
            return str;
        } catch (Throwable th10) {
            return str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g(android.content.Context r5) {
        /*
            r1 = 0
            if (r5 == 0) goto L_0x0046
            java.lang.String r0 = "sensor"
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch: Throwable -> 0x0045
            android.hardware.SensorManager r0 = (android.hardware.SensorManager) r0     // Catch: Throwable -> 0x0045
            if (r0 == 0) goto L_0x0046
            r2 = -1
            java.util.List r0 = r0.getSensorList(r2)     // Catch: Throwable -> 0x0045
            if (r0 == 0) goto L_0x0046
            int r2 = r0.size()     // Catch: Throwable -> 0x0045
            if (r2 <= 0) goto L_0x0046
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0045
            r2.<init>()     // Catch: Throwable -> 0x0045
            java.util.Iterator r3 = r0.iterator()     // Catch: Throwable -> 0x0045
        L_0x0023:
            boolean r0 = r3.hasNext()     // Catch: Throwable -> 0x0045
            if (r0 == 0) goto L_0x004c
            java.lang.Object r0 = r3.next()     // Catch: Throwable -> 0x0045
            android.hardware.Sensor r0 = (android.hardware.Sensor) r0     // Catch: Throwable -> 0x0045
            java.lang.String r4 = r0.getName()     // Catch: Throwable -> 0x0045
            r2.append(r4)     // Catch: Throwable -> 0x0045
            int r4 = r0.getVersion()     // Catch: Throwable -> 0x0045
            r2.append(r4)     // Catch: Throwable -> 0x0045
            java.lang.String r0 = r0.getVendor()     // Catch: Throwable -> 0x0045
            r2.append(r0)     // Catch: Throwable -> 0x0045
            goto L_0x0023
        L_0x0045:
            r0 = move-exception
        L_0x0046:
            r0 = r1
        L_0x0047:
            if (r0 != 0) goto L_0x004b
            java.lang.String r0 = ""
        L_0x004b:
            return r0
        L_0x004c:
            java.lang.String r0 = r2.toString()     // Catch: Throwable -> 0x0045
            java.lang.String r0 = com.alipay.b.a.a.a.a.d(r0)     // Catch: Throwable -> 0x0045
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.g(android.content.Context):java.lang.String");
    }

    public static String h() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        long j = 0;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    j = Integer.parseInt(readLine.split("\\s+")[1]);
                }
                try {
                    fileReader.close();
                } catch (Throwable th3) {
                }
                try {
                    bufferedReader.close();
                } catch (Throwable th4) {
                }
            } catch (Throwable th5) {
                th = th5;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable th6) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th7) {
                    }
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileReader = null;
            bufferedReader = null;
        }
        return String.valueOf(j);
    }

    public static String h(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return jSONArray.toString();
    }

    public static String i() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String j() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = statFs.getBlockCount() * statFs.getBlockSize();
            }
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    public static String j(Context context) {
        try {
            return new StringBuilder().append(context.getResources().getDisplayMetrics().widthPixels).toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String k() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    public static String k(Context context) {
        try {
            return new StringBuilder().append(context.getResources().getDisplayMetrics().heightPixels).toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String l() {
        String str = "";
        try {
            str = Build.SERIAL;
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    public static String l(Context context) {
        String str;
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (macAddress.length() != 0 && !"02:00:00:00:00:00".equals(macAddress)) {
                        return macAddress;
                    }
                } catch (Throwable th) {
                    return str;
                }
            }
            return v();
        } catch (Throwable th2) {
            return "";
        }
    }

    public static String m() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    public static String m(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable th) {
                    return simSerialNumber;
                }
            }
            return "";
        } catch (Throwable th2) {
            return "";
        }
    }

    public static String n() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    public static String n(Context context) {
        String str = "";
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    public static String o() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            return new StringBuilder().append(currentTimeMillis - (currentTimeMillis % 1000)).toString();
        } catch (Throwable th) {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
        if ("02:00:00:00:00:00".equals(r0) == false) goto L_0x0029;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String o(android.content.Context r3) {
        /*
            java.lang.String r0 = "android.permission.BLUETOOTH"
            boolean r0 = a(r3, r0)
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = ""
        L_0x000a:
            return r0
        L_0x000b:
            java.lang.String r0 = y()
            if (r0 == 0) goto L_0x001f
            int r1 = r0.length()     // Catch: Throwable -> 0x002e
            if (r1 == 0) goto L_0x001f
            java.lang.String r1 = "02:00:00:00:00:00"
            boolean r1 = r1.equals(r0)     // Catch: Throwable -> 0x002e
            if (r1 == 0) goto L_0x0029
        L_0x001f:
            android.content.ContentResolver r1 = r3.getContentResolver()     // Catch: Throwable -> 0x002e
            java.lang.String r2 = "bluetooth_address"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r1, r2)     // Catch: Throwable -> 0x002e
        L_0x0029:
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = ""
            goto L_0x000a
        L_0x002e:
            r1 = move-exception
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.o(android.content.Context):java.lang.String");
    }

    public static String p() {
        try {
            return new StringBuilder().append(SystemClock.elapsedRealtime()).toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String p(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return String.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String q() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                if (new File(strArr[i]).exists()) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String q(android.content.Context r3) {
        /*
            java.lang.String r1 = ""
            java.lang.String r0 = "android.permission.ACCESS_WIFI_STATE"
            boolean r0 = a(r3, r0)
            if (r0 == 0) goto L_0x000d
            java.lang.String r0 = ""
        L_0x000c:
            return r0
        L_0x000d:
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r3.getSystemService(r0)     // Catch: Throwable -> 0x0028
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch: Throwable -> 0x0028
            boolean r2 = r0.isWifiEnabled()     // Catch: Throwable -> 0x0028
            if (r2 == 0) goto L_0x0029
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()     // Catch: Throwable -> 0x0028
            java.lang.String r0 = r0.getBSSID()     // Catch: Throwable -> 0x0028
        L_0x0023:
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = ""
            goto L_0x000c
        L_0x0028:
            r0 = move-exception
        L_0x0029:
            r0 = r1
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.q(android.content.Context):java.lang.String");
    }

    public static String r() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable th) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static String r(Context context) {
        try {
            String u2 = u(context);
            return (!a.b(u2) || !a.b(z())) ? "" : u2 + ":" + z();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String s() {
        Throwable th;
        LineNumberReader lineNumberReader;
        char c;
        StringBuilder sb = new StringBuilder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("/system/build.prop", "ro.product.name=sdk");
        linkedHashMap.put("/proc/tty/drivers", "goldfish");
        linkedHashMap.put("/proc/cpuinfo", "goldfish");
        sb.append("00:");
        for (String str : linkedHashMap.keySet()) {
            LineNumberReader lineNumberReader2 = null;
            try {
                LineNumberReader lineNumberReader3 = new LineNumberReader(new InputStreamReader(new FileInputStream(str)));
                while (true) {
                    try {
                        String readLine = lineNumberReader3.readLine();
                        if (readLine != null) {
                            if (readLine.toLowerCase().contains((CharSequence) linkedHashMap.get(str))) {
                                c = '1';
                                break;
                            }
                        } else {
                            c = '0';
                            break;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        lineNumberReader2 = lineNumberReader3;
                        sb.append('0');
                        if (lineNumberReader2 != null) {
                            try {
                                lineNumberReader2.close();
                            } catch (Throwable th3) {
                            }
                        }
                        throw th;
                    }
                }
                sb.append(c);
                try {
                    lineNumberReader3.close();
                } catch (Throwable th4) {
                }
            } catch (Throwable th5) {
                th = th5;
            }
        }
        return sb.toString();
    }

    public static String s(Context context) {
        try {
            long j = 0;
            if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            for (int i = 0; i < 5; i++) {
                long j2 = -1;
                try {
                    j2 = new File(strArr[i]).lastModified();
                } catch (Throwable th) {
                }
                j = Math.max(j2, j);
            }
            return "1:" + j;
        } catch (Throwable th2) {
            return "";
        }
    }

    public static String t() {
        String str = null;
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", EnvironmentCompat.MEDIA_UNKNOWN);
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str2 : linkedHashMap.keySet()) {
            try {
                String str3 = (String) Build.class.getField(str2).get(null);
                String str4 = (String) linkedHashMap.get(str2);
                if (str3 != null) {
                    str = str3.toLowerCase();
                }
                sb.append((str == null || !str.contains(str4)) ? '0' : '1');
            } catch (Throwable th) {
                sb.append('0');
                throw th;
            }
        }
        return sb.toString();
    }

    public static String t(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("status", -1);
            return (intExtra2 == 2 || intExtra2 == 5 ? "1" : "0") + ":" + intExtra;
        } catch (Throwable th) {
            return "";
        }
    }

    public static String u() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String b = a.b(str, "");
            if (b != null && b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String u(Context context) {
        String str;
        if (a(context, UpdateConfig.g)) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) {
                    return "2G";
                }
                if (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) {
                    return "3G";
                }
                if (subtype == 13) {
                    return "4G";
                }
                str = "UNKNOW";
            } else {
                str = null;
            }
            return str;
        } catch (Throwable th) {
            return null;
        }
    }

    private static String v() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list != null) {
                for (NetworkInterface networkInterface : list) {
                    if (!(networkInterface == null || networkInterface.getName() == null || !networkInterface.getName().equalsIgnoreCase("wlan0"))) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "02:00:00:00:00:00";
                        }
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Integer.valueOf(hardwareAddress[i] & 255)));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return "02:00:00:00:00:00";
    }

    private static String w() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader2;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                readLine = bufferedReader.readLine();
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable th5) {
                    }
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            fileReader = null;
            bufferedReader = null;
        }
        if (!a.a(readLine)) {
            String trim = readLine.trim();
            try {
                bufferedReader.close();
            } catch (Throwable th7) {
            }
            try {
                fileReader.close();
                return trim;
            } catch (Throwable th8) {
                return trim;
            }
        } else {
            try {
                bufferedReader.close();
            } catch (Throwable th9) {
            }
            try {
                fileReader.close();
            } catch (Throwable th10) {
            }
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
        r0 = r2[1].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String x() {
        /*
            r2 = 0
            r6 = 1
            java.lang.String r1 = "/proc/cpuinfo"
            java.lang.String r0 = ""
            java.io.FileReader r3 = new java.io.FileReader     // Catch: Throwable -> 0x0042, all -> 0x0051
            r3.<init>(r1)     // Catch: Throwable -> 0x0042, all -> 0x0051
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: Throwable -> 0x006e, all -> 0x0069
            r4 = 8192(0x2000, float:1.14794E-41)
            r1.<init>(r3, r4)     // Catch: Throwable -> 0x006e, all -> 0x0069
        L_0x0012:
            java.lang.String r2 = r1.readLine()     // Catch: Throwable -> 0x0072, all -> 0x006c
            if (r2 == 0) goto L_0x003b
            boolean r4 = com.alipay.b.a.a.a.a.a(r2)     // Catch: Throwable -> 0x0072, all -> 0x006c
            if (r4 != 0) goto L_0x0012
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch: Throwable -> 0x0072, all -> 0x006c
            if (r2 == 0) goto L_0x0012
            int r4 = r2.length     // Catch: Throwable -> 0x0072, all -> 0x006c
            if (r4 <= r6) goto L_0x0012
            r4 = 0
            r4 = r2[r4]     // Catch: Throwable -> 0x0072, all -> 0x006c
            java.lang.String r5 = "BogoMIPS"
            boolean r4 = r4.contains(r5)     // Catch: Throwable -> 0x0072, all -> 0x006c
            if (r4 == 0) goto L_0x0012
            r4 = 1
            r2 = r2[r4]     // Catch: Throwable -> 0x0072, all -> 0x006c
            java.lang.String r0 = r2.trim()     // Catch: Throwable -> 0x0072, all -> 0x006c
        L_0x003b:
            r3.close()     // Catch: Throwable -> 0x005f
        L_0x003e:
            r1.close()     // Catch: Throwable -> 0x0061
        L_0x0041:
            return r0
        L_0x0042:
            r1 = move-exception
            r1 = r2
        L_0x0044:
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch: Throwable -> 0x0063
        L_0x0049:
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch: Throwable -> 0x004f
            goto L_0x0041
        L_0x004f:
            r1 = move-exception
            goto L_0x0041
        L_0x0051:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0054:
            if (r3 == 0) goto L_0x0059
            r3.close()     // Catch: Throwable -> 0x0065
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()     // Catch: Throwable -> 0x0067
        L_0x005e:
            throw r0
        L_0x005f:
            r2 = move-exception
            goto L_0x003e
        L_0x0061:
            r1 = move-exception
            goto L_0x0041
        L_0x0063:
            r2 = move-exception
            goto L_0x0049
        L_0x0065:
            r2 = move-exception
            goto L_0x0059
        L_0x0067:
            r1 = move-exception
            goto L_0x005e
        L_0x0069:
            r0 = move-exception
            r1 = r2
            goto L_0x0054
        L_0x006c:
            r0 = move-exception
            goto L_0x0054
        L_0x006e:
            r1 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0044
        L_0x0072:
            r2 = move-exception
            r2 = r3
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.x():java.lang.String");
    }

    private static String y() {
        BluetoothAdapter defaultAdapter;
        String str = "";
        try {
            defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        } catch (Throwable th) {
        }
        if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
            return "";
        }
        str = defaultAdapter.getAddress();
        return str == null ? "" : str;
    }

    private static String z() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public final String e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Throwable th) {
            return "1";
        }
    }
}
