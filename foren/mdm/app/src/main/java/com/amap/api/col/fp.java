package com.amap.api.col;

import com.amap.api.services.core.AMapException;

/* compiled from: StrategyConvert.java */
/* loaded from: classes.dex */
public class fp {
    public static int[] a(int i) {
        int[] iArr = new int[2];
        switch (i) {
            case 0:
                iArr[0] = 0;
                iArr[1] = 266280;
                break;
            case 1:
                iArr[0] = 1;
                iArr[1] = 790568;
                break;
            case 2:
                iArr[0] = 2;
                iArr[1] = 266280;
                break;
            case 3:
                iArr[0] = 5;
                iArr[1] = 266280;
                break;
            case 4:
                iArr[0] = 4;
                iArr[1] = 266280;
                break;
            case 5:
                iArr[0] = 9;
                iArr[1] = 266280;
                break;
            case 6:
                iArr[0] = 0;
                iArr[1] = 2363432;
                break;
            case 7:
                iArr[0] = 1;
                iArr[1] = 2363432;
                break;
            case 8:
                iArr[0] = 12;
                iArr[1] = 266280;
                break;
            case 9:
                iArr[0] = 12;
                iArr[1] = 2363432;
                break;
            case 10:
                iArr[0] = 13;
                iArr[1] = 8786040;
                break;
            case 11:
                iArr[0] = 9;
                iArr[1] = 8786040;
                break;
            case 12:
                iArr[0] = 4;
                iArr[1] = 8786040;
                break;
            case 13:
                iArr[0] = 0;
                iArr[1] = 10883192;
                break;
            case 14:
                iArr[0] = 1;
                iArr[1] = 8786040;
                break;
            case 15:
                iArr[0] = 4;
                iArr[1] = 10883192;
                break;
            case 16:
                iArr[0] = 1;
                iArr[1] = 10883192;
                break;
            case 17:
                iArr[0] = 12;
                iArr[1] = 8786040;
                break;
            case 18:
                iArr[0] = 12;
                iArr[1] = 10883192;
                break;
            case 19:
                iArr[0] = 0;
                iArr[1] = 8786042;
                break;
            case 20:
                iArr[0] = 4;
                iArr[1] = 8786042;
                break;
        }
        return iArr;
    }

    public static int a(boolean z, boolean z2, boolean z3, boolean z4) {
        try {
            switch (Integer.parseInt((z ? "1" : "0") + (z2 ? "1" : "0") + (z3 ? "1" : "0") + (z4 ? "1" : "0"))) {
                case 0:
                    return 10;
                case 1:
                    return 19;
                case 10:
                    return 14;
                case 11:
                    throw new IllegalArgumentException("高速优先与避免收费不能同时为true");
                case 100:
                    return 13;
                case 101:
                    throw new IllegalArgumentException("高速优先与不走高速不能同时为true");
                case 110:
                    return 16;
                case 1000:
                    return 12;
                case 1001:
                    return 20;
                case 1010:
                    return 17;
                case AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR /* 1100 */:
                    return 15;
                case 1110:
                    return 18;
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
