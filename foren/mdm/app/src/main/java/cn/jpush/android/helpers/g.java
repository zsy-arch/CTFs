package cn.jpush.android.helpers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public final class g {
    private static Queue<Integer> a = new ConcurrentLinkedQueue();

    public static int a() {
        if (a.size() > 0) {
            return a.poll().intValue();
        }
        return 0;
    }

    public static boolean a(int i) {
        return a.offer(Integer.valueOf(i));
    }

    public static int b() {
        return a.size();
    }

    public static boolean b(int i) {
        return a.contains(Integer.valueOf(i));
    }
}
