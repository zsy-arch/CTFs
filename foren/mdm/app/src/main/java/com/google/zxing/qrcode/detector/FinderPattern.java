package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes2.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    public FinderPattern(float posX, float posY, float estimatedModuleSize) {
        this(posX, posY, estimatedModuleSize, 1);
    }

    private FinderPattern(float posX, float posY, float estimatedModuleSize, int count) {
        super(posX, posY);
        this.estimatedModuleSize = estimatedModuleSize;
        this.count = count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    public int getCount() {
        return this.count;
    }

    public boolean aboutEquals(float moduleSize, float i, float j) {
        if (Math.abs(i - getY()) > moduleSize || Math.abs(j - getX()) > moduleSize) {
            return false;
        }
        float moduleSizeDiff = Math.abs(moduleSize - this.estimatedModuleSize);
        if (moduleSizeDiff <= 1.0f || moduleSizeDiff <= this.estimatedModuleSize) {
            return true;
        }
        return false;
    }

    public FinderPattern combineEstimate(float i, float j, float newModuleSize) {
        int combinedCount = this.count + 1;
        return new FinderPattern(((this.count * getX()) + j) / combinedCount, ((this.count * getY()) + i) / combinedCount, ((this.count * this.estimatedModuleSize) + newModuleSize) / combinedCount, combinedCount);
    }
}
