package com.google.zxing.datamatrix.encoder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface Encoder {
    void encode(EncoderContext encoderContext);

    int getEncodingMode();
}
