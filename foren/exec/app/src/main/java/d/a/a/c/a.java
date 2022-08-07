package d.a.a.c;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.smtt.sdk.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static String[] f1602a = {"/dev/socket/qemud", "/dev/qemu_pipe"};

    public static boolean a() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                process.destroy();
                return true;
            }
            process.destroy();
            return false;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    public static long b() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return -1L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static boolean a(Context context) {
        Boolean bool;
        String str;
        boolean z;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!(defaultAdapter == null || TextUtils.isEmpty(defaultAdapter.getName()))) {
            if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) == null) {
                bool = true;
            } else {
                bool = false;
            }
            if (!bool.booleanValue()) {
                if (!(Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT))) {
                    try {
                        Process start = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
                        StringBuffer stringBuffer = new StringBuffer();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                        }
                        bufferedReader.close();
                        str = stringBuffer.toString().toLowerCase();
                    } catch (IOException unused) {
                        str = BuildConfig.FLAVOR;
                    }
                    if (!(str.contains("intel") || str.contains("amd"))) {
                        int i = 0;
                        while (true) {
                            String[] strArr = f1602a;
                            if (i >= strArr.length) {
                                z = false;
                                break;
                            } else if (new File(strArr[i]).exists()) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (!z) {
                            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                            int intExtra = registerReceiver.getIntExtra("voltage", 99999);
                            int intExtra2 = registerReceiver.getIntExtra("temperature", 99999);
                            return (intExtra == 0 && intExtra2 == 0) || (intExtra == 10000 && intExtra2 == 0);
                        }
                    }
                }
            }
        }
        return true;
    }
}
