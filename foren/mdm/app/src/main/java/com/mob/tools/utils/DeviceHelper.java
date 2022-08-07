package com.mob.tools.utils;

import android.app.ActivityManager;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.http.config.URLConfig;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.HanziToPinyin;
import com.mob.tools.MobLog;
import com.umeng.update.UpdateConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    private Context context;

    /* loaded from: classes2.dex */
    private class GSConnection implements ServiceConnection {
        boolean got;
        private final BlockingQueue<IBinder> iBinders;

        private GSConnection() {
            this.got = false;
            this.iBinders = new LinkedBlockingQueue();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                this.iBinders.put(service);
            } catch (Throwable t) {
                MobLog.getInstance().w(t);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder takeBinder() throws InterruptedException {
            if (this.got) {
                throw new IllegalStateException();
            }
            this.got = true;
            return this.iBinders.poll(1500L, TimeUnit.MILLISECONDS);
        }
    }

    private DeviceHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    private String getCurrentNetworkHardwareAddress() throws Throwable {
        byte[] mac;
        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
        if (nis == null) {
            return null;
        }
        for (NetworkInterface intf : Collections.list(nis)) {
            Enumeration<InetAddress> ias = intf.getInetAddresses();
            if (ias != null) {
                for (InetAddress add : Collections.list(ias)) {
                    if (!(add.isLoopbackAddress() || !(add instanceof Inet4Address) || (mac = intf.getHardwareAddress()) == null)) {
                        StringBuilder buf = new StringBuilder();
                        int length = mac.length;
                        for (int i = 0; i < length; i++) {
                            buf.append(String.format("%02x:", Byte.valueOf(mac[i])));
                        }
                        if (buf.length() > 0) {
                            buf.deleteCharAt(buf.length() - 1);
                        }
                        return buf.toString();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0041 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getHardwareAddressFromShell(java.lang.String r10) {
        /*
            r9 = this;
            r3 = 0
            r0 = 0
            java.lang.Runtime r6 = java.lang.Runtime.getRuntime()     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0046, all -> 0x0056
            r7.<init>()     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.String r8 = "cat /sys/class/net/"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.String r8 = "/address"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.String r7 = r7.toString()     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.Process r4 = r6.exec(r7)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.io.InputStream r6 = r4.getInputStream()     // Catch: Throwable -> 0x0046, all -> 0x0056
            r2.<init>(r6)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: Throwable -> 0x0046, all -> 0x0056
            r1.<init>(r2)     // Catch: Throwable -> 0x0046, all -> 0x0056
            java.lang.String r3 = r1.readLine()     // Catch: Throwable -> 0x0062, all -> 0x005f
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch: Throwable -> 0x0043
            r0 = r1
        L_0x003b:
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            if (r6 == 0) goto L_0x0042
            r3 = 0
        L_0x0042:
            return r3
        L_0x0043:
            r6 = move-exception
            r0 = r1
            goto L_0x003b
        L_0x0046:
            r5 = move-exception
        L_0x0047:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: all -> 0x0056
            r6.d(r5)     // Catch: all -> 0x0056
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch: Throwable -> 0x0054
            goto L_0x003b
        L_0x0054:
            r6 = move-exception
            goto L_0x003b
        L_0x0056:
            r6 = move-exception
        L_0x0057:
            if (r0 == 0) goto L_0x005c
            r0.close()     // Catch: Throwable -> 0x005d
        L_0x005c:
            throw r6
        L_0x005d:
            r7 = move-exception
            goto L_0x005c
        L_0x005f:
            r6 = move-exception
            r0 = r1
            goto L_0x0057
        L_0x0062:
            r5 = move-exception
            r0 = r1
            goto L_0x0047
        L_0x0065:
            r0 = r1
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getHardwareAddressFromShell(java.lang.String):java.lang.String");
    }

    public static synchronized DeviceHelper getInstance(Context c) {
        DeviceHelper deviceHelper2;
        synchronized (DeviceHelper.class) {
            if (deviceHelper == null && c != null) {
                deviceHelper = new DeviceHelper(c);
            }
            deviceHelper2 = deviceHelper;
        }
        return deviceHelper2;
    }

    private String getLocalDeviceKey() throws Throwable {
        String strKey = null;
        if (getSdcardState()) {
            File cacheRoot = new File(getSdcardPath(), "ShareSDK");
            if (cacheRoot.exists()) {
                File keyFile = new File(cacheRoot, ".dk");
                if (keyFile.exists() && keyFile.renameTo(new File(R.getCacheRoot(this.context), ".dk"))) {
                    keyFile.delete();
                }
            }
            File keyFile2 = new File(R.getCacheRoot(this.context), ".dk");
            if (keyFile2.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyFile2));
                Object key = ois.readObject();
                strKey = null;
                if (key != null && (key instanceof char[])) {
                    strKey = String.valueOf((char[]) ((char[]) key));
                }
                ois.close();
            }
        }
        return strKey;
    }

    private Object getSystemService(String name) {
        try {
            return this.context.getSystemService(name);
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return null;
        }
    }

    private boolean is4GMobileNetwork() {
        TelephonyManager phone = (TelephonyManager) getSystemService("phone");
        return phone != null && phone.getNetworkType() == 13;
    }

    private boolean isFastMobileNetwork() {
        TelephonyManager phone = (TelephonyManager) getSystemService("phone");
        if (phone == null) {
            return false;
        }
        switch (phone.getNetworkType()) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            default:
                return false;
            case 3:
                return true;
            case 5:
                return true;
            case 6:
                return true;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
        }
    }

    private boolean isSystemApp(PackageInfo pi) {
        return ((pi.applicationInfo.flags & 1) == 1) || ((pi.applicationInfo.flags & 128) == 1);
    }

    private String[] listNetworkHardwareAddress() throws Throwable {
        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
        if (nis == null) {
            return null;
        }
        List<NetworkInterface> interfaces = Collections.list(nis);
        HashMap<String, String> macs = new HashMap<>();
        for (NetworkInterface intf : interfaces) {
            byte[] mac = intf.getHardwareAddress();
            if (mac != null) {
                StringBuilder buf = new StringBuilder();
                int length = mac.length;
                for (int i = 0; i < length; i++) {
                    buf.append(String.format("%02x:", Byte.valueOf(mac[i])));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                macs.put(intf.getName(), buf.toString());
            }
        }
        ArrayList<String> names = new ArrayList<>(macs.keySet());
        ArrayList<String> wlans = new ArrayList<>();
        ArrayList<String> eths = new ArrayList<>();
        ArrayList<String> rmnets = new ArrayList<>();
        ArrayList<String> dummys = new ArrayList<>();
        ArrayList<String> usbs = new ArrayList<>();
        ArrayList<String> rmnetUsbs = new ArrayList<>();
        ArrayList<String> others = new ArrayList<>();
        while (names.size() > 0) {
            String name = names.remove(0);
            if (name.startsWith("wlan")) {
                wlans.add(name);
            } else if (name.startsWith("eth")) {
                eths.add(name);
            } else if (name.startsWith("rev_rmnet")) {
                rmnets.add(name);
            } else if (name.startsWith("dummy")) {
                dummys.add(name);
            } else if (name.startsWith("usbnet")) {
                usbs.add(name);
            } else if (name.startsWith("rmnet_usb")) {
                rmnetUsbs.add(name);
            } else {
                others.add(name);
            }
        }
        Collections.sort(wlans);
        Collections.sort(eths);
        Collections.sort(rmnets);
        Collections.sort(dummys);
        Collections.sort(usbs);
        Collections.sort(rmnetUsbs);
        Collections.sort(others);
        names.addAll(wlans);
        names.addAll(eths);
        names.addAll(rmnets);
        names.addAll(dummys);
        names.addAll(usbs);
        names.addAll(rmnetUsbs);
        names.addAll(others);
        String[] macArr = new String[names.size()];
        for (int i2 = 0; i2 < macArr.length; i2++) {
            macArr[i2] = macs.get(names.get(i2));
        }
        return macArr;
    }

    private void saveLocalDeviceKey(String key) throws Throwable {
        if (getSdcardState()) {
            File keyFile = new File(R.getCacheRoot(this.context), ".dk");
            if (keyFile.exists()) {
                keyFile.delete();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(keyFile));
            oos.writeObject(key.toCharArray());
            oos.flush();
            oos.close();
        }
    }

    public String Base64AES(String msg, String key) {
        try {
            String result = Base64.encodeToString(Data.AES128Encode(key, msg), 0);
            return result.contains("\n") ? result.replace("\n", "") : result;
        } catch (Throwable e) {
            MobLog.getInstance().w(e);
            return null;
        }
    }

    public boolean checkPermission(String permission) throws Throwable {
        int res;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.importClass("android.content.Context");
                Integer ret = (Integer) ReflectHelper.invokeInstanceMethod(this.context, "checkSelfPermission", permission);
                res = ret == null ? -1 : ret.intValue();
            } catch (Throwable t) {
                MobLog.getInstance().w(t);
                res = -1;
            }
        } else {
            this.context.checkPermission(permission, Process.myPid(), Process.myUid());
            res = this.context.getPackageManager().checkPermission(permission, getPackageName());
        }
        return res == 0;
    }

    public String getAdvertisingID() {
        try {
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            GSConnection gsc = new GSConnection();
            this.context.bindService(intent, gsc, 1);
            IBinder binder = gsc.takeBinder();
            Parcel input = Parcel.obtain();
            Parcel output = Parcel.obtain();
            input.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            binder.transact(1, input, output, 0);
            output.readException();
            String adsid = output.readString();
            output.recycle();
            input.recycle();
            MobLog.getInstance().i("getAdvertisingID === " + adsid, new Object[0]);
            this.context.unbindService(gsc);
            return adsid;
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return null;
        }
    }

    public String getAndroidID() {
        String androidId = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
        MobLog.getInstance().i("getAndroidID === " + androidId, new Object[0]);
        return androidId;
    }

    public String getAppLanguage() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    public String getAppName() {
        String appName = this.context.getApplicationInfo().name;
        if (appName != null) {
            return appName;
        }
        int appLbl = this.context.getApplicationInfo().labelRes;
        return appLbl > 0 ? this.context.getString(appLbl) : String.valueOf(this.context.getApplicationInfo().nonLocalizedLabel);
    }

    public int getAppVersion() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return 0;
        }
    }

    public String getAppVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return "1.0";
        }
    }

    public String getBluetoothName() {
        try {
            BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
            if (myDevice != null && checkPermission("android.permission.BLUETOOTH")) {
                return myDevice.getName();
            }
        } catch (Throwable e) {
            MobLog.getInstance().d(e);
        }
        return null;
    }

    public String getBssid() {
        WifiManager wifi;
        WifiInfo info;
        try {
            if (!checkPermission("android.permission.ACCESS_WIFI_STATE") || (wifi = (WifiManager) getSystemService("wifi")) == null || (info = wifi.getConnectionInfo()) == null) {
                return null;
            }
            String bssid = info.getBSSID();
            if (bssid == null) {
                bssid = null;
            }
            return bssid;
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return null;
        }
    }

    public String getCarrier() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        if (tm == null) {
            return "-1";
        }
        String operator = tm.getSimOperator();
        return TextUtils.isEmpty(operator) ? "-1" : operator;
    }

    public String getCarrierName() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        if (tm == null) {
            return null;
        }
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                String operator = tm.getSimOperatorName();
                if (TextUtils.isEmpty(operator)) {
                    return null;
                }
                return operator;
            }
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
        }
        return null;
    }

    public int getCellId() {
        TelephonyManager tm;
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION") && (tm = (TelephonyManager) getSystemService("phone")) != null) {
                return ((GsmCellLocation) tm.getCellLocation()).getCid();
            }
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
        }
        return -1;
    }

    public int getCellLac() {
        TelephonyManager tm;
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION") && (tm = (TelephonyManager) getSystemService("phone")) != null) {
                return ((GsmCellLocation) tm.getCellLocation()).getLac();
            }
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
        }
        return -1;
    }

    public String getCharAndNumr(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(System.currentTimeMillis() ^ SystemClock.elapsedRealtime());
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                stringBuffer.insert(i + 1, (char) (random.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    public String getDetailNetworkTypeForStatic() {
        String networkType = getNetworkType().toLowerCase();
        return (TextUtils.isEmpty(networkType) || "none".equals(networkType)) ? "none" : networkType.startsWith("wifi") ? "wifi" : networkType.startsWith("4g") ? "4g" : networkType.startsWith("3g") ? "3g" : networkType.startsWith("2g") ? "2g" : networkType.startsWith("bluetooth") ? "bluetooth" : networkType;
    }

    public String getDeviceData() {
        return Base64AES(getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 16));
    }

    public String getDeviceDataNotAES() {
        return getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
    }

    public String getDeviceId() {
        String deviceId = getIMEI();
        return (!TextUtils.isEmpty(deviceId) || Build.VERSION.SDK_INT < 9) ? deviceId : getSerialno();
    }

    public String getDeviceKey() {
        String localKey;
        String newKey;
        try {
            localKey = getLocalDeviceKey();
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            localKey = null;
        }
        if (!TextUtils.isEmpty(localKey) && localKey.length() >= 40) {
            return localKey;
        }
        try {
            String mac = getMacAddress();
            String udid = getDeviceId();
            newKey = Data.byteToHex(Data.SHA1(mac + ":" + udid + ":" + getModel()));
        } catch (Throwable t2) {
            MobLog.getInstance().d(t2);
            newKey = null;
        }
        if (TextUtils.isEmpty(newKey) || newKey.length() < 40) {
            newKey = getCharAndNumr(40);
        }
        if (newKey != null) {
            try {
                saveLocalDeviceKey(newKey);
            } catch (Throwable t3) {
                MobLog.getInstance().w(t3);
            }
        }
        return newKey;
    }

    public String getDeviceType() {
        UiModeManager um = (UiModeManager) getSystemService("uimode");
        if (um != null) {
            switch (um.getCurrentModeType()) {
                case 1:
                    return "NO_UI";
                case 2:
                    return "DESK";
                case 3:
                    return "CAR";
                case 4:
                    return "TELEVISION";
                case 5:
                    return "APPLIANCE";
                case 6:
                    return "WATCH";
            }
        }
        return "UNDEFINED";
    }

    public String getIMEI() {
        TelephonyManager phone = (TelephonyManager) getSystemService("phone");
        if (phone == null) {
            return null;
        }
        String deviceId = null;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                deviceId = phone.getDeviceId();
            }
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
        }
        if (TextUtils.isEmpty(deviceId)) {
            return null;
        }
        return deviceId;
    }

    public String getIMSI() {
        TelephonyManager phone = (TelephonyManager) getSystemService("phone");
        if (phone == null) {
            return null;
        }
        String imsi = null;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                imsi = phone.getSubscriberId();
            }
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
        }
        if (TextUtils.isEmpty(imsi)) {
            return null;
        }
        return imsi;
    }

    public String getIPAddress() {
        try {
            if (checkPermission(UpdateConfig.h)) {
                Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                while (en.hasMoreElements()) {
                    Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Throwable e) {
            MobLog.getInstance().w(e);
        }
        return "0.0.0.0";
    }

    public ArrayList<HashMap<String, String>> getInstalledApp(boolean includeSystemApp) {
        CharSequence label;
        try {
            PackageManager pm = this.context.getPackageManager();
            List<PackageInfo> pis = pm.getInstalledPackages(0);
            ArrayList<HashMap<String, String>> apps = new ArrayList<>();
            for (PackageInfo pi : pis) {
                if (includeSystemApp || !isSystemApp(pi)) {
                    HashMap<String, String> app = new HashMap<>();
                    app.put("pkg", pi.packageName);
                    String appName = pi.applicationInfo.name;
                    if (appName == null) {
                        int appLbl = pi.applicationInfo.labelRes;
                        if (appLbl > 0 && (label = pm.getText(pi.packageName, appLbl, pi.applicationInfo)) != null) {
                            appName = label.toString().trim();
                        }
                        if (appName == null) {
                            appName = String.valueOf(pi.applicationInfo.nonLocalizedLabel);
                        }
                    }
                    app.put("name", appName);
                    app.put("version", pi.versionName);
                    apps.add(app);
                }
            }
            return apps;
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return new ArrayList<>();
        }
    }

    public String getLine1Number() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        return tm == null ? "-1" : tm.getLine1Number();
    }

    public Location getLocation(int GPSTimeout, int networkTimeout, boolean useLastKnown) {
        try {
            if (checkPermission("android.permission.ACCESS_FINE_LOCATION")) {
                return new LocationHelper().getLocation(this.context, GPSTimeout, networkTimeout, useLastKnown);
            }
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
        }
        return null;
    }

    public float[] getLocation(int GPSTimeout, int networkTimeout) {
        Location loc = getLocation(GPSTimeout, networkTimeout, true);
        if (loc != null) {
            return new float[]{(float) loc.getLatitude(), (float) loc.getLongitude()};
        }
        return null;
    }

    public String getMCC() {
        String imsi;
        if (((TelephonyManager) getSystemService("phone")) == null || (imsi = getIMSI()) == null || imsi.length() < 3) {
            return null;
        }
        return imsi.substring(0, 3);
    }

    public String getMNC() {
        String imsi;
        if (((TelephonyManager) getSystemService("phone")) == null || (imsi = getIMSI()) == null || imsi.length() < 5) {
            return null;
        }
        return imsi.substring(3, 5);
    }

    public String getMacAddress() {
        WifiInfo info;
        String hd;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                hd = getHardwareAddressFromShell("wlan0");
            } catch (Throwable t) {
                MobLog.getInstance().d(t);
                hd = null;
            }
            if (hd == null) {
                try {
                    hd = getCurrentNetworkHardwareAddress();
                } catch (Throwable t2) {
                    MobLog.getInstance().d(t2);
                    hd = null;
                }
            }
            if (hd == null) {
                try {
                    String[] hds = listNetworkHardwareAddress();
                    if (hds.length > 0) {
                        hd = hds[0];
                    }
                } catch (Throwable t3) {
                    MobLog.getInstance().d(t3);
                    hd = null;
                }
            }
            if (hd != null) {
                return hd;
            }
        }
        WifiManager wifi = (WifiManager) getSystemService("wifi");
        if (wifi == null || (info = wifi.getConnectionInfo()) == null) {
            return null;
        }
        String mac = info.getMacAddress();
        if (mac == null) {
            mac = null;
        }
        return mac;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getMime() {
        return getIMEI();
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getNetworkOperator() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        if (tm == null) {
            return null;
        }
        return tm.getNetworkOperator();
    }

    public String getNetworkType() {
        NetworkInfo network;
        ConnectivityManager conn = (ConnectivityManager) getSystemService("connectivity");
        if (conn == null) {
            return "none";
        }
        try {
            if (!checkPermission(UpdateConfig.g) || (network = conn.getActiveNetworkInfo()) == null || !network.isAvailable()) {
                return "none";
            }
            int type = network.getType();
            switch (type) {
                case 0:
                    return is4GMobileNetwork() ? "4G" : isFastMobileNetwork() ? "3G" : "2G";
                case 1:
                    return "wifi";
                case 2:
                case 3:
                case 4:
                case 5:
                default:
                    return String.valueOf(type);
                case 6:
                    return "wimax";
                case 7:
                    return "bluetooth";
                case 8:
                    return "dummy";
                case 9:
                    return "ethernet";
            }
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return "none";
        }
    }

    public String getNetworkTypeForStatic() {
        String networkType = getNetworkType().toLowerCase();
        return (TextUtils.isEmpty(networkType) || "none".equals(networkType)) ? "none" : (networkType.startsWith("4g") || networkType.startsWith("3g") || networkType.startsWith("2g")) ? "cell" : networkType.startsWith("wifi") ? "wifi" : "other";
    }

    public String getOSCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getOSLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getOSVersion() {
        return String.valueOf(getOSVersionInt());
    }

    public int getOSVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    public String getOSVersionName() {
        return Build.VERSION.RELEASE;
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    public int getPlatformCode() {
        return 1;
    }

    public JSONArray getRunningApp() {
        List<ActivityManager.RunningAppProcessInfo> apps;
        JSONArray appNmes = new JSONArray();
        ActivityManager am = (ActivityManager) getSystemService("activity");
        if (!(am == null || (apps = am.getRunningAppProcesses()) == null)) {
            for (ActivityManager.RunningAppProcessInfo app : apps) {
                appNmes.put(app.processName);
            }
        }
        return appNmes;
    }

    public String getRunningAppStr() throws JSONException {
        JSONArray apps = getRunningApp();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < apps.length(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(String.valueOf(apps.get(i)));
        }
        return sb.toString();
    }

    public String getSSID() {
        WifiManager wifi;
        WifiInfo info;
        try {
            if (!checkPermission("android.permission.ACCESS_WIFI_STATE") || (wifi = (WifiManager) getSystemService("wifi")) == null || (info = wifi.getConnectionInfo()) == null) {
                return null;
            }
            String ssid = info.getSSID().replace("\"", "");
            if (ssid == null) {
                ssid = null;
            }
            return ssid;
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return null;
        }
    }

    public String getScreenSize() {
        int[] size = R.getScreenSize(this.context);
        return this.context.getResources().getConfiguration().orientation == 1 ? size[0] + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + size[1] : size[1] + EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME + size[0];
    }

    public String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public boolean getSdcardState() {
        try {
            if (checkPermission(UpdateConfig.f)) {
                return "mounted".equals(Environment.getExternalStorageState());
            }
            return false;
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            return false;
        }
    }

    public String getSerialno() {
        if (Build.VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            return (String) c.getMethod("get", String.class, String.class).invoke(c, "ro.serialno", EnvironmentCompat.MEDIA_UNKNOWN);
        } catch (Throwable t) {
            MobLog.getInstance().d(t);
            return null;
        }
    }

    public String getSignMD5() {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().w(e);
            return null;
        }
    }

    public String getSimSerialNumber() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        return tm == null ? "-1" : tm.getSimSerialNumber();
    }

    public String getTopTaskPackageName() {
        boolean hasPer;
        try {
            hasPer = checkPermission("android.permission.GET_TASKS");
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
            hasPer = false;
        }
        if (hasPer) {
            try {
                ActivityManager am = (ActivityManager) getSystemService("activity");
                return am == null ? null : Build.VERSION.SDK_INT <= 20 ? am.getRunningTasks(1).get(0).topActivity.getPackageName() : am.getRunningAppProcesses().get(0).processName.split(":")[0];
            } catch (Throwable t2) {
                MobLog.getInstance().w(t2);
            }
        }
        return null;
    }

    public void hideSoftInput(View view) {
        Object service = getSystemService("input_method");
        if (service != null) {
            ((InputMethodManager) service).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean isMainProcess(int pid) {
        String application = null;
        ActivityManager mActivityManager = (ActivityManager) getSystemService("activity");
        if (mActivityManager.getRunningAppProcesses() == null) {
            return pid <= 0;
        }
        int mPid = pid <= 0 ? Process.myPid() : pid;
        Iterator<ActivityManager.RunningAppProcessInfo> it = mActivityManager.getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo appProcess = it.next();
            if (appProcess.pid == mPid) {
                application = appProcess.processName;
                break;
            }
        }
        return getPackageName().equals(application);
    }

    public boolean isRooted() {
        return false;
    }

    public HashMap<String, String> ping(String address, int count, int packetsize) {
        ArrayList<Float> sucRes = new ArrayList<>();
        try {
            int bytes = packetsize + 8;
            Process p = Runtime.getRuntime().exec("ping -c " + count + " -s " + packetsize + HanziToPinyin.Token.SEPARATOR + address);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith(bytes + " bytes from")) {
                    if (line.endsWith("ms")) {
                        line = line.substring(0, line.length() - 2).trim();
                    } else if (line.endsWith(URLConfig.baidu_url)) {
                        line = line.substring(0, line.length() - 1).trim() + "000";
                    }
                    int i = line.indexOf("time=");
                    if (i > 0) {
                        sucRes.add(Float.valueOf(Float.parseFloat(line.substring(i + 5).trim())));
                    }
                }
                line = br.readLine();
            }
            p.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        int sucCount = sucRes.size();
        int fldCount = count - sucRes.size();
        float min = 0.0f;
        float max = 0.0f;
        float average = 0.0f;
        if (sucCount > 0) {
            min = Float.MAX_VALUE;
            for (int i2 = 0; i2 < sucCount; i2++) {
                float item = sucRes.get(i2).floatValue();
                if (item < min) {
                    min = item;
                }
                if (item > max) {
                    max = item;
                }
                average += item;
            }
            average /= sucCount;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("transmitted", String.valueOf(count));
        map.put("received", String.valueOf(sucCount));
        map.put("loss", String.valueOf(fldCount));
        map.put("min", String.valueOf(min));
        map.put("max", String.valueOf(max));
        map.put("avg", String.valueOf(average));
        return map;
    }

    public void showSoftInput(View view) {
        Object service = getSystemService("input_method");
        if (service != null) {
            ((InputMethodManager) service).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
        }
    }
}
