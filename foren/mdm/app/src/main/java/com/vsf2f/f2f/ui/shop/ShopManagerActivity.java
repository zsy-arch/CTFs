package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ImageBean;
import com.vsf2f.f2f.bean.PicBean;
import com.vsf2f.f2f.bean.ShopInfoBean;
import com.vsf2f.f2f.bean.ShopSellerCheckBean;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.ui.view.ImageCycleView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class ShopManagerActivity extends BaseActivity implements CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener {
    private String address;
    private String areaId;
    private CameraUtil camera;
    private ShopInfoBean detailBean;
    private EditText editIntroduce;
    private EditText editPhone;
    private EditText editShopName;
    private ImageCycleView icvViewPager;
    private int imgFlag;
    private ImageView imgHead;
    private ImageView imgHeadIcon;
    private boolean isEdit;
    private ImageView ivPhoneClear;
    private ImageView ivShopNameClear;
    private String lat;
    private String lng;
    private String logo_id;
    private PictureDialog pictureDlg;
    private String seller_id;
    private EditText tvAddress;
    private TextView tvSaleType;
    private TextView txtPrompt;
    private String userName;
    private String sellerTypeIds = "";
    private Map<Integer, ImageBean> picIdMap = new LinkedHashMap();
    private int clickFlag = 0;
    private int maxAdvert = 3;
    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.3
        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void onImageClick(int position, View imageView) {
            if (ShopManagerActivity.this.isEdit) {
                ShopManagerActivity.this.clickFlag = position;
                ShopManagerActivity.this.showPictureDlg(1);
            }
        }

        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void turnToEnd(boolean b) {
        }

        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void displayImage(String imageUrl, ImageView imageView) {
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_shop_manager;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.shop_manager, 0);
        setOnClickListener(R.id.shop_manager_kvHead);
        setOnClickListener(R.id.shop_manager_kvArea);
        setOnClickListener(R.id.shop_manager_kvSaleType);
        this.tvSaleType = (TextView) getView(R.id.shop_manager_tvSaleType);
        this.txtPrompt = (TextView) getViewAndClick(R.id.shop_manager_txtPrompt);
        this.tvAddress = (EditText) getView(R.id.shop_manager_tvAddress);
        this.ivPhoneClear = (ImageView) getViewAndClick(R.id.shop_manager_ivPhoneClear);
        this.ivShopNameClear = (ImageView) getViewAndClick(R.id.shop_manager_ivShopNameClear);
        this.imgHead = (ImageView) getView(R.id.shop_manager_imgHead);
        this.imgHeadIcon = (ImageView) getView(R.id.shop_manager_imgHeadIcon);
        this.editPhone = (EditText) getView(R.id.shop_manager_editPhone);
        this.editShopName = (EditText) getView(R.id.shop_manager_editShopName);
        this.editIntroduce = (EditText) getView(R.id.shop_manager_editIntroduce);
        this.icvViewPager = (ImageCycleView) getViewAndClick(R.id.shop_manager_icvViewPager);
        this.icvViewPager.setScaleMatch(true);
        this.icvViewPager.setStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        this.icvViewPager.getLayoutParams().width = width;
        this.icvViewPager.getLayoutParams().height = (width / 16) * 9;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (getBundle() == null || !getBundle().getBoolean(Constant.FLAG)) {
            setEdit(true);
            this.editShopName.setEnabled(true);
            this.editShopName.setHint(R.string.shop_name_prompt);
            getViewAndClick(R.id.shop_manager_ivShopName).setVisibility(8);
        } else {
            checkOpen();
            requestData();
        }
        this.editShopName.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ShopManagerActivity.this.isEdit || charSequence.length() <= 0) {
                    ShopManagerActivity.this.ivShopNameClear.setVisibility(8);
                } else {
                    ShopManagerActivity.this.ivShopNameClear.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editPhone.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ShopManagerActivity.this.isEdit || charSequence.length() <= 0) {
                    ShopManagerActivity.this.ivPhoneClear.setVisibility(8);
                } else {
                    ShopManagerActivity.this.ivPhoneClear.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initPicList() {
        if (this.detailBean.getAdvertWapId() != null) {
            String[] arr = this.detailBean.getAdvertWapId().split(",");
            for (int i = 0; i < arr.length; i++) {
                this.picIdMap.put(Integer.valueOf(i), new ImageBean(arr[i], ""));
            }
        }
        if (this.detailBean.getAdvertWapList() != null) {
            List<PicBean> picList = this.detailBean.getAdvertWapList();
            for (int i2 = 0; i2 < picList.size(); i2++) {
                if (this.picIdMap.get(Integer.valueOf(i2)) != null) {
                    this.picIdMap.get(Integer.valueOf(i2)).setRemark(picList.get(i2).getPath());
                }
            }
        }
    }

    public String getUserName() {
        if (TextUtils.isEmpty(this.userName)) {
            this.userName = DemoHelper.getInstance().getCurrentUserName();
        }
        return this.userName;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_LOAD_SHOP_INFORMATION, ComUtil.getZCApi(this.context, getString(R.string.API_LOAD_SHOP_INFORMATION)), ShopInfoBean.class);
    }

    public void checkOpen() {
        getClient().get(R.string.API_SELLER_CHECK, ComUtil.getZCApi(this.context, getString(R.string.API_SELLER_CHECK) + "?categoryId=1"), ShopSellerCheckBean.class);
    }

    public void requestCommit() {
        String banner_id = getIdListStr();
        if (TextUtils.isEmpty(banner_id)) {
            MyToast.show(this.context, getString(R.string.toast_least_select_one));
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put("storeName", this.editShopName.getText().toString().trim());
        params.put("advertWapId", banner_id);
        params.put("pictureId", this.logo_id);
        params.put("phone", this.editPhone.getText().toString());
        params.put("sellGoodsType", this.sellerTypeIds);
        params.put("description", ComUtil.UTF(this.editIntroduce.getText().toString()));
        params.put("address", this.address);
        params.put("areaId", this.areaId);
        params.put("latitude", this.lat);
        params.put("longitude", this.lng);
        if (this.detailBean == null) {
            getClient().post(R.string.API_OPEN_SHOP_ADD, ComUtil.getZCApi(this.context, getString(R.string.API_OPEN_SHOP_ADD)), params);
            return;
        }
        params.put("id", this.seller_id);
        getClient().post(R.string.API_OPEN_SHOP_UPDATE, ComUtil.getZCApi(this.context, getString(R.string.API_OPEN_SHOP_UPDATE)), params);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_LOAD_SHOP_INFORMATION /* 2131296370 */:
                this.detailBean = (ShopInfoBean) result.getObj();
                if (this.detailBean != null) {
                    this.logo_id = this.detailBean.getPictureId() != null ? this.detailBean.getPictureId() : "";
                    this.sellerTypeIds = this.detailBean.getSellGoodsType() != null ? this.detailBean.getSellGoodsType() : "";
                    this.areaId = this.detailBean.getAreaId() != null ? this.detailBean.getAreaId() : "";
                    this.seller_id = this.detailBean.getId() != null ? this.detailBean.getId() : "";
                    initPicList();
                    setEdit(false);
                    updateUI();
                    return;
                }
                return;
            case R.string.API_OPEN_SHOP_ADD /* 2131296382 */:
                setEdit(false);
                MyToast.show(this, (int) R.string.toast_shop_open);
                DemoHelper.getInstance().setOpenStore(1);
                startAct(ShopMainActivity.class);
                finish();
                return;
            case R.string.API_OPEN_SHOP_UPDATE /* 2131296383 */:
                setEdit(false);
                MyToast.show(this, getString(R.string.toast_change_success));
                setResult(-1);
                finish();
                return;
            case R.string.API_SELLER_CHECK /* 2131296400 */:
                ShopSellerCheckBean sellerCheckBean = (ShopSellerCheckBean) result.getObj();
                if (sellerCheckBean == null) {
                    return;
                }
                if (sellerCheckBean.getMaxAdvert() != null) {
                    this.maxAdvert = Integer.parseInt(sellerCheckBean.getMaxAdvert());
                    return;
                } else {
                    this.maxAdvert = 3;
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        updateViewpager();
        if (!(this.detailBean.getLogo() == null || this.detailBean.getLogo().getSpath() == null)) {
            ComUtil.displayImage(this.context, this.imgHead, this.detailBean.getLogo().getSpath());
        }
        if (this.detailBean.getStoreName() != null) {
            this.editShopName.setText(this.detailBean.getStoreName());
        }
        this.lat = this.detailBean.getLatitude();
        this.lng = this.detailBean.getLongitude();
        this.address = this.detailBean.getAddress();
        this.tvAddress.setText(this.address);
        this.editPhone.setText(this.detailBean.getPhone());
        this.tvSaleType.setText(this.detailBean.getSellGoodsTypeName());
        if (this.detailBean.getDescription() != null) {
            this.editIntroduce.setText(this.detailBean.getDescription());
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        if (!this.isEdit) {
            MyToast.show(this.context, (int) R.string.editor_click_hint);
            return;
        }
        switch (view.getId()) {
            case R.id.shop_manager_icvViewPager /* 2131755280 */:
                if (this.isEdit) {
                    showPictureDlg(1);
                    return;
                }
                return;
            case R.id.shop_manager_kvHead /* 2131755684 */:
                showPictureDlg(0);
                return;
            case R.id.shop_manager_ivShopNameClear /* 2131755688 */:
                this.editShopName.setText("");
                return;
            case R.id.shop_manager_ivPhoneClear /* 2131755691 */:
                this.editPhone.setText("");
                return;
            case R.id.shop_manager_kvArea /* 2131755692 */:
                startActForResult(ChoiceLocationActivity.class, 111);
                return;
            case R.id.shop_manager_kvSaleType /* 2131755694 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG, this.sellerTypeIds);
                startActForResult(ShopBusinessActivity.class, bundle, 999);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPictureDlg(int flag) {
        this.imgFlag = flag;
        if (this.camera == null) {
            this.camera = new CameraUtil(this, this);
        }
        if (this.pictureDlg == null) {
            this.pictureDlg = new PictureDialog(this.context);
            this.pictureDlg.init(this);
        }
        this.pictureDlg.show();
    }

    private String getIdListStr() {
        List<String> id_List = getIdList(true);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < id_List.size(); i++) {
            sb.append(id_List.get(i));
            if (i < id_List.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private List<String> getIdList(boolean trim) {
        List<String> list = new ArrayList<>();
        for (ImageBean picIdBean : this.picIdMap.values()) {
            if (!trim || !TextUtils.isEmpty(picIdBean.getId())) {
                list.add(picIdBean.getId());
            }
        }
        return list;
    }

    public List<String> getImgList() {
        List<String> imgCache = new ArrayList<>();
        for (ImageBean picIdBean : this.picIdMap.values()) {
            String path = picIdBean.getRemark();
            if (!TextUtils.isEmpty(path)) {
                imgCache.add(path);
            }
        }
        return imgCache;
    }

    public Map<Integer, String> getImgMap() {
        Map<Integer, String> imgCache = new HashMap<>();
        for (int i = 0; i < this.picIdMap.size(); i++) {
            String path = this.picIdMap.get(Integer.valueOf(i)).getRemark();
            if (!TextUtils.isEmpty(path) && !path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                imgCache.put(Integer.valueOf(i), path);
            }
        }
        return imgCache;
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        if (this.imgFlag == 0) {
            this.camera.cropImageUri(1, 1, 256);
        } else {
            this.camera.cropImageUri(16, 9, 256);
        }
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        if (this.imgFlag == 0) {
            this.camera.cropImageUri(path, 1, 1, 256);
        } else {
            this.camera.cropImageUri(path, 16, 9, 256);
        }
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String path) {
        MyLog.e("onCameraCutSuccess: " + path);
        File file = new File(path);
        if (this.imgFlag == 0) {
            Glide.with(this.context).load(file).into(this.imgHead);
            UploadLogo(path);
            return;
        }
        if (getImgList().size() >= this.maxAdvert) {
            this.picIdMap.get(Integer.valueOf(this.clickFlag)).setRemark(path);
        } else {
            this.picIdMap.put(Integer.valueOf(this.picIdMap.size()), new ImageBean("", path));
        }
        int lastCount = this.icvViewPager.getmImageCount();
        updateViewpager();
        if (lastCount < this.maxAdvert) {
            this.icvViewPager.setRollPage();
        }
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgPhotoClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
    }

    public void setEdit(boolean isEdit) {
        int i = R.string.please_select;
        int i2 = R.string.Not_Set;
        this.isEdit = isEdit;
        if (isEdit) {
            this.icvViewPager.setStop();
            this.ivPhoneClear.setVisibility(0);
        } else {
            this.ivPhoneClear.setVisibility(8);
        }
        setHeaderRightTxt(isEdit ? R.string.save : R.string.edit);
        this.txtPrompt.setVisibility(isEdit ? 0 : 8);
        this.tvSaleType.setEnabled(isEdit);
        this.tvSaleType.setHint(isEdit ? R.string.shop_detail_type : R.string.please_select);
        this.tvAddress.setEnabled(isEdit);
        EditText editText = this.tvAddress;
        if (isEdit) {
            i = R.string.shop_detail_point;
        }
        editText.setHint(i);
        this.editPhone.setEnabled(isEdit);
        this.editPhone.setHint(isEdit ? R.string.shop_phone : R.string.Not_Set);
        this.editIntroduce.setEnabled(isEdit);
        EditText editText2 = this.editIntroduce;
        if (isEdit) {
            i2 = R.string.shop_enter_introduce;
        }
        editText2.setHint(i2);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        if (!this.isEdit) {
            setEdit(true);
        } else if (TextUtils.isEmpty(this.editShopName.getText())) {
            MyToast.show(this.context, (int) R.string.shop_name_no_set);
        } else if (TextUtils.isEmpty(this.logo_id)) {
            MyToast.show(this.context, getString(R.string.toast_uploading_logo));
        } else if (TextUtils.isEmpty(this.editPhone.getText())) {
            MyToast.show(this.context, getString(R.string.toast_enter_phone_number));
        } else {
            this.address = this.tvAddress.getText().toString();
            if (TextUtils.isEmpty(this.address)) {
                MyToast.show(this.context, getString(R.string.shop_select_area_prompt));
            } else if (TextUtils.isEmpty(this.sellerTypeIds)) {
                MyToast.show(this.context, getString(R.string.toast_select_main_sale));
            } else {
                setResult(-1);
                UploadAdvert();
            }
        }
    }

    private void UploadLogo(String path) {
        if (this.imgFlag == 0) {
            List<String> paths = new ArrayList<>();
            paths.add(path);
            new UploadUtils().UploadPicturesGetOSS(this, getUserName(), com.vsf2f.f2f.ui.utils.Constant.USER_BUCKET, 12, paths, null, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.4
                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onSuccess(List<Map<String, String>> list, List<String> picIds) {
                    ShopManagerActivity.this.logo_id = picIds.get(0);
                    ShopManagerActivity.this.imgHeadIcon.post(new Runnable() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ShopManagerActivity.this.imgHeadIcon.setVisibility(0);
                        }
                    });
                }

                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onFailed() {
                    MyToast.show(ShopManagerActivity.this, (int) R.string.upload_failed);
                    ShopManagerActivity.this.imgHead.setImageResource(R.mipmap.def_head);
                }

                @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
                public void onProgress(long currentSize, long totalSize) {
                    MyLog.e("onProgress", currentSize + "/" + totalSize);
                }
            });
        }
    }

    private void UploadAdvert() {
        final Map<Integer, String> imgMap = getImgMap();
        if (imgMap.isEmpty()) {
            requestCommit();
            return;
        }
        List<String> valueList = new ArrayList<>(imgMap.values());
        getClient().showDialogNow(R.string.uploading);
        new UploadUtils().UploadPicturesGetOSS(this, getUserName(), com.vsf2f.f2f.ui.utils.Constant.ADVERT_BUCKET, 9, valueList, null, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.5
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds != null) {
                    int i = 0;
                    for (Integer key : imgMap.keySet()) {
                        ((ImageBean) ShopManagerActivity.this.picIdMap.get(key)).setId(picIds.get(i));
                        i++;
                    }
                }
                ShopManagerActivity.this.requestCommit();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ShopManagerActivity.this.getClient().dialogDismiss();
                MyToast.show(ShopManagerActivity.this, (int) R.string.upload_failed);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    public void updateViewpager() {
        this.icvViewPager.setImageResources(getImgList(), this.mAdCycleViewListener);
        this.icvViewPager.setCurrentItem(this.clickFlag);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.isEdit) {
            super.onBackPressed();
            this.icvViewPager.setStop();
            return;
        }
        hideSoftKeyboard();
        new EaseAlertDialog(this, R.string.exit_enter_, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.ShopManagerActivity.6
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    ShopManagerActivity.this.icvViewPager.setStop();
                    ShopManagerActivity.this.finish();
                }
            }
        }, true).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PoiItem poiItem;
        super.onActivityResult(requestCode, resultCode, data);
        if (this.camera != null) {
            this.camera.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == -1) {
            switch (requestCode) {
                case 111:
                    break;
                default:
                    return;
                case 999:
                    String detail = null;
                    if (!TextUtils.isEmpty(data.getStringExtra(Constant.FLAG2))) {
                        this.sellerTypeIds = data.getStringExtra(Constant.FLAG2);
                    }
                    if (!TextUtils.isEmpty(data.getStringExtra(Constant.FLAG))) {
                        detail = data.getStringExtra(Constant.FLAG);
                    }
                    this.tvSaleType.setText(detail);
                    break;
            }
            if (!(data.getBundleExtra("bund") == null || (poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key")) == null)) {
                LatLonPoint latLng = poiItem.getLatLonPoint();
                this.lat = latLng.getLatitude() + "";
                this.lng = latLng.getLongitude() + "";
                this.areaId = poiItem.getAdCode();
                this.address = (poiItem.getCityName() + poiItem.getAdName() + poiItem.getBusinessArea()) + poiItem.getSnippet();
            }
            this.tvAddress.setText(this.address);
        }
    }
}
