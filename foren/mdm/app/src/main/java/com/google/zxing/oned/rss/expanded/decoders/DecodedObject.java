package com.google.zxing.oned.rss.expanded.decoders;

/* loaded from: classes.dex */
abstract class DecodedObject {
    private final int newPosition;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedObject(int newPosition) {
        this.newPosition = newPosition;
    }

    final int getNewPosition() {
        return this.newPosition;
    }
}
