package com.hy.frame.view.recycler.xRefreshView;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes2.dex */
public class LogUtils {
    public static CustomLogger customLogger;
    public static String customTagPrefix = "";
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    /* loaded from: classes2.dex */
    public interface CustomLogger {
        void d(String str, String str2);

        void d(String str, String str2, Throwable th);

        void e(String str, String str2);

        void e(String str, String str2, Throwable th);

        void i(String str, String str2);

        void i(String str, String str2, Throwable th);

        void v(String str, String str2);

        void v(String str, String str2, Throwable th);

        void w(String str, String str2);

        void w(String str, String str2, Throwable th);

        void w(String str, Throwable th);

        void wtf(String str, String str2);

        void wtf(String str, String str2, Throwable th);

        void wtf(String str, Throwable th);
    }

    private LogUtils() {
    }

    public static void enableLog(boolean enable) {
        allowD = enable;
        allowE = enable;
        allowI = enable;
        allowV = enable;
        allowW = enable;
    }

    private static String generateTag(StackTraceElement caller) {
        String callerClazzName = caller.getClassName();
        String tag = String.format("%s.%s(L:%d)", callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1), caller.getMethodName(), Integer.valueOf(caller.getLineNumber()));
        if (TextUtils.isEmpty(customTagPrefix)) {
            return tag;
        }
        return customTagPrefix + ":" + tag;
    }

    public static void d(String content) {
        if (allowD) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.d(tag, content);
            } else {
                Log.d(tag, content);
            }
        }
    }

    public static void d(String content, Throwable tr) {
        if (allowD) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.d(tag, content, tr);
            } else {
                Log.d(tag, content, tr);
            }
        }
    }

    public static void e(String content) {
        if (allowE) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.e(tag, content);
            } else {
                Log.e(tag, content);
            }
        }
    }

    public static void e(String content, Throwable tr) {
        if (allowE) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.e(tag, content, tr);
            } else {
                Log.e(tag, content, tr);
            }
        }
    }

    public static void i(String content) {
        if (allowI) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.i(tag, content);
            } else {
                Log.i(tag, content);
            }
        }
    }

    public static void i(String content, Throwable tr) {
        if (allowI) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.i(tag, content, tr);
            } else {
                Log.i(tag, content, tr);
            }
        }
    }

    public static void v(String content) {
        if (allowV) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.v(tag, content);
            } else {
                Log.v(tag, content);
            }
        }
    }

    public static void v(String content, Throwable tr) {
        if (allowV) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.v(tag, content, tr);
            } else {
                Log.v(tag, content, tr);
            }
        }
    }

    public static void w(String content) {
        if (allowW) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.w(tag, content);
            } else {
                Log.w(tag, content);
            }
        }
    }

    public static void w(String content, Throwable tr) {
        if (allowW) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.w(tag, content, tr);
            } else {
                Log.w(tag, content, tr);
            }
        }
    }

    public static void w(Throwable tr) {
        if (allowW) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.w(tag, tr);
            } else {
                Log.w(tag, tr);
            }
        }
    }

    public static void wtf(String content) {
        if (allowWtf) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(tag, content);
            } else {
                Log.wtf(tag, content);
            }
        }
    }

    public static void wtf(String content, Throwable tr) {
        if (allowWtf) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(tag, content, tr);
            } else {
                Log.wtf(tag, content, tr);
            }
        }
    }

    public static void wtf(Throwable tr) {
        if (allowWtf) {
            String tag = generateTag(getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(tag, tr);
            } else {
                Log.wtf(tag, tr);
            }
        }
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
