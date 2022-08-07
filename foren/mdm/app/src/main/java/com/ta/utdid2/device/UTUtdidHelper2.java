package com.ta.utdid2.device;

import com.ta.utdid2.android.utils.AESUtils;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.StringUtils;

/* loaded from: classes2.dex */
public class UTUtdidHelper2 {
    private String mAESKey;

    public UTUtdidHelper2() {
        this.mAESKey = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.mAESKey = Base64.encodeToString(this.mAESKey.getBytes(), 0);
    }

    public String dePack(String pPackedUtdid) {
        return AESUtils.decrypt(this.mAESKey, pPackedUtdid);
    }

    public String dePackWithBase64(String pUtdidWithBase64) {
        String lEResult = AESUtils.decrypt(this.mAESKey, pUtdidWithBase64);
        if (StringUtils.isEmpty(lEResult)) {
            return null;
        }
        try {
            return new String(Base64.decode(lEResult, 0));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
