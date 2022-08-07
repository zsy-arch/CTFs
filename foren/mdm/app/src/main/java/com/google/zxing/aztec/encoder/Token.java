package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

/* loaded from: classes.dex */
public abstract class Token {
    static final Token EMPTY = new SimpleToken(null, 0, 0);
    private final Token previous;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void appendTo(BitArray bitArray, byte[] bArr);

    public Token(Token previous) {
        this.previous = previous;
    }

    public final Token getPrevious() {
        return this.previous;
    }

    public final Token add(int value, int bitCount) {
        return new SimpleToken(this, value, bitCount);
    }

    public final Token addBinaryShift(int start, int byteCount) {
        return new BinaryShiftToken(this, start, byteCount);
    }
}
