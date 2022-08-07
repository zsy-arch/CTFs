package com.ta.utdid2.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Random;

/* loaded from: classes2.dex */
public class PhoneInfoUtils {
    public static final String getUniqueID() {
        int t1 = (int) (System.currentTimeMillis() / 1000);
        int t3 = new Random().nextInt();
        int t4 = new Random().nextInt();
        byte[] b1 = IntUtils.getBytes(t1);
        byte[] b2 = IntUtils.getBytes((int) System.nanoTime());
        byte[] b3 = IntUtils.getBytes(t3);
        byte[] b4 = IntUtils.getBytes(t4);
        byte[] bUniqueID = new byte[16];
        System.arraycopy(b1, 0, bUniqueID, 0, 4);
        System.arraycopy(b2, 0, bUniqueID, 4, 4);
        System.arraycopy(b3, 0, bUniqueID, 8, 4);
        System.arraycopy(b4, 0, bUniqueID, 12, 4);
        return Base64.encodeToString(bUniqueID, 2);
    }

    public static String getImei(Context context) {
        String imei = null;
        if (context != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
                if (tm != null) {
                    imei = tm.getDeviceId();
                }
            } catch (Exception e) {
            }
        }
        if (StringUtils.isEmpty(imei)) {
            return getUniqueID();
        }
        return imei;
    }

    public static String getImsi(Context context) {
        String imsi = null;
        if (context != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
                if (tm != null) {
                    imsi = tm.getSubscriberId();
                }
            } catch (Exception e) {
            }
        }
        if (StringUtils.isEmpty(imsi)) {
            return getUniqueID();
        }
        return imsi;
    }
}
