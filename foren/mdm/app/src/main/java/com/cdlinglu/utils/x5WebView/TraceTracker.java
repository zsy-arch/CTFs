package com.cdlinglu.utils.x5WebView;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TraceTracker {
    private static Map<String, TaskRecord> records = new HashMap();
    private static TraceTracker traceTracker;

    public static synchronized TraceTracker getInstance() {
        TraceTracker traceTracker2;
        synchronized (TraceTracker.class) {
            if (traceTracker == null) {
                traceTracker = new TraceTracker();
            }
            traceTracker2 = traceTracker;
        }
        return traceTracker2;
    }

    public void startTrace(String tag, boolean isDisplayResultImmediately) {
        if (!records.containsKey(tag)) {
            TaskRecord record = new TaskRecord();
            record.startTrace(tag, isDisplayResultImmediately);
            records.put(tag, record);
            return;
        }
        records.get(tag).startTrace(tag, isDisplayResultImmediately);
    }

    public void endingTrace(String tag) {
        if (records.containsKey(tag)) {
            records.get(tag).endingTrace(tag);
        }
    }
}
