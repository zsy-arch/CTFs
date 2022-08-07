package com.hyphenate.util;

import android.content.Context;
import com.vsf2f.f2f.ui.utils.area.ConvertUtils;
import java.text.DecimalFormat;
import u.aly.av;

/* loaded from: classes2.dex */
public class TextFormater {
    private static final int GB_SP_DIFF = 160;
    private static final int[] secPosvalueList = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600};
    private static final char[] firstLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z'};

    private static char convert(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] - 160);
        }
        int i2 = (bArr[0] * 100) + bArr[1];
        for (int i3 = 0; i3 < 23; i3++) {
            if (i2 >= secPosvalueList[i3] && i2 < secPosvalueList[i3 + 1]) {
                return firstLetter[i3];
            }
        }
        return '-';
    }

    public static String formatStr(Context context, int i, String str) {
        return String.format(context.getText(i).toString(), str);
    }

    public static String getDataSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        return j < 0 ? av.aG : j < 1024 ? j + "bytes" : j < 1048576 ? decimalFormat.format(((float) j) / 1024.0f) + "KB" : j < ConvertUtils.GB ? decimalFormat.format((((float) j) / 1024.0f) / 1024.0f) + "MB" : decimalFormat.format(((((float) j) / 1024.0f) / 1024.0f) / 1024.0f) + "GB";
    }

    public static String getFirstLetter(String str) {
        String lowerCase = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lowerCase.length(); i++) {
            char[] cArr = {lowerCase.charAt(i)};
            byte[] bytes = new String(cArr).getBytes();
            if (bytes[0] >= 128 || bytes[0] <= 0) {
                sb.append(convert(bytes));
            } else {
                sb.append(cArr);
            }
        }
        return sb.toString().substring(0, 1);
    }

    public static String getKBDataSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        return j < 1024 ? j + "KB" : j < 1048576 ? decimalFormat.format(((float) j) / 1024.0f) + "MB" : j < ConvertUtils.GB ? decimalFormat.format((((float) j) / 1024.0f) / 1024.0f) + "GB" : av.aG;
    }
}
