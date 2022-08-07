package com.vsf2f.f2f.ui.utils.list;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class ListStringUtil {
    private static boolean isContent = false;

    public static boolean HaveContent(List<String> data) {
        if (isContent) {
            isContent = false;
        }
        if (data == null) {
            return isContent;
        }
        for (int i = 0; i < data.size(); i++) {
            String str = data.get(i);
            if (!TextUtils.isEmpty(str) && !"".equals(str) && !isContent) {
                isContent = true;
            }
        }
        return isContent;
    }
}
