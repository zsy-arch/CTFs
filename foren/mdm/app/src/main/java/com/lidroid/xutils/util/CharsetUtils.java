package com.lidroid.xutils.util;

import com.google.zxing.common.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* loaded from: classes2.dex */
public class CharsetUtils {
    public static final String DEFAULT_ENCODING_CHARSET = "ISO-8859-1";
    public static final List<String> SUPPORT_CHARSET = new ArrayList();

    private CharsetUtils() {
    }

    public static String toCharset(String str, String charset, int judgeCharsetLength) {
        try {
            return new String(str.getBytes(getEncoding(str, judgeCharsetLength)), charset);
        } catch (Throwable ex) {
            LogUtils.w(ex);
            return str;
        }
    }

    public static String getEncoding(String str, int judgeCharsetLength) {
        for (String charset : SUPPORT_CHARSET) {
            if (isCharset(str, charset, judgeCharsetLength)) {
                return charset;
            }
        }
        return "ISO-8859-1";
    }

    public static boolean isCharset(String str, String charset, int judgeCharsetLength) {
        try {
            String temp = str.length() > judgeCharsetLength ? str.substring(0, judgeCharsetLength) : str;
            return temp.equals(new String(temp.getBytes(charset), charset));
        } catch (Throwable th) {
            return false;
        }
    }

    static {
        SUPPORT_CHARSET.add("ISO-8859-1");
        SUPPORT_CHARSET.add(StringUtils.GB2312);
        SUPPORT_CHARSET.add("GBK");
        SUPPORT_CHARSET.add("GB18030");
        SUPPORT_CHARSET.add("US-ASCII");
        SUPPORT_CHARSET.add(HTTP.ASCII);
        SUPPORT_CHARSET.add("ISO-2022-KR");
        SUPPORT_CHARSET.add("ISO-8859-2");
        SUPPORT_CHARSET.add("ISO-2022-JP");
        SUPPORT_CHARSET.add("ISO-2022-JP-2");
        SUPPORT_CHARSET.add("UTF-8");
    }
}
