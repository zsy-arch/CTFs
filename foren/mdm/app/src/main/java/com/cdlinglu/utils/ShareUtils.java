package com.cdlinglu.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.MyApplication;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.ProductBean;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.bean.ShopInfoBean;
import com.vsf2f.f2f.ui.other.BaseUiListener;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ShareUtils {
    private static ShareUtils instance;
    private Context context;
    private boolean exception;
    private int flag;
    private BaseUiListener listener;
    private Tencent mTencent;
    private ShareThirdBean share;
    private Bitmap bitmap = null;
    Handler handler = new Handler() { // from class: com.cdlinglu.utils.ShareUtils.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                ShareUtils.this.wxShare(ShareUtils.this.bitmap);
            }
        }
    };

    public ShareUtils(Context context) {
        this.context = context;
        this.listener = new BaseUiListener(context);
        this.mTencent = Tencent.createInstance(IDUtil.QQ_ID, context.getApplicationContext());
    }

    public static ShareUtils get(Context context) {
        if (instance == null) {
            instance = new ShareUtils(context);
        }
        return instance;
    }

    public BaseUiListener getListener() {
        return this.listener;
    }

    public void setListener(BaseUiListener listener) {
        this.listener = listener;
    }

    public void qqShare() {
        if (checkQQ()) {
            Bundle params = new Bundle();
            params.putInt("req_type", 1);
            params.putString("title", getShare().getTitle());
            params.putString("summary", getShare().getContext());
            params.putString("targetUrl", getShare().getUrl());
            params.putString("imageUrl", getShare().getPic());
            this.mTencent.shareToQQ((Activity) this.context, params, this.listener);
        }
    }

    public void qqSharePic(String picpath) {
        if (checkQQ()) {
            Bundle params = new Bundle();
            params.putInt("req_type", 5);
            params.putString("imageLocalUrl", picpath);
            this.mTencent.shareToQQ((Activity) this.context, params, this.listener);
        }
    }

    public void qqZoneSharePic() {
        if (checkQQ()) {
            Bundle params = new Bundle();
            ArrayList<String> url = new ArrayList<>();
            url.add(getShare().getPic());
            params.putInt("req_type", 1);
            params.putString("title", getShare().getTitle());
            params.putString("targetUrl", getShare().getUrl());
            params.putStringArrayList("imageUrl", url);
            this.mTencent.shareToQzone((Activity) this.context, params, this.listener);
        }
    }

    public void qqZoneShare() {
        if (checkQQ()) {
            Bundle params = new Bundle();
            params.putString("title", getShare().getTitle());
            if (!TextUtils.isEmpty(getShare().getContext())) {
                params.putString("summary", getShare().getContext());
            }
            params.putString("targetUrl", getShare().getUrl());
            ArrayList<String> url = new ArrayList<>();
            if (TextUtils.isEmpty(getShare().getPic())) {
                getShare().setPic("http://vsf2f.com/vj_logo_s.png");
            }
            url.add(getShare().getPic());
            params.putInt("req_type", 1);
            params.putStringArrayList("imageUrl", url);
            this.mTencent.shareToQzone((Activity) this.context, params, this.listener);
        }
    }

    public void wxShare(Bitmap bitmap) {
        int i = 1;
        try {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = getShare().getUrl();
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = getShare().getTitle();
            msg.description = getShare().getContext();
            if (this.exception) {
                msg.setThumbImage(BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.ico_logo));
            } else if (bitmap == null) {
                msg.setThumbImage(BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.ico_logo));
            } else {
                msg.setThumbImage(Bitmap.createScaledBitmap(bitmap, 120, 120, true));
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            if (this.flag == 0) {
                i = 0;
            }
            req.scene = i;
            MyApplication.wxApi.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wxSharePic(int flag, Bitmap bitmap) {
        int i = 1;
        if (checkWx()) {
            try {
                WXImageObject imageObject = new WXImageObject(bitmap);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imageObject;
                msg.setThumbImage(Bitmap.createScaledBitmap(bitmap, 120, 120, true));
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                if (flag == 0) {
                    i = 0;
                }
                req.scene = i;
                MyApplication.wxApi.sendReq(req);
            } catch (Exception e) {
                e.printStackTrace();
                MyToast.show(this.context, "请重试");
            }
        }
    }

    public void GetLocalOrNetBitmap() {
        try {
            InputStream in = new BufferedInputStream(new URL(getShare().getPic()).openStream(), 1024);
            ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(dataStream, 1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            this.bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            wxShare(this.bitmap);
            this.exception = true;
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[1024];
        while (true) {
            int read = in.read(b);
            if (read != -1) {
                out.write(b, 0, read);
            } else {
                return;
            }
        }
    }

    public void shareTask(int flag) {
        this.flag = flag;
        if (checkWx()) {
            ThreadPool.newThreadPool(new Runnable() { // from class: com.cdlinglu.utils.ShareUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    ShareUtils.this.GetLocalOrNetBitmap();
                    if (!ShareUtils.this.exception) {
                        ShareUtils.this.handler.sendEmptyMessage(0);
                    }
                }
            });
        }
    }

    public ShareThirdBean getShare() {
        if (this.share == null) {
            this.share = new ShareThirdBean();
        }
        return this.share;
    }

    public void setShare(ShareThirdBean share) {
        this.share = share;
    }

    public Tencent getmTencent() {
        return this.mTencent;
    }

    public boolean checkWx() {
        if (MyApplication.wxApi.isWXAppInstalled()) {
            return true;
        }
        MyToast.show(this.context, (int) R.string.no_install_wx);
        return false;
    }

    private boolean checkQQ() {
        if (this.mTencent != null && this.mTencent.isSupportSSOLogin((Activity) this.context)) {
            return true;
        }
        MyToast.show(this.context, (int) R.string.no_install_qq);
        return false;
    }

    public AjaxParams shareShop(ShopInfoBean shop) {
        String username = DemoHelper.getInstance().getCurrentUserName();
        String herf = ComUtil.getZCApi(this.context, "/m/shop/" + username + ".mobile");
        JSONObject jsonObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(shop.getLogo().getSpath())) {
                String p = shop.getLogo().getSpath();
                jsonObject.put("logo", p.substring(p.indexOf(".com") + 5, p.indexOf("?")));
            }
            jsonObject.put("type", "shop");
            jsonObject.put("userName", username);
            jsonObject.put("href", herf);
            jsonObject.put("storeName", shop.getStoreName());
            jsonObject.put(EditActivity.CONTENT, shop.getDescription());
            jsonObject.put("sales", 0);
            jsonObject.put("inSale", 3);
            jsonObject.put(FlagUtil.URL_SAVE, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shareCircle(jsonObject.toString(), herf);
    }

    public AjaxParams shareCircle(String shop, String herf) {
        AjaxParams params = new AjaxParams();
        params.put(EditActivity.CONTENT, shop);
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("href", herf);
        params.put("type", 4);
        params.put("categoryId", 0);
        return params;
    }

    public AjaxParams shareProduct(ProductBean productShare) {
        JSONObject jsonObject = new JSONObject();
        String herf = ComUtil.getZCApi(this.context, "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + productShare.getGuid());
        try {
            if (!TextUtils.isEmpty(productShare.getPicture().getSpath())) {
                JSONArray jsonArray = new JSONArray();
                String p = productShare.getPicture().getSpath();
                jsonArray.put(p.substring(p.indexOf(".com") + 5, p.indexOf("?")));
                jsonObject.put("pic", jsonArray);
            }
            jsonObject.put("type", Constant.PRODUCTS_BUCKET);
            jsonObject.put("guid", productShare.getGuid());
            jsonObject.put("href", herf);
            jsonObject.put("userName", DemoHelper.getInstance().getCurrentUserName());
            jsonObject.put("goodsName", productShare.getGoodsName());
            jsonObject.put(EditActivity.CONTENT, productShare.getExplains());
            jsonObject.put(f.aS, productShare.getSalesPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shareCircle(jsonObject.toString(), herf);
    }

    public AjaxParams shareNeeds(DemandDetailBean detailBean, String type) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (detailBean.getImgUrlList().size() != 0) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(detailBean.getImgUrlList().get(0));
                jsonObject.put("pic", jsonArray);
            }
            jsonObject.put("type", type);
            jsonObject.put("bizId", detailBean.getMoId());
            jsonObject.put("userName", detailBean.getPublishUser());
            jsonObject.put("title", detailBean.getTitle());
            jsonObject.put(EditActivity.CONTENT, detailBean.getDescription());
            jsonObject.put(f.aS, detailBean.getReward());
            jsonObject.put("unit", detailBean.getUnit());
            jsonObject.put("typeName", detailBean.getServiceModeStr());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shareCircle(jsonObject.toString(), "");
    }
}
