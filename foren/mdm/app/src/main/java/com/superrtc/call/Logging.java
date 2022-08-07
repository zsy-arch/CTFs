package com.superrtc.call;

import android.support.v4.internal.view.SupportMenu;
import com.superrtc.sdk.RtcConnection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: classes2.dex */
public class Logging {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$call$Logging$Severity;
    private static RtcConnection.LogListener fallbackLogger = RtcConnection.sLogListener;
    private static int loglevel = RtcConnection.loglevel;
    private static volatile boolean nativeLibLoaded;
    private static volatile boolean tracingEnabled;

    /* loaded from: classes2.dex */
    public enum Severity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableTracing(String str, int i, int i2);

    private static native void nativeLog(int i, String str, String str2);

    static /* synthetic */ int[] $SWITCH_TABLE$com$superrtc$call$Logging$Severity() {
        int[] iArr = $SWITCH_TABLE$com$superrtc$call$Logging$Severity;
        if (iArr == null) {
            iArr = new int[Severity.values().length];
            try {
                iArr[Severity.LS_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Severity.LS_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Severity.LS_SENSITIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Severity.LS_VERBOSE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Severity.LS_WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            $SWITCH_TABLE$com$superrtc$call$Logging$Severity = iArr;
        }
        return iArr;
    }

    static {
        try {
            System.loadLibrary("hyphenate_av");
            nativeLibLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            fallbackLogger.onLog(loglevel, "Failed to load hyphenate_av: ");
        }
    }

    /* loaded from: classes2.dex */
    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(1024),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(8192),
        TRACE_ALL(SupportMenu.USER_MASK);
        
        public final int level;

        TraceLevel(int level) {
            this.level = level;
        }
    }

    public static void enableLogThreads() {
        if (!nativeLibLoaded) {
            fallbackLogger.onLog(loglevel, "Cannot enable log thread because native lib not loaded.");
        } else {
            nativeEnableLogThreads();
        }
    }

    public static void enableLogTimeStamps() {
        if (!nativeLibLoaded) {
            fallbackLogger.onLog(loglevel, "Cannot enable log timestamps because native lib not loaded.");
        } else {
            nativeEnableLogTimeStamps();
        }
    }

    public static synchronized void enableTracing(String path, EnumSet<TraceLevel> levels, Severity severity) {
        synchronized (Logging.class) {
            if (!nativeLibLoaded) {
                fallbackLogger.onLog(loglevel, "Cannot enable tracing because native lib not loaded.");
            } else if (!tracingEnabled) {
                int nativeLevel = 0;
                Iterator it = levels.iterator();
                while (it.hasNext()) {
                    nativeLevel |= ((TraceLevel) it.next()).level;
                }
                nativeEnableTracing(path, nativeLevel, severity.ordinal());
                tracingEnabled = true;
            }
        }
    }

    public static void log(Severity severity, String tag, String message) {
        if (tracingEnabled) {
            nativeLog(severity.ordinal(), tag, message);
            return;
        }
        switch ($SWITCH_TABLE$com$superrtc$call$Logging$Severity()[severity.ordinal()]) {
            case 3:
                Level level = Level.INFO;
                break;
            case 4:
                Level level2 = Level.WARNING;
                break;
            case 5:
                Level level3 = Level.SEVERE;
                break;
            default:
                Level level4 = Level.FINE;
                break;
        }
        fallbackLogger.onLog(loglevel, String.valueOf(tag) + ": " + message);
    }

    public static void d(String tag, String message) {
        log(Severity.LS_INFO, tag, message);
    }

    public static void e(String tag, String message) {
        log(Severity.LS_ERROR, tag, message);
    }

    public static void w(String tag, String message) {
        log(Severity.LS_WARNING, tag, message);
    }

    public static void e(String tag, String message, Throwable e) {
        log(Severity.LS_ERROR, tag, message);
        log(Severity.LS_ERROR, tag, e.toString());
        log(Severity.LS_ERROR, tag, getStackTraceString(e));
    }

    public static void w(String tag, String message, Throwable e) {
        log(Severity.LS_WARNING, tag, message);
        log(Severity.LS_WARNING, tag, e.toString());
        log(Severity.LS_WARNING, tag, getStackTraceString(e));
    }

    public static void v(String tag, String message) {
        log(Severity.LS_VERBOSE, tag, message);
    }

    private static String getStackTraceString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
