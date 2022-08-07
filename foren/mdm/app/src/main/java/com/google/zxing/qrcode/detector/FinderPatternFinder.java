package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public FinderPatternFinder(BitMatrix image) {
        this(image, null);
    }

    public FinderPatternFinder(BitMatrix image, ResultPointCallback resultPointCallback) {
        this.image = image;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    protected final BitMatrix getImage() {
        return this.image;
    }

    protected final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> hints) throws NotFoundException {
        boolean tryHarder = hints != null && hints.containsKey(DecodeHintType.TRY_HARDER);
        boolean pureBarcode = hints != null && hints.containsKey(DecodeHintType.PURE_BARCODE);
        int maxI = this.image.getHeight();
        int maxJ = this.image.getWidth();
        int iSkip = (maxI * 3) / 228;
        if (iSkip < 3 || tryHarder) {
            iSkip = 3;
        }
        boolean done = false;
        int[] stateCount = new int[5];
        int i = iSkip - 1;
        while (i < maxI && !done) {
            stateCount[0] = 0;
            stateCount[1] = 0;
            stateCount[2] = 0;
            stateCount[3] = 0;
            stateCount[4] = 0;
            int currentState = 0;
            int j = 0;
            while (j < maxJ) {
                if (this.image.get(j, i)) {
                    if ((currentState & 1) == 1) {
                        currentState++;
                    }
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if ((currentState & 1) != 0) {
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (currentState != 4) {
                    currentState++;
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if (!foundPatternCross(stateCount)) {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = stateCount[3];
                    stateCount[2] = stateCount[4];
                    stateCount[3] = 1;
                    stateCount[4] = 0;
                    currentState = 3;
                } else if (handlePossibleCenter(stateCount, i, j, pureBarcode)) {
                    iSkip = 2;
                    if (this.hasSkipped) {
                        done = haveMultiplyConfirmedCenters();
                    } else {
                        int rowSkip = findRowSkip();
                        if (rowSkip > stateCount[2]) {
                            i += (rowSkip - stateCount[2]) - 2;
                            j = maxJ - 1;
                        }
                    }
                    currentState = 0;
                    stateCount[0] = 0;
                    stateCount[1] = 0;
                    stateCount[2] = 0;
                    stateCount[3] = 0;
                    stateCount[4] = 0;
                } else {
                    stateCount[0] = stateCount[2];
                    stateCount[1] = stateCount[3];
                    stateCount[2] = stateCount[4];
                    stateCount[3] = 1;
                    stateCount[4] = 0;
                    currentState = 3;
                }
                j++;
            }
            if (foundPatternCross(stateCount) && handlePossibleCenter(stateCount, i, maxJ, pureBarcode)) {
                iSkip = stateCount[0];
                if (this.hasSkipped) {
                    done = haveMultiplyConfirmedCenters();
                }
            }
            i += iSkip;
        }
        FinderPattern[] patternInfo = selectBestPatterns();
        ResultPoint.orderBestPatterns(patternInfo);
        return new FinderPatternInfo(patternInfo);
    }

    private static float centerFromEnd(int[] stateCount, int end) {
        return ((end - stateCount[4]) - stateCount[3]) - (stateCount[2] / 2.0f);
    }

    protected static boolean foundPatternCross(int[] stateCount) {
        int totalModuleSize = 0;
        for (int i = 0; i < 5; i++) {
            int count = stateCount[i];
            if (count == 0) {
                return false;
            }
            totalModuleSize += count;
        }
        if (totalModuleSize < 7) {
            return false;
        }
        float moduleSize = totalModuleSize / 7.0f;
        float maxVariance = moduleSize / 2.0f;
        return Math.abs(moduleSize - ((float) stateCount[0])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[1])) < maxVariance && Math.abs((3.0f * moduleSize) - ((float) stateCount[2])) < 3.0f * maxVariance && Math.abs(moduleSize - ((float) stateCount[3])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[4])) < maxVariance;
    }

    private int[] getCrossCheckStateCount() {
        this.crossCheckStateCount[0] = 0;
        this.crossCheckStateCount[1] = 0;
        this.crossCheckStateCount[2] = 0;
        this.crossCheckStateCount[3] = 0;
        this.crossCheckStateCount[4] = 0;
        return this.crossCheckStateCount;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r3[1] = r3[1] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r9 < r0) goto L_0x0043;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r10 < r0) goto L_0x0043;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r8.image.get(r10 - r0, r9 - r0) != false) goto L_0x0043;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r3[1] <= r11) goto L_0x0025;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r9 < r0) goto L_0x004c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (r10 < r0) goto L_0x004c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r3[1] <= r11) goto L_0x0057;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
        r3[0] = r3[0] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
        if (r9 < r0) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
        if (r10 < r0) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r8.image.get(r10 - r0, r9 - r0) == false) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
        if (r3[0] <= r11) goto L_0x004e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
        if (r3[0] <= r11) goto L_0x0073;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0073, code lost:
        r1 = r8.image.getHeight();
        r2 = r8.image.getWidth();
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0082, code lost:
        if ((r9 + r0) >= r1) goto L_0x0094;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
        if ((r10 + r0) >= r2) goto L_0x0094;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0092, code lost:
        if (r8.image.get(r10 + r0, r9 + r0) != false) goto L_0x009f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
        if ((r9 + r0) >= r1) goto L_0x009c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
        if ((r10 + r0) < r2) goto L_0x00b2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009f, code lost:
        r3[2] = r3[2] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a9, code lost:
        r3[3] = r3[3] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b4, code lost:
        if ((r9 + r0) >= r1) goto L_0x00cb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b8, code lost:
        if ((r10 + r0) >= r2) goto L_0x00cb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c4, code lost:
        if (r8.image.get(r10 + r0, r9 + r0) != false) goto L_0x00cb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c9, code lost:
        if (r3[3] < r11) goto L_0x00a9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cd, code lost:
        if ((r9 + r0) >= r1) goto L_0x00d8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d1, code lost:
        if ((r10 + r0) >= r2) goto L_0x00d8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d6, code lost:
        if (r3[3] < r11) goto L_0x00e4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00d8, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00db, code lost:
        r3[4] = r3[4] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e6, code lost:
        if ((r9 + r0) >= r1) goto L_0x00fd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ea, code lost:
        if ((r10 + r0) >= r2) goto L_0x00fd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00f6, code lost:
        if (r8.image.get(r10 + r0, r9 + r0) == false) goto L_0x00fd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00fb, code lost:
        if (r3[4] < r11) goto L_0x00db;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0100, code lost:
        if (r3[4] < r11) goto L_0x0105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0102, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0121, code lost:
        if (java.lang.Math.abs(((((r3[0] + r3[1]) + r3[2]) + r3[3]) + r3[4]) - r12) >= (r12 * 2)) goto L_0x012c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0127, code lost:
        if (foundPatternCross(r3) == false) goto L_0x012c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0129, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x012c, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
        if (r10 >= r0) goto L_0x002e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean crossCheckDiagonal(int r9, int r10, int r11, int r12) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckDiagonal(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r3[1] <= r10) goto L_0x004b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        r3[0] = r3[0] + 1;
        r0 = r0 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
        if (r0 < 0) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
        if (r1.get(r9, r0) == false) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r3[0] <= r10) goto L_0x0042;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        if (r3[0] <= r10) goto L_0x0060;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
        r0 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r0 >= r2) goto L_0x006a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r1.get(r9, r0) != false) goto L_0x006f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
        if (r0 != r2) goto L_0x0082;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
        r3[2] = r3[2] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0079, code lost:
        r3[3] = r3[3] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
        if (r0 >= r2) goto L_0x008f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
        if (r1.get(r9, r0) != false) goto L_0x008f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008d, code lost:
        if (r3[3] < r10) goto L_0x0079;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
        if (r0 == r2) goto L_0x0096;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0094, code lost:
        if (r3[3] < r10) goto L_0x00a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0096, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009a, code lost:
        r3[4] = r3[4] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a3, code lost:
        if (r0 >= r2) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a9, code lost:
        if (r1.get(r9, r0) == false) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ae, code lost:
        if (r3[4] < r10) goto L_0x009a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
        if (r3[4] < r10) goto L_0x00b9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b5, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d7, code lost:
        if ((java.lang.Math.abs(((((r3[0] + r3[1]) + r3[2]) + r3[3]) + r3[4]) - r11) * 5) < (r11 * 2)) goto L_0x00dd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00e1, code lost:
        if (foundPatternCross(r3) == false) goto L_0x00e9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:?, code lost:
        return centerFromEnd(r3, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckVertical(int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r3[1] <= r10) goto L_0x004b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        r3[0] = r3[0] + 1;
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
        if (r1 < 0) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
        if (r0.get(r1, r9) == false) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r3[0] <= r10) goto L_0x0042;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        if (r3[0] <= r10) goto L_0x0060;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
        r1 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r1 >= r2) goto L_0x006a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r0.get(r1, r9) != false) goto L_0x006f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
        if (r1 != r2) goto L_0x0082;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
        r3[2] = r3[2] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0079, code lost:
        r3[3] = r3[3] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
        if (r1 >= r2) goto L_0x008f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
        if (r0.get(r1, r9) != false) goto L_0x008f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008d, code lost:
        if (r3[3] < r10) goto L_0x0079;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
        if (r1 == r2) goto L_0x0096;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0094, code lost:
        if (r3[3] < r10) goto L_0x00a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0096, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009a, code lost:
        r3[4] = r3[4] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a3, code lost:
        if (r1 >= r2) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a9, code lost:
        if (r0.get(r1, r9) == false) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ae, code lost:
        if (r3[4] < r10) goto L_0x009a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
        if (r3[4] < r10) goto L_0x00b9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b5, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d5, code lost:
        if ((java.lang.Math.abs(((((r3[0] + r3[1]) + r3[2]) + r3[3]) + r3[4]) - r11) * 5) < r11) goto L_0x00db;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d7, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00df, code lost:
        if (foundPatternCross(r3) == false) goto L_0x00e7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e7, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:?, code lost:
        return centerFromEnd(r3, r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckHorizontal(int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckHorizontal(int, int, int, int):float");
    }

    protected final boolean handlePossibleCenter(int[] stateCount, int i, int j, boolean pureBarcode) {
        int stateCountTotal = stateCount[0] + stateCount[1] + stateCount[2] + stateCount[3] + stateCount[4];
        float centerJ = centerFromEnd(stateCount, j);
        float centerI = crossCheckVertical(i, (int) centerJ, stateCount[2], stateCountTotal);
        if (!Float.isNaN(centerI)) {
            float centerJ2 = crossCheckHorizontal((int) centerJ, (int) centerI, stateCount[2], stateCountTotal);
            if (!Float.isNaN(centerJ2) && (!pureBarcode || crossCheckDiagonal((int) centerI, (int) centerJ2, stateCount[2], stateCountTotal))) {
                float estimatedModuleSize = stateCountTotal / 7.0f;
                boolean found = false;
                int index = 0;
                while (true) {
                    if (index >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern center = this.possibleCenters.get(index);
                    if (center.aboutEquals(estimatedModuleSize, centerI, centerJ2)) {
                        this.possibleCenters.set(index, center.combineEstimate(centerI, centerJ2, estimatedModuleSize));
                        found = true;
                        break;
                    }
                    index++;
                }
                if (!found) {
                    FinderPattern point = new FinderPattern(centerJ2, centerI, estimatedModuleSize);
                    this.possibleCenters.add(point);
                    if (this.resultPointCallback != null) {
                        this.resultPointCallback.foundPossibleResultPoint(point);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        ResultPoint firstConfirmedCenter = null;
        for (FinderPattern center : this.possibleCenters) {
            if (center.getCount() >= 2) {
                if (firstConfirmedCenter == null) {
                    firstConfirmedCenter = center;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(firstConfirmedCenter.getX() - center.getX()) - Math.abs(firstConfirmedCenter.getY() - center.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int confirmedCount = 0;
        float totalModuleSize = 0.0f;
        int max = this.possibleCenters.size();
        for (FinderPattern pattern : this.possibleCenters) {
            if (pattern.getCount() >= 2) {
                confirmedCount++;
                totalModuleSize += pattern.getEstimatedModuleSize();
            }
        }
        if (confirmedCount < 3) {
            return false;
        }
        float average = totalModuleSize / max;
        float totalDeviation = 0.0f;
        for (FinderPattern pattern2 : this.possibleCenters) {
            totalDeviation += Math.abs(pattern2.getEstimatedModuleSize() - average);
        }
        return totalDeviation <= 0.05f * totalModuleSize;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int startSize = this.possibleCenters.size();
        if (startSize < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (startSize > 3) {
            float totalModuleSize = 0.0f;
            float square = 0.0f;
            for (FinderPattern center : this.possibleCenters) {
                float size = center.getEstimatedModuleSize();
                totalModuleSize += size;
                square += size * size;
            }
            float average = totalModuleSize / startSize;
            float stdDev = (float) Math.sqrt((square / startSize) - (average * average));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(average, null));
            float limit = Math.max(0.2f * average, stdDev);
            int i = 0;
            while (i < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i).getEstimatedModuleSize() - average) > limit) {
                    this.possibleCenters.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            float totalModuleSize2 = 0.0f;
            for (FinderPattern possibleCenter : this.possibleCenters) {
                totalModuleSize2 += possibleCenter.getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(totalModuleSize2 / this.possibleCenters.size(), null));
            this.possibleCenters.subList(3, this.possibleCenters.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        /* synthetic */ FurthestFromAverageComparator(float f, FurthestFromAverageComparator furthestFromAverageComparator) {
            this(f);
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return -1;
            }
            return dA == dB ? 0 : 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        /* synthetic */ CenterComparator(float f, CenterComparator centerComparator) {
            this(f);
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            if (center2.getCount() != center1.getCount()) {
                return center2.getCount() - center1.getCount();
            }
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return 1;
            }
            return dA == dB ? 0 : -1;
        }
    }
}
