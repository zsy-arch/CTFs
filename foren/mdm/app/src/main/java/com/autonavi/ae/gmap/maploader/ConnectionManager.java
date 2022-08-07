package com.autonavi.ae.gmap.maploader;

import com.autonavi.ae.gmap.GLMapEngine;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class ConnectionManager extends SingalThread {
    private static final int MAX_THREAD_COUNT = 5;
    GLMapEngine mGLMapEngine;
    public boolean threadFlag = true;
    private ExecutorService mThreadPool = Executors.newFixedThreadPool(5);
    private ArrayList<AsMapRequestor> mThreadPoolList = new ArrayList<>();
    private ArrayList<BaseMapLoader> mConnPool = new ArrayList<>();

    public ConnectionManager(GLMapEngine gLMapEngine) {
        this.mGLMapEngine = gLMapEngine;
    }

    public void shutDown() {
        if (this.mConnPool != null) {
            try {
                this.mThreadPool.shutdownNow();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void insertConntionTask(BaseMapLoader baseMapLoader) {
        synchronized (this.mConnPool) {
            this.mConnPool.add(baseMapLoader);
        }
        try {
            doAwake();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void checkListPool() {
        ArrayList arrayList = new ArrayList();
        int size = this.mThreadPoolList.size();
        for (int i = 0; i < size; i++) {
            AsMapRequestor asMapRequestor = this.mThreadPoolList.get(i);
            BaseMapLoader baseMapLoader = asMapRequestor.mMapLoader;
            if (!baseMapLoader.isRequestValid() || baseMapLoader.hasFinished()) {
                arrayList.add(asMapRequestor);
                baseMapLoader.doCancel();
            }
        }
        this.mThreadPoolList.removeAll(arrayList);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            doAsyncRequest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void doAsyncRequest() {
        boolean z;
        while (this.threadFlag) {
            checkListPool();
            while (true) {
                if (this.mThreadPoolList.size() <= 5) {
                    synchronized (this.mConnPool) {
                        if (this.mConnPool.size() <= 0) {
                            z = false;
                            break;
                        }
                        BaseMapLoader remove = this.mConnPool.remove(0);
                        if (remove != null) {
                            AsMapRequestor asMapRequestor = new AsMapRequestor(remove);
                            this.mThreadPoolList.add(asMapRequestor);
                            if (!this.mThreadPool.isShutdown()) {
                                this.mThreadPool.execute(asMapRequestor);
                                continue;
                            } else {
                                continue;
                            }
                        }
                        if (remove == null) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                try {
                    sleep(30L);
                } catch (Exception e) {
                }
            } else {
                try {
                    if (this.threadFlag) {
                        doWait();
                    }
                } catch (Throwable th) {
                }
            }
        }
    }
}
