package com.alibaba.sdk.android.oss.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class OSSLogToFileUtils {
    private static final String LOG_DIR_NAME = "OSSLog";
    private static OSSLogToFileUtils instance;
    private static Context sContext;
    private static File sLogFile;
    private boolean useSdCard = true;
    private static LogThreadPoolManager logService = LogThreadPoolManager.newInstance();
    private static SimpleDateFormat sLogSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static long LOG_MAX_SIZE = 5242880;
    private static boolean sWrite2Local = false;

    private OSSLogToFileUtils() {
    }

    public static void init(Context context, ClientConfiguration cfg) {
        OSSLog.logDebug("init ...", false);
        if (sContext == null || instance == null || sLogFile == null || !sLogFile.exists()) {
            if (cfg != null) {
                LOG_MAX_SIZE = cfg.getMaxLogSize();
            }
            sContext = context.getApplicationContext();
            instance = getInstance();
            sLogFile = instance.getLogFile();
            if (sLogFile != null) {
                OSSLog.logInfo("LogFilePath is: " + sLogFile.getPath(), false);
                long logFileSize = getLogFileSize(sLogFile);
                OSSLog.logInfo("Log max size is: " + Formatter.formatFileSize(context, LOG_MAX_SIZE), false);
                OSSLog.logInfo("Log now size is: " + Formatter.formatFileSize(context, logFileSize), false);
                if (LOG_MAX_SIZE < logFileSize) {
                    OSSLog.logInfo("init reset log file", false);
                    instance.resetLogFile();
                    return;
                }
                return;
            }
            return;
        }
        OSSLog.logDebug("LogToFileUtils has been init ...", false);
    }

    public static OSSLogToFileUtils getInstance() {
        if (instance == null) {
            synchronized (OSSLogToFileUtils.class) {
                if (instance == null) {
                    instance = new OSSLogToFileUtils();
                }
            }
        }
        return instance;
    }

    private long readSDCardSpace() {
        long sdCardSize = 0;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getPath());
            sdCardSize = sf.getAvailableBlocks() * sf.getBlockSize();
        }
        OSSLog.logDebug("sd卡存储空间:" + String.valueOf(sdCardSize) + "kb", false);
        return sdCardSize;
    }

    private long readSystemSpace() {
        StatFs sf = new StatFs(Environment.getRootDirectory().getPath());
        long systemSpaceSize = (sf.getAvailableBlocks() * sf.getBlockSize()) / 1024;
        OSSLog.logDebug("内部存储空间:" + String.valueOf(systemSpaceSize) + "kb", false);
        return systemSpaceSize;
    }

    public void setUseSdCard(boolean useSdCard) {
        this.useSdCard = useSdCard;
    }

    public void resetLogFile() {
        OSSLog.logDebug("Reset Log File ... ", false);
        if (!sLogFile.getParentFile().exists()) {
            OSSLog.logDebug("Reset Log make File dir ... ", false);
            sLogFile.getParentFile().mkdir();
        }
        File logFile = new File(sLogFile.getParent() + "/logs.csv");
        if (logFile.exists()) {
            logFile.delete();
        }
        createNewFile(logFile);
    }

    public void deleteLogFile() {
        File logFile = new File(sLogFile.getParent() + "/logs.csv");
        if (logFile.exists()) {
            OSSLog.logDebug("delete Log File ... ", false);
            logFile.delete();
        }
    }

    public void deleteLogFileDir() {
        deleteLogFile();
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + LOG_DIR_NAME);
        if (dir.exists()) {
            OSSLog.logDebug("delete Log FileDir ... ", false);
            dir.delete();
        }
    }

    public static void reset() {
        sContext = null;
        instance = null;
        sLogFile = null;
    }

    public static long getLogFileSize(File file) {
        Throwable th;
        Exception e;
        FileInputStream fis;
        long size = 0;
        if (file != null && file.exists()) {
            FileInputStream fis2 = null;
            try {
                try {
                    fis = new FileInputStream(file);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                size = fis.available();
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e3) {
                        OSSLog.logError(e3.toString(), false);
                    }
                }
            } catch (Exception e4) {
                e = e4;
                fis2 = fis;
                OSSLog.logError(e.toString(), false);
                if (fis2 != null) {
                    try {
                        fis2.close();
                    } catch (IOException e5) {
                        OSSLog.logError(e5.toString(), false);
                    }
                }
                return size;
            } catch (Throwable th3) {
                th = th3;
                fis2 = fis;
                if (fis2 != null) {
                    try {
                        fis2.close();
                    } catch (IOException e6) {
                        OSSLog.logError(e6.toString(), false);
                    }
                }
                throw th;
            }
        }
        return size;
    }

    public static long getLocalLogFileSize() {
        return getLogFileSize(sLogFile);
    }

    private File getLogFile() {
        File file;
        boolean canStorage = true;
        if (!this.useSdCard || !Environment.getExternalStorageState().equals("mounted")) {
            if (readSystemSpace() <= LOG_MAX_SIZE / 1024) {
                canStorage = false;
            }
            file = new File(sContext.getFilesDir().getPath() + File.separator + LOG_DIR_NAME);
        } else {
            if (readSDCardSpace() <= LOG_MAX_SIZE / 1024) {
                canStorage = false;
            }
            file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + LOG_DIR_NAME);
        }
        File logFile = null;
        if (canStorage) {
            if (!file.exists()) {
                file.mkdirs();
            }
            logFile = new File(file.getPath() + "/logs.csv");
            if (!logFile.exists()) {
                createNewFile(logFile);
            }
        }
        return logFile;
    }

    public void createNewFile(File logFile) {
        try {
            logFile.createNewFile();
        } catch (Exception e) {
            OSSLog.logError("Create log file failure !!! " + e.toString(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFunctionInfo(StackTraceElement[] ste) {
        if (ste == null) {
            return "[" + sLogSDF.format(new Date()) + "]";
        }
        return null;
    }

    public synchronized void write(Object str) {
        if (!(!OSSLog.isEnableLog() || sContext == null || instance == null || sLogFile == null)) {
            if (!sLogFile.exists()) {
                resetLogFile();
            }
            logService.addExecuteTask(new WriteCall(str));
        }
    }

    /* loaded from: classes.dex */
    private static class WriteCall implements Runnable {
        private Object mStr;

        public WriteCall(Object mStr) {
            this.mStr = mStr;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OSSLogToFileUtils.sLogFile != null) {
                OSSLogToFileUtils.getInstance();
                long logFileSize = OSSLogToFileUtils.getLogFileSize(OSSLogToFileUtils.sLogFile);
                OSSLog.logInfo("Log max size is: " + Formatter.formatFileSize(OSSLogToFileUtils.sContext, OSSLogToFileUtils.LOG_MAX_SIZE), false);
                OSSLog.logInfo("Log now size is: " + Formatter.formatFileSize(OSSLogToFileUtils.sContext, logFileSize), false);
                if (logFileSize > OSSLogToFileUtils.LOG_MAX_SIZE) {
                    OSSLogToFileUtils.getInstance().resetLogFile();
                }
                try {
                    PrintWriter pw = new PrintWriter((Writer) new FileWriter(OSSLogToFileUtils.sLogFile, true), true);
                    if (pw != null) {
                        OSSLog.logDebug("file exist:" + OSSLogToFileUtils.sLogFile.exists(), false);
                        OSSLog.logDebug("write data", false);
                        setBaseInfo(pw);
                        if (this.mStr instanceof Throwable) {
                            printEx(pw);
                        } else {
                            pw.println(OSSLogToFileUtils.getInstance().getFunctionInfo(null) + " - " + this.mStr.toString());
                        }
                        pw.println("------>end of log");
                        pw.println();
                        pw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private PrintWriter setBaseInfo(PrintWriter pw) {
            pw.println("android_version：" + Build.VERSION.RELEASE);
            pw.println("mobile_model：" + Build.MODEL);
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) OSSLogToFileUtils.sContext.getSystemService("connectivity")).getActiveNetworkInfo();
            String networkState = "unconnected";
            if (activeNetworkInfo != null && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                networkState = "connected";
            }
            if (!TextUtils.isEmpty(getOperatorName())) {
                pw.println("operator_name：" + getOperatorName());
            }
            pw.println("network_state：" + networkState);
            pw.println(new StringBuilder().append("network_type：").append(activeNetworkInfo).toString() != null ? activeNetworkInfo.getTypeName() : EnvironmentCompat.MEDIA_UNKNOWN);
            return pw;
        }

        private PrintWriter printEx(PrintWriter pw) {
            pw.println("crash_time：" + OSSLogToFileUtils.sLogSDF.format(new Date()));
            ((Throwable) this.mStr).printStackTrace(pw);
            return pw;
        }

        private String getOperatorName() {
            String operator = ((TelephonyManager) OSSLogToFileUtils.sContext.getSystemService("phone")).getSimOperator();
            if (operator == null) {
                return "";
            }
            if (operator.equals("46000") || operator.equals("46002")) {
                return "CMCC";
            }
            if (operator.equals("46001")) {
                return "CUCC";
            }
            if (operator.equals("46003")) {
                return "CTCC";
            }
            return "";
        }
    }
}
