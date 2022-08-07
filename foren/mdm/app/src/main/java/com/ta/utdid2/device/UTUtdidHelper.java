package com.ta.utdid2.device;

import com.ta.utdid2.android.utils.AESUtils;
import com.ta.utdid2.android.utils.Base64;
import java.util.Random;

/* loaded from: classes2.dex */
public class UTUtdidHelper {
    private static Random random = new Random();
    private String mAESKey;

    public UTUtdidHelper() {
        this.mAESKey = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.mAESKey = Base64.encodeToString(this.mAESKey.getBytes(), 2);
    }

    public String pack(byte[] pUtdid) {
        return AESUtils.encrypt(this.mAESKey, Base64.encodeToString(pUtdid, 2));
    }

    public String packUtdidStr(String pUtdid) {
        return AESUtils.encrypt(this.mAESKey, pUtdid);
    }

    public String dePack(String pPackedUtdid) {
        return AESUtils.decrypt(this.mAESKey, pPackedUtdid);
    }

    public static String generateRandomUTDID() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 24; i++) {
            sb.append((char) (random.nextInt(25) + 65));
        }
        return sb.toString();
    }
}
