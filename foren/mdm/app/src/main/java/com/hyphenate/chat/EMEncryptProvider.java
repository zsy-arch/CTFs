package com.hyphenate.chat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface EMEncryptProvider {
    byte[] decrypt(byte[] bArr, String str);

    byte[] encrypt(byte[] bArr, String str);
}
