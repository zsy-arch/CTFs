package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;

/* loaded from: classes.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0076  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r17, java.util.Map<com.google.zxing.DecodeHintType, ?> r18) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r16 = this;
            r8 = 0
            r7 = 0
            com.google.zxing.aztec.detector.Detector r3 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r13 = r17.getBlackMatrix()
            r3.<init>(r13)
            r10 = 0
            r2 = 0
            r13 = 0
            com.google.zxing.aztec.AztecDetectorResult r4 = r3.detect(r13)     // Catch: NotFoundException -> 0x006b, FormatException -> 0x006e
            com.google.zxing.ResultPoint[] r10 = r4.getPoints()     // Catch: NotFoundException -> 0x006b, FormatException -> 0x006e
            com.google.zxing.aztec.decoder.Decoder r13 = new com.google.zxing.aztec.decoder.Decoder     // Catch: NotFoundException -> 0x006b, FormatException -> 0x006e
            r13.<init>()     // Catch: NotFoundException -> 0x006b, FormatException -> 0x006e
            com.google.zxing.common.DecoderResult r2 = r13.decode(r4)     // Catch: NotFoundException -> 0x006b, FormatException -> 0x006e
        L_0x001f:
            if (r2 != 0) goto L_0x0033
            r13 = 1
            com.google.zxing.aztec.AztecDetectorResult r4 = r3.detect(r13)     // Catch: NotFoundException -> 0x0071, FormatException -> 0x0082
            com.google.zxing.ResultPoint[] r10 = r4.getPoints()     // Catch: NotFoundException -> 0x0071, FormatException -> 0x0082
            com.google.zxing.aztec.decoder.Decoder r13 = new com.google.zxing.aztec.decoder.Decoder     // Catch: NotFoundException -> 0x0071, FormatException -> 0x0082
            r13.<init>()     // Catch: NotFoundException -> 0x0071, FormatException -> 0x0082
            com.google.zxing.common.DecoderResult r2 = r13.decode(r4)     // Catch: NotFoundException -> 0x0071, FormatException -> 0x0082
        L_0x0033:
            if (r18 == 0) goto L_0x0045
            com.google.zxing.DecodeHintType r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r0 = r18
            java.lang.Object r12 = r0.get(r13)
            com.google.zxing.ResultPointCallback r12 = (com.google.zxing.ResultPointCallback) r12
            if (r12 == 0) goto L_0x0045
            int r14 = r10.length
            r13 = 0
        L_0x0043:
            if (r13 < r14) goto L_0x007a
        L_0x0045:
            com.google.zxing.Result r11 = new com.google.zxing.Result
            java.lang.String r13 = r2.getText()
            byte[] r14 = r2.getRawBytes()
            com.google.zxing.BarcodeFormat r15 = com.google.zxing.BarcodeFormat.AZTEC
            r11.<init>(r13, r14, r10, r15)
            java.util.List r1 = r2.getByteSegments()
            if (r1 == 0) goto L_0x005f
            com.google.zxing.ResultMetadataType r13 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r11.putMetadata(r13, r1)
        L_0x005f:
            java.lang.String r6 = r2.getECLevel()
            if (r6 == 0) goto L_0x006a
            com.google.zxing.ResultMetadataType r13 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r11.putMetadata(r13, r6)
        L_0x006a:
            return r11
        L_0x006b:
            r5 = move-exception
            r8 = r5
            goto L_0x001f
        L_0x006e:
            r5 = move-exception
            r7 = r5
            goto L_0x001f
        L_0x0071:
            r13 = move-exception
            r5 = r13
        L_0x0073:
            if (r8 == 0) goto L_0x0076
            throw r8
        L_0x0076:
            if (r7 == 0) goto L_0x0079
            throw r7
        L_0x0079:
            throw r5
        L_0x007a:
            r9 = r10[r13]
            r12.foundPossibleResultPoint(r9)
            int r13 = r13 + 1
            goto L_0x0043
        L_0x0082:
            r13 = move-exception
            r5 = r13
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
