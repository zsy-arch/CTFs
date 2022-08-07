package com.cdlinglu.utils;

import android.content.Context;
import com.hy.frame.util.AppShare;

/* loaded from: classes.dex */
public class GuideUtil {
    private static final String FOUND_FRAGMENT = "guide_foundfragment";
    private static final String MINE_FRAGMENT = "guide_minefragment1";
    private static final String RED_PACKET1 = "guide_redpacket1";
    private static final String RED_PACKET2 = "guide_redpacket2";

    public static boolean getMineFragment(Context context) {
        return !AppShare.get(context).getBoolean(MINE_FRAGMENT);
    }

    public static void setMineFragment(Context context) {
        AppShare.get(context).putBoolean(MINE_FRAGMENT, true);
    }

    public static boolean getDiscoverFragment(Context context) {
        return !AppShare.get(context).getBoolean(FOUND_FRAGMENT);
    }

    public static void setDiscoverFragment(Context context) {
        AppShare.get(context).putBoolean(FOUND_FRAGMENT, true);
    }

    public static boolean getRedPacketFragment(Context context) {
        return !AppShare.get(context).getBoolean(RED_PACKET1);
    }

    public static void setRedPacketFragment(Context context) {
        AppShare.get(context).putBoolean(RED_PACKET1, true);
    }

    public static boolean getRedPacketFragment2(Context context) {
        return !AppShare.get(context).getBoolean(RED_PACKET2);
    }

    public static void setRedPacketFragment2(Context context) {
        AppShare.get(context).putBoolean(RED_PACKET2, true);
    }
}
