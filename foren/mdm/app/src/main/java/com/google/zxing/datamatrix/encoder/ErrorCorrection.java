package com.google.zxing.datamatrix.encoder;

import com.parse.ParseException;
import com.tencent.smtt.sdk.TbsListener;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 248, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, ParseException.FILE_DELETE_ERROR, 158, 91, 61, 42, ParseException.VALIDATION_ERROR, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 97, 178, 100, 242}, new int[]{156, 97, 192, ParseException.UNSUPPORTED_SERVICE, 95, 9, 157, ParseException.OPERATION_FORBIDDEN, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 109, 129, 94, 254, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, ParseException.FILE_DELETE_ERROR, 145, 253, 79, 108, 82, 27, 174, 186, Constant.PICTURE_TYPE_172}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, ParseException.REQUEST_LIMIT_EXCEEDED, 197, ParseException.INVALID_LINKED_SESSION, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, ParseException.REQUEST_LIMIT_EXCEEDED, 21, 5, Constant.PICTURE_TYPE_172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, ParseException.SCRIPT_ERROR, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, 130, 250, 162, 181, 102, 120, 84, 179, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, ParseException.INVALID_LINKED_SESSION, 80, 182, 229, 18, 2, 4, 68, 33, 101, ParseException.DUPLICATE_VALUE, 95, ParseException.OPERATION_FORBIDDEN, 115, 44, 175, 184, 59, 25, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 98, 81, 112}, new int[]{77, 193, ParseException.DUPLICATE_VALUE, 31, 19, 38, 22, ParseException.FILE_DELETE_ERROR, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, TbsListener.ErrorCode.DEXOAT_EXCEPTION, 5, 9, 5}, new int[]{245, 132, Constant.PICTURE_TYPE_172, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 92, 253, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 19}, new int[]{175, 9, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 238, 12, 17, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 208, 100, 29, 175, 170, 230, 192, TbsListener.ErrorCode.COPY_EXCEPTION, 235, 150, 159, 36, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 38, 200, 132, 54, 228, 146, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, ParseException.DUPLICATE_VALUE, 245, 127, 67, 247, 28, ParseException.REQUEST_LIMIT_EXCEEDED, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, 197, 190, 250, 106, 185, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 175, 64, 114, 71, 161, 44, 147, 6, 27, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, TbsListener.ErrorCode.EXCEED_INCR_UPDATE, 124, 86, 47, 11, 204}, new int[]{TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 228, Constant.PICTURE_TYPE_173, 89, ParseException.INVALID_LINKED_SESSION, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 15, 160, 227, 236, 66, ParseException.INVALID_ROLE_NAME, ParseException.FILE_DELETE_ERROR, 185, 202, 167, 179, 25, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 232, 96, 210, 231, 136, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 239, 181, 241, 59, 52, Constant.PICTURE_TYPE_172, 25, 49, 232, 211, 189, 64, 54, 108, ParseException.FILE_DELETE_ERROR, 132, 63, 96, 103, 82, 186}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int p = 1;
        for (int i = 0; i < 255; i++) {
            ALOG[i] = p;
            LOG[p] = i;
            p *= 2;
            if (p >= 256) {
                p ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String codewords, SymbolInfo symbolInfo) {
        if (codewords.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(codewords);
        int blockCount = symbolInfo.getInterleavedBlockCount();
        if (blockCount == 1) {
            sb.append(createECCBlock(codewords, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] dataSizes = new int[blockCount];
            int[] errorSizes = new int[blockCount];
            int[] startPos = new int[blockCount];
            for (int i = 0; i < blockCount; i++) {
                dataSizes[i] = symbolInfo.getDataLengthForInterleavedBlock(i + 1);
                errorSizes[i] = symbolInfo.getErrorLengthForInterleavedBlock(i + 1);
                startPos[i] = 0;
                if (i > 0) {
                    startPos[i] = startPos[i - 1] + dataSizes[i];
                }
            }
            for (int block = 0; block < blockCount; block++) {
                StringBuilder temp = new StringBuilder(dataSizes[block]);
                for (int d = block; d < symbolInfo.getDataCapacity(); d += blockCount) {
                    temp.append(codewords.charAt(d));
                }
                String ecc = createECCBlock(temp.toString(), errorSizes[block]);
                int pos = 0;
                int e = block;
                while (e < errorSizes[block] * blockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + e, ecc.charAt(pos));
                    e += blockCount;
                    pos++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence codewords, int numECWords) {
        return createECCBlock(codewords, 0, codewords.length(), numECWords);
    }

    private static String createECCBlock(CharSequence codewords, int start, int len, int numECWords) {
        int table = -1;
        int i = 0;
        while (true) {
            if (i >= FACTOR_SETS.length) {
                break;
            } else if (FACTOR_SETS[i] == numECWords) {
                table = i;
                break;
            } else {
                i++;
            }
        }
        if (table < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + numECWords);
        }
        int[] poly = FACTORS[table];
        char[] ecc = new char[numECWords];
        for (int i2 = 0; i2 < numECWords; i2++) {
            ecc[i2] = 0;
        }
        for (int i3 = start; i3 < start + len; i3++) {
            int m = ecc[numECWords - 1] ^ codewords.charAt(i3);
            for (int k = numECWords - 1; k > 0; k--) {
                if (m == 0 || poly[k] == 0) {
                    ecc[k] = ecc[k - 1];
                } else {
                    ecc[k] = (char) (ecc[k - 1] ^ ALOG[(LOG[m] + LOG[poly[k]]) % 255]);
                }
            }
            if (m == 0 || poly[0] == 0) {
                ecc[0] = 0;
            } else {
                ecc[0] = (char) ALOG[(LOG[m] + LOG[poly[0]]) % 255];
            }
        }
        char[] eccReversed = new char[numECWords];
        for (int i4 = 0; i4 < numECWords; i4++) {
            eccReversed[i4] = ecc[(numECWords - i4) - 1];
        }
        return String.valueOf(eccReversed);
    }
}
