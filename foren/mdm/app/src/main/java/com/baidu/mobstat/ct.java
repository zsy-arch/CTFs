package com.baidu.mobstat;

import android.net.LocalServerSocket;
import java.io.IOException;

/* loaded from: classes.dex */
public final class ct {
    private LocalServerSocket a;

    public final synchronized boolean a() {
        boolean z;
        if (this.a == null) {
            this.a = new LocalServerSocket("com.baidu.mobstat.bplus");
            z = true;
        }
        z = false;
        return z;
    }

    public final synchronized void b() {
        if (this.a != null) {
            try {
                this.a.close();
                this.a = null;
            } catch (IOException e) {
            }
        }
    }
}
