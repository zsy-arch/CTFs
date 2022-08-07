package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

    public DecoderResult decode(boolean[][] image) throws ChecksumException, FormatException {
        return decode(image, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(boolean[][] image, Map<DecodeHintType, ?> hints) throws ChecksumException, FormatException {
        int dimension = image.length;
        BitMatrix bits = new BitMatrix(dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (image[i][j]) {
                    bits.set(j, i);
                }
            }
        }
        return decode(bits, hints);
    }

    public DecoderResult decode(BitMatrix bits) throws ChecksumException, FormatException {
        return decode(bits, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.common.DecoderResult decode(com.google.zxing.common.BitMatrix r8, java.util.Map<com.google.zxing.DecodeHintType, ?> r9) throws com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r7 = this;
            com.google.zxing.qrcode.decoder.BitMatrixParser r3 = new com.google.zxing.qrcode.decoder.BitMatrixParser
            r3.<init>(r8)
            r2 = 0
            r0 = 0
            com.google.zxing.common.DecoderResult r4 = r7.decode(r3, r9)     // Catch: FormatException -> 0x000c, ChecksumException -> 0x0031
        L_0x000b:
            return r4
        L_0x000c:
            r1 = move-exception
            r2 = r1
        L_0x000e:
            r3.remask()     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r5 = 1
            r3.setMirror(r5)     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r3.readVersion()     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r3.readFormatInformation()     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r3.mirror()     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            com.google.zxing.common.DecoderResult r4 = r7.decode(r3, r9)     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData r5 = new com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r6 = 1
            r5.<init>(r6)     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            r4.setOther(r5)     // Catch: FormatException -> 0x002c, ChecksumException -> 0x0038
            goto L_0x000b
        L_0x002c:
            r5 = move-exception
            r1 = r5
        L_0x002e:
            if (r2 == 0) goto L_0x0034
            throw r2
        L_0x0031:
            r1 = move-exception
            r0 = r1
            goto L_0x000e
        L_0x0034:
            if (r0 == 0) goto L_0x0037
            throw r0
        L_0x0037:
            throw r1
        L_0x0038:
            r5 = move-exception
            r1 = r5
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.Decoder.decode(com.google.zxing.common.BitMatrix, java.util.Map):com.google.zxing.common.DecoderResult");
    }

    private DecoderResult decode(BitMatrixParser parser, Map<DecodeHintType, ?> hints) throws FormatException, ChecksumException {
        Version version = parser.readVersion();
        ErrorCorrectionLevel ecLevel = parser.readFormatInformation().getErrorCorrectionLevel();
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(parser.readCodewords(), version, ecLevel);
        int totalBytes = 0;
        for (DataBlock dataBlock : dataBlocks) {
            totalBytes += dataBlock.getNumDataCodewords();
        }
        byte[] resultBytes = new byte[totalBytes];
        int resultOffset = 0;
        int length = dataBlocks.length;
        int i = 0;
        while (i < length) {
            DataBlock dataBlock2 = dataBlocks[i];
            byte[] codewordBytes = dataBlock2.getCodewords();
            int numDataCodewords = dataBlock2.getNumDataCodewords();
            correctErrors(codewordBytes, numDataCodewords);
            int i2 = 0;
            int resultOffset2 = resultOffset;
            while (i2 < numDataCodewords) {
                resultBytes[resultOffset2] = codewordBytes[i2];
                i2++;
                resultOffset2++;
            }
            i++;
            resultOffset = resultOffset2;
        }
        return DecodedBitStreamParser.decode(resultBytes, version, ecLevel, hints);
    }

    private void correctErrors(byte[] codewordBytes, int numDataCodewords) throws ChecksumException {
        int numCodewords = codewordBytes.length;
        int[] codewordsInts = new int[numCodewords];
        for (int i = 0; i < numCodewords; i++) {
            codewordsInts[i] = codewordBytes[i] & 255;
        }
        try {
            this.rsDecoder.decode(codewordsInts, codewordBytes.length - numDataCodewords);
            for (int i2 = 0; i2 < numDataCodewords; i2++) {
                codewordBytes[i2] = (byte) codewordsInts[i2];
            }
        } catch (ReedSolomonException e) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
