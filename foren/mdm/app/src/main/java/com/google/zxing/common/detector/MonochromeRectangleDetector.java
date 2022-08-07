package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix image) {
        this.image = image;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        int deltaY = Math.max(1, height / 256);
        int deltaX = Math.max(1, width / 256);
        int top = ((int) findCornerFromCenter(halfWidth, 0, 0, width, halfHeight, -deltaY, 0, height, halfWidth / 2).getY()) - 1;
        ResultPoint pointB = findCornerFromCenter(halfWidth, -deltaX, 0, width, halfHeight, 0, top, height, halfHeight / 2);
        int left = ((int) pointB.getX()) - 1;
        ResultPoint pointC = findCornerFromCenter(halfWidth, deltaX, left, width, halfHeight, 0, top, height, halfHeight / 2);
        int right = ((int) pointC.getX()) + 1;
        ResultPoint pointD = findCornerFromCenter(halfWidth, 0, left, right, halfHeight, deltaY, top, height, halfWidth / 2);
        return new ResultPoint[]{findCornerFromCenter(halfWidth, 0, left, right, halfHeight, -deltaY, top, ((int) pointD.getY()) + 1, halfWidth / 4), pointB, pointC, pointD};
    }

    private ResultPoint findCornerFromCenter(int centerX, int deltaX, int left, int right, int centerY, int deltaY, int top, int bottom, int maxWhiteRun) throws NotFoundException {
        int[] range;
        int[] lastRange = null;
        int y = centerY;
        int x = centerX;
        while (y < bottom && y >= top && x < right && x >= left) {
            if (deltaX == 0) {
                range = blackWhiteRange(y, maxWhiteRun, left, right, true);
            } else {
                range = blackWhiteRange(x, maxWhiteRun, top, bottom, false);
            }
            if (range != null) {
                lastRange = range;
                y += deltaY;
                x += deltaX;
            } else if (lastRange == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (deltaX == 0) {
                int lastY = y - deltaY;
                if (lastRange[0] >= centerX) {
                    return new ResultPoint(lastRange[1], lastY);
                }
                if (lastRange[1] <= centerX) {
                    return new ResultPoint(lastRange[0], lastY);
                }
                return new ResultPoint(deltaY > 0 ? lastRange[0] : lastRange[1], lastY);
            } else {
                int lastX = x - deltaX;
                if (lastRange[0] >= centerY) {
                    return new ResultPoint(lastX, lastRange[1]);
                }
                if (lastRange[1] <= centerY) {
                    return new ResultPoint(lastX, lastRange[0]);
                }
                return new ResultPoint(lastX, deltaX < 0 ? lastRange[0] : lastRange[1]);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x003e A[EDGE_INSN: B:67:0x003e->B:22:0x003e ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0073 A[EDGE_INSN: B:76:0x0073->B:40:0x0073 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] blackWhiteRange(int r8, int r9, int r10, int r11, boolean r12) {
        /*
            r7 = this;
            int r5 = r10 + r11
            int r0 = r5 / 2
            r2 = r0
        L_0x0005:
            if (r2 >= r10) goto L_0x001a
        L_0x0007:
            int r2 = r2 + 1
            r1 = r0
        L_0x000a:
            if (r1 < r11) goto L_0x004f
        L_0x000c:
            int r1 = r1 + (-1)
            if (r1 <= r2) goto L_0x0084
            r5 = 2
            int[] r5 = new int[r5]
            r6 = 0
            r5[r6] = r2
            r6 = 1
            r5[r6] = r1
        L_0x0019:
            return r5
        L_0x001a:
            if (r12 == 0) goto L_0x0027
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r2, r8)
            if (r5 == 0) goto L_0x002f
        L_0x0024:
            int r2 = r2 + (-1)
            goto L_0x0005
        L_0x0027:
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r8, r2)
            if (r5 != 0) goto L_0x0024
        L_0x002f:
            r4 = r2
        L_0x0030:
            int r2 = r2 + (-1)
            if (r2 < r10) goto L_0x003e
            if (r12 == 0) goto L_0x0046
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r2, r8)
            if (r5 == 0) goto L_0x0030
        L_0x003e:
            int r3 = r4 - r2
            if (r2 < r10) goto L_0x0044
            if (r3 <= r9) goto L_0x0005
        L_0x0044:
            r2 = r4
            goto L_0x0007
        L_0x0046:
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r8, r2)
            if (r5 == 0) goto L_0x0030
            goto L_0x003e
        L_0x004f:
            if (r12 == 0) goto L_0x005c
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r1, r8)
            if (r5 == 0) goto L_0x0064
        L_0x0059:
            int r1 = r1 + 1
            goto L_0x000a
        L_0x005c:
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r8, r1)
            if (r5 != 0) goto L_0x0059
        L_0x0064:
            r4 = r1
        L_0x0065:
            int r1 = r1 + 1
            if (r1 >= r11) goto L_0x0073
            if (r12 == 0) goto L_0x007b
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r1, r8)
            if (r5 == 0) goto L_0x0065
        L_0x0073:
            int r3 = r1 - r4
            if (r1 >= r11) goto L_0x0079
            if (r3 <= r9) goto L_0x000a
        L_0x0079:
            r1 = r4
            goto L_0x000c
        L_0x007b:
            com.google.zxing.common.BitMatrix r5 = r7.image
            boolean r5 = r5.get(r8, r1)
            if (r5 == 0) goto L_0x0065
            goto L_0x0073
        L_0x0084:
            r5 = 0
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }
}
