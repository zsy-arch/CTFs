package com.parse;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class ManifestInfo {
    private static final int NUMBER_OF_PUSH_INTENTS = 3;
    private static final String TAG = "com.parse.ManifestInfo";
    private static PushType pushType;
    private static final Object lock = new Object();
    private static long lastModified = -1;
    static int versionCode = -1;
    static String versionName = null;
    private static int iconId = 0;
    private static String displayName = null;

    /* loaded from: classes2.dex */
    public enum ManifestCheckResult {
        HAS_ALL_DECLARATIONS,
        MISSING_OPTIONAL_DECLARATIONS,
        MISSING_REQUIRED_DECLARATIONS
    }

    ManifestInfo() {
    }

    public static long getLastModified() {
        synchronized (lock) {
            if (lastModified == -1) {
                lastModified = new File(getContext().getApplicationInfo().sourceDir).lastModified();
            }
        }
        return lastModified;
    }

    public static int getVersionCode() {
        synchronized (lock) {
            if (versionCode == -1) {
                try {
                    versionCode = getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    PLog.e(TAG, "Couldn't find info about own package", e);
                }
            }
        }
        return versionCode;
    }

    public static String getVersionName() {
        synchronized (lock) {
            if (versionName == null) {
                try {
                    versionName = getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    PLog.e(TAG, "Couldn't find info about own package", e);
                }
            }
        }
        return versionName;
    }

    public static String getDisplayName(Context context) {
        synchronized (lock) {
            if (displayName == null) {
                displayName = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
            }
        }
        return displayName;
    }

    public static int getIconId() {
        synchronized (lock) {
            if (iconId == 0) {
                iconId = getContext().getApplicationInfo().icon;
            }
        }
        return iconId;
    }

    static boolean hasIntentReceiver(String action) {
        return !getIntentReceivers(action).isEmpty();
    }

    public static List<ResolveInfo> getIntentReceivers(String... actions) {
        Context context = getContext();
        String packageName = context.getPackageName();
        List<ResolveInfo> list = new ArrayList<>();
        for (String action : actions) {
            list.addAll(context.getPackageManager().queryBroadcastReceivers(new Intent(action), 32));
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!list.get(i).activityInfo.packageName.equals(packageName)) {
                list.remove(i);
            }
        }
        return list;
    }

    private static boolean usesPushBroadcastReceivers() {
        int intentsRegistered = 0;
        if (hasIntentReceiver(ParsePushBroadcastReceiver.ACTION_PUSH_RECEIVE)) {
            intentsRegistered = 0 + 1;
        }
        if (hasIntentReceiver(ParsePushBroadcastReceiver.ACTION_PUSH_OPEN)) {
            intentsRegistered++;
        }
        if (hasIntentReceiver(ParsePushBroadcastReceiver.ACTION_PUSH_DELETE)) {
            intentsRegistered++;
        }
        if (intentsRegistered == 0 || intentsRegistered == 3) {
            return intentsRegistered == 3;
        }
        throw new SecurityException("The Parse Push BroadcastReceiver must implement a filter for all of com.parse.push.intent.RECEIVE, com.parse.push.intent.OPEN, and com.parse.push.intent.DELETE");
    }

    static void setPushType(PushType newPushType) {
        synchronized (lock) {
            pushType = newPushType;
        }
    }

    public static PushType getPushType() {
        boolean hasRequiredGcmDeclarations;
        boolean hasRequiredPpnsDeclarations = true;
        synchronized (lock) {
            if (pushType == null) {
                boolean isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
                boolean isPPNSAvailable = PPNSUtil.isPPNSAvailable();
                boolean hasAnyGcmSpecificDeclaration = hasAnyGcmSpecificDeclaration();
                ManifestCheckResult gcmSupportLevel = gcmSupportLevel();
                ManifestCheckResult ppnsSupportLevel = ppnsSupportLevel();
                boolean hasPushBroadcastReceiver = usesPushBroadcastReceivers();
                if (gcmSupportLevel != ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS) {
                    hasRequiredGcmDeclarations = true;
                } else {
                    hasRequiredGcmDeclarations = false;
                }
                if (ppnsSupportLevel == ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS) {
                    hasRequiredPpnsDeclarations = false;
                }
                if (hasPushBroadcastReceiver && isGooglePlayServicesAvailable && hasRequiredGcmDeclarations) {
                    pushType = PushType.GCM;
                } else if (!hasPushBroadcastReceiver || !isPPNSAvailable || !hasRequiredPpnsDeclarations || (hasAnyGcmSpecificDeclaration && isGooglePlayServicesAvailable)) {
                    pushType = PushType.NONE;
                    if (hasAnyGcmSpecificDeclaration) {
                        if (!hasPushBroadcastReceiver) {
                            PLog.e(TAG, "Push is currently disabled. This is probably because you migrated from an older version of Parse. This version of Parse requires your app to have a BroadcastReceiver that handles com.parse.push.intent.RECEIVE, com.parse.push.intent.OPEN, and com.parse.push.intent.DELETE. You can do this by adding these lines to your AndroidManifest.xml:\n\n <receiver android:name=\"com.parse.ParsePushBroadcastReceiver\"\n   android:exported=false>\n  <intent-filter>\n     <action android:name=\"com.parse.push.intent.RECEIVE\" />\n     <action android:name=\"com.parse.push.intent.OPEN\" />\n     <action android:name=\"com.parse.push.intent.DELETE\" />\n   </intent-filter>\n </receiver>");
                        }
                        if (!isGooglePlayServicesAvailable) {
                            PLog.e(TAG, "Cannot use GCM for push on this device because Google Play Services is not available. Install Google Play Services from the Play Store.");
                        }
                        if (!hasRequiredGcmDeclarations) {
                            PLog.e(TAG, "Cannot use GCM for push because the app manifest is missing some required declarations. Please " + getGcmManifestMessage());
                        }
                    }
                } else {
                    pushType = PushType.PPNS;
                    if (isGooglePlayServicesAvailable) {
                        Log.w(TAG, "Using PPNS for push even though Google Play Services is available. Please " + getGcmManifestMessage());
                    }
                }
                PLog.v(TAG, "Using " + pushType + " for push.");
                if (Parse.getLogLevel() <= 5) {
                    if (pushType == PushType.GCM && gcmSupportLevel == ManifestCheckResult.MISSING_OPTIONAL_DECLARATIONS) {
                        PLog.w(TAG, "Using GCM for Parse Push, but the app manifest is missing some optional declarations that should be added for maximum reliability. Please " + getGcmManifestMessage());
                    } else if (pushType == PushType.PPNS && ppnsSupportLevel == ManifestCheckResult.MISSING_OPTIONAL_DECLARATIONS) {
                        PLog.w(TAG, "Using PPNS for push, but the app manifest is missing some optional declarations that should be added for maximum reliability. Please " + getPpnsManifestMessage());
                    }
                }
            }
        }
        return pushType;
    }

    public static String getNonePushTypeLogMessage() {
        return "Push is not configured for this app because the app manifest is missing required declarations. Please add the following declarations to your app manifest to use GCM for push: " + getGcmManifestMessage();
    }

    private static Context getContext() {
        return Parse.getApplicationContext();
    }

    private static PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    private static ApplicationInfo getApplicationInfo(Context context, int flags) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), flags);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Bundle getApplicationMetadata(Context context) {
        ApplicationInfo info = getApplicationInfo(context, 128);
        if (info != null) {
            return info.metaData;
        }
        return null;
    }

    private static PackageInfo getPackageInfo(String name) {
        try {
            return getPackageManager().getPackageInfo(name, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static ServiceInfo getServiceInfo(Class<? extends Service> clazz) {
        try {
            return getPackageManager().getServiceInfo(new ComponentName(getContext(), clazz), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static ActivityInfo getReceiverInfo(Class<? extends BroadcastReceiver> clazz) {
        try {
            return getPackageManager().getReceiverInfo(new ComponentName(getContext(), clazz), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static boolean hasRequestedPermissions(Context context, String... permissions) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (pi.requestedPermissions == null) {
                return false;
            }
            return Arrays.asList(pi.requestedPermissions).containsAll(Arrays.asList(permissions));
        } catch (PackageManager.NameNotFoundException e) {
            PLog.e(TAG, "Couldn't find info about own package", e);
            return false;
        }
    }

    private static boolean hasGrantedPermissions(Context context, String... permissions) {
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        for (String permission : permissions) {
            if (packageManager.checkPermission(permission, packageName) != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkResolveInfo(Class<? extends BroadcastReceiver> clazz, List<ResolveInfo> infoList) {
        for (ResolveInfo info : infoList) {
            if (info.activityInfo != null && clazz.getCanonicalName().equals(info.activityInfo.name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkReceiver(Class<? extends BroadcastReceiver> clazz, String permission, Intent[] intents) {
        ActivityInfo receiver = getReceiverInfo(clazz);
        if (receiver == null) {
            return false;
        }
        if (!(permission == null || permission.equals(receiver.permission))) {
            return false;
        }
        for (Intent intent : intents) {
            List<ResolveInfo> receivers = getPackageManager().queryBroadcastReceivers(intent, 0);
            if (receivers.isEmpty() || !checkResolveInfo(clazz, receivers)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasAnyGcmSpecificDeclaration() {
        Context context = getContext();
        return hasRequestedPermissions(context, "com.google.android.c2dm.permission.RECEIVE") || hasRequestedPermissions(context, new StringBuilder().append(context.getPackageName()).append(".permission.C2D_MESSAGE").toString()) || getReceiverInfo(GcmBroadcastReceiver.class) != null;
    }

    private static boolean isGooglePlayServicesAvailable() {
        return Build.VERSION.SDK_INT >= 8 && getPackageInfo("com.google.android.gsf") != null;
    }

    private static ManifestCheckResult gcmSupportLevel() {
        Context context = getContext();
        if (getServiceInfo(PushService.class) == null) {
            return ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS;
        }
        if (!hasRequestedPermissions(context, UpdateConfig.h, UpdateConfig.g, "android.permission.WAKE_LOCK", "com.google.android.c2dm.permission.RECEIVE", context.getPackageName() + ".permission.C2D_MESSAGE")) {
            return ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS;
        }
        String packageName = context.getPackageName();
        if (!checkReceiver(GcmBroadcastReceiver.class, "com.google.android.c2dm.permission.SEND", new Intent[]{new Intent(GCMService.RECEIVE_PUSH_ACTION).setPackage(packageName).addCategory(packageName), new Intent(GCMService.REGISTER_RESPONSE_ACTION).setPackage(packageName).addCategory(packageName)})) {
            return ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS;
        }
        if (!hasGrantedPermissions(context, "android.permission.VIBRATE")) {
            return ManifestCheckResult.MISSING_OPTIONAL_DECLARATIONS;
        }
        return ManifestCheckResult.HAS_ALL_DECLARATIONS;
    }

    private static ManifestCheckResult ppnsSupportLevel() {
        Context context = getContext();
        if (getServiceInfo(PushService.class) == null) {
            return ManifestCheckResult.MISSING_REQUIRED_DECLARATIONS;
        }
        if (!hasGrantedPermissions(context, UpdateConfig.h, UpdateConfig.g, "android.permission.VIBRATE", "android.permission.WAKE_LOCK", "android.permission.RECEIVE_BOOT_COMPLETED")) {
            return ManifestCheckResult.MISSING_OPTIONAL_DECLARATIONS;
        }
        String packageName = context.getPackageName();
        if (!checkReceiver(ParseBroadcastReceiver.class, null, new Intent[]{new Intent("android.intent.action.BOOT_COMPLETED").setPackage(packageName), new Intent("android.intent.action.USER_PRESENT").setPackage(packageName)})) {
            return ManifestCheckResult.MISSING_OPTIONAL_DECLARATIONS;
        }
        return ManifestCheckResult.HAS_ALL_DECLARATIONS;
    }

    private static String getGcmManifestMessage() {
        String packageName = getContext().getPackageName();
        String gcmPackagePermission = packageName + ".permission.C2D_MESSAGE";
        return "make sure that these permissions are declared as children of the root <manifest> element:\n\n<uses-permission android:name=\"android.permission.INTERNET\" />\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />\n<uses-permission android:name=\"android.permission.VIBRATE\" />\n<uses-permission android:name=\"android.permission.WAKE_LOCK\" />\n<uses-permission android:name=\"android.permission.GET_ACCOUNTS\" />\n<uses-permission android:name=\"com.google.android.c2dm.permission.RECEIVE\" />\n<permission android:name=\"" + gcmPackagePermission + "\" android:protectionLevel=\"signature\" />\n<uses-permission android:name=\"" + gcmPackagePermission + "\" />\n\nAlso, please make sure that these services and broadcast receivers are declared as children of the <application> element:\n\n<service android:name=\"com.parse.PushService\" />\n<receiver android:name=\"com.parse.GcmBroadcastReceiver\" android:permission=\"com.google.android.c2dm.permission.SEND\">\n  <intent-filter>\n    <action android:name=\"com.google.android.c2dm.intent.RECEIVE\" />\n    <action android:name=\"com.google.android.c2dm.intent.REGISTRATION\" />\n    <category android:name=\"" + packageName + "\" />\n  </intent-filter>\n</receiver>\n<receiver android:name=\"com.parse.ParsePushBroadcastReceiver\" android:exported=false>\n  <intent-filter>\n    <action android:name=\"com.parse.push.intent.RECEIVE\" />\n    <action android:name=\"com.parse.push.intent.OPEN\" />\n    <action android:name=\"com.parse.push.intent.DELETE\" />\n  </intent-filter>\n</receiver>";
    }

    private static String getPpnsManifestMessage() {
        return "make sure that these permissions are declared as children of the root <manifest> element:\n\n<uses-permission android:name=\"android.permission.INTERNET\" />\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />\n<uses-permission android:name=\"android.permission.RECEIVE_BOOT_COMPLETED\" />\n<uses-permission android:name=\"android.permission.VIBRATE\" />\n<uses-permission android:name=\"android.permission.WAKE_LOCK\" />\n\nAlso, please make sure that these services and broadcast receivers are declared as children of the <application> element:\n\n<service android:name=\"com.parse.PushService\" />\n<receiver android:name=\"com.parse.ParseBroadcastReceiver\">\n  <intent-filter>\n    <action android:name=\"android.intent.action.BOOT_COMPLETED\" />\n    <action android:name=\"android.intent.action.USER_PRESENT\" />\n  </intent-filter>\n</receiver>\n<receiver android:name=\"com.parse.ParsePushBroadcastReceiver\" android:exported=false>\n  <intent-filter>\n    <action android:name=\"com.parse.push.intent.RECEIVE\" />\n    <action android:name=\"com.parse.push.intent.OPEN\" />\n    <action android:name=\"com.parse.push.intent.DELETE\" />\n  </intent-filter>\n</receiver>";
    }
}
