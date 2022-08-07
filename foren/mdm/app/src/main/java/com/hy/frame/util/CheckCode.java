package com.hy.frame.util;

import android.content.Context;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class CheckCode {
    private static CheckCode instance = null;

    public static CheckCode get() {
        if (instance == null) {
            instance = new CheckCode();
        }
        return instance;
    }

    public boolean isMobile(String mobiles) {
        if (mobiles == null) {
            return false;
        }
        return Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$").matcher(mobiles).matches();
    }

    public String floatToString(float f) {
        String str = f + "";
        if (str.indexOf(".") > 0) {
            return str.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return str;
    }

    public boolean isContainSpecialSymbols(String str) {
        return !Pattern.compile("[^\\:\\!\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\\\\\]\\^\\_\\`\\{\\|\\}\\~]*").matcher(str).matches();
    }

    public String moneyToString(int money) {
        return floatToString(money / 10.0f);
    }

    public boolean isNum(String str) {
        return str.matches("[0-9]+");
    }

    public int dip2px(Context context, float dipValue) {
        return (int) ((dipValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public boolean isImage(String path) {
        return path.matches("(?i).+?\\.(png|jpg|gif|bmp)");
    }
}
