package com.vsf2f.f2f.ui.utils;

import android.content.Context;
import com.em.DemoHelper;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ConfigBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ConfigUtil {
    private static String filePath;
    private static boolean isFinished = false;

    public static void updateConfig(final Context mContext, String cusVersion) {
        final String updateURL = mContext.getString(R.string.CONFIG_URL, cusVersion);
        filePath = FolderUtil.getCachePath() + File.separator + cusVersion + "_config.txt";
        if (HyUtil.isNetworkConnected(mContext)) {
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.ConfigUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        HttpURLConnection conn = (HttpURLConnection) new URL(updateURL).openConnection();
                        conn.connect();
                        conn.getContentLength();
                        InputStream is = conn.getInputStream();
                        File configFile = new File(ConfigUtil.filePath);
                        if (configFile.exists()) {
                            MyLog.e("config", "配置文件已存在,下载进行覆盖");
                            configFile.delete();
                        }
                        FileOutputStream fos = new FileOutputStream(configFile);
                        int count = 0;
                        byte[] buf = new byte[1024];
                        do {
                            int numRead = is.read(buf);
                            count += numRead;
                            if (numRead <= 0) {
                                boolean unused = ConfigUtil.isFinished = true;
                                ConfigUtil.readConfig(mContext);
                            } else {
                                fos.write(buf, 0, numRead);
                            }
                        } while (!ConfigUtil.isFinished);
                        MyLog.e("config", "下载配置文件成功");
                        fos.close();
                        is.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            });
        } else if (new File(filePath).exists()) {
            MyLog.e("config", "无网络，配置文件已存在,读取本地");
            readConfig(mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readConfig(Context context) {
        String content1 = DesUtil.decrypt(ReadFromFile.readFileByChars(filePath), true);
        AppShare.get(context).putString(Constant.APP_CONFIG, content1);
        try {
            JSONArray list = new JSONObject(content1).getJSONArray("obj");
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < list.length(); i++) {
                JSONObject json = list.getJSONObject(i);
                map.put(json.optString("key"), json.optString("value"));
            }
            ConfigBean configBean = new ConfigBean();
            configBean.setZc_origin(map.get("zc_origin"));
            configBean.setZc_host(map.get("zc_host"));
            configBean.setF2f_origin(map.get("f2f_origin"));
            configBean.setF2f_host(map.get("f2f_host"));
            configBean.setYlzc_origin(map.get("ylzc_origin"));
            configBean.setYlzc_host(map.get("ylzc_host"));
            configBean.setNavbg_color(map.get("navbg_color"));
            configBean.setBg_color(map.get("bg_color"));
            configBean.setShop(map.get("shop"));
            configBean.setWap_mall_url(map.get("wap_mall_url"));
            configBean.setProxy_url(map.get("proxy_url"));
            configBean.setDzpt_url(map.get("dzpt_url"));
            configBean.setDph_url(map.get("dph_url"));
            configBean.setSph_url(map.get("sph_url"));
            configBean.setWith_wx(map.get("with_wx"));
            configBean.setPay_wx(map.get("pay_wx"));
            configBean.setWith_ali(map.get("with_ali"));
            configBean.setPay_ali(map.get("pay_ali"));
            configBean.setWeixin(map.get("weixin"));
            configBean.setWx_gzh(map.get("wx_gzh"));
            configBean.setCs_tel(map.get("cs_tel"));
            configBean.setCp_email(map.get("cp_email"));
            configBean.setBuygold_url(map.get("buygold_url"));
            configBean.setJoin_ad_url(map.get("join_ad_url"));
            configBean.setGame_ext_url(map.get("game_ext_url"));
            configBean.setManor_index(map.get("manor_index"));
            configBean.setPermissions_contrast(map.get("permissions_contrast"));
            configBean.setThird_web_datas(map.get("third_web_datas"));
            configBean.setGxb_buy_com(map.get("gxb_buy_com"));
            DemoHelper.getInstance().saveConfig(configBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void downDemandType(final Context mContext) {
        String v = mContext.getString(R.string.DEMAND_TYPE_V);
        final String filePath2 = FolderUtil.getCachePath() + File.separator + "demand" + v + "_config.txt";
        final String updateURL = mContext.getString(R.string.DEMAND_TYPE_URL, v);
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.ConfigUtil.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    try {
                        HttpURLConnection conn = (HttpURLConnection) new URL(updateURL).openConnection();
                        conn.connect();
                        conn.getContentLength();
                        InputStream is = conn.getInputStream();
                        Reader reader = new InputStreamReader(is, "utf-8");
                        while (true) {
                            try {
                                int tempchar = reader.read();
                                if (tempchar == -1) {
                                    break;
                                } else if (((char) tempchar) != '\r') {
                                    stringBuilder.append((char) tempchar);
                                }
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                MyLog.e("config", "下载需求属性失败");
                                return;
                            } catch (Throwable th) {
                                th = th;
                                MyLog.e("config", "");
                                throw th;
                            }
                        }
                        AppShare.get(mContext).putString(Constant.SERCVICE_CONFIG, stringBuilder.toString());
                        File txtFile = new File(filePath2);
                        if (txtFile.exists()) {
                            MyLog.e("config", "需求类型配置文件存在");
                            MyLog.e("config", "");
                            return;
                        }
                        FileOutputStream fos = new FileOutputStream(txtFile);
                        int count = 0;
                        byte[] buf = new byte[1024];
                        do {
                            int numRead = is.read(buf);
                            count += numRead;
                            if (numRead <= 0) {
                                boolean unused = ConfigUtil.isFinished = true;
                            } else {
                                fos.write(buf, 0, numRead);
                            }
                        } while (!ConfigUtil.isFinished);
                        MyLog.e("config", "下载配置文件成功");
                        fos.close();
                        is.close();
                        reader.close();
                        MyLog.e("config", "下载需求属性成功,点击重新进入");
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        });
    }
}
