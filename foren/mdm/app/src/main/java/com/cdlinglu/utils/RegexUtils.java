package com.cdlinglu.utils;

import com.autonavi.ae.guide.GuideControl;
import com.hyphenate.util.EMPrivateConstant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class RegexUtils {
    public static boolean isIdCard(String IDStr) {
        try {
            return new RegexUtils().V_IDCARD(IDStr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isUrl(String isUrlStr) {
        return test(isUrlStr, "[hH][Tt]{2}[pP][sS]?[\\:][\\/]{2}.+");
    }

    public static boolean isEnglish(String str) {
        return test(str, "[a-zA-Z]*");
    }

    public static boolean isNumber(String str) {
        return test(str, "[0-9]*");
    }

    public static boolean isDate(String strDate) {
        return test(strDate, "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    }

    public static boolean test(String regexString, boolean case_insensitive, String regex) {
        if ((case_insensitive ? Pattern.compile(regex, 2) : Pattern.compile(regex)).matcher(regexString).matches()) {
            return true;
        }
        return false;
    }

    public static boolean test(String regexString, String regex) {
        if (regexString == null) {
            return false;
        }
        return regexString.matches(regex);
    }

    private boolean V_IDCARD(String IDStr) throws Exception {
        String[] ValCodeArr = {"1", "0", EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME, GuideControl.CHANGE_PLAY_TYPE_LZL_COMMON, "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", GuideControl.CHANGE_PLAY_TYPE_LZL_COMMON, "10", "5", "8", "4", "2", "1", "6", "3", "7", GuideControl.CHANGE_PLAY_TYPE_LZL_COMMON, "10", "5", "8", "4", "2"};
        String Ai = "";
        if (IDStr.length() == 15 || IDStr.length() == 18) {
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }
            if (!isNumber(Ai)) {
                throw new Exception("身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。");
            }
            String strYear = Ai.substring(6, 10);
            String strMonth = Ai.substring(10, 12);
            String strDay = Ai.substring(12, 14);
            if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
                throw new Exception("身份证生日无效。");
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0) {
                    throw new Exception("身份证生日不在有效范围。");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                throw new Exception("身份证月份无效");
            } else if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                throw new Exception("身份证日期无效");
            } else {
                int TotalmulAiWi = 0;
                for (int i = 0; i < 17; i++) {
                    TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
                }
                String Ai2 = Ai + ValCodeArr[TotalmulAiWi % 11];
                if (IDStr.length() != 18 || Ai2.equals(IDStr.toLowerCase())) {
                    return true;
                }
                throw new Exception("身份证无效，不是合法的身份证号码");
            }
        } else {
            throw new Exception("身份证号码长度应该为15位或18位。");
        }
    }

    public static boolean isMatch(String string, String pattern, boolean ignoreCase) {
        return ignoreCase ? Pattern.compile(pattern, 2).matcher(string).matches() : Pattern.compile(pattern).matcher(string).matches();
    }

    public static boolean isMatch(String string, String pattern) {
        return isMatch(string, pattern, false);
    }

    public static String searchPattern(String string, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(string);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static String replace(String string, String pattern, String replaceOf) {
        return Pattern.compile(pattern).matcher(string).replaceAll(replaceOf);
    }

    public static void main(String[] args) {
        System.out.println(isIdCard("452402198902305474"));
    }
}
