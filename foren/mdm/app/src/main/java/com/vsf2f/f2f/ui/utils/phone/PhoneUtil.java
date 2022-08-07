package com.vsf2f.f2f.ui.utils.phone;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.cdlinglu.utils.PermissionUtil;
import com.hy.frame.util.HyUtil;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.bean.PhoneBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import u.aly.av;

/* loaded from: classes2.dex */
public class PhoneUtil {
    public static List<PhoneBean> getPhoneNumberFromMobile(Activity activity) {
        Cursor cursor;
        List<PhoneBean> list = new ArrayList<>();
        if (PermissionUtil.getContactsPermissions(activity, 111) && (cursor = activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)) != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(av.g));
                String number = clearPhoneNumber(cursor.getString(cursor.getColumnIndex("data1")));
                if (!HyUtil.isEmpty(number)) {
                    PhoneBean phoneInfo = new PhoneBean();
                    phoneInfo.setName(name);
                    phoneInfo.setPhone(number);
                    list.add(phoneInfo);
                }
            }
            cursor.close();
        }
        return list;
    }

    public static String clearPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return "";
        }
        String replace = phoneNumber;
        if (replace.startsWith("+86")) {
            replace = replace.replace("+86", "");
        }
        if (replace.contains(HanziToPinyin.Token.SEPARATOR)) {
            replace = replace.replaceAll(HanziToPinyin.Token.SEPARATOR, "");
        }
        if (replace.contains("-")) {
            return replace.replaceAll("-", "");
        }
        return replace;
    }

    public static Set<String> getKeySetByMap(Map<String, PhoneBean> map) {
        Set<String> keySet = new HashSet<>();
        keySet.addAll(map.keySet());
        return keySet;
    }

    public static List<String> getKeyListBySet(Set<String> set) {
        List<String> keyList = new ArrayList<>();
        keyList.addAll(set);
        return keyList;
    }
}
