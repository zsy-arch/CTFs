package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final int[] INDEXES_START_PATTERN;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    static {
        int[] iArr = new int[4];
        iArr[1] = 4;
        iArr[2] = 1;
        iArr[3] = 5;
        INDEXES_START_PATTERN = iArr;
    }

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap image, Map<DecodeHintType, ?> hints, boolean multiple) throws NotFoundException {
        BitMatrix bitMatrix = image.getBlackMatrix();
        List<ResultPoint[]> barcodeCoordinates = detect(multiple, bitMatrix);
        if (barcodeCoordinates.isEmpty()) {
            bitMatrix = bitMatrix.clone();
            bitMatrix.rotate180();
            barcodeCoordinates = detect(multiple, bitMatrix);
        }
        return new PDF417DetectorResult(bitMatrix, barcodeCoordinates);
    }

    private static List<ResultPoint[]> detect(boolean multiple, BitMatrix bitMatrix) {
        List<ResultPoint[]> barcodeCoordinates = new ArrayList<>();
        int row = 0;
        int column = 0;
        boolean foundBarcodeInRow = false;
        while (row < bitMatrix.getHeight()) {
            ResultPoint[] vertices = findVertices(bitMatrix, row, column);
            if (vertices[0] == null && vertices[3] == null) {
                if (!foundBarcodeInRow) {
                    break;
                }
                foundBarcodeInRow = false;
                column = 0;
                for (ResultPoint[] barcodeCoordinate : barcodeCoordinates) {
                    if (barcodeCoordinate[1] != null) {
                        row = (int) Math.max(row, barcodeCoordinate[1].getY());
                    }
                    if (barcodeCoordinate[3] != null) {
                        row = Math.max(row, (int) barcodeCoordinate[3].getY());
                    }
                }
                row += 5;
            } else {
                foundBarcodeInRow = true;
                barcodeCoordinates.add(vertices);
                if (!multiple) {
                    break;
                } else if (vertices[2] != null) {
                    column = (int) vertices[2].getX();
                    row = (int) vertices[2].getY();
                } else {
                    column = (int) vertices[4].getX();
                    row = (int) vertices[4].getY();
                }
            }
        }
        return barcodeCoordinates;
    }

    private static ResultPoint[] findVertices(BitMatrix matrix, int startRow, int startColumn) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        ResultPoint[] result = new ResultPoint[8];
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, START_PATTERN), INDEXES_START_PATTERN);
        if (result[4] != null) {
            startColumn = (int) result[4].getX();
            startRow = (int) result[4].getY();
        }
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return result;
    }

    private static void copyToResult(ResultPoint[] result, ResultPoint[] tmpResult, int[] destinationIndexes) {
        for (int i = 0; i < destinationIndexes.length; i++) {
            result[destinationIndexes[i]] = tmpResult[i];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
        if (r17 > 0) goto L_0x0087;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
        r12[0] = new com.google.zxing.ResultPoint(r10[0], r17);
        r12[1] = new com.google.zxing.ResultPoint(r10[1], r17);
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0087, code lost:
        r17 = r17 - 1;
        r11 = findGuardPattern(r14, r18, r17, r16, false, r19, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0097, code lost:
        if (r11 == null) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0099, code lost:
        r10 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009b, code lost:
        r17 = r17 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.zxing.ResultPoint[] findRowsWithPattern(com.google.zxing.common.BitMatrix r14, int r15, int r16, int r17, int r18, int[] r19) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.findRowsWithPattern(com.google.zxing.common.BitMatrix, int, int, int, int, int[]):com.google.zxing.ResultPoint[]");
    }

    private static int[] findGuardPattern(BitMatrix matrix, int column, int row, int width, boolean whiteFirst, int[] pattern, int[] counters) {
        Arrays.fill(counters, 0, counters.length, 0);
        int patternLength = pattern.length;
        boolean isWhite = whiteFirst;
        int patternStart = column;
        int pixelDrift = 0;
        while (matrix.get(patternStart, row) && patternStart > 0) {
            int pixelDrift2 = pixelDrift + 1;
            if (pixelDrift >= 3) {
                break;
            }
            patternStart--;
            pixelDrift = pixelDrift2;
        }
        int x = patternStart;
        int counterPosition = 0;
        while (x < width) {
            if (matrix.get(x, row) ^ isWhite) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition != patternLength - 1) {
                    counterPosition++;
                } else if (patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{patternStart, x};
                } else {
                    patternStart += counters[0] + counters[1];
                    System.arraycopy(counters, 2, counters, 0, patternLength - 2);
                    counters[patternLength - 2] = 0;
                    counters[patternLength - 1] = 0;
                    counterPosition--;
                }
                counters[counterPosition] = 1;
                isWhite = !isWhite;
            }
            x++;
        }
        if (counterPosition != patternLength - 1 || patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
            return null;
        }
        return new int[]{patternStart, x - 1};
    }

    private static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
        int numCounters = counters.length;
        int total = 0;
        int patternLength = 0;
        for (int i = 0; i < numCounters; i++) {
            total += counters[i];
            patternLength += pattern[i];
        }
        if (total < patternLength) {
            return Float.POSITIVE_INFINITY;
        }
        float unitBarWidth = total / patternLength;
        float maxIndividualVariance2 = maxIndividualVariance * unitBarWidth;
        float totalVariance = 0.0f;
        for (int x = 0; x < numCounters; x++) {
            int counter = counters[x];
            float scaledPattern = pattern[x] * unitBarWidth;
            float variance = ((float) counter) > scaledPattern ? counter - scaledPattern : scaledPattern - counter;
            if (variance > maxIndividualVariance2) {
                return Float.POSITIVE_INFINITY;
            }
            totalVariance += variance;
        }
        return totalVariance / total;
    }
}
