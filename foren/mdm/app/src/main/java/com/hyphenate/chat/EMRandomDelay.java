package com.hyphenate.chat;

import java.util.Random;

/* loaded from: classes2.dex */
class EMRandomDelay {
    public int timeDelay(int i) {
        return i == 0 ? new Random().nextInt(5) + 1 : i == 1 ? new Random().nextInt(54) + 6 : new Random().nextInt(540) + 60;
    }
}
