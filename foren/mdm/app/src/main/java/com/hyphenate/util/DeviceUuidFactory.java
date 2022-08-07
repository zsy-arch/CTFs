package com.hyphenate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
public class DeviceUuidFactory {
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_FILE = "device_id.xml";
    protected static UUID uuid;

    public DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, 0);
                    String string = sharedPreferences.getString("device_id", null);
                    if (string != null) {
                        uuid = UUID.fromString(string);
                    } else {
                        String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                        try {
                            if (!"9774d56d682e549c".equals(string2)) {
                                uuid = UUID.nameUUIDFromBytes(string2.getBytes("utf8"));
                            } else {
                                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : generateDeviceUuid(context);
                            }
                            sharedPreferences.edit().putString("device_id", uuid.toString()).apply();
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    private UUID generateDeviceUuid(Context context) {
        String str = Build.BOARD + Build.BRAND + Build.CPU_ABI + Build.DEVICE + Build.DISPLAY + Build.FINGERPRINT + Build.HOST + Build.ID + Build.MANUFACTURER + Build.MODEL + Build.PRODUCT + Build.TAGS + Build.TYPE + Build.USER;
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        return (!isEmpty(deviceId) || !isEmpty(string) || !isEmpty(macAddress)) ? UUID.nameUUIDFromBytes((str + deviceId + string + macAddress).getBytes()) : UUID.randomUUID();
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String) && ((String) obj).trim().length() == 0) {
            return true;
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    public UUID getDeviceUuid() {
        return uuid;
    }
}
