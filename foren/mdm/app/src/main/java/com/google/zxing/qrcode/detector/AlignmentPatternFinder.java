package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
final class AlignmentPatternFinder {
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final int[] crossCheckStateCount = new int[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPatternFinder(BitMatrix image, int startX, int startY, int width, int height, float moduleSize, ResultPointCallback resultPointCallback) {
        this.image = image;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.moduleSize = moduleSize;
        this.resultPointCallback = resultPointCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPattern find() throws NotFoundException {
        AlignmentPattern confirmed;
        AlignmentPattern confirmed2;
        int startX = this.startX;
        int height = this.height;
        int maxJ = startX + this.width;
        int middleI = this.startY + (height / 2);
        int[] stateCount = new int[3];
        for (int iGen = 0; iGen < height; iGen++) {
            int i = middleI + ((iGen & 1) == 0 ? (iGen + 1) / 2 : -((iGen + 1) / 2));
            stateCount[0] = 0;
            stateCount[1] = 0;
            stateCount[2] = 0;
            int j = startX;
            while (j < maxJ && !this.image.get(j, i)) {
                j++;
            }
            int currentState = 0;
            while (j < maxJ) {
                if (!this.image.get(j, i)) {
                    if (currentState == 1) {
                        currentState++;
                    }
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (currentState == 1) {
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (currentState != 2) {
                    currentState++;
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (foundPatternCross(stateCount) && (confirmed2 = handlePossibleCenter(stateCount, i, j)) != null) {
                    return confirmed2;
                } else {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = 1;
                    stateCount[2] = 0;
                    currentState = 1;
                }
                j++;
            }
            if (foundPatternCross(stateCount) && (confirmed = handlePossibleCenter(stateCount, i, maxJ)) != null) {
                return confirmed;
            }
        }
        if (!this.possibleCenters.isEmpty()) {
            return this.possibleCenters.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static float centerFromEnd(int[] stateCount, int end) {
        return (end - stateCount[2]) - (stateCount[1] / 2.0f);
    }

    private boolean foundPatternCross(int[] stateCount) {
        float moduleSize = this.moduleSize;
        float maxVariance = moduleSize / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(moduleSize - stateCount[i]) >= maxVariance) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
        if (r3[1] <= r13) goto L_0x0038;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
        r3[0] = r3[0] + 1;
        r0 = r0 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r0 < 0) goto L_0x0044;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r1.get(r12, r0) != false) goto L_0x0044;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r3[0] <= r13) goto L_0x0030;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        if (r3[0] > r13) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        r0 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
        if (r0 >= r2) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        if (r1.get(r12, r0) == false) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        if (r3[1] <= r13) goto L_0x008c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0056, code lost:
        if (r0 == r2) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r3[1] > r13) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
        if (r0 >= r2) goto L_0x0068;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r1.get(r12, r0) != false) goto L_0x0068;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
        if (r3[2] <= r13) goto L_0x0095;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006a, code lost:
        if (r3[2] > r13) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
        if ((java.lang.Math.abs(((r3[0] + r3[1]) + r3[2]) - r14) * 5) >= (r14 * 2)) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0085, code lost:
        if (foundPatternCross(r3) == false) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008c, code lost:
        r3[1] = r3[1] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0095, code lost:
        r3[2] = r3[2] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:?, code lost:
        return centerFromEnd(r3, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckVertical(int r11, int r12, int r13, int r14) {
        /*
            r10 = this;
            r9 = 2
            r5 = 2143289344(0x7fc00000, float:NaN)
            r8 = 1
            r7 = 0
            com.google.zxing.common.BitMatrix r1 = r10.image
            int r2 = r1.getHeight()
            int[] r3 = r10.crossCheckStateCount
            r3[r7] = r7
            r3[r8] = r7
            r3[r9] = r7
            r0 = r11
        L_0x0014:
            if (r0 < 0) goto L_0x0020
            boolean r6 = r1.get(r12, r0)
            if (r6 == 0) goto L_0x0020
            r6 = r3[r8]
            if (r6 <= r13) goto L_0x0027
        L_0x0020:
            if (r0 < 0) goto L_0x0026
            r6 = r3[r8]
            if (r6 <= r13) goto L_0x0038
        L_0x0026:
            return r5
        L_0x0027:
            r6 = r3[r8]
            int r6 = r6 + 1
            r3[r8] = r6
            int r0 = r0 + (-1)
            goto L_0x0014
        L_0x0030:
            r6 = r3[r7]
            int r6 = r6 + 1
            r3[r7] = r6
            int r0 = r0 + (-1)
        L_0x0038:
            if (r0 < 0) goto L_0x0044
            boolean r6 = r1.get(r12, r0)
            if (r6 != 0) goto L_0x0044
            r6 = r3[r7]
            if (r6 <= r13) goto L_0x0030
        L_0x0044:
            r6 = r3[r7]
            if (r6 > r13) goto L_0x0026
            int r0 = r11 + 1
        L_0x004a:
            if (r0 >= r2) goto L_0x0056
            boolean r6 = r1.get(r12, r0)
            if (r6 == 0) goto L_0x0056
            r6 = r3[r8]
            if (r6 <= r13) goto L_0x008c
        L_0x0056:
            if (r0 == r2) goto L_0x0026
            r6 = r3[r8]
            if (r6 > r13) goto L_0x0026
        L_0x005c:
            if (r0 >= r2) goto L_0x0068
            boolean r6 = r1.get(r12, r0)
            if (r6 != 0) goto L_0x0068
            r6 = r3[r9]
            if (r6 <= r13) goto L_0x0095
        L_0x0068:
            r6 = r3[r9]
            if (r6 > r13) goto L_0x0026
            r6 = r3[r7]
            r7 = r3[r8]
            int r6 = r6 + r7
            r7 = r3[r9]
            int r4 = r6 + r7
            int r6 = r4 - r14
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 * 5
            int r7 = r14 * 2
            if (r6 >= r7) goto L_0x0026
            boolean r6 = r10.foundPatternCross(r3)
            if (r6 == 0) goto L_0x0026
            float r5 = centerFromEnd(r3, r0)
            goto L_0x0026
        L_0x008c:
            r6 = r3[r8]
            int r6 = r6 + 1
            r3[r8] = r6
            int r0 = r0 + 1
            goto L_0x004a
        L_0x0095:
            r6 = r3[r9]
            int r6 = r6 + 1
            r3[r9] = r6
            int r0 = r0 + 1
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.AlignmentPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    private AlignmentPattern handlePossibleCenter(int[] stateCount, int i, int j) {
        int stateCountTotal = stateCount[0] + stateCount[1] + stateCount[2];
        float centerJ = centerFromEnd(stateCount, j);
        float centerI = crossCheckVertical(i, (int) centerJ, stateCount[1] * 2, stateCountTotal);
        if (!Float.isNaN(centerI)) {
            float estimatedModuleSize = ((stateCount[0] + stateCount[1]) + stateCount[2]) / 3.0f;
            for (AlignmentPattern center : this.possibleCenters) {
                if (center.aboutEquals(estimatedModuleSize, centerI, centerJ)) {
                    return center.combineEstimate(centerI, centerJ, estimatedModuleSize);
                }
            }
            AlignmentPattern point = new AlignmentPattern(centerJ, centerI, estimatedModuleSize);
            this.possibleCenters.add(point);
            if (this.resultPointCallback != null) {
                this.resultPointCallback.foundPossibleResultPoint(point);
            }
        }
        return null;
    }
}
