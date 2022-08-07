package com.tencent.stat;

/* loaded from: classes2.dex */
public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6),
    ONLY_WIFI_NO_CACHE(7);
    
    int a;

    StatReportStrategy(int i) {
        this.a = i;
    }

    public static StatReportStrategy getStatReportStrategy(int i) {
        StatReportStrategy[] values = values();
        for (StatReportStrategy statReportStrategy : values) {
            if (i == statReportStrategy.a()) {
                return statReportStrategy;
            }
        }
        return null;
    }

    public int a() {
        return this.a;
    }
}
