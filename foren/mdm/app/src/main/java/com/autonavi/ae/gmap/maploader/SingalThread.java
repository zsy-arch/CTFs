package com.autonavi.ae.gmap.maploader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class SingalThread extends Thread {
    private final Lock mLock = new ReentrantLock();
    private final Condition mWaiting = this.mLock.newCondition();
    private boolean isWaiting = true;
    public String logTag = "SingalThread";

    public void doWait() throws InterruptedException {
        try {
            this.mLock.lock();
            this.isWaiting = true;
            this.mWaiting.await();
        } finally {
            this.mLock.unlock();
        }
    }

    public void doAwake() {
        if (this.isWaiting) {
            try {
                this.mLock.lock();
                this.isWaiting = false;
                this.mWaiting.signal();
            } finally {
                this.mLock.unlock();
            }
        }
    }
}
