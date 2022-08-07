package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.e;
import com.tencent.stat.a.f;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.p;

/* loaded from: classes2.dex */
public class k implements Runnable {
    private e a;
    private StatReportStrategy b;
    private c c = new l(this);

    public k(e eVar) {
        this.b = null;
        this.a = eVar;
        this.b = StatConfig.getStatSendStrategy();
    }

    private void a() {
        if (n.b().a() > 0) {
            n.b().a(this.a, (c) null);
            n.b().a(-1);
            return;
        }
        a(true);
    }

    private void a(boolean z) {
        d.b().a(this.a, this.c);
    }

    @Override // java.lang.Runnable
    public void run() {
        StatLogger statLogger;
        StatLogger statLogger2;
        StatLogger statLogger3;
        StatLogger statLogger4;
        StatLogger statLogger5;
        StatLogger statLogger6;
        try {
            if (StatConfig.isEnableStatService()) {
                if (this.a.a() == f.ERROR || this.a.d().length() <= StatConfig.getMaxReportEventLength()) {
                    if (StatConfig.getMaxSessionStatReportCount() > 0) {
                        if (StatConfig.getCurSessionStatReportCount() >= StatConfig.getMaxSessionStatReportCount()) {
                            statLogger5 = StatService.i;
                            statLogger5.e("Times for reporting events has reached the limit of StatConfig.getMaxSessionStatReportCount() in current session.");
                            return;
                        }
                        StatConfig.c();
                    }
                    statLogger2 = StatService.i;
                    statLogger2.i("Lauch stat task in thread:" + Thread.currentThread().getName());
                    Context c = this.a.c();
                    if (!com.tencent.stat.common.k.h(c)) {
                        n.a(c).a(this.a, (c) null);
                        return;
                    }
                    if (StatConfig.isEnableSmartReporting() && this.b != StatReportStrategy.ONLY_WIFI_NO_CACHE && com.tencent.stat.common.k.g(c)) {
                        this.b = StatReportStrategy.INSTANT;
                    }
                    switch (h.a[this.b.ordinal()]) {
                        case 1:
                            a();
                            return;
                        case 2:
                            if (com.tencent.stat.common.k.e(c)) {
                                a();
                                return;
                            } else {
                                n.a(c).a(this.a, (c) null);
                                return;
                            }
                        case 3:
                        case 4:
                            n.a(c).a(this.a, (c) null);
                            return;
                        case 5:
                            if (n.a(this.a.c()) != null) {
                                n.a(c).a(this.a, new m(this));
                                return;
                            }
                            return;
                        case 6:
                            try {
                                n.a(c).a(this.a, (c) null);
                                Long valueOf = Long.valueOf(p.a(c, "last_period_ts", 0L));
                                Long valueOf2 = Long.valueOf(System.currentTimeMillis());
                                if (Long.valueOf(Long.valueOf(valueOf2.longValue() - valueOf.longValue()).longValue() / 60000).longValue() > StatConfig.getSendPeriodMinutes()) {
                                    n.a(c).a(-1);
                                    p.b(c, "last_period_ts", valueOf2.longValue());
                                    return;
                                }
                                return;
                            } catch (Exception e) {
                                statLogger3 = StatService.i;
                                statLogger3.e(e);
                                return;
                            }
                        case 7:
                            if (com.tencent.stat.common.k.e(c)) {
                                a(false);
                                return;
                            }
                            return;
                        default:
                            statLogger4 = StatService.i;
                            statLogger4.error("Invalid stat strategy:" + StatConfig.getStatSendStrategy());
                            return;
                    }
                } else {
                    statLogger6 = StatService.i;
                    statLogger6.e("Event length exceed StatConfig.getMaxReportEventLength(): " + StatConfig.getMaxReportEventLength());
                }
            }
        } catch (Throwable th) {
            statLogger = StatService.i;
            statLogger.e(th);
        }
    }
}
