package com.vsf2f.f2f.ui.view;

import android.text.InputFilter;
import android.text.Spanned;

/* loaded from: classes2.dex */
public class PointLengthFilter implements InputFilter {
    private static final int DECIMAL_DIGITS = 2;
    private int length;

    public PointLengthFilter() {
    }

    public PointLengthFilter(int length) {
        this.length = length;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if ("".equals(source.toString())) {
            return null;
        }
        String dValue = dest.toString();
        if (".".equals(source)) {
            int dlength = dValue.length();
            if (dValue.contains(".")) {
                return "";
            }
            if (dlength == 0 || (dstart == 0 && dlength <= 2)) {
                return "0.";
            }
            if (dstart + 2 >= dlength) {
                return null;
            }
            return "";
        }
        String[] splitArray = dValue.split("\\.");
        int numLength = splitArray[0].length();
        if (this.length != 0 && this.length <= numLength && dstart <= numLength && !".".equals(source)) {
            return "";
        }
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            if (dstart > numLength && dotValue.length() >= 2) {
                return "";
            }
        }
        return null;
    }
}
