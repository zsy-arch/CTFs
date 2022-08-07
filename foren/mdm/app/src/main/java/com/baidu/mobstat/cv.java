package com.baidu.mobstat;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* loaded from: classes.dex */
final class cv implements Comparator<ScanResult> {
    /* renamed from: a */
    public int compare(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
