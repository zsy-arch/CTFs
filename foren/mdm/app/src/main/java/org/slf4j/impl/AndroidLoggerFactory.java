package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class AndroidLoggerFactory implements ILoggerFactory {
    static final String ANONYMOUS_TAG = "null";
    static final int TAG_MAX_LENGTH = 23;
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap();

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String name) {
        String tag = loggerNameToTag(name);
        Logger logger = this.loggerMap.get(tag);
        if (logger != null) {
            return logger;
        }
        Logger newInstance = new AndroidLoggerAdapter(tag);
        Logger oldInstance = this.loggerMap.putIfAbsent(tag, newInstance);
        return oldInstance == null ? newInstance : oldInstance;
    }

    static String loggerNameToTag(String loggerName) {
        if (loggerName == null) {
            return "null";
        }
        int length = loggerName.length();
        if (length <= 23) {
            return loggerName;
        }
        int tagLength = 0;
        int lastTokenIndex = 0;
        StringBuilder tagName = new StringBuilder(26);
        do {
            int lastPeriodIndex = loggerName.indexOf(46, lastTokenIndex);
            if (lastPeriodIndex != -1) {
                tagName.append(loggerName.charAt(lastTokenIndex));
                if (lastPeriodIndex - lastTokenIndex > 1) {
                    tagName.append('*');
                }
                tagName.append('.');
                lastTokenIndex = lastPeriodIndex + 1;
                tagLength = tagName.length();
            } else {
                int tokenLength = length - lastTokenIndex;
                if (tagLength == 0 || tagLength + tokenLength > 23) {
                    return getSimpleName(loggerName);
                }
                tagName.append((CharSequence) loggerName, lastTokenIndex, length);
                return tagName.toString();
            }
        } while (tagLength <= 23);
        return getSimpleName(loggerName);
    }

    private static String getSimpleName(String loggerName) {
        int length = loggerName.length();
        int lastPeriodIndex = loggerName.lastIndexOf(46);
        return (lastPeriodIndex == -1 || length - (lastPeriodIndex + 1) > 23) ? '*' + loggerName.substring((length - 23) + 1) : loggerName.substring(lastPeriodIndex + 1);
    }
}
