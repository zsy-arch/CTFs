package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.MyGridView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.litepal.crud.DataSupport;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PublishGridAdapter;
import com.vsf2f.f2f.bean.DBCircleDraftBean;
import com.vsf2f.f2f.bean.ProductBean;
import com.vsf2f.f2f.bean.ProductCategory;
import com.vsf2f.f2f.ui.circle.CirclesAddActivity;
import com.vsf2f.f2f.ui.dialog.SaveDraftDialog;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.ImageUtil;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.ui.view.PointLengthFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GoodsPublishActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_DESC = 60;
    private static final int REQUEST_GOODS_CLASSIFY = 40;
    private static final int REQUEST_GOODS_TYPE = 50;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private static final int REQUEST_SHOP_MENU = 30;
    private static String resRatio = "5";
    private PublishGridAdapter adapter;
    private String carriageStr;
    private EditText carriageView;
    private String descStr;
    private EditText descView;
    private int discount;
    private Spinner forSaleView;
    private String goodMoney;
    private String goodsCategoryId;
    private String goodsTypeGuid;
    private String goodsTypedId;
    private KeyValueView goods_type;
    private MyGridView gridView;
    private boolean isChange;
    private boolean isPost;
    private boolean isPublish;
    private LinearLayout lly_spec;
    private String nameStr;
    private EditText nameView;
    private KeyValueView postShopView;
    private String priceStr;
    private EditText priceView;
    private TextView publishView;
    private String selectClassifyName;
    private String selectMenuName;
    private String shopMenusId;
    private String storeStr;
    private EditText storeView;
    private String username;
    private EditText voucherView;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String usableCoupon = "0";

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_goods_publish;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.goods_publish, 0);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            finish();
        }
        this.lly_spec = (LinearLayout) getView(R.id.lly_spec);
        this.nameView = (EditText) getView(R.id.et_goods_name);
        this.descView = (EditText) getView(R.id.et_goods_desc);
        this.storeView = (EditText) getView(R.id.et_store_num);
        this.priceView = (EditText) getView(R.id.et_goods_price);
        this.priceView.setFilters(new InputFilter[]{new PointLengthFilter(7)});
        this.gridView = (MyGridView) getView(R.id.gridView_publish_goods);
        this.carriageView = (EditText) getView(R.id.et_carriage_publish);
        this.postShopView = (KeyValueView) getViewAndClick(R.id.kv_post_shop);
        this.goods_type = (KeyValueView) getViewAndClick(R.id.kv_select_goods_type);
        this.voucherView = (EditText) getView(R.id.et_voucher);
        this.publishView = (TextView) getViewAndClick(R.id.btn_publish_goods2);
        this.forSaleView = (Spinner) getView(R.id.sp_for_sale);
        this.forSaleView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = GoodsPublishActivity.this.getResources().getStringArray(R.array.for_sale_array)[position];
                GoodsPublishActivity.this.discount = position;
                if (str.contains("%")) {
                    String unused = GoodsPublishActivity.resRatio = str.substring(0, str.indexOf("%"));
                    GoodsPublishActivity.this.changeRealMoney();
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.adapter = new PublishGridAdapter(this, this.imagePaths);
        this.username = DemoHelper.getInstance().getCurrentUserName();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.goodsCategoryId = null;
        this.goodsTypedId = null;
        this.shopMenusId = null;
        this.goodsTypeGuid = null;
        queryPicResult();
        this.isPublish = false;
        this.priceView.addTextChangedListener(new EnterDecimalListener());
        this.carriageView.addTextChangedListener(new EnterDecimalListener());
        this.gridView.setAdapter((ListAdapter) this.adapter);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (GoodsPublishActivity.this.imagePaths.size() >= 9 || i != GoodsPublishActivity.this.imagePaths.size()) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(GoodsPublishActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(GoodsPublishActivity.this.imagePaths);
                    GoodsPublishActivity.this.startActivityForResult(intent, 20);
                    return;
                }
                PhotoPickerIntent intent2 = new PhotoPickerIntent(GoodsPublishActivity.this.context);
                intent2.setSelectModel(SelectModel.MULTI);
                intent2.setShowCamera(true);
                intent2.setMaxTotal(9);
                intent2.setSelectedPaths(GoodsPublishActivity.this.imagePaths);
                GoodsPublishActivity.this.startActivityForResult(intent2, 10);
            }
        });
        this.voucherView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.3
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    GoodsPublishActivity.this.usableCoupon = s.toString();
                } else {
                    GoodsPublishActivity.this.usableCoupon = "0";
                }
                GoodsPublishActivity.this.changeRealMoney();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.priceView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.4
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    GoodsPublishActivity.this.goodMoney = s.toString();
                } else {
                    GoodsPublishActivity.this.goodMoney = "0";
                }
                GoodsPublishActivity.this.changeRealMoney();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void queryPicResult() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && beans.get(i) != null && "4".equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                DBCircleDraftBean bean = beans.get(i);
                this.nameView.setText(bean.getCommodity_name());
                this.storeView.setText(bean.getCommodity_inventory());
                this.carriageView.setText(bean.getCommodity_freight());
                this.goods_type.getTxtKey().setText(bean.getCommodity_classify());
                this.postShopView.getTxtKey().setText(bean.getCommodity_menu());
                this.selectClassifyName = bean.getCommodity_classify();
                this.selectMenuName = bean.getCommodity_menu();
                this.goodsTypedId = bean.getCommodity_goodsTypedId();
                this.goodsCategoryId = bean.getCommodity_goodsCategoryId();
                this.goodsTypeGuid = bean.getCommodity_goodsTypeGuid();
                this.shopMenusId = bean.getCommodity_shopMenusId();
                this.forSaleView.setSelection(bean.getCommodity_discount());
                String str = getResources().getStringArray(R.array.for_sale_array)[bean.getCommodity_discount()];
                this.discount = bean.getCommodity_discount();
                this.goodMoney = bean.getCommodity_price();
                this.priceView.setText(this.goodMoney);
                if (!TextUtils.isEmpty(bean.getCommodity_scroll())) {
                    this.usableCoupon = bean.getCommodity_scroll();
                    this.voucherView.setText(this.usableCoupon);
                }
                if (str.contains("%")) {
                    int y = str.indexOf("%");
                    Log.e("5200", str);
                    resRatio = str.substring(0, y);
                }
                this.descView.setText(bean.getCommodity_explain());
                picResult(bean);
                changeRealMoney();
            }
        }
    }

    private void picResult(DBCircleDraftBean bean) {
        if (!TextUtils.isEmpty(bean.getImg_list())) {
            this.imagePaths.addAll(Arrays.asList(bean.getImg_list().split(",")));
        }
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                case 30:
                    this.selectMenuName = data.getStringExtra("selectName");
                    this.shopMenusId = data.getStringExtra("shopMenusId");
                    this.postShopView.getTxtKey().setText(this.selectMenuName);
                    return;
                case 40:
                    this.selectClassifyName = data.getStringExtra("name");
                    this.goods_type.getTxtKey().setText(this.selectClassifyName);
                    ProductCategory product = (ProductCategory) data.getParcelableExtra(Constant.PRODUCT);
                    this.goodsTypedId = Integer.toString(product.getTypeId());
                    this.goodsCategoryId = Integer.toString(product.getId());
                    this.goodsTypeGuid = product.getTypeGuid();
                    return;
                default:
                    return;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (this.imagePaths == null) {
            this.imagePaths = new ArrayList<>();
        }
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.kv_select_goods_type /* 2131755337 */:
                if (!this.isPublish) {
                    startActForResult(GoodsClassifyActivity.class, 40);
                    return;
                }
                return;
            case R.id.kv_add_detail /* 2131755342 */:
                Intent descIntent = new Intent(this, AddDescriptionActivity.class);
                descIntent.putExtra("guid", 111);
                startActivityForResult(descIntent, 60);
                return;
            case R.id.kv_post_shop /* 2131755345 */:
                if (!this.isPublish) {
                    startActForResult(ClassifyActivity.class, 30);
                    return;
                }
                return;
            case R.id.btn_publish_goods2 /* 2131755347 */:
                if (!this.isPost) {
                    this.isPost = true;
                    this.isPost = dealWithContent();
                    return;
                }
                return;
            case R.id.add_spec /* 2131755350 */:
                final View specview = LayoutInflater.from(this.context).inflate(R.layout.layout_good_pubulish_spec, (ViewGroup) null);
                specview.findViewById(R.id.spec_llyDel).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        GoodsPublishActivity.this.lly_spec.removeView(specview);
                    }
                });
                this.lly_spec.addView(specview);
                return;
            default:
                return;
        }
    }

    private boolean dealWithContent() {
        this.nameStr = this.nameView.getText().toString().trim();
        this.priceStr = this.priceView.getText().toString().trim();
        this.storeStr = this.storeView.getText().toString().trim();
        this.carriageStr = this.carriageView.getText().toString().trim();
        this.descStr = this.descView.getText().toString().trim();
        if (this.imagePaths.size() == 0) {
            MyToast.show(this.context, "请添加商品图片");
            return false;
        } else if (TextUtils.isEmpty(this.nameStr) || "".equals(this.nameStr)) {
            MyToast.show(this.context, "请填写商品名称");
            return false;
        } else if (TextUtils.isEmpty(this.priceStr)) {
            MyToast.show(this.context, "请填写商品价格");
            return false;
        } else if (TextUtils.isEmpty(this.goodsCategoryId) || TextUtils.isEmpty(this.goodsTypedId)) {
            MyToast.show(this.context, "请选择商品分类");
            return false;
        } else if (TextUtils.isEmpty(this.shopMenusId)) {
            MyToast.show(this.context, "请选择发布分类");
            return false;
        } else {
            getClient().showDialogNow(R.string.uploading);
            if (TextUtils.isEmpty(resRatio)) {
                resRatio = "5";
            }
            if (TextUtils.isEmpty(this.storeStr)) {
                this.storeStr = "0";
            }
            if (TextUtils.isEmpty(this.carriageStr)) {
                this.carriageStr = "0";
            }
            if (TextUtils.isEmpty(this.descStr)) {
                this.descStr = "";
            }
            getClient().showDialogNow(R.string.toast_uploading);
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    List<String> allPaths = new ArrayList<>();
                    long ids = System.currentTimeMillis();
                    for (int i = 0; i < GoodsPublishActivity.this.imagePaths.size(); i++) {
                        allPaths.add(ImageUtil.compressImage((String) GoodsPublishActivity.this.imagePaths.get(i), ids + "_" + i, 1024));
                    }
                    GoodsPublishActivity.this.dealEditData(allPaths);
                }
            });
            return true;
        }
    }

    public void dealEditData(List<String> imagePaths) {
        JSONArray imgs = new JSONArray();
        JSONObject img0 = new JSONObject();
        Map<String, String> maps = new HashMap<>();
        for (int i = 0; i < imagePaths.size(); i++) {
            try {
                String imagePath = imagePaths.get(i);
                String objectKey = ComUtil.getObjectKey(this.username, imagePath);
                maps.put(objectKey, imagePath);
                JSONObject img = new JSONObject();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imagePath, options);
                img.put("path", objectKey);
                img.put("width", options.outWidth);
                img.put(MessageEncoder.ATTR_IMG_HEIGHT, options.outHeight);
                img.put("size", new File(imagePath).length());
                imgs.put(img);
                if (i == 0) {
                    img0 = img;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        postProduct(img0, imgs);
        new UploadUtils().UploadCirclePic(this, this.username, Constant.PRODUCTS_BUCKET, "", maps, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.7
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                MyToast.show(GoodsPublishActivity.this.context, (int) R.string.upload_success);
                GoodsPublishActivity.this.finish();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyToast.show(GoodsPublishActivity.this.context, GoodsPublishActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    public void postProduct(JSONObject defaultImageId, JSONArray attachPictureIds) {
        AjaxParams params = new AjaxParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("goodsPicture", defaultImageId);
            jsonObject.put("picturesList", attachPictureIds);
            jsonObject.put("resRatio", resRatio);
            jsonObject.put("isRecom", "1");
            jsonObject.put("marketable", "1");
            jsonObject.put("goodsCategoryId", this.goodsCategoryId);
            jsonObject.put("goodsTypeId", this.goodsTypedId);
            jsonObject.put("shopMenusId", this.shopMenusId);
            jsonObject.put("goodsName", ComUtil.UTF(this.nameStr));
            jsonObject.put("salesPrice", this.priceStr);
            jsonObject.put("defaultFreight", this.carriageStr);
            jsonObject.put("store", this.storeStr);
            jsonObject.put("usableCoupon", this.usableCoupon);
            jsonObject.put("explains", ComUtil.UTF(this.descStr));
            getClient().post(R.string.API_SAVE_PRODUCT, ComUtil.getZCApi(this.context, getString(R.string.API_SAVE_PRODUCT)), jsonObject.toString(), params, ProductBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SAVE_PRODUCT /* 2131296399 */:
                queryAllResult();
                ProductBean productBean = (ProductBean) result.getObj();
                this.publishView.setVisibility(8);
                this.isPublish = true;
                setResult(-1);
                Bundle bundle = new Bundle();
                bundle.putString("goodsId", productBean.getId() + "");
                bundle.putString("goodsGuid", productBean.getGuid());
                bundle.putString("productName", productBean.getGoodsName());
                bundle.putString("resRatio", resRatio);
                bundle.putParcelable("data", productBean);
                startAct(GoodPublishScucessActivity.class, bundle);
                getApp().removeFinish(CirclesAddActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        getClient().dialogDismiss();
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

    /* loaded from: classes2.dex */
    class EnterDecimalListener implements TextWatcher {
        EnterDecimalListener() {
            GoodsPublishActivity.this = this$0;
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

    public void changeRealMoney() {
        try {
            double money1 = Double.parseDouble(this.goodMoney) * (1.0d - (Double.parseDouble(resRatio) / 100.0d));
            if (TextUtils.isEmpty(this.usableCoupon)) {
                this.usableCoupon = "0";
            }
            if (Integer.parseInt(this.usableCoupon) > money1) {
                this.usableCoupon = ((int) money1) + "";
                this.voucherView.setText(this.usableCoupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveProduct() {
        DBCircleDraftBean bean = new DBCircleDraftBean();
        bean.setUsername(this.username);
        bean.setImg_list(savePic(this.imagePaths));
        bean.setCommodity_name(this.nameView.getText().toString());
        bean.setCommodity_price(this.goodMoney);
        bean.setCommodity_inventory(this.storeView.getText().toString());
        bean.setCommodity_freight(this.carriageView.getText().toString());
        bean.setCommodity_classify(this.selectClassifyName);
        bean.setCommodity_menu(this.selectMenuName);
        bean.setCommodity_goodsCategoryId(this.goodsCategoryId);
        bean.setCommodity_goodsTypedId(this.goodsTypedId);
        bean.setCommodity_goodsTypeGuid(this.goodsTypeGuid);
        bean.setCommodity_shopMenusId(this.shopMenusId);
        bean.setCommodity_discount(this.discount);
        bean.setCommodity_explain(this.descView.getText().toString());
        bean.setCommodity_scroll(this.voucherView.getText().toString());
        bean.setType("4");
        queryAllResult();
        Log.e("5200", "查询到产品图片保存结果" + bean.save());
    }

    public void queryAllResult() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && "4".equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                beans.get(i).delete();
            }
        }
    }

    private String savePic(ArrayList<String> imagePaths) {
        String picStr = "";
        for (int i = 0; i < imagePaths.size(); i++) {
            if (i != 0) {
                picStr = picStr + ",";
            }
            picStr = picStr + imagePaths.get(i);
        }
        return picStr;
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.isPublish) {
            hideSoftKeyboard();
            new SaveDraftDialog(this.context, new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsPublishActivity.8
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        GoodsPublishActivity.this.saveProduct();
                    } else {
                        GoodsPublishActivity.this.queryAllResult();
                    }
                    GoodsPublishActivity.this.finish();
                }
            }).show();
            return;
        }
        finish();
    }
}
