package com.vsf2f.f2f.ui.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class ThreadPool {
    private static ExecutorService cachedThreadPool;

    public static void newThreadPool(Runnable runnable) {
        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        cachedThreadPool.execute(runnable);
    }
}
