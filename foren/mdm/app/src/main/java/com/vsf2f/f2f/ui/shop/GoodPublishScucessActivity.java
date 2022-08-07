package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.view.View;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ProductBasicBean;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;
import com.vsf2f.f2f.ui.utils.Constant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GoodPublishScucessActivity extends BaseActivity {
    private boolean has = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_good_scucess;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.publish_scucess, R.drawable.icon_shop_ransmit);
        setOnClickListener(R.id.PublishScucess_addSpec);
        setOnClickListener(R.id.PublishScucess_addDetail);
        UserShared.getInstance().setStoreGoodNum(UserShared.getInstance().getStoreGoodNum() + 1);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.PublishScucess_addSpec /* 2131755310 */:
                getBundle().putBoolean("has", this.has);
                startActForResult(GoodsSpecActivity.class, getBundle(), 111);
                return;
            case R.id.PublishScucess_addDetail /* 2131755311 */:
                Intent descIntent = new Intent(this, AddDescriptionActivity.class);
                descIntent.putExtra("guid", getBundle().getString("goodsGuid"));
                startActivity(descIntent);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_APP_BASIC_PRODUCT /* 2131296292 */:
                if (UserShared.getInstance().getStoreGoodNum() >= 3) {
                    shareCircle((ProductBasicBean) result.getObj());
                    return;
                } else {
                    MyToast.show(this.context, "上架三个以上产品，激活店铺后，才可以分享推广产品");
                    return;
                }
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享圈子成功");
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog operateDialog = new MoreOperateDialog(this.context);
        operateDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.shop.GoodPublishScucessActivity.1
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                if (flag == R.id.more_btnShare2) {
                    GoodPublishScucessActivity.this.getProductInfo(GoodPublishScucessActivity.this.getBundle().getString("goodsGuid"));
                }
            }
        });
        operateDialog.showBtnShare2();
        operateDialog.show();
    }

    public void getProductInfo(String guid) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().get(R.string.API_APP_BASIC_PRODUCT, ComUtil.getZCApi(this.context, getString(R.string.API_APP_BASIC_PRODUCT, new Object[]{guid})), params, ProductBasicBean.class, false);
    }

    public void shareCircle(ProductBasicBean productInfo) {
        AjaxParams params = new AjaxParams();
        JSONObject jsonObject = new JSONObject();
        try {
            if (productInfo.getGoodsPicture() != null) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(productInfo.getGoodsPicture().getPath());
                jsonObject.put("pic", jsonArray);
            }
            jsonObject.put("type", Constant.PRODUCTS_BUCKET);
            jsonObject.put("guid", productInfo.getGuid());
            jsonObject.put("href", ComUtil.getZCApi(this.context, "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + productInfo.getGuid()));
            jsonObject.put("userName", DemoHelper.getInstance().getCurrentUserName());
            jsonObject.put("goodsName", productInfo.getGoodsName());
            jsonObject.put(EditActivity.CONTENT, productInfo.getExplains());
            jsonObject.put(f.aS, productInfo.getSalesPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put(EditActivity.CONTENT, jsonObject.toString());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("type", 4);
        params.put("categoryId", 0);
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), params, String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 111) {
            this.has = true;
        }
    }
}
