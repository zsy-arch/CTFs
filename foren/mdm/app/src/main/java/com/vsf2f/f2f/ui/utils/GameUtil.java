package com.vsf2f.f2f.ui.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.TbsWebActivity;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class GameUtil {
    public static String getVsSign(String jsonStr) {
        return getVsSign(jsonStr, false);
    }

    public static String getVsSign(String jsonStr, boolean isPay) {
        try {
            Map<String, String> params = (Map) JSONObject.parseObject(jsonStr, Map.class);
            params.put("appKey", "VSGT2017120622410007148");
            params.put("nonceStr", ComUtil.getRandomString(32));
            params.put("timestamp", String.valueOf(System.currentTimeMillis()));
            params.put("version", DemoHelper.getInstance().getVersion());
            params.put("signType", "MD5");
            params.put("sign", ComUtil.encryptMap(params, isPay));
            String encrypt = DesUtil.encrypt(JSONObject.toJSONString(params));
            org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("vs_access_sign", encrypt);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void startGame(Context context) {
        String manorUrl = DemoHelper.getInstance().readConfig().getManor_index();
        if (TextUtils.isEmpty(manorUrl)) {
            manorUrl = context.getString(R.string.API_GAME_HTML);
        }
        Bundle bundleWeb = new Bundle();
        bundleWeb.putBoolean("isLoadGame", true);
        bundleWeb.putString(Constant.FLAG, manorUrl);
        Intent intentWeb = new Intent(context, TbsWebActivity.class);
        intentWeb.putExtra(Constant.BUNDLE, bundleWeb);
        context.startActivity(intentWeb);
    }
}
