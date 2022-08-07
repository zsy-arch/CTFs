package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

/* loaded from: classes.dex */
public final class FinderPattern {
    private final ResultPoint[] resultPoints;
    private final int[] startEnd;
    private final int value;

    public FinderPattern(int value, int[] startEnd, int start, int end, int rowNumber) {
        this.value = value;
        this.startEnd = startEnd;
        this.resultPoints = new ResultPoint[]{new ResultPoint(start, rowNumber), new ResultPoint(end, rowNumber)};
    }

    public int getValue() {
        return this.value;
    }

    public int[] getStartEnd() {
        return this.startEnd;
    }

    public ResultPoint[] getResultPoints() {
        return this.resultPoints;
    }

    public boolean equals(Object o) {
        return (o instanceof FinderPattern) && this.value == ((FinderPattern) o).value;
    }

    public int hashCode() {
        return this.value;
    }
}
