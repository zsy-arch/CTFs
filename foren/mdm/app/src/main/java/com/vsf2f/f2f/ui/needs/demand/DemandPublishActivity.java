package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.dialog.SelectDialog;
import com.vsf2f.f2f.ui.dialog.TimeChoiceDialog;
import com.vsf2f.f2f.ui.needs.NeedsTypeChoiceActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class DemandPublishActivity extends BaseActivity implements TimeChoiceDialog.TimeCallBack {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_CODE_ADDRESS = 1010;
    private static final int REQUEST_CODE_TYPE = 1012;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String address;
    private TextView describe_length;
    private String descripe;
    private EditText ed_address2;
    private EditText et_describe;
    private EditText et_price;
    private EditText et_tel;
    private EditText et_telor;
    private EditText et_title;
    private LinearLayout exist_voice;
    private FrameLayout fly_lastPage;
    boolean isAbroad;
    private boolean isNextPage;
    private ImageView iv_voice;
    private KeyValueView kv_mode;
    private KeyValueView kv_time;
    private KeyValueView kv_type;
    private KeyValueView kv_unit;
    private LinearLayout lly_nextPage;
    private MyGridView mgv_pic;
    private int modeNo;
    private boolean notFirstNext;
    private PicAddBtnAdapter picAdapter;
    private String price;
    private String tel;
    private String telor;
    private TimeChoiceDialog timeChoiceDialog;
    private String title;
    private TextView tv_address;
    private MusicPlayer voicePlayer;
    protected EaseVoiceRecorderView voiceRecorderView;
    private String area = "";
    private String typeId = "";
    private String voiceId = "";
    private String voiceDuration = "";
    private String voiceFilePath = "";
    private ArrayList<String> imagePaths = new ArrayList<>();
    private MyLocation myLocation = new MyLocation();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_publish;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.publish_demand, 0);
        this.voiceRecorderView = (EaseVoiceRecorderView) getView(R.id.voice_recorder);
        this.describe_length = (TextView) getView(R.id.et_describe_length);
        this.et_describe = (EditText) getView(R.id.et_describe);
        this.fly_lastPage = (FrameLayout) getView(R.id.fly_lastPage);
        this.lly_nextPage = (LinearLayout) getView(R.id.lly_nextPage);
        this.exist_voice = (LinearLayout) getView(R.id.exist_voice);
        this.voicePlayer = (MusicPlayer) getViewAndClick(R.id.voicePlayer);
        this.mgv_pic = (MyGridView) getView(R.id.mgv_pic);
        this.et_title = (EditText) getView(R.id.et_title);
        this.et_tel = (EditText) getView(R.id.et_tel);
        this.et_telor = (EditText) getView(R.id.et_telor);
        this.et_price = (EditText) getView(R.id.et_price);
        this.et_price.setFilters(new InputFilter[]{new PointLengthFilter(7)});
        this.tv_address = (TextView) getViewAndClick(R.id.tv_address);
        this.ed_address2 = (EditText) getViewAndClick(R.id.ed_address2);
        this.kv_mode = (KeyValueView) getViewAndClick(R.id.kv_mode);
        this.kv_unit = (KeyValueView) getViewAndClick(R.id.kv_unit);
        this.kv_type = (KeyValueView) getViewAndClick(R.id.kv_type);
        this.kv_time = (KeyValueView) getViewAndClick(R.id.kv_time);
        setOnClickListener(R.id.clear_voice);
        setOnClickListener(R.id.btn_commit);
        setOnClickListener(R.id.btn_next);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (UserShared.getInstance().getIsVerifyState(this.context)) {
            if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            DemandPublishActivity.this.startActForResult(BindPhoneActivity.class, 0);
                        }
                        DemandPublishActivity.this.finish();
                    }
                }, true).show();
                return;
            }
            this.timeChoiceDialog = new TimeChoiceDialog(this, this);
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
            this.et_describe.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.2
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    DemandPublishActivity.this.describe_length.setText(s.length() + "");
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }
            });
            this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths, R.dimen.padding_big);
            this.mgv_pic.setAdapter((ListAdapter) this.picAdapter);
            this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (DemandPublishActivity.this.imagePaths.size() >= 9 || i != DemandPublishActivity.this.imagePaths.size()) {
                        PhotoPreviewIntent intent = new PhotoPreviewIntent(DemandPublishActivity.this.context);
                        intent.setCurrentItem(i);
                        intent.setPhotoPaths(DemandPublishActivity.this.imagePaths);
                        DemandPublishActivity.this.startActivityForResult(intent, 20);
                        return;
                    }
                    PhotoPickerIntent intent2 = new PhotoPickerIntent(DemandPublishActivity.this.context);
                    intent2.setSelectModel(SelectModel.MULTI);
                    intent2.setShowCamera(true);
                    intent2.setMaxTotal(9);
                    intent2.setSelectedPaths(DemandPublishActivity.this.imagePaths);
                    DemandPublishActivity.this.startActivityForResult(intent2, 10);
                }
            });
            this.iv_voice = (ImageView) getView(R.id.iv_voice);
            this.iv_voice.setOnTouchListener(new View.OnTouchListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.4
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    return DemandPublishActivity.this.voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.4.1
                        @Override // com.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback
                        public void onVoiceRecordComplete(String voiceFilePath2, int voiceTimeLength) {
                            DemandPublishActivity.this.voiceDuration = voiceTimeLength + "";
                            DemandPublishActivity.this.voicePlayer.setDuration(DemandPublishActivity.this.voiceDuration);
                            DemandPublishActivity.this.voiceFilePath = voiceFilePath2;
                            DemandPublishActivity.this.upLoadVoice(DemandPublishActivity.this.voiceFilePath);
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
                new SelectDialog(this.context, "选择服务/共享方式", unitTexts, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.5
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        DemandPublishActivity.this.kv_mode.setValue(unitTexts[index]);
                        DemandPublishActivity.this.modeNo = index;
                    }
                }).show();
                return;
            case R.id.kv_unit /* 2131755971 */:
                final String[] unitTexts2 = this.context.getResources().getStringArray(R.array.unit_name);
                new SelectDialog(this.context, "选择单位", unitTexts2, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.6
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        DemandPublishActivity.this.kv_unit.setValue(unitTexts2[index]);
                    }
                }).show();
                return;
            case R.id.btn_commit /* 2131755972 */:
                commit();
                return;
            case R.id.btn_next /* 2131755974 */:
                nextStep();
                return;
            case R.id.kv_time /* 2131755978 */:
                this.timeChoiceDialog.show();
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
        if (TextUtils.isEmpty(this.title)) {
            MyToast.show(getApp(), (int) R.string.error_no_title);
        } else if (TextUtils.isEmpty(this.typeId)) {
            MyToast.show(getApp(), "请选择需求类型");
        } else if (TextUtils.isEmpty(this.price)) {
            MyToast.show(getApp(), (int) R.string.error_no_price);
        } else if (10.0d > Double.valueOf(this.price).doubleValue()) {
            MyToast.show(getApp(), (int) R.string.toast_input_ten_money);
        } else {
            if (!this.notFirstNext) {
                palyTime(30);
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
        } else if (TextUtils.isEmpty(this.myLocation.getLatitude())) {
            MyToast.show(getApp(), (int) R.string.error_no_location);
        } else if (TextUtils.isEmpty(this.telor)) {
            MyToast.show(getApp(), (int) R.string.error_no_telor);
        } else if (TextUtils.isEmpty(this.tel)) {
            MyToast.show(getApp(), (int) R.string.error_no_tel);
        } else if (TextUtils.isEmpty(this.kv_time.getValue())) {
            MyToast.show(getApp(), (int) R.string.error_no_time);
        } else if (this.imagePaths.size() == 0) {
            commitDate(null);
        } else {
            getClient().showDialogNow(R.string.toast_uploading);
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    List<String> allPaths = new ArrayList<>();
                    long ids = System.currentTimeMillis();
                    for (int i = 0; i < DemandPublishActivity.this.imagePaths.size(); i++) {
                        allPaths.add(ImageUtil.compressImage((String) DemandPublishActivity.this.imagePaths.get(i), ids + "_" + i, 1024));
                    }
                    DemandPublishActivity.this.dealEditData();
                }
            });
        }
    }

    public void dealEditData() {
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, 17, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.8
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    DemandPublishActivity.this.getClient().dialogDismiss();
                    MyToast.show(DemandPublishActivity.this, DemandPublishActivity.this.getString(R.string.upload_failed));
                    return;
                }
                DemandPublishActivity.this.commitDate(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                DemandPublishActivity.this.getClient().dialogDismiss();
                MyToast.show(DemandPublishActivity.this, DemandPublishActivity.this.getString(R.string.upload_failed));
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
        new UploadUtils().UploadFileGetUrl(this, "0002_voice/", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, Constant.PICTURE_TYPE_171, voiceList, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.9
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    MyToast.show(DemandPublishActivity.this.getApplicationContext(), DemandPublishActivity.this.getString(R.string.upload_failed));
                    return;
                }
                DemandPublishActivity.this.voiceId = picIds.get(0);
                DemandPublishActivity.this.exist_voice.post(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DemandPublishActivity.this.exist_voice.setVisibility(0);
                    }
                });
                MyLog.e("voiceId=" + DemandPublishActivity.this.voiceId);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                DemandPublishActivity.this.getClient().dialogDismiss();
                MyToast.show(DemandPublishActivity.this.getApplicationContext(), DemandPublishActivity.this.getString(R.string.upload_failed));
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
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(picIds.get(i));
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
        params.put("area", this.area);
        params.put("payType", 0);
        params.put("voiceUrl", this.voiceId);
        params.put("voiceDuration", this.voiceDuration);
        params.put("title", this.title);
        params.put("description", this.descripe);
        params.put("unit", this.kv_unit.getValue().toString());
        params.put("reward", HyUtil.formatToMoney(this.price));
        params.put("serviceMode", this.modeNo);
        params.put("shareTypeId", this.typeId);
        params.put("contactUser", this.telor);
        params.put("contactPhone", this.tel);
        params.put("expireTime", DateUtils.parseLongDate(this.kv_time.getValue().toString()));
        params.put("areaCode", this.myLocation.getAdCode());
        params.put("cityCode", this.myLocation.getCityCode());
        params.put("provinceCode", this.myLocation.getProvince());
        params.put("lat", this.myLocation.getLatitude());
        params.put("lng", this.myLocation.getLongitude());
        params.put("address", this.address);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DEMAND_PUBLISH, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_PUBLISH)), params, ApplyPayInfoBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        int requestCode = result.getRequestCode();
        if (result.getErrorCode() == 0) {
            switch (requestCode) {
                case R.string.API_DEMAND_PUBLISH /* 2131296330 */:
                    ApplyPayInfoBean bean = (ApplyPayInfoBean) result.getObj();
                    bean.setBizId(bean.getMoId() + "");
                    bean.setPayTitle(getString(R.string.bail_demand));
                    Bundle bundle = new Bundle();
                    bundle.putInt("moId", bean.getMoId());
                    bundle.putBoolean("isAbroad", this.isAbroad);
                    bundle.putSerializable("payinfo", bean);
                    startAct(DemandPublishScucessActivity.class, bundle);
                    finish();
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
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
                case 1010:
                    Bundle bundle = data.getBundleExtra("bund");
                    if (bundle != null) {
                        PoiItem poiItem = (PoiItem) bundle.getParcelable("key");
                        this.isAbroad = bundle.getBoolean("isAbroad");
                        if (poiItem != null) {
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
        } else {
            new EaseAlertDialog(this.context, "放弃编辑", "本次编辑尚未提交，是否放弃编辑", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity.10
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        DemandPublishActivity.this.finish();
                    }
                }
            }, true).show();
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        if (this.picAdapter == null) {
            this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths);
            this.mgv_pic.setAdapter((ListAdapter) this.picAdapter);
            return;
        }
        this.picAdapter.notifyDataSetChanged();
    }

    @Override // com.vsf2f.f2f.ui.dialog.TimeChoiceDialog.TimeCallBack
    public void palyTime(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, time);
        this.kv_time.setValue(calendar.get(1) + "-" + DateUtils.fillZero(calendar.get(2) + 1) + "-" + DateUtils.fillZero(calendar.get(5)) + " 23:59:59");
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        VoicePlayer.stopVoice();
    }
}
