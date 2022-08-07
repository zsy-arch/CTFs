package com.vsf2f.f2f.ui.needs.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.EaseVoiceRecorderView;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.MyGridView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicAddBtnAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.dialog.SelectDialog;
import com.vsf2f.f2f.ui.needs.NeedsTypeChoiceActivity;
import com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ServicePublishActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_CODE_ADDRESS = 1010;
    private static final int REQUEST_CODE_TYPE = 1012;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String address;
    private TextView describe_length;
    private String descripe;
    private EditText ed_address2;
    private EditText ed_stock;
    private EditText et_describe;
    private EditText et_price;
    private EditText et_tel;
    private EditText et_telor;
    private EditText et_title;
    private LinearLayout exist_voice;
    private FrameLayout fly_lastPage;
    private boolean isNextPage;
    private ImageView iv_voice;
    private KeyValueView kv_distance;
    private KeyValueView kv_mode;
    private KeyValueView kv_state;
    private KeyValueView kv_type;
    private KeyValueView kv_unit;
    private LinearLayout lly_nextPage;
    private MyGridView mgv_pic;
    private MyLocation myLocation;
    private boolean notFirstNext;
    private PicAddBtnAdapter picAdapter;
    private String price;
    private int serviceModeId;
    private String tel;
    private String telor;
    private String title;
    private TextView tv_address;
    private MusicPlayer voicePlayer;
    protected EaseVoiceRecorderView voiceRecorderView;
    private String area = "";
    private String typeId = "";
    private String stockStr = "";
    private String voiceId = "";
    private String voiceDuration = "";
    private String voiceFilePath = "";
    private ArrayList<String> imagePaths = new ArrayList<>();
    private int serviceAreaId = 1;
    private int serviceStateId = 1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_service_publish;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.publish_service, 0);
        this.voiceRecorderView = (EaseVoiceRecorderView) getView(R.id.voice_recorder);
        this.describe_length = (TextView) getView(R.id.et_describe_length);
        this.et_describe = (EditText) getView(R.id.et_describe);
        this.fly_lastPage = (FrameLayout) getView(R.id.fly_lastPage);
        this.lly_nextPage = (LinearLayout) getView(R.id.lly_nextPage);
        this.voicePlayer = (MusicPlayer) getViewAndClick(R.id.voicePlayer);
        this.mgv_pic = (MyGridView) getView(R.id.mgv_pic);
        this.et_title = (EditText) getView(R.id.et_title);
        this.et_tel = (EditText) getView(R.id.et_tel);
        this.et_telor = (EditText) getView(R.id.et_telor);
        this.et_price = (EditText) getView(R.id.et_price);
        this.ed_stock = (EditText) getView(R.id.ed_stock);
        this.et_price.setFilters(new InputFilter[]{new PointLengthFilter(7)});
        this.exist_voice = (LinearLayout) getViewAndClick(R.id.exist_voice);
        this.tv_address = (TextView) getViewAndClick(R.id.tv_address);
        this.ed_address2 = (EditText) getView(R.id.ed_address2);
        this.kv_mode = (KeyValueView) getViewAndClick(R.id.kv_mode);
        this.kv_unit = (KeyValueView) getViewAndClick(R.id.kv_unit);
        this.kv_type = (KeyValueView) getViewAndClick(R.id.kv_type);
        this.kv_state = (KeyValueView) getViewAndClick(R.id.kv_state);
        this.kv_distance = (KeyValueView) getViewAndClick(R.id.kv_distance);
        setOnClickListener(R.id.clear_voice);
        setOnClickListener(R.id.btn_commit);
        setOnClickListener(R.id.btn_next);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (UserShared.getInstance().getIsVerifyState(this.context)) {
            if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            ServicePublishActivity.this.startActForResult(BindPhoneActivity.class, 0);
                        }
                        ServicePublishActivity.this.finish();
                    }
                }, true).show();
                return;
            }
            this.myLocation = MyApplication.getMyLocation();
            if (this.myLocation != null) {
                this.address = this.myLocation.getAddress();
                if (TextUtils.isEmpty(this.address)) {
                    this.tv_address.setText("点击定位选择地址");
                    this.ed_address2.setText("点击定位选择地址");
                } else {
                    this.tv_address.setText(this.address);
                    this.ed_address2.setText(this.address);
                }
            } else {
                this.myLocation = new MyLocation();
            }
            this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(ServicePublishActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(ServicePublishActivity.this.imagePaths);
                    ServicePublishActivity.this.startActivityForResult(intent, 20);
                }
            });
            this.et_describe.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.3
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ServicePublishActivity.this.describe_length.setText(s.length() + "");
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }
            });
            this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths, R.dimen.padding_big);
            this.mgv_pic.setAdapter((ListAdapter) this.picAdapter);
            this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.4
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (ServicePublishActivity.this.imagePaths.size() >= 9 || i != ServicePublishActivity.this.imagePaths.size()) {
                        PhotoPreviewIntent intent = new PhotoPreviewIntent(ServicePublishActivity.this.context);
                        intent.setCurrentItem(i);
                        intent.setPhotoPaths(ServicePublishActivity.this.imagePaths);
                        ServicePublishActivity.this.startActivityForResult(intent, 20);
                        return;
                    }
                    PhotoPickerIntent intent2 = new PhotoPickerIntent(ServicePublishActivity.this.context);
                    intent2.setSelectModel(SelectModel.MULTI);
                    intent2.setShowCamera(true);
                    intent2.setMaxTotal(9);
                    intent2.setSelectedPaths(ServicePublishActivity.this.imagePaths);
                    ServicePublishActivity.this.startActivityForResult(intent2, 10);
                }
            });
            this.iv_voice = (ImageView) getView(R.id.iv_voice);
            this.iv_voice.setOnTouchListener(new View.OnTouchListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.5
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    return ServicePublishActivity.this.voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.5.1
                        @Override // com.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback
                        public void onVoiceRecordComplete(String voiceFilePath2, int voiceTimeLength) {
                            ServicePublishActivity.this.voiceDuration = voiceTimeLength + "";
                            ServicePublishActivity.this.voicePlayer.setDuration(ServicePublishActivity.this.voiceDuration);
                            ServicePublishActivity.this.voiceFilePath = voiceFilePath2;
                            ServicePublishActivity.this.upLoadVoice(ServicePublishActivity.this.voiceFilePath);
                        }
                    });
                }
            });
            if (!PermissionUtil.getAudioPermissions(this, 111)) {
                this.iv_voice.setEnabled(false);
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 111 && grantResults.length > 0 && grantResults[0] == 0) {
            this.iv_voice.setEnabled(true);
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
            case R.id.voicePlayer /* 2131755923 */:
                new VoicePlayer(this, this.voiceFilePath, (MusicPlayer) v).onClick(v);
                this.voiceId = "";
                return;
            case R.id.tv_address /* 2131755924 */:
            case R.id.kv_address /* 2131756167 */:
                startActForResult(ChoiceLocationActivity.class, 1010);
                return;
            case R.id.clear_voice /* 2131755967 */:
                this.exist_voice.setVisibility(4);
                this.voiceId = "";
                return;
            case R.id.kv_type /* 2131755968 */:
                startActForResult(NeedsTypeChoiceActivity.class, 1012);
                return;
            case R.id.kv_mode /* 2131755969 */:
                final String[] unitTexts = {"线上", "线下"};
                new SelectDialog(this.context, "选择服务/共享方式", unitTexts, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.7
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServicePublishActivity.this.kv_mode.setValue(unitTexts[index]);
                        ServicePublishActivity.this.serviceModeId = index;
                    }
                }).show();
                return;
            case R.id.kv_unit /* 2131755971 */:
                final String[] enumUnit = this.context.getResources().getStringArray(R.array.unit_name);
                new SelectDialog(this.context, "选择单位", enumUnit, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.8
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServicePublishActivity.this.kv_unit.setValue(enumUnit[index]);
                    }
                }).show();
                return;
            case R.id.btn_commit /* 2131755972 */:
                commit();
                return;
            case R.id.btn_next /* 2131755974 */:
                nextStep();
                return;
            case R.id.kv_distance /* 2131756150 */:
                final String[] enumDistance = this.context.getResources().getStringArray(R.array.distance);
                new SelectDialog(this.context, "选择范围", enumDistance, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.9
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServicePublishActivity.this.serviceAreaId = index;
                        ServicePublishActivity.this.kv_distance.setValue(enumDistance[index]);
                    }
                }).show();
                return;
            case R.id.kv_state /* 2131756151 */:
                final String[] enumState = this.context.getResources().getStringArray(R.array.service_state);
                new SelectDialog(this.context, "选择状态", enumState, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.6
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServicePublishActivity.this.serviceStateId = index;
                        ServicePublishActivity.this.kv_state.setValue(enumState[index]);
                    }
                }).show();
                return;
            default:
                return;
        }
    }

    public void changeNextPage(boolean toNext) {
        if (toNext) {
            this.fly_lastPage.setVisibility(8);
            this.lly_nextPage.setVisibility(0);
            this.fly_lastPage.setAnimation(AnimationUtils.makeOutAnimation(this, false));
            this.lly_nextPage.setAnimation(AnimationUtils.makeInAnimation(this, false));
        } else {
            this.fly_lastPage.setVisibility(0);
            this.lly_nextPage.setVisibility(8);
            this.fly_lastPage.setAnimation(AnimationUtils.makeInAnimation(this, true));
            this.lly_nextPage.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        }
        this.isNextPage = toNext;
    }

    private void nextStep() {
        this.title = this.et_title.getText().toString();
        this.price = this.et_price.getText().toString().trim();
        this.descripe = this.et_describe.getText().toString();
        this.stockStr = this.ed_stock.getText().toString();
        if (TextUtils.isEmpty(this.title)) {
            MyToast.show(getApp(), (int) R.string.error_no_title);
        } else if (TextUtils.isEmpty(this.address)) {
            MyToast.show(getApp(), "请选择地址");
        } else if (TextUtils.isEmpty(this.typeId)) {
            MyToast.show(getApp(), "请选择服务/共享类型");
        } else if (TextUtils.isEmpty(this.price)) {
            MyToast.show(getApp(), (int) R.string.error_no_price);
        } else if (10.0d > Double.valueOf(this.price).doubleValue()) {
            MyToast.show(getApp(), (int) R.string.toast_input_ten_money);
        } else if (TextUtils.isEmpty(this.stockStr)) {
            MyToast.show(getApp(), "请输入库存数量");
        } else {
            if (!this.notFirstNext) {
                UserInfo userinfo = getUserInfo();
                if (!TextUtils.isEmpty(userinfo.getName())) {
                    this.et_telor.setText(userinfo.getName());
                } else if (!TextUtils.isEmpty(userinfo.getNickName())) {
                    this.et_telor.setText(userinfo.getNickName());
                }
                if (!TextUtils.isEmpty(userinfo.getPhone())) {
                    this.et_tel.setText(userinfo.getPhone());
                }
                this.notFirstNext = true;
            }
            if (this.serviceModeId == 0) {
                this.kv_distance.setVisibility(8);
            } else {
                this.kv_distance.setVisibility(0);
            }
            if (this.myLocation == null) {
                MyToast.show(getApp(), (int) R.string.error_no_location);
            } else {
                changeNextPage(true);
            }
        }
    }

    private void commit() {
        this.telor = this.et_telor.getText().toString().trim();
        this.tel = this.et_tel.getText().toString().trim();
        if (TextUtils.isEmpty(this.ed_address2.getText().toString().trim())) {
            MyToast.show(getApp(), "请填写地址");
        } else if (this.myLocation == null) {
            MyToast.show(getApp(), (int) R.string.error_no_location);
        } else if (TextUtils.isEmpty(this.telor)) {
            MyToast.show(getApp(), (int) R.string.error_no_telor);
        } else if (TextUtils.isEmpty(this.tel)) {
            MyToast.show(getApp(), (int) R.string.error_no_tel);
        } else if (this.serviceModeId == 1 && TextUtils.isEmpty(this.kv_distance.getValue())) {
            MyToast.show(getApp(), (int) R.string.error_no_service_area);
        } else if (this.imagePaths.size() == 0) {
            commitDate(null);
        } else {
            getClient().showDialogNow(R.string.toast_uploading);
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    List<String> allPaths = new ArrayList<>();
                    long ids = System.currentTimeMillis();
                    for (int i = 0; i < ServicePublishActivity.this.imagePaths.size(); i++) {
                        allPaths.add(ImageUtil.compressImage((String) ServicePublishActivity.this.imagePaths.get(i), ids + "_" + i, 1024));
                    }
                    ServicePublishActivity.this.dealEditData();
                }
            });
        }
    }

    public void dealEditData() {
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, 17, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.11
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    ServicePublishActivity.this.getClient().dialogDismiss();
                    MyToast.show(ServicePublishActivity.this.getApplicationContext(), ServicePublishActivity.this.getString(R.string.upload_failed));
                    return;
                }
                MyLog.e("upload", "commitDate=");
                ServicePublishActivity.this.commitDate(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ServicePublishActivity.this.getClient().dialogDismiss();
                MyToast.show(ServicePublishActivity.this.getApplicationContext(), ServicePublishActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    public void upLoadVoice(String voicePath) {
        List<String> voiceList = new ArrayList<>();
        voiceList.add(voicePath);
        new UploadUtils().UploadFileGetUrl(this, "0002_voice/", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, Constant.PICTURE_TYPE_171, voiceList, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.12
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    MyToast.show(ServicePublishActivity.this.getApplicationContext(), ServicePublishActivity.this.getString(R.string.upload_failed));
                    return;
                }
                ServicePublishActivity.this.voiceId = picIds.get(0);
                ServicePublishActivity.this.exist_voice.post(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ServicePublishActivity.this.exist_voice.setVisibility(0);
                    }
                });
                MyLog.e("voiceId=" + ServicePublishActivity.this.voiceId);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ServicePublishActivity.this.getClient().dialogDismiss();
                MyToast.show(ServicePublishActivity.this.getApplicationContext(), ServicePublishActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    public void commitDate(List<String> picIds) {
        AjaxParams params = new AjaxParams();
        if (picIds != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < picIds.size(); i++) {
                if (i == 0) {
                    sb.append(picIds.get(i));
                } else {
                    sb.append("," + picIds.get(i));
                }
            }
            params.put("picUrls", sb.toString());
        }
        if (TextUtils.isEmpty(this.area)) {
            if (!TextUtils.isEmpty(this.myLocation.getProvince())) {
                this.area = this.myLocation.getProvince() + this.myLocation.getCity() + this.myLocation.getDistrict();
            } else if (!TextUtils.isEmpty(this.address)) {
                this.area = this.address.substring(0, this.address.indexOf("市") + 1);
            }
        }
        if (this.serviceModeId == 1) {
            params.put("area", this.serviceAreaId);
        }
        params.put("inventory", this.stockStr);
        params.put("status", this.serviceStateId);
        params.put("payType", 0);
        params.put("voiceUrl", this.voiceId);
        params.put("voiceDuration", this.voiceDuration);
        params.put("title", this.title);
        params.put("description", this.descripe);
        params.put("reward", HyUtil.formatToMoney(this.price));
        params.put("serviceMode", this.serviceModeId);
        params.put("unit", this.kv_unit.getValue().toString());
        params.put("shareTypeId", this.typeId);
        params.put("contactUser", this.telor);
        params.put("contactPhone", this.tel);
        params.put("areaCode", this.myLocation.getAdCode());
        params.put("cityCode", this.myLocation.getCityCode());
        params.put("provinceCode", this.myLocation.getProvince());
        params.put("lat", this.myLocation.getLatitude());
        params.put("lng", this.myLocation.getLongitude());
        params.put("address", this.address);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SERVICE_PUBLISH, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_PUBLISH)), params, DemandDetailBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SERVICE_PUBLISH /* 2131296415 */:
                Bundle bundle = new Bundle();
                bundle.putInt("moId", ((DemandDetailBean) result.getObj()).getMoId());
                if (this.serviceStateId == 1 && Integer.parseInt(this.stockStr) > 0) {
                    bundle.putBoolean("share", true);
                }
                startAct(ServicePublishScucessActivity.class, bundle);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PoiItem poiItem;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    loadAdapter(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdapter(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                case 1010:
                    if (!(data.getBundleExtra("bund") == null || (poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key")) == null)) {
                        LatLonPoint latLng = poiItem.getLatLonPoint();
                        this.area = poiItem.getCityName() + poiItem.getAdName() + poiItem.getBusinessArea();
                        this.address = this.area + poiItem.getSnippet();
                        MyLocation location = new MyLocation();
                        location.setLatitude(latLng.getLatitude() + "");
                        location.setLongitude(latLng.getLongitude() + "");
                        location.setProvince(poiItem.getProvinceName());
                        location.setCityCode(poiItem.getCityCode());
                        location.setAdCode(poiItem.getAdCode());
                        location.setAddress(this.address + "");
                        this.myLocation = location;
                    }
                    this.tv_address.setText(this.address);
                    this.ed_address2.setText(this.address);
                    return;
                case 1012:
                    this.typeId = data.getStringExtra("id");
                    this.kv_type.setValue(data.getStringExtra("name"));
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.isNextPage) {
            changeNextPage(false);
            return;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("放弃编辑");
        dialog.setMessage("本次编辑尚未提交，是否放弃编辑");
        dialog.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog2, int which) {
                ServicePublishActivity.this.finish();
            }
        });
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishActivity.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog2, int which) {
            }
        });
        dialog.show();
    }

    private void loadAdapter(ArrayList<String> paths) {
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        if (this.picAdapter == null) {
            this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths, R.dimen.padding_big);
            this.mgv_pic.setAdapter((ListAdapter) this.picAdapter);
            return;
        }
        this.picAdapter.notifyDataSetChanged();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        VoicePlayer.stopVoice();
        super.onDestroy();
    }
}
