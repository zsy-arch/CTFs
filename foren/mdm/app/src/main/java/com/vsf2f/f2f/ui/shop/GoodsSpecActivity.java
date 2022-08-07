package com.vsf2f.f2f.ui.shop;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GoodsTypeListBean;
import com.vsf2f.f2f.bean.ProductBasicBean;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GoodsSpecActivity extends BaseActivity {
    private String goodsGuid;
    private String goodsId;
    private ScrollView lly_scroll;
    private LinearLayout lly_spec;
    private String productName;
    private String resRatio;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_goods_spec_list;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.edit_goods_type, R.string.save);
        this.lly_spec = (LinearLayout) getViewAndClick(R.id.lly_spec);
        this.lly_scroll = (ScrollView) getViewAndClick(R.id.lly_scroll);
        setOnClickListener(R.id.add_spec);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() != null) {
            this.goodsId = getBundle().getString("goodsId");
            this.goodsGuid = getBundle().getString("goodsGuid");
            this.productName = getBundle().getString("productName");
            this.resRatio = getBundle().getString("resRatio");
            if (getBundle().getBoolean("has")) {
                getProductSpec();
            } else {
                addSpecView();
            }
        }
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
            case R.id.add_spec /* 2131755350 */:
                addSpecView();
                return;
            default:
                return;
        }
    }

    private void addSpecView() {
        final View specview = LayoutInflater.from(this.context).inflate(R.layout.layout_good_pubulish_spec, (ViewGroup) null);
        specview.findViewById(R.id.spec_llyDel).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsSpecActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GoodsSpecActivity.this.lly_spec.removeView(specview);
            }
        });
        this.lly_spec.addView(specview);
        this.lly_scroll.post(new Runnable() { // from class: com.vsf2f.f2f.ui.shop.GoodsSpecActivity.2
            @Override // java.lang.Runnable
            public void run() {
                GoodsSpecActivity.this.lly_scroll.fullScroll(130);
            }
        });
        specview.findViewById(R.id.et_model).requestFocus();
    }

    private void addSpecViewTxt(String txtGuid, String txtModel, String txtPrice, String txtStock) {
        final View specview = LayoutInflater.from(this.context).inflate(R.layout.layout_good_pubulish_spec, (ViewGroup) null);
        ((TextView) specview.findViewById(R.id.txt_guid)).setText(txtGuid);
        ((EditText) specview.findViewById(R.id.et_model)).setText(txtModel);
        ((EditText) specview.findViewById(R.id.et_price)).setText(txtPrice);
        ((EditText) specview.findViewById(R.id.et_stock)).setText(txtStock);
        specview.findViewById(R.id.spec_llyDel).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsSpecActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GoodsSpecActivity.this.lly_spec.removeView(specview);
            }
        });
        this.lly_spec.addView(specview);
    }

    private void getProductSpec() {
        getClient().get(R.string.API_APP_PRODUCT, ComUtil.getZCApi(this.context, getString(R.string.API_APP_PRODUCT, new Object[]{this.goodsGuid})), new AjaxParams(), ProductBasicBean.class, false);
    }

    private void addSpecForApp2() {
        JSONArray array = new JSONArray();
        for (int i = 0; i < this.lly_spec.getChildCount(); i++) {
            try {
                View specview = this.lly_spec.getChildAt(i);
                String txtmodel = ((EditText) specview.findViewById(R.id.et_model)).getText().toString();
                if (!TextUtils.isEmpty(txtmodel)) {
                    JSONObject object = new JSONObject();
                    object.put("goodsId", this.goodsId);
                    object.put("goodsGuid", this.goodsGuid);
                    object.put("resRatio", this.resRatio);
                    object.put("productName", ComUtil.UTF(this.productName));
                    object.put("salesPrice", ((EditText) specview.findViewById(R.id.et_price)).getText().toString());
                    object.put("store", ((EditText) specview.findViewById(R.id.et_stock)).getText().toString());
                    JSONArray specArray = new JSONArray();
                    JSONObject specObject = new JSONObject();
                    specObject.put("specName", "型号");
                    specObject.put("specGuid", "VSF2F0COMMON0SPEC0GUID0FOR0MODEL");
                    specObject.put("specValue", ComUtil.UTF(txtmodel));
                    specObject.put("specValueGuid", ((TextView) specview.findViewById(R.id.txt_guid)).getText().toString());
                    specArray.put(specObject);
                    object.put("productsSpecsVoList", specArray);
                    array.put(object);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (array.length() > 0) {
            MyLog.e(array.toString());
            getClient().post(R.string.API_PRODUCT_ADD_SPEC2, ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCT_ADD_SPEC2)), array.toString(), null, String.class, false);
            return;
        }
        getClient().post(R.string.API_GOODS_SPEC_DELALL, ComUtil.getZCApi(this.context, getString(R.string.API_GOODS_SPEC_DELALL, new Object[]{this.goodsGuid})), null, String.class);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        addSpecForApp2();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_APP_PRODUCT /* 2131296295 */:
                List<GoodsTypeListBean> datas = ((ProductBasicBean) result.getObj()).getProductsList();
                for (int i = 0; i < datas.size(); i++) {
                    GoodsTypeListBean typebean = datas.get(i);
                    addSpecViewTxt(typebean.getProductsSpecsVoList().get(0).getSpecValueGuid(), typebean.getProductsSpecsVoList().get(0).getSpecValue(), typebean.getSalesPrice(), typebean.getStore());
                }
                return;
            case R.string.API_GOODS_SPEC_DELALL /* 2131296363 */:
                break;
            case R.string.API_PRODUCT_ADD_SPEC2 /* 2131296392 */:
                setResult(-1);
                break;
            default:
                return;
        }
        MyToast.show(this.context, "保存成功");
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_APP_PRODUCT /* 2131296295 */:
                addSpecView();
                return;
            default:
                super.onRequestError(result);
                return;
        }
    }
}
