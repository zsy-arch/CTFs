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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.EaseVoiceRecorderView;
import com.em.DemoHelper;
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
import com.vsf2f.f2f.ui.dialog.SelectDialog;
import com.vsf2f.f2f.ui.needs.NeedsTypeChoiceActivity;
import com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity;
import com.vsf2f.f2f.ui.utils.Constant;
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

/* loaded from: classes2.dex */
public class ServiceModifyActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_CODE_ADDRESS = 1010;
    private static final int REQUEST_CODE_TYPE = 1012;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String address;
    private TextView describe_length;
    private String descripe;
    private DemandDetailBean detailBean;
    private EditText ed_stock;
    private String[] enumState;
    private EditText et_describe;
    private EditText et_price;
    private EditText et_title;
    private LinearLayout exist_voice;
    private ImageView iv_voice;
    private KeyValueView kv_distance;
    private KeyValueView kv_mode;
    private KeyValueView kv_state;
    private KeyValueView kv_type;
    private KeyValueView kv_unit;
    private String lat;
    private String lng;
    private MyGridView mgv_pic;
    private int moId;
    private MyLocation myLocation;
    private PicAddBtnAdapter picAdapter;
    private String price;
    private String stock;
    private String title;
    private TextView tv_address;
    private MusicPlayer voicePlayer;
    protected EaseVoiceRecorderView voiceRecorderView;
    private String typeId = "";
    private String voiceId = "";
    private String voicePath = "";
    private String voiceDuration = "";
    private String[] modeTexts = {"线上", "线下"};
    private ArrayList<String> imagePaths = new ArrayList<>();
    private int serviceStateId = 0;
    private int serviceAreaId = 1;
    private int serviceModeId = 0;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_service_modify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.complete_service, 0);
        this.enumState = getResources().getStringArray(R.array.service_state);
        this.moId = getBundle().getInt("moId");
        this.myLocation = (MyLocation) getBundle().getSerializable(Constant.MY_LOCATION);
        if (this.myLocation == null) {
            this.myLocation = new MyLocation();
        }
        this.et_describe = (EditText) getView(R.id.et_describe);
        this.voicePlayer = (MusicPlayer) getViewAndClick(R.id.voicePlayer);
        this.voiceRecorderView = (EaseVoiceRecorderView) getView(R.id.voice_recorder);
        this.describe_length = (TextView) getView(R.id.et_describe_length);
        this.et_title = (EditText) getView(R.id.et_title);
        this.mgv_pic = (MyGridView) getView(R.id.mgv_pic);
        this.et_price = (EditText) getView(R.id.et_price);
        this.ed_stock = (EditText) getView(R.id.ed_stock);
        this.et_price.setFilters(new InputFilter[]{new PointLengthFilter(7)});
        setOnClickListener(R.id.btn_commit);
        this.exist_voice = (LinearLayout) getViewAndClick(R.id.exist_voice);
        this.tv_address = (TextView) getViewAndClick(R.id.tv_address);
        this.kv_mode = (KeyValueView) getViewAndClick(R.id.kv_mode);
        this.kv_unit = (KeyValueView) getViewAndClick(R.id.kv_unit);
        this.kv_type = (KeyValueView) getViewAndClick(R.id.kv_type);
        this.kv_distance = (KeyValueView) getViewAndClick(R.id.kv_distance);
        this.kv_distance.setValue("全市");
        this.kv_state = (KeyValueView) getViewAndClick(R.id.kv_state);
        this.kv_state.setValue("待上架");
        getDetailInfo();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.mgv_pic = (MyGridView) getView(R.id.mgv_pic);
        this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(ServiceModifyActivity.this.context);
                intent.setCurrentItem(i);
                intent.setPhotoPaths(ServiceModifyActivity.this.imagePaths);
                ServiceModifyActivity.this.startActivityForResult(intent, 20);
            }
        });
        this.et_describe.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ServiceModifyActivity.this.describe_length.setText(s.length() + "");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        getView(R.id.iv_voice).setOnTouchListener(new View.OnTouchListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return ServiceModifyActivity.this.voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.3.1
                    @Override // com.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        ServiceModifyActivity.this.voiceDuration = voiceTimeLength + "";
                        ServiceModifyActivity.this.voicePlayer.setDuration(ServiceModifyActivity.this.voiceDuration);
                        ServiceModifyActivity.this.upLoadVoice(voiceFilePath);
                    }
                });
            }
        });
        this.address = this.myLocation.getAddress();
        if (TextUtils.isEmpty(this.address)) {
            this.tv_address.setText("定位失败，请重新选择");
        } else {
            this.tv_address.setText(this.address);
        }
        this.iv_voice = (ImageView) getView(R.id.iv_voice);
        this.iv_voice.setOnTouchListener(new View.OnTouchListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return ServiceModifyActivity.this.voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.4.1
                    @Override // com.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        ServiceModifyActivity.this.voiceDuration = voiceTimeLength + "";
                        ServiceModifyActivity.this.voicePlayer.setDuration(ServiceModifyActivity.this.voiceDuration);
                        ServiceModifyActivity.this.upLoadVoice(voiceFilePath);
                    }
                });
            }
        });
        if (!PermissionUtil.getAudioPermissions(this, 111)) {
            this.iv_voice.setEnabled(false);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 111 && grantResults.length > 0 && grantResults[0] == 0) {
            this.iv_voice.setEnabled(true);
        }
    }

    private void showInfo() {
        if (this.detailBean != null) {
            this.lat = this.detailBean.getLat() + "";
            this.lng = this.detailBean.getLng() + "";
            this.address = this.detailBean.getAddress();
            this.imagePaths.addAll(this.detailBean.getImgUrlList());
            this.et_describe.setText(this.detailBean.getDescription() + "");
            this.et_price.setText(this.detailBean.getReward() + "");
            this.et_title.setText(this.detailBean.getTitle() + "");
            this.kv_unit.setValue(this.detailBean.getUnit());
            this.serviceStateId = this.detailBean.getStatus();
            this.kv_state.setValue(this.enumState[this.serviceStateId]);
            this.ed_stock.setText(this.detailBean.getInventory() + "");
            this.tv_address.setText(this.address + "");
            if (!TextUtils.isEmpty(this.detailBean.getVoiceFullUrl())) {
                this.voiceDuration = this.detailBean.getVoiceDuration();
                this.voicePath = this.detailBean.getVoiceFullUrl();
                this.voicePlayer.setDuration(this.voiceDuration);
                this.exist_voice.setVisibility(0);
            } else {
                this.exist_voice.setVisibility(4);
            }
            this.typeId = this.detailBean.getShareTypeObj().getId() + "";
            this.kv_type.setValue(this.detailBean.getTypeName());
            this.voiceId = this.detailBean.getVoiceUrl();
            this.serviceModeId = this.detailBean.getServiceMode();
            this.kv_mode.setValue(this.modeTexts[this.serviceModeId]);
            if (this.serviceModeId == 1) {
                try {
                    this.serviceAreaId = Integer.parseInt(this.detailBean.getArea());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.kv_distance.setValue(this.detailBean.getAreaStr());
                this.kv_distance.setVisibility(0);
            } else {
                this.kv_distance.setVisibility(8);
            }
            if (HyUtil.isEmpty(this.detailBean.getImgUrlList())) {
                this.imagePaths.addAll(this.detailBean.getImgUrlList());
            }
            this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths, R.dimen.padding_big);
            this.mgv_pic.setAdapter((ListAdapter) this.picAdapter);
            this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.5
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (ServiceModifyActivity.this.imagePaths.size() >= 9 || i != ServiceModifyActivity.this.imagePaths.size()) {
                        PhotoPreviewIntent intent = new PhotoPreviewIntent(ServiceModifyActivity.this.context);
                        intent.setCurrentItem(i);
                        intent.setPhotoPaths(ServiceModifyActivity.this.imagePaths);
                        ServiceModifyActivity.this.startActivityForResult(intent, 20);
                        return;
                    }
                    PhotoPickerIntent intent2 = new PhotoPickerIntent(ServiceModifyActivity.this.context);
                    intent2.setSelectModel(SelectModel.MULTI);
                    intent2.setShowCamera(true);
                    intent2.setMaxTotal(9);
                    intent2.setSelectedPaths(ServiceModifyActivity.this.imagePaths);
                    ServiceModifyActivity.this.startActivityForResult(intent2, 10);
                }
            });
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
                new VoicePlayer(this, this.voicePath, (MusicPlayer) v).onClick(v);
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
                new SelectDialog(this.context, "选择服务/共享方式", this.modeTexts, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.6
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServiceModifyActivity.this.kv_mode.setValue(ServiceModifyActivity.this.modeTexts[index]);
                        ServiceModifyActivity.this.serviceModeId = index;
                        if (index == 1) {
                            ServiceModifyActivity.this.kv_distance.setVisibility(0);
                        } else {
                            ServiceModifyActivity.this.kv_distance.setVisibility(8);
                        }
                    }
                }).show();
                return;
            case R.id.kv_unit /* 2131755971 */:
                final String[] unitTexts2 = this.context.getResources().getStringArray(R.array.unit_name);
                new SelectDialog(this.context, "选择单位", unitTexts2, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.7
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServiceModifyActivity.this.kv_unit.setValue(unitTexts2[index]);
                    }
                }).show();
                return;
            case R.id.btn_commit /* 2131755972 */:
                commit();
                return;
            case R.id.kv_distance /* 2131756150 */:
                final String[] enumDistance = this.context.getResources().getStringArray(R.array.distance);
                new SelectDialog(this.context, "选择范围", enumDistance, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.9
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServiceModifyActivity.this.serviceAreaId = index;
                        ServiceModifyActivity.this.kv_distance.setValue(enumDistance[index]);
                    }
                }).show();
                return;
            case R.id.kv_state /* 2131756151 */:
                new SelectDialog(this.context, "选择状态", this.enumState, new SelectDialog.SelectCallBack() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.8
                    @Override // com.vsf2f.f2f.ui.dialog.SelectDialog.SelectCallBack
                    public void select(int index) {
                        ServiceModifyActivity.this.serviceStateId = index;
                        ServiceModifyActivity.this.kv_state.setValue(ServiceModifyActivity.this.enumState[ServiceModifyActivity.this.serviceStateId]);
                    }
                }).show();
                return;
            default:
                return;
        }
    }

    private void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_SERVICE_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_DETAIL)) + "?shareServiceId=" + this.moId, DemandDetailBean.class);
    }

    private void commit() {
        this.title = this.et_title.getText().toString();
        this.price = this.et_price.getText().toString().trim();
        this.descripe = this.et_describe.getText().toString();
        if (TextUtils.isEmpty(this.title)) {
            MyToast.show(this.context, (int) R.string.error_no_title);
        } else if (TextUtils.isEmpty(this.price)) {
            MyToast.show(this.context, (int) R.string.error_no_price);
        } else if (10.0d > Double.valueOf(this.price).doubleValue()) {
            MyToast.show(this.context, (int) R.string.toast_input_ten_money);
        } else {
            this.stock = this.ed_stock.getText().toString();
            if (TextUtils.isEmpty(this.stock) || Integer.valueOf(this.stock).intValue() <= 0) {
                MyToast.show(getApplicationContext(), "库存数量不能为空 ");
            } else if (this.imagePaths.size() == 0) {
                commitDate(null);
            } else {
                dealEditData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadVoice(String voicePath) {
        List<String> voiceList = new ArrayList<>();
        voiceList.add(voicePath);
        new UploadUtils().UploadFileGetUrl(this, "0002_voice/", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, Constant.PICTURE_TYPE_171, voiceList, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.10
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    MyToast.show(ServiceModifyActivity.this.getApplicationContext(), ServiceModifyActivity.this.getString(R.string.upload_failed));
                    return;
                }
                ServiceModifyActivity.this.voiceId = picIds.get(0);
                ServiceModifyActivity.this.exist_voice.post(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ServiceModifyActivity.this.exist_voice.setVisibility(0);
                    }
                });
                MyLog.e("voiceId=" + ServiceModifyActivity.this.voiceId);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ServiceModifyActivity.this.getClient().dialogDismiss();
                MyToast.show(ServiceModifyActivity.this.getApplicationContext(), ServiceModifyActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }

    private void dealEditData() {
        getClient().showDialogNow(R.string.toast_uploading);
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, 17, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.11
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                ServiceModifyActivity.this.getClient().dialogDismiss();
                ServiceModifyActivity.this.commitDate(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                ServiceModifyActivity.this.getClient().dialogDismiss();
                MyToast.show(ServiceModifyActivity.this.getApp(), ServiceModifyActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                ServiceModifyActivity.this.getClient().showDialogNow(ServiceModifyActivity.this.getString(R.string.toast_uploading_progress, new Object[]{Long.valueOf(currentSize), Long.valueOf(totalSize)}));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitDate(List<String> picIds) {
        getClient().setShowDialog(true);
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
        } else {
            params.put("picUrls", "");
        }
        if (this.serviceModeId == 1) {
            params.put("area", this.serviceAreaId);
        }
        if (!TextUtils.isEmpty(this.voiceId)) {
            params.put("voiceUrl", this.voiceId);
            params.put("voiceDuration", this.voiceDuration);
        } else {
            params.put("voiceUrl", "");
            params.put("voiceDuration", "");
        }
        params.put("unit", this.kv_unit.getValue().toString());
        params.put("inventory", this.stock);
        params.put("title", this.title);
        params.put("moId", this.detailBean.getMoId());
        params.put("description", this.descripe);
        params.put("reward", HyUtil.formatToMoney(this.price));
        params.put("serviceMode", this.serviceModeId);
        params.put("shareTypeId", this.typeId);
        params.put("status", this.serviceStateId);
        if (this.myLocation != null) {
            params.put("lat", this.lat);
            params.put("lng", this.lng);
            params.put("address", this.address);
            params.put("provinceCode", this.myLocation.getProvince());
            params.put("cityCode", this.myLocation.getCityCode());
            params.put("areaCode", this.myLocation.getAdCode());
        }
        getClient().post(R.string.API_SERVICE_PUBLISH, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_PUBLISH)), params, DemandDetailBean.class);
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
                    if (data.getBundleExtra("bund") != null) {
                        this.myLocation = new MyLocation();
                        PoiItem poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key");
                        LatLonPoint latLng = poiItem.getLatLonPoint();
                        this.lat = latLng.getLatitude() + "";
                        this.lng = latLng.getLongitude() + "";
                        this.address = poiItem.getCityName() + poiItem.getAdName() + poiItem.getBusinessArea() + poiItem.getSnippet();
                        this.myLocation.setLatitude(this.lat + "");
                        this.myLocation.setLongitude(this.lng + "");
                        this.myLocation.setProvince(poiItem.getProvinceName());
                        this.myLocation.setCityCode(poiItem.getCityCode());
                        this.myLocation.setAdCode(poiItem.getAdCode());
                        this.myLocation.setAddress(this.address + "");
                    }
                    this.tv_address.setText(this.address + "");
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

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("放弃编辑");
        dialog.setMessage("本次编辑尚未提交，是否放弃编辑");
        dialog.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog2, int which) {
                ServiceModifyActivity.this.finish();
            }
        });
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog2, int which) {
            }
        });
        dialog.show();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
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

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_SERVICE_DETAIL /* 2131296412 */:
                this.detailBean = (DemandDetailBean) result.getObj();
                showInfo();
                return;
            case R.string.API_SERVICE_HELP /* 2131296413 */:
            case R.string.API_SERVICE_HOMEPAGE /* 2131296414 */:
            default:
                return;
            case R.string.API_SERVICE_PUBLISH /* 2131296415 */:
                new EaseAlertDialog(this, R.string.service_modify_scucess, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServiceModifyActivity.14
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        ServiceModifyActivity.this.setResult(-1);
                        if (confirmed) {
                            ServiceModifyActivity.this.finish();
                        }
                    }
                }, false).show();
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        VoicePlayer.stopVoice();
        super.onDestroy();
    }
}
