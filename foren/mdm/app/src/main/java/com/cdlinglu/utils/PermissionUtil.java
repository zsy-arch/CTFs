package com.cdlinglu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.util.MyLog;
import com.umeng.update.UpdateConfig;
import com.umeng.update.a;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PermissionUtil {

    /* loaded from: classes.dex */
    public interface OnRequestPermissionsResultCallbacks {
        void onPermissionsDenied(int i, List<String> list, boolean z);

        void onPermissionsGranted(int i, List<String> list, boolean z);
    }

    private static boolean needCheckPermission() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean getExternalStoragePermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.READ_EXTERNAL_STORAGE", UpdateConfig.f);
    }

    public static boolean getVideoPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", UpdateConfig.f);
    }

    public static boolean getCameraPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", UpdateConfig.f);
    }

    public static boolean getAudioPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", UpdateConfig.f);
    }

    public static boolean getLocationPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean getContactsPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.READ_CONTACTS");
    }

    public static boolean getSendSMSPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.SEND_SMS");
    }

    public static boolean getCallPhonePermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, "android.permission.CALL_PHONE");
    }

    public static boolean getAlertPermissionCheck(@NonNull Activity activity) {
        if (!needCheckPermission()) {
            return true;
        }
        boolean has = checkAlertWindowPermission(activity);
        MyLog.e("AlertWindowPermission=", has + "");
        if (has) {
            return has;
        }
        requestAlertWindowPermission(activity);
        return has;
    }

    public static boolean checkAlertWindowPermission(Context context) {
        Boolean result = true;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                result = (Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context);
            } catch (Exception e) {
                MyLog.e("Alert", Log.getStackTraceString(e));
            }
        }
        return result.booleanValue();
    }

    public static void requestAlertWindowPermission(Context context) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static List<String> getDeniedPermissions(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return null;
        }
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != 0) {
                deniedPermissions.add(permission);
            }
        }
        if (deniedPermissions.isEmpty()) {
            return null;
        }
        return deniedPermissions;
    }

    public static boolean hasPermissons(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean deniedRequestPermissonsAgain(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return false;
        }
        for (String permission : getDeniedPermissions(activity, permissions)) {
            MyLog.e("deniedPermissions", ContextCompat.checkSelfPermission(activity, permission) + "");
            if (ContextCompat.checkSelfPermission(activity, permission) == -1 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    public static void startApplicationDetailsSettings(@NonNull Activity activity, int requestCode) {
        Toast.makeText(activity, "点进权限，并打开全部权限", 1).show();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts(a.d, activity.getPackageName(), null));
        activity.startActivityForResult(intent, requestCode);
    }

    public static boolean requestPerssions(final Activity activity, final int requestCode, String... permissions) {
        if (!needCheckPermission() || hasPermissons(activity, permissions)) {
            return true;
        }
        if (deniedRequestPermissonsAgain(activity, permissions)) {
            new EaseAlertDialog((Context) activity, "权限申请", "去面对面-权限中开启权限，以正常使用相关功能", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.cdlinglu.utils.PermissionUtil.1
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        PermissionUtil.startApplicationDetailsSettings(activity, requestCode);
                    }
                }
            }, true).show();
        } else {
            List<String> deniedPermissions = getDeniedPermissions(activity, permissions);
            if (deniedPermissions != null) {
                ActivityCompat.requestPermissions(activity, (String[]) deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
        }
        return false;
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull OnRequestPermissionsResultCallbacks callBack) {
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == 0) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }
        if (callBack != null) {
            if (!granted.isEmpty()) {
                callBack.onPermissionsGranted(requestCode, granted, denied.isEmpty());
            }
            if (!denied.isEmpty()) {
                callBack.onPermissionsDenied(requestCode, denied, granted.isEmpty());
            }
        }
    }
}
