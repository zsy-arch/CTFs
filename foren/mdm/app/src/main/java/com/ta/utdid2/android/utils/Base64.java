package com.ta.utdid2.android.utils;

import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    static {
        $assertionsDisabled = !Base64.class.desiredAssertionStatus();
    }

    /* loaded from: classes2.dex */
    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    public static byte[] decode(String str, int flags) {
        return decode(str.getBytes(), flags);
    }

    public static byte[] decode(byte[] input, int flags) {
        return decode(input, 0, input.length, flags);
    }

    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        Decoder decoder = new Decoder(flags, new byte[(len * 3) / 4]);
        if (!decoder.process(input, offset, len, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            byte[] temp = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, temp, 0, decoder.op);
            return temp;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Decoder extends Coder {
        private static final int[] DECODE;
        private static final int[] DECODE_WEBSAFE;
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        static {
            int[] iArr = new int[256];
            iArr[0] = -1;
            iArr[1] = -1;
            iArr[2] = -1;
            iArr[3] = -1;
            iArr[4] = -1;
            iArr[5] = -1;
            iArr[6] = -1;
            iArr[7] = -1;
            iArr[8] = -1;
            iArr[9] = -1;
            iArr[10] = -1;
            iArr[11] = -1;
            iArr[12] = -1;
            iArr[13] = -1;
            iArr[14] = -1;
            iArr[15] = -1;
            iArr[16] = -1;
            iArr[17] = -1;
            iArr[18] = -1;
            iArr[19] = -1;
            iArr[20] = -1;
            iArr[21] = -1;
            iArr[22] = -1;
            iArr[23] = -1;
            iArr[24] = -1;
            iArr[25] = -1;
            iArr[26] = -1;
            iArr[27] = -1;
            iArr[28] = -1;
            iArr[29] = -1;
            iArr[30] = -1;
            iArr[31] = -1;
            iArr[32] = -1;
            iArr[33] = -1;
            iArr[34] = -1;
            iArr[35] = -1;
            iArr[36] = -1;
            iArr[37] = -1;
            iArr[38] = -1;
            iArr[39] = -1;
            iArr[40] = -1;
            iArr[41] = -1;
            iArr[42] = -1;
            iArr[43] = 62;
            iArr[44] = -1;
            iArr[45] = -1;
            iArr[46] = -1;
            iArr[47] = 63;
            iArr[48] = 52;
            iArr[49] = 53;
            iArr[50] = 54;
            iArr[51] = 55;
            iArr[52] = 56;
            iArr[53] = 57;
            iArr[54] = 58;
            iArr[55] = 59;
            iArr[56] = 60;
            iArr[57] = 61;
            iArr[58] = -1;
            iArr[59] = -1;
            iArr[60] = -1;
            iArr[61] = -2;
            iArr[62] = -1;
            iArr[63] = -1;
            iArr[64] = -1;
            iArr[66] = 1;
            iArr[67] = 2;
            iArr[68] = 3;
            iArr[69] = 4;
            iArr[70] = 5;
            iArr[71] = 6;
            iArr[72] = 7;
            iArr[73] = 8;
            iArr[74] = 9;
            iArr[75] = 10;
            iArr[76] = 11;
            iArr[77] = 12;
            iArr[78] = 13;
            iArr[79] = 14;
            iArr[80] = 15;
            iArr[81] = 16;
            iArr[82] = 17;
            iArr[83] = 18;
            iArr[84] = 19;
            iArr[85] = 20;
            iArr[86] = 21;
            iArr[87] = 22;
            iArr[88] = 23;
            iArr[89] = 24;
            iArr[90] = 25;
            iArr[91] = -1;
            iArr[92] = -1;
            iArr[93] = -1;
            iArr[94] = -1;
            iArr[95] = -1;
            iArr[96] = -1;
            iArr[97] = 26;
            iArr[98] = 27;
            iArr[99] = 28;
            iArr[100] = 29;
            iArr[101] = 30;
            iArr[102] = 31;
            iArr[103] = 32;
            iArr[104] = 33;
            iArr[105] = 34;
            iArr[106] = 35;
            iArr[107] = 36;
            iArr[108] = 37;
            iArr[109] = 38;
            iArr[110] = 39;
            iArr[111] = 40;
            iArr[112] = 41;
            iArr[113] = 42;
            iArr[114] = 43;
            iArr[115] = 44;
            iArr[116] = 45;
            iArr[117] = 46;
            iArr[118] = 47;
            iArr[119] = 48;
            iArr[120] = 49;
            iArr[121] = 50;
            iArr[122] = 51;
            iArr[123] = -1;
            iArr[124] = -1;
            iArr[125] = -1;
            iArr[126] = -1;
            iArr[127] = -1;
            iArr[128] = -1;
            iArr[129] = -1;
            iArr[130] = -1;
            iArr[131] = -1;
            iArr[132] = -1;
            iArr[133] = -1;
            iArr[134] = -1;
            iArr[135] = -1;
            iArr[136] = -1;
            iArr[137] = -1;
            iArr[138] = -1;
            iArr[139] = -1;
            iArr[140] = -1;
            iArr[141] = -1;
            iArr[142] = -1;
            iArr[143] = -1;
            iArr[144] = -1;
            iArr[145] = -1;
            iArr[146] = -1;
            iArr[147] = -1;
            iArr[148] = -1;
            iArr[149] = -1;
            iArr[150] = -1;
            iArr[151] = -1;
            iArr[152] = -1;
            iArr[153] = -1;
            iArr[154] = -1;
            iArr[155] = -1;
            iArr[156] = -1;
            iArr[157] = -1;
            iArr[158] = -1;
            iArr[159] = -1;
            iArr[160] = -1;
            iArr[161] = -1;
            iArr[162] = -1;
            iArr[163] = -1;
            iArr[164] = -1;
            iArr[165] = -1;
            iArr[166] = -1;
            iArr[167] = -1;
            iArr[168] = -1;
            iArr[169] = -1;
            iArr[170] = -1;
            iArr[171] = -1;
            iArr[172] = -1;
            iArr[173] = -1;
            iArr[174] = -1;
            iArr[175] = -1;
            iArr[176] = -1;
            iArr[177] = -1;
            iArr[178] = -1;
            iArr[179] = -1;
            iArr[180] = -1;
            iArr[181] = -1;
            iArr[182] = -1;
            iArr[183] = -1;
            iArr[184] = -1;
            iArr[185] = -1;
            iArr[186] = -1;
            iArr[187] = -1;
            iArr[188] = -1;
            iArr[189] = -1;
            iArr[190] = -1;
            iArr[191] = -1;
            iArr[192] = -1;
            iArr[193] = -1;
            iArr[194] = -1;
            iArr[195] = -1;
            iArr[196] = -1;
            iArr[197] = -1;
            iArr[198] = -1;
            iArr[199] = -1;
            iArr[200] = -1;
            iArr[201] = -1;
            iArr[202] = -1;
            iArr[203] = -1;
            iArr[204] = -1;
            iArr[205] = -1;
            iArr[206] = -1;
            iArr[207] = -1;
            iArr[208] = -1;
            iArr[209] = -1;
            iArr[210] = -1;
            iArr[211] = -1;
            iArr[212] = -1;
            iArr[213] = -1;
            iArr[214] = -1;
            iArr[215] = -1;
            iArr[216] = -1;
            iArr[217] = -1;
            iArr[218] = -1;
            iArr[219] = -1;
            iArr[220] = -1;
            iArr[221] = -1;
            iArr[222] = -1;
            iArr[223] = -1;
            iArr[224] = -1;
            iArr[225] = -1;
            iArr[226] = -1;
            iArr[227] = -1;
            iArr[228] = -1;
            iArr[229] = -1;
            iArr[230] = -1;
            iArr[231] = -1;
            iArr[232] = -1;
            iArr[233] = -1;
            iArr[234] = -1;
            iArr[235] = -1;
            iArr[236] = -1;
            iArr[237] = -1;
            iArr[238] = -1;
            iArr[239] = -1;
            iArr[240] = -1;
            iArr[241] = -1;
            iArr[242] = -1;
            iArr[243] = -1;
            iArr[244] = -1;
            iArr[245] = -1;
            iArr[246] = -1;
            iArr[247] = -1;
            iArr[248] = -1;
            iArr[249] = -1;
            iArr[250] = -1;
            iArr[251] = -1;
            iArr[252] = -1;
            iArr[253] = -1;
            iArr[254] = -1;
            iArr[255] = -1;
            DECODE = iArr;
            int[] iArr2 = new int[256];
            iArr2[0] = -1;
            iArr2[1] = -1;
            iArr2[2] = -1;
            iArr2[3] = -1;
            iArr2[4] = -1;
            iArr2[5] = -1;
            iArr2[6] = -1;
            iArr2[7] = -1;
            iArr2[8] = -1;
            iArr2[9] = -1;
            iArr2[10] = -1;
            iArr2[11] = -1;
            iArr2[12] = -1;
            iArr2[13] = -1;
            iArr2[14] = -1;
            iArr2[15] = -1;
            iArr2[16] = -1;
            iArr2[17] = -1;
            iArr2[18] = -1;
            iArr2[19] = -1;
            iArr2[20] = -1;
            iArr2[21] = -1;
            iArr2[22] = -1;
            iArr2[23] = -1;
            iArr2[24] = -1;
            iArr2[25] = -1;
            iArr2[26] = -1;
            iArr2[27] = -1;
            iArr2[28] = -1;
            iArr2[29] = -1;
            iArr2[30] = -1;
            iArr2[31] = -1;
            iArr2[32] = -1;
            iArr2[33] = -1;
            iArr2[34] = -1;
            iArr2[35] = -1;
            iArr2[36] = -1;
            iArr2[37] = -1;
            iArr2[38] = -1;
            iArr2[39] = -1;
            iArr2[40] = -1;
            iArr2[41] = -1;
            iArr2[42] = -1;
            iArr2[43] = -1;
            iArr2[44] = -1;
            iArr2[45] = 62;
            iArr2[46] = -1;
            iArr2[47] = -1;
            iArr2[48] = 52;
            iArr2[49] = 53;
            iArr2[50] = 54;
            iArr2[51] = 55;
            iArr2[52] = 56;
            iArr2[53] = 57;
            iArr2[54] = 58;
            iArr2[55] = 59;
            iArr2[56] = 60;
            iArr2[57] = 61;
            iArr2[58] = -1;
            iArr2[59] = -1;
            iArr2[60] = -1;
            iArr2[61] = -2;
            iArr2[62] = -1;
            iArr2[63] = -1;
            iArr2[64] = -1;
            iArr2[66] = 1;
            iArr2[67] = 2;
            iArr2[68] = 3;
            iArr2[69] = 4;
            iArr2[70] = 5;
            iArr2[71] = 6;
            iArr2[72] = 7;
            iArr2[73] = 8;
            iArr2[74] = 9;
            iArr2[75] = 10;
            iArr2[76] = 11;
            iArr2[77] = 12;
            iArr2[78] = 13;
            iArr2[79] = 14;
            iArr2[80] = 15;
            iArr2[81] = 16;
            iArr2[82] = 17;
            iArr2[83] = 18;
            iArr2[84] = 19;
            iArr2[85] = 20;
            iArr2[86] = 21;
            iArr2[87] = 22;
            iArr2[88] = 23;
            iArr2[89] = 24;
            iArr2[90] = 25;
            iArr2[91] = -1;
            iArr2[92] = -1;
            iArr2[93] = -1;
            iArr2[94] = -1;
            iArr2[95] = 63;
            iArr2[96] = -1;
            iArr2[97] = 26;
            iArr2[98] = 27;
            iArr2[99] = 28;
            iArr2[100] = 29;
            iArr2[101] = 30;
            iArr2[102] = 31;
            iArr2[103] = 32;
            iArr2[104] = 33;
            iArr2[105] = 34;
            iArr2[106] = 35;
            iArr2[107] = 36;
            iArr2[108] = 37;
            iArr2[109] = 38;
            iArr2[110] = 39;
            iArr2[111] = 40;
            iArr2[112] = 41;
            iArr2[113] = 42;
            iArr2[114] = 43;
            iArr2[115] = 44;
            iArr2[116] = 45;
            iArr2[117] = 46;
            iArr2[118] = 47;
            iArr2[119] = 48;
            iArr2[120] = 49;
            iArr2[121] = 50;
            iArr2[122] = 51;
            iArr2[123] = -1;
            iArr2[124] = -1;
            iArr2[125] = -1;
            iArr2[126] = -1;
            iArr2[127] = -1;
            iArr2[128] = -1;
            iArr2[129] = -1;
            iArr2[130] = -1;
            iArr2[131] = -1;
            iArr2[132] = -1;
            iArr2[133] = -1;
            iArr2[134] = -1;
            iArr2[135] = -1;
            iArr2[136] = -1;
            iArr2[137] = -1;
            iArr2[138] = -1;
            iArr2[139] = -1;
            iArr2[140] = -1;
            iArr2[141] = -1;
            iArr2[142] = -1;
            iArr2[143] = -1;
            iArr2[144] = -1;
            iArr2[145] = -1;
            iArr2[146] = -1;
            iArr2[147] = -1;
            iArr2[148] = -1;
            iArr2[149] = -1;
            iArr2[150] = -1;
            iArr2[151] = -1;
            iArr2[152] = -1;
            iArr2[153] = -1;
            iArr2[154] = -1;
            iArr2[155] = -1;
            iArr2[156] = -1;
            iArr2[157] = -1;
            iArr2[158] = -1;
            iArr2[159] = -1;
            iArr2[160] = -1;
            iArr2[161] = -1;
            iArr2[162] = -1;
            iArr2[163] = -1;
            iArr2[164] = -1;
            iArr2[165] = -1;
            iArr2[166] = -1;
            iArr2[167] = -1;
            iArr2[168] = -1;
            iArr2[169] = -1;
            iArr2[170] = -1;
            iArr2[171] = -1;
            iArr2[172] = -1;
            iArr2[173] = -1;
            iArr2[174] = -1;
            iArr2[175] = -1;
            iArr2[176] = -1;
            iArr2[177] = -1;
            iArr2[178] = -1;
            iArr2[179] = -1;
            iArr2[180] = -1;
            iArr2[181] = -1;
            iArr2[182] = -1;
            iArr2[183] = -1;
            iArr2[184] = -1;
            iArr2[185] = -1;
            iArr2[186] = -1;
            iArr2[187] = -1;
            iArr2[188] = -1;
            iArr2[189] = -1;
            iArr2[190] = -1;
            iArr2[191] = -1;
            iArr2[192] = -1;
            iArr2[193] = -1;
            iArr2[194] = -1;
            iArr2[195] = -1;
            iArr2[196] = -1;
            iArr2[197] = -1;
            iArr2[198] = -1;
            iArr2[199] = -1;
            iArr2[200] = -1;
            iArr2[201] = -1;
            iArr2[202] = -1;
            iArr2[203] = -1;
            iArr2[204] = -1;
            iArr2[205] = -1;
            iArr2[206] = -1;
            iArr2[207] = -1;
            iArr2[208] = -1;
            iArr2[209] = -1;
            iArr2[210] = -1;
            iArr2[211] = -1;
            iArr2[212] = -1;
            iArr2[213] = -1;
            iArr2[214] = -1;
            iArr2[215] = -1;
            iArr2[216] = -1;
            iArr2[217] = -1;
            iArr2[218] = -1;
            iArr2[219] = -1;
            iArr2[220] = -1;
            iArr2[221] = -1;
            iArr2[222] = -1;
            iArr2[223] = -1;
            iArr2[224] = -1;
            iArr2[225] = -1;
            iArr2[226] = -1;
            iArr2[227] = -1;
            iArr2[228] = -1;
            iArr2[229] = -1;
            iArr2[230] = -1;
            iArr2[231] = -1;
            iArr2[232] = -1;
            iArr2[233] = -1;
            iArr2[234] = -1;
            iArr2[235] = -1;
            iArr2[236] = -1;
            iArr2[237] = -1;
            iArr2[238] = -1;
            iArr2[239] = -1;
            iArr2[240] = -1;
            iArr2[241] = -1;
            iArr2[242] = -1;
            iArr2[243] = -1;
            iArr2[244] = -1;
            iArr2[245] = -1;
            iArr2[246] = -1;
            iArr2[247] = -1;
            iArr2[248] = -1;
            iArr2[249] = -1;
            iArr2[250] = -1;
            iArr2[251] = -1;
            iArr2[252] = -1;
            iArr2[253] = -1;
            iArr2[254] = -1;
            iArr2[255] = -1;
            DECODE_WEBSAFE = iArr2;
        }

        public Decoder(int flags, byte[] output) {
            this.output = output;
            this.alphabet = (flags & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override // com.ta.utdid2.android.utils.Base64.Coder
        public int maxOutputSize(int len) {
            return ((len * 3) / 4) + 10;
        }

        /* JADX WARN: Code restructure failed: missing block: B:104:?, code lost:
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:111:?, code lost:
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:?, code lost:
            return false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
            return false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0110, code lost:
            switch(r7) {
                case 0: goto L_0x011b;
                case 1: goto L_0x011d;
                case 2: goto L_0x0123;
                case 3: goto L_0x012b;
                case 4: goto L_0x013b;
                default: goto L_0x0113;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0113, code lost:
            r2 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0114, code lost:
            r11.state = r7;
            r11.op = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x011b, code lost:
            r2 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x011d, code lost:
            r11.state = 6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0123, code lost:
            r2 = r3 + 1;
            r4[r3] = (byte) (r8 >> 4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x012b, code lost:
            r2 = r3 + 1;
            r4[r3] = (byte) (r8 >> 10);
            r4[r2] = (byte) (r8 >> 2);
            r2 = r2 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x013b, code lost:
            r11.state = 6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r15 != false) goto L_0x0110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
            r11.state = r7;
            r11.value = r8;
            r11.op = r3;
         */
        @Override // com.ta.utdid2.android.utils.Base64.Coder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean process(byte[] r12, int r13, int r14, boolean r15) {
            /*
                Method dump skipped, instructions count: 352
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.android.utils.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    public static String encodeToString(byte[] input, int flags) {
        try {
            return new String(encode(input, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] input, int offset, int len, int flags) {
        try {
            return new String(encode(input, offset, len, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] input, int flags) {
        return encode(input, 0, input.length, flags);
    }

    public static byte[] encode(byte[] input, int offset, int len, int flags) {
        Encoder encoder = new Encoder(flags, null);
        int output_len = (len / 3) * 4;
        if (!encoder.do_padding) {
            switch (len % 3) {
                case 1:
                    output_len += 2;
                    break;
                case 2:
                    output_len += 3;
                    break;
            }
        } else if (len % 3 > 0) {
            output_len += 4;
        }
        if (encoder.do_newline && len > 0) {
            output_len += (encoder.do_cr ? 2 : 1) * (((len - 1) / 57) + 1);
        }
        encoder.output = new byte[output_len];
        encoder.process(input, offset, len, true);
        if ($assertionsDisabled || encoder.op == output_len) {
            return encoder.output;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled;
        private static final byte[] ENCODE;
        private static final byte[] ENCODE_WEBSAFE;
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            $assertionsDisabled = !Base64.class.desiredAssertionStatus();
            ENCODE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
            ENCODE_WEBSAFE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        }

        public Encoder(int flags, byte[] output) {
            boolean z = true;
            this.output = output;
            this.do_padding = (flags & 1) == 0;
            this.do_newline = (flags & 2) == 0;
            this.do_cr = (flags & 4) == 0 ? false : z;
            this.alphabet = (flags & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        @Override // com.ta.utdid2.android.utils.Base64.Coder
        public int maxOutputSize(int len) {
            return ((len * 8) / 5) + 10;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0210  */
        @Override // com.ta.utdid2.android.utils.Base64.Coder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean process(byte[] r15, int r16, int r17, boolean r18) {
            /*
                Method dump skipped, instructions count: 602
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.android.utils.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    private Base64() {
    }
}
