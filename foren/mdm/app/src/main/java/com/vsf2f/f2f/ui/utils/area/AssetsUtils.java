package com.vsf2f.f2f.ui.utils.area;

import android.content.Context;
import com.hy.frame.util.MyLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class AssetsUtils {
    public static String readText(Context context, String assetPath) {
        MyLog.d("read assets file as text: " + assetPath);
        try {
            return ConvertUtils.toString(context.getAssets().open(assetPath));
        } catch (Exception e) {
            MyLog.e(e);
            return "";
        }
    }

    public static String readJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
