package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import java.util.Timer;

/* loaded from: classes.dex */
public class bt implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ bs b;

    public bt(bs bsVar, Context context) {
        this.b = bsVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        Timer timer;
        SendStrategyEnum sendStrategyEnum;
        SendStrategyEnum sendStrategyEnum2;
        Handler handler;
        int i;
        Timer timer2;
        timer = this.b.e;
        if (timer != null) {
            timer2 = this.b.e;
            timer2.cancel();
            this.b.e = null;
        }
        this.b.c = SendStrategyEnum.values()[be.a().a(this.a)];
        this.b.d = be.a().b(this.a);
        this.b.b = be.a().c(this.a);
        sendStrategyEnum = this.b.c;
        if (sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.b.d(this.a);
        } else {
            sendStrategyEnum2 = this.b.c;
            if (sendStrategyEnum2.equals(SendStrategyEnum.ONCE_A_DAY)) {
                this.b.d(this.a);
            }
        }
        handler = this.b.i;
        bu buVar = new bu(this);
        i = this.b.f;
        handler.postDelayed(buVar, i * 1000);
    }
}
