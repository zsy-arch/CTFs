package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.d;
import com.tencent.stat.common.StatLogger;
import java.lang.Thread;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class g implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ Context a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(Context context) {
        this.a = context;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        StatLogger statLogger;
        StatLogger statLogger2;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        StatLogger statLogger3;
        StatLogger statLogger4;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler2;
        if (StatConfig.isEnableStatService()) {
            n.a(this.a).a(new d(this.a, StatService.a(this.a, false), 2, th), (c) null);
            statLogger = StatService.i;
            statLogger.debug("MTA has caught the following uncaught exception:");
            statLogger2 = StatService.i;
            statLogger2.error(th);
            uncaughtExceptionHandler = StatService.j;
            if (uncaughtExceptionHandler != null) {
                statLogger4 = StatService.i;
                statLogger4.debug("Call the original uncaught exception handler.");
                uncaughtExceptionHandler2 = StatService.j;
                uncaughtExceptionHandler2.uncaughtException(thread, th);
                return;
            }
            statLogger3 = StatService.i;
            statLogger3.debug("Original uncaught exception handler not set.");
        }
    }
}
