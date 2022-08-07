package com.easeui.utils;

import com.hyphenate.util.EMLog;
import com.hyphenate.util.ImageUtils;
import com.hyphenate.util.PathUtil;

/* loaded from: classes.dex */
public class EaseImageUtils extends ImageUtils {
    public static String getImagePath(String remoteUrl) {
        String path = PathUtil.getInstance().getImagePath() + "/" + remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1, remoteUrl.length());
        EMLog.d("msg", "image path:" + path);
        return path;
    }

    public static String getThumbnailImagePath(String thumbRemoteUrl) {
        String path = PathUtil.getInstance().getImagePath() + "/th" + thumbRemoteUrl.substring(thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
        EMLog.d("msg", "thum image path:" + path);
        return path;
    }
}
