package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PublishGridAdapter;
import com.vsf2f.f2f.bean.ProductBasicBean;
import com.vsf2f.f2f.bean.ProductBean;
import com.vsf2f.f2f.bean.ProductCategory;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.ui.view.PointLengthFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class GoodsEditActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_DESC = 60;
    private static final int REQUEST_GOODS_CLASSIFY = 40;
    private static final int REQUEST_GOODS_TYPE = 50;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private static final int REQUEST_SHOP_MENU = 30;
    private PublishGridAdapter adapter;
    private String carriageStr;
    private EditText carriageView;
    private String descStr;
    private EditText descView;
    private String goodMoney;
    private KeyValueView goods_type;
    private GridView gridView;
    private String nameStr;
    private EditText nameView;
    private KeyValueView postShopView;
    private TextView postView;
    private String priceStr;
    private EditText priceView;
    private ProductBean productBean;
    private ProductBasicBean productInfo;
    private Spinner saleView;
    private String storeStr;
    private EditText storeView;
    private EditText voucherView;
    private static String resRatio = "";
    private static String goodsCategoryId = null;
    private static String goodsTypedId = null;
    private static String shopMenusId = null;
    private static String goodsTypeGuid = null;
    private static String guid = null;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String usableCoupon = "0";

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_edit_goods;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.goods_edit, R.string.save);
        setOnClickListener(R.id.kv_add_detail_edit);
        setOnClickListener(R.id.kv_add_type_edit);
        this.nameView = (EditText) getView(R.id.et_goods_name_eidt);
        this.storeView = (EditText) getView(R.id.et_store_num_edit);
        this.descView = (EditText) getView(R.id.et_goods_description_edit);
        this.priceView = (EditText) getView(R.id.et_goods_price_edit);
        this.priceView.setFilters(new InputFilter[]{new PointLengthFilter(7)});
        this.saleView = (Spinner) getView(R.id.sp_for_sale_eidt);
        this.gridView = (GridView) getView(R.id.gridView_edit_goods);
        this.carriageView = (EditText) getView(R.id.et_carriage_edit);
        this.postShopView = (KeyValueView) getViewAndClick(R.id.kv_post_shop_edit);
        this.goods_type = (KeyValueView) getViewAndClick(R.id.kv_select_goods_type_edit);
        this.voucherView = (EditText) getView(R.id.et_voucher_edit);
        this.postView = (TextView) getViewAndClick(R.id.btn_edit_goods);
        this.adapter = new PublishGridAdapter(this, this.imagePaths);
        this.saleView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = GoodsEditActivity.this.getResources().getStringArray(R.array.for_sale_array)[position];
                if (str.contains("%")) {
                    String unused = GoodsEditActivity.resRatio = str.substring(0, str.indexOf("%"));
                    GoodsEditActivity.this.changeRealMoney();
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        ProductBean productBean;
        if (!(getBundle() == null || (productBean = (ProductBean) getBundle().getParcelable(Constant.FLAG)) == null)) {
            guid = productBean.getGuid();
            if (!TextUtils.isEmpty(guid) && !"".equals(guid)) {
                getProductInfo(guid);
            }
        }
        this.priceView.addTextChangedListener(new EnterDecimalListener());
        this.carriageView.addTextChangedListener(new EnterDecimalListener());
        this.gridView.setAdapter((ListAdapter) this.adapter);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (GoodsEditActivity.this.imagePaths.size() >= 8 || i != GoodsEditActivity.this.imagePaths.size()) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(GoodsEditActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(GoodsEditActivity.this.imagePaths);
                    GoodsEditActivity.this.startActivityForResult(intent, 20);
                    return;
                }
                PhotoPickerIntent intent2 = new PhotoPickerIntent(GoodsEditActivity.this.context);
                intent2.setSelectModel(SelectModel.MULTI);
                intent2.setShowCamera(true);
                intent2.setMaxTotal(8);
                intent2.setSelectedPaths(GoodsEditActivity.this.imagePaths);
                GoodsEditActivity.this.startActivityForResult(intent2, 10);
            }
        });
        this.voucherView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.3
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    GoodsEditActivity.this.usableCoupon = s.toString();
                } else {
                    GoodsEditActivity.this.usableCoupon = "0";
                }
                GoodsEditActivity.this.changeRealMoney();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.priceView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.4
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    GoodsEditActivity.this.goodMoney = s.toString();
                } else {
                    GoodsEditActivity.this.goodMoney = "0";
                }
                GoodsEditActivity.this.changeRealMoney();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    loadAdapter(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdapter(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                case 30:
                    String selectName = data.getStringExtra("selectName");
                    shopMenusId = data.getStringExtra("shopMenusId");
                    this.postShopView.getTxtKey().setText(selectName);
                    this.postShopView.getTxtKey().setTextColor(SupportMenu.CATEGORY_MASK);
                    return;
                case 40:
                    this.goods_type.getTxtKey().setText(data.getStringExtra("name"));
                    ProductCategory product = (ProductCategory) data.getParcelableExtra(com.vsf2f.f2f.ui.utils.Constant.PRODUCT);
                    goodsTypedId = Integer.toString(product.getTypeId());
                    goodsCategoryId = Integer.toString(product.getId());
                    goodsTypeGuid = product.getTypeGuid();
                    return;
                case 50:
                    this.productInfo.setOnSpec("1");
                    return;
                default:
                    return;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        if (this.imagePaths == null) {
            this.imagePaths = new ArrayList<>();
        }
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        List<ProductBasicBean.AttachPictureListBean> attachPictureList = this.productInfo.getAttachPictureList();
        for (int j = 0; j < attachPictureList.size(); j++) {
            if (!(attachPictureList.get(j).getPictures() == null || attachPictureList.get(j).getPictures().getPath() == null)) {
                this.imagePaths.add(attachPictureList.get(j).getPictures().getSpath());
            }
        }
        if (this.productInfo.getUsableCoupon() < 0) {
            this.voucherView.setText("0");
        } else {
            this.voucherView.setText(this.productInfo.getUsableCoupon() + "");
        }
        this.nameView.setText(this.productInfo.getGoodsName());
        this.priceView.setText(this.productInfo.getSalesPrice() + "");
        int resRatio2 = this.productInfo.getResRatio();
        int ratioPosi = 5;
        String[] saleArray = getResources().getStringArray(R.array.for_sale_array);
        for (int i = 0; i < saleArray.length; i++) {
            if (saleArray[i].equals(resRatio2 + "%")) {
                ratioPosi = i;
                changeRealMoney();
            }
        }
        this.saleView.setSelection(ratioPosi);
        this.storeView.setText(this.productInfo.getStore() + "");
        this.carriageView.setText(this.productInfo.getDefaultFreight() + "");
        this.descView.setText(this.productInfo.getExplains());
        this.goods_type.getTxtKey().setText(this.productInfo.getGoodsCategoryName());
        this.postShopView.getTxtKey().setText(this.productInfo.getShopMenusName());
        goodsCategoryId = Integer.toString(this.productInfo.getGoodsCategoryId());
        goodsTypedId = Integer.toString(this.productInfo.getGoodsTypeId());
        shopMenusId = this.productInfo.getShopMenusId();
        goodsTypeGuid = this.productInfo.getGoodsTypeGuid();
        this.productBean = new ProductBean();
        this.productBean.setId(this.productInfo.getId());
        this.productBean.setGuid(this.productInfo.getGuid());
        this.productBean.setIssuer(this.productInfo.getIssuer());
        this.productBean.setDefaultImageId(this.productInfo.getDefaultImageId());
        this.productBean.setShopMenusId(this.productInfo.getShopMenusId());
        this.productBean.setGoodsName(this.productInfo.getGoodsName());
        this.productBean.setGoodsTypeId(this.productInfo.getGoodsTypeId());
        this.productBean.setGoodsCategoryId(this.productInfo.getGoodsCategoryId());
        this.productBean.setDefaultFreight(this.productInfo.getDefaultFreight());
        this.productBean.setSalesPrice(this.productInfo.getSalesPrice());
        this.productBean.setResPrice(this.productInfo.getResPrice());
        this.productBean.setResRatio(this.productInfo.getResRatio());
        this.productBean.setExplains(this.productInfo.getExplains());
        this.productBean.setStore(this.productInfo.getStore());
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.kv_select_goods_type_edit /* 2131755988 */:
                startActForResult(GoodsClassifyActivity.class, 40);
                return;
            case R.id.kv_post_shop_edit /* 2131755995 */:
                startActForResult(ClassifyActivity.class, 30);
                return;
            case R.id.kv_add_type_edit /* 2131755997 */:
                if (this.productInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("goodsId", this.productInfo.getId() + "");
                    bundle.putString("goodsGuid", this.productInfo.getGuid());
                    bundle.putString("productName", this.productInfo.getGoodsName());
                    bundle.putString("resRatio", resRatio);
                    bundle.putBoolean("has", this.productInfo.getOnSpec().equals("1"));
                    startActForResult(GoodsSpecActivity.class, bundle, 50);
                    return;
                }
                return;
            case R.id.kv_add_detail_edit /* 2131755998 */:
                if (guid != null && !TextUtils.isEmpty(guid) && !"".equals(guid)) {
                    MyLog.e("guid-->" + guid);
                    Intent descIntent = new Intent(this, AddDescriptionActivity.class);
                    descIntent.putExtra("guid", guid);
                    startActivityForResult(descIntent, 60);
                    return;
                }
                return;
            case R.id.btn_edit_goods /* 2131755999 */:
                dealWithContent();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        dealWithContent();
    }

    private void dealWithContent() {
        this.nameStr = this.nameView.getText().toString().trim();
        this.priceStr = this.priceView.getText().toString().trim();
        this.storeStr = this.storeView.getText().toString().trim();
        this.carriageStr = this.carriageView.getText().toString().trim();
        this.descStr = this.descView.getText().toString().trim();
        if (this.imagePaths.size() == 0) {
            MyToast.show(this, "请添加商品图片");
        } else if (TextUtils.isEmpty(this.nameStr) || "".equals(this.nameStr)) {
            MyToast.show(this, "请填写商品名称");
        } else if (TextUtils.isEmpty(this.priceStr)) {
            MyToast.show(this, "请填写商品价格");
        } else if (TextUtils.isEmpty(goodsCategoryId) || TextUtils.isEmpty(goodsTypedId)) {
            MyToast.show(this, "请选择商品分类");
        } else if (TextUtils.isEmpty(shopMenusId)) {
            MyToast.show(this, "请选择发布分类");
        } else {
            getClient().showDialogNow(R.string.uploading);
            if (TextUtils.isEmpty(resRatio) || "".equals(resRatio)) {
                resRatio = "5";
            }
            if (TextUtils.isEmpty(this.storeStr)) {
                this.storeStr = "0";
            }
            if (TextUtils.isEmpty(this.carriageStr)) {
                this.carriageStr = "0";
            }
            if (TextUtils.isEmpty(this.descStr) || "".equals(this.descStr)) {
                this.descStr = "";
            }
            List<String> urlList = new ArrayList<>();
            List<String> path = new ArrayList<>();
            for (int i = 0; i < this.imagePaths.size(); i++) {
                if (this.imagePaths.get(i).startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    urlList.add(this.imagePaths.get(i));
                } else {
                    path.add(this.imagePaths.get(i));
                }
            }
            final List<String> idList = new ArrayList<>();
            if (urlList.size() != 0) {
                List<ProductBasicBean.AttachPictureListBean> attachPictureList = this.productInfo.getAttachPictureList();
                for (int i2 = 0; i2 < attachPictureList.size(); i2++) {
                    ProductBasicBean.AttachPictureListBean.PicturesBean pictures = attachPictureList.get(i2).getPictures();
                    String picPath = pictures.getPath();
                    for (int j = 0; j < urlList.size(); j++) {
                        if (picPath.equals(urlList.get(j))) {
                            idList.add(pictures.getId() + "");
                        }
                    }
                }
            }
            if (path.size() == 0) {
                String defaultImageId = idList.get(0);
                String attachPictureIds = "";
                for (int i3 = 0; i3 < idList.size(); i3++) {
                    if (i3 + 1 >= idList.size()) {
                        attachPictureIds = attachPictureIds + idList.get(i3);
                    } else {
                        attachPictureIds = attachPictureIds + idList.get(i3) + ",";
                    }
                }
                updateProduct(defaultImageId, attachPictureIds);
                return;
            }
            new UploadUtils().UploadPicturesGetOSS(this, DemoHelper.getInstance().getCurrentUserName(), com.vsf2f.f2f.ui.utils.Constant.PRODUCTS_BUCKET, 2, path, null, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.5
                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                    String attachPictureIds2 = "";
                    if (idList.size() > 0 && picIds != null && picIds.size() > 0) {
                        String defaultImageId2 = (String) idList.get(0);
                        for (int i4 = 0; i4 < idList.size(); i4++) {
                            attachPictureIds2 = attachPictureIds2 + ((String) idList.get(i4)) + ",";
                        }
                        for (int i5 = 0; i5 < picIds.size(); i5++) {
                            if (i5 + 1 >= picIds.size()) {
                                attachPictureIds2 = attachPictureIds2 + picIds.get(i5);
                            } else {
                                attachPictureIds2 = attachPictureIds2 + picIds.get(i5) + ",";
                            }
                        }
                        GoodsEditActivity.this.updateProduct(defaultImageId2, attachPictureIds2);
                    } else if (idList.size() == 0 && picIds != null && picIds.size() > 0) {
                        String defaultImageId3 = picIds.get(0);
                        for (int i6 = 0; i6 < picIds.size(); i6++) {
                            if (i6 + 1 >= picIds.size()) {
                                attachPictureIds2 = attachPictureIds2 + picIds.get(i6);
                            } else {
                                attachPictureIds2 = attachPictureIds2 + picIds.get(i6) + ",";
                            }
                        }
                        GoodsEditActivity.this.updateProduct(defaultImageId3, attachPictureIds2);
                    }
                }

                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onFailed() {
                    MyToast.show(GoodsEditActivity.this.context, (int) R.string.upload_failed);
                    GoodsEditActivity.this.getClient().dialogDismiss();
                }

                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onProgress(long currentSize, long totalSize) {
                    MyLog.e("onProgress", currentSize + "/" + totalSize);
                }
            });
        }
    }

    public void getProductInfo(String guid2) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        getClient().get(R.string.API_APP_BASIC_PRODUCT, ComUtil.getZCApi(this.context, getString(R.string.API_APP_BASIC_PRODUCT, new Object[]{guid2})), params, ProductBasicBean.class, false);
    }

    public void updateProduct(String defaultImageId, String attachPictureIds) {
        AjaxParams params = new AjaxParams();
        params.put("id", this.productBean.getId() + "");
        params.put("guid", this.productBean.getGuid());
        params.put("resRatio", resRatio);
        params.put("isRecom", "1");
        params.put("marketable", "1");
        params.put("goodsCategoryId", goodsCategoryId);
        params.put("goodsTypeId", goodsTypedId);
        params.put("shopMenusId", shopMenusId);
        params.put("goodsName", ComUtil.UTF(this.nameStr));
        params.put("salesPrice", this.priceStr);
        params.put("defaultFreight", this.carriageStr);
        params.put("store", this.storeStr);
        params.put("usableCoupon", this.usableCoupon);
        params.put("explains", ComUtil.UTF(this.descStr));
        params.put("defaultImageId", defaultImageId);
        params.put("attachPictureIds", attachPictureIds);
        getClient().post(R.string.API_UPDATE_PRODUCT, ComUtil.getZCApi(this.context, getString(R.string.API_UPDATE_PRODUCT)), params, ProductBean.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int requestCode = result.getRequestCode();
        int errorCode = result.getErrorCode();
        switch (requestCode) {
            case R.string.API_APP_BASIC_PRODUCT /* 2131296292 */:
                if (errorCode == 0) {
                    Object obj = result.getObj();
                    if (obj != null) {
                        this.productInfo = (ProductBasicBean) obj;
                        updateUI();
                        return;
                    }
                    return;
                }
                MyToast.show(this, "获取数据失败");
                return;
            case R.string.API_UPDATE_PRODUCT /* 2131296447 */:
                if (errorCode == 0) {
                    MyToast.show(this, "编辑成功");
                    Object obj2 = result.getObj();
                    if (obj2 != null) {
                        this.productBean = (ProductBean) obj2;
                        MyLog.e("after-success-bean-->" + this.productBean.toString());
                    }
                    this.postView.setVisibility(8);
                    setResult(-1);
                    finish();
                    return;
                } else if (errorCode == 1) {
                    MyToast.show(this, "编辑失败");
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        InputMethodManager imm;
        if (ev.getAction() == 0) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev) && (imm = (InputMethodManager) getSystemService("input_method")) != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        } else if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        } else {
            return onTouchEvent(ev);
        }
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] leftTop = {0, 0};
        v.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        return event.getX() <= ((float) left) || event.getX() >= ((float) (left + v.getWidth())) || event.getY() <= ((float) top) || event.getY() >= ((float) (top + v.getHeight()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeRealMoney() {
        try {
            double money1 = Integer.parseInt(this.goodMoney) * (1.0d - (Double.parseDouble(resRatio) / 100.0d));
            if (Integer.parseInt(this.usableCoupon) > money1) {
                this.usableCoupon = ((int) money1) + "";
                this.voucherView.setText(this.usableCoupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* loaded from: classes2.dex */
    class EnterDecimalListener implements TextWatcher {
        EnterDecimalListener() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            if (temp.contains(".")) {
                int posDot = temp.indexOf(".");
                if (posDot <= 0) {
                    s.clear();
                } else if ((temp.length() - posDot) - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                }
            }
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        new EaseAlertDialog(this, R.string.exit_enter_, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsEditActivity.6
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    GoodsEditActivity.this.finish();
                }
            }
        }, true).show();
    }
}
