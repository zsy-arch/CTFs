package com.mob.tools.log;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;

/* loaded from: classes2.dex */
public abstract class NLog {
    private static HashMap<String, NLog> loggers = new HashMap<>();
    private static LogPrinter printer = new LogPrinter();

    static {
        MobUncaughtExceptionHandler.register();
    }

    public NLog() {
        loggers.put(getSDKTag(), this);
        if (loggers.size() == 1) {
            loggers.put("__FIRST__", this);
        }
    }

    protected static final NLog getInstanceForSDK(final String sdkTag, boolean createNew) {
        NLog instance = loggers.get(sdkTag);
        if (instance == null) {
            instance = loggers.get("__FIRST__");
        }
        return (instance != null || !createNew) ? instance : new NLog() { // from class: com.mob.tools.log.NLog.1
            @Override // com.mob.tools.log.NLog
            protected String getSDKTag() {
                return sdkTag;
            }
        };
    }

    public static void setCollector(String sdkTag, LogCollector collector) {
        printer.setCollector(sdkTag, collector);
    }

    public static void setContext(Context context) {
        if (context != null) {
            printer.setContext(context);
            NativeErrorHandler.prepare(context);
        }
    }

    public final int crash(Throwable t) {
        return printer.println(getSDKTag(), 6, 1, Log.getStackTraceString(t));
    }

    public final int d(Object format, Object... args) {
        String s = format.toString();
        return printer.println(getSDKTag(), 3, 0, args.length > 0 ? String.format(s, args) : s);
    }

    public final int d(Throwable t) {
        return printer.println(getSDKTag(), 3, 0, Log.getStackTraceString(t));
    }

    public final int d(Throwable throwable, Object format, Object... args) {
        String s = format.toString();
        StringBuilder sb = new StringBuilder();
        if (args.length > 0) {
            s = String.format(s, args);
        }
        return printer.println(getSDKTag(), 3, 0, sb.append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public final int e(Object format, Object... args) {
        String s = format.toString();
        return printer.println(getSDKTag(), 6, 0, args.length > 0 ? String.format(s, args) : s);
    }

    public final int e(Throwable t) {
        return printer.println(getSDKTag(), 6, 0, Log.getStackTraceString(t));
    }

    public final int e(Throwable throwable, Object format, Object... args) {
        String s = format.toString();
        StringBuilder sb = new StringBuilder();
        if (args.length > 0) {
            s = String.format(s, args);
        }
        return printer.println(getSDKTag(), 6, 0, sb.append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    protected abstract String getSDKTag();

    public final int i(Object format, Object... args) {
        String s = format.toString();
        return printer.println(getSDKTag(), 4, 0, args.length > 0 ? String.format(s, args) : s);
    }

    public final int i(Throwable t) {
        return printer.println(getSDKTag(), 4, 0, Log.getStackTraceString(t));
    }

    public final int i(Throwable throwable, Object format, Object... args) {
        String s = format.toString();
        StringBuilder sb = new StringBuilder();
        if (args.length > 0) {
            s = String.format(s, args);
        }
        return printer.println(getSDKTag(), 4, 0, sb.append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public final void nativeCrashLog(String log) {
        printer.nativeCrashLog(getSDKTag(), log);
    }

    public final int v(Object format, Object... args) {
        String s = format.toString();
        return printer.println(getSDKTag(), 2, 0, args.length > 0 ? String.format(s, args) : s);
    }

    public final int v(Throwable t) {
        return printer.println(getSDKTag(), 2, 0, Log.getStackTraceString(t));
    }

    public final int v(Throwable throwable, Object format, Object... args) {
        String s = format.toString();
        StringBuilder sb = new StringBuilder();
        if (args.length > 0) {
            s = String.format(s, args);
        }
        return printer.println(getSDKTag(), 2, 0, sb.append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public final int w(Object format, Object... args) {
        String s = format.toString();
        return printer.println(getSDKTag(), 5, 0, args.length > 0 ? String.format(s, args) : s);
    }

    public final int w(Throwable t) {
        return printer.println(getSDKTag(), 5, 0, Log.getStackTraceString(t));
    }

    public final int w(Throwable throwable, Object format, Object... args) {
        String s = format.toString();
        StringBuilder sb = new StringBuilder();
        if (args.length > 0) {
            s = String.format(s, args);
        }
        return printer.println(getSDKTag(), 5, 0, sb.append(s).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }
}
